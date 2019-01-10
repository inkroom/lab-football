/**
 * @Title: UpdatePhoneDao.java 
 * @Package com.nsu.dao.common  
 * @Description: 修改手机号码DAO   
 * @author 朱明民 
 * @date 2017年4月12日 下午3:08:16
 * @version V1.0 
 */
package com.nsu.dao.common;

import java.util.Map;

/** 
 * @ClassName: UpdatePhoneDao   
 * @Description: 修改手机号码DAO  
 * <详细介绍>  
 * @date 2017年4月12日 下午3:08:16   
 * @author 朱明民  
 *   
 */
public interface UpdatePhoneDao {
	
	/**
	 * 根据id获取用户手机号码
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getPhoneById(Long id) throws Exception;
	
	/**
	 * 修改手机号码
	 * @param map
	 * @throws Exception
	 */
	public void updatePhone(Map<String, Object> map) throws Exception;

	/**
	 * 修改手机号码(现场管理员)
	 * @param map
	 * @throws Exception
	 */
	public void updatePhoneLive(Map<String, Object> map) throws Exception;

}
