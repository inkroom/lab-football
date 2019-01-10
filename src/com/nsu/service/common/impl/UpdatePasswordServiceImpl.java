/**
 * @Title: UpdatePasswordServiceImpl.java 
 * @Package com.nsu.service.common.impl  
 * @Description: 修改密码实现类
 * @author 朱明民 
 * @date 2017年4月12日 上午9:11:43
 * @version V1.0 
 */
package com.nsu.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.nsu.service.common.IUpdatePasswordService;

/** 
 * @ClassName: UpdatePasswordServiceImpl   
 * @Description: 修改密码实现类
 * <详细介绍>  
 * @date 2017年4月12日 上午9:11:43   
 * @author 朱明民  
 *   
 */

@Service("UpdatePassword")
public class UpdatePasswordServiceImpl implements IUpdatePasswordService{

	@Resource
	private com.nsu.dao.common.UpdatePasswordDao updatePasswordDao;
	
	
	/**
	 * 获取数据库密码
	 */
	public String getPasswordById(int id) throws Exception {
		 return updatePasswordDao.getPasswordById(id);
	}

	/**
	 * 修改密码
	 */
	public void updatePassword(Map<String, Object> map) throws Exception {
		updatePasswordDao.updatePassword(map);		
	}
	

}
