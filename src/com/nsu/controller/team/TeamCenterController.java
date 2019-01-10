package com.nsu.controller.team;


import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nsu.bean.team.TeamBasicInfoBean;

import com.nsu.bean.team.TeamCenterDO;

import com.nsu.common.Constants;


import com.nsu.controller.common.UploadBaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.team.ITeamCenterService;
import com.nsu.service.team.ITeamSendVerificationCodeService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.SystemSeparatorUtil;
import com.nsu.util.V;
import com.nsu.util.jedis.JedisClient;

import net.sf.json.JSONArray;

/**
 * 球队中心
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 19:00:03
 */
@Controller
@RequestMapping(value="/team")
public class TeamCenterController extends UploadBaseController{
	/**
	 * 获取根路径
	 */
	private String rootPath = SystemSeparatorUtil.getRootPath();
	/**
	 * 获取系统文件路径格式
	 */
	private final String separator = SystemSeparatorUtil.getSystemSeparator();
	/**
	 * 文件上传成功
	 */
	private final String ROLE = "team";
	private final String FILE_TYPE_IMG = "image";
	private final String FILE_TYPE_MUSIC = "music";
	
	@Resource(name="teamCenterService") 
	ITeamCenterService teamCenterService;
	
	@Resource(name="teamSendVerificationCodeService")
	private ITeamSendVerificationCodeService teamSendVerificationCodeService;
	@Resource(name = "SelectMessage")
	ISelectMessageService SelectMessage;

	
	@Autowired
    private JedisClient jedisClient;
	
	/**
	 * 进入球队中心页面，显示球队的基本信息
	 * @author ljl
	 * @createDate 2017-04-10 19:11:25
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/team_center_view")
	public String teamCenterView(HttpSession session,Model model){
		//从session中获取用户的账号ID
		
		String TEAM_ID = TeamUtil.getAccountInfoInSession(session, "teamID");
		
		if(V.checkEmpty(TEAM_ID) == true){
			log.info("没有获取到球队id");
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		
		//获取球队基本信息
		Map<String, Object> map = teamCenterService.findFootballTeamInfo(TEAM_ID);
		model.addAttribute("map", map);
		
		//获取学生信息
		List<TeamCenterDO> listSTU = teamCenterService.findTeamplayerInfo(TEAM_ID);
		log.info("playername: "+listSTU);
		model.addAttribute("liststu", listSTU);
		
		//获取教师姓名
		List<TeamCenterDO> listCOACH = teamCenterService.findTeamCoachName(TEAM_ID);
		log.info("看看老师名字"+listCOACH);
		model.addAttribute("listcoach",listCOACH);
		
		//获取机构信息
		TeamCenterDO ORG= teamCenterService.findTeamOrgDaoService(TEAM_ID);
		log.info("机构信息"+ORG);
		model.addAttribute("ORG",ORG);
		
		//获取球队信息
		TeamBasicInfoBean teamBasicInfo = teamCenterService.findTeamBasicInfo(TEAM_ID);
		if(teamBasicInfo!=null){
			model.addAttribute("teamFlag", teamBasicInfo.getTeamFlag());
			model.addAttribute("teamLogo", teamBasicInfo.getTeamLogo());
			model.addAttribute("teamMusic", teamBasicInfo.getTeamMusic()+"?v="+new Date());//上传新的队歌后，让浏览器重新加载音乐文件；而不是读取缓存文件
			log.info("***********"+teamBasicInfo);
		}
		
		
		//进入球队中心页面
		return TeamUtil.TEAM_MESSAGE_PAGE;
	}
	

	/**
	 * 进入编辑信息页面
	 * @author ljl
	 * @createDate 2017-04-14 09:19:42
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/team_edit")
	public String editTeamInfoPage(Model model, HttpSession session) {
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		if(V.checkEmpty(teamID) == true){
			return TeamUtil.TEAM_SESSION_OUTIME_PAGE;
		}
		try {
			//获取球队信息
			TeamBasicInfoBean teamBasicInfo = teamCenterService.findTeamBasicInfo(teamID);
			if(V.checkEmpty(teamBasicInfo.getTeamLeaderPhone()) == false){
				String phone = InfoProtUtil.xorInfo(teamBasicInfo.getTeamLeaderPhone());
				teamBasicInfo.setTeamLeaderPhone(phone);
			}
			
			teamBasicInfo.setTeamID(teamID);
			model.addAttribute("teamBasicInfo", teamBasicInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TeamUtil.TEAM_EDIT_INFO_PAGE;
	}
	
	/**
	 * 完善球队信息,返回处理结果
	 * @author ljl
	 * @createDate 2017-04-12 14:19:40
	 * @return
	 */
	@RequestMapping(value="/team_improve_info", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamImproveInformation(TeamBasicInfoBean teamBasicInfo,Model model, HttpSession session){
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
		JSONObject jsonStr = new JSONObject();
		String result = null;
		if(userMap == null || userMap.size()==0 || V.checkEmpty(userMap.get("A_ID")) == true 
				|| V.checkEmpty(userMap.get("USERNAME")) == true || V.checkEmpty(userMap.get("teamID")) == true){
			jsonStr.put("status", 500);
			jsonStr.put("message", "登录失效,2秒后跳转到登录页面!");
		}else{
			//判断是否需要邮箱验证码
			boolean needEmail = true;
			if(V.checkEmpty(session.getAttribute("needEmail")) == false && "1".equals(session.getAttribute("needEmail").toString()) == true){
				needEmail = false;
			}
			String bindingEmail = teamSendVerificationCodeService.findTeambindingEmail(userMap.get("A_ID").toString());
			Map<String, String> codeMap = V.verificationCode(jedisClient, 4, 2, null, bindingEmail, teamBasicInfo.getCode());
			if(needEmail && codeMap.get("result").equals("1") == false){
				jsonStr.put("status", 404);
				jsonStr.put("message", codeMap.get("resultMsg"));
			}else{
				teamBasicInfo.setaID(userMap.get("A_ID").toString());
				teamBasicInfo.setAccount(userMap.get("USERNAME").toString());
				teamBasicInfo.setTeamID(userMap.get("teamID").toString());
				teamBasicInfo.setTeamCreateTime(userMap.get("teamCreateDate").toString());
				
				result = teamCenterService.updateTeamBasicInfo(teamBasicInfo);
				userMap.remove("aName");
				userMap.put("aName",teamBasicInfo.getTeamName().toString());
				session.removeAttribute(Constants.LOGIN_USER);
				session.setAttribute(Constants.LOGIN_USER,userMap);
				if(V.checkEmpty(result) == true){
					jsonStr.put("status", 200);
					jsonStr.put("message", "操作成功");
					
					session.removeAttribute("needEmail");
				}else{
					jsonStr.put("status", 404);
					jsonStr.put("message", result);
				}
			}
		}
		return jsonStr.toString();
	}
	
	/**
	 * 初次登录绑定邮箱
	 * @author ljl
	 * @createDate 2017-04-28 09:12:56
	 * @param email
	 * @param code
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/team_improve_info_email", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamImproveEmailInformation(String email, String code,Model model, HttpSession session){

		JSONObject jsonStr = new JSONObject();
		String result = "登录失效,2秒后跳转到登录页面!";
		int status = 500;
		String aid = TeamUtil.getAccountInfoInSession(session, "A_ID");
		String account = TeamUtil.getAccountInfoInSession(session, "USERNAME");
		if(V.checkEmpty(code) == true){
			status = 404;
			result = "请填写验证码";
		}else{
				//验证验证码
			Map<String, String> codeMap = V.verificationCode(jedisClient, 4, 2, null, email, code);
			if(codeMap.get("result").equals("1") == false){
				status = 404;
				result = codeMap.get("resultMsg");
			}else{
				//更新邮箱
				if(V.checkEmpty(aid) == false && V.checkEmpty(account) == false){
					result = teamCenterService.updateTeamEmail(aid, email, code, account);
					if(V.checkEmpty(result) == true){
						status = 200;
						result = "操作成功";
					}else{
						status = 404;
					}
				}
			}
		}
			
		jsonStr.put("status", status);
		jsonStr.put("message", result);
		return jsonStr.toString();
	}
	
	/**
	 * 上传队徽和队旗图片
	 * @author ljl
	 * @createDate 2017-04-17 20:55:57
	 * @param imgStr
	 * @param type
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/upload_photo", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String uploadPhoto(@RequestParam("image")String imgStr,String type
			, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		String result = "";
		int status = 200;
		JSONObject resJosn = new JSONObject();
		if(V.checkEmpty(type) == true ){
			result = "上传图片失败";
			status = 404;
			log.info("***********上传图片失败***********");
		}else{
			String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
			if(V.checkEmpty(teamID) == true){
				result = "登录会话失效,请重新登录";
				status = 404;
				log.info("***********登录会话失效,没有获取到球队ID***********");
			}else{
				//构建路径
				String imgName = type+teamID;
				StringBuffer tempPath = new StringBuffer();
				tempPath.append(rootPath).append(separator).append("resources").append(separator).append("temp")
						.append(separator).append(imgName+".jpg");
				String userId = TeamUtil.getAccountInfoInSession(session, "A_ID");
				File file = TeamUtil.base64ToFile(tempPath.toString(), imgStr);
				try{
					String filePath = fileUploadReplace(file, request, ROLE, FILE_TYPE_IMG, userId, imgName+".jpg");
					log.info(filePath);
					//存储图片路径
					result = teamCenterService.updateTeamPhotoes(teamID, filePath, type);
					huashiPrintln(request);
					if(V.checkEmpty(result) == true){
						result = "上传图片成功";
					}else{
						status = 404;
					}
				}catch(Exception e){
					e.printStackTrace();
					log.error(e.getMessage());
					log.info("***********上传图片失败***********");
				}
			}	
		}
		resJosn.put("result", result);
		resJosn.put("status", status);
		return resJosn.toString();
		
	}

	/**
	 * 上传队歌
	 * @author ljl
	 * @createDate 2017-04-19 21:35:22
	 * @return
	 */
	@RequestMapping(value = "/upload_team_song", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String uploadTeamMusic(@RequestParam MultipartFile musicFile
			, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		String result = "";
		int status = 200;
		JSONObject resJosn = new JSONObject();
		String teamID = TeamUtil.getAccountInfoInSession(session, "teamID");
		String userId = TeamUtil.getAccountInfoInSession(session, "A_ID");
		if(V.checkEmpty(teamID) == false){
			try {
				Map<String, Object> map =  fileUpload(musicFile, request, ROLE, FILE_TYPE_MUSIC, userId, "teamSong"+teamID+".mp3", 5*1024*1024);
				if(map!=null && map.isEmpty()== false){
					if(V.checkEmpty(map.get("error")) == true){
						if(V.checkEmpty(map.get("path")) == false){
							//存储队歌路径
							result = teamCenterService.updateTeamSong(teamID, map.get("path"));
							result = "上传文件成功";
						}else{
							result = "文件上传失败";
							status = 404;
						}

					}else{
						result = map.get("error").toString();
						status = 404;
					}
				}else{
					result = "文件上传失败";
					status = 404;
				}

			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
				result = "上传文件失败";
				status = 404;
			}
		}else{
			result="登录会话失效,请重新登录";
			status = 404;
		}
		log.info("队歌上传结果："+result);
		resJosn.put("message", result);
		resJosn.put("status", status);
		return resJosn.toString();
	}
	

	/**
	 * @Description: 查询机构信息	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:41:22
	 * @return
	 */
	@RequestMapping(value="/team_center_org", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamCoachone(String ORG_ID, HttpSession session){
		log.info("悄悄"+ORG_ID);
		int status = 404;
		String result = "登录会话失效,请重新登录";
		JSONObject jsonstr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		TeamCenterDO ORG= teamCenterService.findALLOrgDaoService(ORG_ID);
		if(ORG!=null){
			if(ORG.getORG_ID() != ""){
				status = 200;
			    result = "查询成功";
			    jsonArray = JSONArray.fromObject(ORG);
				jsonstr.put("ORG", jsonArray);
			}else{
				result = "无此机构";
			}
		}else{
			result = "无此机构";
		}
			
		log.info(result);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	
	}
	
	/**
	 * @Description: 确认加入	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:12:13
	 * @return
	 */
	@RequestMapping(value="/team_center_agree", produces="text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String teamcenteragree(String ORG_ID, HttpSession session, TeamCenterDO teamCenterDO){
		int status = 404;
		String result = "登录会话失效,请重新登录";
		JSONObject jsonstr = new JSONObject();
		
		String TEAM_ID = TeamUtil.getAccountInfoInSession(session, "teamID");
		
           result = teamCenterService.updateOrgStatusService(ORG_ID, TEAM_ID);
			if("success".equals(result)){
				result = "操作成功";
				status = 200;
				
				int ORG_AID = teamCenterService.findAIDBYORGService(ORG_ID);
				teamCenterDO = teamCenterService.findAIDBYTEAMService(TEAM_ID);
				log.info("ORG_AID++++++++"+ORG_AID);
				log.info("~~~~~~~~~~~~~~~"+teamCenterDO.getA_ID()+"1111111"+teamCenterDO.getTEAM_NAME());
				Map<String,Object> map = new HashMap<>();
				map.put("PS_RECEIVE", ORG_AID);//接收人A_ID
				map.put("PS_SEND_ID", teamCenterDO.getA_ID());//发送人ID
				map.put("PS_TITLE", teamCenterDO.getTEAM_NAME()+"球队申请加入机构");//消息标题
				map.put("PS_TEXT", teamCenterDO.getTEAM_NAME()+"球队申请加入机构<br/>请前往->管理申请->球队申请中进行审核 ");//消息内容
				try {
					SelectMessage.insertPersonMessage(map);
					log.info("同意" + TEAM_ID);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				result = "操作失败,请重试";
			}
			
			
		log.info(result);
		jsonstr.put("status", status);
		jsonstr.put("message", result);
		return jsonstr.toString();
	
	}
	
}
