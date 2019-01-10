package com.nsu.controller.publicity;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.controller.BaseController;
import com.nsu.service.publicty.ICoachPubService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
* @Title: CoachPubController
* @Package com.nsu.controller.publicity;
* @Description: 教练员公示
* @author 曾绩平
* @date 2017-04-20
* @version V1.0
*/
@Controller
@RequestMapping("/info")
public class CoachPubController extends BaseController {

	private List<CoachPubBean> coachAll;
	@Resource
	private ICoachPubService coachService;
	
	/**
	 * 查询教练员信息
	 * @param
	 * @return String
	 */
	@RequestMapping(value = "/coachInfo", method = RequestMethod.GET)
	public String coachInfo(){
		return "/publicity/info_coach";
	}
	
	@RequestMapping(value = "/coachList", method = RequestMethod.POST)
	public void coachList(int pageNum, HttpServletResponse response) throws Exception {
		
        PageInfo<CoachPubBean> pageInfo=coachService.getCoachAllService(pageNum, 10);
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 根据教练员名查询教练员信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/searchCoach", method = RequestMethod.POST)
	public void searchCoach(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String search = request.getParameter("search");
		coachAll = coachService.getCoachByNameService(search);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("size", coachAll.size());
			result.put("coachAll", coachAll);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询教练员详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/coachDetail", method = RequestMethod.POST)
	public void coachDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String num = request.getParameter("num");
		CoachPubBean detail = coachService.getCoachDetailService(num);
		String team = coachService.getCoachTeamService(num);
		detail.setTeam(team);
		
		CoachPubBean score = coachService.getCoachScoreService(num);
		
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("detail", detail);
			result.put("score", score);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
}
