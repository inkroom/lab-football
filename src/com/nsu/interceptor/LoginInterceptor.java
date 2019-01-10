package com.nsu.interceptor;

import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.bean.common.AjaxBean;
import com.nsu.utils.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

public class LoginInterceptor implements HandlerInterceptor {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.debug("**********************  preHandle拦截  **********************");
		String url = request.getServletPath();
		HandlerMethod handlerMethod = null;
		boolean isAjax = false;
		try {
			handlerMethod = (HandlerMethod) handler;
			if (handlerMethod != null && handlerMethod.getBean() instanceof Anonymous){
				return true;
			}
			isAjax = handlerMethod.getMethodAnnotation(InterceptorAnno.class).isAjax();
		}catch (Exception e){
			return true;
		}
		if (url.contains("login") || url.contains("logout") || url.contains("register")) {
			return true;
		}else {
			if (request.getSession().getAttribute(Constants.LOGIN_USER)!=null) {
				return true;
			}
		}

		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") || isAjax){
			AjaxBean ajaxBean = new AjaxBean();
			LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
			ajaxBean.setStatus("500");
			ajaxBean.setMsg("身份信息已过期，请重新登录！");
			ajaxBean.put("url",url);
			ResponseUtil.write(response, JsonMapper.toJsonString(ajaxBean));
			log.debug("**********************  登陆拦截  **********************");
			return false;
		}


		log.debug("**********************  登陆拦截  **********************");
		response.sendRedirect(request.getContextPath()+Constants.LOGIN_URL);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
