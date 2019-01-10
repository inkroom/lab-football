package javadev.iip.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import javadev.core.Constants;
import javadev.core.bean.BeanManager;
import javadev.core.common.Anonymous;
import javadev.core.util.InfoProtUtil;
import javadev.core.util.QueryUtil;
import javadev.iip.service.ServiceManager;
import javadev.iip.service.UserService;

/**
 * 登录拦截器，用于阻止未登录用户访问系统
 */
public class RoleAuthorityInterceptor implements Interceptor {
	protected final Log log = LogFactory.getLog(getClass());

	UserService userService;

	public void destroy() {
	}

	public void init() {
		userService = ((ServiceManager) BeanManager.getBean("serviceManager")).getUserService();
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action) invocation.getAction();
		String actionName = invocation.getInvocationContext().getName();
		String actionURL = ServletActionContext.getRequest().getServletPath();	
		log.info("-----action:" + action + "-" + actionName + "-" + actionURL);
		
		
		// 这里要求实现了Anonymous接口的Action以及固定的login和logoutAction可以跳过登录拦截		
		if (action instanceof Anonymous) {
			log.info("--------actionUrl:" + actionURL);
			if(userService.ifRoleHasAuthority("0", actionURL)){
				return invocation.invoke();
			}else{
				return "authorityError";
			}
		} else {
			Map user=(Map) (ActionContext.getContext().getSession().get(Constants.LOGIN_USER));
			if (user != null) {

				Object role = user.get("TYPE");
				if (role != null&&userService.ifRoleHasAuthority(role.toString(), actionURL)) {
					return invocation.invoke();
				} else {
					return "authorityError";
				}
			} else {
				return "authorityError";
			/*	ActionContext.getContext().getSession().put(Constants.ORIGINAL_URL,
						QueryUtil.getRequestURL(ServletActionContext.getRequest()));
				return Action.LOGIN;*/
			}
		}
	}

	private String dd(ActionInvocation invocation) throws Exception {
		//没有请求参数的action直接放行
		if(ServletActionContext.getRequest().getParameterMap().size()==0)
			return invocation.invoke();

		//获取客户端令牌
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String token="";
		
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equalsIgnoreCase("token"))
				token = cookies[i].getValue();
		}

		//客户端令牌的比对
		if(token!=null&&token.equals(ServletActionContext.getRequest().getSession().getAttribute("token"))){
			return invocation.invoke();
		}else
			return "authorityError";
	}

}
