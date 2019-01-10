package com.nsu.listener;

import com.nsu.common.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;

public class ServletContextListener implements javax.servlet.ServletContextListener {
	
	
	Logger log = Logger.getLogger(ServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		String basePath = arg0.getServletContext().getContextPath();
		arg0.getServletContext().setAttribute(Constants.BASE_PATH, basePath);
		
		//arg0.getServletContext().setAttribute("picPath", "http://10.13.5.5:8080/pic/");

	}


	
	
	
	
	
	
	

}
