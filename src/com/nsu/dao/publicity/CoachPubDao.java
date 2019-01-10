package com.nsu.dao.publicity;

import java.util.List;

import com.nsu.bean.publicity.CoachPubBean;

/**
* @Title: InfoCoachDao
* @Package com.nsu.dao.publicity;
* @Description: 教练员公示InfoCoachDao
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface CoachPubDao {

	/**
	 * 查询教练员信息
	 * @param 
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> getCoachAll() throws Exception;
	
	/**
	 * 根据教练员名查询教练员信息
	 * @param name
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> getCoachByName(String name) throws Exception;
	
	/**
	 * 查询教练员详细信息
	 * @param id
	 * @return CoachPubBean
	 */
	public CoachPubBean getCoachDetail(String id) throws Exception;
	
	/**
	 * 查询教练员所属的球队
	 * @param id
	 * @return String
	 */
	public List<String> getCoachTeam(String id) throws Exception;
	
	/**
	 * 查询教练员积分
	 * @param id
	 * @return CoachPubBean
	 */
	public CoachPubBean getCoachScore(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> showCoach() throws Exception;
	
}
