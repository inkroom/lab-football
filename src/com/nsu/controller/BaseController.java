package com.nsu.controller;

import com.nsu.common.Constants;
import com.nsu.util.InfoProUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 功能描述：基础的Controller
 * @author 梅谢兵
 * @version 1.0 Create Date: 2016-8-31
 */


public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());

	protected void huashiPrintln(Object o){
		log.info("█████████████████████████████████████████████████████████████████████████████████");
		log.info(o);
		log.info("▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");

	}

	/**
	 * 向session中存放密码加密所需要的盐
	 * @param session
	 */
	protected static void setSaltForSession(HttpSession session){
		String salt = InfoProUtil.getRandomString(8);
		session.setAttribute(Constants.SALT_IN_SESSION, salt);
	}

	/**
	 * 向session存储数据
	 * @param key 存储数据的key值
	 * @param value 存储数据的value值
	 * @param session
	 */
	protected static void setSessionParameter(String key,Object value,HttpSession session){
		session.setAttribute(key, value);
	}
	
	/**
	 * 向redirectAttributes中存储临时信息
	 * @param key 存储数据的key值
	 * @param value 存储数据的value值
	 * @param redirectAttributes
	 */
	protected static void setFlashParameter(String key,Object value, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(key, value);
	}
}
