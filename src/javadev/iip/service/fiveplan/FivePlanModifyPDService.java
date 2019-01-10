package javadev.iip.service.fiveplan;

import java.util.Date;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import javadev.iip.service.BaseService;

public class FivePlanModifyPDService extends BaseService{

	private static final String SQL_GET_RIGHT_UPDATE_PASSWORD = "SELECT COUNT(A_ID) FROM ACCOUNT WHERE A_ID=? AND STATUS=1";
	/**
	 * 功能的描述: 获取修改密码权限
	 * @author:朱春雨
	 * return:boolean
	 * Create Date:2016-9-7
	 */
	public boolean getRightUp(String aId){
		return (jt.queryForObject(SQL_GET_RIGHT_UPDATE_PASSWORD, Integer.class, new Object[]{aId}) == 1);
	}
	
	//更新密码sql语句
	private static final String SQL_UPDATE_PASSWORD = "UPDATE ACCOUNT SET PASSWORD=?,OPER_BY=?,OPER_DATE=SYSDATE WHERE USERNAME=? AND STATUS=1";
	/**
	 * 功能的描述: 修改密码
	 * @author:朱春雨
	 * return:boolean
	 * Create Date:2016-9-7
	 */
	public boolean updatePassword(String username, String aId, String newPassword) {
		

			return (jt.update(SQL_UPDATE_PASSWORD, new Object[]{newPassword,aId,username})==1);
		
	}
	//查询球队账号密码sql语句
		private static final String SQL_GET_FivePlan_BY_USERNAME = "select A_ID,USERNAME,PASSWORD,SALT,STATUS,O_ID,TYPE,O_NAME from ACCOUNT where USERNAME=? and STATUS=1";
		/**
		 * 功能的描述: 查询球队账号密码，用于判断修改时旧密码是否正确
		 * @author:朱春雨
		 * @return:Map<String, Object>
		 * Create Date:2016-9-7
		 */
		public Map<String, Object> getFivePlanByUserName(String username) {
			try {
				return jt.queryForMap(SQL_GET_FivePlan_BY_USERNAME, new Object[] { username });
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}
}
