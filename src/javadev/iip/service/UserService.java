package javadev.iip.service;


public class UserService extends BaseService {

	private static final String SQL_GET_ROLE_ACTION = "select count(*)  from PRIV_CONF where ACTION_NAME=? and TYPE=?";

	/**
	 * 
	 * 功能的描述: 查询当前角色是否可以执行当前action
	 * 
	 * @author:陈建
	 * @param:String role,
	 *                   角色号
	 * @param:String actionURL,想要执行的action地址
	 * @return:boolean, 可以执行返回true,不能执行返回false
	 * Create Date:2016-4-28
	 */
	public boolean ifRoleHasAuthority(String type, String actionURL) {
		int num = jt.queryForObject(SQL_GET_ROLE_ACTION, new Object[] { actionURL,type }, Integer.class);
		return (num > 0);
	}
}
