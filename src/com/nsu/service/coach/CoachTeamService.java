package com.nsu.service.coach;

import java.util.List;
import java.util.Map;

public interface CoachTeamService {
	/**
	 * 通过教练员的ID搜索所有球队
	 * @param coachID
	 * @return
	 */
	public List<String> getCoachTeams(String coachID);
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
	public int coachMatchCount(String teamID);
	/**
	 * 通过球队ID获取比赛
	 * @param teamID
	 * @return
	 */
	public List<Map<String, Object>> coachMatchInfo(String teamID,int page,int pageSize);
	/**
	 * 通过赛事ID查询比赛完成的结果
	 * @param raceID
	 * @return
	 */
	public Map<String, Object> getRaceResultsDetails(String raceID);
}
