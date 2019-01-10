package com.nsu.controller.player.system;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.player.PlayerMatchService;

import net.sf.json.JSONArray;

/**
 * @ClassName PlayerMatchController
 * @Description (球员：我的比赛)
 * @author hm
 * @Date 2017年4月12日 下午3:18:23
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/player/system")
public class PlayerMatchController extends BaseController {
	@Resource
	private PlayerMatchService playerMatchService;

	/**
	 * 进入我的比赛
	 * 
	 * @return
	 */
	@RequestMapping(value = "/player_match")
	public String toMatch(HttpSession session,HttpServletResponse response) {
		return "/player/player_system/player_match";
	}

	/**
	 * 得到球队信息
	 * @param session
	 * @return
	 */
	@GetMapping(value = "/match/getTeam", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTeam(HttpSession session) {
		List<Map<String, Object>> result = null;
		String userId = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		try {
			log.info(userId);
			result = playerMatchService.getTeamNameAndId(userId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info(JSONArray.fromObject(result).toString());
		return JSONArray.fromObject(result).toString();
	}

	/**
	 * 根据球队id得到赛事列表
	 * @param session
	 * @param teamId
	 * @return
	 */
	@GetMapping(value = "/match/getMatch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getMatch(String teamId) {
		 List<Map<String, Object>> result = null;
		try {
			log.info(teamId);
			result = playerMatchService.getMatchList(teamId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info(result.toString());
		return JSONArray.fromObject(result).toString();
	}

	/**
	 * 通过赛事ID查询比赛完成的结果
	 * @param session
	 * @param r_id
	 * @return
	 */
	@GetMapping(value = "/match/getMatchInfo")
	@ResponseBody
	public Map<String, Object> getMatchInfo(HttpSession session,String r_id) {
		Map<String, Object> raceMap = playerMatchService.getRaceResultsDetails(r_id);
		return raceMap;
	}
}
