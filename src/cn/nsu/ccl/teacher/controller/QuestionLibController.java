/**
 * <p>QuestionLibController.java文件的详细描述：
 * 实现与题库管理员的相关操作
 * </p>
 * @Title: ExamManagerController.java
 * @Package cn.nsu.ccl.teacher.controller
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午4:11:27
 * @version V1.0
 */
package cn.nsu.ccl.teacher.controller;

import cn.nsu.ccl.teacher.entity.Question;
import cn.nsu.ccl.teacher.entity.QuestionLibList;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>QuestionLibController类的描述：
 * 实现与考试管理相关的下载模板，上传题库，页面跳转,创建题库等操作
 * </p>
 * @ClassName: QuestionLibController
 * @Description: TODO
 * @author: 暴沸
 * @email: baofeidyz@foxmail.com
 * @date 2016年11月27日 下午9:15:25
 */
@Controller
public class QuestionLibController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServiceManager service;

	
	
	/**
	 * 
	 * <p>downloadExamDemo方法的描述：
	 * 下载题库模版
	 * </p>
	 * @Title: QuestionLibController的downloadExamDemo方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:15:46
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherDownloadQuestionLibDemo")
	public ResponseEntity<byte[]> downloadExamDemo(HttpSession session){
		String path = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/exam/examDemo.xlsx";
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
	 * <p>addQuestion方法的描述：
	 * 实现创建题库的操作
	 * </p>
	 * @Title: QuestionLibController的addQuestion方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:16:12
	 * @param file
	 * @param questionLibName
	 * @param session
	 * @param response
	 * @param request
	 * @return
	 */
 	@RequestMapping(value="teacherAddquestionToLib",method=RequestMethod.POST)
 	public String addQuestion(@RequestParam("file") CommonsMultipartFile file,String questionLibName,HttpSession session,HttpServletResponse response,HttpServletRequest request){
 		//获取教师登录信息，教师邮箱帐号
 		String teacherEmail = (String)session.getAttribute("teacherEmail");
		//判断同名教师下面是否存在同名题库，若存在则直接返回提示
		if (service.getQuestionLibService().getQuestionLibId(questionLibName, teacherEmail)!=-1) {
			request.setAttribute("state", "exist");
			return "teacher/questionLib/result";
		};
		
		//接收上传的题库文件
		//保存文件的路径
		//保存最后excel文件转成集合
		ArrayList<Question> list = new ArrayList<>();
		String path = request.getServletContext().getRealPath("/")+"WEB-INF/teacher/questionLib/";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
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
	          //检查上传的文件是否合法
	          if (service.getQuestionLibService().checkExcel(path)) {
	        	  //文件合法则开始转换成集合数据
		          //获取上传的文件的集合
	        	  System.out.println("5");
		          list = service.getQuestionLibService().getQuestionLibListByExcel(path);
		          System.out.println("6");
	          }

	      }
	      System.out.println("6.1");
		//根据题库名称和教师id在题库列表信息表中创建题库的列表信息;
		System.out.println("这是在controll中获取到的题库集合的大小="+list.size());
 		if(service.getQuestionLibService().addQuestions(questionLibName, teacherEmail, list)){
 			System.out.println("8");
 			request.setAttribute("state", "success");
		}else{
			System.out.println("9");
			request.setAttribute("state", "fail");
		}
		//设置返回的数据格式
		response.setContentType("application/json");
		//修改编码为UTF-8
		response.setCharacterEncoding("UTF-8");
		return "teacher/questionLib/result";
 	}
	
	/**
	 * 
	 * <p>getQuestionLib方法的描述：
	 * 通过教师id获取题库信息，并返回编辑题库的列表中
	 * </p>
	 * @Title: QuestionLibController的getQuestionLib方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:16:33
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherEditQuestionLib")
	public String getQuestionLib(HttpSession session){
		String teacherEmail = (String)session.getAttribute("teacherEmail");
		//获取题库列表信息
		List<QuestionLibList> questionLibList = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		//将题库信息存到请求request中
		request.setAttribute("questionLibList", questionLibList);
		return "teacher/questionLib/editLib";
	}

	/**
	 * 
	 * <p>deleteQuestionLib方法的描述：
	 * 按照题库id删除题库,返回状态
	 * </p>
	 * @Title: QuestionLibController的deleteQuestionLib方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:17:02
	 * @param str
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value="teacherDeleteQuestionLib",method=RequestMethod.POST)
	@ResponseBody
	//传入拼接成功的字符串
	public String deleteQuestionLib(String str,HttpSession session,HttpServletResponse response){
		//获取现在登陆的老师id
		String teacherEmail = (String)session.getAttribute("teacherEmail");
		//分割字符串
		String[] libraryNames =str.split(",");
		int count = 0;
		for (int i = 0; i < libraryNames.length; i++) {
			if (service.getQuestionLibService().deleteQuestionLibList(libraryNames[i],teacherEmail)) {
				count++;//记录删除成功的信息条数
			}
		}
		System.out.println(String.format("成功删除%d条信息，失败%d条",count,libraryNames.length-count));
		JSONObject jsonObject = new JSONObject();
		if (libraryNames.length==count) {//判断是否全部成功删除
			jsonObject.put("state", "success");
		}
		else jsonObject.put("state", "fail");
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>toAddlib方法的描述：
	 * 跳转到增加题库界面
	 * </p>
	 * @Title: QuestionLibController的toAddlib方法
	 * @Description: TODO
	 * @author: 暴沸
	 * @author baofeidyz@foxmail.com
	 * @date 2016年11月27日 下午9:17:13
	 * @return
	 */
	@RequestMapping(value = "teacherToCreatelib")
	public String toAddlib(){
		return "teacher/questionLib/createLib";
	}


}
