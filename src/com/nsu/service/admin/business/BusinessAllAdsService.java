package com.nsu.service.admin.business;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BusinessAllAdsService extends BaseService {
	private static final String GET_ALL_SCHOOLS = "select s_id,school_name,school_type,_status from school where _status = 1";
	public List<Map<String, Object>> getAllSchools(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jt.queryForList(GET_ALL_SCHOOLS);
		return list;
	}
	private static final String GET_SCHOOL_BY_SID = "select s_id,school_name,school_type,_status from school where _status = 1 and s_id like ? and school_name like ?";
	public Map<String, Object> getSchoolBySid(String s_id,String s_name){
		Map<String, Object> map = new HashMap<String, Object>();
		map = jt.queryForMap(GET_SCHOOL_BY_SID,new Object[]{s_id,s_name});
		return map;
	}
}
