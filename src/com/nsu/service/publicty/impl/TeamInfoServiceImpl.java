package com.nsu.service.publicty.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.bean.publicity.TeamInfoBean;
import com.nsu.dao.publicity.TeamInfoDao;
import com.nsu.service.BaseService;
import com.nsu.service.publicty.ITeamInfoService;
import com.nsu.util.CalcAge;
import com.nsu.util.InfoProtUtil;

/**
* @Title: TeamInfoServiceImpl
* @Package com.nsu.service.publicty.impl;
* @Description: 球队公示TeamInfoServiceImpl
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

@Service("iTeamInfoService")
public class TeamInfoServiceImpl extends BaseService implements ITeamInfoService {

	/**
	 * 自动注入teamInfoDao
	 */
	@Resource
	private TeamInfoDao teamInfoDao;
	
	/**
	 * 查询球队信息
	 * @param 
	 * @return PageInfo<TeamInfoBean>
	 */
	@Override
	public PageInfo<TeamInfoBean> getTeamAllService(int pageNum, int pageSize) throws Exception {
		log.info("----------getTeamAllService--------------");
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<TeamInfoBean> list = teamInfoDao.getTeamAll();//DB操作查询数据
        for(TeamInfoBean team: list){
        	if(team.getOrganization() == null){
        		team.setOrganization("暂无");
        	}
        }
        PageInfo<TeamInfoBean> pageInfo=new PageInfo<TeamInfoBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}
	
	/**
	 * 查询球队信息（机构）
	 * @param 
	 * @return PageInfo<TeamInfoBean>
	 */
	@Override
	public PageInfo<TeamInfoBean> getTeamAllOrgService(int pageNum, int pageSize, long id) throws Exception {
		log.info("----------getTeamAllService--------------");
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<TeamInfoBean> list = teamInfoDao.getTeamAllOrg(id);//DB操作查询数据
        for(TeamInfoBean team: list){
        	if(team.getOrganization() == null){
        		team.setOrganization("暂无");
        	}
        }
        PageInfo<TeamInfoBean> pageInfo=new PageInfo<TeamInfoBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}

	/**
	 * 根据队名查询球队信息
	 * @param name
	 * @return List<TeamInfoBean>
	 */
	@Override
	public List<TeamInfoBean> getTeamByNameService(String name) throws Exception {
		log.info("----------getTeamByNameService--------------");
		List<TeamInfoBean> list = teamInfoDao.getTeamByName(name);
		for(TeamInfoBean team: list){
        	if(team.getOrganization() == null){
        		team.setOrganization("暂无");
        	}
        }
		return list;
	}

	/**
	 * 查询球队详细信息
	 * @param name
	 * @return TeamInfoBean
	 */
	@Override
	public TeamInfoBean getTeamDetailService(String id) throws Exception {
		log.info("----------getTeamDetailService--------------");
		TeamInfoBean team = teamInfoDao.getTeamDetail(id);
		if(team.getOrganization() == null){
    		team.setOrganization("暂无");
    	}
		//1省，2市，3县，4校
		switch(team.getLevel()){
		case "1":
			team.setLevel("省级");
			break;
		case "2":
			team.setLevel("市级");
			break;
		case "3":
			team.setLevel("县级");
			break;
		case "4":
			team.setLevel("校级");
			break;
		case "5":
			team.setLevel("无");
			break;
		}
		
		//球队类型：1.小学 、2.初中、3.高中、4.大学、5.混合
		switch(team.getType()){
		case "1":
			team.setType("小学 ");
			break;
		case "2":
			team.setType("初中");
			break;
		case "3":
			team.setType("高中");
			break;
		case "4":
			team.setType("混合");
			break;
		}
		return team;
	}

	/**
	 * 查询教练员
	 * @param id
	 * @return List<CoachPubBean>
	 */
	@Override
	public List<CoachPubBean> getCoachService(String id) throws Exception {
		log.info("----------getCoachService--------------");
		List<CoachPubBean> coaches = teamInfoDao.getCoach(id);
		for(CoachPubBean coach : coaches) {
			String cardid = InfoProtUtil.xorInfo(coach.getBirthday().toString());
			Integer age = CalcAge.IDCardNoToAge(cardid);
			coach.setBirthday(age.toString());
		}
		return coaches;
	}

	/**
	 * 查询球员
	 * @param id
	 * @return List<PlayerPubBean>
	 */
	@Override
	public List<PlayerPubBean> getPlayerService(String id) throws Exception {
		log.info("----------getPlayerService--------------");
		return teamInfoDao.getPlayer(id);
	}

	@Override
	public List<TeamInfoBean> showTeamService() throws Exception {
		log.info("----------getPlayerService--------------");
		return teamInfoDao.showTeam();
	}

}
