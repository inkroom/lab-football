package com.nsu.service.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CoachMobileService {
	/**
	 * 通过A_ID查询教练员的校级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachSchoolMatch(String A_ID);
	/**
	 * 通过A_ID查询教练员的县级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachCountyMatch(String A_ID);
	/**
	 * 通过A_ID查询教练员的市级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachCityMatch(String A_ID);
	/**
	 * 通过A_ID查询教练员的省级比赛
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachProvinceMatch(String A_ID);
	/**
	 * 查询教练员其他没有级别比赛的数据
	 * @param A_ID
	 * @return
	 */
	List<Map<String, Object>> getCoachOtherMatch(String A_ID);
	/**
	 * @param a_ID
	 * @return 
	 */
	Map<String, Object> getCoachID(String a_ID);
	/**
	 * 
	 * @param team_id
	 * @return
	 */
	List<String>getPushInfo(String team_id);

}
