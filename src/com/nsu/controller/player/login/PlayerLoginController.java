package com.nsu.controller.player.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.nsu.common.Anonymous;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.bean.player.ResultJson;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.player.login.PlayerLoginService;
import com.nsu.util.FormatPattern;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.VerifyUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @Title: LoginController.java
 * @Package com.nsu.controller.player.login
 * @Description: 球员登录Controller
 * @author 侯松梁
 * @date 2017年4月10日 下午7:28:00
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerLoginController extends BaseController implements Anonymous {
	// 前台数据存放
	private Map<String, Object> map = new HashMap<>();
	// 后台数据
	private Map<String, Object> mapinfo = new HashMap<>();
	// 重定向登录地址
	private String url = "redirect:/player/login_view.html";
	@Resource
	PlayerLoginService playerLoginService;

	/**
	 * 进入登录界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login_view")
	public String toLogin(HttpSession session) {
		if (null != session.getAttribute(Constants.LOGIN_USER)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			String A_ID = map.get("A_ID").toString();
			try {
				mapinfo = playerLoginService.getUserNameById(A_ID);
				if (mapinfo != null) {
					if (mapinfo.get("A_USERNAME").toString() != null) {
						return "redirect:/player/player_index.html";
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		// 判断session中是否存在盐
		if (session.getAttribute(Constants.SALT_IN_SESSION) == null) {
			setSaltForSession(session);
		}
		return "/player/login/login";
	}

	/**
	 * 跳转主页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/player_index")
	public String toindex(Model model, HttpSession session) {
		if (null != session.getAttribute(Constants.LOGIN_USER)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
			String A_ID = map.get("A_ID").toString();
			try {
				mapinfo = playerLoginService.getUserNameById(A_ID);
				if (mapinfo != null) {
					if (mapinfo.get("A_USERNAME").toString() != null) {
						mapinfo = playerLoginService.getUserNameById(A_ID);
						mapinfo.put("A_USERNAME", InfoProtUtil.xorInfo(mapinfo.get("A_USERNAME").toString()));

						try {
							session.setAttribute("SMSPhone",InfoProtUtil.xorInfo(mapinfo.get("A_PHONE").toString()));
							session.setAttribute("SMSEmail",mapinfo.get("A_EMAIL").toString());
						}catch (Exception e){
							log.info("此异常不用管，第一次为空");
						}

						model.addAttribute("username", mapinfo);
						return "/player/player_index";
					}
				}
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}else{
			return "redirect:/player/login_view.html";
		}
		return "redirect:/player/login_view.html";
	}

	/**
	 * 登录验证 ---验证码验证
	 * 
	 * @param username
	 * @param p
	 * @param password
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login_judge")
	public String loginJudge(String username, String p, String password, RedirectAttributes redirectAttributes,
			HttpSession session) {
		if (!VerifyUtil.isNotEmpty(username) && !VerifyUtil.isNotEmpty(p)) {
			redirectAttributes.addFlashAttribute("info", "用户名或密码不能为空");
			return url;
		} else if (!VerifyUtil.isNotEmpty(password)) {
			redirectAttributes.addFlashAttribute("info", "验证码不能为空");
			return url;
		} else if (!password.equalsIgnoreCase(session.getAttribute(Constants.RANDOM_CODE).toString())) {
			log.info(session.getAttribute(Constants.RANDOM_CODE).toString());
			redirectAttributes.addFlashAttribute("info", "验证码错误");
			return url;
		} else {
			return playerExist(username, p, redirectAttributes, session);
		}
	}

	/**
	 * 
	 * @Description (放盐)
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/getSalt", produces = "text/html;charset=UTF-8")
	public String getSalt(HttpSession session) {
		if (session.getAttribute(Constants.SALT_IN_SESSION) == null) {
			setSaltForSession(session);
		}
		return session.getAttribute(Constants.SALT_IN_SESSION).toString();
	}

	/**
	 * 
	 * @Description (将对象转换成Json字符串)
	 * @param obj
	 * @return
	 */
	public String objToJsonString(ResultJson obj) {
		return JSONObject.fromObject(obj).toString();
	}

	/**
	 * 登录验证 ---判断用户是否存在
	 * 
	 * @param username
	 * @param p
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	public String playerExist(String username, String p, RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			log.info("----------------------------");
			map.put("username", InfoProtUtil.xorInfo(username));
			map.put("password", p);
			mapinfo = playerLoginService.getPlayer(map);
			if (mapinfo == null) {
				redirectAttributes.addFlashAttribute("info", "用户名或密码错误");
				return url;
			} else {
				return pwdJudge(username, p, redirectAttributes, session);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return url;
	}

	/**
	 * 
	 * @Description (登录验证 ---判断用户是否存在)
	 * @param username
	 * @param passwd
	 * @return
	 */
	public Boolean playerExist(String username, String passwd) {
		try {
			map.put("username", username);
			map.put("password", passwd);
			System.out.println(map.toString());
			mapinfo = playerLoginService.getPlayer(map);
			System.out.println(mapinfo);
			if (mapinfo == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return true;
	}

	/**
	 * 登录验证 ---验证密码是否正确
	 * 
	 * @param username
	 * @param p
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	public String pwdJudge(String username, String p, RedirectAttributes redirectAttributes, HttpSession session) {
		if (InfoProtUtil.comparePass(mapinfo.get("A_PASSWORD").toString(),
				session.getAttribute(Constants.SALT_IN_SESSION).toString(), p)) {
			setSessionParameter(Constants.LOGIN_USER, mapinfo, session);
			setSessionParameter(Constants.LOGIN_USER_ID, mapinfo.get("A_ID"), session);
			redirectAttributes.addFlashAttribute("info", "登录成功");
			try {
				playerLoginService.updateLoginTime(mapinfo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return "redirect:/player/player_index.html";
		} else {
			redirectAttributes.addFlashAttribute("info", "用户名或密码错误");
			return url;
		}
	}

	/**
	 * 退出
	 * 
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/logOut")
	public String logOut(HttpSession session, RedirectAttributes redirectAttributes) {
		session.removeAttribute(Constants.LOGIN_USER);
		session.removeAttribute(Constants.LOGIN_USER_ID);
		return "redirect:/player/login_view.html";
	}
}
