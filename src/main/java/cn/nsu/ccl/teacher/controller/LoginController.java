package cn.nsu.ccl.teacher.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;

@Controller
public class LoginController {
	@Autowired
	private ServiceManager service;

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
	//登录成功后跳转到登录成功界面
	@RequestMapping(value="teacherIndex")
	public String toIndex(){
		return "teacher/index";
	}
	//显示index中掺杂的欢迎界面
	@RequestMapping(value="teacherWelcome")
	public String welcome(){
		return "teacher/welcome";
	}
	
}
