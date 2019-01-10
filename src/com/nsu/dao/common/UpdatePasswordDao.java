/**
 * @Title: UpdatePasswordDao.java 
 * @Package com.nsu.dao.common  
 * @Description: 修改密码DAO  
 * @author 朱明民 
 * @date 2017年4月12日 上午9:01:24
 * @version V1.0 
 */
package com.nsu.dao.common;

import java.util.Map;

/** 
 * @ClassName: UpdatePasswordDao   
 * @Description: 修改密码DAO  
 * <详细介绍>  
 * @date 2017年4月12日 上午9:01:24   
 * @author 朱明民  
 *   
 */
public interface UpdatePasswordDao {
	
	/**
	 * 根据id获取用户密码
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getPasswordById(int id) throws Exception;
	
	/**
	 * 修改密码
	 * @param map
	 * @throws Exception
	 */
	public void updatePassword(Map<String, Object> map) throws Exception;

}
