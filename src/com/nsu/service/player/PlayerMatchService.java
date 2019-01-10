package com.nsu.service.player;

import java.util.List;
import java.util.Map;

import com.nsu.bean.player.MobilePlayerMatchBean;
/**
 * @ClassName PlayerMatchService
 * @author hm
 * @Date 2017年4月17日 下午3:33:57
 * @version 1.0.0
 */
public interface PlayerMatchService {
	/**
	 * 
	 * @Description (获取球队ID) A_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTeamID(String A_ID) throws Exception;

	/**
	 * 
	 * @Description (获取队伍名) TEAM_ID,TeamName
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamNameAndId(String a_id) throws Exception;

	/**
	 * 获取赛事ID
	 * 
	 * @Description (获取赛事ID) TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMatchID(String TEAM_ID) throws Exception;

	/**
	 * 
	 * @Description (获取赛事列表) TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMatchList(String TEAM_ID) throws Exception;

	/**
	 * 根据球队id获取赛事名称
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMatchName(String TEAM_ID) throws Exception;

	/**
	 * 根据赛事id得到赛程信息
	 * @param COM_ID
	 * @return
	 * @throws Exception
	 */
	public List<MobilePlayerMatchBean> getSchedule(String COM_ID, String TEAM_ID) throws Exception;
	/**
	 * 通过赛事ID查询比赛完成的结果
	 * @param raceID
	 * @return
	 */
	public Map<String, Object> getRaceResultsDetails(String raceID);
}
