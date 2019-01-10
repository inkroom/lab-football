/**
 * @Title: ForgetPasswordDao.java 
 * @Package com.nsu.dao.common  
 * @Description: 忘记密码DAO 
 * @author 朱明民 
 * @date 2017年4月14日 上午9:21:54
 * @version V1.0 
 */
package com.nsu.dao.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: ForgetPasswordDao
 * @Description: 忘记密码DAO
 * @date 2017年4月14日 上午9:21:54
 * @author 朱明民
 * 
 */
public interface ForgetPasswordDao {
	/**
	 * 根据用户名查询账号是否存在
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getUsernameById(@Param("username")String username,@Param("type")String type) throws Exception;

	/**
	 * 根据用户名查询账号信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUsernameInfoById(@Param("username")String username,@Param("type")String type) throws Exception;

	/**
	 * 更新新密码
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateUsernamePassword(Map<String, Object> map) throws Exception;

	/**
	 * 更新验证码
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateEmailCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据邮箱查询账号
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUsernameEmail(String email) throws Exception;
	
	/**
	 * 根据用户名查询邮箱验证码
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getEmailCode(@Param("A_USERNAME")String A_USERNAME, @Param("type") String type) throws Exception;
}
