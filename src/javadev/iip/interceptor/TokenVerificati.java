package javadev.iip.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javadev.core.common.Anonymous;
import javadev.core.common.TokenImmune;
import javadev.core.util.InfoProtUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;



public class TokenVerificati implements Interceptor{

	/**
	 * token令牌比对
	 */
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String actionName = invocation.getInvocationContext().getName();
		String token = request.getParameter("token");
		Action action = (Action) invocation.getAction();
		
		//拥有tokenImmue注解的action方法直接跳过此拦截器
		Method method = invocation.getAction().getClass().getMethod(invocation.getProxy().getMethod(), null);
		Annotation antation = method.getAnnotation(TokenImmune.class);
		if(antation!=null){
			return invocation.invoke();
		}
		
		//action名称中带有login词语的都会重新分配token令牌
		if(actionName.toLowerCase().contains("login")){
			String newToken = InfoProtUtil.getRandomString(16);
			request.getSession().setAttribute("token", newToken);
			response.setHeader("token", newToken);
		}
		
		//没有请求参数的action直接放行
		if(request.getParameterMap().size()==0||
				action instanceof Anonymous){
			return invocation.invoke();
		}
		
		//token令牌比对
		if(token!=null&&token.equals(request.getSession().getAttribute("token"))){
			//生成新的token
			String newToken = InfoProtUtil.getRandomString(16);
			request.getSession().setAttribute("token", newToken);
			response.setHeader("token", newToken);
			
			String result = invocation.invoke();
			
			return result;
		}
		
		//将错误信息打给浏览器
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().write("<script>alert('此页面为高度安全页面，为了您信息的安全,请不要点击浏览器的回退、刷新按钮或重复提交数据！')</script>");
		
		//将页面返回访问者的地址
		
		
		return "tokenReturn";
//		return invocation.invoke();
	}

}
