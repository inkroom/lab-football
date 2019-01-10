package com.nsu.controller.player.system;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.player.PlayerTeamBean;
import com.nsu.bean.player.ResultJson;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.player.PlayerInfoService;
import com.nsu.service.player.PlayerTeamApplyService;
import com.nsu.util.V;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PlayerTeamApplyController
 * @Description (球员申请加入球队)
 * @author hm
 * @Date 2017年4月12日 下午3:19:30
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/player/system")
public class PlayerTeamApplyController extends BaseController {
	@Resource
	private PlayerTeamApplyService playerTeamApplyService;
	@Resource
	private ISelectMessageService iSelectMessageService;
	@Resource
	PlayerInfoService playerInfoService;
	private final String TEAM_ID_IS_EMPTY = "球队码为空";
	private final String TEAM_IS_APPLIED = "已申请过该球队";
	private final String TEAM_IS_NOT_ALLOW_APPLY = "该级别球队已经发起过申请";
	private final String APPLY_SUCCESS = "申请成功，请等待审核";
	private final String TEAM_IS_NOT_FOUND = "未找到该球队";
	private final String TEAM_NUM_FORMAT_ERROR = "球队码错误，请重新输入";
	private final Integer PAGE_SIZE = 5;

	/**
	 * 进入球队申请页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/player_teamapply")
	public String toTeam(Model model, HttpSession session, Integer pageNum) {
		return "/player/player_system/player_teamapply";
	}

	/**
	 * 进入球队申请页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/player_teamapplys", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String toTeams(Model model, HttpSession session, Integer pageNum) {
		JSONObject jsonObject = new JSONObject();
		String userId = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		try {
			PageInfo<PlayerTeamBean> result = playerTeamApplyService.getTeamApplyList(userId, pageNum, PAGE_SIZE);
			jsonObject.put("pageInfo", result);
			log.debug(result.toString());
			model.addAttribute("team", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * @Description (向球队发起申请)
	 * @param team_id
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/applay/team", produces = "text/html;charset=UTF-8")
	public String applyTeam(String team_id, HttpSession session) {
		String userId = session.getAttribute(Constants.LOGIN_USER_ID).toString();
		if (V.checkEmpty(team_id)) {
			return JSONObject.fromObject(new ResultJson("404", TEAM_ID_IS_EMPTY)).toString();
		}
		if (isApplied(team_id, userId)) {
			return JSONObject.fromObject(new ResultJson("404", TEAM_IS_APPLIED)).toString();
		}
		if (!allowApply(team_id, userId)) {
			return JSONObject.fromObject(new ResultJson("404", TEAM_IS_NOT_ALLOW_APPLY)).toString();
		}
		try {
			if (playerTeamApplyService.applyTeam(userId, team_id)) {
				Map<String,Object> message = new HashMap<>();
				message.put("PS_RECEIVE",playerTeamApplyService.getTeamAId(team_id));
				message.put("PS_SEND_ID",userId);
				message.put("PS_TITLE","球员申请");
				message.put("PS_TEXT","球员："+playerInfoService.getPlayerInfo(userId).get("A_NAME")+"<br>"+"申请加入球队,"+"请进入球队管理中查看。");
				iSelectMessageService.insertPersonMessage(message);
				return JSONObject.fromObject(new ResultJson("200", APPLY_SUCCESS)).toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}
	/**
	 * 
	 * @Description (通过球队🐎搜索球队)
	 * @param team_num
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/applay/getTeamInfo", produces = "text/html;charset=UTF-8")
	public String getTeamInfo(String team_num) {
		if (V.checkEmpty(team_num)) {
			return JSONObject.fromObject(new ResultJson("404", TEAM_ID_IS_EMPTY)).toString();
		}
		if (!checkCHSAndENAndNumber(team_num, 10)) {
			return JSONObject.fromObject(new ResultJson("404", TEAM_NUM_FORMAT_ERROR)).toString();
		}
		try {
			PlayerTeamBean playerTeamBean = playerTeamApplyService.getTeamInfo(team_num);
			log.debug(playerTeamBean.toString());
			return JSONObject.fromObject(new ResultJson("200", "success", playerTeamBean)).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(new ResultJson("404", TEAM_IS_NOT_FOUND)).toString();
	}

	/**
	 * 
	 * @Description (判断该球队是否已经申请过)
	 * @param team_id
	 * @param a_id
	 * @return true已经申请过
	 */
	public boolean isApplied(String team_id, String a_id) {
		try {
			return playerTeamApplyService.isApplied(team_id, a_id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @Description (判断是否允许申请该级别球队)
	 * @param team_id
	 * @param a_id
	 * @return true允许申请，false不允许申请
	 */
	public boolean allowApply(String team_id, String a_id) {
		try {
			return playerTeamApplyService.allowApply(team_id, a_id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return true;
		}
	}

	/**
	 * 
	 * @Description (校验球队码是否符合要求)
	 * @param str
	 * @param length
	 * @return
	 */
	public boolean checkCHSAndENAndNumber(String str, int length) {
//		String regExpSpace = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{10}$";
		String regExpSpace=	"^[\\da-zA-Z]{10}$";
		if (str != null && str.length() > 0 && str.length() <= length) {
			if (V.regularVerification(regExpSpace, str) == true) {
				return true;
			}
		}
		return false;
	}
}
