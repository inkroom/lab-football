package com.nsu.service.team;

import java.util.List;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.ScheduleBean;
/**
 * 球队根据赛程安排上场球员
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-18 10:24:54
 */
public interface ITeamScheduleArrangeService {

	/**
	 * 
	 * 查询总赛程
	 * @author ljl
	 * @createDate 2017-04-18 18:42:31
	 * @param comID 赛事ID
	 * @param teamID 球队ID
	 * @return
	 */
	public int findSchedulesNum(String comID, String teamID);

	/**
	 * 获取已经挑选参赛的队员
	 * @author ljl
	 * @createDate 2017-04-18 19:08:07
	 * @param teamID 球队ID
	 * @param rID 赛程ID
	 * @return 返回所有已经参赛的球员的P_ID的拼接
	 */
	public String findAddMatchPlayersID(String teamID, String rID);

	/**
	 * 安排球员
	 * @author ljl
	 * @createDate 2017-04-19 09:01:26
	 * @param teamID 球队ID
	 * @param pid 球员ID
	 * @param rID 赛程ID
	 * @param type 0添加 1移除
	 * @return
	 */
	public String updateArrangePlayers(String teamID, String pid, String rID, String type);

	/**
	 * 根据赛事ID和球队ID查询赛程信息
	 * 分页使用
	 * @author ljl
	 * @createDate 2017-04-24 16:55:15
	 * @param comID 赛事ID
	 * @param teamID 球队ID
	 * @param num 要看的页码，如果为null或“”，默认为1
	 * @return
	 */
	public Page findSchedulesByComIDRecords(String comID, String teamID, String num);
}
