package com.nsu.service.school.admin;

import java.util.Map;
import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolInfoAdminService extends BaseService {
	private final static String GET_OVERVIEW_BY_URL_TYPE ="select * from school_info where school_url = ? and type=? and _status = 1";
	public Map<String, Object> getOverviewAdminService(String schoolUrl,String type) throws Exception {
		return jt.queryForMap(GET_OVERVIEW_BY_URL_TYPE,new Object[]{schoolUrl,type});

	}
	
	
	private final static String UPDATE_OVERVIEW_HEADMASTER_MESSAGE = "update school_info set content = ? , oper_date = now() where school_url = ? and type=? ";
	public boolean updateOverviewInfo(String content,String schoolUrl,String type) throws Exception {
		return jt.update(UPDATE_OVERVIEW_HEADMASTER_MESSAGE,new Object[]{content,schoolUrl,type}) == 1 ;
	}
}
