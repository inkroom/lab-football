package com.nsu.interceptor;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsu.bean.index.AjaxBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.service.core.IRoleAuthorityService;


public class RoleAuthorityInterceptor implements HandlerInterceptor {

	/**
	 * 0管理员，1球员，2教练，3机构, 4.球队, 5.现场管理员
	 */

	@Resource(name="iRoleAuthorityService")
	private IRoleAuthorityService iRoleAuthorityService;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerMethod handlerMethod = null;
		String url = request.getServletPath();
		boolean isAjax = false;
		try {
			handlerMethod = (HandlerMethod) handler;
			if (handlerMethod != null && handlerMethod.getBean() instanceof Anonymous ){
				return true;
			}
			isAjax = handlerMethod.getMethodAnnotation(InterceptorAnno.class).isAjax();
		}catch (Exception e){

		}

		if (url.contains("login") || url.contains("logout") || url.contains("register")) {
			return true;
		}

		Map user=(Map) (request.getSession().getAttribute(Constants.LOGIN_USER));
		String role = null;
		try {
			role = user.get("A_TYPE").toString();
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (user != null && role !=null) {
			log.info(url);
			String roleString = url.substring(1,url.indexOf("/",1));

			if (role.equals("1") && roleString.equals("player")){
				return true;
			}else if (role.equals("2") && roleString.equals("coach")){
				return true;
			}else if (role.equals("3") && roleString.equals("org")){
				return true;
			}else if (role.equals("4") && roleString.equals("team")){
				return true;
			}else if (role.equals("5") && roleString.equals("site")){
				return true;
			}else if (role.equals("0") && roleString.equals("admin")){
				return true;
			}else {
				url = url.substring(1,url.indexOf("/",1));
				url = request.getContextPath()+"/"+url+"/"+"error_authority.html";

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
					LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
					map.put("url",url);
					ajaxBean.setBody("url",url);
					ResponseUtil.write(response,ajaxBean);
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
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
