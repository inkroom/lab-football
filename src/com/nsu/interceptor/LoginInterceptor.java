package com.nsu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsu.bean.index.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nsu.common.Constants;

import java.util.LinkedHashMap;

public class LoginInterceptor implements HandlerInterceptor {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		log.debug("**********************  preHandle拦截  LoginInterceptor  **********************");
		String url = request.getServletPath();
		HandlerMethod handlerMethod = null;
		boolean isAjax = false;
		try {
			handlerMethod = (HandlerMethod) handler;
			if (handlerMethod != null && handlerMethod.getBean() instanceof Anonymous ){
				log.debug("**********************  preHandle拦截  LoginInterceptor handlerMethod != null"+handlerMethod != null);
				log.debug(handlerMethod.getBean() instanceof Anonymous);
				log.debug("**********************  preHandle拦截  LoginInterceptor handlerMethod != null || handlerMethod.getBean() instanceof Anonymous  **********************");
				return true;
			}
			isAjax = handlerMethod.getMethodAnnotation(InterceptorAnno.class).isAjax();
		}catch (Exception e){

		}
		if (url.contains("login") || url.contains("logout") || url.contains("register")) {
			return true;
		}else {
			if (request.getSession().getAttribute(Constants.LOGIN_USER)!=null) {
				return true;
			}
		}


		log.info("**********************************************************  ");
		log.info("**********************************************************  ");
		log.info("**********************************************************  ");
		log.info("**********************************************************  ");
		log.info("                            登录已拦截                       ");
		log.info("                           "+url + "                    ");
		log.info("**********************************************************  ");
		log.info("**********************************************************  ");
		log.info("**********************************************************  ");
		log.info("**********************************************************  ");

		url = url.substring(1,url.indexOf("/",1));
		log.info("**********************  "+url + "**********************  ");
		//response.sendRedirect(request.getContextPath() + "/" + url + Constants.LOGIN_URL);


		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) || isAjax){
			AjaxBean ajaxBean = new AjaxBean();
			LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
			ajaxBean.setStatus("500");
			ajaxBean.setMsg("身份信息已过期，请重新登录！");
			ajaxBean.setBody("url",url);
			ResponseUtil.write(response, JsonMapper.toJsonString(ajaxBean));
			return false;
		}

		response.sendRedirect(request.getContextPath()+"/"+url+"/"+"no_login.html");

		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.debug("**********************  postHandle拦截  **********************");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("**********************  afterCompletion拦截  **********************");
	}

}
