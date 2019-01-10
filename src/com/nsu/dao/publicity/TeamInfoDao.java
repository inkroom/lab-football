package com.nsu.dao.publicity;

import java.util.List;

import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.bean.publicity.TeamInfoBean;

/**
* @Title: TeamInfoDao
* @Package com.nsu.dao.publicity;
* @Description: 球队公示TeamInfoDao
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface TeamInfoDao {

	/**
	 * 查询球队信息
	 * @param 
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeamAll() throws Exception;
	
	/**
	 * 查询球队信息（机构）
	 * @param id
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeamAllOrg(long id) throws Exception;
	
	/**
	 * 根据队名查询球队信息
	 * @param name
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> getTeamByName(String name) throws Exception;
	
	/**
	 * 查询球队详细信息
	 * @param id
	 * @return TeamInfoBean
	 */
	public TeamInfoBean getTeamDetail(String id) throws Exception;
	
	/**
	 * 查询教练员
	 * @param id
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> getCoach(String id) throws Exception;
	
	/**
	 * 查询球员
	 * @param id
	 * @return List<PlayerPubBean>
	 */
	public List<PlayerPubBean> getPlayer(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<TeamInfoBean>
	 */
	public List<TeamInfoBean> showTeam() throws Exception;
}
