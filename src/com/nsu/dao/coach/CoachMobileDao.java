package com.nsu.dao.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CoachMobileDao {
	/**
	 * 通过A_ID查询教练员的校级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachSchoolMatch(@Param("A_ID")String A_ID);
	/**
	 * 通过A_ID查询教练员的县级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachCountyMatch(@Param("A_ID")String A_ID);
	/**
	 * 通过A_ID查询教练员的市级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachCityMatch(@Param("A_ID")String A_ID);
	/**
	 * 通过A_ID查询教练员的省级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachProvinceMatch(@Param("A_ID")String A_ID);
	/**
	 * 查询教练员其他没有级别比赛的数据
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachOtherMatch(@Param("A_ID")String A_ID); 
	/**
	 * 
	 * @param A_ID
	 * @param level
	 * @return
	 */
	List<Map<String, Object>> getCoachCOM_ID(@Param("A_ID")String A_ID,@Param("COM_LEVEL")String level);
	/**
	 * @param a_ID
	 * @return
	 */
	Map<String, Object> getCoachID(String a_ID);
	/**
	 * @param team_id 
	 * @return 
	 * 
	 */
	List<String> getTeamPushInfo(String team_id);
	
}
