package com.nsu.staticvar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.nsu.bean.player.PlayerActityBean;
import com.nsu.util.V;

/**
 * 球队所有公共工具和常量
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 18:10:33
 */
public class TeamVar {

	/** 球队登录页面 */
	public final static String TEAM_LOGING_PAGE ="/team/team_login_page";

	/** 重定向球队登录页面 */
	public final static String REDIRECT_TEAM_LOGING_PAGE = "redirect:/team/login_view.html";
	
	/** 球队主页面 */
	public final static String TEAM_INDEX_PAGE = "/team/team_index_page";
	
	/** 重定向到球队主页面 */
	public final static String TEAM_INDEX_HTML = "redirect:/team/team_index_view.html";
	
	/** 球队中心页面 */
	public final static String TEAM_MESSAGE_PAGE = "/team/team_system/team_message";
	
	/** 重定向球队中心页面 */
	public final static String TEAM_MESSAGE_HTML = "redirect:/team/team_system/team_message.html";
	
	/** 球队比赛信息页面 */
	public final static String TEAM_MATCH_PAGE = "/team/team_system/team_match";
	
	/** 球队比赛信息子页面 */
	public final static String TEAM_MATCH_PAGE_TWO = "/team/team_system/team_matchtwo";
	
	/** 重定向球队比赛信息页面 */
	public final static String TEAM_MATCH_HTML = "redirect:/team/team_match_view.action";
	
	/** 球队管理页面 */
	public final static String TEAM_MANAGE_PAGE = "/team/team_system/team_manage";
	
	/** 重定向球队管理页面 */
	public final static String TEAM_MANAGE_HTML = "redirect:/team/team_manage_view.action";
	
	/** 球队比赛申请页面 */
	public final static String TEAM_APPLY_PAGE = "/team/team_system/match_apply";

	/** 球队安排参赛上场球员页面*/
	public final static String TEAM_SCHEDULE_ARRANGE_PAGE = "/team/team_system/team_schedule_arrange";
	
	/** 球队修改信息页面 */
	public final static String TEAM_EDIT_INFO_PAGE = "/team/team_system/team_edit";
	
	/** 球队进入球员申请管理界面 */
	public final static String TEAM_PLAYER_PAGE = "/team/team_system/Audit-application";
	
	/** 球队进入教练申请管理界面 */
	public final static String TEAM_COACH_PAGE = "/team/team_system/coach-apply-audit";
	
	/** 登录失效跳转页面 */
	public final static String TEAM_SESSION_OUTIME_PAGE = "/team/tologin";

	

//	/**
//	 * 通过身份证号获取年龄
//	 * @author ljl
//	 * @createDate 2017-04-19 18:11:35
//	 * @param IdNO 身份证号
//	 * @return
//	 */
//	public static int IdNOToAge(String IdNO){
//        int leh = IdNO.length();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy");
//        String year=df.format(new Date());
//        String dates="";
//        int u = 0;
//        if (leh == 18) {
//            dates = IdNO.substring(6, 10);
//            u=Integer.parseInt(year)-Integer.parseInt(dates);
//
//        }else{
//            dates = IdNO.substring(6,8);
//            u=Integer.parseInt(year)-(Integer.parseInt(dates)+1900);
//        }
//        return u;
//    }
	
	/**
	 * 计算球员积分
	 * @author ljl
	 * @createDate 2017-05-03 17:27:01
	 * @param map
	 * @return
	 */
	public static int calculaPlayerScore(Map<String, Object> map){
		int win = 0, lose = 0, flat = 0;
		if(map!=null){
			win = (V.checkEmpty(map.get("PS_CITY_WIN"))==true?0:Integer.parseInt(map.get("PS_CITY_WIN").toString()))
					+(V.checkEmpty(map.get("PS_PROVINE_WIN"))==true?0:Integer.parseInt(map.get("PS_PROVINE_WIN").toString()))
					+(V.checkEmpty(map.get("PS_COUNTY_WIN"))==true?0:Integer.parseInt(map.get("PS_COUNTY_WIN").toString()))
					+(V.checkEmpty(map.get("PS_SCHOOL_WIN"))==true?0:Integer.parseInt(map.get("PS_SCHOOL_WIN").toString()))
					+(V.checkEmpty(map.get("PS_OTHER_WIN"))==true?0:Integer.parseInt(map.get("PS_OTHER_WIN").toString()));
			
			lose = (V.checkEmpty(map.get("PS_CITY_LOSE"))==true?0:Integer.parseInt(map.get("PS_CITY_LOSE").toString()))
					+(V.checkEmpty(map.get("PS_PROVINE_LOSE"))==true?0:Integer.parseInt(map.get("PS_PROVINE_LOSE").toString()))
					+(V.checkEmpty(map.get("PS_COUNTY_LOSE"))==true?0:Integer.parseInt(map.get("PS_COUNTY_LOSE").toString()))
					+(V.checkEmpty(map.get("PS_SCHOOL_LOSE"))==true?0:Integer.parseInt(map.get("PS_SCHOOL_LOSE").toString()))
					+(V.checkEmpty(map.get("PS_OTHER_LOSS"))==true?0:Integer.parseInt(map.get("PS_OTHER_LOSS").toString()));
			
			flat = (V.checkEmpty(map.get("PS_CITY_FLAT"))==true?0:Integer.parseInt(map.get("PS_CITY_FLAT").toString()))
					+(V.checkEmpty(map.get("PS_PROVINE_FLAT"))==true?0:Integer.parseInt(map.get("PS_PROVINE_FLAT").toString()))
					+(V.checkEmpty(map.get("PS_COUNTY_FLAT"))==true?0:Integer.parseInt(map.get("PS_COUNTY_FLAT").toString()))
					+(V.checkEmpty(map.get("PS_SCHOOL_FLAT"))==true?0:Integer.parseInt(map.get("PS_SCHOOL_FLAT").toString()))
					+(V.checkEmpty(map.get("PS_OTHER_ALL"))==true?0:Integer.parseInt(map.get("PS_OTHER_ALL").toString()));
			
		}
		
		return win*3+flat*2+lose;
	}
	
	/**
	 * 将PlayerActityBean对象中比赛信息拼接成字符串
	 * @author ljl
	 * @createDate 2017-06-05 17:28:56
	 * @param list
	 * @return
	 */
	public static String PojoToString(List<PlayerActityBean> list){
		String str = "";
		if(list==null || list.isEmpty()){
			
			return "暂无数据";
		}
		boolean tmp = false;
		for(PlayerActityBean playerActity : list){
			if(tmp){
				str += "<br/>";
			}
			//XXXX年X月+在+XXX球队+参加 +赛事名称+获得+排名
			str += playerActity.getCom_end() + ",在" + playerActity.getTeam_name() 
			+ "参加<b>" + playerActity.getCom_name() + "</b>获得" + playerActity.getCom_rank();
			tmp = true;
		}
		return str;
	}

}
