package com.nsu.controller.team;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.nsu.staticvar.TeamVar;
import com.nsu.util.RequestUtil;
import org.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamMatchBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamMachService;
import com.nsu.util.V;

import net.sf.json.JSONArray;

/**
 * 球队比赛信息查看 显示球队近期的比赛情况
 * 
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 19:00:03
 */
@Controller
@RequestMapping(value = "/team")
public class TeamMatchController extends BaseController {
	
	@Resource(name = "teamMatchService")
	ITeamMachService teamMachService;

	/**
	 * 进入球队比赛信息页面
	 * 
	 * @author ljl
	 * @createDate 2017-04-10 19:10:54
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/team_match_view/{num}")
	@RestfulUrlAnnotation(refulUrl="/team/team_match_view/*.action")
	@InterceptorAnno(isRestful = true)
	public String teamMatchView(@PathVariable("num")String num,HttpSession session, Model model,RedirectAttributes redirectAttributes) {
		// 从session中获取用户的账号ID
		String TEAM_ID = RequestUtil.getAccountInfoInSession(session, "teamID");
		log.info("查看球队id++"+TEAM_ID);
		if(V.checkEmpty(TEAM_ID)){
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
		Page page = teamMachService.MatchAllInfoService(TEAM_ID, num);
		model.addAttribute("page", page);
		log.info("++++++++哈哈" + page.toString());
		// 进入球队比赛页面
		return TeamVar.TEAM_MATCH_PAGE;
	}
	
	
    /**
     * @Description: 根据赛事名称查看赛事
     * @author 侯润达
     * @create_date 2017年4月21日 上午12:05:04
     * @return
     */
	@RequestMapping(value = "/team_match_find")
	public String teamMatchFind(@RequestParam(name = "C_NAME", required=false) String C_NAME, HttpSession session, Model model
			) {
		String TEAM_ID = RequestUtil.getAccountInfoInSession(session, "teamID");
		if (!V.checkEmpty(TEAM_ID)) {
		//空就返回全部
		if (StringUtils.isNotEmpty(C_NAME)) {
			log.info("CNAME在不在！" + C_NAME);
			Page page = teamMachService.MatchAllInfoByNameService(TEAM_ID, C_NAME);
			log.info("size="+page.getRecords().size());
			model.addAttribute("page", page);
			return TeamVar.TEAM_MATCH_PAGE;
		} else if (StringUtils.isEmpty(C_NAME)) {
			Page page = teamMachService.MatchAllInfoService(TEAM_ID, "1");
			model.addAttribute("page", page);
		}
		return TeamVar.TEAM_MATCH_PAGE;
		}else{
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
	}
	
	/**
	 * @Description: 查看一个赛事下的赛程
	 * @author 侯润达
	 * @create_date 2017年5月11日 下午3:57:55
	 * @return
	 */
	@RequestMapping(value = "/team_match_viewtwo/{comID}/{num}")
	@RestfulUrlAnnotation(refulUrl="/team/team_match_viewtwo/*/*.action")
	public String teamScheduleArrange(@PathVariable("comID")String comID, @PathVariable("num") String num, Model model, HttpSession session){
		String TEAM_ID = RequestUtil.getAccountInfoInSession(session, "teamID");
		if (!V.checkEmpty(TEAM_ID)) {
		Page page = null;
		if(!V.checkEmpty(comID) && !V.checkEmpty(TEAM_ID)){
			//根据赛事ID查询赛程信息
			page = teamMachService.MatchRecordInfoService(TEAM_ID, comID , num);
			model.addAttribute("page", page);
		}else{
			//没有获取到赛事ID,从定向到赛事申请页面
			log.info("******没有获取到赛事ID*******");
			log.info("没有从session中获取到球队主键ID");
			model.addAttribute("message", "系统异常,未获取到赛事,请重试");
		}
		return TeamVar.TEAM_MATCH_PAGE_TWO;
		}else{
			// 登录回话失效
			log.info("session登录失效，没获取到球队id");
			return TeamVar.TEAM_SESSION_OUTIME_PAGE;
		}
	}
	

	/**
	 * @Description: 异步查看赛程详细信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午12:09:43
	 * @return
	 */
	@RequestMapping(value = "/team_match_see",produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String MatchView(String R_ID){
		log.info("是否传送成功！"+R_ID);
        JSONObject jsonstr = new JSONObject();
        //创建JSONArray对象
        JSONArray jsonHomeList ;
        JSONArray jsonVisitList;
        JSONArray jsonHomeInfo ;
        JSONArray jsonVisitInfo;
        JSONArray jsonError ;
        //接受弹窗页面信息
        List<TeamMatchBean> HomeList = teamMachService.MatchHomeTeamListService(R_ID);
        List<TeamMatchBean> VisitList = teamMachService.MatchVisitTeamListService(R_ID);
		TeamMatchBean HomeInfo = teamMachService.MatchHomeTeamGradeService(R_ID);
		TeamMatchBean VisitInfo = teamMachService.MatchVisitTeamGradeService(R_ID);
		TeamMatchBean ErrorInfo = teamMachService.MatchErrorInfoService(R_ID);
		log.info("看看都是啥"+HomeList);
		log.info("看看都是啥"+VisitList);
		log.info("看看都是啥"+HomeInfo);
		log.info("看看都是啥"+VisitInfo);
		log.info("看看都是啥"+ErrorInfo);
		//将信息装换为Array对象
		jsonHomeList = JSONArray.fromObject(HomeList);
		jsonVisitList = JSONArray.fromObject(VisitList); 
        jsonHomeInfo = JSONArray.fromObject(HomeInfo);
        jsonVisitInfo = JSONArray.fromObject(VisitInfo);
        jsonError = JSONArray.fromObject(ErrorInfo);
        //将所有信息放入json中
        jsonstr.put("jsonHomeList", jsonHomeList);
		jsonstr.put("jsonVisitList", jsonVisitList);	
		jsonstr.put("jsonHomeInfo", jsonHomeInfo);
		jsonstr.put("jsonVisitInfo", jsonVisitInfo);
		jsonstr.put("jsonError", jsonError);
	   return jsonstr.toString();
	}

}
