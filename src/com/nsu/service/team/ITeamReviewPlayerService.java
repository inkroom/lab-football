package com.nsu.service.team;

import java.util.List;
import java.util.Set;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamReviewPlayerBean;

/**
 * 球队审核申请球员service 接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-20 15:38:39
 */
public interface ITeamReviewPlayerService {

	/**
	 * 查询所有申请加入当前球队的球员基本信息
	 * @author ljl
	 * @createDate 2017-04-20 15:40:08
	 * @param teamID 球队ID
	 * @return
	 */
	public List<TeamCenterDO> findApplyPlayersByTeamID(String teamID);

	/**
	 * 根据球员PID查询球员的所有基本信息
	 * @author ljl
	 * @createDate 2017-04-20 18:32:32
	 * @param pid
	 * @param teamID
	 * @return
	 */
	public TeamReviewPlayerBean findApplyPlayersByPID(String pid, String teamID);

	/**
	 * 更改申请队员的审核状态位
	 * @author ljl
	 * @createDate 2017-04-20 20:39:51
	 * @param pid
	 * @param operType
	 * @param teamID
	 * @return
	 */
	public String updatePlayerAuditStatus(String pid, String operType, String ptNum, String teamID);

	/**
	 * 查询所有申请加入当前球队的球员基本信息
	 * 根据用户要查看的页码，返回封装了所有与分页有关的Page对象
	 * @author ljl
	 * @createDate 2017-04-21 09:18:29
	 * @param teamID 球队ID
	 * @param num 要看的页码，如果为null或“”，默认为1
	 * @return
	 */
	public Page findApplyPlayersRecordsByTeamID(String teamID, String num);

	/**
	 * 根据球员名字和球队ID查询球员信息
	 * @author ljl
	 * @createDate 2017-04-21 14:23:19
	 * @param teamID
	 * @param seacherKeyWord
	 * @return
	 */
	public Page findApplyPlayersRecordsByPlayerName(String teamID, String playerName);

	/**
	 * 验证球衣号是否可用
	 * @author ljl
	 * @createDate 2017-04-27 13:04:35
	 * @param ptNum 球衣号
	 * @param teamID 球队ID
	 * @return 可用返回true，否则返回false
	 */
	public boolean vaildataPlayerJerseyNumberIsOnly(String ptNum, String teamID);

	
}
