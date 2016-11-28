/**
 * <p>ExamManagerController.java文件的详细描述：
 * 完成与考试管理相关的操作
 * </p>
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONArray;
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
	 * <p>toCreateExam方法的描述：
	 * 跳转到创建考试信息界面
	 * </p>
	 * @Title: ExamManagerController的toCreateExam方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:10:45
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
	/**
	 * 
	 * <p>createExam方法的描述：
	 * 完成创建考试信息（不包括学生信息）
	 * </p>
	 * @Title: ExamManagerController的createExam方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:10:57
	 * @param libraryId
	 * @param examName
	 * @param examStartTime
	 * @param examEndTime
	 * @param danNum
	 * @param danScore
	 * @param duoNum
	 * @param duoScore
	 * @param pNum
	 * @param pScore
	 * @param response
	 */
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
		//调用service存储考试信息，返回1代表成功创建，0代表创建失败，-1代表该考试名称已经存在
		int state = service.getExamService().addExamInfo(examInfoEntity);
		if (state==1) {
			jsonObject.put("state", "success");
		}
		if (state==-1) {
			jsonObject.put("state", "exist");
		}
		if (state==0) {
			jsonObject.put("state", "fail");
		}
		//设置返回的数据格式
		response.setContentType("application/json");
		//修改编码为UTF-8
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>upLoadFile方法的描述：
	 * 上传考生信息表，并添加到对应的考试信息中
	 * </p>
	 * @Title: ExamManagerController的upLoadFile方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:11:08
	 * @param request
	 * @param examName
	 * @return
	 */
	@RequestMapping(value = "teacherUploadStudentExcel")  
	public String upLoadFile(HttpServletRequest request,String examName) {  
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		String path = request.getServletContext().getRealPath("/")+"WEB-INF/teacher/";
    //用于存储保存状态
    boolean state = false;
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
  			//调用service将数据保存到数据库
  			if (service.getExamService().addStudentInfo(studentInfoEntity,teacherEmail,examName)) {
  				//保存成功
  				state = true;
  				//复制为true了
  			}
  		}
      }
      	//创建成功
		if (state) {
  			return "teacher/exam/createExamInfoSuccess";
		}
      //默认返回 创建失败
      return "teacher/exam/createExamInfoFail";
	} 
	
	/**
	 * 
	 * <p>toEditExamInfo方法的描述：
	 * 跳转到修改考试信息界面
	 * </p>
	 * @Title: ExamManagerController的toEditExamInfo方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:11:20
	 * @return
	 */
	@RequestMapping(value="teacherEditExam")
	public String toEditExamInfo(){
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
		return "teacher/exam/editExamInfo";
	}
	
	/**
	 * 
	 * <p>editExamInfo方法的描述：
	 * 实现教师修改考试信息
	 * </p>
	 * @Title: ExamManagerController的editExamInfo方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:11:30
	 * @param examId
	 * @param examStartTime
	 * @param examEndTime
	 * @param danNum
	 * @param danScore
	 * @param duoNum
	 * @param duoScore
	 * @param pNum
	 * @param pScore
	 * @param response
	 */
	@RequestMapping(value="teacherEditExamDo",method=RequestMethod.POST)
	public void editExamInfo(int examId,
			String examStartTime,String examEndTime,
			String danNum,String danScore,
			String duoNum,String duoScore,
			String pNum,String pScore,HttpServletResponse response){
		//实例化考试信息对象
		ExamInfoEntity examInfo = new ExamInfoEntity();
		//添加考试id信息
		examInfo.setExamId(examId);
		//添加考试开始时间
		examInfo.setStartTime(examStartTime);
		//添加考试结束时间
		examInfo.setEndTime(examEndTime);
		//添加单选题个数信息
		examInfo.setChoiceNumber(danNum);
		//添加单选题分数信息
		examInfo.setChoiceScore(danScore);
		//添加多选题个数信息
		examInfo.setMultiputeChoiceNumber(duoNum);
		//添加多选题分数信息
		examInfo.setMultiputeChoiceScore(duoScore);
		//添加判断题个数信息
		examInfo.setJudgeNumber(pNum);
		//添加判断题分数信息
		examInfo.setJudgeScore(pScore);
		//实例化一个jsonobject对象用于存储状态信息
		JSONObject jsonObject = new JSONObject();
		//调用service更新考试信息数据
		if (service.getExamService().updateExamInfo(examInfo)) {
			jsonObject.put("state", "success");
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
	/**
	 * 
	 * <p>getQuestionLibListByExamId方法的描述
	 * 通过考试id找到对应的题库信息，用于在修改考试信息时的题库显示功能
	 * </p>
	 * @Title: ExamManagerController的getQuestionLibListByExamId方法
	 * @Description: TODO
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月24日 下午7:38:15
	 * @param examId
	 * @param response
	 */
	@RequestMapping(value="teacherGetExamInfoByExamId",method=RequestMethod.POST)
	public void getQuestionLibListByExamId(int examId,HttpServletResponse response){
		//初始化一个int型变量，存储题库id
		int libraryId = -1;
		//初始化一个题库列表信息，用于返回值
		QuestionLibListEntity qEntity = new QuestionLibListEntity();
		//新建一个JSONArray对象，用于存储返回的信息
		JSONArray jsonArray = new JSONArray();
		//获取在会话中的教师邮箱信息
		String teacherEmail = (String) session.getAttribute("teacherEmail");
		//获取该教师所有的考试信息
		ArrayList<ExamInfoEntity> examInfoEntities = service.getExamService().getExamInfoByTeacherEmail(teacherEmail);
		//遍历所有的考试信息，匹配与传入的考试id相等的数据，并获取到对应的题库id
		for(int i = 0; i < examInfoEntities.size();i++){
			ExamInfoEntity  examInfoEntity = examInfoEntities.get(i);
			if(examInfoEntity.getExamId()==examId){
				libraryId = examInfoEntity.getQuestionListNumber();
			}
		}
		//使用教师id获取对应的题库列表信息
		ArrayList<QuestionLibListEntity> questionLibListEntities = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		//遍历题库信息找到与之前的题库id相同的题库列表信息
		for(int j = 0; j < questionLibListEntities.size();j++){
			QuestionLibListEntity questionLibListEntity = questionLibListEntities.get(j);
			//匹配与之前的题库id相同的数据
			if (questionLibListEntity.getLibraryId()==libraryId) {
				//将匹配的题库列表信息转存，准备返回给前台界面
				qEntity = questionLibListEntity;
			}
		}
		//将找到的题库列表信息返回前台
		jsonArray.add(qEntity);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>deleteExamByExamId方法的描述：
	 * 实现通过考试id删除考试信息的操作
	 * </p>
	 * @Title: ExamManagerController的deleteExamByExamId方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:11:44
	 * @param examId
	 * @param response
	 */
	@RequestMapping(value="teacherDeleteExamInfoByExamId",method=RequestMethod.POST)
	public void deleteExamByExamId(int examId,HttpServletResponse response){
		//新建一个jsonobject对象用于存储结果信息
		JSONObject jsonObject = new JSONObject();
		//执行删除考试信息的操作
		if(service.getExamService().deleteExamInfoByExamId(examId)){
			//删除成功
			jsonObject.put("state", "success");
		}else{
			//删除失败
			jsonObject.put("state", "fail");
		}
		//将处理结果返回到前端界面
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>downloadExamDemo方法的描述：
	 * 实现下载考生信息模版的功能
	 * </p>
	 * @Title: ExamManagerController的downloadExamDemo方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:12:02
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherDownloadStudentDemo")
	public ResponseEntity<byte[]> downloadExamDemo(HttpSession session){
        String path = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/exam/studentDemo.xlsx";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName = null;
        try {
        	//强制转码，避免下载文件名无法使用中文
        	fileName  = new String("考生信息模版.xlsx".getBytes("utf-8"), "iso-8859-1");
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
