package com.nsu.service.site;

import java.util.Map;

public interface SiteService {
	/**
	 * 登录时判断账号是否存在
	 * @return
	 */
	boolean selectName(String name)throws  Exception;
	
	/**
	 * 登录时通过账号判断密码是否正确
	 * @param name
	 * @return
	 */
	String selectPassword(String name)throws  Exception;
	
	long selectSE_ID(int AID) throws Exception;
	/**
	 * 查询A_ID
	 * @param name
	 * @return
	 */
	String selectA_ID(String name)throws  Exception;
	/**
	 * 查询用户状态是否可以登录
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	boolean selectStatus(String userName)throws  Exception;
	/**
	 * 查询账号名称
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	String selectuserName(int A_ID)throws  Exception;
	/**
	 * 得到用户基本信息
	 * @param username
	 * @param pushInfo
	 * @param deviceInfo
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getUserInfo(String username,String pushInfo,String deviceInfo)throws  Exception;
	/**
	 * 登录后判断用户是否完善信息
	 * @param AID
	 */
	String PreInfo(int AID) throws  Exception;
	/**
	 * 完善信息时插入信息
	 * @param name
	 * @param phone
	 * @param AID
	 * @return
	 * @throws Exception
	 */
	String insertPreInfo(String name,String phone,int AID)throws  Exception;
}
