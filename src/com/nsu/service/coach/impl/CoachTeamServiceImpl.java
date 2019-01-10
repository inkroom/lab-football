package com.nsu.service.coach.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.dao.coach.CoachTeamDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachTeamService;
@Service
public class CoachTeamServiceImpl extends BaseService implements CoachTeamService {
	@Autowired
	private CoachTeamDao coachTeamDao;
	
	@Override
	public List<String> getCoachTeams(String coachID) {
		return coachTeamDao.getCoachTeams(coachID);
	}

	@Override
	public Map<String, Object> coachTeamDetail(String teamID) {
		List<String> coachList = coachTeamDao.getTeamCoachs(teamID);
		Map<String, Object> ctMap = coachTeamDao.coachTeamDetail(teamID);
		ctMap.put("TEAM_COACHS", coachList);
		return ctMap;
	}

	@Override
	public List<Map<String, Object>> coachTeamPlayerDetail(String teamID) {
		return coachTeamDao.coachTeamPlayerDetail(teamID);
	}

	@Override
	public List<Map<String, Object>> coachMatchInfo(String teamID,int page,int pageSize) {
		return coachTeamDao.coachMatchInfo(teamID,(page-1)*pageSize,pageSize);
	}

	@Override
	public Map<String, Object> getRaceResultsDetails(String raceID) {
		Map<String, Object> map = coachTeamDao.getRaceScore(raceID);
		map.put("RaceWarning", coachTeamDao.getRaceWarning(raceID));
		map.put("RaceSendOff", coachTeamDao.getRaceSendOff(raceID));
		map.put("RacePunish", coachTeamDao.getRacePunish(raceID));
		return map;
	}

	@Override
	public int coachMatchCount(String teamID) {
		
		return coachTeamDao.coachMatchCount(teamID);
	}

}
