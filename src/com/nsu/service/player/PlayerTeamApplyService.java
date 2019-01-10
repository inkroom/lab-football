package com.nsu.service.player;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.player.PlayerTeamBean;
/**
 * @ClassName PlayerTeamApplyService
 * @Description (球队申请球队Service接口)
 * @author hm
 * @Date 2017年4月17日 下午9:25:25
 * @version 1.0.0
 */
public interface PlayerTeamApplyService {
	/**
	 * 获取球队信息
	 * @param team_num
	 * @return
	 * @throws Exception
	 */
	public PlayerTeamBean getTeamInfo(String team_num) throws Exception;

	/**
	 * 获取我的球队申请列表
	 * @param a_id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageInfo<PlayerTeamBean> getTeamApplyList(String a_id, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 向球队发起申请
	 * @param a_id
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public boolean applyTeam(String a_id, String team_id) throws Exception;

	/**
	 * 
	 * @Description (判断球队是否已经申请过)
	 * @param team_id
	 * @param a_id
	 * @return true表示已经申请过，false表示未被申请过
	 * @throws Exception
	 */
	public boolean isApplied(String team_id, String a_id) throws Exception;

	/**
	 * 
	 * @Description (判断是否允许申请该级别球队)
	 * @param team_id
	 * @param a_id
	 * @return true允许申请，false不允许申请
	 */
	public boolean allowApply(String team_id, String a_id) throws Exception;

	/**
	 * 获取球队a_id
	 * @param team_id
	 * @return
	 * @throws Exception
	 */
	public String getTeamAId(String team_id) throws Exception;
}
