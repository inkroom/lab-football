package com.nsu.dao.team;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamReviewPlayerBean;

/**
 * 球队管理球申请加入球队的球员dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-20 15:46:25
 */
public interface TeamReviewPlayerDao {

	/**
	 * 根据球队主键ID查询申请加入球队的球员基本信息
	 * @author ljl
	 * @createDate 2017-04-20 15:47:31
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public List<TeamCenterDO> findApplyPlayersByTeamID(String teamID) throws Exception;

	/**
	 * 根据球员pid和球队id查询球员信息
	 * @author ljl
	 * @createDate 2017-04-20 18:35:16
	 * @param pid
	 * @param teamID
	 * @return
	 */
	public TeamReviewPlayerBean findApplyPlayersByPID(@Param("pid") String pid, @Param("teamID") String teamID) throws Exception;

	/**
	 * 更改申请队员的审核状态位
	 * @author ljl
	 * @createDate 2017-04-20 20:37:35
	 * @param pid
	 * @param operType
	 * @return
	 */
	public int updatePlayerAuditStatus(@Param("pid") String pid,@Param("operType") String operType,@Param("ptNum") String ptNum, @Param("teamID") String teamID) throws Exception;

	/**
	 * 查询当前申请加入这个球队的球员数目
	 * @author ljl
	 * @createDate 2017-04-21 09:25:53
	 * @param teamID
	 * @return
	 */
	public int findApplyJoinTeamPlayersTotalRecordsNum(String teamID) throws Exception;

	/**
	 * 根据球队主键ID查询申请加入球队的球员基本信息
	 * @author ljl
	 * @createDate 2017-04-21 09:27:52
	 * @param teamID 球队ID
	 * @param startIndex 开始页数
	 * @param pageSize 查询记录条数
	 * @return
	 */
	public List<TeamCenterDO> findApplyJoinTeamPlayersPageRecords(@Param("teamID") Object teamID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize) throws Exception;

	/**
	 * 根据球队主键ID和球员名字查询申请加入球队的球员基本信息
	 * @author ljl
	 * @createDate 2017-04-21 14:27:59
	 * @param teamID 球队ID
	 * @param playerName 球员名字
	 * @return
	 */
	public List<TeamCenterDO> findApplyJoinTeamPlayersByPlayerNamePageRecords(@Param("teamID") String teamID, @Param("playerName") String playerName) throws Exception;

	/**
	 * 根据球aid查询球员的比赛信息
	 * @author ljl
	 * @createDate 2017-04-24 10:30:43
	 * @param aid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayerInfo(@Param("A_ID") String aid) throws Exception;

	/**
	 * 根据球队ID查询球队数量
	 * @author ljl
	 * @createDate 2017-04-27 13:08:51
	 * @param teamID
	 * @return
	 */
	public int findJerseyNumberNum(@Param("teamID") String teamID, @Param("ptNum") String ptNum) throws Exception;

	/**
	 * 根据球员pid查询球员学号
	 * @author ljl
	 * @createDate 2017-05-24 16:44:36
	 * @param aid
	 * @return
	 */
	public String findPlyaerSchoolByAid(String aid) throws Exception;

	/**
	 * 根据球员aid查询球员学籍号和学校
	 * @author ljl
	 * @createDate 2017-06-05 15:20:52
	 * @param aId
	 * @return
	 */
	public Map<String, Object> findPlyaerStuIDAndSchool(String aId) throws Exception;

}
