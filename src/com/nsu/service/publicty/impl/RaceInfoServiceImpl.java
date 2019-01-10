package com.nsu.service.publicty.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.RaceInfoBean;
import com.nsu.dao.publicity.RaceInfoDao;
import com.nsu.service.BaseService;
import com.nsu.service.publicty.IRaceInfoService;


/**
* @Title: RaceInfoServiceImpl
* @Package com.nsu.service.publicty.impl;
* @Description: 赛程公示RaceInfoServiceImpl
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

@Service("iRaceInfoService")
public class RaceInfoServiceImpl extends BaseService implements IRaceInfoService {

	@Resource
	private RaceInfoDao raceInfoDao;
	
	/**
	 * 查询赛程信息
	 * @param 
	 * @return List<RaceInfoBean>
	 */
	@Override
	public PageInfo<RaceInfoBean> getRaceAllService(int pageNum, int pageSize) throws Exception {
		log.info("----------getRaceAllService--------------");
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<RaceInfoBean> list = raceInfoDao.getRaceAll();//DB操作查询数据
        for(RaceInfoBean race: list){
        	setRefereeAndWin(race);
        }
        PageInfo<RaceInfoBean> pageInfo=new PageInfo<RaceInfoBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}

	/**
	 * 根据赛程名查询赛程信息
	 * @param name
	 * @return List<RaceInfoBean>
	 */
	@Override
	public List<RaceInfoBean> getRaceByNameService(String name) throws Exception {
		log.info("----------getRaceByNameService--------------");
		List<RaceInfoBean> list = raceInfoDao.getRaceByName(name);
		for(RaceInfoBean race: list){
			setRefereeAndWin(race);
        }
		return list;
	}

	/**
	 * 查询赛程详情
	 * @param id
	 * @return RaceInfoBean
	 */
	@Override
	public RaceInfoBean getRaceDetailService(String id) throws Exception {
		log.info("----------getRaceDetailService--------------");
		RaceInfoBean race = raceInfoDao.getRaceDetail(id);
		
		setRefereeAndWin(race);
		return race;
	}

	/**
	 * 查询队名
	 * @param id
	 * @return String
	 */
	@Override
	public String getTeamNameService(String id) throws Exception {
		log.info("----------getTeamNameService--------------");
		return raceInfoDao.getTeamName(id);
	}
	
	/*
	 * 根据赛事直播状态设置  裁判员  和  胜出队伍  的值
	 * @param race
	 */
	public void setRefereeAndWin(RaceInfoBean race) throws Exception {
		String teamOne = getTeamNameService(race.getTeamOne());
    	String teamTwo = getTeamNameService(race.getTeamTwo());
    	race.setTeamOne(teamOne);
    	race.setTeamTwo(teamTwo);
    	if(race.getReferee() == null){
    		race.setReferee("暂无");
    	}
    	
    	switch (race.getStatus()) {
		case "1":
			race.setWinTeam("尚未开始");
			break;
		case "2":
			race.setWinTeam("正在比赛");
			break;
		case "3":
			break;
    	}
	}

}
