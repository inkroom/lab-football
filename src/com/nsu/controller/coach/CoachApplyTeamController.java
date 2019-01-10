package com.nsu.controller.coach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
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
import com.nsu.service.coach.CoachApplyTeamService;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.util.ResponseUtil;
/**
 * 教练员申请球队
 * @author Mark
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachApplyTeamController extends BaseController{
	private static final int PAGE_SIZE = 5;
	@Autowired
	private CoachApplyTeamService coachApplyTeamService;
	@Autowired
	private ISelectMessageService iSelectMessageService;
	/**
	 * 通过教练ID查看目前的球队申请状态
	 * @return
	 */
	@RequestMapping(value="/apply_info", method = RequestMethod.GET)
	public ModelAndView getCoachApplyTeamsInfo(HttpSession session,
			@RequestParam(value="page",required=false,defaultValue="1")int page){
		String coachID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
			//获取当前提交过申请的球队的条数
		int totalPage = coachApplyTeamService.getApplyedTeamsTotal(coachID);
		log.info(totalPage%PAGE_SIZE);
		if (totalPage%PAGE_SIZE!=0) {
			totalPage=totalPage/PAGE_SIZE+1;
		}else {
			totalPage/=PAGE_SIZE;
		}
		if (page>=totalPage && totalPage !=0) {
			page = totalPage;
		}
		if(page<1){
			page=1;
		}
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> tInfo = coachApplyTeamService.getApplyTeamsInfo(coachID,page,PAGE_SIZE);
		mv.addObject("page", page);
		mv.addObject("tInfo", tInfo);
		
		mv.addObject("totalPage",totalPage);
		mv.setViewName("/coach/coach-system/teamApply");
		return mv;
	}
	/**
	 * 通过球队编号搜索球队的信息
	 * @param teamNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/apply_team_info",method = RequestMethod.POST)
	public Map<String, Object> getApplyTeamInfo(@RequestParam("td")String teamNum){
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{10,10}");
		Matcher matcher = pattern.matcher(teamNum);
		if (matcher.matches()) {
			Map<String, Object> tInfo =  coachApplyTeamService.getApplyTeamInfo(teamNum.trim());
			
			return tInfo;
		}else {
			return null;
		}

	}
	/**
	 * 通过球队ID申请加入球队
	 * @param teamID
	 * @param session
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/applyteam",method = RequestMethod.POST)
	public void coachApplyTeam(@RequestParam("td")String teamID,
			
			HttpSession session,
			HttpServletResponse response) throws Exception{
		String message="";
		String coachID = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		if (coachApplyTeamService.coachApplyTeamStatus(coachID, teamID)) {//判断教练是否还有权限加入此球队
			if (coachApplyTeamService.coachApplyTeam(coachID, teamID)) {
				message = "申请成功,请等待球队审核";
				Map<String, Object> map1=coachApplyTeamService.selectAidByteamID(teamID);
				Map<String, Object> map2=coachApplyTeamService.selectcoachNameAndAidBycoachID(coachID);
				Map<String, Object> map =new HashMap<String,Object>();
				log.info(map2+"++++++++++++"+map1+"-------------");
				map.put("PS_RECEIVE", map1.get("A_ID"));
				map.put("PS_SEND_ID",map2.get("A_ID") );
				map.put("PS_TITLE", "教练员申请");
				String text="教练员：<strong>"+map2.get("A_NAME")+"</strong><br>申请加入球队,请进入球队消息查看";
				map.put("PS_TEXT",text);
				iSelectMessageService.insertPersonMessage(map);
				log.info("");
			}else {
				message = "未知原因申请失败";
			}
		}else {
			message = "您已经申请过此球队了";
		}
		try {
			ResponseUtil.write(response, message);
		} catch (Exception e) {
			log.error(e.getMessage());

		}
	}
}
