package com.nsu.service.player.login;
import java.util.Map;
/**
 * 
* @Title: PlayerLoginService.java
* @Package com.nsu.service.player.login
* @Description: 球员登录Service
* @author 侯松梁
* @date 2017年4月10日 下午7:30:42
* @version V1.0
 */
public interface PlayerLoginService {

	/**
	 * 根据用户名获取用户信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayer(Map<String, Object> map) throws Exception;

	/**
	 * 更新登录时间
	 * @param map
	 * @throws Exception
	 */
	public void updateLoginTime(Map<String, Object> map) throws Exception;

	/**
	 * 存入球员注册信息
	 *
	 * @Description (插入注册帐号信息)
	 * @param user
	 * @return
	 * @throws Exception
	 * @author hm
	 */
	public Boolean insertUser(String user,String passwd,String phone,String bir) throws Exception;

	/**
	 * 判断用户是否存在
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Boolean userIsExist(String username) throws Exception;

	/**
	 * 判断身份证是否存在
	 * @param id_card
	 * @return
	 * @throws Exception
	 */
	public Boolean idCardIsExist (String id_card) throws Exception;

	/**
	 * @Description (获取用户信息)
	 * @param username
	 * @param pushInfo
	 * @param deviceInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserInfo(String username,String pushInfo,String deviceInfo) throws Exception;

	/**
	 * 根据id获取球员姓名
	 *
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserNameById(String A_ID) throws Exception;

	/**
	 *
	 * @Description (获取当前username所属的A_ID)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String getA_ID(String user) throws Exception;
}
