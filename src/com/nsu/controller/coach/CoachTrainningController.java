/**
 * 
 */
package com.nsu.controller.coach;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nsu.bean.message.MessagePersonBean;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.mobilepush.MobilePush;
import com.nsu.service.coach.CoachMobileService;
import com.nsu.service.coach.CoachTrainingService;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.util.VerifyUtil;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/**    
* @Title: CoachTrainningController.java
* @Package com.nsu.controller.coach 
* @Description: 教练员发布球队训练
* @author 潘泳言   * @date 2017年4月17日 下午2:12:13  
* @version V1.0    */
@Controller
@RequestMapping("/coach")

public class CoachTrainningController extends BaseController{
	@Autowired
	private CoachTrainingService coachTrainingService;
	@Autowired
	private ISelectMessageService iSelectMessageService;
	@Autowired
	private CoachMobileService coachMobileService;
	@Autowired
	private MobilePush mobilePush;
	@InterceptorAnno(createToken = true)
	@RequestMapping(value="/training", method = RequestMethod.GET)
	public ModelAndView getCoachTeams(HttpServletRequest request){
		ModelAndView mv  = new ModelAndView();
		String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();	
		//获得教练的所有球队ID
		List<String> teamList = coachTrainingService.getCoachTeams(coachID);
		mv.addObject("tInfo", teamList);
		//进入发布训练页面
		mv.setViewName("/coach/coach-system/training");
		return mv;
	}
 /*title
  *place 
  * time
  * typs
  * teamid
  * */
	@InterceptorAnno(checkToken = true,removeToken = true)
	@RequestMapping(value="/coachTraining",method=RequestMethod.POST)
	public ModelAndView coachTrainning(
			@RequestParam("title")String title,
			@RequestParam("place")String place,
			@RequestParam("time")String time,
			@RequestParam("typs")String typs,
			@RequestParam("teamid")String teamid,
			HttpSession session,
			HttpServletRequest request) {
		ModelAndView mv= new ModelAndView();
		//空值校验
		if(!VerifyUtil.isNotEmpty(title)){
			mv.addObject("errmsg","标题为必填项");
			mv.setViewName("/coach/coach-system/training");
			return mv;
		}else if (!VerifyUtil.isNotEmpty(place)){
			mv.addObject("errmsg","地点为必填项");
			mv.setViewName("/coach/coach-system/training");
			return mv;
		}else if (!VerifyUtil.isNotEmpty(time)){
			mv.addObject("errmsg","时间为必填项");
			mv.setViewName("/coach/coach-system/training");
			return mv;
		}else if (!VerifyUtil.isNotEmpty(teamid)){
			mv.addObject("errmsg","球队为必填项");
			mv.setViewName("/coach/coach-system/training");
			return mv;
		}
		//存入教练训练表
		String coach_ID=session.getAttribute(Constants.LOGIN_USER_ID).toString();
		if(coachTrainingService.coachTrainningInfo(title,teamid,place,time,typs,coach_ID)){		
				mv.addObject("errmsg", "发布成功");
				String coachID = request.getSession().getAttribute(Constants.LOGIN_USER_ID).toString();	
				List<String> teamList = coachTrainingService.getCoachTeams(coachID);
				mv.addObject("tInfo", teamList);
				@SuppressWarnings("unchecked")
				Map<String, Object> coachMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
				String COACH_NAME=coachMap.get("A_NAME").toString();
				//times当前时间
				String SM_TEXT;
				SM_TEXT="教练:"+COACH_NAME+".<br>发布了训练:"+title+".<br>地点:"+place+",<br>开始时间:"+time+".<br>备注："+typs;
				
				//存入消息表 SM_TYPE=3训练消息
				//SM_TYPE 消息种类，为3是教练消息
				List<String> list = coachMobileService.getPushInfo(teamid);
				log.info("推送的信息：" + list);
				List<String> playerlist = coachTrainingService.ByTeamidSelectPlayerid(teamid);
			    
				List<MessagePersonBean> list2 = new ArrayList<MessagePersonBean>();
				 log.info(playerlist.size());
				 
				if (playerlist.size()!= 0) {
					for (int i = 0; i < playerlist.size(); i++) {
						MessagePersonBean mpb = new MessagePersonBean();
						mpb.setPS_TITLE("训练消息："+title);
						mpb.setPS_TEXT(SM_TEXT);
						mpb.setPS_SEND_ID(coach_ID);
						mpb.setPS_RECEIVE(playerlist.get(i));
						list2.add(mpb);
					}
					try {
						iSelectMessageService.insertTrainingMessage(list2);
						
						}
					 catch (Exception e) {
						log.info("失败");
						e.printStackTrace();
					}
					
                 for(int i=0;i<list.size();i++){
						
						if(list.get(i)!=null){
							try {
								  mobilePush.sendAndroidUnicast(title,typs,list.get(i));
							} catch (Exception e) {
								
							}
						
							   log.info("执行到mobilePush");
						}
						log.info("执行到mobilePush231312");
						}
					log.info("执行到for外");
					
				} else {
					log.info("该队没有球员");
				}
				log.info("sss"+list2);
				
				mv.setViewName("/coach/coach-system/training");
			}else {
				mv.addObject("errmsg", "发布失败");
			}
			return mv;
	}
	
}
