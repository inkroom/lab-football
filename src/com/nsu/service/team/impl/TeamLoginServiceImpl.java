package com.nsu.service.team.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.controller.team.TeamUtil;
import com.nsu.dao.team.TeamCenterDao;
import com.nsu.dao.team.TeamLoginDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamLoginService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.V;

import net.sf.json.JSONArray;

/**
 * 球队管理员登录service实现类
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-10 17:21:49
 */
@Service("teamLoginService")
public class TeamLoginServiceImpl extends BaseService implements ITeamLoginService{

	@Resource
	TeamLoginDao teamLoginDao;
	@Autowired
	TeamCenterDao teamCenterDao;

	@Override
	public Map<String, Object> findAccountInfo(String username) {
		try{
			log.info("账号=  "+username);
			return teamLoginDao.findTeamUserInfo(username);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String findTeamLeader(String aid) {
		try{
			return teamLoginDao.findTeamLeader(aid);
		}catch(Exception e){

			log.error(e.getMessage());
			return "";
		}
	}

	@Override
	public Map<String, Object> getUserInfo(String username, String pushInfo, String deviceInfo)throws Exception {
		Map<String, Object> mobile=new HashMap<String,Object>();
		Map<String, Object> teamInfoMap=new HashMap<String,Object>();
		mobile.put("pushInfo", pushInfo);
		mobile.put("deviceInfo", deviceInfo);
		mobile.put("username", username);
		teamLoginDao.updateMobileInfo(mobile);
		//查询球队基本信息
		//队名、队旗、领队、领队电话
		Map<String, Object> map = teamLoginDao.getUserInfo(username);
		if(V.checkEmpty(map.get("TEAM_PHONE_NUM")) == false){
			String phone = InfoProtUtil.xorInfo(map.get("TEAM_PHONE_NUM").toString());
			map.remove("TEAM_PHONE_NUM");
			map.put("TEAM_PHONE_NUM", phone);
		}
		if(map!=null && map.isEmpty() == false){
			teamInfoMap.put("teamName", map.get("TEAM_NAME"));
			teamInfoMap.put("teamFlag", map.get("TEAM_FLAG"));
			teamInfoMap.put("teamLeader", map.get("TEAM_LEADER"));
			teamInfoMap.put("teamPhone", map.get("TEAM_PHONE_NUM"));
		}
		//查询球队赛事信息：球队积分 、比赛场次、胜利、失败、胜率
		Map<String, Object> matchMap = getTeamMatchInfo(map.get("teamID").toString());
		//积分
		teamInfoMap.put("teamScore", matchMap.get("TEAM_SCORE"));
		//胜利
		teamInfoMap.put("teamWin", matchMap.get("win"));
		//失败
		teamInfoMap.put("teamFail", matchMap.get("fail"));
		//总场次
		teamInfoMap.put("teamMatchTimes", matchMap.get("MATCHTIMES"));
		//平均胜率
		teamInfoMap.put("teamAvgwin", matchMap.get("AVGWIN"));

		//教练名
		List<Map<String, Object>> coachlist = teamLoginDao.getTeamCoachsInfo(map.get("teamID"));
		String coachStr = "";
		if(coachlist != null && coachlist.size()>0){
			for(int i = 0; i < coachlist.size(); i++){
				if(V.checkEmpty(coachlist.get(i)) == false){
					coachStr += coachlist.get(i).get("A_NAME").toString()+" ";
				}
			}
		}else{
			coachStr = "无";
		}
		log.info("********: "+coachStr);
		teamInfoMap.put("coachsInfo", coachStr==""?"无":coachStr);

		//球员：名字、头像、性别、位置、学校、积分
		List<Map<String, Object>> playerlist = teamLoginDao.getTeamPlayersInfo(map.get("teamID"));
		//球队球员数
		teamInfoMap.put("playersNum", playerlist==null?0:playerlist.size());

		JSONArray jsonPlayerArray = JSONArray.fromObject(getPlayersScore(playerlist));
		teamInfoMap.put("playersInfo", jsonPlayerArray);

		return teamInfoMap;
	}

	/**
	 * 获取球员积分
	 * @author ljl
	 * @createDate 2017-05-03 16:48:39
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> getPlayersScore(List<Map<String, Object>> list){
		if(list!=null && list.size()>0){
			for(int i = 0 ; i < list.size(); i ++){
				if(V.checkEmpty(list.get(i).get("pid")) == false){
					list.get(i).put("score", getPlayerScore(list.get(i).get("pid")));
				}else{
					list.remove(i);
				}
			}
		}
		return list;
	}

	/**
	 * 根据球队ID查询球队赛事次数和计算积分
	 * @author ljl
	 * @createDate 2017-04-23 15:22:24
	 * @param teamID
	 * @return
	 */
	public Map<String, Object> getTeamMatchInfo(String teamID){
		//操作数据库
		Map<String, Object> map=teamCenterDao.findFootballTeamDao(teamID);
		if(map!=null && map.isEmpty()==false){
			//计算积分，并存于map
			String TS_SCHOOL_VICTOR = V.checkEmpty(map.get("TS_SCHOOL_VICTOR"))==true?"0":map.get("TS_SCHOOL_VICTOR").toString();//得到校级获胜球数
			String TS_COUNTRY_VICTOR = V.checkEmpty(map.get("TS_COUNTRY_VICTOR"))==true?"0":map.get("TS_COUNTRY_VICTOR").toString();//得到县级获胜球数
			String TS_CITY_VICTOR = V.checkEmpty(map.get("TS_CITY_VICTOR"))==true?"0":map.get("TS_CITY_VICTOR").toString();//得到市级获胜球数
			String TS_PROVINCE_VICTOR = V.checkEmpty(map.get("TS_PROVINCE_VICTOR"))==true?"0":map.get("TS_PROVINCE_VICTOR").toString();//得到省级获胜球数
			String TS_SCHOOL_LOSE = V.checkEmpty(map.get("TS_SCHOOL_LOSE"))==true?"0":map.get("TS_SCHOOL_LOSE").toString();//得到校级获胜球数
			String TS_COUNTRY_LOSE = V.checkEmpty(map.get("TS_COUNTRY_LOSE"))==true?"0":map.get("TS_COUNTRY_LOSE").toString();//得到县级获胜球数
			String TS_CITY_LOSE = V.checkEmpty(map.get("TS_CITY_LOSE"))==true?"0":map.get("TS_CITY_LOSE").toString();//得到市级获胜球数
			String TS_PROVINCE_LOSE = V.checkEmpty(map.get("TS_PROVINCE_LOSE"))==true?"0":map.get("TS_PROVINCE_LOSE").toString();//得到省级获胜球数
			String TEAM_SCORE = "";//积分
			String winer = "";//获胜场次总数
			String loser = "";//获取失败场次总数
			winer = String.valueOf(Integer.valueOf(TS_SCHOOL_VICTOR)+Integer.valueOf(TS_COUNTRY_VICTOR)+Integer.valueOf(TS_CITY_VICTOR)+Integer.valueOf(TS_PROVINCE_VICTOR));
			loser = String.valueOf(Integer.valueOf(TS_SCHOOL_LOSE)+Integer.valueOf(TS_COUNTRY_LOSE)+Integer.valueOf(TS_CITY_LOSE)+Integer.valueOf(TS_PROVINCE_LOSE));

			TEAM_SCORE =String.valueOf(Integer.valueOf(winer)*3+Integer.valueOf(loser));

			map.put("TEAM_SCORE", TEAM_SCORE);
			map.put("win", winer);
			map.put("fail", loser);
			//计算总数
			String MATCHTIMES = String.valueOf(Integer.valueOf(winer)+Integer.valueOf(loser));
			map.put("MATCHTIMES", MATCHTIMES);

			//计算平均胜率
			String AVGWIN = (Integer.valueOf(winer)+Integer.valueOf(loser))==0?"0":String.valueOf(Integer.valueOf(winer)*100/(Integer.valueOf(winer)+Integer.valueOf(loser)));
			map.put("AVGWIN",AVGWIN);

			if(V.checkEmpty(map.get("TEAM_PHONE_NUM")) == false){
				String phone = InfoProtUtil.xorInfo(map.get("TEAM_PHONE_NUM").toString());
				map.remove("TEAM_PHONE_NUM");
				map.put("TEAM_PHONE_NUM", phone);
			}
			if(V.checkEmpty(map.get("TEAM_TYPE")) == false){
				String teamType = map.get("TEAM_TYPE").toString();
				map.remove("TEAM_TYPE");
				if(teamType.equals("1")){
					map.put("TEAM_TYPE", "小学");
				}else if(teamType.equals("2")){
					map.put("TEAM_TYPE", "初中");
				}else if(teamType.equals("3")){
					map.put("TEAM_TYPE", "高中");
				}else{
					map.put("TEAM_TYPE", "混合");
				}
			}

			return map;
		}else{
			map = new HashMap<String,Object>();
			map.put("MATCHTIMES", 0);
			map.put("AVGWIN",0);
			map.put("TEAM_SCORE", 0);
			map.put("win", 0);
			map.put("fail", 0);
			map.put("AVGWIN",0);
			map.put("TEAM_TYPE", "无");
			return map;
		}
	}

	/**
	 * 计算球员积分
	 * @author ljl
	 * @createDate 2017-05-03 16:59:48
	 * @param pid
	 * @return
	 */
	public int getPlayerScore(Object pid){
		int score = 0;
		if(V.checkEmpty(pid) == false){
			try{
				Map<String, Object> map = teamLoginDao.findTeamPlayerScore(pid);
				if(map!=null){
					score = TeamUtil.calculaPlayerScore(map);
				}
			}catch(Exception e){

				log.error(e.getMessage());
			}
		}
		return score;
	}

}
