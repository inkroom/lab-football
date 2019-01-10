package com.nsu.service.school.admin;

import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolFoundAdminService extends BaseService {
	private final static String GET_FOUND_BY_URl = "SELECT * FROM school_founds where school_url = ? and _status=1;";  
	public Map<String, Object> getFound(String schoolUrl){
		return jt.queryForMap(GET_FOUND_BY_URl, schoolUrl);
	}
	
	private final static String UPATE_IN_FOUND_BY_URL = "update school_founds set create_date=now() , title_in = ? , content1 = ? , content2 = ? , content3 = ? , content4 = ? , content5 = ? , content6 = ? , content7 = ? where school_url = ? and _status = 1  ";
	public boolean updateInFound(Map<String, Object> map){
		if (jt.update(UPATE_IN_FOUND_BY_URL, new Object[]{map.get("titileIn"),map.get("content1"),map.get("content2"),map.get("content3"),map.get("content4"),map.get("content5"),map.get("content6"),map.get("content7"),map.get("schoolUrl")}) == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	
	private final static String UPATE_OUT_FOUND_BY_URL = "update school_founds set create_date=now() , title_out = ? , content8 = ? , content9 = ? , content10 = ? , content11 = ? , content12 = ? , content13 = ? , content14 = ? , content15 = ? , content16 = ? , content17 = ? , content18 = ? , content19 = ? , content20 = ? , content21 = ? , content22 = ? , content23 = ? , content24 = ? where school_url = ? and _status = 1  ";
	public boolean updateOutFound(Map<String, Object> map){
		if (jt.update(UPATE_OUT_FOUND_BY_URL, new Object[]{map.get("titileOut"),map.get("content8"),map.get("content9"),map.get("content10"),map.get("content11"),map.get("content12"),map.get("content13"),map.get("content14"),map.get("content15"),map.get("content16"),map.get("content17"),map.get("content18"),map.get("content19"),map.get("content20"),map.get("content21"),map.get("content22"),map.get("content23"),map.get("content24"),map.get("schoolUrl")}) == 1) {
			return true;
		}else {
			return false;
		}
	}
	
}
