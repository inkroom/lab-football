package com.nsu.service.team;

import java.util.List;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamApplyMatchBean;
/**
 * 球队比赛生申请管理接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-17 10:51:24
 */
public interface ITeamApplyMatchService {


	/**
	 * 球队申请比赛
	 * 对球队申请赛事是否合法进行判断已经更新球队赛事申请情况，返回处理结果
	 * @author ljl
	 * @createDate 2017-04-17 17:46:38
	 * @param teamID 球队ID
	 * @param comID 赛事ID
	 * @return
	 */
	public String updataTeamApplyMatch(String teamID, String comID,Integer teamType);

	/**
	 * 根据赛事名和球队ID查询数据
	 * @author ljl
	 * @createDate 2017-04-19 19:40:56
	 * @param teamID 球队ID
	 * @param matchName 赛事名
	 * @return
	 */
	public Page findTeamApplyMatchInfoBySeacherKey(String teamID, String matchName);

	/**
	 * 获取球队申请比赛情况以及近期可以参加的赛事
	 * 分页查询使用
	 * @author ljl
	 * @createDate 2017-04-21 14:47:50
	 * @param teamID 球队ID
	 * @param num 要看的页码，如果为null或“”，默认为1
	 * @return
	 */
	public Page findTeamApplyMatchInfoRecordsByTeamID(String teamID, String num);

}
