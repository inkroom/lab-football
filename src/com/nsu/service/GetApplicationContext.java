package com.nsu.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GetApplicationContext implements ApplicationContextAware {

	private static ApplicationContext ac;
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ac = arg0;
	}
	
	public static ApplicationContext getApplicationContext(){
		return ac;
	}
	
	public static Object getBean(String name){
		return ac.getBean(name);
	}
}
