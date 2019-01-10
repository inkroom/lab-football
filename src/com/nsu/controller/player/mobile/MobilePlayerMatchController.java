package com.nsu.controller.player.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.player.MobilePlayerMatchBean;
import com.nsu.bean.player.ResultJson;
import com.nsu.controller.BaseController;
import com.nsu.service.player.PlayerMatchService;
import com.nsu.util.RSAencrypt.MobileToken;

/**
 * 
 * @Title: MobilePlayerMatchController.java
 * @Package com.nsu.controller.player.mobile
 * @Description: TODO(球员手机比赛信息)
 * @author 侯松梁
 * @date 2017年4月19日 下午3:47:47
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/mobile/player")
public class MobilePlayerMatchController extends BaseController {
	// 返回信息
	private final String successCode = "200";
	private final String errorCode = "404";
	private final String systemError = "查询失败，请稍后查看！";

	@Resource
	PlayerMatchService playerMatchService;

	/**
	 * 得到比赛信息
	 * @param token
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/getmatch")
	public ResultJson getMatchInfo(@RequestParam("token")String token) {
		Map<String, Object> info = null;
		List<Map<String, Object>> result1 = null;
		List<Map<String, Object>> result2 = new ArrayList<>();
		List<MobilePlayerMatchBean> result3 = null;
		List<Object> result = new ArrayList<>();
		//存放删除信息
		List<Object> delList = new ArrayList<>();
		int number = 0;
		try {
			//解析token，得到用户ID
			info = MobileToken.getTokenMap(token);
			String userId = info.get("A_ID").toString();
			log.info(userId);
			result1 = playerMatchService.getTeamNameAndId(userId);
			//得到用户比赛相关信息
			if (result1.size() > 0) {
				for (int i = 0; i < result1.size(); i++) {
					result2 = playerMatchService.getMatchName(result1.get(i).get("teamId").toString());
					if (result2.size() > 0) {
						for (int j = 0; j < result2.size(); j++) {
							log.info(result2.size());
							result3 = playerMatchService.getSchedule(result2.get(j).get("COM_ID").toString(),result2.get(j).get("TEAM_ID").toString());
							log.info(result3);
							if (result3.size() > 0) {
								result2.get(j).put("r_name_array", result3);
								result2.get(j).remove("COM_ID");
							}else{
								delList.add(result2.get(j));
							}
						}
						//移除无用信息
						result2.removeAll(delList);
						result.add(result2);
					}
					number += result2.size();
				}
				
			} else {
				return new ResultJson(successCode, result, "0");
			}
			log.info(result);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultJson(errorCode, systemError);
		}
		
		return new ResultJson(successCode, result, String.valueOf(number));
	}

}
