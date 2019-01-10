package com.nsu.service.team;

import java.util.Map;
/**
 * 球队管理员service登录接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 17:15:49
 */
public interface ITeamLoginService {

	/**
	 * 查询球队管理员用户信息
	 * @author ljl
	 * @createDate 2017-04-10 17:16:21
	 * @param username 账号
	 * @return
	 */
	public Map<String, Object> findAccountInfo(String username);

	/**
	 * 查询球队领队
	 * @author ljl
	 * @createDate 2017-04-12 20:07:06
	 * @param aid
	 * @return
	 */
	public String findTeamLeader(String aid);

	/**
	 * 获取球队基本信息，更新球队手机信息
	 * @author ljl
	 * @createDate 2017-04-21 18:47:32
	 * @param username
	 * @param pushInfo
	 * @param deviceInfo
	 * @return
	 */
	public Map<String, Object> getUserInfo(String username, String pushInfo, String deviceInfo) throws Exception;

}
