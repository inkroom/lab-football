package com.nsu.dao.team;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamMobileResultBean;

/**
 * 球队手机端dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-05-10 16:14:47
 */
public interface TeamMobileDao {

	/**
	 * 根据aid查询球队teamid
	 * @author ljl
	 * @createDate 2017-05-10 16:17:25
	 * @param aid
	 * @return
	 */
	public String findTeamIDByAID(String aid) throws Exception;

	/**
	 * 根据球队id查询已经结束的比赛信息
	 * @author ljl
	 * @createDate 2017-05-10 16:27:20
	 * @param teamID
	 * @return
	 */
	public List<TeamMobileResultBean> findTeamMatchesInfo(String teamID) throws Exception;
	
	/**
	 * 根据球队id查询正在进行的比赛信息
	 * @author ljl
	 * @createDate 2017-05-10 16:27:20
	 * @param teamID
	 * @return
	 */
	public List<TeamMobileResultBean> findTeamMatchesIsPlayingInfo(String teamID) throws Exception;

	/**
	 * 根据球队id和赛事id查询赛程信息
	 * @author ljl
	 * @createDate 2017-05-10 16:43:27
	 * @param comID
	 * @param teamID
	 * @return
	 */
	public List<Map<String, Object>> findRaceDetailsInfo(@Param("comID") String comID,@Param("teamID") String teamID) throws Exception;

	
}
