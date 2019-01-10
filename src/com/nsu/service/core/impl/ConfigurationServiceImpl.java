package com.nsu.service.core.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.nsu.service.core.IConfigurationService;

@Service(value="configurationService")
public class ConfigurationServiceImpl implements IConfigurationService {
	
	private String savePath;


	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}


	@Override
	public String getTheSavePath(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return savePath+request.getContextPath();
	}


	
	
	
}
