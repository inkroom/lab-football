package com.nsu.dao.team;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamCoachDO;

public interface TeamCoachDao {

	/**
	 * @Description: 查询教练页面信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午2:19:23
	 * @return
	 */
	public List<TeamCoachDO> selectCoachInfo(@Param("TEAM_ID") String TEAM_ID,@Param("startIndex") Object startIndex,@Param("pageSize") Object pageSize);
	
	/**
	 * @Description: 根据教练名字查教练信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午10:18:35
	 * @return
	 */
	public List<TeamCoachDO> selectCoachInfoByName(@Param("COACH_NAME") String COACH_NAME,@Param("TEAM_ID") String TEAM_ID);
	
	/**
	 * @Description: 根据教练员ID查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:37:37
	 * @return
	 */
	public TeamCoachDO selectCoachInfoByCOACHID(@Param("COACH_ID") String COACH_ID,@Param("COACH_STATUS") String COACH_STATUS);
	
	/**
	 * @Description: 通过	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	public int updatecoachone(@Param("COACH_ID")String COACH_ID,@Param("TEAM_ID")String TEAM_ID);
	
	
	/**
	 * @Description: 不通过
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	public int updatecoachtwo(@Param("COACH_ID")String COACH_ID,@Param("TEAM_ID")String TEAM_ID);
	
	
	/**
	 * @Description: 查询所有状态教练总数	
	 * @author 侯润达
	 * @create_date 2017年4月23日 下午5:23:55
	 * @return
	 */
	public int selectCoachCount(String TEAM_ID);
	
}
