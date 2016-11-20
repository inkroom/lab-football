package cn.nsu.ccl.teacher.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.nsu.ccl.teacher.service.ServiceManager;

/**
 * 
 * <p>ExamController类的描述</p>
 * @ClassName: ExamController
 * @Description: TODO实现与题库相关的操作
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午1:58:27
 */
@Controller
public class QuestionLibController {
	@Autowired
	private ServiceManager service;
	/**
	 * 
	 * <p>downloadExamDemo方法的描述</p>
	 * @Title: ExamController的downloadExamDemo方法
	 * @Description: TODO实现教师登录操作
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月19日 下午1:58:49
	 * @param session
	 * @return
	 */
	@RequestMapping(value="downloadQuestionLibDemo")
	public ResponseEntity<byte[]> downloadExamDemo(HttpSession session){
		String path = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/examDemo.xlsx";
		File file = new File(path);
	    HttpHeaders headers = new HttpHeaders();
	    String fileName = null;
	    try {
	    	//强制转码，避免下载文件名无法使用中文
	    	fileName  = new String("在线考试系统题库模版.xlsx".getBytes("utf-8"), "iso-8859-1");
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
	/**
	 * 
	 * <p>toCreatExam方法的描述</p>
	 * @Title: ExamController的toCreatExam方法
	 * @Description: TODO跳转到教师创建题库信息界面
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月19日 下午2:01:47
	 * @return
	 */
	@RequestMapping(value="teacherCreateExam")
	public String toCreatExam(){
		return "teacher/questionLib/createQuestionLib";
	}
//	/**
//	 * 
//	 * <p>createExam方法的描述</p>
//	 * @Title: ExamController的createExam方法
//	 * @Description: TODO实现创建题库的操作
//	 * @author 暴沸 baofeidyz@foxmail.com
//	 * @date 2016年11月19日 下午5:04:03
//	 * @param file
//	 * @param session
//	 */
//	@RequestMapping(value="teacherCreateExamDo",method=RequestMethod.POST)
//	public void createExam(@RequestParam("file") CommonsMultipartFile file,String examName,HttpSession session){
//		//调用service上传到文件，并返回路径
//		String filePath = service.getExamService().updateExamExcel(file,session);
//		//验证文件是否按照要求进行编写
//		if (service.getExamService().checkExamExcel(filePath)) {
//			//将数据存储到数据库
//			
//		}
//	}
}
