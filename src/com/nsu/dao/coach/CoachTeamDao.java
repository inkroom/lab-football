package com.nsu.dao.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CoachTeamDao {
	/**
	 * 通过教练员的ID搜索所有球队
	 * @param coachID
	 * @return
	 */
	public List<String> getCoachTeams(String coachID);
	/**
	 * 获得球队的所有教练
	 * @param teamID
	 * @return
	 */
	public List<String> getTeamCoachs(String teamID);
	/**
	 * 通过球队ID搜索教练员所带领某一球队的信息
	 * @param teamID
	 * @return
	 */
	public Map<String, Object> coachTeamDetail(String teamID);
	/**
	 * 通过球队ID搜索教练员所带领某一球队球员的相关信息
	 * @param teamID
	 * @return
	 */
	public List<Map<String, Object>> coachTeamPlayerDetail(String teamID);
	/**
	 * 通过球队ID获取比赛
	 * @param teamID
	 * @return
	 */
	public int coachMatchCount(@Param("teamID")String teamID);
	/**
	 * 通过球队ID获取比赛
	 * @param teamID
	 * @return
	 */
	public List<Map<String, Object>> coachMatchInfo(@Param("teamID")String teamID,@Param("index")int index,@Param("size")int size);
	/**
	 * 通过赛事ID查询比赛完成的结果
	 * @param raceID
	 * @return
	 */
	public Map<String, Object> getRaceResultsDetails(String raceID);
	/**
	 * 比赛详情的第一个表有人数主客队和比分 
	 * @param raceID
	 * @return
	 */
	public Map<String, Object> getRaceScore(String raceID);
	/**
	 * 警告人员名单
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRaceWarning(String raceID);
	/**
	 * 罚出人员名单
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRaceSendOff(String raceID);
	/**
	 * 红牌，罚球，错判情况
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRacePunish(String raceID);
}
