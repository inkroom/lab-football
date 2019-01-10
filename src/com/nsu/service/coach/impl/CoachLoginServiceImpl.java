/**
 * 
 */
package com.nsu.service.coach.impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nsu.dao.coach.CoachLoginDao;
import com.nsu.dao.coach.CoachTrainingDao;
import com.nsu.service.BaseService;
import com.nsu.service.coach.CoachLoginService;
import com.nsu.util.CalcAge;
import com.nsu.util.InfoProtUtil;

/**    
* @Title: CoachLoginService.java
* @Package com.nsu.service.coach.impl 
* @Description: TODO
* @author 潘泳言   * @date 2017年4月11日 下午2:18:19  
* @version V1.0    */
@Service
public class CoachLoginServiceImpl extends BaseService implements CoachLoginService{

	@Autowired
	private  CoachLoginDao coachLoginDao;
	@Autowired
	private CoachTrainingDao coachTrainingDao;
	public InfoProtUtil infoProtUtil;	
	@Override
	public boolean coachLogin(String phone, String pass,String salt) {
		String coachPass;
	  coachPass= coachLoginDao.getCoachPass(InfoProtUtil.xorInfo(phone));
	  if(InfoProtUtil.comparePass(coachPass,salt,pass)){
		  return true;
	  }else {
		log.info("没有此用户或密码错误");
		return false;
	}	
	}
	@Override
	public Map<String, Object> coachInfo(String phone) {
		Map<String, Object> map=new HashMap<String,Object>();

		 map=coachLoginDao.getCoachInfo(InfoProtUtil.xorInfo(phone));
	
		return map;
	}
	@Override
	public Map<String, Object> getUserInfo(String username, String pushInfo, String deviceInfo) {
		log.info(username+"---"+pushInfo+"---"+deviceInfo);
		Map<String, Object> mobile=new HashMap<String,Object>();
		mobile.put("pushInfo", pushInfo);
		mobile.put("deviceInfo", deviceInfo);
		mobile.put("username", username);
		coachLoginDao.updateMobileInfo(mobile);
		Map<String, Object> map = coachLoginDao.getUserInfo(username);
		log.info(map.toString()+"service");
		map.put("COACH_BIRTHDAY",CalcAge.IDCardNoToAge(InfoProtUtil.xorInfo(map.get("A_ID_CARD").toString())));
		map.put("teams", coachTrainingDao.getCoachTeams(map.get("COACH_ID").toString()));
		   
		return map;
	}

}
