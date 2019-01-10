package com.nsu.service.admin.school;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AdminSchoolService extends BaseService {
	
	public boolean getSchoolName(String schoolname) {
		return (jt.queryForObject("call get_school_count(?,?)", new Object[] {schoolname, 1}, Integer.class) == 0);
	}

	public boolean getSchoolUrl(String schoolurl) {
		return (jt.queryForObject("call get_school_count(?,?)", new Object[] {schoolurl, 2}, Integer.class) == 0);
	}

	public Map<String, Object> addSchool(String schoolname, String schoolurl, String schooltype, String operby) throws Exception {
		return jt.queryForMap("call add_school(?,?,?,?)", new Object[] {schoolname,schoolurl,schooltype,operby});

	}
	
	public List<Map<String, Object>> getSchoolInfo(Map<String, Integer> page){
		return jt.queryForList("call get_school_list(?,?)",new Object[]{page.get("pageStart"),page.get("pageSize")});
	}
	
	public long getSchoolTotal(){
		return jt.queryForObject("call get_school_total",long.class);
	}
	
	
	public boolean updateSchoolStatus(String url,String username,String type){
		//call update_school_status('1300000002','meixiebing','2');
		return jt.queryForMap("call update_school_status(?,?,?)",new Object[]{username,url,type}).get("success").equals("1");
	}
	
}
