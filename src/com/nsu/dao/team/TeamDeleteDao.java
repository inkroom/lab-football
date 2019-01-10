package com.nsu.dao.team;

import com.nsu.bean.team.TeamCenterDO;

/**
 * 
* @ClassName: 球队删除队员教练
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月17日 下午10:53:43 
*
 */
public interface TeamDeleteDao {


	public boolean wouldDelete(String teamId) throws Exception;

	/**
	 * @Description:删除球队学生	
	 * @author 侯润达
	 * @create_date 2017年4月17日 下午10:59:26
	 * @return
	 */
	public int delatestu(TeamCenterDO teamCenterDO);
	
	
	/**
	 * @Description:删除球队教练员
	 * @author 侯润达
	 * @create_date 2017年4月17日 下午11:00:17
	 * @return
	 */
	public int delatecoach(TeamCenterDO teamCenterDO);
}
