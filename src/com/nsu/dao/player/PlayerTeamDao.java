package com.nsu.dao.player;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
* @Title: PlayerMatchDao.java
* @Package com.nsu.dao.player
* @Description: 球队信息Dao
* @author 侯松梁
* @date 2017年4月14日 下午4:21:54
* @version V1.0
 */
public interface PlayerTeamDao {
	
	/**
	 * 获取球队球员
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPlayerTeam(@Param("TEAM_ID")String TEAM_ID) throws Exception;
	
	/**
	 * 根据球队id获取球队信息
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTeamInfoById(@Param("TEAM_ID")String TEAM_ID) throws Exception;
	
	/**
	 * 根据球员id获取球队
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamName(@Param("A_ID")String A_ID) throws Exception;
	
	/**
	 * 根据球队id获取教练
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCoachName(@Param("TEAM_ID") String TEAM_ID) throws Exception;
	
	/**
	 * 根据球队id获取球队比赛记录
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTeamMatchInfo(@Param("TEAM_ID")String TEAM_ID) throws Exception;

	/**
	 * 根据球队id获取电话
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public String getTeamPhoneNumber(@Param("TEAM_ID")String TEAM_ID) throws Exception;

}
