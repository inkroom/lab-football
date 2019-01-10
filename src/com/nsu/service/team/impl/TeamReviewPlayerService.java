package com.nsu.service.team.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamReviewPlayerBean;
import com.nsu.staticvar.TeamVar;
import com.nsu.dao.player.PlayerInfoDao;
import com.nsu.dao.team.TeamReviewPlayerDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamReviewPlayerService;
import com.nsu.util.V;

/**
 * 球队审核申请球员service 接口实现类
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-20 15:41:43
 */
@Service("teamReviewPlayerService")
public class TeamReviewPlayerService extends BaseService implements ITeamReviewPlayerService{

	@Autowired
	TeamReviewPlayerDao teamReviewPlayerDao;
	@Resource
	PlayerInfoDao playerInfoDao;
	
	@Override
	public List<TeamCenterDO> findApplyPlayersByTeamID(String teamID) {
		try{
			return  teamReviewPlayerDao.findApplyPlayersByTeamID(teamID);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

			return null;
		}
	}

	@Override
	public TeamReviewPlayerBean findApplyPlayersByPID(String pid, String teamID) {
		//查询球员基本信息
		try{
			TeamReviewPlayerBean playerInfo = teamReviewPlayerDao.findApplyPlayersByPID(pid, teamID);
			
			if(playerInfo != null){
				if(V.checkEmpty(playerInfo.getAid()) == false){
					//查询学号、和 学校
					
					Map<String, Object> stuMap = teamReviewPlayerDao.findPlayerStuIDAndSchool(playerInfo.getAid());
					if(stuMap!=null){
						String stuID = V.checkEmpty(stuMap.get("stuID"))?"无":stuMap.get("stuID").toString();
						String school = V.checkEmpty(stuMap.get("school"))?"无":stuMap.get("school").toString();
						playerInfo.setPlayerStuID(stuID);
						playerInfo.setSchool(school);
					}
					//计算球员积分
					Map<String, Object> teamMap = teamReviewPlayerDao.getPlayerInfo(playerInfo.getAid());
					
					if(teamMap!=null && teamMap.size()>0){
						int num = TeamVar.calculaPlayerScore(teamMap);
						playerInfo.setTotalIntegral(num);
					}
				}
			}
			return playerInfo;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public String updatePlayerAuditStatus(String pid, String operType, String ptNum, String teamID) {
		if(V.checkPositiveInteger(operType, 2) == false){
			return "非法操作";
		}
		int tmpNum = Integer.parseInt(operType);
		if(tmpNum>2 || tmpNum<1){
			return "非法操作";
		}
		
		try{
			int num = teamReviewPlayerDao.updatePlayerAuditStatus(pid, operType, ptNum, teamID);
			if(num>0){
				//如果更新成功数据条数>1， 则存在异常数据
				//解决方案：
				return "";
			}
			return "操作失败";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return "操作失败";
			
		}
		
	}

	@Override
	public Page findApplyPlayersRecordsByTeamID(String teamID, String num) {
		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		log.info("teamid： ******"+teamID);
		try{
			//获取总页数
			totalRecordsNum = teamReviewPlayerDao.findApplyJoinTeamPlayersTotalRecordsNum(teamID);
			page = new Page(pageNum, totalRecordsNum, 8);
			
			page.setUrl("/team/team_player_view/");
			
			List records = teamReviewPlayerDao.findApplyJoinTeamPlayersPageRecords(teamID, page.getStartIndex(),page.getPageSize());
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return page;
	}

	@Override
	public Page findApplyPlayersRecordsByPlayerName(String teamID, String playerName) {
		int pageNum = 1;
		Page page = null;
		try{
			page = new Page(pageNum, 1, 8);
			page.setUrl("/team/team_player_view/");
			List records = teamReviewPlayerDao.findApplyJoinTeamPlayersByPlayerNamePageRecords(teamID, playerName);
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return page;
	}

	@Override
	public boolean vaildataPlayerJerseyNumberIsOnly(String ptNum, String teamID) {
		try{
			return teamReviewPlayerDao.findJerseyNumberNum(teamID, ptNum)==0;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}	
}
