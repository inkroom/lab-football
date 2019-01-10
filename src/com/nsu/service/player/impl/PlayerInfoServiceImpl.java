package com.nsu.service.player.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.nsu.staticvar.CommonVar;
import com.nsu.util.InfoProUtil;
import com.nsu.util.StringHelper;
import org.springframework.stereotype.Service;

import com.nsu.bean.player.PlayerActityBean;
import com.nsu.bean.player.PlayerInfoBean;
import com.nsu.dao.player.PlayerInfoDao;
import com.nsu.service.player.PlayerInfoService;

/**
 * 
 * @Title: PlayerInfoServiceImpl.java
 * @Package com.nsu.service.player.impl
 * @Description: 球员信息实现类
 * @author 侯松梁
 * @date 2017年4月11日 下午2:27:21
 * @version V1.0
 */
@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

	@Resource
	PlayerInfoDao playerInfoDao;

	@Override
	public Map<String, Object> getPlayerInfo(String id) throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		map = playerInfoDao.getPlayerInfo(id);
		//得到积分信息
		List<Map<String, Object>> teamMap = getTeamInfo(id);
		if (teamMap != null) {	
			map.put("INTEGRAL", getIntegral(teamMap));
		} else {
			map.put("INTEGRAL", 0);
		}
		
		//得到学籍号
		map.put("STU_NUM", playerInfoDao.getStuNum(map.get(CommonVar.Account.ID_CARD).toString()));
		
		// 得到信息，将敏感信息解密后再存放
		String A_ID_CARD = InfoProUtil.xorInfo(map.get(CommonVar.Account.ID_CARD).toString());
		map.put(CommonVar.Account.ID_CARD, A_ID_CARD);
		map.put(CommonVar.Account.PHONE, InfoProUtil.xorInfo(map.get(CommonVar.Account.PHONE).toString()));
		// 计算年龄
		map.put("AGE", StringHelper.IDCardNoToAge(map.get(CommonVar.Account.ID_CARD).toString()));
		return map;
	}

	@Override
	public void updatePlayerInfo(PlayerInfoBean playerInfoBean) throws Exception {
		playerInfoDao.updatePlayerInfo(playerInfoBean);
		playerInfoDao.updatePlayerInfoName(playerInfoBean);
	}

	@Override
	public List<PlayerActityBean> getPlayerActivityInfo(String A_ID, String COM_BIG_LEVEL) throws Exception {
		//获取活动记录
		List<PlayerActityBean> list = playerInfoDao.getPlayerActivityInfo(A_ID, COM_BIG_LEVEL);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				//时间格式转化
				String data = list.get(i).getCom_end();
				list.get(i).setCom_end(data.substring(0,4)+"年"+data.substring(5,7)+"月");
				//球队名处理
				String team_name = list.get(i).getTeam_name();
				if (!team_name.substring(team_name.length()-1, team_name.length()).equals("队")) {
					list.get(i).setTeam_name(team_name+"队");
				}
				//得到名次并记录
				if (list.get(i).getTeam_id().equals(list.get(i).getCom_winner())) {
					list.get(i).setCom_rank("冠军");
				} else if (list.get(i).getTeam_id().equals(list.get(i).getCom_second())) {
					list.get(i).setCom_rank("亚军");
				} else if (list.get(i).getTeam_id().equals(list.get(i).getCom_third())){
					list.get(i).setCom_rank("季军");
				}
			}
		}
		return list;
	}

	@Override
	public boolean updatePhoto(String a_id, String filePath) throws Exception {
		int result = playerInfoDao.updatePlayerPhoto(a_id, filePath);
		return result == 1;
	}

	@Override
	public boolean updateWonderfulPhoto(String a_id, String filePath) throws Exception {
		Map<String, Object>wonder=new HashMap<String,Object>();
		wonder.put("a_id", a_id);
		wonder.put("save_path", filePath);
		wonder.put("file_type", 0);
		wonder.put("up_status", 1);
		return playerInfoDao.insertWonderfulPhoto(wonder) == 1;
	}

	@Override
	public List<Map<String, Object>> getWdpic(String A_ID) throws Exception {
		return playerInfoDao.getWdpic(A_ID);
	}

	@Override
	public List<Map<String, Object>> getTeamInfo(String A_ID) throws Exception {
		return playerInfoDao.getTeamInfo(A_ID);
	}

	@Override
	public String getStuNum(String STU_ID_CARD) throws Exception {
		return playerInfoDao.getStuNum(STU_ID_CARD);
	}

	@Override
	public Map<String, Object> getStudentInfo(String STU_ID_CARD) throws Exception {
		return playerInfoDao.getStudentInfo(STU_ID_CARD);
	}
	
	/**
	 * 得到球员积分
	 * @param list
	 */
	public Integer getIntegral(List<Map<String, Object>> list) {
		//判断是否为空并计算积分
		Integer integral = 0;
		//得到各级比赛场次
		for (int i = 0; i < list.size(); i++) {
			Integer PS_SCHOOL_WIN = (Integer) list.get(i).get("PS_SCHOOL_WIN");
			Integer PS_SCHOOL_FLAT = (Integer) list.get(i).get("PS_SCHOOL_FLAT");
			Integer PS_SCHOOL_LOSE = (Integer) list.get(i).get("PS_SCHOOL_LOSE");
			Integer PS_COUNTY_WIN = (Integer) list.get(i).get("PS_COUNTY_WIN");
			Integer PS_COUNTY_FLAT = (Integer) list.get(i).get("PS_COUNTY_FLAT");
			Integer PS_COUNTY_LOSE = (Integer) list.get(i).get("PS_COUNTY_LOSE");
			Integer PS_CITY_WIN = (Integer) list.get(i).get("PS_CITY_WIN");
			Integer PS_CITY_FLAT = (Integer) list.get(i).get("PS_CITY_FLAT");
			Integer PS_CITY_LOSE = (Integer) list.get(i).get("PS_CITY_LOSE");
			Integer PS_PROVINE_WIN = (Integer) list.get(i).get("PS_PROVINE_WIN");
			Integer PS_PROVINE_FLAT = (Integer) list.get(i).get("PS_PROVINE_FLAT");
			Integer PS_PROVINE_LOSE = (Integer) list.get(i).get("PS_PROVINE_LOSE");
			Integer PS_OTHER_WIN = (Integer) list.get(i).get("PS_OTHER_WIN");
			Integer PS_OTHER_LOSS = (Integer) list.get(i).get("PS_OTHER_LOSS");
			Integer PS_OTHER_ALL = (Integer) list.get(i).get("PS_OTHER_ALL");

			//判断并计算积分
			if (PS_SCHOOL_WIN != null) 
				integral += PS_SCHOOL_WIN*3;
			if (PS_SCHOOL_FLAT != null)
				integral += PS_SCHOOL_FLAT*2;
			if (PS_SCHOOL_LOSE != null)
				integral += PS_SCHOOL_LOSE;
			if (PS_COUNTY_WIN != null) 
				integral += PS_COUNTY_WIN*3;
			if (PS_COUNTY_FLAT != null)
				integral += PS_COUNTY_FLAT*2;
			if (PS_COUNTY_LOSE != null)
				integral += PS_COUNTY_LOSE;
			if (PS_CITY_WIN != null) 
				integral += PS_CITY_WIN*3;
			if (PS_CITY_FLAT != null)
				integral += PS_CITY_FLAT*2;
			if (PS_CITY_LOSE != null)
				integral += PS_CITY_LOSE;
			if (PS_PROVINE_WIN != null) 
				integral += PS_PROVINE_WIN*3;
			if (PS_PROVINE_FLAT != null)
				integral += PS_PROVINE_FLAT*2;
			if (PS_PROVINE_LOSE != null)
				integral += PS_PROVINE_LOSE;
			if (PS_OTHER_WIN != null)
				integral += PS_OTHER_WIN * 3;
			if (PS_OTHER_LOSS != null) 
				integral += PS_OTHER_LOSS;
			if (PS_OTHER_ALL != null)
				integral += PS_OTHER_ALL * 2;
		}
		
		return integral;
	}

}
