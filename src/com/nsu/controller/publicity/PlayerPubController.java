package com.nsu.controller.publicity;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.controller.BaseController;
import com.nsu.service.publicty.IPlayerPubService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
* @Title: PlayerPubController
* @Package com.nsu.controller.publicity;
* @Description: 球员公示
* @author 曾绩平
* @date 2017-04-20
* @version V1.0
*/
@Controller
@RequestMapping("/info")
public class PlayerPubController extends BaseController {

	private List<PlayerPubBean> playerAll;
	@Resource
	private IPlayerPubService playerService;
	
	/**
	 * 查询球员信息
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/playerInfo", method = RequestMethod.GET)
	public String playerInfo(){
		return "/publicity/info_player";
	}
	
	@RequestMapping(value = "/playerList", method = RequestMethod.POST)
	public void playerList(int pageNum, HttpServletResponse response) throws Exception {
		
        PageInfo<PlayerPubBean> pageInfo=playerService.getPlayerAllService(pageNum, 10);
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 根据球员名查询球员信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/searchPlayer", method = RequestMethod.POST)
	public void searchPlayer(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String search = request.getParameter("search");
		playerAll = playerService.getPlayerByNameService(search);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("size", playerAll.size());
			result.put("playerAll", playerAll);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询球员详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/playerDetail", method = RequestMethod.POST)
	public void playerDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String num = request.getParameter("num");
		PlayerPubBean detail = playerService.getPlayerDetailService(num);
		String team = playerService.getPlayerTeamService(num);
		detail.setTeam(team);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("detail", detail);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
}
