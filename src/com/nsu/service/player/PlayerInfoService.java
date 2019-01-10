package com.nsu.service.player;

import java.util.List;
import java.util.Map;


import com.nsu.bean.player.PlayerActityBean;
import com.nsu.bean.player.PlayerInfoBean;

/**
 * 
 * @Title: PlayerInfoService.java
 * @Package com.nsu.service.player
 * @Description: 球员信息
 * @author 侯松梁
 * @date 2017年4月11日 下午2:25:31
 * @version V1.0
 */
public interface PlayerInfoService {

	/**
	 * 根据id获取球员信息
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayerInfo(String id) throws Exception;

	/**
	 * 更新球员信息
	 * @param playerInfoBean
	 * @throws Exception
	 */
	public void updatePlayerInfo(PlayerInfoBean playerInfoBean) throws Exception;

	/**
	 * 得到球员参加活动记录
	 * @param A_ID
	 * @param COM_BIG_LEVEL
	 * @return
	 * @throws Exception
	 */
	public List<PlayerActityBean> getPlayerActivityInfo(String A_ID, String COM_BIG_LEVEL) throws Exception;

	/**
	 * @Description (更新球员的头像信息)
	 * @param filePath
	 * @throws Exception
	 */
	public boolean updatePhoto(String a_id, String filePath) throws Exception;

	/**
	 * (更新球员精彩图片)
	 * @param a_id
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public boolean updateWonderfulPhoto(String a_id, String filePath) throws Exception;

	/**
	 * 根据id获取球员精彩图片
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getWdpic(String A_ID) throws Exception;

	/**
	 * 根据id获取球队信息
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTeamInfo(String A_ID) throws Exception;

	/**
	 * 根据身份证获取球员学籍号
	 * @param STU_ID_CARD
	 * @return
	 * @throws Exception
	 */
	public String getStuNum(String STU_ID_CARD) throws Exception;

	/**
	 * 根据身份证获取球员学籍信息
	 * @param STU_ID_CARD
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStudentInfo(String STU_ID_CARD) throws Exception;

}
