package com.nsu.controller.player.system;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.player.PlayerTeamService;


/**
 * @Title: PlayerInfoController.java
 * @Package com.nsu.controller.player.system
 * @Description: 球员球队信息Controller
 * @author 侯松梁
 * @Date 2017年4月12日 下午3:19:10
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/player/system")
public class PlayerTeamController extends BaseController {
	//单个球队信息
	private Map<String, Object> map = null;
	//后台数据存放
	private List<Map<String, Object>> list = null;
	//球员信息
	private List<Map<String, Object>> player = null;
	//教练信息
	private List<Map<String, Object>> coach = null;
	
	@Resource
	PlayerTeamService playerTeamService;
	
	/**
	 * 进入我的球队页面,显示信息
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/player_team")
	public String toTeam(Model model, HttpSession session,HttpServletResponse response) {
		String A_ID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		try {
			list = playerTeamService.getTeamName(A_ID);
			model.addAttribute("team_list", playerTeamService.getTeamName(A_ID));
			if (list.size() > 0) {
				//默认显示第一条数据	
				String TEAM_ID = list.get(0).get("TEAM_ID").toString();
				map = playerTeamService.getTeamInfoById(TEAM_ID);
				getTeamInfo();
				model.addAttribute("TEAM_MEMBER", player);
				model.addAttribute("COACH_NAME_LIST", coach);
				model.addAttribute("team_info", map);
			} else {
				model.addAttribute("info", "暂未加入任何球队");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/player/player_system/player_team";
	}
	
	/**
	 * 球队id得到详细信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/player_team/info", method = RequestMethod.POST)
	public Map<String, Object> showTeamInfo(@RequestParam("id") String id, HttpSession session) {
		try {
			map = playerTeamService.getTeamInfoById(id);
			getTeamInfo();
			map.put("TEAM_MEMBER", player);
			map.put("COACH_NAME_LIST", coach);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		huashiPrintln(map.get("BAT_AVG"));
		return map;
	}
	
	
	
	/**
	 * 对相关信息处理
	 * @throws Exception
	 */
	public void getTeamInfo() throws Exception {
		//得到相关队员
		String TEAM_ID = map.get("TEAM_ID").toString();
		player = playerTeamService.getPlayerTeam(TEAM_ID);
		//得到球队教练
		coach = playerTeamService.getCoachName(TEAM_ID);
	}

}
