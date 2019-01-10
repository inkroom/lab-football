package com.nsu.service.site;

import java.util.Map;

public interface SiteLiveService {

	/**
	 * 
	 * @param Hteam_score  主队得分
	 * @param Vteam_score  客队得分
	 * @param dateString        当前时间
	 * @param LiveText    直播文本信息
	 * @param LiveImg      图片url
	 * @param infoType 文本类型 1.普通信息    2.判罚信息     3.进球信息
	 * @param rID          比赛id
	 */
	void insertLiveInfo(
			int Hteam_score,
			int Vteam_score,
			String dateString,
			String LiveText,
			String LiveImg,
			int infoType,
			long rID)throws  Exception;
	
	/**
	 * 查询两个队伍基本信息
	 * @param A_ID
	 * @return
	 */
	Map<String, Object> selectInfoBeforeGame(int A_ID)throws  Exception;
	
	/**
	 * 查询比赛对应id R_Id
	 * @param A_ID
	 * @return
	 */
	long selectRID(int A_ID)throws  Exception;
	
	/**
	 * 提交一次后进入页面刷新时显示当前比赛的比分信息
	 * @param A_ID
	 * @return
	 */
	Map<String, Integer> selectSocreNow(int A_ID)throws  Exception;
	
	/**
	 * 查询参赛队伍名称
	 * @param tId
	 * @return
	 */
	String selectTeamNames(int tId)throws  Exception;
	
	
	String selectGameName(long RID) throws Exception;
	/**
	 * 结束比赛 ，填写结束时间，改变结束状态为2
	 * @param RID
	 */
	void endGame(long RID)throws  Exception;
	/**
	 * 查询比赛ID SE_ID
	 * @param AID
	 * @return
	 * @throws Exception
	 */
	long slectSE(int AID)throws  Exception;
	/**
	 * 结束比赛时置状态
	 * @param time
	 * @param SEID
	 * @throws Exception
	 */
	void endStatus(String time,long SEID)throws  Exception;
	/**
	 * 结束比赛时间
	 * @param time
	 * @param RID
	 * @throws Exception
	 */
	void endTime(String time,String win,long RID)throws  Exception;
	/**
	 * 查询状态，看比赛是否可用
	 * @param RID
	 * @return
	 * @throws Exception
	 */
	int selectStatus(long RID)throws  Exception;
	/**
	 * 更新比赛详情表的状态
	 * @param RID
	 * @param status
	 * @throws Exception
	 */
	void updateDetailStatus(long RID,int status)throws  Exception;
	/**
	 * 更新比赛的队伍，教练，球员分数记录
	 * @param winTeamNum
	 * @param R_ID
	 * @param A_ID
	 * @throws Exception
	 */
	void updateScore(int winTeamNum,long R_ID, int A_ID) throws  Exception;
	/**
	 * 查询队伍logo
	 * @param TID
	 * @return
	 * @throws Exception
	 */
	String selectLogo(int TID)throws  Exception;
	/**
	 * 设置比赛的状态
	 */
	void setStatus(String status,long R_ID);

	void setA_Status(long A_ID);
}
