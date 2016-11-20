
package cn.nsu.ccl.teacher.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import cn.nsu.ccl.teacher.entity.QuestionListEntity;
import cn.nsu.ccl.teacher.entity.TeacherEntity;
import cn.nsu.ccl.teacher.service.ServiceManager;
/**
 * 
 * <p>QuestionLibContoller类的描述--题库相关操作</p>
 * @ClassName: ExamController
 * @Description: 下载模板,创建题库及上传题目集合，删除题库，跳转到创建题库界面，注销 session ，显示题库列表，
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月19日 下午1:58:27
*/
@Controller
public class QuestionLibContoller {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private ServiceManager serviceManger;

	/**
	 * <p>downloadLibDemo方法的描述-下载模板</p>
	 * @Title: QuestionLibContoller的downloadLibDemo方法
	 * @Description: 下载题库模板--然后跳转到上传题库界面
	 * @author 2213974854@qq.com
	 * @date 2016年11月20日 下午8:55:38
	 * @param session
	 * @return
	 */
	@RequestMapping(value="teacherDownloadLibDemo")
	public ResponseEntity<byte[]> downloadLibDemo(HttpSession session){
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
	 * <p>toCreatLib方法的描述--跳转到创建题库界面</p>
	 * @Title: QuestionLibContoller的toCreatLib方法
	 * @Description: 跳转到教师创建题库信息界面
	 * @author 2213974854@qq.com
	 * @date 2016年11月20日 下午8:56:24
	 * @return
	 */
	@RequestMapping(value="teacherCreateLib")
	public String toCreatLib(){
		return "teacher/createLib";
	}

	/**
	 * <p>createLib方法的描述--创建题库,上传题目集合</p>
	 * @Title: QuestionLibContoller的createLib方法
	 * @Description:实现创建题库的操作
	 * @author 2213974854@qq.com
	 * @date 2016年11月20日 下午8:57:03
	 * @param file
	 * @param examName
	 * @param session
	 */
	@RequestMapping(value="teacherCreateLibDo",method=RequestMethod.POST)
	public void createLib(@RequestParam("file") CommonsMultipartFile file,String examName,HttpSession session){
//		//调用service上传到文件，并返回路径
//		String filePath = service.getExamService().updateExamExcel(file,session);
//		//验证文件是否按照要求进行编写
//		if (service.getExamService().checkExamExcel(filePath)) {
//			//将数据存储到数据库
//			
//		}
	}
	
	/**
	 * <p>getQuestionLibList方法的描述</p>
	 * @Title: QuestionLibContoller的getQuestionLibList方法
	 * @Description:获取题库列表信息
	 * @author 2213974854@qq.com
	 * @date 2016年11月20日 下午9:47:00
	 * @param teacherId
	 */
	@RequestMapping(value="teacherGetQuestionLibList")
	public String getQuestionLibList(String teacherId){
		return "questionLib/views/questionLibIndex.jsp";
	}
	/**
	 * 功能：进入首页
	 * 方法名：index
	 * 返回值：String
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @return 登录成功返回首页视图，否则将 questionLibList 置为false
	 */
	@RequestMapping("/teacher")
	public String index() {
		String teacher = (String) request.getSession().getAttribute("teacher");
		if (teacher == null) {
			return "redirect:questionLib/login.do";
		}
		return "redirect:/teacher/index.do";
	}

/*
	*//**
	 * 功能：上传
	 * 方法名：upload
	 * 返回值：String
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param file 上传的文件
	 * @param questionLibName 题库名，但是此时不创建题库
	 * @return 返回题目json数据
	 *//*
	@RequestMapping("questionLib/upload")
	public String upload(@RequestParam("file") CommonsMultipartFile file, String questionLibName) {
		// 保存临时文件
		UploadService uploadService = serviceManger.getUploadService();
		String path = request.getSession().getServletContext().getRealPath("/");
		File tempFile = uploadService.upload(file, path);
		QuestionService questionService = serviceManger.getQuestionService();
		//		ArrayList<Question> questionList = questionService.getQuestions(tempFile);

		if (questionService.checkExcel(tempFile)) {// 文件合格
			//因为重定向后 reques t里的数据会丢失，所以临时保存在 session 中，
			//到下一个controller 后在重新放入 request 中，同时删除 session 中的数据
			ArrayList<Question> questionList = questionService.getQuestions(tempFile);
			request.getSession().setAttribute("questionList", questionList);
			request.getSession().setAttribute("questionLibName", questionLibName);
			request.getSession().setAttribute("filePath", file.getOriginalFilename());
		} else {// 文件不合格
			request.getSession().setAttribute("checkResult", false);
		}
		uploadService.delete(tempFile);
		return "redirect:/questionLib/questionIndex.do";
	}
*/
	/**
	 * 功能：确认添加题目到数据库
	 * 方法名：submit
	 * 返回值：void
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param json 题目数据
	 * @param questionLibName 题库名，同时创建题库
	 * @throws Exception 
	 */
	/*@RequestMapping("questionLib/submit")
	@ResponseBody
	public void submit(String questionJson, String questionLibName) throws Exception {
		// 获取登录的教师对象
		String teacherUsername = (String) request.getSession().getAttribute("teacher");
		// 创建题库
		int questionLibId = serviceManger.getQuestionLibService().create(questionLibName, teacherUsername);
		response.setContentType("application/json;charset=utf-8");
		if (questionLibId != -1) {
				if (serviceManger.getQuestionService().submit(questionJson, questionLibId)) {// 添加成功
					response.getWriter().write("{\"status\":1,\"questionLibId\":" + questionLibId + "}");
				} else {// 添加失败
					response.getWriter().write("{\"status\":0}");
				}
		} else {
			response.getWriter().write("{\"status\":0}");
		}
		response.getWriter().flush();
	}
*/
	/**
	 * 功能：删除题库
	 * 方法名：deleteQuestionLib
	 * 返回值：void
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param questionLibId 题库编号的字符串形式
	 * @return 返回json形式状态码
	 * @throws Exception 
	 *//*
	@RequestMapping("questionLib/deleteQuestionLib")
	@ResponseBody
	public void deleteQuestionLib(String questionLibId) throws Exception {
		// int i_questionLibId = Integer.parseInt(questionLibId);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (serviceManger.getQuestionLibService().delete(questionLibId)) {// 删除成功
			out.write("{\"status\":1}");
		} else {
			out.write("{\"status\":0}");
		}
		out.flush();
		out.close();
	}

*/

	/**
	 * 功能：获取对应的题库题目集合
	 * 方法名：enter
	 * 返回值：void
	 * 开发人员：墨盒
	 * 邮箱：m18942819324@163.com
	 * 创建时间：2016-8-26
	 * @param questionLibId 题库编号的字符串形式
	 * @return 登录成功返回到题目json数据，否则放回状态码
	 * @throws Exception 
	 *//*
	@RequestMapping("questionLib/enter")
	@ResponseBody //不加这个很容易报异常
	public void enter(String questionLibId) throws Exception {
		QuestionLibService questionLibService = serviceManger.getQuestionLibService();
		String questionJson = questionLibService.getQuestions(questionLibId);
		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=utf-8");
			out = response.getWriter();
			out.println(questionJson);
			out.flush();
			//			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * <p>exit方法的描述</p>
	 * @Title: QuestionLibContoller的exit方法
	 * @Description: 教师退出，删除 session 中的教师数据
	 * @author 2213974854@qq.com
	 * @date 2016年11月20日 下午9:20:18
	 * @return
	 *//*
	@RequestMapping("questionLib/exit")
	public String exit() {
		
		request.getSession().invalidate();
		return "redirect:/teacher.do";
	}*/
	
}

