package com.nsu.service.coach.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.dao.coach.CoachMobileDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachMobileService;
@Service
public class CoachMobileServiceImpl extends BaseService implements CoachMobileService{

	@Autowired
	private CoachMobileDao coachMobileDao;
	
	@Override
	public List<Map<String, Object>> getCoachSchoolMatch(String A_ID) {
		List<Map<String, Object>> listMap = coachMobileDao.getCoachSchoolMatch(A_ID);
		return jifenCount(listMap,"4",A_ID);
	}

	@Override
	public List<Map<String, Object>> getCoachCountyMatch(String A_ID) {
		List<Map<String, Object>> listMap = coachMobileDao.getCoachCountyMatch(A_ID);
		return jifenCount(listMap,"3",A_ID);
	}

	@Override
	public List<Map<String, Object>> getCoachCityMatch(String A_ID) {
		List<Map<String, Object>> listMap = coachMobileDao.getCoachCityMatch(A_ID);
		return jifenCount(listMap,"2",A_ID);
	}

	@Override
	public List<Map<String, Object>> getCoachProvinceMatch(String A_ID) {
		List<Map<String, Object>> listMap = coachMobileDao.getCoachProvinceMatch(A_ID);
		return jifenCount(listMap,"1",A_ID);
	}

	@Override
	public List<Map<String, Object>> getCoachOtherMatch(String A_ID) {
		List<Map<String, Object>> listMap = coachMobileDao.getCoachOtherMatch(A_ID);
		return jifenCount(listMap,"5",A_ID);
		
	}
	
	private List<Map<String, Object>> jifenCount(List<Map<String, Object>> listMap,String level,String A_ID){
		List<Map<String, Object>> COM_List = coachMobileDao.getCoachCOM_ID(A_ID, level);
		for (int i = 0; i < COM_List.size(); i++) {		
			COM_List.get(i).put("jifen", 0);
			COM_List.get(i).put("title", "无");
			Map<String, Object> cMap = COM_List.get(i);
			for (int j = 0; j < listMap.size(); j++) {
				Map<String, Object> map = listMap.get(j);
				if (COM_List.get(i).get("COM_ID").toString().equals(map.get("COM_ID").toString())) {
					if (map.get("R_WIN_STATUS")!=null){
					//客队获胜
					if (map.get("R_WIN_STATUS").toString().equals("1")) {
						if (map.get("R_V_TEAM_ID").toString().equals(map.get("TEAM_ID").toString())) {
							cMap.put("jifen", Integer.parseInt(cMap.get("jifen").toString())+3);
						}
					}
					//主队获胜
					if (map.get("R_WIN_STATUS").toString().equals("0")) {
						if (map.get("R_H_TEAM_ID").toString().equals(map.get("TEAM_ID").toString())) {
							cMap.put("jifen", Integer.parseInt(cMap.get("jifen").toString())+3);
						}
					}
					}
					if (cMap.get("COM_WINNER")!=null&&cMap.get("COM_WINNER").toString().equals(map.get("TEAM_ID").toString())) {
						cMap.put("title", "冠军");
					}else if (cMap.get("COM_SECOND")!=null&&cMap.get("COM_SECOND").toString().equals(map.get("TEAM_ID").toString())) {
						cMap.put("title", "亚军");
					}else if (cMap.get("COM_THIRD")!=null&&cMap.get("COM_THIRD").toString().equals(map.get("TEAM_ID").toString())) {
						cMap.put("title", "季军");
					}
				}
			}
		}
		return COM_List;
	}

	/* (non-Javadoc)
	 * @see com.nsu.service.coach.CoachMobileService#getCoachID(java.lang.String)
	 */
	@Override
	public Map<String, Object> getCoachID(String a_ID) {
		Map<String, Object> map=coachMobileDao.getCoachID(a_ID);
		return map;
	}
	@Override
	public List<String> getPushInfo(String team_id) {
		List<String> list=coachMobileDao.getTeamPushInfo(team_id);
		return list;
	}
	

}
