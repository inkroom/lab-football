package com.nsu.controller.team;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.bean.team.TeamRegisterDO;
import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.team.ITeamLoginService;
import com.nsu.service.team.ITeamRegisterService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.V;

/**
 * 球队管理员登录
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 13:59:15
 */
@Controller
@RequestMapping(value="/team")
public class TeamLoginController extends BaseController implements Anonymous{

	@Resource(name="teamLoginService")
	private ITeamLoginService teamLoginService;

	@Resource(name="teamRegisterService")
	private ITeamRegisterService TeamRegisterService;

	/**
	 * 球队管理员登录进入页面
	 * @author ljl
	 * @createDate 2017-04-10 14:02:17
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login_view")
	public String loginView(HttpSession session,Model model,TeamRegisterDO teamRegisterDO){
		//设置加密的盐
		//判断是否存在盐
		Object salt = session.getAttribute(Constants.SALT_IN_SESSION);
		if(V.checkEmpty(salt)){
			setSaltForSession(session);
		}

		model.addAttribute("user",teamRegisterDO.getUser());
		return TeamUtil.TEAM_LOGING_PAGE;
	}
	/**
	 * 球队管理员主页
	 * @author ljl
	 * @createDate 2017-04-11 19:39:54
	 * @return
	 */
	@RequestMapping(value="/team_index_view")
	public String teamIndexView(HttpSession session, RedirectAttributes redirectAttributes){

		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);

		if(userMap == null || userMap.size()==0 || V.checkEmpty(userMap.get("A_ID")) == true
				|| V.checkEmpty(userMap.get("USERNAME")) == true){

			redirectAttributes.addFlashAttribute("Msg", "登录会话失效，请重新登录");
			return TeamUtil.REDIRECT_TEAM_LOGING_PAGE;
		}
		//防止用户已经完善了信息，还在不停的弹出修改框
		if(V.checkEmpty(userMap.get("infoImporveLevel")) || checkTeamImporvedinfo(userMap) > 0){
			String username = InfoProtUtil.xorInfo(userMap.get("USERNAME").toString());
			userMap = teamLoginService.findAccountInfo(username);

			if(userMap==null || userMap.size()==0){
				redirectAttributes.addFlashAttribute("Msg", "登录会话失效，请重新登录");
				return TeamUtil.REDIRECT_TEAM_LOGING_PAGE;
			}

			int improvedinfoType = checkTeamImporvedinfo(userMap);
			//未绑定邮箱
			if(improvedinfoType == 1){
				session.setAttribute("needEmail", 1);
			}

			userMap.remove("USERNAME");
			userMap.remove("PASSWORD");
			userMap.put("USERNAME", InfoProtUtil.xorInfo(username));
			userMap.put("infoImporveLevel", improvedinfoType);

		}

		session.removeAttribute(Constants.LOGIN_USER);
		session.setAttribute(Constants.LOGIN_USER, userMap);
		log.info("球队登录用户session信息："+userMap);
		return TeamUtil.TEAM_INDEX_PAGE;
	}

	/**
	 * 跳转消息页面
	 * @return
	 */
	@RequestMapping(value="/message-all")
	public String loginView(){
		return "/message/message-all";
	}
	/**
	 * 球队管理员登录信息校验
	 * @author ljl
	 * @createDate 2017-04-10 14:09:38
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login_check")
	public String loginCheck(String username, String password, String code, Model model
			, HttpSession session, RedirectAttributes redirectAttributes){

		boolean isCorrect = true;
		Map<String, Object> userMap = null;
		if(V.checkEmpty(session.getAttribute(Constants.RANDOM_CODE)) == true){
			if(V.checkEmpty(session.getAttribute("Msg")) == false){
				session.removeAttribute("Msg");
			}
			return TeamUtil.REDIRECT_TEAM_LOGING_PAGE;
		}
		//获取服务端验证码
		String codeServer = session.getAttribute(Constants.RANDOM_CODE).toString();
		//校验验证码
		if(V.checkEmpty(code)==true || code.trim().equals(codeServer)==false){

			redirectAttributes.addFlashAttribute("Msg", "验证码错误");
			isCorrect = false;
		}else{
			if(V.checkEmpty(username) == true || V.checkEmpty(password) == true){

				redirectAttributes.addFlashAttribute("Msg", "请填写用户名和密码");
				log.info("用户名或密码没填写");
				isCorrect = false;
			}else{
				//查询数据的值
				username = username.trim();
				userMap = teamLoginService.findAccountInfo(InfoProtUtil.xorInfo(username));
				log.info("用户数据："+userMap);
				//校验用户名和密码
				if(userMap==null || userMap.isEmpty()){
					redirectAttributes.addFlashAttribute("Msg", "用户名或密码错误");
					isCorrect = false;
					log.info("无用户信息");
				}else{
					if(V.checkEmpty(userMap.get("A_ID"))){
						redirectAttributes.addFlashAttribute("Msg", "用户数据异常,请联系管理员");
						isCorrect = false;
						log.info("数据异常,没有A_ID");
					}else{
						if(V.checkEmpty(session.getAttribute(Constants.SALT_IN_SESSION)) || V.checkEmpty(userMap.get("PASSWORD")) ||!InfoProtUtil.comparePass(userMap.get("PASSWORD").toString(),
								session.getAttribute(Constants.SALT_IN_SESSION).toString(), password)
								){
							redirectAttributes.addFlashAttribute("Msg", "用户名或密码错误");
							isCorrect = false;
							log.info("用户名或密码错误");
						}
					}

				}
			}
		}

		if(isCorrect){
			//合法，进入球员主页
			userMap.remove("PASSWORD");
			String account = InfoProtUtil.xorInfo(userMap.get("USERNAME").toString());
			userMap.remove("USERNAME");
			userMap.put("USERNAME", account);
			session.setAttribute(Constants.LOGIN_USER, userMap);
			session.setAttribute("SMSPhone",account);
			try {
				session.setAttribute("SMSEmail",userMap.get("email").toString());
			}catch (Exception e){
				log.error("此异常不用去管，第一次登录的为空");
			}
			if(!V.checkEmpty(session.getAttribute("Msg")) ){
				session.removeAttribute("Msg");
			}

			return TeamUtil.TEAM_INDEX_HTML;
		}else{
			//非法，返回登录界面
			redirectAttributes.addFlashAttribute("username", username);
			//刷新加密的盐
//			setSaltForSession(session);
			return TeamUtil.REDIRECT_TEAM_LOGING_PAGE;
		}
	}
	/**
	 * 球队管理员登出
	 * @author ljl
	 * @createDate 2017-04-10 20:41:48
	 * @return
	 */
	@RequestMapping(value="/login_out")
	public String teamLoginOut(HttpSession session){
		//session.invalidate();
		session.removeAttribute(Constants.LOGIN_USER);
		session.removeAttribute("needEmail");
		return TeamUtil.REDIRECT_TEAM_LOGING_PAGE;
	}

	/**
	 * 校验球队信息是否完善
	 * @author ljl
	 * @createDate 2017-04-28 09:21:17
	 * @param map
	 * @return
	 */
	public int checkTeamImporvedinfo(Map<String, Object> map){
		if(V.checkEmpty(map.get("email"))){
			return 1;
		}
		if(V.checkEmpty(map.get("aName")) || V.checkEmpty(map.get("leader"))){
			return 2;
		}
		if(V.checkEmpty(map.get("TEAM_BADGE"))){
			return 3;
		}
		return 0;
	}


}