package com.nsu.service.school;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolFeatureService extends BaseService {
	
	private final static String GET_PEOPLE_LIST = "select * from school_feature where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getFeatureList(String schoolUrl,String type,Map<String, Integer> page,HttpServletRequest request) throws Exception {
		return jt.queryForList(GET_PEOPLE_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_PEOPLE_TATOL = "select count(*) from school_feature where school_url = ? and type = ? and _status = 1";
	public long getFeatureTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_PEOPLE_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_PEOPLE_MAP = "select * from school_feature where school_url = ? and type=? and sc_feature = ?";
	public Map<String, Object> getFeatureMap(String schoolUrl,String typeFeature,String scId,HttpServletRequest request) throws Exception {
		return jt.queryForMap(GET_PEOPLE_MAP,new Object[]{schoolUrl,typeFeature,scId});
	}

	
}