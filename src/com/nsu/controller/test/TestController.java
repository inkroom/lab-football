package com.nsu.controller.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nsu.bean.common.FileUploadBean;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.exception.ExtensionsException;
import com.nsu.exception.LengthException;
import com.nsu.exception.NullFileParamException;
import com.nsu.exception.SizeException;
import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nsu.controller.BaseController;
import com.nsu.service.core.IConfigurationService;
import com.nsu.service.test.ITestService;

/**
* @Title: TestController
* @Package com.nsu.controller.test
* @Description: 测试 Controller
* @author 梅谢兵
* @date 2017-03-06
* @version V1.0
*/

@Controller
@RequestMapping(value="/test")
public class TestController extends UploadBaseController{
	
	@Resource
	private ITestService iTestService;
	
	@Resource(name="configurationService")
	private IConfigurationService configurationService ;
	
	@RequestMapping(value="/{username}/test",method=RequestMethod.GET)
	public String test(@PathVariable String username,RedirectAttributes redirectAttributes,HttpServletRequest servletRequest){

		System.out.println(configurationService.getTheSavePath(servletRequest));
		
		log.info("servletRequest="+servletRequest.getParameter("username"));
		
		log.debug("username："+username+"    注意：日志打印记得使用Log  禁止使用System.out.println");
		log.debug("这里是：TestController.class    注意：日志打印记得使用Log  禁止使用System.out.println");
		

		try {
			log.debug(iTestService.testService("meixiebing"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
		
		/**
		 * RedirectAttributes 重定向传值
		 */
		redirectAttributes.addFlashAttribute("key", "value");
		
		log.debug("---------------over----------------------");
		return "redirect:/test/test_second.action";
	}



	@RequestMapping("/upload_view")
	public String testFileUploadView(){
		return "/test/index";
	}

	@RequestMapping("/upload")
	public String testFileUpload(MultipartFile myFile ,HttpServletRequest request){
		String errorInfo = null;
		try {
			huashiPrintln(fileUpload(myFile,request,"coach","image"));
		}catch (ExtensionsException e) {
			errorInfo =  e.getMessage();
		} catch (LengthException e) {
			errorInfo = e.getMessage();
		} catch (SizeException e) {
			errorInfo = e.getMessage();
		} catch (NullFileParamException e) {
			errorInfo = e.getMessage();
		}
		huashiPrintln(errorInfo);
		return null;
	}





	
}
