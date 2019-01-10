package com.nsu.listener;

import javax.servlet.ServletContextEvent;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nsu.common.Constants;
import com.nsu.service.core.IConfigurationService;

public class ServletContextListener implements javax.servlet.ServletContextListener {
	
	
	Logger log = Logger.getLogger(ServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		String basePath = arg0.getServletContext().getContextPath();
		arg0.getServletContext().setAttribute(Constants.BASE_PATH, basePath);
		
		//arg0.getServletContext().setAttribute("picPath", "http://10.13.5.5:8080/pic/");

	}


	
	
	
	
	
	
	

}
