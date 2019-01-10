/**
 * 
 */
package com.nsu.service.coach.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.api.log.Log;
import com.nsu.dao.coach.CoachTrainingDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachTrainingService;

import sun.util.logging.resources.logging;
/**    
* @Title: CoachTrainningServiceImpl.java
* @Package com.nsu.service.coach.impl 
* @Description: TODO 
* @author 潘泳言   * @date 2017年4月17日 下午2:27:01  
* @version V1.0    */
@Service
public class CoachTrainingServiceImpl extends BaseService implements CoachTrainingService {
 @ Autowired
 private CoachTrainingDao coachTrainingDao;
	
	@Override
	public boolean coachTrainningInfo(String title,String teamid, String place, String time, String typs,String coachid) {
		 log.info("++++++++++++-----------");
		coachTrainingDao.coachTrainingIssue(title,teamid,place, time, typs,coachid);
		
		return true;
	}


	@Override
	public List<String> getCoachTeams(String coachID) {
		return coachTrainingDao.getCoachTeams(coachID);
	}
	@Override
	public boolean coachSystemInfo(String A_ID,int SM_TYPE, String SM_TITLE, String SM_TEXT, String SM_DATE,
			int SM_STATUS,String coachId) {
		
		coachTrainingDao.coachSystemIssue(A_ID, SM_TYPE, SM_TITLE, SM_TEXT, SM_DATE, SM_STATUS,coachId);
		return true;
	}
   
	@Override
	public List<String> ByTeamidSelectPlayerid(String team_id) {
		
		return coachTrainingDao.ByTeamidSelectPlayerid(team_id);
	}
	@Override
	public String ByPlayerSelectdeviceInfo(String string) {
		
		return coachTrainingDao.ByPlayerSelectdeviceInfo(string);
	}
    
}
