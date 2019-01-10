package com.nsu.controller.coach.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsu.bean.message.MessagePersonBean;
import com.nsu.bean.player.ResultJson;
import com.nsu.controller.BaseController;
import com.nsu.mobilepush.MobilePush;
import com.nsu.service.coach.CoachMobileService;
import com.nsu.service.coach.CoachTrainingService;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.util.RSAencrypt.MobileToken;

@Controller
@RequestMapping("/mobile/coach")
public class CoachMobileController extends BaseController {
	// 返回信息
	private final String successCode = "200";
	private final String errorCode = "404";
	private final String systemError = "查询失败，请稍后查看！";
	@Autowired
	private CoachMobileService coachMobileService;
	@Autowired
	private CoachTrainingService coachTrainingService;
	@Autowired
	private MobilePush mobilePush;
	@Autowired
	private ISelectMessageService iSelectMessageService;

	@ResponseBody
	@RequestMapping("/match")
	public ResultJson getCoachMatch(@RequestParam("token") String token, @RequestParam("type") String M_TYPE) {
		try {
			Map<String, Object> info = new HashMap<>();
			// 解析token
			info = MobileToken.getTokenMap(token);
			String A_ID = info.get("A_ID").toString();
			List<Map<String, Object>> listMap = new ArrayList<>();
			if (M_TYPE.equals("1")) {// 省级比赛
				listMap = coachMobileService.getCoachProvinceMatch(A_ID);
			} else if (M_TYPE.equals("2")) {// 市级比赛
				listMap = coachMobileService.getCoachCityMatch(A_ID);
			} else if (M_TYPE.equals("3")) {// 县级比赛
				listMap = coachMobileService.getCoachCountyMatch(A_ID);
			} else if (M_TYPE.equals("4")) {// 校级比赛
				listMap = coachMobileService.getCoachSchoolMatch(A_ID);
			} else if (M_TYPE.equals("5")) {// 其他比赛
				listMap = coachMobileService.getCoachOtherMatch(A_ID);
			}
			return new ResultJson(successCode, listMap);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(errorCode, systemError);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/releaseTraning")
	public ResultJson releaseTraning(@RequestParam("token") String token, @RequestParam("forms") String forms) {
		try {
			// 解析token
			Map<String, Object> info = new HashMap<>();
			info = MobileToken.getTokenMap(token);
			log.info(info.toString() + "=====");
			JSONObject form = MobileToken.analysisParam(forms);
			log.info(forms.toString()+"-------------");
		//	JSONObject form=new JSONObject(forms);
			log.info(form+"+++++++++++++++++");
			Map<String, Object> traningMap = new HashMap<String,Object>();
			traningMap.put("teamId", form.getString("teamId"));
			traningMap.put("time", form.getString("time"));
			traningMap.put("title", form.getString("title"));
			traningMap.put("addr", form.getString("addr"));
			traningMap.put("content",form.getString("content")
					);
//			traningMap.put("teamId", form.get("teamId"));
//			traningMap.put("time", form.get("time"));
//			traningMap.put("title", form.get("title"));
//			traningMap.put("addr", form.get("addr"));
//			traningMap.put("content", form.get("content"));
			String A_ID = info.get("A_ID").toString();
			log.info("-----" + traningMap.toString());
			//获得推送的消息
			List<String> list = coachMobileService.getPushInfo(traningMap.get("teamId").toString());
			log.info("推送的信息：" + list);
			// 手机推送
			// mobilePush.sendAndroidUnicast(traningMap.get("title").toString(),traningMap.get("content").toString(),"Ao-tdggIUtkwglIf7wxSnXiRbVVgn5zykt3pbLuSPVfk");


			Map<String, Object> coachInfo = coachMobileService.getCoachID(A_ID);
			String coachid = coachInfo.get("COACH_ID").toString();
			String COACH_NAME = coachInfo.get("COACH_NAME").toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String times = df.format(new Date());
			log.info(coachInfo+"//////");
			// 插入coachtrain表
			coachTrainingService.coachTrainningInfo(traningMap.get("title").toString(),
					traningMap.get("teamId").toString(), traningMap.get("addr").toString(), times,
					traningMap.get("content").toString(), coachid);
			String SM_TEXT = "教练:" + COACH_NAME + ".<br>发布了训练:" + traningMap.get("title").toString() + ".<br>地点:"
					+ traningMap.get("addr").toString() + ",<br>开始时间:" + times + ".<br>备注："
					+ traningMap.get("content").toString();
			

			List<String> playerlist = coachTrainingService.ByTeamidSelectPlayerid(traningMap.get("teamId").toString());
			List<MessagePersonBean> list2 = new ArrayList<MessagePersonBean>();
			 log.info(playerlist.size());
			 if (playerlist.size()!= 0) {
					for (int i = 0; i < playerlist.size(); i++) {
						MessagePersonBean mpb = new MessagePersonBean();
						mpb.setPS_TITLE("训练消息:"+traningMap.get("title").toString());
						mpb.setPS_TEXT(SM_TEXT);
						mpb.setPS_SEND_ID(coachid);
						mpb.setPS_RECEIVE(playerlist.get(i));
						list2.add(mpb);
					}
					try {
						iSelectMessageService.insertTrainingMessage(list2);
						 } catch (Exception e) {
						log.info("失败");
						e.printStackTrace();
					}
					
					for(int i=0;i<list.size();i++){
						
						if(list.get(i)!=null){
							   mobilePush.sendAndroidUnicast(traningMap.get("title").toString(),traningMap.get("content").toString(),list.get(i));
							   log.info("执行到mobilePush");
						}
						log.info("执行到mobilePush231312");
						}
					log.info("执行到for外");
					}
			 else {
					log.info("该队没有球员");
				}
			log.info("sys成功");
			log.info("插入信息成功");
			return new ResultJson(successCode, "发布成功");
		} 
		catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(errorCode, systemError);
		}

	}
}
