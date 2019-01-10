package com.nsu.controller.player.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.nsu.util.InfoProUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.player.ResultJson;
import com.nsu.service.player.PlayerTeamService;
import com.nsu.util.RSAencrypt.MobileToken;

/**
 * 
* @Title: MobliePlayerTeamController.java
* @Package com.nsu.controller.player.mobile
* @Description: TODO（手机球员查看球队信息）
* @author 侯松梁
* @date 2017年4月24日 上午11:20:52
* @version V1.0
 */
@Controller
@RequestMapping(value = "mobile/player")
public class MobliePlayerTeamController {
	// 返回信息
	private final String successCode = "200";
	private final String errorCode = "404";
	private final String systemError = "查询失败，请稍后查看！";
	private Map<String, Object> result1 = null;
	
	@Resource
	PlayerTeamService playerTeamService;
	
	/**
	 * 得到球队信息
	 * @param token
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/getTeamInfo")
	public ResultJson getTeamInfo(@RequestParam("token")String token) {
		Map<String, Object> info = null;
		List<Map<String, Object>> list = null;
		//返回值存放
		List<Object> result = new ArrayList<>();
		//解析token
		info = MobileToken.getTokenMap(token);
		String A_ID = info.get("A_ID").toString();
		
		try {
			list = playerTeamService.getTeamName(A_ID);
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					result1 = playerTeamService.getTeamInfoById(map.get("TEAM_ID").toString());
					result1.put("PHONE_NUM", InfoProUtil.xorInfo(playerTeamService.getTeamPhoneNum(map.get("TEAM_ID").toString())));
					//球员人数
					result1.put("player_num", playerTeamService.getPlayerTeam(map.get("TEAM_ID").toString()).size());
					List<Map<String, Object>> coachList = playerTeamService.getCoachName(map.get("TEAM_ID").toString());
					//对教练信息进行处理
					String coachInfo = "";
					for (int i = 0; i < coachList.size(); i++) {
						coachInfo = coachInfo + coachList.get(i).get("COACH_NAME");
						if (i != coachList.size() - 1) {
							coachInfo = coachInfo + "、";
						}
					}
					result1.put("COACH_NAME", coachInfo);
					infoProces(result1);
					
					result.add(result1);
				}
			} else {
				return new ResultJson(errorCode, result, "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultJson(errorCode, systemError);
		}
		return new ResultJson(successCode, result, String.valueOf(list.size()));
	}
	
	/**
	 * 对返回信息处理，如果为空，返回""
	 * @param map
	 */
	private void infoProces(Map<String, Object> map){
		if (map.get("TEAM_BADGE") == null) {
			result1.put("TEAM_BADGE", "");
		}
		if (map.get("TEAM_LEADER") == null) {
			result1.put("TEAM_LEADER", "");
		}
		if (map.get("TEAM_AFFILIATION") == null) {
			result1.put("TEAM_AFFILIATION", "");
		}
		if (map.get("WIN_NUM") == null) {
			result1.put("WIN_NUM", 0);
		}
		if (map.get("LOSE_NUM") == null) {
			result1.put("LOSE_NUM", 0);
		}
		//移除无用信息
		result1.remove("TS_SCHOOL_VICTOR");
		result1.remove("TS_SCHOOL_LOSE");
		result1.remove("TS_PROVINCE_VICTOR");
		result1.remove("TS_PROVINCE_LOSE");
		result1.remove("TS_COUNTRY_VICTOR");
		result1.remove("TS_COUNTRY_LOSE");
		result1.remove("TS_CITY_DRAW");
		result1.remove("TS_CITY_VICTOR");
		result1.remove("TS_CITY_LOSE");
		result1.remove("TS_PROVINCE_DRAW");
		result1.remove("TS_SCHOOL_DRAW");
		result1.remove("TS_COUNTRY_DRAW");
		result1.remove("TEAM_ID");
	}

}
