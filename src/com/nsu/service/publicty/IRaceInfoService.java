package com.nsu.service.publicty;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.RaceInfoBean;

/**
* @Title: IRaceInfoService
* @Package com.nsu.service.publicty;
* @Description: 赛程公示IRaceInfoService
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/
public interface IRaceInfoService {

	/**
	 * 查询赛程信息
	 * @param 
	 * @return PageInfo<RaceInfoBean>
	 */
	public PageInfo<RaceInfoBean> getRaceAllService(int pageNum, int pageSize) throws Exception;
	
	/**
	 * 根据赛程名查询赛程信息
	 * @param name
	 * @return List<RaceInfoBean>
	 */
	public List<RaceInfoBean> getRaceByNameService(String name) throws Exception;
	
	/**
	 * 查询赛程详情
	 * @param id
	 * @return RaceInfoBean
	 */
	public RaceInfoBean getRaceDetailService(String id) throws Exception;
	
	/**
	 * 查询队名
	 * @param id
	 * @return String
	 */
	public String getTeamNameService(String id) throws Exception;
}
