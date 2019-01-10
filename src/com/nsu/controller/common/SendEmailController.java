/**
 * @Title: SendEmailController.java 
 * @Package com.nsu.controller.common  
 * @Description: 邮箱验证，发送邮件
 * @author 朱明民 
 * @date 2017年4月21日 下午2:30:50
 * @version V1.0 
 */
package com.nsu.controller.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.controller.BaseController;
import com.nsu.service.common.IForgetPasswordService;
import com.nsu.util.InfoProUtil;
import com.nsu.util.Mail;
import com.nsu.util.ResponseUtil;

import javax.annotation.Resource;


/**
 * @ClassName: SendEmailController
 * @Description: 邮箱验证，发送邮件 <详细介绍>
 * @date 2017年4月21日 下午2:30:50
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/email")
public class SendEmailController extends BaseController {

	@Resource(name = "ForgetPassword")
	private IForgetPasswordService iForgetPasswordService;
	 
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public void sendEmail(@RequestParam(value = "email") String email,HttpSession session1,HttpServletResponse  response) throws IOException {
		JSONObject info = new JSONObject();
		//用户输入的账号的邮箱以及验证码
			String code = InfoProUtil.getRandomString(6);
			//将随机数插入数据库
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("A_USERNAME", session1.getAttribute("A_USERNAME"));
			map.put("code", code);
			map.put("A_TYPE", session1.getAttribute("A_TYPE"));
			try {
				iForgetPasswordService.updateEmailCode(map);
				if (Mail.sendEmail(email, code)) {
					info.put("error", "发送成功！");
					ResponseUtil.write(response, info);
				} else {
					info.put("error", "发送失败！");
					ResponseUtil.write(response, info);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
