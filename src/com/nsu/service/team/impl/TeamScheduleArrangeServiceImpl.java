package com.nsu.service.team.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.ScheduleBean;
import com.nsu.dao.team.TeamScheduleArrangeDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamScheduleArrangeService;
/**
 * 球队安排球员service接口实现类
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-21 13:44:46
 */
@Service("teamScheduleArrangeService")
public class TeamScheduleArrangeServiceImpl extends BaseService implements ITeamScheduleArrangeService{

	@Autowired
	TeamScheduleArrangeDao teamScheduleArrangeDao;

	@Override
	public int findSchedulesNum(String comID, String teamID) {
		try{
			return teamScheduleArrangeDao.findSchedulesNum(comID, teamID);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String findAddMatchPlayersID(String teamID, String rID) {
		String isPlanMatchPlayerIDStr = "";
		try{
			List<String> list = teamScheduleArrangeDao.findPlanMatchPlayers(teamID, rID);
			if(list!=null && list.size()>0){
				for(int i=0; i < list.size(); i++){
					isPlanMatchPlayerIDStr += "-" + list.get(i);
				}
				log.info("已经安排参赛的球员："+isPlanMatchPlayerIDStr);
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return isPlanMatchPlayerIDStr;
	}

	@Override
	public String updateArrangePlayers(String teamID, String pid, String rID, String type) {
		//判断是移除还是添加
		log.info("teamID:" + teamID + pid + rID + type);
		if(type.equals("0") == true){
			//添加; ，有记录则判断审核状态是否为待审核
				//先判断是否有记录
			
			try{
				int record = teamScheduleArrangeDao.findCountRecordMatchPlayers(teamID, pid, rID);
				int num = -1;
				if(record>0){
					//防止脏数据
					if(record>1){
						num = teamScheduleArrangeDao.updateMatchPlayersStatus(teamID, pid, rID, 3+"");
						if(num<1){
							log.info("**********1*");
							return "操作失败,请重试!";
						}
						//执行插入操作
						num = teamScheduleArrangeDao.insertMatchPlayers(teamID, pid, rID);
						if(num > 0){
							return null;
						}
						log.info("*********2**");
						return "操作失败,请重试!";
					}else{
						num = teamScheduleArrangeDao.updateMatchPlayersStatus(teamID, pid, rID, 0+"");
						if(num > 0){
							return null;
						}
						log.info("*******3****");
						return "操作失败,请重试!";
					}
				}else{
					//执行插入操作
					num = teamScheduleArrangeDao.insertMatchPlayers(teamID, pid, rID);
					if(num > 0){
						return null;
					}
					log.info("******4*****");
					return "操作失败,请重试!";
				}
			}catch(Exception e){
				log.error(e.getMessage());
				log.info("*******5****");
				return "操作失败,请重试!";
			}
			
		}else{
			//移除； 直接执行更新操作，将审核状态改为3删除
			try{
				int num = teamScheduleArrangeDao.updateMatchPlayersStatus(teamID, pid, rID, 3+"");
				if(num > 0){
					return null;
				}
				log.info("*******6****");
				return "操作失败,请重试!";
			}catch(Exception e){
				log.error(e.getMessage());
				log.info("*******7****");
				return "操作失败,请重试!";
			}
		}
	}

	@Override
	public Page findSchedulesByComIDRecords(String comID, String teamID, String num) {
		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		if(num!=null && !num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		try{
			//获取总页数
			totalRecordsNum = teamScheduleArrangeDao.findSchedulesNum(comID, teamID);
			page = new Page(pageNum, totalRecordsNum, 8);
			
			page.setUrl("/team/team_schedule_arrange_view/"+comID+"/");
			
			List records = ProcessingMatchData(teamScheduleArrangeDao.findSchedulesByComIDPageRecords(teamID, comID, page.getStartIndex(),page.getPageSize()), teamID);
			page.setRecords(records);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
		return page;
	}

	/**
	 * 对数据进一步处理
	 * @author ljl
	 * @createDate 2017-04-24 17:04:36
	 * @param findSchedulesByComIDPageRecords
	 * @return
	 */
	public List<ScheduleBean> ProcessingMatchData(List<ScheduleBean> scheduleList,String teamID) {

		int num = 0;
		for(int i = 0; i < scheduleList.size(); i++){
			//查询已安排上场的人数
			try{
				num = teamScheduleArrangeDao.findCountArrangedNum(teamID, scheduleList.get(i).getrID());
			}catch(Exception e){
				log.error(e.getMessage());
				num = 0;
			}
			scheduleList.get(i).setArrangedNum(num+"");
			if(scheduleList.get(i).getrHTeamID().equals(teamID) == false){
				//主队不是当前球队；将主队和客队调换
				String tmpTeamBadge = scheduleList.get(i).getrHTeamBadge();
				String tmpTeamName = scheduleList.get(i).getrHTeamName();
				String tmpTEamID = scheduleList.get(i).getrHTeamID();
				//主队
				scheduleList.get(i).setrHTeamID(teamID);
				scheduleList.get(i).setrHTeamBadge(scheduleList.get(i).getrVTeamBadge());
				scheduleList.get(i).setrHTeamName(scheduleList.get(i).getrVTeamName());
				//客队
				scheduleList.get(i).setrVTeamID(tmpTEamID);
				scheduleList.get(i).setrVTeamBadge(tmpTeamBadge);
				scheduleList.get(i).setrVTeamName(tmpTeamName);
			}
		}
		return scheduleList;
	}

}
