package com.nsu.controller.team;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamCenterService;
import com.nsu.service.team.ITeamScheduleArrangeService;
import com.nsu.util.V;

import net.sf.json.JSONArray;

/**
 * 球队安排上场球员
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-12 19:13:37
 */
@Controller
@RequestMapping(value="/team")
public class TeamScheduleArrangeController extends BaseController{

	@Resource(name="teamCenterService") 
	ITeamCenterService teamCenterService;
	@Resource(name="teamScheduleArrangeService") 
	ITeamScheduleArrangeService teamScheduleArrangeService;
	
	/**
	 * 进入球队安排上场球员页面
	 * @author ljl
	 * @createDate 2017-04-12 19:13:59
	 * @return
	 */
	@RequestMapping(value = "/team_schedule_arrange_view/{comID}/{num}")
	@RestfulUrlAnnotation(refulUrl="/team/team_schedule_arrange_view/*/*.action")
	@InterceptorAnno(isRestful = true)
	public String teamScheduleArrange(@PathVariable("comID")String comID, @PathVariable("num") String num, Model model, HttpSession session){
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		Page page = null;
		int scheduleNum = 0;
		if(V.checkEmpty(teamID) == false){
			if(V.checkEmpty(comID) == false){
				//根据赛事ID查询赛程信息
				page = teamScheduleArrangeService.findSchedulesByComIDRecords(comID, teamID, num);
				//赛程数量
				scheduleNum = teamScheduleArrangeService.findSchedulesNum(comID, teamID);
				if(scheduleNum == 0){
					model.addAttribute("message", "赛事举办方尚未安排赛程,请注意关注");
				}
				model.addAttribute("scheduleNum", scheduleNum);
				model.addAttribute("page", page);
			}
		}else{
			
			log.info("没有从session中获取到球队主键ID");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		return TeamUtil.TEAM_SCHEDULE_ARRANGE_PAGE;
	}
	
	/**
	 * 获取球队安排队员参与当前赛程的情况
	 * @author ljl
	 * @createDate 2017-04-18 15:08:51
	 * @param rID
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/find_team_plan_players", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String findTeamPlayers(String rID, HttpSession session){
		JSONObject jsonstr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int status = 404;
		String result = "服务器忙,请稍后再试";
		
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if(V.checkEmpty(teamID) == false){
			
			List<TeamCenterDO> playersList = teamCenterService.findTeamplayerInfo(teamID);
			
			if(playersList != null && playersList.size()>0){
				//对判断球员是否被安排参赛
				playersList = perfectData(playersList, teamID, rID);
				jsonArray = JSONArray.fromObject(playersList);
			}
			result = "";
			status = 200;
		}else{
			result = "登录失效,2秒后跳转到登录页面!";
			status = 500;
		}
		
		jsonstr.put("playersList", jsonArray);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	}
	
	/**
	 * 判断球员是否已经参加当前赛程；同时计算积分
	 * @author ljl
	 * @createDate 2017-04-19 08:43:34
	 * @param playersList
	 * @param teamID
	 * @param rID
	 * @return
	 */
	public List<TeamCenterDO> perfectData(List<TeamCenterDO> playersList, String teamID, String rID){
		//获取已经参赛的球员
		String isAddMatchPlayerIDStr = teamScheduleArrangeService.findAddMatchPlayersID(teamID, rID)+"-";
		
		for(int i = 0; i < playersList.size(); i++){
			
			if(isAddMatchPlayerIDStr.indexOf("-"+playersList.get(i).getpID()+"-") != -1){
				//球员已经被选择加入这场赛事
				playersList.get(i).setIsAddMatch(1);
			}else{
				playersList.get(i).setIsAddMatch(0);
			}
			playersList.get(i).setrID(rID);
		}
		return playersList;
	}

	/**
	 * 安排球员
	 * @author ljl
	 * @createDate 2017-04-19 08:50:23
	 * @param pid
	 * @param rID
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/team_arrange_players", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String arrangePlayers(String pid, String rID, String type, HttpSession session){
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		JSONObject jsonstr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int status = 404;
		String result = "数据异常,请刷新再试";
		
		List<TeamCenterDO> playersList = new ArrayList<TeamCenterDO>();
		if(V.checkEmpty(teamID) == false){
			if( V.checkEmpty(pid) == false && V.checkEmpty(rID) == false &&  V.checkEmpty(type) == false){
				
				result = teamScheduleArrangeService.updateArrangePlayers(teamID, pid, rID, type);
				log.info(result);
				if(V.checkEmpty(result) == true){
					status = 200;
					result = "操作成功";
					//重新获取球员信息
					playersList = teamCenterService.findTeamplayerInfo(teamID);
					if(playersList != null && playersList.size()>0){
						//对判断球员是否被安排参赛
						playersList = perfectData(playersList, teamID, rID);
						jsonArray = JSONArray.fromObject(playersList);
					}
				}
			}
		}else{
			result = "登录失效,2秒后跳转到登录页面!";
			status = 500;
		}
		
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		jsonstr.put("playersList", jsonArray);
		return jsonstr.toString();
	}
	
	
}
