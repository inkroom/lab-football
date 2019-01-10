package com.nsu.service.team;

import java.util.List;

import javax.swing.ListModel;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCoachDO;


public interface ITeamCoachService {

	/**
	 * @Description: 查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午3:19:45
	 * @return
	 */
	Page selectCoachInfoService(String TEAM_ID,String num);
	
	/**
	 * @Description: 根据姓名查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午10:26:20
	 * @return
	 */
	Page selectCoachInfoByNameService(String COACH_NAME,String TEAM_ID);
	
	/**
	 * @Description: 根据教练员ID查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	public TeamCoachDO selectCoachInfoByCOACHIDService(String COACH_ID,String COACH_STATUS);
	
	
	/**
	 * @Description: 通过	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	public String updatecoachoneService(String COACH_ID,String TEAM_ID);
	
	
	/**
	 * @Description: 不通过
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	public String updatecoachtwoService(String COACH_ID,String TEAM_ID);
	
	
	
}
