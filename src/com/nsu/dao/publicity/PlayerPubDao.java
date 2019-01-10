package com.nsu.dao.publicity;

import java.util.List;
import java.util.Map;

import com.nsu.bean.publicity.PlayerPubBean;

/**
* @Title: InfoPlayerDao
* @Package com.nsu.dao.publicity;
* @Description: 球员公示InfoPlayerDao
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface PlayerPubDao {

	/**
	 * 查询球员信息
	 * @param 
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> getPlayerAll() throws Exception;
	
	/**
	 * 根据球员名查询球员信息
	 * @param name
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> getPlayerByName(String name) throws Exception;
	
	/**
	 * 查询球员详细信息
	 * @param id
	 * @return PlayerPubBean
	 */
	public PlayerPubBean getPlayerDetail(String id) throws Exception;
	
	/**
	 * 查询球员所属的球队
	 * @param id
	 * @return List<String>
	 */
	public List<String> getPlayerTeam(String id) throws Exception;
	
	/**
	 * 查询积分
	 * @param id
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getScore(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param 
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> showPlayer() throws Exception;
	
}
