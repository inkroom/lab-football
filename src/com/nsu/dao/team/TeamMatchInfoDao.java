package com.nsu.dao.team;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.ScheduleBean;
import com.nsu.bean.team.TeamMatchBean;

public interface TeamMatchInfoDao {

	/**
	 * @Description: 根据比赛名字查比赛信息	
	 * @author 侯润达
	 * @create_date 2017年4月20日 上午11:31:02
	 * @return
	 */
	public List<ScheduleBean> MatchInfoByName(@Param("TEAM_ID")String TEAM_ID,@Param("C_NAME")String C_NAME);
	
	
	/**
	 * @Description: 查询主队警告，罚出人员信息	
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:24:00
	 * @return
	 */
	public List<TeamMatchBean> MatchHomeTeamList(String R_ID);
	
	/**
	 * @Description: 查询客队警告，罚出人员信息
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:25:43
	 * @return
	 */
	public List<TeamMatchBean> MatchVisitTeamList(String R_ID);
	
	/**
	 * @Description: 查询主队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	public TeamMatchBean MatchHomeTeamGrade(String R_ID);
	
	/**
	 * @Description: 查询客队人数，比分
	 * @author 侯润达
	 * @create_date 2017年4月21日 下午9:26:10
	 * @return
	 */
	public TeamMatchBean MatchVisitTeamGrade(String R_ID);
	
	/**
	 * @Description: 查询所有警告信息	
	 * @author 侯润达
	 * @create_date 2017年4月20日 下午11:28:58
	 * @return
	 */
	public TeamMatchBean MatchErrorInfo(String R_ID);
	
	
	/**
	 * @Description: 查询赛程总数	
	 * @author 侯润达
	 * @create_date 2017年4月23日 下午2:18:19
	 * @return
	 */
	public int MatchComNum(@Param("TEAM_ID") String TEAM_ID,@Param("COM_ID") String COM_ID);
	
	
	/**
	 * @Description: 查询赛事所有信息
	 * @author 侯润达
	 * @create_date 2017年4月23日 下午2:18:19
	 * @return
	 */
	public List<TeamMatchBean> MatchAllInfo(@Param("TEAM_ID") String TEAM_ID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize);
	
	
	/**
	 * @Description: 查询赛事信息总数
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:35:31
	 * @return
	 */
	public int MatchAllInfoNum(String TEAM_ID);
	
	
	/**
	 * @Description: 根据赛事名查询赛事信息
	 * @author 侯润达
	 * @create_date 2017年4月19日 下午5:35:31
	 * @return
	 */
	public List<TeamMatchBean> MatchAllInfoByName(@Param("TEAM_ID")String TEAM_ID,@Param("C_NAME")String C_NAME);
}
