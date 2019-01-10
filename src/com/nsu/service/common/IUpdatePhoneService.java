/**
 * @Title: IUpdatePhoneService.java 
 * @Package com.nsu.service.common  
 * @Description: 修改手机号码接口
 * @author 朱明民 
 * @date 2017年4月12日 下午3:10:42
 * @version V1.0 
 */
package com.nsu.service.common;

import java.util.Map;

/** 
 * @ClassName: IUpdatePhoneService   
 * @Description: 修改手机号码接口
 * <详细介绍>  
 * @date 2017年4月12日 下午3:10:42   
 * @author 朱明民  
 *   
 */
public interface IUpdatePhoneService {
	
	/**
	 * 调用Dao 获取数据库中手机号码信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getPhoneById(Long id) throws Exception;
	
	/**
	 * 调用Dao 修改数据库中手机号码信息(非现场管理员)
	 * @param map
	 * @throws Exception
	 */
	public void updatePhone(Map<String, Object> map) throws Exception;

	/**
	 * 调用Dao 修改数据库中手机号码信息(现场管理员)
	 * @param map
	 * @throws Exception
	 */
	public void updatePhoneLive(Map<String, Object> map) throws Exception;

}
