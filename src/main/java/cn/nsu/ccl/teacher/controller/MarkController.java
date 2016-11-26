/**
 * <p>MarkController.java文件的详细描述</p>
 * @Title: MarkController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月26日 下午12:58:05
 * @version V1.0
 */
package cn.nsu.ccl.teacher.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>MarkController类的描述</p>
 * @ClassName: MarkController
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月26日 下午12:58:05
 */
@Controller
public class MarkController {
	@Autowired
	private ServiceManager service;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="teacherShowMark")
	public String toShowMarkList(){
		//1.获取会话seesion中的教师邮箱
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//2.通过教师邮箱获取考试列表信息
		ArrayList<ExamInfoEntity> list = service.getExamService().getExamInfoByTeacherEmail(teacherEmail);
		//3.寻找考试信息，遍历寻找是否可以导出成绩
		//新建一个jsonArray用于存储最后的结果
		JSONArray jsonArray = new JSONArray();
		//3.1遍历所有的考试信息
		for(int i = 0; i < list.size();i++){
			//3.2获取到对应的考试对象
			ExamInfoEntity examInfoEntity = list.get(i);
			//3.3获取当前时间
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//3.4使用当前时间与考试结束时间相匹配
			if (now.compareTo(examInfoEntity.getEndTime())>0) {
				//3.4.1此处证明当前时间大于考试结束时间，代表在现在时刻，考试已经结束
				JSONObject jsonObject = new JSONObject();
				//3.4.1.1存入考试id
				jsonObject.put("examId", examInfoEntity.getExamId());
				//3.4.1.2存入考试状态信息
				jsonObject.put("state", "end");
				jsonArray.add(jsonObject);
			}else{
				//3.4.2证明此处不满足，则存入notEnd
				JSONObject jsonObject = new JSONObject();
				//3.4.2.1存入考试id
				jsonObject.put("examId", examInfoEntity.getExamId());
				//3.4.2.2存入考试状态信息
				jsonObject.put("state","notEnd");
				jsonArray.add(jsonObject);
			}
		}
		//4.1将数据存到request中
		request.setAttribute("examInfoList", list);
		//4.2将获取到的考试信息存放于请求request中
		request.setAttribute("examState", jsonArray);
		//5.跳转到mark/examList.jsp
		return "teacher/mark/examList";
	}
	
	@RequestMapping(value="teacherDownloadMark")
	public ResponseEntity<byte[]> downloadExamDemo(int examId){
		//获取项目的上下文路径
		String contextPath = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/mark";
		//从会话session中获取教师邮箱帐号
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//获取考试id对应的考试名称examName
		ArrayList<ExamInfoEntity> examList = service.getExamService().getExamInfoByTeacherEmail(teacherEmail);
		//初始化一个String类型的变量用于存储考试名称
		String examName = null;
		//遍历集合找到对应的考试名称
		for(int i = 0; i < examList.size();i++){
			ExamInfoEntity examInfoEntity = examList.get(i);
			if(examInfoEntity.getExamId()==examId){
				examName = examInfoEntity.getExamName();
			}
		}
		//根据传入的考试id找到对应成绩信息的EXCEL地址
		String markPath = service.getMarkServiceImpl().getScoreExcel(examId, contextPath,examName);
		File file = new File(markPath);
	    HttpHeaders headers = new HttpHeaders();
	    String fileName = examName+"考试成绩.xlsx";
	    try {
	    	//强制转码，避免下载文件名无法使用中文
	    	fileName  = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	    headers.setContentDispositionFormData("attachment", fileName);
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    try {
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	    } catch (IOException e) {
	    	
	    }
	    return null;
	}
	
}
