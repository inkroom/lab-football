package com.nsu.service.player.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.player.PlayerTeamBean;
import com.nsu.controller.BaseController;
import com.nsu.dao.player.PlayerTeamApplyDao;
import com.nsu.service.player.PlayerTeamApplyService;
import com.nsu.util.V;

/**
 * @ClassName PlayerTeamApplyServiceImpl
 * @Description (球队申请球队Service实现类)
 * @author hm
 * @Date 2017年4月17日 下午9:25:58
 * @version 1.0.0
 */
@Service
public class PlayerTeamApplyServiceImpl implements PlayerTeamApplyService {
	protected static final Log log = LogFactory.getLog(BaseController.class);
	@Resource
	private PlayerTeamApplyDao playerTeamApplyDao;

	@Override
	public PlayerTeamBean getTeamInfo(String team_num) throws Exception {
		return setTeamInfo(playerTeamApplyDao.getTeamInfo(team_num));
	}

	@Override
	public PageInfo<PlayerTeamBean> getTeamApplyList(String a_id, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		List<PlayerTeamBean> teamInfos = playerTeamApplyDao.getTeamApplyList(a_id);
		for (PlayerTeamBean teamInfo : teamInfos) {
			setTeamInfo(teamInfo);
		}
		PageInfo<PlayerTeamBean> pageInfo = new PageInfo<PlayerTeamBean>(teamInfos);
		return pageInfo;
	}

	@Override
	public boolean applyTeam(String a_id, String team_id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String p_id = playerTeamApplyDao.getP_id(a_id);
		param.put("p_id", p_id);
		param.put("team_id", team_id);
		int result = playerTeamApplyDao.insertMidPlayerTeam(param);
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isApplied(String team_id, String a_id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String p_id = playerTeamApplyDao.getP_id(a_id);
		param.put("p_id", p_id);
		param.put("team_id", team_id);
		int result = playerTeamApplyDao.isApplied(param);
		if (result >= 1)
			return true;
		return false;
	}

	@Override
	public boolean allowApply(String team_id, String a_id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String p_id = playerTeamApplyDao.getP_id(a_id);
		param.put("p_id", p_id);
		param.put("team_id", team_id);
		int team_rank = playerTeamApplyDao.getTeamRank(param);
		param.put("team_rank", team_rank);
		log.info(param);
		int result = playerTeamApplyDao.allowApply(param);
		if (result >= 1)
			return false;
		return true;
	}

	@Override
	public String getTeamAId(String team_id) throws Exception {
		return playerTeamApplyDao.getTeamAId(team_id);
	}

	/**
	 * 
	 * @Description (查询球队的积分，胜率，场次等信息)
	 * @param teamInfo
	 * @return
	 * @throws Exception
	 */
	public PlayerTeamBean setTeamInfo(PlayerTeamBean teamInfo) throws Exception {
		if (V.checkEmpty(teamInfo.getTeamAffiliation())) {
			teamInfo.setTeamAffiliation("无");
		}
		Map<String, Object> rate = getAllCount(playerTeamApplyDao.getTeamScoreInfo(teamInfo.getTeamID()));
		if (rate != null) {
			teamInfo.setIntegral(rate.get("integral").toString());
			teamInfo.setWinRate(rate.get("winRate").toString());
			teamInfo.setMatchNum(rate.get("matchNum").toString());
		} else {
			teamInfo.setIntegral("0 分");
			teamInfo.setWinRate("0%");
			teamInfo.setMatchNum("0 场");
		}
		log.info(teamInfo.toString());
		return teamInfo;
	}

	/**
	 * 得到各级比赛场次，计算中场次、胜率
	 * 
	 * @param map
	 */
	public Map<String, Object> getAllCount(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		// 得到各级比赛场次
		Integer TS_SCHOOL_VICTOR = (Integer) map.get("TS_SCHOOL_VICTOR");
		Integer TS_SCHOOL_DRAW = (Integer) map.get("TS_SCHOOL_DRAW");
		Integer TS_SCHOOL_LOSE = (Integer) map.get("TS_SCHOOL_LOSE");
		Integer TS_COUNTRY_VICTOR = (Integer) map.get("TS_COUNTRY_VICTOR");
		Integer TS_COUNTRY_DRAW = (Integer) map.get("TS_COUNTRY_DRAW");
		Integer TS_COUNTRY_LOSE = (Integer) map.get("TS_COUNTRY_LOSE");
		Integer TS_CITY_VICTOR = (Integer) map.get("TS_CITY_VICTOR");
		Integer TS_CITY_DRAW = (Integer) map.get("TS_CITY_DRAW");
		Integer TS_CITY_LOSE = (Integer) map.get("TS_CITY_LOSE");
		Integer TS_PROVINCE_VICTOR = (Integer) map.get("TS_PROVINCE_VICTOR");
		Integer TS_PROVINCE_DRAW = (Integer) map.get("TS_PROVINCE_DRAW");
		Integer TS_PROVINCE_LOSE = (Integer) map.get("TS_PROVINCE_LOSE");
		// 其它
		Integer TS_OTHER_WIN = (Integer) map.get("TS_OTHER_WIN");
		Integer TS_OTHER_LOSS = (Integer) map.get("TS_OTHER_LOSS");
		Integer TS_OTHER_ALL = (Integer) map.get("TS_OTHER_ALL");
		//
		// 定义总场次
		Integer counts = 0;
		// 定义胜利场次
		Integer wincounts = 0;
		// 定义积分
		Integer integral = 0;
		// 判断是否为空并计算总场次
		if (TS_OTHER_WIN != null) {
			counts += TS_OTHER_WIN;
			wincounts += TS_OTHER_WIN;
			integral += TS_OTHER_WIN * 3;
		}

		if (TS_SCHOOL_VICTOR != null) {
			counts += TS_SCHOOL_VICTOR;
			wincounts += TS_SCHOOL_VICTOR;
			integral += TS_SCHOOL_VICTOR * 3;
		}
		if (TS_OTHER_LOSS != null) {
			counts += TS_OTHER_LOSS;
			integral += TS_OTHER_LOSS;
		}
		if (TS_SCHOOL_DRAW != null) {
			counts += TS_SCHOOL_DRAW;
			integral += TS_SCHOOL_DRAW * 2;
		}
		if (TS_OTHER_ALL != null) {
			counts += TS_OTHER_ALL;
			integral += TS_OTHER_ALL * 2;
		}
		if (TS_SCHOOL_LOSE != null) {
			counts += TS_SCHOOL_LOSE;
			integral += TS_SCHOOL_LOSE;
		}
		if (TS_COUNTRY_VICTOR != null) {
			counts += TS_COUNTRY_VICTOR;
			wincounts += TS_COUNTRY_VICTOR;
			integral += TS_COUNTRY_VICTOR * 3;
		}
		if (TS_COUNTRY_DRAW != null) {
			counts += TS_COUNTRY_DRAW;
			integral += TS_COUNTRY_DRAW * 2;
		}
		if (TS_COUNTRY_LOSE != null) {
			counts += TS_COUNTRY_LOSE;
			integral += TS_COUNTRY_LOSE;
		}
		if (TS_CITY_VICTOR != null) {
			counts += TS_CITY_VICTOR;
			wincounts += TS_CITY_VICTOR;
			integral += TS_CITY_VICTOR * 3;
		}
		if (TS_CITY_DRAW != null) {
			counts += TS_CITY_DRAW;
			integral += TS_CITY_DRAW * 2;
		}
		if (TS_CITY_LOSE != null) {
			counts += TS_CITY_LOSE;
			integral += TS_CITY_LOSE;
		}
		if (TS_PROVINCE_VICTOR != null) {
			counts += TS_PROVINCE_VICTOR;
			wincounts += TS_PROVINCE_VICTOR;
			integral += TS_PROVINCE_VICTOR * 3;
		}
		if (TS_PROVINCE_DRAW != null) {
			counts += TS_PROVINCE_DRAW;
			integral += TS_PROVINCE_DRAW * 2;
		}
		if (TS_PROVINCE_LOSE != null) {
			counts += TS_PROVINCE_LOSE;
			integral += TS_PROVINCE_LOSE;
		}
		if (counts == 0) {
			result.put("winRate", "0%");
		} else {
			// 保留小数点后两位
			DecimalFormat df = new DecimalFormat("0.00");
			result.put("winRate", df.format((float) wincounts / counts * 100) + "%");
		}
		result.put("matchNum", counts + " 场");
		result.put("integral", integral + " 分");
		return result;
	}

}
