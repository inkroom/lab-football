package com.nsu.service.publicty;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;

/**
* @Title: IInfoCoachService
* @Package com.nsu.service.publicty;
* @Description: 教练员公示IInfoCoachService
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface ICoachPubService {

	/**
	 * 查询教练员信息
	 * @param 
	 * @return PageInfo<CoachPubBean>
	 */
	public PageInfo<CoachPubBean> getCoachAllService(int pageNum, int pageSize) throws Exception;
	
	/**
	 * 根据教练员名查询教练员信息
	 * @param name
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> getCoachByNameService(String name) throws Exception;
	
	/**
	 * 查询教练员详细信息
	 * @param id
	 * @return CoachPubBean
	 */
	public CoachPubBean getCoachDetailService(String id) throws Exception;
	
	/**
	 * 查询教练员所属的球队
	 * @param id
	 * @return String
	 */
	public String getCoachTeamService(String id) throws Exception;
	
	/**
	 * 查询教练员积分
	 * @param id
	 * @return CoachPubBean
	 */
	public CoachPubBean getCoachScoreService(String id) throws Exception;
	
	/**
	 * 首页显示
	 * @param
	 * @return List<CoachPubBean>
	 */
	public List<CoachPubBean> showCoachService() throws Exception;
}
