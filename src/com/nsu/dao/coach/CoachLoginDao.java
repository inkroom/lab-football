/**
 * 
 */
package com.nsu.dao.coach;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**    
* @Title: CoachLoginDao.java
* @Package com.nsu.dao.coach 
* @Description: 
* @author 潘泳言   * @date 2017年4月11日 下午3:00:24  
* @version V1.0    */
public interface CoachLoginDao {
	/**
	 * 获得账号对应的密码
	 * @param phone
	 * @return
	 */
	public String getCoachPass(@Param("phone")String phone);
	/**
	 * 获得账号对应的教练信息
	 * @param phone
	 * @return
	 */
	public Map<String, Object> getCoachInfo(@Param("phone")String phone);
	
	/**
	 * 
	 * @Description (更新用户的手机信息)
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public int updateMobileInfo(@Param("mobile") Map<String, Object> mobile);
	
	/**
	 * 
	 * @Description (获取用户信息)
	 * @param a_id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserInfo(@Param("username") String username);
}
