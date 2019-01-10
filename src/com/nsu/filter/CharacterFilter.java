package com.nsu.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class CharacterFilter extends OncePerRequestFilter {
	
	private final Log log = LogFactory.getLog(getClass());
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.debug("**********************  xss  filter start  **********************");
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String servletPath=request.getServletPath();
		String requestURI=request.getRequestURI();
		log.debug("**********************  path:"+path+"  **********************");  
		log.debug("**********************  basePath:"+basePath+"  **********************");   
		log.debug("**********************  servletPath:"+servletPath+"  **********************");  
		log.debug("**********************  requestURI:"+requestURI+"  **********************");
		try{
			XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper((HttpServletRequest)request);
			filterChain.doFilter(xssRequestWrapper, response);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
	}

	

	
}
