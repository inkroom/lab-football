package com.nsu.service.publicty;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.bean.publicity.TeamInfoBean;

/**
* @Title: ITeamInfoService
* @Package com.nsu.service.publicty;
* @Description: 球队公示ITeamInfoService
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface ITeamInfoService {

	/**
	 * 查询球队信息
	 * @param pageNum
	 * @param pageSize
	 * @return PageInfo<TeamInfoBean>
	 */
	public PageInfo<TeamInfoBean> getTeamAllService(int pageNum, int pageSize) throws Exception;
	
	/**
	 * 查询球队信息（机构）
	 * @param pageNum
	 * @param pageSize
	 * @param id
	 * @return PageInfo<TeamInfoBean>
	 */
	public PageInfo<TeamInfoBean> getTeamAllOrgService(int pageNum, int pageSize, long id) throws Exception;
	
	/**
	 * 根据队名查询球队信息
	 * @param name
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeamByNameService(String name) throws Exception;
	
	/**
	 * 查询球队详细信息
	 * @param id
	 * @return TeamInfoBean
	 */
	public TeamInfoBean getTeamDetailService(String id) throws Exception;
	
	/**
	 * 查询教练员
	 * @param id
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> getCoachService(String id) throws Exception;
	
	/**
	 * 查询球员
	 * @param id
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> getPlayerService(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> showTeamService() throws Exception;
}
