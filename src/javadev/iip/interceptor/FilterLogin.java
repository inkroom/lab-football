package javadev.iip.interceptor;

import java.io.IOException;

import javadev.core.Constants;
import javadev.core.util.InfoProtUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FilterLogin implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		if(request.getSession().getAttribute(Constants.LOGIN_USER)!=null){
			Cookie cookie = new Cookie("token",InfoProtUtil.getRandomString(16));
			response.addCookie(cookie);
			
			request.getSession().setAttribute("token", cookie.getValue());
		}
		
		arg2.doFilter(request, response);
	}

}
