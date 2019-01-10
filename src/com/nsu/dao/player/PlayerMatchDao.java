package com.nsu.dao.player;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.player.MobilePlayerMatchBean;
import com.nsu.bean.player.PlayerMatchBean;

/**
 * @ClassName PlayerMatchDao
 * @Description (球员比赛dao)
 * @author hm
 * @Date 2017年4月17日 下午2:26:10
 * @version 1.0.0
 */
public interface PlayerMatchDao {
	/**
	 * 
	 * @Description (获取球队ID)
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<String> getTeamID(@Param("A_ID") String A_ID) throws Exception;

	/**
	 * 
	 * @Description (获取队伍名)
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public String getTeamName(@Param("TEAM_ID") String TEAM_ID) throws Exception;

	/**
	 * 获取赛事ID
	 * 
	 * @Description (获取赛事ID)
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<String> getMatchID(@Param("TEAM_ID") String TEAM_ID) throws Exception;

	/**
	 * 
	 * @Description (获取赛事列表)
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<PlayerMatchBean> getMatchList(@Param("TEAM_ID") String TEAM_ID) throws Exception;

	/**
	 * 获取赛事名称
	 * 
	 * @param TEAM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMatchName(@Param("TEAM_ID") String TEAM_ID) throws Exception;

	/**
	 * 获取赛程信息
	 * 
	 * @param COM_ID
	 * @return
	 * @throws Exception
	 */
	public List<MobilePlayerMatchBean> getSchedule(@Param("COM_ID") String COM_ID, @Param("TEAM_ID") String TEAM_ID) throws Exception;

	/**
	 * 比赛详情的第一个表有人数主客队和比分
	 * 
	 * @param raceID
	 * @return
	 */
	public Map<String, Object> getRaceScore(String raceID);

	/**
	 * 警告人员名单
	 * 
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRaceWarning(String raceID);

	/**
	 * 罚出人员名单
	 * 
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRaceSendOff(String raceID);

	/**
	 * 红牌，罚球，错判情况
	 * 
	 * @param raceID
	 * @return
	 */
	public List<Map<String, Object>> getRacePunish(String raceID);

	/**
	 * 获取赛事名称,结束时间
	 * @param com_id
	 * @return
	 */
	public Map<String, String> getRaceNameAndEndTime(int com_id);
}
