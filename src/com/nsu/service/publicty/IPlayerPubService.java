package com.nsu.service.publicty;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.PlayerPubBean;

/**
* @Title: IInfoPlayerService
* @Package com.nsu.service.publicty;
* @Description: 球员公示IInfoPlayerService
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface IPlayerPubService {

	/**
	 * 查询球员信息
	 * @param 
	 * @return PageInfo<PlayerPubBean>
	 */
	public PageInfo<PlayerPubBean> getPlayerAllService(int pageNum, int pageSize) throws Exception;
	
	/**
	 * 根据球员名查询球员信息
	 * @param name
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> getPlayerByNameService(String name) throws Exception;
	
	/**
	 * 查询球员详细信息
	 * @param id
	 * @return PlayerPubBean
	 */
	public PlayerPubBean getPlayerDetailService(String id) throws Exception;
	
	/**
	 * 查询球员所属的球队
	 * @param id
	 * @return String
	 */
	public String getPlayerTeamService(String id) throws Exception;
	
	/**
	 * 查询积分
	 * @param id
	 * @return Map<String, String>
	 */
	public Map<String, String> getScoreService(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param 
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> showPlayerService() throws Exception;
}
