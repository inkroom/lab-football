package com.nsu.listener;

import javax.servlet.ServletContextEvent;
import org.apache.log4j.Logger;
import com.nsu.service.ServiceManager;

public class ServletContextListener implements javax.servlet.ServletContextListener {
	private ServiceManager serviceManager;

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	Logger log = Logger.getLogger(ServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		
		
		String basePath = event.getServletContext().getContextPath();
		event.getServletContext().setAttribute("basePath", basePath);
		
		event.getServletContext().setAttribute("ss", "http://"+"120.78.187.96"+"/ss/");
		
		event.getServletContext().setAttribute("picPath", "http://"+"localhost"+"/pic/");
		
		event.getServletContext().setAttribute("submitWarnInfo", "注意：保存按钮是将本次填写的数据添加到系统中，提交按钮会将保存在系统中的数据更新为正式数据，可被上级机构查看统计。<br/>(点击提交按钮前必须确认点击过保存按钮)");
		
		event.getServletContext().setAttribute("submitSaveInfo", "将本次填写的数据添加到系统中");
		
		event.getServletContext().setAttribute("submitPrimaryInfo", "将保存的数据更新为正式数据");
	}

}
