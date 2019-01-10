package com.nsu.dao.team;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.ScheduleBean;

/**
 * 球队根据赛程安排上场球员dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-18 10:29:39
 */
public interface TeamScheduleArrangeDao {

	/**
	 * 根据赛事ID和球队ID查询赛程信息
	 * @author ljl
	 * @createDate 2017-04-18 10:32:37
	 * @param comID 赛事ID
	 * @param teamID 球队ID
	 * @return
	 */
	public List<ScheduleBean> findSchedulesByComID(@Param("comID") String comID, @Param("teamID") String teamID) throws Exception;

	/**
	 * 根据赛事ID和球队ID查询赛程数量
	 * @author ljl
	 * @createDate 2017-04-18 18:44:58
	 * @param comID 赛事ID
	 * @param teamID 球队ID
	 * @return
	 * @throws Exception
	 */
	public int findSchedulesNum(@Param("comID") String comID, @Param("teamID") String teamID) throws Exception;

	/**
	 * 获取已经挑选参赛的队员
	 * @author ljl
	 * @createDate 2017-04-18 19:13:09
	 * @param teamID 球队ID
	 * @param rID 赛程ID
	 * @return
	 */
	public List<String> findPlanMatchPlayers(@Param("teamID") String teamID, @Param("rID") String rID) throws Exception;

	/**
	 * 更新球队队员在MID_TEAM_RACE 表的状态值
	 * @author ljl
	 * @createDate 2017-04-19 09:23:48
	 * @param teamID 球队ID
	 * @param pid 球员ID
	 * @param rID 赛程ID
	 * @param checkStatus 状态位
	 * @return
	 */
	public int updateMatchPlayersStatus(@Param("teamID") String teamID, @Param("pid") String pid, @Param("rID") String rID, @Param("checkStatus") String checkStatus) throws Exception;

	/**
	 * 根据P_ID查询MID_TEAM_RACE 表是否存在记录
	 * @author ljl
	 * @createDate 2017-04-19 09:33:37
	 * @param teamID 球队ID
	 * @param pid 球员ID
	 * @param rID 赛程ID
	 * @return
	 */
	public int findCountRecordMatchPlayers(@Param("teamID") String teamID, @Param("pid") String pid, @Param("rID") String rID) throws Exception;

	/**
	 * 向MID_TEAM_RACE 表插入数据
	 * @author ljl
	 * @createDate 2017-04-19 09:47:26
	 * @param teamID 球队ID
	 * @param pid 球员ID
	 * @param rID 赛程ID
	 * @return
	 */
	public int insertMatchPlayers(@Param("teamID") String teamID, @Param("pid") String pid, @Param("rID") String rID) throws Exception;
	
	/**
	 * @Description: 查询本队赛程信息
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:22:43
	 * @return
	 */
	public List<ScheduleBean> MatchRecordInfo(@Param("TEAM_ID") String TEAM_ID,@Param("COM_ID") String COM_ID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize)throws Exception;

	/**
	 * 分页查询赛程信息
	 * @author ljl
	 * @createDate 2017-04-24 17:01:29
	 * @param teamID 球队ID
	 * @param comID 赛事ID
	 * @param startIndex 开始
	 * @param pageSize 查询条数
	 * @return
	 */
	public List<ScheduleBean> findSchedulesByComIDPageRecords(@Param("teamID") Object teamID, @Param("comID") Object comID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize) throws Exception;

	/**
	 * 根据teamID和赛程ID查询球队安排了多少人上场
	 * @author ljl
	 * @createDate 2017-04-24 18:35:36
	 * @param teamID 赛事ID
	 * @param rID 赛程ID
	 * @return
	 */
	public int findCountArrangedNum(@Param("teamID") String teamID, @Param("rID") String rID) throws Exception;
	
}
