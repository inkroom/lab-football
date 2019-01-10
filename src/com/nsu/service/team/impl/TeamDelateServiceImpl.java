package com.nsu.service.team.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.bean.team.TeamCenterDO;
import com.nsu.controller.BaseController;
import com.nsu.dao.team.TeamDeleteDao;
import com.nsu.service.team.ITeamDelateService;

@Service("teamdelateService")
public class TeamDelateServiceImpl extends BaseController implements ITeamDelateService{

	@Resource
    TeamDeleteDao teamDelateDao;


	@Override
	public boolean wouldDelete(String teamId)throws Exception {
		return !teamDelateDao.wouldDelete(teamId);
	}

	/**
	 * @Description: 删除学生
	 * @author 侯润达
	 * @create_date 2017年4月18日 下午4:00:45
	 * @return
	 */
	@Override
	public String delatestu(TeamCenterDO teamCenterDO) {
		int delstu = teamDelateDao.delatestu(teamCenterDO);
		log.info("删除学生信息条数："+delstu);
		// TODO Auto-generated method stub
		if(delstu >= 1){
		return "success";
		}
	return "error";		
	}

	/**
	 * @Description: 删除教练	
	 * @author 侯润达
	 * @create_date 2017年4月18日 下午4:01:14
	 * @return
	 */
	@Override
	public String delatecoach(TeamCenterDO teamCenterDO) {
		// TODO Auto-generated method stub
		int delcoach = teamDelateDao.delatecoach(teamCenterDO);
		log.info("删除教练信息条数:"+delcoach);
		if(delcoach>=1){
		return "success";
		}
	return "error";
	}

}
