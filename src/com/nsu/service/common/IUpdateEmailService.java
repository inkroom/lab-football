/**
 * @Title: IUpdateEmailService.java 
 * @Package com.nsu.service.common  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年4月27日 下午4:36:38
 * @version V1.0 
 */
package com.nsu.service.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
 * @ClassName: IUpdateEmailService   
 * @Description: 修改邮箱接口 
 * @date 2017年4月27日 下午4:36:38   
 * @author 朱明民  
 *   
 */
public interface IUpdateEmailService {

	/**
	 * 根据A_ID查询邮箱
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getEmailById(int id) throws Exception;
	
	/**
	 * 根据A_ID查询邮箱验证码
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public String getEmailCheckCode(@Param("A_ID")int A_ID) throws Exception;

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
