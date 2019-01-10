package com.nsu.dao.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CoachApplyTeamDao {
	/**
	 * 通过教练员ID获取当前提交过申请的球队的条数
	 * @param coachID
	 * @return
	 */
	int getApplyedTeamsTotal(String coachID);
	/**
	 * 通过教练员ID获取当前提交过的球队的申请状态
	 * @param coachID
	 * @begin 从哪一条开始
	 * @end 条数
	 * @return
	 */
	List<Map<String, Object>> getApplyTeamsInfo(@Param("coachID")String coachID,@Param("index")int index,@Param("size")int size);
	/**
	 * 通过球队Num查询教练员申请的球队信息
	 * @param teamNum
	 * @return
	 */
	Map<String, Object> getApplyTeamInfo(String teamNum);
	/**
	 * 根据教练员ID和teamID判断教练员是否可以申请此球队
	 * @param coachID
	 * @param teamID
	 * @return
	 */
	String coachApplyTeamStatus(@Param("coachID")String coachID,@Param("teamID")String teamID);
	/**
	 * 根据教练员ID和teamID添加一条教练员申请记录
	 * @param coachID
	 * @param teamID
	 * @return
	 */
	int coachApplyTeam(@Param("coachID")String coachID,@Param("teamID")String teamID);
	/**
	 * @param teamID
	 * @return
	 */
	Map<String, Object>selectAidByteamID(String teamID);
	/**
	 * @param coachID
	 * @return
	 */
	Map<String, Object> selectcoachNameAndAidBycoachID(String coachID);
	
}
