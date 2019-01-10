package com.nsu.controller.organization.match;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.organization.CompetitionBean;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.service.organization.IOrgModifyMatchService;
import com.nsu.util.ResponseUtil;
import com.nsu.util.V;
import com.nsu.util.VerifyUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrgModifyMatchController
 * @Description: 机构修改可以修改和删除的赛事
 * @Date: 2017/4/29 12:15
 * @author: Xiaoyuxuan
 */
@Controller
@RequestMapping("/org")
public class OrgModifyMatchController extends OrgMatchUtil {
	
	@Resource(name="iOrgModifyMatchService")
	private IOrgModifyMatchService iOrgModifyMatchService;
	
	private static final String PAGE_TO_LIST_MATCH = "/organization/organization_system/org_list_modifiable_match";
	private static final String PAGE_TO_MODIFY_MATCH = "/organization/organization_system/org_modify_match";
	private static final String REDIRECT_TO_LOGIN= "redirect:/org_login/to_login.html";

//	private JSONObject resultJson = new JSONObject();

	private Map<String, Object> userMap;
	private List<Map<String, Object>> matchList;
	private Map<String, String> matchInfo = new HashMap<String, String>();
	private String Msg;
	private List<Map<String, Object>>  select_Match_List;
	private String person_num;
	private String person_sex;
	private String match_level;
	private String match_big_level;
	private int msg_type = 1;
	private String msg_title;
	private String msg_text;
	
	/**
	 * 进入到修改赛事的页面
	 * @return
	 */
	@InterceptorAnno(createToken = true)
	@RequestMapping(value = "/to_modify_match_view")
	public String toModify_view() {
		return PAGE_TO_LIST_MATCH;
	}

	/**
	 * 显示数据
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/to_modify_match",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Map<String, Object>> findQueryAll(Integer pageNum,HttpSession session){
		PageInfo<Map<String, Object>> pageInfo = null;
        try {
            /*从机构登录用户中获取ORG_ID*/
            userMap = getUserMap(session);
            long o_id = Long.parseLong(userMap.get("ORG_ID").toString());
            pageInfo = iOrgModifyMatchService.getPageEffectiveMatchList(pageNum, o_id);
            
            log.info(pageInfo);
            JSONObject object = new JSONObject();
            object.put("pageInfo", pageInfo);
			return pageInfo;
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return pageInfo;
    }

	/**
	 * 显示待修改信息
	 * @param match_id
	 * @param response
	 * @param model
	 * @return
	 */
	@InterceptorAnno(createToken = true, isRestful = true)
	@RequestMapping("/{match_id}/modify_match")
	public String modifyMatch(@PathVariable String match_id, HttpServletResponse response, Model model){
		JSONObject resultJson = new JSONObject();
		if(!VerifyUtil.isNotEmpty(match_id)){
			resultJson.put("modifyMatch_msg", "温馨提示：修改失败!");
			sendJsonResult(response, resultJson);
			return PAGE_TO_LIST_MATCH;
		}
		int com_id = Integer.parseInt(match_id);
		try {
			Map<String, Object> matchMap = iOrgModifyMatchService.getMatchByMatchId(com_id);
			log.info("比赛详情："+matchMap);
			person_num = getPersonNum(Integer.parseInt(matchMap.get("COM_GROUNP").toString()));
			person_sex = getPersonSex(Integer.parseInt(matchMap.get("COM_TYPE").toString()));
			match_level = getMatchLevel(Integer.parseInt(matchMap.get("COM_LEVEL").toString()));
			match_big_level = getMatchBigLevel(Integer.parseInt(matchMap.get("COM_BIG_LEVEL").toString()));
			matchMap.put("person_num", person_num);
			matchMap.put("person_sex", person_sex);
			matchMap.put("match_level", match_level);
			matchMap.put("match_big_level", match_big_level);
			model.addAttribute("matchMap", matchMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PAGE_TO_MODIFY_MATCH;
	}

	/**
	 * 修改赛事信息
	 * @param com_id
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
	 * @param redirectAttributes
	 * @return
	 */
	@InterceptorAnno(checkToken = true, removeToken = true, isAjax = false, isRestful = true)
	@RequestMapping("/{com_id}/submit_modify")
	public String submitModify(@PathVariable int com_id, String old_match_name, String match_name, String person_num, String person_sex,
		String match_big_level, String match_level, String match_startDate, String match_endDate,
		String enroll_startDate, String enroll_endDate, HttpSession session, RedirectAttributes redirectAttributes){
		boolean mask = verificationMatchName(match_name);
		if(mask) {
			matchInfo = putMatchInfoToMap(match_name, person_num, person_sex, match_big_level, match_level,
				match_startDate, match_endDate, enroll_startDate, enroll_endDate);
			msg_title = userMap.get("ORG_NAME").toString()+"修改"+old_match_name;
			msg_text = getMsgText(old_match_name, match_name, match_startDate, match_endDate, enroll_startDate, enroll_endDate, matchInfo);
			int a_id = Integer.parseInt(userMap.get("A_ID").toString());
			Msg = addMatchToDatabase(matchInfo,com_id,msg_text,msg_title,a_id);
		} else {
			Msg = "填写格式有误，请重新填写";
		}
		log.info(Msg);
		redirectAttributes.addFlashAttribute("msg", Msg);
		return "redirect:/org/"+com_id+"/modify_match.action";
	}

	/**
	 * 修改详情
	 * @param matchInfo
	 * @param com_id
	 * @param msg_text
	 * @param msg_title
	 * @param a_id
	 * @return
	 */
	private String addMatchToDatabase(Map<String, String> matchInfo, 
			int com_id, String msg_text, String msg_title, int a_id) {
		long o_id = Long.parseLong(userMap.get("ORG_ID").toString());
		String info = "";
		try {
			if (!iOrgModifyMatchService.isDuplicationMatchName(o_id, matchInfo.get("match_name"),com_id)) {
				info = "赛事名称已存在，请重新填写赛事名称";
			}else if (!iOrgModifyMatchService.isChangeMatch(matchInfo, com_id)) {
				info = "请选择要修改的项";
			} else if (iOrgModifyMatchService.modifyMatchInfo(com_id,o_id, matchInfo, msg_type, msg_title, msg_text, a_id)) {
				info = "修改赛事成功";
			} else {
				info = "修改赛事失败";
			}
		} catch (Exception e) {
			info = "系统错误，请稍后重试";
			log.error(e.getMessage());
		}
		return info;
	}
	
	/**
	 * 搜素赛事
	 * @param match_name
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/selectmatch", method = RequestMethod.POST)
    public void selectByMatchName1(@RequestParam(value="match_name")String match_name,HttpSession session, HttpServletResponse response){
		JSONObject object = new JSONObject();
		
		try {
			userMap = getUserMap(session);
			long o_id = Long.parseLong(userMap.get("ORG_ID").toString());
			
            select_Match_List=iOrgModifyMatchService.selectByMatchName(match_name,o_id);
            
            if(select_Match_List.size()==0){
            	object.put("msg", "没有此数据");
            	ResponseUtil.write(response, object);
            }else {
            	object.put("info", select_Match_List);
            	ResponseUtil.write(response, object);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			object.put("msg", "系统错误，请稍后重试");
        	try {
				ResponseUtil.write(response, object);
			} catch (Exception e1) {
				log.error(e.getMessage());
			}
		}
    }
	/**
	 * 校验赛事名称填写是否符合规范
	 * @param match_name
	 * @return
	 */
	private boolean verificationMatchName(String match_name) {
		boolean mask = false;
		if( V.checkCHSAndENAndNumber(match_name, 20)){
			mask = true;
		}
		return mask;
	}

	/**
	 * 将数据库中的person_num转换成前台识别的
	 * @param person_num
	 */
	private String getPersonNum(int person_num) {
		String personNum = "";
		if(person_num == 1){
			personNum = "personNum_five";
        } else if(person_num == 2){
			personNum = "personNum_seven";
        } else if(person_num == 3){
			personNum = "personNum_eleven";
        } else if(person_num == 4){
			personNum = "personNum_no";
        }
        return personNum;
	}

	/**
	 * 将数据库中的person_sex转换成前台识别的
	 * @param person_sex
	 */
	private String getPersonSex(int person_sex) {
		String personSex = "";
		if(person_sex == 1){
			personSex = "personSex_male";
        } else if(person_sex == 2){
			personSex = "personSex_female";
        } else if(person_sex == 3){
			personSex = "personSex_no";
        }
        return personSex;
	}

	/**
	 * 将数据库中的match_level转换成前台识别的
	 * @param match_level
	 */
	private String getMatchLevel(int match_level) {
		String matchLevel = "";
		if(match_level == 1){
			matchLevel = "matchLevel_primarySchool";
        } else if(match_level == 2){
			matchLevel = "matchLevel_juniorSchool";
        } else if(match_level == 3){
			matchLevel = "matchLevel_highSchool";
        } else if(match_level == 4){
			matchLevel = "matchLevel_university";
        } else if(match_level == 5){
			matchLevel = "matchLevel_no";
        }
        return matchLevel;
	}

	/**
	 * 将数据库中的match_big_level转换成前台识别的
	 * @param match_big_level
	 * @return
	 */
	private String getMatchBigLevel(int match_big_level) {
		String matchBigLevel = "";
		if(match_big_level == 1){
			matchBigLevel = "matchBigLevel_province";
		} else if(match_big_level == 2){
			matchBigLevel = "matchBigLevel_city";
		} else if(match_big_level == 3){
			matchBigLevel = "matchBigLevel_county";
		} else if(match_big_level == 4){
			matchBigLevel = "matchBigLevel_school";
		} else if(match_big_level == 5){
			matchBigLevel = "matchBigLevel_no";
		}
		return matchBigLevel;
	}
	/**
	 * 将选中的赛事删除
	 * @param match_id
	 * @param response
	 * @return
	 */
	@InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
	@RequestMapping("/delete_match")
	public String deleteMatch(String match_name, String match_id, HttpServletResponse response){
		JSONObject resultJson = new JSONObject();
		if(!VerifyUtil.isNotEmpty(match_id)){
			resultJson.put("deleteMatch_msg", "温馨提示：删除失败!");
			sendJsonResult(response, resultJson);
			return null;
		}
		int com_id = Integer.parseInt(match_id);
		long o_id = Long.parseLong(userMap.get("ORG_ID").toString());
		int a_id = Integer.parseInt(userMap.get("A_ID").toString());
		msg_title = "赛事取消";
		msg_text = userMap.get("ORG_NAME").toString()+"取消了"+"“"+match_name+"”"+"赛事";
		try {
			int count = iOrgModifyMatchService.getScheduleCount(match_id);
			if (count > 0) {
				resultJson.put("deleteMatch_msg_error", "温馨提示：删除失败! 因已有赛程开始");
			} else {
				int result = iOrgModifyMatchService.deleteMatch(com_id, o_id, msg_type, msg_title, msg_text, a_id);
				if(result == 1){
					resultJson.put("deleteMatch_msg", "温馨提示：删除成功!");
				} else{
					resultJson.put("deleteMatch_msg_error", "温馨提示：删除失败!");
				}
			}
			sendJsonResult(response, resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送json格式的数据
	 * @param response
	 * @param result
	 */
	public void sendJsonResult(HttpServletResponse response, JSONObject result){
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			System.out.printf("jsonResult出错了！");
			e.printStackTrace();
		}
	}
	/**
	 * 处理编辑赛事的异常信息
	 * @param model
	 * @param e
	 */
	private void disposeModifyMatchException(Model model, Exception e) {
		model.addAttribute("modifyMatch_msg", Msg);
		e.printStackTrace();
	}
	
	@RequestMapping("/modfiy_match")
	public String modifyMatch(HttpSession session, RedirectAttributes redirectAttributes){
		userMap = getUserMap(session);
		if(userMap == null || userMap.isEmpty()){
			Msg = "登录失效，请重新登录";
			setFlashParameter("modifyMatch_msg",Msg,redirectAttributes);
			return REDIRECT_TO_LOGIN;
		}
		
		return PAGE_TO_LIST_MATCH;
	}

	/**
	 * 拼接发布赛事消息的详细内容
	 * @param old_match_name
	 * @param match_name
	 * @param match_startDate
	 * @param match_endDate
	 * @param enroll_startDate
	 * @param enroll_endDate
	 * @param map
	 * @return
	 */
	private String getMsgText(String old_match_name, String match_name, String match_startDate, String match_endDate, 
			String enroll_startDate, String enroll_endDate, Map<String, String> map) {
		Map<String, String> info = getTransformation(map);
		return userMap.get("ORG_NAME").toString()+"修改"+"“"+old_match_name+"”"+"信息"+
				".<br>赛事名称:"+match_name+
				".<br>发布时间:"+enroll_startDate+
				".<br>赛制:"+info.get("person_num")+
				".<br>级别:"+info.get("match_big_level")+
				".<br>性别:"+info.get("person_sex")+
				".<br>组别:"+info.get("match_level")+
				".<br>报名时间:"+enroll_startDate+"至"+enroll_endDate+
				".<br>赛事时间:"+match_startDate+"至"+match_endDate+".";
	}
	
}
