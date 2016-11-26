package cn.nsu.ccl.teacher.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
import net.sf.json.JSONObject;

/**
 * 
 * <p>ExamController类的描述</p>
 * @ClassName: ExamController
 * @Description: 下载模板，上传题库，页面跳转,创建题库
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午1:58:27
 */
@Controller
public class QuestionLibController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServiceManager service;

	
	
	/**
	 * 
	 * <p>downloadExamDemo方法的描述</p>
	 * @Title: ExamController的downloadExamDemo方法
	 * @Description:下载模板
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月19日 下午1:58:49
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherDownloadQuestionLibDemo")
    public ResponseEntity<byte[]> downloadExamDemo(HttpSession session){
    	System.out.println("teacherDownloadQuestionLibDemo");
        String path = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/exam/examDemo.xlsx";
        System.out.println(path);
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
        	System.out.println("之前");
        	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (IOException e) {
        	System.out.println("fuck");
        }
        return null;
    }
	/**
 	 * 
 	 * <p>createExam方法的描述</p>
 	 * @Title: QuestionLibController的addQuestionLib方法
 	 * @Description: TODO实现创建题库的操作
 	 * @author 暴沸 baofeidyz@foxmail.com
 	 * @date 2016年11月19日 下午5:04:03
 	 * @param file
 	 * @param session
 	 */
 	@RequestMapping(value="teacherAddquestionToLib",method=RequestMethod.POST)
 	public String addQuestion(@RequestParam("file") CommonsMultipartFile file,String questionLibName,HttpSession session,HttpServletResponse response){
 		 String teacherEmail = (String)session.getAttribute("teacherEmail");
		JSONObject jsonObject = new JSONObject();
		//判断同名教师下面是否存在同名题库
		if (service.getQuestionLibService().getQuestionLibId(questionLibName, teacherEmail)==-1) {
			jsonObject.put("state", "error");
			return "teacher/questionLib/result";
		};
		//根据题库名称和教师id创建题库;
		if (service.getQuestionLibService().addQuestionLibList(questionLibName, teacherEmail)) {
			 int questionLibId=service.getQuestionLibService().getQuestionLibId(questionLibName, teacherEmail);
		 		// 调用service上传到文件，并返回路径
		 		/*String filePath = service.getQuestionService().upload(file, session);
		 		// 验证文件是否按照要求进行编写
		 		File qFile=(File)file;
		 		if (service.getQuestionService().checkExcel(file)) {
					
				}
		 		if (service.getQuestionService().checkExamExcel(filePath)) {
		 		// 将数据存储到数据库
		 			service.getQuestionService().submit(, teacherEmail);
		 		}*/
			jsonObject.put("state", "success");
		}
		else
		jsonObject.put("state", "fail");
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
 	return "teacher/questionLib/result";
 	}
	/*@RequestMapping("teacherEditQuestionLib")
	public String questionIndex(String questionLibName) throws Exception {
		String teacherEmail = (String) request.getSession().getAttribute("teacher");
		if (teacherEmail == null) {
			return "redirect:questionLib/login.do";
		}
		//获取该教师创建的题库列表
		ArrayList<QuestionLibListEntity> questionLibList = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		request.setAttribute("questionLibList", questionLibList);
		if ((Boolean) request.getSession().getAttribute("checkResult") == Boolean.FALSE) {//文件不合格
			request.setAttribute("checkResult", request.getSession().getAttribute("checkResult"));
			request.getSession().removeAttribute("checkResult");
		} else if (request.getSession().getAttribute("filePath") != null) {//确保是重定向
			//将 session 中的数据放到 request 中
			request.setAttribute("questionList", request.getSession().getAttribute("questionList"));
			request.setAttribute("questionLibName", request.getSession().getAttribute("questionLibName"));
			request.setAttribute("filePath", request.getSession().getAttribute("filePath"));
			//删除 session 中的数据
			request.getSession().removeAttribute("questionList");
			request.getSession().removeAttribute("questionLibName");
			request.getSession().removeAttribute("filePath");
		}
		return "questionLib/views/questionLibIndex.jsp";
	}*/
	/**
	 * <p>getQuestionLib方法的描述</p>
	 * @Title: QuestionLibController的getQuestionLib方法
	 * @Description: 通过教教师的id获取题库信息，并显示在编辑题库页面
	 * @author 2213974854@qq.com
	 * @date 2016年11月21日 下午7:43:56
	 * @return
	 */
	@RequestMapping(value="teacherEditQuestionLib")
	public String getQuestionLib(HttpSession session){
		String teacherEmail = (String)session.getAttribute("teacherEmail");
		System.out.println("teacherEmail="+teacherEmail);
		//获取题库列表信息
		ArrayList<QuestionLibListEntity> questionLibList = service.getQuestionLibService().getQuestionLibListByTeacherEmail(teacherEmail);
		System.out.println(questionLibList.size());
		//将题库信息存到请求request中
		request.setAttribute("questionLibList", questionLibList);
		return "teacher/questionLib/editLib";
	}

	/**
	 * <p>deleteQuestionLib方法的描述</p>
	 * @Title: QuestionLibController的deleteQuestionLib方法
	 * @Description: 按照题库id删除题库,返回状态
	 * @author 2213974854@qq.com
	 * @date 2016年11月23日 下午5:15:37
	 * @param libraryNames
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
//		//设置返回的数据格式
//		response.setContentType("application/json");
//		//修改编码为UTF-8
//		response.setCharacterEncoding("UTF-8");
//		try {
//			response.getWriter().print(jsonObject.toString());
//			System.out.println("jsonObject.toString()==");
//			System.out.println(jsonObject.toString());
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		return jsonObject.toString();
	//测试字符串传入是否成功-----------------------------开始----------------------------------------
	/*public void deleteQuestionLib(String  libraryNames,HttpSession session,HttpServletResponse response){
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("state", "success");
		System.out.println("libraryNames+"+libraryNames);
		//设置返回的数据格式
		response.setContentType("application/json");
		//修改编码为UTF-8
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonObject.toString());
			System.out.println("jsonObject.toString()==");
			System.out.println(jsonObject.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//不能成功返回到页面request.readyState===1
*/
	//结束-----------------------------------------------------------------
	/*	
		System.out.println(request.getAttribute("libraryNames"));
		String teacherEmail = (String)session.getAttribute("teacherEmail");
		System.out.println("libraryNames.size()="+libraryNames.size());
		int count=0;
		
		for (int i = 0; i < libraryNames.size(); i++) {
			
			String libraryName= libraryNames.get(i);
			if (service.getQuestionLibService().deleteQuestionLibList(libraryName,teacherEmail)) {
				count++;
			}
			
			
		}
		
			System.out.println(String.format("成功删除%d条信息，失败%d条",count,libraryNames.size()-count));
			JSONObject jsonObject = new JSONObject();
			if (libraryNames.size()==count) {
				jsonObject.put("state", "success");
			}
			else jsonObject.put("state", "fail");
			//设置返回的数据格式
			response.setContentType("application/json");
			//修改编码为UTF-8
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().print(jsonObject.toString());
				System.out.println("jsonObject.toString()==");
				System.out.println(jsonObject.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	
	}
	/**
	 * <p>toAddlib方法的描述</p>
	 * @Title: QuestionLibController的toAddlib方法
	 * @Description: 跳转到增加题库界面
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月25日 下午2:39:45
	 * @return
	 */
	@RequestMapping(value = "teacherToCreatelib")
	public String toAddlib(){
		return "teacher/questionLib/createLib";
	}


}
