package com.nsu.dao.site;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface SiteLiveDao {
	

	/**
	 * 查询当前两队的比分
	 * @param A_ID
	 */
	Map<String, Integer> selectScores(@Param("A_ID")long A_ID);
	
	/**
	 * 查询比赛id
	 * @param A_ID
	 * @return
	 */
	Long selectRID(@Param("A_ID")long A_ID);
	
	String selectGameName(@Param("R_ID") long R_ID);
	/**
	 * 正在直播时在details表显示正在直播
	 * @param R_ID
	 */
	void updateDetailsStatus(@Param("R_ID")long R_ID,
							 @Param("status")int status);

	
	/**
	 * 插入直播信息
	 * @param RL_H_T_SCORE 主队得分
	 * @param RL_V_T_SCORE 客队得分
	 * @param RL_TIME    当前时间
	 * @param RL_TEXT    直播文本信息
	 * @param RL_IMAGES  图片url
	 * @param RL_ST      文本类型 1.普通信息    2.判罚信息     3.进球信息
	 * @param R_ID       比赛id
	 */
	void insertLiveInfo(
			@Param("RL_H_T_SCORE")int RL_H_T_SCORE,
			@Param("RL_V_T_SCORE")int RL_V_T_SCORE,
			@Param("RL_TIME")String RL_TIME,
			@Param("RL_TEXT")String RL_TEXT,
			@Param("RL_IMAGES")String RL_IMAGES,
			@Param("RL_ST")int RL_ST,
			@Param("R_ID")long R_ID
			);
	
	/**
	 * 查询两队赛前信息及开始比赛时间
	 * @param A_ID
	 */
	Map<String, Object> selectAllBeforeGame(@Param("A_ID")int A_ID);
	/**
	 * 查询队伍名称
	 * @param T_ID
	 * @return
	 */
	String selectTeamName(@Param("T_ID")int T_ID);
	/**
	 * 查询队伍logo
	 * @param T_ID
	 * @return
	 */
	String selectTeamLogo(@Param("T_ID")int T_ID);
	/**
	 * 结束比赛
	 * @param R_ID
	 */
	void endGame(@Param("R_ID")long R_ID);
	void endTime(@Param("time")String time,@Param("win") String win,@Param("R_ID")long R_ID);
	/**
	 * 查询比赛ID SE_ID
	 * @param A_ID
	 * @return
	 */
	Long selectSE(@Param("A_ID")int A_ID);
	/**
	 * 结束比赛时更改状态位
	 * @param SE_ID
	 * @param time
	 */
	void endStatus(@Param("SE_ID")long SE_ID,@Param("time") String time);
	/**
	 * 查询比赛是否可用
	 * @param R_ID
	 * @return
	 */
	int selectStatus(@Param("R_ID")long R_ID);

	void setStatus(@Param("status")String status,@Param("R_ID") long R_ID);

	void setA_Status(@Param("A_ID") long A_ID);
}
