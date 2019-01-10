package com.nsu.controller.coach;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.coach.CoachTeamService;
/**
 * 教练员和球队的相关操作
 * @author Mark
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachTeamController extends BaseController{
	@Autowired
	private CoachTeamService coachTeamService;
	/**
	 * 获取教练员所带领的所有球队
	 * @param request
	 * @return
	 */
	@RequestMapping(value="coach_team", method = RequestMethod.GET)
	public ModelAndView getCoachTeams(HttpServletRequest request){
		ModelAndView mv  = new ModelAndView();
		String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();	
		List<String> teamList = coachTeamService.getCoachTeams(coachID);
		mv.addObject("tInfo", teamList);
		mv.setViewName("/coach/coach-system/coach_team");
		return mv;
	}
	/**
	 * 获取和教练员相关的队伍信息
	 * @param teamID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/team/info", method = RequestMethod.POST)
	public Map<String, Object> coachTeamDetail(@RequestParam("td")String teamID){
		Map<String, Object> tInfo = coachTeamService.coachTeamDetail(teamID);
		return tInfo;
	}
	/**
	 * 通过球队的ID获取所有队员的基本信息
	 * @param teamID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/team/players", method = RequestMethod.POST)
	public List<Map<String, Object>> coachTeamPlayerDetail(@RequestParam("td")String teamID){
		List<Map<String, Object>> pInfo = coachTeamService.coachTeamPlayerDetail(teamID);
		return pInfo;
	}
}
