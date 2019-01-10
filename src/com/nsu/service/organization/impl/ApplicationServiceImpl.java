package com.nsu.service.organization.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.dao.organization.ApplicationDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.IApplicationService;

/**
 * 
 * @ClassName: ApplicationServiceImpl 
 * @Description: 实现查询所有球队的申请管理
 * @author 严涛
 * @date 2017年4月17日 下午5:31:11 
 *
 */
@Service("iApplicationService")
public class ApplicationServiceImpl extends BaseService implements IApplicationService {

	@Resource
	private ApplicationDao applicationDao;
	long org_id = 0;
	/**
	 * 查询申请的所有数据
	 */
	@Override
	public PageInfo<Map<Object, Object>> findAll(@Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize, 
			@Param("ORG_ID") long ORG_ID) throws Exception {
		PageInfo<Map<Object, Object>> pageInfo = null;
		org_id = ORG_ID;
		try{
		PageHelper.startPage(pageNum,pageSize);
		applicationDao.updateTeamAffiliation(ORG_ID);
		List<Map<Object, Object>> list = applicationDao.findAll(ORG_ID);
		
		pageInfo=new PageInfo<Map<Object,Object>>(list); 
		}catch(Exception e){
			e.getStackTrace();
		}
		return pageInfo;
	}
	

	/**
	 * 查看球队申请详情
	 */
	@Override
	public Map<String, Object> findTeamMessage(long team_id) throws Exception {
		Map<String, Object> TeamMap = applicationDao.findTeamMessage(team_id);
		List<String> coachList = new ArrayList<String>();
		applicationDao.updateTeamOrgID(org_id, team_id);
         coachList = applicationDao.selectCoachByTeamID(team_id);
         String a_name = "";
         if(coachList == null){
 			a_name = "暂无";
 		}
         for(int i = 0;i < coachList.size(); i ++){
        	 a_name = a_name+coachList.get(i)+",";
         }
		
		if(TeamMap != null){
			
			Map<String, Object> TeamMap2 = applicationDao.findTeamMessage(team_id);
			
			if(TeamMap2.get("TS_SCHOOL_VICTOR") == null  ) {
				TeamMap2.put("TS_SCHOOL_VICTOR",0 );
			 }
			if(TeamMap2.get("TS_COUNTRY_VICTOR") == null) {
				TeamMap2.put("TS_COUNTRY_VICTOR",0 );
			 }
			if(TeamMap2.get("TS_CITY_VICTOR") == null) {
				TeamMap2.put("TS_CITY_VICTOR",0 );
			 }
			if(TeamMap2.get("TS_PROVINCE_VICTOR") == null) {
				TeamMap2.put("TS_PROVINCE_VICTOR",0 );
			 }
			if(TeamMap2.get("TS_SCHOOL_LOSE") == null) {
				TeamMap2.put("TS_SCHOOL_LOSE",0 );
			 }
			if(TeamMap2.get("TS_COUNTRY_LOSE") == null) {
				TeamMap2.put("TS_COUNTRY_LOSE",0 );
			 }
			if(TeamMap2.get("TS_CITY_LOSE") == null) {
				TeamMap2.put("TS_CITY_LOSE",0 );
			 }
			if(TeamMap2.get("TS_PROVINCE_LOSE") == null) {
				TeamMap2.put("TS_PROVINCE_LOSE",0 );
			 }
			if(TeamMap2.get("TS_SCHOOL_DRAW") == null ) {
				TeamMap2.put("TS_SCHOOL_DRAW",0 );
			 }
			if(TeamMap2.get("TS_COUNTRY_DRAW") == null) {
				TeamMap2.put("TS_COUNTRY_DRAW",0 );
			 }
			if(TeamMap2.get("TS_CITY_DRAW") == null  ) {
				TeamMap2.put("TS_CITY_DRAW",0 );
			 }
			if(TeamMap2.get("TS_PROVINCE_DRAW") == null ) {
				TeamMap2.put("TS_PROVINCE_DRAW",0 );
			 }
			if(TeamMap2.get("TS_OTHER_WIN") == null ) {
				TeamMap2.put("TS_OTHER_WIN",0 );
			 }
			if(TeamMap2.get("TS_OTHER_LOSS") == null ) {
				TeamMap2.put("TS_OTHER_LOSS",0 );
			 }
			
			if(TeamMap2.get("TEAM_NUM") == null ){
				TeamMap2.put("TEAM_NUM","暂无");
			}
			if(TeamMap2.get("TEAM_NAME") == null ){
				TeamMap2.put("TEAM_NAME","暂无");
			}
			if(TeamMap2.get("TEAM_LEADER") == null){
				TeamMap2.put("TEAM_LEADER","暂无");
			}
			int TEAM_AUDIT = (int) TeamMap2.get("TEAM_AUDIT");
			if(TeamMap2.get("TEAM_AFFILIATION") == null ||  TEAM_AUDIT == 0 ||  TEAM_AUDIT == 2 ){
				TeamMap2.put("TEAM_AFFILIATION","暂无");
			}
			
			//胜利场数
			int school_victor = (int) TeamMap2.get("TS_SCHOOL_VICTOR");
			int country_victor = (int) TeamMap2.get("TS_COUNTRY_VICTOR");
			int city_victor = (int) TeamMap2.get("TS_CITY_VICTOR");
			int province_victor = (int) TeamMap2.get("TS_PROVINCE_VICTOR");
			//失败场数
			int school_lose = (int) TeamMap2.get("TS_SCHOOL_LOSE");
			int country_lose = (int) TeamMap2.get("TS_COUNTRY_LOSE");
			int city_lose = (int) TeamMap2.get("TS_CITY_LOSE");
			int province_lose = (int) TeamMap2.get("TS_PROVINCE_LOSE");
			
			//球队胜输场次
			int ts_other_win = (int) TeamMap2.get("TS_OTHER_WIN")+school_victor+country_victor+city_victor+province_victor;
			int ts_other_loss = (int) TeamMap2.get("TS_OTHER_LOSS")+school_lose+country_lose+city_lose+province_lose;
			int all_team = ts_other_win+ts_other_loss;
			
			//球队积分
			int team_score = ts_other_win*3+ts_other_loss;
					
			
			if(all_team == 0){
				TeamMap2.put("team_win_probability",0);
			}else{
				double a = (double)ts_other_win;
				double b = (double)all_team;
				//球队平均胜率
				double team_win_probability = a/b;
				BigDecimal bg = new BigDecimal(team_win_probability).setScale(4,BigDecimal.ROUND_HALF_UP);
				double temp = bg.doubleValue()*100;
				if(temp == 0 ){
					TeamMap2.put("team_win_probability","0%");
				}else{
					TeamMap2.put("team_win_probability",new DecimalFormat("#,##0.00").format(new Double(temp))+"%");
				}
			}
			TeamMap2.put("team_score",team_score);
			TeamMap2.put("team_Match_play", all_team);
			TeamMap2.put("A_NAME", a_name);
			return TeamMap2;
			
		}else{
			Map<String, Object> TeamMap1 = new HashMap<>();
			TeamMap1.put("team_score",0 );
			TeamMap1.put("team_Match_play",0 );
			TeamMap1.put("team_win_probability","0%" );
			TeamMap1.put("TEAM_NAME","暂无" );
			TeamMap1.put("TEAM_LEADER","暂无" );
			TeamMap1.put("TEAM_NUM", "暂无");
			TeamMap1.put("TEAM_AFFILIATION","暂无" );
			TeamMap1.put("A_NAME", a_name);
			return TeamMap1;
		}
	}

	/**
	 * 查看球员信息
	 */
	@Override
	public List<Map<String, Object>> findPlayerMessage(long team_id) throws Exception {
		
		 List<Map<String, Object>> playersList = new ArrayList<Map<String, Object>>(); 
		 playersList = applicationDao.findPlayerMessage(team_id);
		 Map<String, Object> playerMap = new HashMap<String, Object>();
		 int allScore = 0;//球队总积分
		 for(int i= 0;i<playersList.size();i++){
			 playerMap = playersList.get(i);
			 
			 int city_win = (int) playerMap.get("PS_CITY_WIN");
			 int city_lose = (int) playerMap.get("PS_CITY_LOSE");
			 
			 int provine_win = (int) playerMap.get("PS_PROVINE_WIN");
			 int provine_lose = (int) playerMap.get("PS_PROVINE_LOSE");
			 
			 int county_win = (int) playerMap.get("PS_COUNTY_WIN");
			 int county_lose = (int) playerMap.get("PS_COUNTY_LOSE");
			 
			 int school_win = (int) playerMap.get("PS_SCHOOL_WIN");
			 int school_lose = (int) playerMap.get("PS_SCHOOL_LOSE");
			 
			 int other_win = (int) playerMap.get("PS_OTHER_WIN");
			 int other_loss = (int) playerMap.get("PS_OTHER_LOSS");
			 
			 //球员总积分
			 allScore = city_win*3+provine_win*3+county_win*3+school_win*3+other_win*3+city_lose+provine_lose+county_lose+school_lose+other_loss;
			 if(playerMap.get("P_BIRTHDAY") == null){
				 playerMap.put("P_BIRTHDAY","2017-01-01" );
			 }
			 //球员年龄
			String date = playerMap.get("P_BIRTHDAY").toString(); 
			date = date.substring(0, 4);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new java.util.Date());
			int Age = Integer.parseInt(year) - Integer.parseInt(date);
			 //球员积分
			 playerMap.put("player_score", allScore);
			 playerMap.put("Age", Age);
			 
		 }
		return playersList;
}
	/**
	 * 更新通过球队信息
	 */
	@Override
	public int updateTeamStatus(@Param("team_id") long team_id,@Param("match_id") Integer match_id) throws Exception {
		int num = applicationDao.updateTeamStatus(team_id,match_id);
		if(num == 1){
			return 1;
		}
		return 0;
	}


	/**
	 * 更新不通过球队
	 */
	@Override
	public int updateTeam(@Param("team_id") long team_id,@Param("match_id") Integer match_id) throws Exception {
		int num1 = applicationDao.updateTeam(team_id,match_id);
		if(num1 == 1){
			return 1;
		}
		return 0;
	}


	/**
	 * 搜索
	 */
	@Override
	public List<Map<Object, Object>> findone(long ORG_ID, String comName) throws Exception {
		
		return applicationDao.findone(ORG_ID, comName);
	}



	/**
	 * 根据赛事名称查赛事id
	 */
	@Override
	public Map<String, Object> selectMatchIDByMatchName(String match_name) throws Exception {
		
		return applicationDao.selectMatchIDByMatchName(match_name);
	}

	/*根据机构id查机构A_ID*/
	@Override
	public int selectAId(long org_id) throws Exception {
		
		return applicationDao.selectAId(org_id);
	}

	
	/*根据机构id查机构名称*/
	@Override
	public String selectOrgName(long org_id) throws Exception {
		return applicationDao.selectOrgName(org_id);
	}

}