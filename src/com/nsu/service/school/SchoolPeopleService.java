package com.nsu.service.school;

import java.util.List;
import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolPeopleService extends BaseService {
	
	private final static String GET_PEOPLE_LIST = "select * from school_people where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getPeopleList(String schoolUrl,String type,Map<String, Integer> page) throws Exception {
		return jt.queryForList(GET_PEOPLE_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_PEOPLE_TATOL = "select count(*) from school_people where school_url = ? and type = ? and _status = 1";
	public long getPeopleTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_PEOPLE_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_PEOPLE_MAP = "select * from school_people where school_url = ? and type= ? and sc_people = ?";
	public Map<String, Object> getPeopleMap(String schoolUrl,String typePeople,String scId) throws Exception {
		return jt.queryForMap(GET_PEOPLE_MAP,new Object[]{schoolUrl,typePeople,scId});
	}

	
}