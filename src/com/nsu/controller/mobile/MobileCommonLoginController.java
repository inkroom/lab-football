package com.nsu.controller.mobile;

import com.nsu.bean.player.ResultJson;
import com.nsu.controller.BaseController;
import com.nsu.service.coach.CoachLoginService;
import com.nsu.service.core.IMobileLoginService;
import com.nsu.service.organization.OrgLoginService;
import com.nsu.service.player.login.PlayerLoginService;
import com.nsu.service.site.SiteService;
import com.nsu.service.team.ITeamLoginService;
import com.nsu.util.RSAencrypt.MobileToken;
import com.nsu.util.VerifyUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/mobile/player")
public class MobileCommonLoginController extends BaseController {
	// 前台数据存放
	private Map<String, Object> map = new HashMap<>();
	// 后台数据
	private Map<String, Object> mapinfo = new HashMap<>();
	// 重定向登录地址
	private final String loginError = "用户名或密码错误";
	private final String passwdIsEmpty = "密码为空";
	private final String userIsEmpty = "用户名为空";
	private final String userIsNotFound = "该用户未注册";
	private final String systemError = "登录失败，请重新登录!";
	private final String logoutError = "退出失败";
	private final String loginErrorCode = "404";
	private final String loginSuccessCoe = "200";

	@Resource
	PlayerLoginService playerLoginService;

	@Resource(name = "teamLoginService")
	private ITeamLoginService teamLoginService;

	@Resource
	CoachLoginService coachLoginService;
	@Resource
	SiteService siteLoginService;

	@Resource
	private IMobileLoginService mobileLoginService;

	@Resource
	private OrgLoginService orgLoginService;

	/**
	 * 手机登录入口
	 *
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/login_judge")
	public ResultJson loginJudge(@RequestParam("forms") String forms) {
		log.info(forms + "    ------- ");
		JSONObject from = null;
		String username = null;
		String passwd = null;
		String pushInfo = null;
		String deviceInfo = null;
		String roleType = null;
		try {
			from = MobileToken.analysisParam(forms);
			log.info("form----------==========" + from);
			username = from.getString("username").replace("\n", ""); // 如果是 机构或者现场管理员 就是 A_PHONE
			passwd = from.getString("passwd");
			pushInfo = from.getString("pushInfo");
			deviceInfo = from.getString("deviceInfo");
			roleType = from.getString("type");
			log.info("type---->" + roleType);

		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultJson(loginErrorCode, systemError);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		if (!VerifyUtil.isNotEmpty(username)) {
			log.info(loginErrorCode + "-" + userIsEmpty);
			return new ResultJson(loginErrorCode, userIsEmpty);
		}
		if (!VerifyUtil.isNotEmpty(passwd)) {
			log.info(loginErrorCode + "-" + passwdIsEmpty);
			return new ResultJson(loginErrorCode, passwdIsEmpty);
		}
		String[] strings = userExist(username, passwd, roleType);
		if (strings == null) {
			log.info(loginErrorCode + "-" + userIsNotFound);
			return new ResultJson(loginErrorCode, userIsNotFound);
		}
		if (!pwdJudge(passwd)) {

			log.info(loginErrorCode + "-" + loginError);
			return new ResultJson(loginErrorCode, loginError);
		} else {
			// setSessionParameter(Constants.LOGIN_USER, mapinfo, session);
			// setSessionParameter(Constants.LOGIN_USER_ID, mapinfo.get("A_ID"),
			// session);
			// 0管理员，1球员，2教练，3机构, 4.球队, 5.现场管理员
			String token = null;
			try {
				if (mobileLoginService.updateDeviceInfo(strings[0],deviceInfo)){
					token = MobileToken.getAndroidToken(strings[1], passwd.substring(0, 9), roleType, strings[0], deviceInfo);
					username = strings[1];
				}else {
					return new ResultJson(loginErrorCode, systemError);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getCause());
				log.error(e.getMessage());
				log.error(e.getClass());

				return new ResultJson(loginErrorCode, systemError);
			}
			result.put("token", token);
			result.put("type", roleType);


			log.info("username   " + username + " password  " + passwd + " pushInfo  " + pushInfo + "  deviceInfo  "
					+ deviceInfo + "  roleType" + roleType);

			if (roleType.equals("1")) {
				log.info("*********球员登录********");
				try {
					result.putAll(playerLoginService.getUserInfo(username, pushInfo, deviceInfo));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResultJson(loginErrorCode, systemError);
				}

			} else if (roleType.equals("2")) {
				try {
					log.info("coach-----------------------");
					result.putAll(coachLoginService.getUserInfo(username, pushInfo, deviceInfo));
					log.info(result.toString()+"+++++++++++++++++");
				} catch (Exception e) {
					e.printStackTrace();
					return new ResultJson(loginErrorCode, systemError);
				}
			} else if (roleType.equals("3")) {
				// 机构
				try {

					log.info("deviceInfo---->"+deviceInfo);
					Map map1 = orgLoginService.getUserInfo(username, pushInfo, deviceInfo);

					result.putAll(map1);

				} catch (Exception e) {
					e.printStackTrace();
					return new ResultJson(loginErrorCode, systemError);
				}

			} else if (roleType.equals("4")) {
				// 球队
				try {
					log.info("*********球队登录********");
					result.putAll(teamLoginService.getUserInfo(username, pushInfo, deviceInfo));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResultJson(loginErrorCode, systemError);
				}
			} else if (roleType.equals("5")) {
				try {
					result.putAll(siteLoginService.getUserInfo(username, pushInfo, deviceInfo));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResultJson(loginErrorCode, systemError);
				}
			}
			return new ResultJson(loginSuccessCoe, result);
		}
	}

	public String[] userExist(String username, String passwd, String roleType) {
		huashiPrintln(username);
		try {
			map.put("username", username);
			map.put("password", passwd);
			mapinfo = mobileLoginService.getUserByUserName(username, roleType);
			if (mapinfo == null) {
				return null;
			} else {
				try {
					String aId = mapinfo.get("A_ID").toString();
					String aUsername = mapinfo.get("A_USERNAME").toString();
					huashiPrintln("A_ID = " + aId);
					String[] strings = new String[2];
					strings[0] = aId;
					strings[1] = aUsername;
					return strings;
				} catch (Exception e) {
					return null;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Boolean pwdJudge(String passwd) {
		return passwd.equals(mapinfo.get("A_PASSWORD").toString());
	}

	@PostMapping("/logout")
	@ResponseBody
	public ResultJson logout(@RequestParam("token") String token) {
		try {
			Map<String, Object> from = MobileToken.getTokenMap(token);
			String a_id = from.get("A_ID").toString();
			if (mobileLoginService.setDeviceInfo(a_id)) {
				return new ResultJson(loginSuccessCoe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultJson(loginSuccessCoe, logoutError);
	}
}
