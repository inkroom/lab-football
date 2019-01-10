package com.nsu.service.coach.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.coach.CoachInfoBean;
import com.nsu.dao.coach.CoachInfoDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachInfoService;
import com.nsu.util.CalcAge;
import com.nsu.util.InfoProtUtil;

@Service
public class CoachInfoServiceImpl extends BaseService implements CoachInfoService{
	@Autowired
	private CoachInfoDao coachinfoDao;
	
	@Override
	public Map<String, Object> getCoachInfoBase(String coachID) {
		Map<String, Object> map = coachinfoDao.getCoachInfoBase(coachID);
		if (map.get("A_ID_CARD")==null) {
			map.put("A_ID_CARD", "/");
		}else {
			map.put("A_ID_CARD", InfoProtUtil.xorInfo(map.get("A_ID_CARD").toString()));
			map.put("age", CalcAge.IDCardNoToAge(map.get("A_ID_CARD").toString()));
		}
		map.put("A_PHONE", InfoProtUtil.xorInfo(map.get("A_PHONE").toString()));
		map = isNull(map, "/");
		return map;
	}
	@Override
	public Map<String, Object> getCoachEditInfo(String coachID) {
	   Map<String, Object> map = coachinfoDao.getCoachEditInfo(coachID);
	   if(map==null){
		   return null;
	   }
	   if (map.get("A_ID_CARD")==null) {
			map.put("A_ID_CARD", "/");
		}else {
			map.put("A_ID_CARD", InfoProtUtil.xorInfo(map.get("A_ID_CARD").toString()));
		}
	   if (map.get("COACH_BIRTHDAY")==null) {
			map.put("COACH_BIRTHDAY", "/");
		}else {
			map.put("COACH_BIRTHDAY",map.get("COACH_BIRTHDAY").toString().substring(0, 10));
		}
	   return map;
	}
	@Override
	public List<Map<String, Object>> getCoachWonderfulPhoto(String coachID) {
	
		return coachinfoDao.getWdpic(coachID);
	}
	//校级
	@Override
	public List<Map<String, Object>> getCoachSchoolMatch(String coachID) {
		List<Map<String, Object>> listMatch= coachinfoDao.getCoachSchoolMatch(coachID);
		return listMatch;
	}
	//县级
	@Override
	public List<Map<String, Object>> getCoachCountryMatch(String coachID) {
		List<Map<String, Object>> listMatch= coachinfoDao.getCoachCountyMatch(coachID);
		return listMatch;
	}
	//市级
	@Override
	public List<Map<String, Object>> getCoachCityMatch(String coachID) {
		List<Map<String, Object>> listMatch= coachinfoDao.getCoachCityMatch(coachID);
		return listMatch;
	}
	//省级
	@Override
	public List<Map<String, Object>> getCoachProvinceMatch(String coachID) {
		List<Map<String, Object>> listMatch= coachinfoDao.getCoachProvinceMatch(coachID);
		return listMatch;
	}
	@Override
	public Map<String, Object> getCoachintegral(String coachID) {
		Map<String, Object> map = coachinfoDao.getCoachintegral(coachID);
		if(map==null){
			return null;
		}
		map = isNull(map, "0");
		return map;
	}
	
	/**
	 * 
	 * @param map 要判空的map
	 * @param fillString 给空键填补值的字符
	 * @return
	 */
	private Map<String, Object> isNull(Map<String, Object> map, String fillString){
		for (Map.Entry<String, Object> entry : map.entrySet()){
			if (map.get(entry.getKey())==null) {
				map.put(entry.getKey(), fillString);
			}
		}
		return map;
	}
	
	@Override
	public boolean headPicUpload(String coachID, String path) {
		try {
			int i = coachinfoDao.headPicUpload(coachID, path);
			if (i!=0) {
				return true;
			}
			return false;
		} catch (Exception e) {	
			log.error(e.getMessage());
			log.error("保存教练员头像失败");
			return false;
		}
	}
	@Override
	public boolean saveCoachEditInfo(CoachInfoBean coach) {
		int i = 0;
		try {
			i = coachinfoDao.saveCoachEditInfo(coach);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (i!=0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean updateWonderfulPhoto(String coachAccountID, String path) {
		try {
			coachinfoDao.updateWonderfulPhoto(coachAccountID, path);
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("保存教练员精彩图片失败");
			return false;
		}
		return true;
	}
	@Override
	public void saveEmail(String A_EMAIL_CHECK_CODE, String A_ID) {
		coachinfoDao.saveEmail(A_EMAIL_CHECK_CODE, A_ID);
	}
	@Override
	public String getEmailCode(String A_ID) {	
		return coachinfoDao.getEmailCode(A_ID);
	}
	@Override
	public List<Map<String, Object>> getCoachOtherMatch(String coachID) {
		return coachinfoDao.getCoachOtherMatch(coachID);
	}

	

	
}
