/**
 * 
 */
package com.nsu.service.coach;

import java.util.List;


/**    
* @Title: CoachTrainningIssue.java
* @Package com.nsu.service.coach 
* @Description: TODO(䐧᳿⋤嫜㋎廯嫤㒆᷵Ὑᶿᵇ) 
* @author 潘泳言   * @date 2017年4月17日 下午2:24:42  
* @version V1.0    */
public interface CoachTrainingService {

	public boolean coachTrainningInfo(String title,String teamid,String place,String time,
		String	typs, String coachid);
	/**
	 * 通过教练员的ID搜索所有球队
	 * @param coachID
	 * @return
	 */
	public List<String> getCoachTeams(String coachID);
	
	public boolean coachSystemInfo(String A_ID,int SM_TYPE,String SM_TITLE,String SM_TEXT,String  SM_DATE,int  SM_STATUS,String coachId);
	/**
	 * @param string
	 * @return
	 */
 public	 List<String> ByTeamidSelectPlayerid(String team_id);
	/**
	 * @param string
	 * @return
	 */
	public String ByPlayerSelectdeviceInfo(String string);
}
