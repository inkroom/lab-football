/**
 * <p>ExamingController.java文件的详细描述</p>
 * @Title: ExamingController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月25日 上午8:43:08
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
 * 处理开始考试的Controller
 * </p>
 * @ClassName: ExamingController
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月25日 上午8:43:08
 */
@Controller
public class ExamingController {
	@Autowired
	private ServiceManager service;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	
	
	@RequestMapping(value="teacherStartExam")
	public String toStatrExam(){
		//1.获取会话seesion中的教师邮箱
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//2.通过教师邮箱获取考试列表信息
		ArrayList<ExamInfoEntity> list = service.getExamService().getExamInfoByTeacherEmail(teacherEmail);
		//3.将获取到的考试信息存放于请求request中
		request.setAttribute("examList", list);
		//4.跳转到examing/choseExam.jsp
		return "teacher/examing/choseExam";
	}
	@RequestMapping(value="teacherStatrExamShowExamList")
	public String getExamList(){
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
