package com.nsu.controller.publicity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.bean.publicity.GameInfoBean;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.bean.publicity.TeamInfoBean;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.publicty.ITeamInfoService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
* @Title: TeamInfoController
* @Package com.nsu.controller.publicity;
* @Description: 球队公示
* @author 曾绩平
* @date 2017-04-20
* @version V1.0
*/
@Controller
@RequestMapping("/info")
public class TeamInfoController extends BaseController {
	
	private List<TeamInfoBean> teamAll;
	@Resource
	private ITeamInfoService teamInfoService;
	
	private Map<Object,Object> map1 = new HashMap<>();//保存机构ID

	/**
	 * 查询球队信息
	 * @param
	 * @return String
	 */
	@RequestMapping(value = "/teamInfo", method = RequestMethod.GET)
	public String teamInfo() {
		return "/publicity/info_team";
	}
	
	@RequestMapping(value = "/teamList", method = RequestMethod.POST)
	public void teamList(int pageNum, HttpServletResponse response) throws Exception {
		
		PageInfo<TeamInfoBean> pageInfo = teamInfoService.getTeamAllService(pageNum, 10);
		
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询球队信息（机构）
	 * @param
	 * @return String
	 */
	@RequestMapping(value = "/teamInfoOrg", method = RequestMethod.GET)
	public String teamInfoOrg() {
		return "/publicity/info_team_org";
	}
	
	@RequestMapping(value = "/teamListOrg", method = RequestMethod.POST)
	public void teamListOrg(int pageNum, HttpServletResponse response, HttpSession session) throws Exception {
		
		map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
		log.info("-----"+map1);
		long ORG_ID = (long) map1.get("ORG_ID");
		log.info("-----"+ORG_ID);
		PageInfo<TeamInfoBean> pageInfo = teamInfoService.getTeamAllOrgService(pageNum, 10, ORG_ID);
		
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 根据队名查询球队信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/searchTeam", method = RequestMethod.POST)
	public void searchTeam(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String search = request.getParameter("search");
		teamAll = teamInfoService.getTeamByNameService(search);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("size", teamAll.size());
			result.put("teamAll", teamAll);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询球队详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/teamInfoDetail", method = RequestMethod.POST)
	public void teamInfoDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String num = request.getParameter("num");
		TeamInfoBean detail = teamInfoService.getTeamDetailService(num);
		List<CoachPubBean> coach = teamInfoService.getCoachService(num);
		List<PlayerPubBean> player = teamInfoService.getPlayerService(num);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("detail", detail);
			result.put("coach", coach);
			result.put("player", player);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
}
