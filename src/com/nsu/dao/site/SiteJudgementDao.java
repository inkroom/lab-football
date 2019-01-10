package com.nsu.dao.site;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 裁判书
 * @author 刘俊
 *
 */
public interface SiteJudgementDao {

	
	
	/**
	 * 通过A_ID查询当场比赛的一些信息
	 * 
	 * @param A_ID
	 * @return
	 */
	Map<String, Object> selectInfo(@Param("A_ID") long A_ID);

	/**
	 * 通过球队ID得到球队名称
	 * 
	 * @param T_ID
	 * @return
	 */
	String selectNameById(@Param("T_ID") int T_ID);

	 /**
	  * 插入上场球员信息
	  * @param R_ID
	  * @param playerName
	  * @param playerNum
	  * @param playerTeam
	  * @param playerPlace
	  */
	void insertPlayer(@Param("R_ID") long R_ID,
			@Param("playerName") String playerName,
			@Param("playerNum") int playerNum,
			@Param("playerTeam") int playerTeam,
			@Param("playerPlace") String playerPlace
			);

	/**
	 * 插入替补表
	 * @param R_ID
	 * @param playerInName
	 * @param playerInNum
	 * @param playerOutName
	 * @param playerOutNum
	 * @param playerTeam
	 * @param time
	 */
	void insertAlternate(@Param("R_ID") long R_ID,
			@Param("playerInName") String playerInName,
			@Param("playerInNum") int playerInNum,
			@Param("playerOutName") String playerOutName,
			@Param("playerOutNum") int playerOutNum,
			@Param("playerTeam") int playerTeam,
			@Param("time") String time
			);

	/**
	 * 插入警告标
	 * @param R_ID
	 * @param teamName
	 * @param playerNum
	 * @param playerName
	 * @param time
	 * @param reason
	 */
	void insertWran(@Param("R_ID") long R_ID,
			@Param("teamName") int teamName,
			@Param("playerNum") int playerNum,
			@Param("playerName") String playerName,
			@Param("time") String time,
			@Param("reason") String reason
			);

	/**
	 * 插入罚令出场表
	 * @param R_ID
	 * @param teamName
	 * @param playerNum
	 * @param playerName
	 * @param time
	 * @param reason
	 */
	void insertOut(@Param("R_ID") long R_ID,
			@Param("teamName") int teamName,
			@Param("playerNum") int playerNum,
			@Param("playerName") String playerName,
			@Param("time") String time,
			@Param("reason") String reason);

	void insertInBall(@Param("R_ID") long R_ID,
					  @Param("teamNum") int teamNum,
					  @Param("playerNum") int playerNum,
					  @Param("playerName") String playerName,
					  @Param("playerID") long playerID,
					  @Param("time") String time);
	
	/**
	 * 重复提交裁判书时删除四个表的重复信息
	 * @param R_ID
	 */
	void deletePlayer(@Param("R_ID") long R_ID);
	void deleteAlter(@Param("R_ID") long R_ID);
	void deleteWran(@Param("R_ID") long R_ID);
	void deleteOut(@Param("R_ID") long R_ID);
	void deleteInBall(@Param("R_ID") long R_ID);
	/**
	 * 查询裁判书信息
	 * @param R_ID
	 * @param teamNum
	 * @return
	 */
	List<Map<String, Object>> selectPlayerList(@Param("R_ID") long R_ID,@Param("teamNum") int teamNum);
	List<Map<String, Object>> selectAlternateList(@Param("R_ID") long R_ID,@Param("teamNum") int teamNum);
	List<Map<String, Object>> selectWranList(@Param("R_ID") long R_ID);
	List<Map<String, Object>> selectOutList(@Param("R_ID") long R_ID);
	List<Map<String, Object>> selectDetails(@Param("R_ID") long R_ID);
	List<Map<String, Object>> selectInBall(@Param("R_ID") long R_ID,@Param("teamNum") int teamNum);

	/**
	 * 更新胜利场次及失败场次
	 * @param T_ID
	 * @param item
	 */
	void  updateTeamInfo(@Param("T_ID") long T_ID,@Param("item") String item);
	void updateCoachInfo(@Param("coachID") long coachID,@Param("item") String item);
	void updatePlayerInfo(@Param("playerID") long playerID,@Param("item") String item);
	/**
	 * 提交裁判书时更新details表中的数据
	 * @param R_ID
	 * @param fileNum
	 * @param gamePlace
	 * @param HTeamregular
	 * @param VTeamregular
	 * @param HTeamExtra
	 * @param VTeamExtra
	 * @param HTeamPenalty
	 * @param VTeamPenalty
	 * @param winStatus
	 * @param winTeam
	 * @param redText
	 * @param wranText
	 * @param errorText
	 * @param refPhone
	 * @param refName
	 */
	void updateDetails(
			@Param("R_ID") long R_ID,
			@Param("fileNum") int fileNum,
			@Param("gamePlace") String gamePlace,
			@Param("HTeamregular") int HTeamregular,
			@Param("VTeamregular") int VTeamregular,
			@Param("HTeamExtra") int HTeamExtra,
			@Param("VTeamExtra") int VTeamExtra,
			@Param("HTeamPenalty") int HTeamPenalty,
			@Param("VTeamPenalty") int VTeamPenalty,
			@Param("winStatus") int winStatus,
			@Param("winTeam") String winTeam,
			@Param("redText") String redText,
			@Param("wranText") String wranText,
			@Param("errorText") String errorText,
			@Param("refPhone") String refPhone,
			@Param("refName") String refName
			);


	/**
	 * 查询比赛两队教练以及上场比赛的球员id
	 * @param T_ID
	 * @return
	 */
	List<Long> selectCoachs(@Param("T_ID") long T_ID);
	List<Long> selectPlayers(@Param("R_ID") long R_ID,@Param("T_ID") long T_ID);
	/**
	 * 查询本场比赛的比赛级别
	 * @param R_ID
	 * @return
	 */
	int selectLevel(@Param("R_ID") long R_ID);
	/**
	 * 查询上场人员的姓名
	 * @param playerId
	 * @return
	 */
	String selectPlayerNameByID(@Param("playerId")long playerId);
	/**
	 * 查询上场球员的ID
	 * @param T_ID
	 * @param number
	 * @return
	 */
	Long selectPlayerId(@Param("T_ID") long T_ID,@Param("number") int number);

	/**
	 * 查询主队ID,客队ID,上场人员
	 * @param R_ID
	 * @return
	 */
	int selectHTeamId(@Param("R_ID") long R_ID);
	int selectVTeamId(@Param("R_ID") long R_ID);
	int selectGamePlayers(@Param("T_ID") long T_ID,@Param("P_ID") long P_ID);
}
