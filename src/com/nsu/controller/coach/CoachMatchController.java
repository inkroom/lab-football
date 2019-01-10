package com.nsu.controller.coach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import net.sf.json.JSONArray;
/**
 * 查询教练员比赛
 * @author Mark
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachMatchController extends BaseController{
	private static final int PAGE_SIZE = 5;
	@Autowired
	private CoachTeamService coachTeamService;
	/**
	 * 通过教练员ID查询所有带领的球队
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/match",method = RequestMethod.GET)
	public ModelAndView getCoachTeams(HttpSession session){
		ModelAndView mv = new ModelAndView();
		String coachID = session.getAttribute(Constants.LOGIN_USER_ID).toString();	
		List<String> teamList = coachTeamService.getCoachTeams(coachID);
		mv.addObject("tInfo", teamList);
		mv.setViewName("/coach/coach-system/coach_match");
		return mv;
	}
	/**
	 * 获取教练所带的球队打了比赛信息，通过球队的ID查找
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/match/team",method = RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String coachMatchInfo(@RequestParam("td")String teamID,HttpSession session,
		@RequestParam(value="page",required=false,defaultValue="1")int page){
		int totalPage = coachTeamService.coachMatchCount(teamID);
		if (totalPage%PAGE_SIZE!=0) {
			totalPage/=PAGE_SIZE;
			totalPage++;
		}else {
			totalPage/=PAGE_SIZE;
		}
		if (totalPage<page&&totalPage !=0 ) {
			page = totalPage;
		}
		if(page<1){
			page=1;
		}
		
		List<Map<String, Object>> mInfo = coachTeamService.coachMatchInfo(teamID,page,PAGE_SIZE);
		session.setAttribute("cPage", page);
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("page", page);
		pageMap.put("totalPage", totalPage);
		mInfo.add(mInfo.size(),pageMap);
		String jsonStr = JSONArray.fromObject(mInfo).toString();
		return jsonStr;
	}
	/**
	 * 根据比赛ID获取当场比赛的各种判罚情况
	 * @param raceID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/match/results_details",method = RequestMethod.POST)
	public Map<String, Object> getRaceResultsDetail(@RequestParam("rd")String raceID){
		Map<String, Object> raceMap = coachTeamService.getRaceResultsDetails(raceID);
		return raceMap;
	}
	
}
