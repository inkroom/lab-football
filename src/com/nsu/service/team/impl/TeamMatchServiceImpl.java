package com.nsu.service.team.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.ScheduleBean;
import com.nsu.bean.team.TeamMatchBean;
import com.nsu.controller.BaseController;
import com.nsu.dao.team.TeamMatchInfoDao;
import com.nsu.dao.team.TeamScheduleArrangeDao;
import com.nsu.service.team.ITeamMachService;
import com.nsu.util.V;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.recompile;

import net.sf.jsqlparser.expression.StringValue;

@Service("teamMatchService")
public class TeamMatchServiceImpl extends BaseController implements ITeamMachService {
	@Resource
	TeamScheduleArrangeDao teamScheduleArrangeDao;

	@Resource
	TeamMatchInfoDao teamMatchInfoDao;
	
	
	/**
	 * @Description: 查询所有赛事下的赛程信息
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:35:31
	 * @return
	 */
	@Override
	public Page MatchRecordInfoService(String TEAM_ID, String COM_ID ,String num){

		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}	
		try{
			//获取总条数
			totalRecordsNum = teamMatchInfoDao.MatchComNum(TEAM_ID,COM_ID);

			log.info("长度长度长度长度长度长度长度"+totalRecordsNum);
			page = new Page(pageNum, totalRecordsNum, 8);
			//url
			page.setUrl("/team/team_match_viewtwo/"+COM_ID+"/");
			
			List<ScheduleBean> list = teamScheduleArrangeDao.MatchRecordInfo(TEAM_ID,COM_ID,page.getStartIndex(),page.getPageSize());
			log.info("list"+list.size());
			for (int i = 0; i < list.size(); i++) {
				String HOME_GRADE;// 主队胜利场数
				String re_win = V.checkEmpty(list.get(i).getRE_WIN()) == true ? "0" : list.get(i).getRE_WIN().toString();
				String pe_win = V.checkEmpty(list.get(i).getPE_WIN()) == true ? "0" : list.get(i).getPE_WIN().toString();
				String ov_win = V.checkEmpty(list.get(i).getOV_WIN()) == true ? "0" : list.get(i).getOV_WIN().toString();
				HOME_GRADE = String.valueOf(Integer.valueOf(re_win) + Integer.valueOf(pe_win) + Integer.valueOf(ov_win));
				list.get(i).setHOME_GRADE(HOME_GRADE);

				String VISITING_GRADE;// 客队胜利场数
				String re_faile = V.checkEmpty(list.get(i).getRE_FAILE()) == true ? "0"
						: list.get(i).getRE_FAILE().toString();
				String pe_faile = V.checkEmpty(list.get(i).getPE_FAILE()) == true ? "0"
						: list.get(i).getPE_FAILE().toString();
				String ov_faile = V.checkEmpty(list.get(i).getOV_FAILE()) == true ? "0"
						: list.get(i).getOV_FAILE().toString();
				VISITING_GRADE = String
						.valueOf(Integer.valueOf(re_faile) + Integer.valueOf(pe_faile) + Integer.valueOf(ov_faile));
				list.get(i).setVISITING_GRADE(VISITING_GRADE);
				log.info("+++++++++"+list);
			}
			page.setRecords(list);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
		
		return page;
		
	}

	/**
	 * @Description: 根据赛程名称查询赛程信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:24:00
	 * @return
	 */
	@Override
	public  Page MatchInfoByNameService(String TEAM_ID, String C_NAME) {

       int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		try{
			page = new Page(pageNum, 1, 8);
			page.setUrl("/team/team_match_find/");
			List records = teamMatchInfoDao.MatchInfoByName(TEAM_ID, C_NAME);
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
       
        return page; 
	}
	

	/**
	 * @Description: 查询主队警告，罚出人员信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:24:00
	 * @return
	 */
	@Override
	public List<TeamMatchBean> MatchHomeTeamListService(String R_ID) {
		List<TeamMatchBean> list = teamMatchInfoDao.MatchHomeTeamList(R_ID);
		return list;
	}

	/**
	 * @Description: 查询客队警告，罚出人员信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:24:00
	 * @return
	 */
	@Override
	public List<TeamMatchBean> MatchVisitTeamListService(String R_ID) {
		List<TeamMatchBean> list = teamMatchInfoDao.MatchVisitTeamList(R_ID);
		return list;
		
	}

	/**
	 * @Description: 查询主队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	@Override
	public TeamMatchBean MatchHomeTeamGradeService(String R_ID) {
		TeamMatchBean list = teamMatchInfoDao.MatchHomeTeamGrade(R_ID);
		String a = list.getR_REGULAR_H_T_S()==null?"0":list.getR_REGULAR_H_T_S();// 常规赛主队得分
		String b = list.getR_OVERTIME_H_T_S()==null?"0":list.getR_OVERTIME_H_T_S();// 加时赛主队得分
		String c = list.getR_PENA_H_T_S()==null?"0":list.getR_PENA_H_T_S();// 点球决胜主队得分
		int ALL = Integer.valueOf(a) + Integer.valueOf(b) + Integer.valueOf(c);
		String all = String.valueOf(ALL);
		list.setH_GRADE(all);
		return list;
	}

	/**
	 * @Description: 查询客队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	@Override
	public TeamMatchBean MatchVisitTeamGradeService(String R_ID) {
		TeamMatchBean list = teamMatchInfoDao.MatchVisitTeamGrade(R_ID);
		String a = list.getR_REGULAR_V_T_S();// 常规赛客队得分
		String b = list.getR_OVERTIME_V_T_S();// 加时赛客队得分
		String c = list.getR_PENA_V_T_S();// 点球决胜客队得分
		String ALL = String.valueOf(Integer.valueOf(a) + Integer.valueOf(b) + Integer.valueOf(c))+"";
		log.info("+++++++++++"+ALL);
		list.setV_GRADE(ALL);
		return list;
	}

	/**
	 * @Description: 查询所有警告信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:28:58
	 * @return
	 */
	@Override
	public TeamMatchBean MatchErrorInfoService(String R_ID) {
		TeamMatchBean list = teamMatchInfoDao.MatchErrorInfo(R_ID);
		return list;
	}

	/**
	 * @Description: 查询所有赛事信息	
	 * @author 侯润达
	 * @create_date 2017年4月25日 上午11:02:21
	 * @return
	 */
	@Override
	public Page MatchAllInfoService(String TEAM_ID, String num) {
		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}	
		try{
			//获取总页数
			totalRecordsNum = teamMatchInfoDao.MatchAllInfoNum(TEAM_ID);	
			page = new Page(pageNum, totalRecordsNum, 8);	
			//url
			page.setUrl("/team/team_match_view/");
			
			List<TeamMatchBean> list = teamMatchInfoDao.MatchAllInfo(TEAM_ID,page.getStartIndex(),page.getPageSize());
			log.info("list"+list.size());
			
			page.setRecords(list);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
		
		return page;
	}
	
    /**
	 * @Description: 根据赛事名称查询赛事信息	
	 * @author 侯润达
	 * @create_date 2017年4月25日 上午11:02:21
	 * @return
	 */
	@Override
	public  Page MatchAllInfoByNameService(String TEAM_ID, String C_NAME) {
		
       int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		try{
			page = new Page(pageNum, 1, 8);
			page.setUrl("/team/team_match_find/");
			List records = teamMatchInfoDao.MatchAllInfoByName(TEAM_ID, C_NAME);
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
       
        return page; 
	}



}
