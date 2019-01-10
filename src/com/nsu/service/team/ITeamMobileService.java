package com.nsu.service.team;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamMobileResultBean;

/**
 * 球队手机端Service接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-05-10 16:04:41
 */
public interface ITeamMobileService {

	/**
	 * 根据球队账号主键aid查询已经结束的赛事和它的赛程信息
	 * @author ljl
	 * @createDate 2017-05-10 16:09:54
	 * @param aid
	 * @return
	 */
	List<TeamMobileResultBean> findTeamMatchInfo(String aid);

	/**
	 * 根据球队账号主键aid查询正在进行的赛事和它的赛程信息
	 * @author ljl
	 * @createDate 2017-05-11 15:19:07
	 * @param aid
	 * @return
	 */
	List<TeamMobileResultBean> findTeamMatchIsPlayingInfo(String aid);
	
	/**
	 * 根据赛事id查询赛程信息
	 * @author ljl
	 * @createDate 2017-05-10 16:41:21
	 * @param comID
	 * @param teamID
	 * @return
	 */
	List<Map<String, Object>> findRaceDetailsInfo(@Param("comID") String comID, @Param("teamID") String teamID);

	
}
