package com.nsu.controller.team.mobile;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.TeamMobileResultBean;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamMobileService;
import com.nsu.util.V;
import com.nsu.util.RSAencrypt.MobileToken;

/**
 * 球队手机端获取赛事信息
 * @author ljl
 * @version 1.0
 * @createDate 2017-05-10 15:16:08
 */
@Controller
@RequestMapping(value="/mobile/team")
public class TeamMobileController extends BaseController{
	
	@Resource(name="teamMobileService") 
	ITeamMobileService teamMobileService;
	
	/**
	 * 获取球队比赛信息
	 * @author ljl
	 * @createDate 2017-05-10 15:24:24
	 * @param token
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/get_matches", produces="text/html;charset=UTF-8;")
	public String findTeamMatchUseInMobile(@RequestParam("token")String token){
		JSONObject json = new JSONObject();
		Map<String, Object> info = null;
		String aid = null;
		try{
			//解析token，得到用户ID
			info = MobileToken.getTokenMap(token);
			if(V.checkEmpty(info.get("A_ID")) == false){
				aid = info.get("A_ID").toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(V.checkEmpty(aid)){
			json.put("status", "404");
			json.put("msg", "查询失败");
		}else{
			//查询已经比赛完了的赛事
			List<TeamMobileResultBean> endMatch = teamMobileService.findTeamMatchInfo(aid);
			//查询正在进行中的比赛
			List<TeamMobileResultBean> inMatch = teamMobileService.findTeamMatchIsPlayingInfo(aid);
			json.put("inMatch", inMatch);
			json.put("endMatch", endMatch);
			json.put("status", "200");
		}
		log.info(json);
		return json.toString();
	}
}
