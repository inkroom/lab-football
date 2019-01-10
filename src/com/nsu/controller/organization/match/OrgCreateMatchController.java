package com.nsu.controller.organization.match;

import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.service.organization.IOrgCreateMatchService;
import com.nsu.util.V;
import com.nsu.util.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OrgCreateMatchController
 * @Description: 机构发布赛事
 * @Date: 2017/4/29 12:15
 * @author: Xiaoyuxuan
 */
@Controller
@RequestMapping("/org")
public class OrgCreateMatchController extends OrgMatchUtil {
	
	@Resource(name="iOrgCreateMatchService")
	private IOrgCreateMatchService iOrgCreateMatchService;
	
	private static final String PAGE_TO_CREATE_MATCH = "/organization/organization_system/org_add_match";
	private static final String REDIRECT_TO_LOGIN= "redirect:/org_login/to_login.html";
	
	private Map<String, Object> userMap;
	private Map<String, String> matchInfo = new HashMap<String, String>();
	private int msg_type = 1;
	private String msg_title;
	private String msg_text;
	private String Msg;
	
	/**
	 * 进入到创建赛事的页面
	 * @return
	 */
	@InterceptorAnno(createToken = true)
	@RequestMapping("/to_create_match")
	public String pageToCreateMatch(HttpSession session, RedirectAttributes redirectAttributes,
									@ModelAttribute("msg") String msg, Model model){
		if(isUserExist(session, redirectAttributes, getUserMap(session))){
			return REDIRECT_TO_LOGIN;
		}
		model.addAttribute("createMatch_msg",msg);
		return PAGE_TO_CREATE_MATCH;
	}
	/**
	 * 发布赛事
	 * @param match_name
	 * @param person_num
	 * @param person_sex
	 * @param match_big_level
	 * @param match_level
	 * @param match_startDate
	 * @param match_endDate
	 * @param enroll_startDate
	 * @param enroll_endDate
	 * @param session
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@InterceptorAnno(checkToken = true, removeToken = true, isAjax = false)
	@RequestMapping("/create_match")
	public String createMatch(String match_name, String person_num, String person_sex, 
			String match_big_level, String match_level, String match_startDate, 
			String match_endDate, String enroll_startDate, String enroll_endDate, 
			HttpSession session, Model model, RedirectAttributes redirectAttributes){
		if(isUserExist(session, redirectAttributes, getUserMap(session))){
			return REDIRECT_TO_LOGIN;
		}
		matchInfo = putMatchInfoToMap(match_name, person_num, person_sex, match_big_level, match_level, 
				match_startDate, match_endDate, enroll_startDate, enroll_endDate);
		boolean mask = verificationMatchInfo(matchInfo);
		userMap = getUserMap(session);
		long o_id = Long.parseLong(userMap.get("ORG_ID").toString());
		try {
			Msg = addMatch(match_name, match_startDate, match_endDate, 
					enroll_startDate, enroll_endDate, mask, o_id, matchInfo);
			//model.addAttribute("createMatch_msg", Msg);
		} catch (Exception e) {
			disposeCreateMatchException(model, e);
		}
		redirectAttributes.addFlashAttribute("msg",Msg);
		return "redirect:/org/to_create_match.html";
	}

	/**
	 * 处理创建赛事时的异常信息
	 * @param model
	 * @param e
	 */
	private void disposeCreateMatchException(Model model, Exception e) {
		Msg = "系统错误，请稍后再试";
		model.addAttribute("createMatch_msg", Msg);
		e.printStackTrace();
	}
	
	/**
	 * 举办一场赛事
	 * @param match_name
	 * @param match_startDate
	 * @param match_endDate
	 * @param enroll_startDate
	 * @param enroll_endDate
	 * @param mask
	 * @param o_id
	 * @throws Exception
	 */
	private String addMatch(String match_name, String match_startDate, String match_endDate, 
			String enroll_startDate, String enroll_endDate, boolean mask, long o_id, Map<String,String> map) throws Exception {
		if(mask && iOrgCreateMatchService.isDuplicationMatchName(o_id, match_name)){
			Msg = addMatchToDatabase(match_name, match_startDate, match_endDate, 
					enroll_startDate, enroll_endDate, o_id, map);
		} else if(!mask){
			Msg = "填写格式有误，请重新填写";
		} else if(!iOrgCreateMatchService.isDuplicationMatchName(o_id, match_name)){
			Msg = "赛事名称已存在，请重新填写赛事名称";
		} else{
			Msg = "系统错误，请稍后再试";
		}
		return Msg;
	}
	
	/**
	 * 在各种校验成功之后，在赛事和消息表中插入赛事相关信息
	 * @param match_name
	 * @param match_startDate
	 * @param match_endDate
	 * @param enroll_startDate
	 * @param enroll_endDate
	 * @param o_id
	 * @return
	 * @throws Exception
	 */
	private String addMatchToDatabase(String match_name, String match_startDate, String match_endDate, 
			String enroll_startDate, String enroll_endDate, long o_id, Map<String, String> map) throws Exception {
		msg_title = userMap.get("ORG_NAME").toString()+"举办"+match_name;
		msg_text = getMsgText(match_name, match_startDate, match_endDate, enroll_startDate, enroll_endDate, map);
		int a_id = Integer.parseInt(userMap.get("A_ID").toString());
		boolean result = iOrgCreateMatchService.addMatchInfo(o_id, matchInfo, msg_type, 
				msg_title, msg_text, a_id);
		if(result){
			Msg = "添加赛事成功";
		} else{
			Msg = "添加赛事失败";
		}
		return Msg;
	}

	/**
	 * 拼接发布赛事消息的详细内容
	 * @param match_name
	 * @param match_startDate
	 * @param match_endDate
	 * @param enroll_startDate
	 * @param enroll_endDate
	 * @return
	 */
	private String getMsgText(String match_name, String match_startDate, String match_endDate, 
			String enroll_startDate, String enroll_endDate, Map<String, String> map) {
        Map<String, String> info = getTransformation(map);
        return userMap.get("ORG_NAME").toString()+"举办"+"“"+match_name+"”"+
                ".<br>赛事名称:"+match_name+
                ".<br>发布时间:"+enroll_startDate+
                ".<br>赛制:"+info.get("person_num")+
                ".<br>级别:"+info.get("match_big_level")+
                ".<br>性别:"+info.get("person_sex")+
                ".<br>组别:"+info.get("match_level")+
                ".<br>报名时间:"+enroll_startDate+"至"+enroll_endDate+
                ".<br>赛事时间:"+match_startDate+"至"+match_endDate+".";
	}
	
	/**
	 * 校验map中的数据是否合法
	 * @param matchInfo
	 * @return
	 */
	private boolean verificationMatchInfo(Map<String, String> matchInfo) {
		boolean mask = false;
		if(mapIsNoEmpty(matchInfo)){
			boolean name = V.checkCHSAndENAndNumber(matchInfo.get("match_name"), 20);
			if(name){
				mask = true;
			}
		}
		return mask;
	}
	
	/**
	 * 校验map中的数据是否含有空数据
	 * @param matchInfo
	 * @return
	 */
	private boolean mapIsNoEmpty(Map<String, String> matchInfo) {
		boolean mask = true;
		Object[] keys = matchInfo.keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			if(!VerifyUtil.isNotEmpty(matchInfo.get(keys[i]))){
				mask = false;
				break;
			}
		}
		return mask;
	}
}
