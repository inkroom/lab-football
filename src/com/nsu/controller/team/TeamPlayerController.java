package com.nsu.controller.team;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.OperMessageBean;
import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamReviewPlayerBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.player.PlayerInfoService;
import com.nsu.service.team.ITeamReviewPlayerService;
import com.nsu.service.team.ITeamSendVerificationCodeService;
import com.nsu.util.V;

import net.sf.json.JSONArray;

/**
 * 
* @ClassName:球队审核球员申请
* @author 侯润达
* @date 2017年4月19日 下午3:14:21 
*
 */
@Controller
@RequestMapping(value="/team")
public class TeamPlayerController extends BaseController{
	
	@Resource(name="teamReviewPlayerService")
	ITeamReviewPlayerService teamReviewService; 
	@Resource(name="teamSendVerificationCodeService")
	private ITeamSendVerificationCodeService teamSendVerificationCodeService;
	@Resource
	PlayerInfoService playerInfoService;
	
	private String[] text = {" ", "同意了您的加入申请!", "拒绝了您的加入申请!"};
	
 	/**
	 * @Description: 进入球队审核球员申请界面，分页
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午3:16:50
	 * @return
	 */
	@RequestMapping(value = "/team_player_view/{num}")
	@RestfulUrlAnnotation(refulUrl="/team/team_player_view/*.action")
	@InterceptorAnno(isRestful = true)
	public String TeamPlayerView(@PathVariable("num")String num, HttpSession session, Model model){
		//查询球队的球员数据数据
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if(V.checkEmpty(teamID) == false){
			Page page = teamReviewService.findApplyPlayersRecordsByTeamID(teamID, num);
			model.addAttribute("page", page);
			
		}else{
			log.info("没有从session中获取到球队主键ID");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		return TeamUtil.TEAM_PLAYER_PAGE;
	}
	
	/**
	 * 根据搜索关键词查询申请的球员信息
	 * @author ljl
	 * @createDate 2017-04-23 17:01:08
	 * @param seacherKeyWord
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/seach_apply_join_team_players")
	public String seachApplyJoinTeamPlayers(String seacherKeyWord, HttpSession session, Model model){
		Page page = null;
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		
		if(V.checkEmpty(teamID) == false){
			if(V.checkEmpty(seacherKeyWord) == false
					&& V.checkCHSAndENOrPeriodOrComma(seacherKeyWord, 60) == true){
				page = teamReviewService.findApplyPlayersRecordsByPlayerName(teamID, seacherKeyWord);
			}else {
				//为空或长度超过60，默认查询所有
				page = teamReviewService.findApplyPlayersRecordsByTeamID(teamID, "1");
			}
		}else{
			log.info("没有从session中获取到球队主键ID");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		
		model.addAttribute("page", page);
		return TeamUtil.TEAM_PLAYER_PAGE;
	}
	
	/**
	 * 获取队员信息
	 * @author ljl
	 * @createDate 2017-04-18 15:08:51
	 * @param rID
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/find_team_apply_players", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String findTeamPlayers(String pid, HttpSession session){
		int status = 404;
		String result = "";
		JSONObject jsonstr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if(V.checkEmpty(teamID) == false){
			TeamReviewPlayerBean player = getPlayerInfo(pid, teamID);
			if(player!=null){
				jsonArray = JSONArray.fromObject(player);
				status = 200;
			}else{
				result = "无该球员信息";
			}
		}else{
			status = 500;
			result = "登录失效,2秒后跳转到登录页面!";
		}
		jsonstr.put("playersList", jsonArray);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	
	}
	
	/**
	 * 根据球员aid获取球员信息
	 * @author ljl
	 * @createDate 2017-06-05 16:41:40
	 * @param playerId
	 * @return
	 */
	private TeamReviewPlayerBean getPlayerInfo(String pId, String teamID){
		TeamReviewPlayerBean playerInfo =  teamReviewService.findApplyPlayersByPID(pId, teamID);
		if(playerInfo == null || V.checkEmpty(playerInfo.getAid())){
			return null;
		}
		String A_ID = playerInfo.getAid().toString();
//		//设置积分
//		try {
//			Map<String, Object> map = playerInfoService.getPlayerInfo(A_ID);
//			if(map!=null && map.isEmpty() == false && V.checkEmpty(map.get("INTEGRAL")) == false){
//				playerInfo.setTotalIntegral(Integer.parseInt(map.get("INTEGRAL").toString()));
//			}else{
//				playerInfo.setTotalIntegral(0);
//			}
//		} catch (Exception e1) {
//			playerInfo.setTotalIntegral(0);
//			e1.printStackTrace();
//		}
		
		// 获奖情况
		// 得到省级记录
		try {
			playerInfo.setProvenceHours(TeamUtil.PojoToString(playerInfoService.getPlayerActivityInfo(A_ID, "1")));
		} catch (Exception e) {
			e.printStackTrace();
			playerInfo.setProvenceHours("暂无数据");
		}
		// 得到市级记录
		try {
			playerInfo.setCityHours(TeamUtil.PojoToString(playerInfoService.getPlayerActivityInfo(A_ID, "2")));
		} catch (Exception e) {
			e.printStackTrace();
			playerInfo.setCityHours("暂无数据");
		}
		// 得到县级记录
		try {
			playerInfo.setCountryHonrs(TeamUtil.PojoToString(playerInfoService.getPlayerActivityInfo(A_ID, "3")));
		} catch (Exception e) {
			e.printStackTrace();
			playerInfo.setCountryHonrs("暂无数据");
		}
		// 得到校级记录
		try {
			playerInfo.setSchoolHonrs(TeamUtil.PojoToString(playerInfoService.getPlayerActivityInfo(A_ID, "4")));
		} catch (Exception e) {
			e.printStackTrace();
			playerInfo.setSchoolHonrs("暂无数据");
		}
		// 得到其他记录
		try {
			playerInfo.setOtherHours(TeamUtil.PojoToString(playerInfoService.getPlayerActivityInfo(A_ID, "5")));
		} catch (Exception e) {
			e.printStackTrace();
			playerInfo.setOtherHours("暂无数据");
		}
	
		return playerInfo;
	}
	
	/**
	 * 更改申请球员的审核状态
	 * @author ljl
	 * @createDate 2017-04-27 12:49:16
	 * @param pid
	 * @param operType
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/team_audit_player_operation", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamAuditPlayerOperation(String pid, String operType, String ptNum,HttpSession session){
		int status = 500;
		String result = "登录失效,2秒后跳转到登录页面!";
		JSONObject jsonstr = new JSONObject();
		
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		
		if(V.checkEmpty(teamID) == false){
			if(V.checkEmpty(pid) == false && V.checkEmpty(operType)==false){
				result = teamReviewService.updatePlayerAuditStatus(pid, operType, ptNum, teamID);
				if(V.checkEmpty(result) == true){
					result = "操作成功";
					status = 200;
					//发送消息
					int index = Integer.parseInt(operType);
					String teamName = TeamUtil.getAccountInfoInSession(session, "aName");
					if(V.checkEmpty(teamName)){
						teamName = "";
					}
					OperMessageBean messageInfo = new OperMessageBean(teamID, pid, "申请加入"+teamName+"球队审核结果", "<strong>"+teamName+"</strong>"+text[index], 4, 1, false, false);
					teamSendVerificationCodeService.sendOperMessage(messageInfo);
				}
			}else{
				status = 404;
				result = "操作失败,请重试";
			}
		}
		
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	
	}
	
	/**
	 * 验证球衣号是否可用
	 * @author ljl
	 * @createDate 2017-05-04 19:25:18
	 * @param ptNum 球衣号
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/team_check_jersey_number", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamAuditPlayerJerseyNumber(String ptNum,HttpSession session){
		int status = 500;
		String result = "登录失效,2秒后跳转到登录页面!";
		JSONObject jsonstr = new JSONObject();
		
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if(V.checkEmpty(teamID) == false){
			if(V.checkEmpty(ptNum) == false){
				boolean isOnly = teamReviewService.vaildataPlayerJerseyNumberIsOnly(ptNum, teamID);
				if(isOnly == true){
					status = 200;
				}else{
					status = 404;
					result = "球衣号"+ptNum+"已被使用";
				}
			}else{
				status = 404;
				result = "请填写球衣号";
			}
		}
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	
	}
	
	
}
