package com.nsu.interceptor;

import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.common.Role;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.bean.common.AjaxBean;
import com.nsu.utils.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class RoleAuthorityInterceptor implements HandlerInterceptor {

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = null;
		String url = request.getServletPath();
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
		}

		Map user= null;
		Role role = null;
		try {
			user = (Map) (request.getSession().getAttribute(Constants.LOGIN_USER));
			role = (Role) (request.getSession().getAttribute(Constants.ROLE));
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (user != null && role !=null) {
			log.info(url);
			String roleString = url.substring(1,url.indexOf("/",1));
			
			if(role.getRoleName().equals(roleString)){
				return true;
			}else {
				url = request.getContextPath()+"/"+roleString+"/"+"error_authority.html";
				if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))||isAjax){
					isAjax = true;
				}
				if (!isAjax){
					response.sendRedirect(url);
				}else {
					AjaxBean ajaxBean = new AjaxBean();
					ajaxBean.setStatus("500");
					ajaxBean.setMsg("登录失效");
					ajaxBean.setSuccess(false);
					ajaxBean.put("url",url);
					ResponseUtil.write(response,ajaxBean);
				}
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");
				log.info("                            权限已拦截                       ");
				log.info("                           "+url + "                    ");
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");
				log.info("**********************************************************  ");

				return false;
			}
		} else {
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
			response.sendRedirect(request.getContextPath()+"/"+url+"/error_authority.html");
			return false;
		}
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
