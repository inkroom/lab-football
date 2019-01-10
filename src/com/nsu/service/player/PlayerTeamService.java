package com.nsu.service.player;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
* @Title: PlayerMatchService.java
* @Package com.nsu.service.player
* @Description: 球员球队Service
* @author 侯松梁
* @date 2017年4月14日 下午4:23:05
* @version V1.0
 */
public interface PlayerTeamService {

	/**
	 * 获取球队球员
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPlayerTeam(String TEAM_ID) throws Exception;

	/**
	 * 根据球队id获取球队信息
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTeamInfoById(String TEAM_ID) throws Exception;

	/**
	 * 根据球员id获取球队
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamName(String A_ID) throws Exception;

	/**
	 * 根据球队id获取教练
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCoachName(String TEAM_ID) throws Exception;

	/**
	 * 根据球队id获取电话
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public String getTeamPhoneNum(String TEAM_ID) throws Exception;

}
