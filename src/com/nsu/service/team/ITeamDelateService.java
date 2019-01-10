package com.nsu.service.team;

import com.nsu.bean.team.TeamCenterDO;

/**
 * 
* @ClassName: 删除service
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月18日 上午10:15:26 
*
 */
public interface ITeamDelateService {

	/**
	 * 判断是否在比赛，如果在比赛。则无法删除
	 * @param teamId
	 * @return
	 */
	public boolean wouldDelete(String teamId) throws Exception;

	/**
	 * @Description: 删除学生
	 * @author 侯润达
	 * @create_date 2017年4月18日 下午4:00:45
	 * @return
	 */
	public String delatestu(TeamCenterDO teamCenterDO);
	
	/**
	 * @Description: 删除教练	
	 * @author 侯润达
	 * @create_date 2017年4月18日 下午4:01:14
	 * @return
	 */
	public String delatecoach(TeamCenterDO teamCenterDO);
}
