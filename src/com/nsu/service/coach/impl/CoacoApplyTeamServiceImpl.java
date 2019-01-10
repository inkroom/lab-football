package com.nsu.service.coach.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.dao.coach.CoachApplyTeamDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachApplyTeamService;
@Service
public class CoacoApplyTeamServiceImpl extends BaseService implements CoachApplyTeamService{

	@Autowired
	private CoachApplyTeamDao coachApplyTeamDao;
	
	@Override
	public List<Map<String, Object>> getApplyTeamsInfo(String coachID,int page,int pageSize) {
		return coachApplyTeamDao.getApplyTeamsInfo(coachID,(page-1)*pageSize,pageSize);
		
	}

	@Override
	public Map<String, Object> getApplyTeamInfo(String teamNum) {
		return coachApplyTeamDao.getApplyTeamInfo(teamNum);
	}

	@Override
	public boolean coachApplyTeamStatus(String coachID, String teamID) {
		//如果数据库里查出数据之后说明教练员目前申请的球队是通过或者待审核状态，不能再次申请
		if (coachApplyTeamDao.coachApplyTeamStatus(coachID, teamID)!=null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public boolean coachApplyTeam(String coachID, String teamID) {
		int i = coachApplyTeamDao.coachApplyTeam(coachID,teamID);
		if (i==1) {
			return true;
		}
		return false;
	}

	@Override
	public int getApplyedTeamsTotal(String coachID) {
		return coachApplyTeamDao.getApplyedTeamsTotal(coachID);
	}

	/* (non-Javadoc)
	 * @see com.nsu.service.coach.CoachApplyTeamService#selectAidByteamID(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectAidByteamID(String teamID) {
		
		return coachApplyTeamDao.selectAidByteamID(teamID);
	}

	/* (non-Javadoc)
	 * @see com.nsu.service.coach.CoachApplyTeamService#selectcoachNameBycoachID(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectcoachNameAndAidBycoachID(String coachID) {
		
		return coachApplyTeamDao.selectcoachNameAndAidBycoachID(coachID);
	}

}
