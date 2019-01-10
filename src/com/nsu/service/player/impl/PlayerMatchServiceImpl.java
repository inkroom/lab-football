package com.nsu.service.player.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.nsu.bean.player.MobilePlayerMatchBean;
import com.nsu.bean.player.PlayerMatchBean;
import com.nsu.dao.player.PlayerMatchDao;
import com.nsu.service.player.PlayerMatchService;

/**
 * @ClassName PlayerMatchServiceImpl
 * @Description TODO(球员赛事)
 * @author hm
 * @Date 2017年4月17日 下午3:35:05
 * @version 1.0.0
 */
@Service
public class PlayerMatchServiceImpl implements PlayerMatchService {
	protected static final Log log = LogFactory.getLog(PlayerMatchServiceImpl.class);
	@Resource
	private PlayerMatchDao playerMatchDao;

	@Override
	public Map<String, Object> getTeamID(String A_ID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRaceResultsDetails(String raceID) {
		Map<String, Object> map = playerMatchDao.getRaceScore(raceID);
		map.put("RaceWarning", playerMatchDao.getRaceWarning(raceID));
		map.put("RaceSendOff", playerMatchDao.getRaceSendOff(raceID));
		map.put("RacePunish", playerMatchDao.getRacePunish(raceID));
		return map;
	}
	@Override
	public List<Map<String, Object>> getTeamNameAndId(String a_id) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		//获取球员球队id
		List<String> teamIds = playerMatchDao.getTeamID(a_id);
		for (String teamId : teamIds) {

			//获取球队名称
			String teamName = playerMatchDao.getTeamName(teamId);
			Map<String, Object> team = new HashMap<String, Object>();
			team.put("teamId", teamId);
			team.put("teamName", teamName);
			result.add(team);
		}
		return result;
	}

	@Override
	public Map<String, Object> getMatchID(String TEAM_ID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getMatchList(String TEAM_ID) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		//根据球队id得到赛事信息
		List<PlayerMatchBean> matchList = playerMatchDao.getMatchList(TEAM_ID);
		for (PlayerMatchBean playerMatchBean : matchList) {
			log.info(playerMatchBean.toString());
			//主队得分
			int hTeamScore = playerMatchBean.getR_overtime_h_t_s() + playerMatchBean.getR_pena_h_t_s()
					+ playerMatchBean.getR_regular_h_t_s();
			//次队得分
			int vTeamScore = playerMatchBean.getR_overtime_v_t_s() + playerMatchBean.getR_pena_v_t_s()
					+ playerMatchBean.getR_regular_v_t_s();
			//得到主队名称
			String hTeamName = playerMatchDao.getTeamName(playerMatchBean.getR_h_team_id());
			//得到客队名称
			String vTeamName = playerMatchDao.getTeamName(playerMatchBean.getR_v_team_id());
			int com_id=playerMatchBean.getCom_id();
			Map<String,String> race=playerMatchDao.getRaceNameAndEndTime(com_id);
			log.info(race.toString());
			Map<String, Object> match = new HashMap<String, Object>();
			match.put("r_id", playerMatchBean.getR_id());
			match.put("r_name", race.get("COM_NAME"));
			match.put("hTeamName", hTeamName);
			match.put("vTeamName", vTeamName);
			match.put("score", hTeamScore+" : "+vTeamScore);
			match.put("time", race.get("COM_END"));
			result.add(match);
		}
		return result;

	}

	@Override
	public List<Map<String, Object>> getMatchName(String TEAM_ID) throws Exception {
		return playerMatchDao.getMatchName(TEAM_ID);
	}

	@Override
	public List<MobilePlayerMatchBean> getSchedule(String COM_ID, String TEAM_ID) throws Exception {
		return playerMatchDao.getSchedule(COM_ID, TEAM_ID);
	}


}
