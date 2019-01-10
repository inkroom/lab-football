package com.nsu.controller.organization.application;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.common.annotation.InterceptorAnno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.organization.IOrgPerfectInfoService;
import com.nsu.util.InfoProUtil;
import com.nsu.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/org")
public class OrgPerfectInfoController extends BaseController{
	@Resource(name="iOrgPerfectInfoService")
	private IOrgPerfectInfoService iOrgPerfectInfoService;
	private String organization_message="/organization/institutions_index";
	private Map<Object,Object> map1 = new HashMap<>();
	private Map<Object, String> map_name = new HashMap<>();
	private long org_id ;//机构id

	/**
	 * 
	 * @Title: State_view 
	 * @Description: 根据机构id查机构name的Action
	 * @param @param model
	 * @param @param session
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping(value="/organization_name")
	public String organizationName( Model model, HttpSession session){
		try {
			
			map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
			org_id = (long)map1.get("ORG_ID");
			map_name = iOrgPerfectInfoService.getOrganizationMessage(org_id);
			if(session.getAttribute("map_name") != null){
				session.removeAttribute("map_name");
			}
			session.setAttribute("map_name", map_name);
			model.addAttribute("map_name", map_name);
		} catch (Exception e) {
			model.addAttribute("Msg","系统发生异常，请重新尝试！");
			e.printStackTrace();
		}
		return organization_message;
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: organizationMessage 
	 * @Description: 机构系统添加完善信息
	 * @param @param username
	 * @param @param userphone
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping(value="/organization_EVPI")
	public String organizationMessage(@RequestParam(value = "user_name",required = true)String user_name,
			@RequestParam(value = "user_phone",required = true) String user_phone , HttpSession session,HttpServletResponse response,
			@RequestParam(value = "user_email",required = true) String user_email) throws Exception{
		
		map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
		org_id = (long)map1.get("ORG_ID");
		JSONObject info = new JSONObject();
		try {
			int num = iOrgPerfectInfoService.updateOrganizationOrg(user_name, InfoProUtil.xorInfo(user_phone),user_email,org_id);
			info.put("result", "完善信息成功！");
			ResponseUtil.write(response, info);
			if(num != 1){
				info.put("result", "服务器错误，请重新尝试！");
				ResponseUtil.write(response, info);
				return info.toString();
			}
		} catch (Exception e) {
			info.put("result", "服务器错误，请重新尝试！");
			e.printStackTrace();
		}
		return info.toString();
	}
	
	/**
	 * 
	* @Title: modifyOrganizationMessage 
	* @Description: 机构修改信息
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@InterceptorAnno(checkToken = true,removeToken = true)
	@RequestMapping(value="/organization_modify")
	public String modifyOrganizationMessage(@RequestParam(value = "userName",required = true) String userName,
			@RequestParam(value = "orgName",required = true) String orgName,
			 HttpSession session,HttpServletResponse response){
		String result = "登录会话失效,请重新登录!";
		map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
		org_id = (long)map1.get("ORG_ID");
		JSONObject info = new JSONObject();
		try {
			int num1 = iOrgPerfectInfoService.modifyAccountName(userName, org_id);
			int num2 = iOrgPerfectInfoService.modifyOrganizationName(orgName, org_id);
			info.put("result", "修改信息成功！");
			ResponseUtil.write(response, info);
			if(num1 != 1 && num2 != 1){
				info.put("result", "服务器错误，请重新尝试！");
				ResponseUtil.write(response, info);
				return info.toString();
			}
		} catch (Exception e) {
			info.put("result", "服务器错误，请重新尝试！");
			e.printStackTrace();
		}
		return info.toString();
	}
}
