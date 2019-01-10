package javadev.iip.service.fiveplan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import javadev.iip.service.BaseService;

public class FivePlanLoginService extends BaseService{
	
	/**
	 * 功能的描述: 查询五年计划学校账号密码，用于登录
	 * @author:朱春雨
	 * @return:Map<String, Object>
	 * Create Date:2016-9-1
	 */
	private static final String SQL_GET_FivePlan_BY_USERNAME = "select * from ACCOUNT join ORG on ACCOUNT.O_ID = ORG.O_ID where ACCOUNT.USERNAME=? and ACCOUNT.STATUS=1 and type = 2";
	
	public Map<String, Object> getFivePlanByUserName(String username)
	{
		try {
			return jt.queryForMap( SQL_GET_FivePlan_BY_USERNAME, new Object[] { username });
		} catch (Exception e) {
			return null;
		}
	}
	public void intoLogin(String username,Object object)
	{
		jt.update("update ACCOUNT set RECENT_LOGIN=sysdate where a_id=?",new Object[]{object});
		//更新操作
		 jt.update("declare  r varchar2(10); begin PRC_UPD_ACCOUNT_ZCY(?,?,r); dbms_output.put_line(r); end;",new Object[]{username,object});
	}
	
	private static final String SQL_GET_LOGIN_TIME = "select OPEN_TIME,SHUTDOWN_TIME,END_DATE_DISTRICT from REPORT_RECORD WHERE STATUS = 1";
	public Map<String, Object> getLoginTime() throws Exception{
		return jt.queryForMap(SQL_GET_LOGIN_TIME);
	}

}
