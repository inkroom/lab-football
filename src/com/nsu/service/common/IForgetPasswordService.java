/**
 * @Title: IForgetPasswordService.java 
 * @Package com.nsu.service.common  
 * @Description: 忘记密码接口 
 * @author 朱明民 
 * @date 2017年4月14日 上午9:24:39
 * @version V1.0 
 */
package com.nsu.service.common;

import java.util.Map;

/** 
 * @ClassName: IForgetPasswordService   
 * @Description: 忘记密码接口 
 * @date 2017年4月14日 上午9:24:39   
 * @author 朱明民  
 *   
 */
public interface IForgetPasswordService {
	
	/**
	 * 调用dao根据用户名查询账号是否存在
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getUsernameById(String username,String type) throws Exception;
	
	/**
	 * 调用dao根据用户名查询账号信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUsernameInfoById(String username,String type) throws Exception;
	
	
	/**
	 * 调用dao更新密码
	 * @param map
	 * @throws Exception
	 */
	public void updateUsernamePassword(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据邮箱查询账号
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUsernameEmail(String email) throws Exception;
	
	/**
	 * 更新验证码
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateEmailCode(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据用户名查询邮箱验证码
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getEmailCode(String A_USERNAME,String type) throws Exception;
}
