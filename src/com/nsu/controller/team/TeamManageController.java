package com.nsu.controller.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.staticvar.TeamVar;
import com.nsu.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.bean.team.TeamCenterDO;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.team.ITeamCenterService;
import com.nsu.service.team.ITeamDelateService;
import com.nsu.util.V;

/**
 * 球队管理
 * 
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 19:00:03
 */
@Controller
@RequestMapping(value = "/team")
public class TeamManageController extends BaseController {
	@Resource(name = "teamCenterService")
	ITeamCenterService teamCenterService;

	@Resource(name = "teamdelateService")
	ITeamDelateService teamdelateService;
	
	@Resource(name = "SelectMessage")
	ISelectMessageService SelectMessage;

	/**
	 * 进入球队管理页面
	 * 
	 * @author ljl
	 * @createDate 2017-04-10 19:10:54
	 * @param session
	 * @return
	 */
	@InterceptorAnno(createToken = true)
	@RequestMapping(value = "/team_manage_view")
	public String teamCenterView(HttpSession session, Model model) {
		// 从session中获取用户的账号ID
		String TEAM_ID = RequestUtil.getAccountInfoInSession(session, "teamID");
		if (!V.checkEmpty(TEAM_ID)) {
			List<TeamCenterDO> map = teamCenterService.findTeamPlayerInfo(TEAM_ID);
			model.addAttribute("stumap", map);
			log.info("看看球员样子++++" + map);
			log.info("提莫id" + TEAM_ID);
			List<TeamCenterDO> List = teamCenterService.findTeamCoachName(TEAM_ID);
			model.addAttribute("coachlist", List);
			log.info("看看教练样子++++" + List);
		} else {
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
		// 进入球队管理页面
		return TeamVar.TEAM_MANAGE_PAGE;
	}

	/**
	 * @Description: 删除团队学生
	 * @author 侯润达
	 * @create_date 2017年5月11日 下午4:18:03
	 * @return
	 */
	@InterceptorAnno(checkToken = true,removeToken = true,isRestful = true)
	@RequestMapping(value = "/team_manage_delatestu/{player}")
	public String teamRedirect(@PathVariable String player, HttpSession session, TeamCenterDO teamCenterDO,
			RedirectAttributes redirectAttributes) {
		log.info("这样能传么？" + player);
		teamCenterDO.setP_ID(player);
		String teamID = RequestUtil.getAccountInfoInSession(session, "teamID");
		if (!V.checkEmpty(teamID)) {
			try {
				if (teamdelateService.wouldDelete(teamID)){
                    log.info("提莫id" + teamID);
                    teamCenterDO.setTEAM_ID(teamID);
                    String del = teamdelateService.delatestu(teamCenterDO);
                    if ("success".equals(del)) {
                        redirectAttributes.addFlashAttribute("message", "删除成功");
                        Long P_AID = teamCenterService.findAIDBYPIDService(player);
                        teamCenterDO = teamCenterService.findAIDBYTEAMService(teamID);
                        log.info("++++++++" + P_AID);
                        log.info("~~~~~~~~~~~~~~~" + teamCenterDO.getA_ID() + "1111111" + teamCenterDO.getTEAM_NAME());
                        Map<String, Object> map = new HashMap<>();
                        map.put("PS_RECEIVE", P_AID);//接收人A_ID
                        map.put("PS_SEND_ID", teamCenterDO.getA_ID());//发送人ID
                        map.put("PS_TITLE", teamCenterDO.getTEAM_NAME() + "解雇信息");//消息标题
                        map.put("PS_TEXT", "您已被<strong>" + teamCenterDO.getTEAM_NAME() + "</strong>解雇");//消息内容
                        try {
                            SelectMessage.insertPersonMessage(map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return TeamVar.TEAM_MANAGE_HTML;
                    }
                }else{
					redirectAttributes.addFlashAttribute("message", "请等比赛结束之后删除人员！");
					return TeamVar.TEAM_MANAGE_HTML;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TeamVar.TEAM_MANAGE_HTML;
		} else {
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
	}

	/**
	 * @Description: 删除团队教练员
	 * @author 侯润达
	 * @create_date 2017年5月11日 下午4:18:23
	 * @return
	 */
	@InterceptorAnno(checkToken = true,removeToken = true,isRestful = true)
	@RequestMapping(value = "/team_manage_delatecoach/{coach}")
	public String teamRedirectcoach(@PathVariable String coach, HttpSession session, TeamCenterDO teamCenterDO,
			RedirectAttributes redirectAttributes) {
		teamCenterDO.setCOACH_ID(coach);
		String teamID = RequestUtil.getAccountInfoInSession(session, "teamID");
		if (!V.checkEmpty(teamID)) {
			teamCenterDO.setTEAM_ID(teamID);
			log.info("看看放进去没" + teamCenterDO.getA_ID());
			String delcoach = teamdelateService.delatecoach(teamCenterDO);
			if ("success".equals(delcoach)) {
				log.info("删除成功");
				redirectAttributes.addFlashAttribute("message", "解雇成功");
				Long CoachAId = teamCenterService.findAIDBYCOACHIDService(coach);
				teamCenterDO = teamCenterService.findAIDBYTEAMService(teamID);
				log.info("++++++++"+CoachAId);
				log.info("~~~~~~~~~~~~~~~"+teamCenterDO.getA_ID()+"1111111"+teamCenterDO.getTEAM_NAME());
				Map<String,Object> map = new HashMap<>();
				map.put("PS_RECEIVE", CoachAId);//接收人A_ID
				map.put("PS_SEND_ID", teamCenterDO.getA_ID());//发送人ID
				map.put("PS_TITLE", teamCenterDO.getTEAM_NAME()+"球队移除信息");//消息标题
				map.put("PS_TEXT", "您已经从<strong>"+teamCenterDO.getTEAM_NAME()+"</strong>队中移除");//消息内容
				try {
					SelectMessage.insertPersonMessage(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return TeamVar.TEAM_MANAGE_HTML;
			}
			redirectAttributes.addFlashAttribute("message", "解雇失败");
			return TeamVar.TEAM_MANAGE_HTML;
		} else {
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
	}

}
