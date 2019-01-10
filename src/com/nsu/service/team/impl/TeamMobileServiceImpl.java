package com.nsu.service.team.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.team.TeamMobileResultBean;
import com.nsu.dao.team.TeamMobileDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamMobileService;
import com.nsu.util.V;

@Service("teamMobileService")
public class TeamMobileServiceImpl extends BaseService implements ITeamMobileService{
	
	@Autowired
	TeamMobileDao teamMobileDao;
	
	@Override
	public List<TeamMobileResultBean> findTeamMatchInfo(String aid) {
		List<TeamMobileResultBean> list = null;
		try{
			//根据aid查询teamID
			String teamID = teamMobileDao.findTeamIDByAID(aid);
			
			if(V.checkEmpty(teamID) == false){
				//根据teamID查询已经结束的赛事信息
				list = teamMobileDao.findTeamMatchesInfo(teamID);
				if(list!=null && list.size()>0){
					//根据赛事id查询赛程信息
					for(int i = 0; i < list.size(); i++){
						String comID = list.get(i).getComID();
						list.get(i).setRaceDetaileslsit(findRaceDetailsInfo(comID, teamID));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> findRaceDetailsInfo(String comID, String teamID) {
		List<Map<String, Object>> list = null;
		try{
			if(V.checkEmpty(comID) == false && V.checkEmpty(teamID) == false){
				log.info(teamID + "  " + comID);
				list = teamMobileDao.findRaceDetailsInfo(comID, teamID);
				if(list!=null){
					for(int i = 0; i < list.size(); i++){
						if(list.get(i).get("rHTeamID").equals(teamID) == false){
							//主队不是当前球队；将主队和客队调换
							Object tmpTeamBadge = list.get(i).get("rHTeamBadge");
							Object tmpTeamName = list.get(i).get("rHTeamName");
							//主队
							list.get(i).remove("rHTeamBadge");
							list.get(i).remove("rHTeamName");
							list.get(i).put("rHTeamBadge", list.get(i).get("rVTeamBadge"));
							list.get(i).put("rHTeamName", list.get(i).get("rVTeamName"));
							//客队
							list.get(i).remove("rVTeamBadge");
							list.get(i).remove("rVTeamName");
							list.get(i).put("rVTeamBadge", tmpTeamBadge);
							list.get(i).put("rVTeamName", tmpTeamName);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TeamMobileResultBean> findTeamMatchIsPlayingInfo(String aid) {
		List<TeamMobileResultBean> list = null;
		try{
			//根据aid查询teamID
			String teamID = teamMobileDao.findTeamIDByAID(aid);
			
			if(V.checkEmpty(teamID) == false){
				//根据teamID查询正在进行的赛事信息
				list = teamMobileDao.findTeamMatchesIsPlayingInfo(teamID);
				if(list!=null && list.size()>0){
					//根据赛事id查询赛程信息
					for(int i = 0; i < list.size(); i++){
						String comID = list.get(i).getComID();
						list.get(i).setRaceDetaileslsit(findRaceDetailsInfo(comID, teamID));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return list;
	}

	
	
	
}
