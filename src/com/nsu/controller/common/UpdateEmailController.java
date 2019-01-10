/**
 * @Title: UpdateEmailController.java 
 * @Package com.nsu.controller.common  
 * @Description: 修改邮箱controller
 * @author 朱明民 
 * @date 2017年4月27日 下午4:29:08
 * @version V1.0 
 */
package com.nsu.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.common.IUpdateEmailService;
import com.nsu.util.ResponseUtil;
import com.nsu.util.jedis.JedisClient;

import net.sf.json.JSONObject;

/**
 * @ClassName: UpdateEmailController
 * @Description: 修改邮箱controller
 * @date 2017年4月27日 下午4:29:08
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/updateEmail")
public class UpdateEmailController extends BaseController {

	@Resource(name = "UpdateEmail")
	private IUpdateEmailService iUpdateEmailService;

	@Autowired
	private JedisClient jedisClient;
	
	/**
	 * 获取旧邮箱回显到页面
	 */
	@RequestMapping(value = "/getEmail")
	public void getEmail( HttpSession session, HttpServletResponse response){
		try {
			JSONObject info = new JSONObject();
			@SuppressWarnings("unchecked")
			Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			int A_ID = Integer.parseInt(map1.get("A_ID").toString());
			String old_email_sql = iUpdateEmailService.getEmailById(A_ID);
			session.setAttribute("SMSEmail", old_email_sql);
			info.put("getEmail", old_email_sql);
			ResponseUtil.write(response, info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(value = "/lastEmailCheck")
	public String lastEmail(@RequestParam(value = "new_email") String new_email, HttpSession session,
			@RequestParam(value = "newEmailCode") String newEmailCode, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		int A_ID = Integer.parseInt(map1.get("A_ID").toString());
		JSONObject info = new JSONObject();
		try {
			//比较验证码
			if (!newEmailCode.equals(jedisClient.get(new_email))) {
				info.put("error", "验证码错误");
				ResponseUtil.write(response, info);
				return null;
			}else {
				//更新邮箱
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email", new_email);
				map.put("id", A_ID);
				iUpdateEmailService.updateEmail(map);
				info.put("error", "验证成功");
				ResponseUtil.write(response, info);
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.put("error", "验证失败");
		ResponseUtil.write(response, info);
		return null;
	}

	
	/**
	 * 验证旧邮箱验证码
	 */
	@RequestMapping(value = "/YzEmailCode",method = RequestMethod.POST)
	@ResponseBody
	public String getEmailCode(@RequestParam(value = "oldEmailCode") String oldEmailCode, HttpSession session, HttpServletResponse response) {
		JSONObject info = new JSONObject();
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			int A_ID = Integer.parseInt(map1.get("A_ID").toString());
			String old_email_sql = iUpdateEmailService.getEmailById(A_ID);
			 if (jedisClient.get(old_email_sql+"num") != null){
		            if (Integer.parseInt(jedisClient.get(old_email_sql+"num"))>5){
		               info.put("error", "验证码错误");
		                jedisClient.del(old_email_sql+"num");
		                jedisClient.del(old_email_sql);
		            }
		        }
		        if (oldEmailCode.equals(jedisClient.get(old_email_sql))){
		        	info.put("error","1");
		        }else {
		        	 info.put("error", "验证码错误");
		            if (jedisClient.get(old_email_sql+"num") != null) {
		                jedisClient.incr(old_email_sql + "num");
		            }
		        }
		        return info.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return info.toString();
	}
}
