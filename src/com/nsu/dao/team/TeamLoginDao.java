package com.nsu.dao.team;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 球队管理员登录dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 17:30:55
 */
public interface TeamLoginDao {

	/**
	 * 查询球队管理员账号信息
	 * @author ljl
	 * @createDate 2017-04-11 17:24:27
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findTeamUserInfo(String username) throws Exception;

	/**
	 * 查询球队领队
	 * @author ljl
	 * @createDate 2017-04-12 20:08:41
	 * @param aid
	 * @return
	 */
	public String findTeamLeader(String aid) throws Exception;
	
	/**
	 * 更新球队管理员的手机信息
	 * @author ljl
	 * @createDate 2017-04-21 18:45:27
	 * @param mobile
	 * @return
	 */
	public int updateMobileInfo(@Param("mobile") Map<String, Object> mobile) throws Exception;

	/**
	 * 手机端登录后获取的球队基本信息数据
	 * @author ljl
	 * @createDate 2017-04-21 18:59:13
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserInfo(String username) throws Exception;

	/**
	 * 根据球队ID 查询教练员信息
	 * @author ljl
	 * @createDate 2017-04-23 15:25:33
	 * @param teamID
	 * @return
	 */
	public List<Map<String, Object>> getTeamCoachsInfo(Object teamID) throws Exception;
	
	/**
	 * 根据球队ID 查询球员员信息
	 * @author ljl
	 * @createDate 2017-04-23 15:52:09
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamPlayersInfo(Object teamID) throws Exception;

	/**
	 * 根据球员的pid查询
	 * @author ljl
	 * @createDate 2017-05-03 17:02:00
	 * @param pid
	 * @return
	 */
	public Map<String, Object> findTeamPlayerScore(Object pid) throws Exception;
}
