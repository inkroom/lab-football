package com.nsu.dao.player;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.player.PlayerActityBean;
import com.nsu.bean.player.PlayerInfoBean;

/**
 * 
 * @Title: PlayerInfoDao.java
 * @Package com.nsu.dao.player
 * @Description: 获取球员信息
 * @author 侯松梁
 * @date 2017年4月11日 下午2:19:16
 * @version V1.0
 */
public interface PlayerInfoDao {
	/**
	 * 根据id获取球员信息
	 * 
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayerInfo(@Param("A_ID")String A_ID) throws Exception;

	/**
	 * 更新球员信息 --- player表
	 * @param playerInfoBean
	 * @throws Exception
	 */
	public void updatePlayerInfo(PlayerInfoBean playerInfoBean) throws Exception;

	/**
	 * 更新球员信息 --- account表
	 * @param playerInfoBean
	 * @throws Exception
	 */
	public void updatePlayerInfoName(PlayerInfoBean playerInfoBean) throws Exception;

	/**
	 * 得到球员参加活动记录
	 * @param A_ID
	 * @param COM_BIG_LEVEL
	 * @return
	 * @throws Exception
	 */
	public List<PlayerActityBean> getPlayerActivityInfo(@Param("A_ID")String A_ID,@Param("COM_BIG_LEVEL")String COM_BIG_LEVEL) throws Exception;

	/**
	 * 
	 * @Description (更新球员的头像信息)
	 * @param filePath
	 * @throws Exception
	 */
	public int updatePlayerPhoto(@Param("a_id") String a_id, @Param("filePath") String filePath) throws Exception;

	/**
	 * @Description (更新球员精彩图片)
	 * @param wonder
	 * @return
	 * @throws Exception
	 */
	public int insertWonderfulPhoto(@Param("wonder")Map<String, Object>wonder) throws Exception;
	
	/**
	 * 根据id获取球员精彩图片
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getWdpic(@Param("A_ID")String A_ID) throws Exception;
	
	/**
	 * 根据id获取球队信息
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamInfo(@Param("A_ID")String A_ID) throws Exception;
	
	/**
	 * 根据身份证获取球员学籍号
	 * @param STU_ID_CARD
	 * @return
	 * @throws Exception
	 */
	public String getStuNum(@Param("STU_ID_CARD")String STU_ID_CARD) throws Exception;
	
	/**
	 * 根据身份证获取球员学籍信息
	 * @param STU_ID_CARD
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStudentInfo(@Param("STU_ID_CARD")String STU_ID_CARD) throws Exception;

	
}
