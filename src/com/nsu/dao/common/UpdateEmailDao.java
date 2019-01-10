/**
 * @Title: UpdateEmailDao.java 
 * @Package com.nsu.dao.common  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年4月27日 下午4:33:35
 * @version V1.0 
 */
package com.nsu.dao.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
 * @ClassName: UpdateEmailDao   
 * @Description: 修改邮箱dao
 * @date 2017年4月27日 下午4:33:35   
 * @author 朱明民  
 *   
 */
public interface UpdateEmailDao {
	
	/**
	 * 根据A_ID查询邮箱
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getEmailById(Long id) throws Exception;
	
	/**
	 * 根据A_ID查询邮箱验证码
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public String getEmailCheckCode(@Param("A_ID") Long A_ID) throws Exception;

	/**
	 * 更新验证码
	 * @param map
	 * @throws Exception
	 */
	public void updateEmailCheckCode(Map<String, Object> map) throws Exception;
	
	/**
	 * 更新邮箱
	 * @param map
	 * @throws Exception
	 */
	public void updateEmail(Map<String, Object> map) throws Exception;
}
