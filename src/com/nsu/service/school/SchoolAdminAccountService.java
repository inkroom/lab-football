package com.nsu.service.school;

import java.util.Map;

import org.ietf.jgss.Oid;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolAdminAccountService extends BaseService {
	
	private final static String GET_SCHOOL_ACCOUNT_BY_USERNAME = "select * from account where username = ? and type= 3";
	public Map<String, Object> getSchoolAccountByUsername(String userName) throws Exception {
		return jt.queryForMap(GET_SCHOOL_ACCOUNT_BY_USERNAME,userName);
	}
	
	/**
	 * 更新登录时间
	 */
	private static final String UPDATE_LOGIN_ = "call update_account_login(?);";
	public void updateSchoolLogin(String userName) throws Exception {	
		jt.update(UPDATE_LOGIN_, userName);
	}
	
	private final static String GET_SCHOOL_INFO_BY_USERNAME = "select * from school where username = ?";
	public Map<String, Object> getSchoolInfo(String userName) throws Exception {
		return jt.queryForMap(GET_SCHOOL_INFO_BY_USERNAME,userName);
	}
	
	
	
	private final static String UPDATE_PASSWORD = "UPDATE account SET _password=? WHERE username=? ;";
	public boolean updatePassword(String username,String password) throws Exception{
		if(jt.update(UPDATE_PASSWORD,new Object[]{password,username}) == 1){
			return true;
		}else{
			return false;
		}
	}
}
