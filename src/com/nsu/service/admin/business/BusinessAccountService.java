package com.nsu.service.admin.business;

import java.util.Map;


import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BusinessAccountService extends BaseService {
	
	private static final String GET_ADMIN_SCHOOL_O_NAME_BY_O_ID = "call get_account(?,?)";
	public Map<String, Object> getAdminByUsername(String userName,String type) throws Exception {
		Map<String, Object> user = jt.queryForMap(GET_ADMIN_SCHOOL_O_NAME_BY_O_ID,new Object[]{userName,type});
		return user;
	}
	
	/**
	 * 更新登录时间
	 */
	private static final String UPDATE_LOGIN_ = "call update_account_login(?);";
	public void updateAdminBLogin(String userName) throws Exception {	
		jt.update(UPDATE_LOGIN_, userName);
	}

	
	
	/**
	 * 修改密码
	 * @param passoword
	 * @return
	 */
	private static final String UPDATE_PASSWORD = "UPDATE ACCOUNT SET PASSWORD = ? , SALT = ? where USERNAME = ?";
	private static final String UPDATE_PASSWORD_STATUS = "UPDATE ACCOUNT SET OPER_DATE = sysdate , OPER_BY = ? where USERNAME = ?";
	public boolean updateSchoolPassword(String[] passoword,String userName,String a_Id) throws Exception {
			if (jt.update(UPDATE_PASSWORD, passoword[0],passoword[1],userName) == 1) {
				jt.update(UPDATE_PASSWORD_STATUS, a_Id,userName);
				return true;
			}else{
				return false;
			}
	}
}
