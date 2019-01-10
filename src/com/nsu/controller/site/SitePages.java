package com.nsu.controller.site;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.nsu.common.Constants;
import com.nsu.util.V;

/**
 * 页面定义及得到session内容基本方法
 * @author 刘俊
 */
public class SitePages {

	/*
	 * 重定向登录页面
	 */
	public final static String REDIRECT_SITE_LOGIN = "redirect:/site/login_view.html";
	/*
	 * 重定向主页面
	 */
	public final static String REDIRECT_SITE_HOME = "redirect:/site/site_home.html";
	/*
	 * 登录页面
	 */
	public final static String SITE_LOGIN = "/site/login";
	/*
	 * 消息中心页面
	 */
	public final static String SITE_MESSAGE = "/message/message-all";
	/*
	 * 现场管理员主页
	 */
	public final static String SITE_MANAGER = "/site/live-index";
	/*
	 * 现场直播页面
	 */
	public final static String SITE_GAMELIVE = "/site/live-system/gameLive";
	/*
	 * 裁判书页面
	 */
	public final static String SITE_JUDEMENT = "/site/live-system/judement";
	/*
	 * 重定向裁判书页面
	 */
	public final static String REDIRECT_SITE_JUDEMENT = "redirect:/site/judement.html";
	/*
	 * 球员验证页面
	 */
	public final static String SITE_PLAYERCHECK = "/site/live-system/playerCheck";

	/**
	 * 得到sessio中的值
	 * @param session
	 * @param key
	 * @return
	 */
	public static String getAccountInfoInSession(HttpSession session, String key){
		@SuppressWarnings("unchecked")
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		if(userMap == null || userMap.size() < 1){
			return null;
		}else{
			if(V.checkEmpty(userMap.get(key)) == true){
				return null;
			}else{
				return userMap.get(key).toString();
			}
		}
	}
}
