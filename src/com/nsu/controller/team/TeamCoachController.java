package com.nsu.controller.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamCoachDO;
import com.nsu.bean.team.TeamReviewPlayerBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.team.ITeamCenterService;
import com.nsu.service.team.ITeamCoachService;
import com.nsu.util.V;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import net.sf.json.JSONArray;

/**
 * 
 * @ClassName: 球队管理教练
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 侯润达
 * @date 2017年4月19日 下午3:14:45
 *
 */
@Controller
@RequestMapping(value = "/team")
public class TeamCoachController extends BaseController {
	@Resource(name = "teamCoachService")
	ITeamCoachService iTeamCoachService;
	
	@Resource(name = "teamCenterService")
	ITeamCenterService teamCenterService;
	
	@Resource(name = "SelectMessage")
	ISelectMessageService SelectMessage;

	/**
	 * @Description: 进入教练管理，查询
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午3:23:58
	 * @return
	 */
	@RequestMapping(value = "/team_coach_view/{num}")
	@RestfulUrlAnnotation(refulUrl = "/team/team_coach_view/*.action")
	@InterceptorAnno(isRestful = true)
	public String TeamCoachView(HttpSession session, Model model, @PathVariable("num") String num,
			@RequestParam(value = "COACH_NAME", required = false) String COACH_NAME) {
		String TEAM_ID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if (V.checkEmpty(TEAM_ID) == false) {
			if (StringUtils.isNotEmpty(COACH_NAME)) {
				Page page = iTeamCoachService.selectCoachInfoByNameService(COACH_NAME, TEAM_ID);
				model.addAttribute("page", page);
			} else {
				Page page = iTeamCoachService.selectCoachInfoService(TEAM_ID, num);
				model.addAttribute("page", page);
			}
			return TeamUtil.TEAM_COACH_PAGE;
		} else {
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
	}

	/**
	 * @Description: 查询模态框
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:12:40
	 * @return
	 */
	@RequestMapping(value = "/team_coach_see", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String TeamCoachSee(String COACH_ID, String COACH_STATUS, HttpSession session) {
		log.info("看看COACH_ID！！！" + COACH_ID);
		log.info("看看COACH_STATUS！！！" + COACH_STATUS);
		int status = 404;
		String result = "登录会话失效,请重新登录";
		JSONObject jsonstr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (COACH_STATUS.equals("通过")) {
			COACH_STATUS = "1";
		} else if (COACH_STATUS.equals("待审核")) {
			COACH_STATUS = "0";
		} else {
			COACH_STATUS = "2";
		}
		log.info("看看COACH_STATUS！！！" + COACH_STATUS);
		TeamCoachDO coach = iTeamCoachService.selectCoachInfoByCOACHIDService(COACH_ID, COACH_STATUS);
		log.info("看看到了没有！！！" + coach.getCOACH_PHOTO());
		if (coach != null) {
			jsonArray = JSONArray.fromObject(coach);
			status = 200;
			log.info("状态开通！！！！！");
		} else {
			result = "无该球员信息";
		}
		jsonstr.put("coachMap", jsonArray);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		log.info("jsonstr++++++++++" + jsonstr);
		log.info("全部走完！！！！！！！！！");
		return jsonstr.toString();
	}

	/**
	 * @Description: 同意
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:12:13
	 * @return
	 */
	@RequestMapping(value = "/team_coach_one", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamCoachone(String COACH_ID, HttpSession session, TeamCenterDO teamCenterDO) {
		int status = 404;
		String result = "登录会话失效,请重新登录";
		JSONObject jsonstr = new JSONObject();
        log.info("教练员id+++++++"+COACH_ID);
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");

		result = iTeamCoachService.updatecoachoneService(COACH_ID, teamID);
		if ("success".equals(result)) {
			result = "操作成功";
			status = 200;
			//查教练的A_ID
			int COACH_AID = teamCenterService.findAIDBYCOACHIDService(COACH_ID);
			teamCenterDO = teamCenterService.findAIDBYTEAMService(teamID);
			log.info("++++++++"+COACH_AID);
			log.info("~~~~~~~~~~~~~~~"+teamCenterDO.getA_ID()+"1111111"+teamCenterDO.getTEAM_NAME());
			Map<String,Object> map = new HashMap<>();
			map.put("PS_RECEIVE", COACH_AID);//接收人A_ID
			map.put("PS_SEND_ID", teamCenterDO.getA_ID());//发送人ID
			map.put("PS_TITLE", "申请加入"+teamCenterDO.getTEAM_NAME()+"审核结果");//消息标题
			map.put("PS_TEXT", "<strong>"+teamCenterDO.getTEAM_NAME()+"</strong>同意了您的加入申请!");//消息内容
			try {
				SelectMessage.insertPersonMessage(map);
				log.info("同意" + teamID);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result = "操作失败,请重试";
		}

		log.info(result);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();

	}

	/**
	 * @Description: 拒绝
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:12:26
	 * @return
	 */
	@RequestMapping(value = "/team_coach_two", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamCoachtwo(String COACH_ID, HttpSession session, TeamCenterDO teamCenterDO) {
		int status = 404;
		String result = "登录会话失效,请重新登录";
		JSONObject jsonstr = new JSONObject();

		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		log.info("教练员id+++++++"+COACH_ID);
		result = iTeamCoachService.updatecoachtwoService(COACH_ID, teamID);
		if ("success".equals(result)) {
			result = "操作成功";
			status = 200;
			int COACH_AID = teamCenterService.findAIDBYCOACHIDService(COACH_ID);
			teamCenterDO = teamCenterService.findAIDBYTEAMService(teamID);
			log.info("++++++++"+COACH_AID);
			log.info("~~~~~~~~~~~~~~~"+teamCenterDO.getA_ID()+"1111111"+teamCenterDO.getTEAM_NAME());
			Map<String,Object> map = new HashMap<>();
			map.put("PS_RECEIVE", COACH_AID);//接收人A_ID
			map.put("PS_SEND_ID", teamCenterDO.getA_ID());//发送人ID
			map.put("PS_TITLE", "申请加入"+teamCenterDO.getTEAM_NAME()+"球队审核结果");//消息标题
			map.put("PS_TEXT", "<strong>"+teamCenterDO.getTEAM_NAME()+"</strong>拒绝了您的加入申请!");//消息内容
			try {
				SelectMessage.insertPersonMessage(map);
				log.info("拒绝" + teamID);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result = "操作失败,请重试";
		}

		log.info(result);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();

	}

}
