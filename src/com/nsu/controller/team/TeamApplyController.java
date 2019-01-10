package com.nsu.controller.team;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.nsu.common.Constants;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.OperMessageBean;
import com.nsu.bean.team.Page;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamApplyMatchService;
import com.nsu.service.team.ITeamSendVerificationCodeService;
import com.nsu.util.V;

import java.util.Map;

/**
 * 球队比赛申请
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 19:00:03
 */
@Controller
@RequestMapping(value="/team")
public class TeamApplyController extends BaseController{

	@Resource(name="teamApplyMatchService")
	ITeamApplyMatchService teamApplyMatchService;
	@Resource(name="teamSendVerificationCodeService")
	private ITeamSendVerificationCodeService teamSendVerificationCodeService;

	/**
	 * 进入球队比赛申请页面
	 * @author ljl
	 * @createDate 2017-04-10 19:10:54
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/team_apply_view/{num}")
	@RestfulUrlAnnotation(refulUrl="/team/team_apply_view/*.action")
	@InterceptorAnno(isRestful = true)
	public String teamCenterView(@PathVariable("num")String num, HttpSession session, Model model){
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");

		if(!V.checkEmpty(teamID)){
			//调用service层查询近期可以申请的比赛、通过的比赛、待审核的比赛
			Page page = teamApplyMatchService.findTeamApplyMatchInfoRecordsByTeamID(teamID, num);
			model.addAttribute("page", page);

		}else{
			//登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		//进入球队比赛申请页面
		return TeamUtil.TEAM_APPLY_PAGE;
	}

	/**
	 * 球队申请比赛
	 * @author ljl
	 * @createDate 2017-04-17 17:33:05
	 * @param comID
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/team_apply_match", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamApplyMatch(String comID, String comName, HttpSession session){
		JSONObject jsonStr = new JSONObject();
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		log.info("session里的东西="+ session.getAttribute(Constants.LOGIN_USER));
		if(!V.checkEmpty(teamID)){
			if(!V.checkEmpty(comID)){
				//申请比赛
				String result = teamApplyMatchService.updataTeamApplyMatch(teamID, comID,Integer.parseInt(((Map)session.getAttribute(Constants.LOGIN_USER)).get("TEAM_TYPE").toString()));
				if(result==null){
					jsonStr.put("status", 200);
					jsonStr.put("message", "报名成功,请等待举办方审核!预祝您取得好成绩");

					//发送消息
					String teamName = TeamUtil.getAccountInfoInSession(session, "aName");
					if(V.checkEmpty(teamName)){
						teamName = "";
					}
					OperMessageBean messageInfo = new OperMessageBean(teamID, comID, teamName+"球队申请参加"+comName+"比赛", "<strong>"+teamName+"</strong>球队申请参加<strong>"+comName+"</strong>比赛，<br/>请前往->管理申请->赛事申请中进行审核", 4, 6, false, false);
					teamSendVerificationCodeService.sendOperMessage(messageInfo);

				}else{
					jsonStr.put("status", 500);
					jsonStr.put("message", result);
				}
			}else{
				jsonStr.put("status", 500);
				jsonStr.put("message", "数据异常，请刷新重试!");
			}
		}else{
			jsonStr.put("status", 500);
			jsonStr.put("message", "登录失效,2秒后跳转到登录页面!");
		}
		return jsonStr.toString();
	}

	/**
	 * 根据用户输入关键词查询赛事信息
	 * @author ljl
	 * @createDate 2017-04-21 16:03:55
	 * @param seacherKeyWord
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/team_seacher_matches")
	public String seacherTeamMatches(String seacherKeyWord, HttpSession session, Model model){
		Page page = null;
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");

		if(!V.checkEmpty(teamID)){

			//调用service层查询近期可以申请的比赛、通过的比赛、待审核的比赛
			if(!V.checkEmpty(seacherKeyWord)
					&& V.checkCHSAndENOrPeriodOrComma(seacherKeyWord, 60)){
				//根据查询关键词查询
				page = teamApplyMatchService.findTeamApplyMatchInfoBySeacherKey(teamID, seacherKeyWord);
			}else {
				//为空或长度超过60，默认查询所有
				page = teamApplyMatchService.findTeamApplyMatchInfoRecordsByTeamID(teamID, "1");
			}
		}else{
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		model.addAttribute("page", page);
		return TeamUtil.TEAM_APPLY_PAGE;
	}



}
