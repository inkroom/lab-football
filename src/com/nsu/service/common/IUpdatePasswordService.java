/**
 * @Title: UpdatePasswordService.java 
 * @Package com.nsu.service.common  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年4月12日 上午9:08:13
 * @version V1.0 
 */
package com.nsu.service.common;

import java.util.Map;

/** 
 * @ClassName: UpdatePasswordService   
 * @Description: 修改密码接口
 * <详细介绍>  
 * @date 2017年4月12日 上午9:08:13   
 * @author 朱明民  
 *   
 */
public interface IUpdatePasswordService {
	
	/**
	 * 调用Dao 获取数据库中密码信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getPasswordById(int id) throws Exception;
	
	/**
	 * 调用Dao 修改数据库中密码信息
	 * @param map
	 * @throws Exception
	 */
	public void updatePassword(Map<String, Object> map) throws Exception;

}
