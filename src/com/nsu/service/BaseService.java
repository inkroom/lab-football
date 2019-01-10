package com.nsu.service;


import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class BaseService {
	@Autowired
	protected JdbcTemplate jt;
	
	protected Logger log = Logger.getLogger(getClass());

	private String startYear;
	
	private String endYear;
	
	protected Calendar time = Calendar.getInstance();
	
	public BaseService(){
		startYear = (time.get(Calendar.YEAR)-1)+"-01-01";
		endYear = (time.get(Calendar.YEAR)+1)+"-01-01";
	}
	
	public void setJdbcTemplate(JdbcTemplate jt) {
		this.jt = jt;
	}

	public String getStartYear() {
		return startYear;
	}

	public String getEndYear() {
		return endYear;
	}
	
}
