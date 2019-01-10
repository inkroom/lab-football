package com.nsu.controller.publicity;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.RaceInfoBean;
import com.nsu.controller.BaseController;
import com.nsu.service.publicty.IRaceInfoService;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
* @Title: RaceInfoController
* @Package com.nsu.controller.publicity;
* @Description: 赛程公示
* @author 曾绩平
* @date 2017-04-20
* @version V1.0
*/
@Controller
@RequestMapping("/info")
public class RaceInfoController extends BaseController {

	private List<RaceInfoBean> raceAll;
	@Resource
	private IRaceInfoService raceInfoService;
	
	/**
	 * 查询赛程信息
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/raceInfo", method = RequestMethod.GET)
	public String teamInfo() {
		return "/publicity/info_race";
	}
	
	@RequestMapping(value = "/raceList", method = RequestMethod.POST)
	public void raceList(int pageNum, HttpServletResponse response) throws Exception {
		
        PageInfo<RaceInfoBean> pageInfo=raceInfoService.getRaceAllService(pageNum, 10);
        JSONObject result = new JSONObject();
        try {
			result.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ResponseUtil.write(response, result);
	}
	
	/**
	 * 查询赛程详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/raceInfoDetail", method = RequestMethod.POST)
	public void gameInfoDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String num = request.getParameter("num");
		RaceInfoBean detail = new RaceInfoBean();
		detail = raceInfoService.getRaceDetailService(num);
		JSONObject result = new JSONObject();
		try {
			result.put("detail", detail);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 根据赛程名查询赛程信息
	 * @param search
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(value = "/searchRace", method = RequestMethod.POST)
	public void searchTeam(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String search = request.getParameter("search");
		raceAll = raceInfoService.getRaceByNameService(search);
		JSONObject result = new JSONObject();
		try {
			result.put("success", true);
			result.put("size", raceAll.size());
			result.put("raceAll", raceAll);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
	}
}
