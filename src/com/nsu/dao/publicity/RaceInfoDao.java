package com.nsu.dao.publicity;

import java.util.List;

import com.nsu.bean.publicity.RaceInfoBean;

/**
* @Title: RaceInfoDao
* @Package com.nsu.dao.publicity;
* @Description: 赛程公示RaceInfoDao
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

public interface RaceInfoDao {

	/**
	 * 查询赛程信息
	 * @param 
	 * @return List<RaceInfoBean>
	 */
	public List<RaceInfoBean> getRaceAll() throws Exception;
	
	/**
	 * 根据赛程名查询赛程信息
	 * @param name
	 * @return List<RaceInfoBean>
	 */
	public List<RaceInfoBean> getRaceByName(String name) throws Exception;
	
	/**
	 * 查询赛程详情
	 * @param id
	 * @return RaceInfoBean
	 */
	public RaceInfoBean getRaceDetail(String id) throws Exception;
	
	/**
	 * 查询队名
	 * @param id
	 * @return String
	 */
	public String getTeamName(String id) throws Exception;
}
