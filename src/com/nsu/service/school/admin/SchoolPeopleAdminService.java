package com.nsu.service.school.admin;

import java.util.List;
import java.util.Map;

import com.nsu.service.BaseService;
import com.nsu.util.base.Clear;
import org.springframework.stereotype.Service;

@Service
public class SchoolPeopleAdminService extends BaseService {
	private final static String ADD_PEOPLE = "INSERT INTO school_people (_name, pic, content, type, school_url, oper_date, _status , content_text) VALUES (?, ?, ?, ?, ?, now(), '1' , ?);";
	public boolean addPeople(String name,String content,String pic,String type,String schoolUrl){
		String content_text = Clear.clearAll(content);
		if (content_text.length()>40) {
			content_text = content_text.substring(0, 40) + "........";
		}else {
			content_text = content_text.substring(0, content_text.length()) + "........";
		}
		return jt.update(ADD_PEOPLE,new Object[]{name,pic,content,type,schoolUrl,content_text})==1;
	}
	
	private final static String GET_PEOPLE_LIST = "select * from school_people where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getPeopleList(String schoolUrl,String type,Map<String, Integer> page) {
		return jt.queryForList(GET_PEOPLE_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_PEOPLE_TATOL = "select count(*) from school_people where school_url = ? and type = ? and _status = 1";
	public long getPeopleTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_PEOPLE_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_PEOPLE_MAP = "select * from school_people where school_url = ? and sc_people = ?";
	public Map<String, Object> getPeopleMap(String schoolUrl,String scId) throws Exception {
		return jt.queryForMap(GET_PEOPLE_MAP,new Object[]{schoolUrl,scId});
	}
	
	private final static String UPDATE_PEOPLE = "update school_people set _name =? , content =? ,content_text = ?,oper_date = now() where sc_people = ? and school_url = ? and _status = 1";
	private final static String UPDATE_PEOPLE_PIC = "update school_people set _name =? , pic = ? , content =? , content_text = ?,oper_date = now() where sc_people = ? and school_url = ? and _status = 1";
	public boolean updatePeople(String name,String content,String pic,String scId,String schoolUrl) throws Exception {
		String content_text = Clear.clearAll(content);
		if (content_text.length()>40) {
			content_text = content_text.substring(0, 40) + "........";
		}else {
			content_text = content_text.substring(0, content_text.length()) + "........";
		}
		if (pic == null) {
			return jt.update(UPDATE_PEOPLE,new Object[]{name,content,content_text,scId,schoolUrl}) == 1;
		}else {
			return jt.update(UPDATE_PEOPLE_PIC,new Object[]{name,pic,content,content_text,scId,schoolUrl}) == 1;
		}
	}
	
	private final static String DELETE_PEOPLE = "update school_people set _status = '0' where sc_people = ? and school_url = ?";
	public boolean deletePeople(String schoolUrl,String scId){
		return jt.update(DELETE_PEOPLE,new Object[]{scId,schoolUrl}) == 1 ;
	}
	
}