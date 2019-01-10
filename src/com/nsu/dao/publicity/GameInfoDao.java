package com.nsu.dao.publicity;

import java.util.List;

import com.nsu.bean.publicity.GameInfoBean;
import com.nsu.bean.publicity.TeamInfoBean;

/**
* @Title: GameInfoDao
* @Package com.nsu.dao.publicity;
* @Description: 赛事公示GameInfoDao
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface GameInfoDao {

	/**
	 * 查询赛事信息
	 * @param 
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> getGameAll() throws Exception;
	
	/**
	 * 查询赛事信息（机构）
	 * @param id
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> getGameAllOrg(long id) throws Exception;
	
	/**
	 * 根据赛事名查询赛事信息
	 * @param name
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> getGameByName(String name) throws Exception;
	
	/**
	 * 查询赛事详细信息
	 * @param id
	 * @return GameInfoBean
	 */
	public GameInfoBean getGamedetail(String id) throws Exception;
	
	/**
	 * 查询参赛球队
	 * @param id
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeam(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> showGame() throws Exception;
	
}
