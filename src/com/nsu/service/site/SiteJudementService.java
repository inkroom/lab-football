package com.nsu.service.site;

import java.util.List;
import java.util.Map;


/**
 * 裁判书
 * @author 刘俊
 *
 */
public interface SiteJudementService {
	
	
	
	/**
	 * 查询比赛基本信息
	 * @param A_ID
	 * @return
	 */
	Map<String, Object> selectInfo(int A_ID)throws  Exception;
	
	/**
	 * 查询球队名称
	 * @param T_ID
	 * @return
	 */
	String selectTeamName(int T_ID)throws  Exception;
	
	/**
	 * 将前台传入的数组存入数据库
	 * @param HPlayer 参赛主队成员信息
	 * @param VPlayer 参赛客队成员信息
	 * @param HAlternate 主队替补成员信息
	 * @param VAlternate 客队替补成员信息
	 * @param Wran 警告成员信息
	 * @param Out  判罚出局成员信息
	 */
	void insertInfo(
			String[] parmas,
			String[] HPlayer,
			String[] VPlayer,
			String[] HAlternate,
			String[] VAlternate,
			String[] InHPlayer,
			String[] InVPlayer,
			String[] Wran,
			String[] Out,
			long R_ID,
			int A_ID
			)throws  Exception;
	
	/**
	 * 将数据查询出来
	 * 下同
	 * @param RID
	 * @param teamNum
	 * @return
	 */
	List<Map<String, Object>> selectPlayerList(long RID,int teamNum)throws  Exception;
	List<Map<String, Object>> selectAlternateList(long RID,int teamNum)throws  Exception;
	List<Map<String, Object>> selectWranList(long RID)throws  Exception;
	List<Map<String, Object>> selectOutList(long RID)throws  Exception;
	List<Map<String, Object>> selectDetails(long RID)throws  Exception;
	List<Map<String, Object>> selectInBall(long RID,int teamNum) throws Exception;
	/**
	 * 将主队上场队员和客队上场队员合并为一个list<Map<String,Object>> 
	 * 便于传入前台数据回显
	 * @param HTeamplayerList
	 * @param VTeamPlayerList
	 * @return
	 */
	List<Map<String, Object>> AllPlayerList(
			List<Map<String, Object>> HTeamplayerList,
			List<Map<String, Object>> VTeamPlayerList)throws  Exception;
	
	/**
	 * 将主队替补队员和客队替补队员合并为一个list<Map<String,Object>> 
	 * 便于传入前台数据回显
	 * @param HTeamAlternateList
	 * @param VTeamAlternateList
	 * @return
	 */
	List<Map<String, Object>> AllAlternateList(
			List<Map<String, Object>> HTeamAlternateList,
			List<Map<String, Object>> VTeamAlternateList)throws  Exception;
	List<Map<String,Object>> AllInBallList(
			List<Map<String, Object>> InBallHTeam,
			List<Map<String, Object>> InBallVTeam)throws Exception;

	/**
	 * 得到上场所有运动员
	 * 得到上场球队所有教练
	 * @param RID
	 * @param TID
	 * @return
	 * @throws Exception
	 */
	List<String> AllPlayers(long RID,int TID)throws  Exception;
	List<Integer> AllPlayersNum(long RID, long TID)throws Exception;
}
