/**
 * <p>ExamingController.java文件的详细描述:
 * 实现与监考相关的相关操作
 * </p>
 * @Title: ExamManagerController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午4:11:27
 * @version V1.0
 */
package cn.nsu.ccl.teacher.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.ExamingInfoEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>ExamingController类的描述
 *	实现与开始考试操作的相关功能
 * </p>
 * @ClassName: ExamingController
 * @Description: TODO
 * @author: 暴沸
 * @email: baofeidyz@foxmail.com
 * @date 2016年11月27日 下午8:48:19
 */
@Controller
public class ExamingController {
	@Autowired
	private ServiceManager service;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 
	 * <p>toStatrExam方法的描述：
	 * 实现展示考试信息列表，并可以点击进入考试
	 * </p>
	 * @Title: ExamingController的toStatrExam方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:07:44
	 * @return
	 */
	@RequestMapping(value="teacherStartExam")
	public String toStatrExam(){
		//从session获取教师邮箱帐号
				String teacherEmail = (String) session.getAttribute("teacherEmail");
				//通过教师邮箱帐号获取该教师所创建的考试信息列表
				ArrayList<ExamInfoEntity> list = service.getExamService().getExamInfoByTeacherEmail(teacherEmail);
				//根据examid获取是否有考生信息
				//1.遍历考试信息
				//2.新建一个jsonArray用于存储所有的对应信息
				JSONArray jsonArray = new JSONArray();
				for(int i = 0; i < list.size();i++){
					ExamInfoEntity entity = list.get(i);
					//3.通过考试id获取该场考试是否有对应的考生信息
					if (service.getExamService().isStudentInfoExistByExamId(entity.getExamId())) {
						//存在考生信息
						//4.新建一个jsonobject对象用于存储信息
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("examId", entity.getExamId());
						jsonObject.put("state", "exist");
						jsonArray.add(jsonObject);
					}else{
						//4.新建一个jsonobject对象用于存储信息
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("examId", entity.getExamId());
						jsonObject.put("state", "null");
						jsonArray.add(jsonObject);
					}
				}
				request.setAttribute("examInfoList", list);
				request.setAttribute("jsonArray", jsonArray.toString());
				return "teacher/examing/examList";
	}
	/**
	 * 
	 * <p>startExam方法的描述：
	 * 从界面端传完一个考试id，然后按照这个考试id开始考试
	 * </p>
	 * @Title: ExamingController的startExam方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:07:53
	 * @param examId
	 * @return
	 */
	@RequestMapping(value="teacherStartExamDo")
	public String startExam(int examId){
		//1.通过传入的考试id获取该场考试信息
		ArrayList<ExamingInfoEntity> examingList = service.getExamingService().getExaming(examId);
		//2.将获取到的考试信息反馈到jsp
		request.setAttribute("examList", examingList);
		//3.将考试id反馈到jsp，用于前端界面获取教师口令
		request.setAttribute("examId", examId);
		return "teacher/examing/examing";
	}
	
	/**
	 * 
	 * <p>getTeacherToken方法的描述：
	 * 实现教师获取教师口令的功能
	 * </p>
	 * @Title: ExamingController的getTeacherToken方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:08:01
	 * @param examId
	 * @param response
	 */
	@RequestMapping(value="teacherGetToken")
	public void getTeacherToken(int examId,HttpServletResponse response){
		//新建一个jsonobject对象用于存储教师口令
		JSONObject jsonObject = new JSONObject();
		//通过会话session获取到教师的邮箱帐号
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//通过传入的考试id去获取教师口令
		String token = service.getExamingService().createToken(teacherEmail, examId);
		jsonObject.put("token", token);
		//发送到前端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
