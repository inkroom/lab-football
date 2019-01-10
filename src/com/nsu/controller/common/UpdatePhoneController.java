/**
 * @Title: UpdatePhoneController.java 
 * @Package com.nsu.controller.common  
 * @Description: 修改手机号码Controller
 * @author 朱明民 
 * @date 2017年4月12日 下午3:39:04
 * @version V1.0 
 */
package com.nsu.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.util.InfoProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.common.IUpdatePhoneService;
import com.nsu.util.FormatPattern;
import com.nsu.util.ResponseUtil;
import com.nsu.util.SMSUtil.AjaxJson;
import com.nsu.util.jedis.JedisClient;

import net.sf.json.JSONObject;

/**
 * @ClassName: UpdatePhoneController
 * @Description: 修改手机号码Controller <详细介绍>
 * @date 2017年4月12日 下午3:39:04
 * @author 朱明民
 * 
 */
@Controller
@RequestMapping(value = "/updatePhone")
public class UpdatePhoneController extends BaseController {

	@Resource(name = "UpdatePhone")
	private IUpdatePhoneService iUpdatePhoneService;

	@Autowired
	private JedisClient jedisClient;

	/**
	 * 获取旧手机回显到页面
	 */
	@RequestMapping(value = "/getPhone")
	public void getEmail(HttpSession session, HttpServletResponse response) {
		try {
			JSONObject info = new JSONObject();
			@SuppressWarnings("unchecked")
			Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			Long A_ID = Long.parseLong(map1.get("A_ID").toString());
			String old_phone_sql = iUpdatePhoneService.getPhoneById(A_ID);
			session.setAttribute("SMSPhone", InfoProUtil.xorInfo(old_phone_sql));
			info.put("getPhone", InfoProUtil.xorInfo(old_phone_sql).replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
			ResponseUtil.write(response, info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 验证旧手机验证码
	 */
	@RequestMapping(value = "/YzPhoneCode",method = RequestMethod.POST)
	@ResponseBody
	public String getPhoneCode(@RequestParam(value = "oldPhoneCode") String oldPhoneCode, @RequestParam(value = "type") String type, HttpSession session, HttpServletResponse response) {
		JSONObject info = new JSONObject();
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			Long A_ID = Long.parseLong(map1.get("A_ID").toString());
			String old_phone_sql = iUpdatePhoneService.getPhoneById(A_ID);
			 if (jedisClient.get(type+"web"+ InfoProUtil.xorInfo(old_phone_sql) +"num") != null){
		            if (Integer.parseInt(jedisClient.get(type+"web"+ InfoProUtil.xorInfo(old_phone_sql) +"num"))>5){
		            	info.put("error","验证码错误");
		                log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + InfoProUtil.xorInfo(old_phone_sql) + "num"));
		                jedisClient.del(type+"web"+ InfoProUtil.xorInfo(old_phone_sql));
		                jedisClient.del(type + "web" + InfoProUtil.xorInfo(old_phone_sql) + "num");
		                return info.toString();
		            }
		        }
		        if (oldPhoneCode.equals(jedisClient.get(type+"web"+ InfoProUtil.xorInfo(old_phone_sql)))) {
		        	info.put("error","1");
		        } else {
		        	info.put("error","验证码错误");
		            if (jedisClient.get(type+"web"+ InfoProUtil.xorInfo(old_phone_sql) +"num") != null){
		                jedisClient.incr(type + "web" + InfoProUtil.xorInfo(old_phone_sql) + "num");
		            }
		        }
		        return info.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return info.toString();
	}
	
	
	
	
	/**
	 * 验证新手机验证码是否输入正确
	 * 
	 * @param newPhoneCode
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/CheckNewCode", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkOldCode(@RequestParam(value = "new_phone") String new_phone,
			@RequestParam(value = "newPhoneCode") String newPhoneCode, @RequestParam(value = "type") String type,HttpServletResponse response,
			HttpSession session, HttpServletRequest request) {
		log.info("--------------------------提交验证手机验证码是否相同------------------------");
		JSONObject info = new JSONObject();
		try {
			if (!phoneCheck(new_phone)) {
				info.put("error", "新手机号码错误！");
				info.put("status", 1);
			}else if (!newPhoneCode.equals(jedisClient.get(type+"web"+new_phone))) {
				info.put("error", "验证码错误！");
				info.put("status", 1);
			}else {
				@SuppressWarnings("unchecked")
				Map<String, Object> map1 = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
				int A_ID = Integer.parseInt(map1.get("A_ID").toString());
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("PHONE", InfoProUtil.xorInfo(new_phone));
				map.put("A_ID", A_ID);
				iUpdatePhoneService.updatePhone(map);
				info.put("error", "修改成功！");
				info.put("status", 2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info.toString();
	}

	/**
	 * @Description (校验手机号码)
	 * @param phone
	 * @return
	 */
	public Boolean phoneCheck(String phone) {
		return FormatPattern.isChinaPhoneLegal(phone);
	}
	
	
	
	 public AjaxJson validateMobileCode(String type,String mobile, String randomCode) {
	        AjaxJson ajaxJson = new AjaxJson();
	        log.info(" *********8" + jedisClient.get(type+"web"+mobile+"num"));
	        if (jedisClient.get(type+"web"+mobile+"num") != null){
	            if (Integer.parseInt(jedisClient.get(type+"web"+mobile+"num"))>5){
	                ajaxJson.setErrorCode("0");
	                ajaxJson.setSuccess(false);
	                ajaxJson.setMsg("验证码错误");
	                log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + mobile + "num"));
	                jedisClient.del(type+"web"+mobile);
	                jedisClient.del(type + "web" + mobile + "num");
	                return ajaxJson;
	            }
	        }
	        if (randomCode.equals(jedisClient.get(type+"web"+mobile))) {
	            ajaxJson.setErrorCode("1");
	            ajaxJson.setSuccess(true);
	            ajaxJson.setMsg("验证码正确");
	        } else {
	            ajaxJson.setErrorCode("-1");
	            ajaxJson.setSuccess(false);
	            ajaxJson.setMsg("验证码错误确");
	            if (jedisClient.get(type+"web"+mobile+"num") != null){
	                jedisClient.incr(type + "web" + mobile + "num");
	            }
	            log.info("\"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + mobile + "num"));
	        }
	        return ajaxJson;
	    }
}
