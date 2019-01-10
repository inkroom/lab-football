package com.nsu.service.publicty;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.GameInfoBean;
import com.nsu.bean.publicity.TeamInfoBean;

/**
* @Title: IGameInfoService
* @Package com.nsu.service.publicty;
* @Description: 赛事公示IGameInfoService
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface IGameInfoService {

	/**
	 * 查询赛事信息
	 * @param pageNum
	 * @param pageSize
	 * @return PageInfo<GameInfoBean>
	 */
	public PageInfo<GameInfoBean> getGameAllService(int pageNum, int pageSize) throws Exception;
	
	/**
	 * 查询赛事信息（机构）
	 * @param pageNum
	 * @param pageSize
	 * @param id
	 * @return PageInfo<GameInfoBean>
	 */
	public PageInfo<GameInfoBean> getGameAllOrgService(int pageNum, int pageSize, long id) throws Exception;
	
	/**
	 * 根据赛事名查询赛事信息
	 * @param name
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> getGameByNameService(String name) throws Exception;
	
	/**
	 * 查询赛事详细信息
	 * @param id
	 * @return GameInfoBean
	 */
	public GameInfoBean getGameDetailService(String id) throws Exception;
	
	/**
	 * 查询参赛球队
	 * @param id
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeamService(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<GameInfoBean>
	 */
	public List<GameInfoBean> showGameService() throws Exception;
}
