package com.nsu.dao.team;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamApplyMatchBean;

/**
 * 球队申请比赛Dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-17 11:09:21
 */
public interface TeamApplyMatchDao {

	/**
	 *  查询所有赛事基本信息和球队申请了的正在进行中的赛事
	 * @author ljl
	 * @createDate 2017-04-17 16:41:40
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public List<TeamApplyMatchBean> findTeamMatchs(String teamID) throws Exception;

	TeamApplyMatchBean findOneMatch(@Param("comID") String comID) throws Exception;


	/**
	 * 根据赛事ID查询赛事报名截止时间是否已过
	 * @author ljl
	 * @createDate 2017-04-17 19:05:36
	 * @param comID
	 * @return 返回0则表示已经超过报名时间，否则报名时间还没有截止
	 * @throws Exception
	 */
	public int findMathContinue(String comID)throws Exception;

	/**
	 * 查询球队是否申请过当前赛事
	 * @author ljl
	 * @createDate 2017-04-17 19:12:38
	 * @param teamID
	 * @param comID
	 * @return 返回null则还没申请过当前赛事，否则申请过
	 * @throws Exception
	 */
	public String findTeamAlreadyApply(Map<String, Object> map)throws Exception;

	/**
	 * 根据teamID和comID更新MID_TEAM_COM表状态位为0：待审核
	 * @author ljl
	 * @createDate 2017-04-17 19:45:07
	 * @param teamID
	 * @param comID
	 * @return
	 */
	public int updateApplyMatchRecord(Map<String, Object> map);

	/**
	 * 向MID_TEAM_COM表插入一条新的球队申请记录
	 * @author ljl
	 * @createDate 2017-04-17 19:45:53
	 * @param teamID
	 * @param comID
	 * @return
	 */
	public int insertApplyMatchRecord(Map<String, Object> map);

	/**
	 * 根据赛事名和球队ID查询数据
	 * @author ljl
	 * @createDate 2017-04-19 19:44:18
	 * @param teamID 球队ID
	 * @param matchName 赛事名
	 * @return
	 */
	public List<TeamApplyMatchBean> findTeamMatchsByMatchName(@Param("teamID") String teamID, @Param("matchName") String matchName);

	/**
	 * 查询所有赛事基本信息和球队申请了的正在进行中的赛事的数量
	 * @author ljl
	 * @createDate 2017-04-21 15:05:32
	 * @param teamID
	 * @return
	 */
	public int findTeamApplyMatchTotalRecordsNum(String teamID) throws Exception;

	/**
	 * 分页查询所有赛事基本信息和球队申请了的正在进行中的赛事
	 * @author ljl
	 * @createDate 2017-04-21 15:06:38
	 * @param teamID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<TeamApplyMatchBean> findTeamApplyMatchPageRecords(@Param("teamID") Object teamID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize);


}
