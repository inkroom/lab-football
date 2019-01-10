package com.nsu.service.player.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.dao.player.PlayerTeamDao;
import com.nsu.service.player.PlayerTeamService;

/**
 * 
* @Title: PlayerMatchServiceImpl.java
* @Package com.nsu.service.player.impl
* @Description: 球员球队信息实现类
* @author 侯松梁
* @date 2017年4月14日 下午4:24:13
* @version V1.0
 */
@Service
public class PlayerTeamServiceImpl implements PlayerTeamService {
	
	@Resource
	PlayerTeamDao playerTeamDao;

	@Override
	public List<Map<String, Object>> getPlayerTeam(String TEAM_ID) throws Exception {
		return playerTeamDao.getPlayerTeam(TEAM_ID);
	}

	@Override
	public Map<String, Object> getTeamInfoById(String TEAM_ID) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map = playerTeamDao.getTeamInfoById(TEAM_ID);
		// 得到各级比赛场次
		Map<String, Object> info = new HashMap<>();
		info = playerTeamDao.getTeamMatchInfo(TEAM_ID);
		if (info != null) {
			
			Integer TS_SCHOOL_VICTOR = (Integer) info.get("TS_SCHOOL_VICTOR");
			Integer TS_SCHOOL_DRAW = (Integer) info.get("TS_SCHOOL_DRAW");
			Integer TS_SCHOOL_LOSE = (Integer) info.get("TS_SCHOOL_LOSE");
			Integer TS_COUNTRY_VICTOR = (Integer) info.get("TS_COUNTRY_VICTOR");
			Integer TS_COUNTRY_DRAW = (Integer) info.get("TS_COUNTRY_DRAW");
			Integer TS_COUNTRY_LOSE = (Integer) info.get("TS_COUNTRY_LOSE");
			Integer TS_CITY_VICTOR = (Integer) info.get("TS_CITY_VICTOR");
			Integer TS_CITY_DRAW = (Integer) info.get("TS_CITY_DRAW");
			Integer TS_CITY_LOSE = (Integer) info.get("TS_CITY_LOSE");
			Integer TS_PROVINCE_VICTOR = (Integer) info.get("TS_PROVINCE_VICTOR");
			Integer TS_PROVINCE_DRAW = (Integer) info.get("TS_PROVINCE_DRAW");
			Integer TS_PROVINCE_LOSE = (Integer) info.get("TS_PROVINCE_LOSE");
			Integer TS_OTHER_WIN = (Integer) info.get("TS_OTHER_WIN");
			Integer TS_OTHER_LOSS = (Integer) info.get("TS_OTHER_LOSS");
			Integer TS_OTHER_ALL = (Integer) info.get("TS_OTHER_ALL");
			
			// 定义总场次
			Integer counts = 0;
			// 定义胜利场次
			Integer wincounts = 0;
			// 定义平局场次
			Integer drawcounts = 0;
			// 定义积分
			Integer integral = 0;
			// 判断是否为空并计算总场次
			if (TS_SCHOOL_VICTOR != null) {
				counts += TS_SCHOOL_VICTOR;
				wincounts += TS_SCHOOL_VICTOR;
				integral += TS_SCHOOL_VICTOR * 3;
			}
			if (TS_SCHOOL_DRAW != null) {
				counts += TS_SCHOOL_DRAW;
				integral += TS_SCHOOL_DRAW * 2;
				drawcounts += TS_SCHOOL_DRAW;
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
				drawcounts += TS_COUNTRY_DRAW;
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
				drawcounts += TS_CITY_DRAW;
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
				drawcounts += TS_PROVINCE_DRAW;
			}
			if (TS_PROVINCE_LOSE != null) {
				counts += TS_PROVINCE_LOSE;
				integral += TS_PROVINCE_LOSE;
			}
			if (TS_OTHER_WIN != null) {
				counts += TS_OTHER_WIN;
				integral += TS_OTHER_WIN * 3;
				wincounts += TS_OTHER_WIN;
			}
			if (TS_OTHER_LOSS != null) {
				counts += TS_OTHER_LOSS;
				integral += TS_OTHER_LOSS;
			}
			if (TS_OTHER_ALL != null) {
				counts += TS_OTHER_ALL;
				integral += TS_OTHER_ALL * 2;
				drawcounts += TS_OTHER_ALL;
			}
			if (counts == 0) {
				map.put("BAT_AVG", 0);
			} else {
				// 保留小数点后两位
				DecimalFormat df = new DecimalFormat("0.00");
				map.put("BAT_AVG", df.format((double) wincounts / (counts - drawcounts) * 100));
			}
			map.put("MATCH_PLAY", counts);
			map.put("INTEGRAL", integral);
			map.put("WIN_NUM", wincounts);
			map.put("LOSE_NUM", counts - wincounts - drawcounts);
		} else {
			map.put("MATCH_PLAY", 0);
			map.put("INTEGRAL", 0);
			map.put("BAT_AVG", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getTeamName(String A_ID) throws Exception {
		return playerTeamDao.getTeamName(A_ID);
	}

	@Override
	public List<Map<String, Object>> getCoachName(String TEAM_ID) throws Exception {
		return playerTeamDao.getCoachName(TEAM_ID);
	}

	@Override
	public String getTeamPhoneNum(String TEAM_ID) throws Exception {
		return playerTeamDao.getTeamPhoneNumber(TEAM_ID);
	}
	
}
