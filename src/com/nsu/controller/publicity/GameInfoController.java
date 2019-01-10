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
import com.nsu.bean.publicity.GameInfoBean;
import com.nsu.bean.publicity.TeamInfoBean;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.publicty.IGameInfoService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
* @Title: GameInfoController
* @Package com.nsu.controller.publicity;
* @Description: 赛事公示
* @author 曾绩平
* @date 2017-04-20
* @version V1.0
*/
@Controller
@RequestMapping("/info")
public class GameInfoController extends BaseController {

	private List<GameInfoBean> gameAll;
	@Resource
	private IGameInfoService gameInfoService;
	
	private Map<Object,Object> map1 = new HashMap<>();//保存机构ID
	
	/**
	 * 查询赛事信息
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/gameInfo", method = RequestMethod.GET)
	public String gameInfo() {
		return "/publicity/info_game";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(int pageNum, HttpServletResponse response) throws Exception {
		
		PageInfo<GameInfoBean> pageInfo = gameInfoService.getGameAllService(pageNum,10);
		
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询赛事信息（机构）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gameInfoOrg", method = RequestMethod.GET)
	public String gameInfoOrg() {
		return "/publicity/info_game_org";
	}
	
	@RequestMapping(value = "/listOrg", method = RequestMethod.POST)
	public void listOrg(int pageNum, HttpServletResponse response, HttpSession session) throws Exception {
		
		map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
		log.info("-----"+map1);
		long ORG_ID = (long) map1.get("ORG_ID");
		log.info("-----"+ORG_ID);
		PageInfo<GameInfoBean> pageInfo = gameInfoService.getGameAllOrgService(pageNum, 10, ORG_ID);
		
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询赛事详细信息
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/gameInfoDetail", method = RequestMethod.POST)
	public void gameInfoDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String num = request.getParameter("num");
		List<TeamInfoBean> team = gameInfoService.getTeamService(num);
		GameInfoBean detail = gameInfoService.getGameDetailService(num);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("detail", detail);
			result.put("team", team);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 根据赛事名查询赛事信息
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/searchGame", method = RequestMethod.POST)
	public void searchGame(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String search = request.getParameter("search");
		gameAll = gameInfoService.getGameByNameService(search);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("size", gameAll.size());
			result.put("gameAll", gameAll);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
}
