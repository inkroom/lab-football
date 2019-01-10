package com.nsu.controller.team;

import java.util.Map;

import javax.annotation.Resource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.team.TeamRegisterDO;
import com.nsu.common.Anonymous;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamRegisterService;
import com.nsu.util.V;
import com.nsu.util.jedis.JedisClient;

@Controller
@RequestMapping(value="/team")
public class TeamRegisterController extends BaseController implements Anonymous{

	@Resource(name="teamRegisterService")
	private ITeamRegisterService TeamRegisterService;
	@Autowired
    private JedisClient jedisClient;
	
	/**
	 * 球队管理员注册
	 * @author ljl
	 * @createDate 2017-04-11 13:43:10
	 * @param teamUserInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/team_register", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamRegister(TeamRegisterDO teamUserInfo) throws Exception {
      
		//从手机session中获取验证码
		String str = "";
		//验证验证码
		Map<String, String> map = V.verificationCode(jedisClient, 4, 1, teamUserInfo.getPhone(), null, teamUserInfo.getPhoneCheck());
		if(map.get("result").equals("1") == true){
			//验证信息
			str = TeamRegisterService.insertRegisterTeamManager(teamUserInfo, null);
		}else{
			//手机验证码错误
			str = map.get("resultMsg");
		}
		JSONObject resJosn = new JSONObject();
		resJosn.put("message", str);
		if(str.equals("success") == true){
			resJosn.put("status", 200);
		}else{
			resJosn.put("status", 500);
		}
		
      return resJosn.toString();
    }
	
	/**
	 * 球队管理员身份证唯一ajax验证
	 * @author ljl
	 * @createDate 2017-04-11 14:02:13
	 * @param idCard
	 * @return
	 */
	public String checkIDCardByAjax(String idCard){
		return checkIDCard(idCard)?"available":"unavailable";
	}
	
	/**
	 * 球队管理员身份证唯一验证
	 * @author ljl
	 * @createDate 2017-04-11 13:43:35
	 * @param idCard
	 * @return
	 */
	public boolean checkIDCard(String idCard){
		
		if(V.checkEmpty(idCard) == true){
			return true;
		}else{
			return TeamRegisterService.findTeanIDCardIsAvailable(idCard);
		}
	}
	
	
	
	
}
