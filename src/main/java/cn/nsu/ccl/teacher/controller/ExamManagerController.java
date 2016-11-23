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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;

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
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	
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
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//获取题库列表信息
		ArrayList<QuestionLibListEntity> questionLibList = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		//将题库信息存到请求request中
		request.setAttribute("questionLibList", questionLibList);
		return "teacher/exam/createExamInfo";
	}
	@RequestMapping(value="teacherCreateExamDo",method=RequestMethod.POST)
	public void createExam(int libraryId ,String examName,
			String examStartTime,String examEndTime,
			String danNum,String danScore,
			String duoNum,String duoScore,
			String pNum,String pScore,HttpServletResponse response){
		//新建一个JsonObject对象，用于存储返回给前台界面的信息
		JSONObject jsonObject = new JSONObject();
		//创建一个考试信息的对象
		ExamInfoEntity examInfoEntity = new ExamInfoEntity();
		//添加老师邮箱属性信息
		examInfoEntity.setTeacherId((String)session.getAttribute("teacherEmail"));
		//添加题库id属性信息
		examInfoEntity.setQuestionListNumber(libraryId);
		//添加考试名称属性信息
		examInfoEntity.setExamName(examName);
		//添加考试开始时间属性信息
		examInfoEntity.setStartTime(examStartTime);
		//添加考试结束时间属性信息
		examInfoEntity.setEndTime(examEndTime);
		//添加单选题个数属性信息
		examInfoEntity.setChoiceNumber(danNum);
		//添加单选题分数属性信息
		examInfoEntity.setChoiceScore(duoScore);
		//添加多选题个数属性信息
		examInfoEntity.setMultiputeChoiceNumber(duoNum);
		//添加多选题分数属性信息
		examInfoEntity.setMultiputeChoiceScore(duoScore);
		//添加判断题个数属性信息
		examInfoEntity.setJudgeNumber(pNum);
		//添加判断题分数属性信息
		examInfoEntity.setJudgeScore(pScore);
		if (service.getExamService().addExamInfo(examInfoEntity)) {
			jsonObject.put("state", "success");
		}else{
			jsonObject.put("state", "fail");
		}
		//设置返回的数据格式
		response.setContentType("application/json");
		//修改编码为UTF-8
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("发回成功或者失败的状态");
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "teacherUploadStudentExcel")  
  public String upLoadFile(HttpServletRequest request) {  
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		
		String path = request.getServletContext().getRealPath("/")+"WEB-INF/teacher/";
      // @RequestParam("file") MultipartFile file,  
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
              request.getSession().getServletContext());
      // 判断 request 是否有文件上传,即多部分请求  
      if (multipartResolver.isMultipart(request)) { 
          // 转换成多部分request  
          MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
          // 取得request中的所有文件名  
          Iterator<String> iter = multiRequest.getFileNames();  
          while (iter.hasNext()) {  
              // 取得上传文件  
              MultipartFile f = multiRequest.getFile(iter.next());  
              if (f != null) {  
                  // 取得当前上传文件的文件名称  
                  String myFileName = f.getOriginalFilename();  
                  // 如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                  if (myFileName.trim() != "") {  
                      // 定义上传路径  
                      path =  path + myFileName;  
                      File localFile = new File(path);  
                      try {
						f.transferTo(localFile);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}  
                  }  
              }  
          }
          
          //接收从excel转回的list集合
  		ArrayList<StudentInfoEntity> list = service.getExamService().excelToList(path);
  		for(int i = 0; i < list.size(); i++){
  			StudentInfoEntity studentInfoEntity = list.get(i);
  			System.out.println(studentInfoEntity.getStudentId());
  			System.out.println(studentInfoEntity.getStudentName());
  			if (!service.getExamService().addStudentInfo(studentInfoEntity,teacherEmail)) {
  				return "";
  			}
  		}
      }
		return "examManager/views/uploadSuccess.jsp";  
  } 
	
	
	
}
