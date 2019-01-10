package com.nsu.service.coach;

import java.util.List;
import java.util.Map;

public interface CoachApplyTeamService {
	/**
	 * 通过教练员ID获取当前提交过申请的球队的条数
	 * @param coachID
	 * @return
	 */
	int getApplyedTeamsTotal(String coachID);
	/**
	 * 通过教练员ID获取当前提交过的球队的申请状态
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getApplyTeamsInfo(String coachID,int page,int pageSize);
	/**
	 * 通过球队ID查询教练员申请的球队信息
	 * @param teamID
	 * @return
	 */
	Map<String, Object> getApplyTeamInfo(String teamNum);
	/**
	 * 根据教练员ID和teamID判断教练员是否可以申请此
	 * @param coachID
	 * @param teamID
	 * @return
	 */
	boolean coachApplyTeamStatus(String coachID,String teamNum);
	/**
	 * 根据教练员ID和teamID添加一条教练员申请记录
	 * @param coachID
	 * @param teamID
	 * @return
	 */
	boolean coachApplyTeam(String coachID,String teamID);
	/**
	 * @param teamID
	 * @return
	 */
	 Map<String , Object> selectAidByteamID(String teamID);
	/**
	 * @param coachID
	 * @return
	 */
	Map<String, Object> selectcoachNameAndAidBycoachID(String coachID);

}
