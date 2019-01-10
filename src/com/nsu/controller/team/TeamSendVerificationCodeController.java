package com.nsu.controller.team;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.nsu.staticvar.TeamVar;
import com.nsu.util.RequestUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Anonymous;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamSendVerificationCodeService;
import com.nsu.util.V;

/**
 * 球队发送验证码
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-27 15:08:05
 */
@Controller
@RequestMapping(value="/team")
public class TeamSendVerificationCodeController extends BaseController implements Anonymous{
	
	@Resource(name="teamSendVerificationCodeService")
	private ITeamSendVerificationCodeService teamSendVerificationCodeService;
	
	/**
	 * 发送邮箱验证码的地址
	 */
	private final static String SENDEMAILURL = "/email/getCode.action";
	
	/**
	 * 向绑定邮箱发送验证码
	 * @author ljl
	 * @createDate 2017-04-12 09:06:19
	 * @param session
	 */
	@Deprecated
	@RequestMapping(value = "/sendEmailCode", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailCode(HttpSession session){
		String aid = RequestUtil.getAccountInfoInSession(session, "A_ID");
		int status = 500;
		String result = "登录回话失效，2秒后跳转至登录界面";
		//用户绑定的邮箱
		String bindingEmail = null;
		JSONObject jsonStr = new JSONObject();
	
		if(!V.checkEmpty(aid)){
			//查询用户的绑定邮箱
			String semail = teamSendVerificationCodeService.findTeambindingEmail(aid);
			status = 200;
			result = SENDEMAILURL;
			bindingEmail = semail;
		}else{
			//登录失效，未发送验证码
			log.info("登录失效，未发送邮箱验证码");
		}

		jsonStr.put("status", status);
		jsonStr.put("message", result);
		jsonStr.put("email", bindingEmail);
		return jsonStr.toString();
	}
}
