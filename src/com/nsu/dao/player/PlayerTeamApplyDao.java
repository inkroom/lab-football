package com.nsu.dao.player;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.player.PlayerTeamBean;

/**
 * @ClassName PlayerTeamApplyDao
 * @Description TODO(球员申请球队)
 * @author hm
 * @Date 2017年4月17日 下午8:44:36
 * @version 1.0.0
 */
public interface PlayerTeamApplyDao {
	/**
	 * 
	 * @Description (获取球队信息)
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public PlayerTeamBean getTeamInfo(@Param("team_num") String team_num) throws Exception;

	/**
	 * 
	 * @Description (获取我的球队申请列表)
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public List<PlayerTeamBean> getTeamApplyList(@Param("a_id") String a_id) throws Exception;

	/**
	 * 
	 * @Description (获取球队积分信息)
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTeamScoreInfo(@Param("team_id") String team_id) throws Exception;

	/**
	 * 
	 * @Description (向球队发起申请)
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public int insertMidPlayerTeam(@Param("team") Map<String, Object> team) throws Exception;

	/**
	 * 
	 * @Description (获取球员ID)
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public String getP_id(@Param("a_id") String a_id) throws Exception;

	/**
	 * 
	 * @Description (判断球队是否已经申请过)
	 * @param team_id
	 * @param p_id
	 * @return
	 * @throws Exception
	 */
	public int isApplied(@Param("mid") Map<String, Object> mid) throws Exception;

	/**
	 * 
	 * @Description (判断是否允许申请该级别球队)
	 * @param team_id
	 * @param a_id
	 * @return true允许申请，false不允许申请
	 */
	public int allowApply(@Param("mid") Map<String, Object> mid) throws Exception;

	/**
	 * 
	 * @Description (获取球队等级)
	 * @param team_id
	 * @param a_id
	 * @return true允许申请，false不允许申请
	 */
	public int getTeamRank(@Param("mid") Map<String, Object> mid) throws Exception;

	/**
	 * 获取球队a_id
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public String getTeamAId(@Param("team_id")String team_id) throws Exception;
}
