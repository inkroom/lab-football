package com.nsu.service.school;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolAdvertisementService extends BaseService {
	
	private static final String GET_ADVERTISEMENT_BY_URL = "select * from advertisement where school_url = ? and type =? and _status=1";
	public List<Map<String, Object>> getAdvertisement(String type,String schoolUrl) throws Exception {
		return jt.queryForList(GET_ADVERTISEMENT_BY_URL,new Object[]{schoolUrl,type});

	}
	
	private static final String GET_ADVERTISEMENT_FOUTH_BY_URL = "select * from advertisement where school_url = ? and position = ? and type =? and _status=1";
	public List<Map<String, Object>> getAdvertisementFouth(String position,String type,String schoolUrl) throws Exception {
	
		return jt.queryForList(GET_ADVERTISEMENT_FOUTH_BY_URL,new Object[]{schoolUrl,position,type});

	}
	
	
}
