/**
 * <p>ExamManagerController.java文件的详细描述</p>
 * @Title: ExamManagerController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午4:11:27
 * @version V1.0
 */
package cn.nsu.ccl.teacher.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;

/**
 * <p>ExamManagerController类的描述</p>
 * @ClassName: ExamManagerController
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午4:11:27
 */
@Controller
public class ExamManagerController {
	@Autowired
	private ServiceManager service;
	private HttpServletRequest request;
	private HttpSession session;
	
	/**
	 * 
	 * <p>跳转到创建考试信息界面</p>
	 * @Title: ExamManagerController的toCreateExam方法
	 * @Description: TODO
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午4:47:48
	 * @return
	 */
	@RequestMapping(value="teacherCreateExam")
	public String toCreateExam(){
		//获取已经登录的教师姓名
	
		String teacherEmail = (String)session.getAttribute("teacherEmail");
		System.out.println("teacherEmail="+teacherEmail);
		//获取题库列表信息
		ArrayList<QuestionLibListEntity> questionLibList = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		System.out.println(questionLibList.size());
		//将题库信息存到请求request中
		request.setAttribute("questionLibList", questionLibList);
		return "teacher/exam/createExamInfo";
	}
	
	
	
}
