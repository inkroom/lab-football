package com.nsu.service.team;

import java.util.List;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.ScheduleBean;
import com.nsu.bean.team.TeamMatchBean;

public interface ITeamMachService {

	/**
	 * @Description: 查询所有赛事下的赛程信息
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:35:31
	 * @return
	 */
	Page MatchRecordInfoService(String TEAM_ID , String COM_ID ,String num);
	
	/**
	 * @Description: 根据赛程名称查询赛程信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:34:09
	 * @return
	 */
	Page MatchInfoByNameService(String TEAM_ID,String C_NAME);
	
	
	/**
	 * @Description: 查询主队警告，罚出人员信息	
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:24:00
	 * @return
	 */
	public List<TeamMatchBean> MatchHomeTeamListService(String R_ID);
	
	/**
	 * @Description: 查询客队警告，罚出人员信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:25:43
	 * @return
	 */
	public List<TeamMatchBean> MatchVisitTeamListService(String R_ID);
	
	/**
	 * @Description: 查询主队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	public TeamMatchBean MatchHomeTeamGradeService(String R_ID);
	
	/**
	 * @Description: 查询客队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	public TeamMatchBean MatchVisitTeamGradeService(String R_ID);
	
	/**
	 * @Description: 查询所有红牌信息	
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:28:58
	 * @return
	 */
	public TeamMatchBean MatchErrorInfoService(String R_ID);
	
	
	/**
	 * @Description: 查询赛事信息
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:35:31
	 * @return
	 */
	Page MatchAllInfoService(String TEAM_ID ,String num);
	
	
	/**
	 * @Description: 根据赛事名称查询赛事信息	
	 * @author 侯润达
	 * @create_date 2017年4月25日 下午3:06:59
	 * @return
	 */
	Page MatchAllInfoByNameService(String TEAM_ID,String C_NAME);
	
	
	
}
