package com.nsu.controller.organization.application;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.bean.index.AjaxBean;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.organization.ITeamApplicationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
* @ClassName: ApplicationController 
* @Description: 机构申请管理的
* @author 严涛
* @date 2017年4月17日 下午3:29:58 
*
 */
@Controller
@RequestMapping(value = "/org")
public class TeamApplicationController extends BaseController{
	@Resource
    private ITeamApplicationService iTeamApplicationService;
	@Resource(name = "SelectMessage")
	ISelectMessageService SelectMessage;
	private String OrgApplications = "/organization/organization_system/team_application";
	private Map<Object,Object> map1 = new HashMap<>();//保存机构ID
	private List<Map<Object, Object>> message = new ArrayList<>();//保存球队信息
	private long team_id;//球队ID
	private long org_id;//机构ID
	private int A_ID;//球队A_ID
	
		@RequestMapping(value = "/team_application")
		public String getApplications(){
			return OrgApplications;
		} 
		
		 /**
		  * 
		 * @Title: selectTeamID 
		 * @Description: 根据球队名称查球队ID
		 * @param     设定文件 
		 * @return void    返回类型 
		 * @throws
		  */
		 @RequestMapping(value = "/seeTeamAllApplicattions" , produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
		 @ResponseBody
		 public String selectTeamID(HttpSession session, Model model, String name, long TEAM_ID){
			 	List<Map<String, Object>> playerList;//存储球员信息
			 	Map<String, Object> map ;//存储球队信息
			 	JSONObject jsonArray = new JSONObject();
			 	JSONArray jsonstr;
			 	JSONArray jsonplayer;
			 	team_id = TEAM_ID;
			try {
				map = iTeamApplicationService.findTeamMessage(team_id);//球队信息
				playerList = iTeamApplicationService.findPlayerMessage(team_id);//球员信息
				jsonstr = JSONArray.fromObject(map);
				jsonplayer = JSONArray.fromObject(playerList);
							
				jsonArray.put("jsonstr",jsonstr );
				jsonArray.put("jsonplayer",jsonplayer);

				AjaxBean ajax = new AjaxBean();
				ajax.setStatus("200");
				ajax.put("jsonstr",map);
				ajax.put("jsonplayer",playerList);
//				return ajax;
					return jsonArray.toString();
			} catch (Exception e) {
				AjaxBean ajaxBean = new AjaxBean();
				ajaxBean.setStatus("500");
				ajaxBean.setMsg("系统异常，请稍后再试！");
//				model.addAttribute("Msg", );
				e.printStackTrace();
//				return ajaxBean;
				return "{\"msg\":\"系统异常，请稍后再试！\"}";
			}
		 }
	
	 @RequestMapping(value="/list1")
	    public void findAll(Integer pageNum,HttpServletResponse response,HttpSession session){
	        try {
	        	map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
				long ORG_ID =(long) map1.get("ORG_ID");
				org_id = ORG_ID;
					PageInfo<Map<Object, Object>> pageInfo=iTeamApplicationService.findAll(pageNum, 10,ORG_ID);
		            JSONObject jsonObject=new JSONObject();
		            jsonObject.put("pageInfo", pageInfo);
		            response.setContentType("text/html;charset=utf-8");
		            PrintWriter out=response.getWriter();
		            out.println(jsonObject.toString());
		            out.flush();
		            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 /**
	  * 
	 * @Title: seeTeamMessage 
	 * @Description: 查看球队详情
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	  */
	 @RequestMapping(value = "/teams_select")
	 public String seeTeamMessage(Model model,HttpSession session,
			 @RequestParam(value = "comName",required = true)String comName){
		 		map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
		 		long ORG_ID =(long) map1.get("ORG_ID");
		 		Map<String, Object> isClick = new HashMap<>();
		 		isClick.put("num", 1);
			try {
				message =  iTeamApplicationService.findone(ORG_ID, comName);
				if(message != null && !message.isEmpty()){
					model.addAttribute("message", message);
					model.addAttribute("isClick", isClick);
				}else{
					model.addAttribute("Msg", "抱歉！找不到您要搜索的球队！");
				}
			} catch (Exception e) {
				model.addAttribute("Msg", "抱歉！找不到您要搜索的球队！");
				e.printStackTrace();
			}
		 return OrgApplications;
	 }

	 /**
	  * 
	 * @Title: updateStatus 
	 * @Description: 更新通过球队审核状态 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
	 @RequestMapping(value = "/updateTeamStatus", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	 @ResponseBody
	 public String updateStatus(HttpSession session){
		 	int status = 404;
		 	String result = "登录会话失效,请重新登录!";
		 	JSONObject jsonstr = new JSONObject();
		 	Map<String, Object> messageMap = new HashMap<>();
		 try {
			 int ID = iTeamApplicationService.selectAId(org_id);//机构A_ID
			 String orgName = iTeamApplicationService.selectOrgName(org_id);//机构名称
			 messageMap.put("PS_RECEIVE",A_ID );
			 messageMap.put("PS_SEND_ID",ID );
			 messageMap.put("PS_TITLE","申请加入"+orgName+"的审核结果" );
			 messageMap.put("PS_TEXT",orgName+"同意了你加入"+orgName+"的申请");
			int num = iTeamApplicationService.updateTeamStatus(team_id);
			if(num == 1){
				result = "操作成功!";
				status = 200;
				jsonstr.put("status", status);
				jsonstr.put("message", result);
				SelectMessage.insertPersonMessage(messageMap);
			}else{
				result = "操作失败,请重试!";
			}
		} catch (Exception e) {
			result = "操作失败,请重试!";
			e.printStackTrace();
		}
		 return jsonstr.toString();
	 }
	 
	 /**
	  * 
	 * @Title: updateStatus 
	 * @Description: 更新不通过球队审核状态 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
	 @RequestMapping(value = "/updateTeamfailerStatus", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	 @ResponseBody
	 public String updatefailerStatus(HttpSession session){
		 	int status = 404;
		 	String result = "登录会话失效,请重新登录!";
		 	JSONObject jsonstr = new JSONObject();
		 	Map<String, Object> messageMap = new HashMap<>();
		 try {
			 int ID = iTeamApplicationService.selectAId(org_id);//机构A_ID
			 String orgName = iTeamApplicationService.selectOrgName(org_id);//机构名称
			 messageMap.put("PS_RECEIVE",A_ID );
			 messageMap.put("PS_SEND_ID",ID );
			 messageMap.put("PS_TITLE","申请加入"+orgName+"的审核结果" );
			 messageMap.put("PS_TEXT",orgName+"拒加了你加入"+orgName+"的申请");
			int num = iTeamApplicationService.updateTeam(team_id);
			if(num == 1){
				result = "操作成功!";
				status = 200;
				jsonstr.put("status", status);
				jsonstr.put("message", result);
				SelectMessage.insertPersonMessage(messageMap);
			}else{
				result = "操作失败,请重试!";
			}
		} catch (Exception e) {
			result = "操作失败,请重试!";
			e.printStackTrace();
		}
		 return jsonstr.toString();
	 }
}
