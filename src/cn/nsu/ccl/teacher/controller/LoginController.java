/**
 * <p>LoginController.java文件的详细描述：
 * 实现与教师登录注销修改密码相关的操作
 * </p>
 * @Title: ExamManagerController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午4:11:27
 * @version V1.0
 */
package cn.nsu.ccl.teacher.controller;


import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 
 * <p>LoginController类的描述
 *	实现与教师登录注销修改密码等操作
 * </p>
 * @ClassName: LoginController
 * @Description: TODO
 * @author: 暴沸
 * @email: baofeidyz@foxmail.com
 * @date 2016年11月27日 下午8:51:25
 */
@Controller
public class LoginController {
	@Autowired
	private ServiceManager service;
	
	/**
	 * 
	 * <p>toLogin方法的描述
	 * 跳转到教师登录界面
	 * </p>
	 * @Title: LoginController的toLogin方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:51:42
	 * @return
	 */
	@RequestMapping(value="teacher")
	public String toLogin(){
		return "teacher/login";
	}
	/**
	 * 
	 * <p>login方法的描述--实现教师登录操作</p>
	 * @Title: LoginController的login方法
	 * @Description: TODO
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:40:25
	 * @param teacherName
	 * @param teacherPassword
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="teacherLogin",method=RequestMethod.POST)
	public void login(String teacherName,String teacherPassword,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject();
		//teacherName代表教师姓名，teacherPassword代表教师的登录密码
		if (service.getTeacherService().login(teacherName, teacherPassword)) {
			//存入session一个登录成功信息
			session.setAttribute("state", "success");
			//将教师邮箱存入session
			session.setAttribute("teacherEmail", teacherName);
			//登录成功则在jsonObject中存入state表示登录成功，并传回前端表示登录成功
			jsonObject.put("state", "success");
		}else{
			jsonObject.put("state", "fail");
		}
		//设置返回的数据格式
		response.setContentType("application/json");
		//修改编码为UTF-8
		response.setCharacterEncoding("UTF-8");
		try {
			//将json数据传回前端
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>toIndex方法的描述
	 * 登录以后跳转到教师主页（与登录后直接跳转相比，分离这两个操作的目的主要是为了刷新的时候不至于重新提交表单，只要能通过会话过滤器则可以访问）
	 * </p>
	 * @Title: LoginController的toIndex方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:52:00
	 * @return
	 */
	@RequestMapping(value="teacherIndex")
	public String toIndex(){
		return "teacher/index";
	}
	/**
	 * 
	 * <p>welcome方法的描述
	 * 显示在教师主页框架右侧的内容
	 * </p>
	 * @Title: LoginController的welcome方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:53:07
	 * @return
	 */
	@RequestMapping(value="teacherWelcome")
	public String welcome(){
		return "teacher/welcome";
	}
	
	/**
	 * 
	 * <p>singOut方法的描述
	 * 实现教师的退出系统操作
	 * </p>
	 * @Title: LoginController的singOut方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:53:29
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherExit")
	public String singOut(HttpSession session){
		//结束会话
		session.invalidate();
		return "redirect:teacher";
	}
	/**
	 * 
	 * <p>toChangePassword方法的描述
	 * 跳转到教师修改密码的界面
	 * </p>
	 * @Title: LoginController的toChangePassword方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:53:52
	 * @return
	 */
	@RequestMapping(value="teacherChangePassword")
	public String toChangePassword(){
		return "teacher/changePassword";
	}
	/**
	 * 
	 * <p>changePassword方法的描述
	 * 实现教师修改密码的操作
	 * </p>
	 * @Title: LoginController的changePassword方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午8:54:06
	 * @param oldPasswd
	 * @param newPasswd
	 * @param session
	 * @param response
	 */
	@RequestMapping(value="teacherChangePasswordDo",method=RequestMethod.POST)
	public void changePassword(String oldPasswd,String newPasswd,HttpSession session,HttpServletResponse response){
		
		//获取教师登录信息（教师邮箱帐号）
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//新建一个jsonobject存储信息
		JSONObject jsonObject = new JSONObject();
		//调用对应的service方法修改密码
		if (service.getTeacherService().updatePassword(teacherEmail, oldPasswd, newPasswd)) {
			jsonObject.put("state","success");
			//并且销毁会话信息
			session.invalidate();
		}else{
			jsonObject.put("state", "fail");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
