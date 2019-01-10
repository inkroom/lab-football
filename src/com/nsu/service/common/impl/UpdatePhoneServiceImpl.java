/**
 * @Title: UpdatePhoneServiceImpl.java 
 * @Package com.nsu.service.common.impl  
 * @Description: 修改手机号码实现实现类
 * @author 朱明民 
 * @date 2017年4月12日 下午3:12:58
 * @version V1.0 
 */
package com.nsu.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.dao.common.UpdatePhoneDao;
import com.nsu.service.common.IUpdatePhoneService;

/** 
 * @ClassName: UpdatePhoneServiceImpl   
 * @Description: 修改手机号码实现实现类
 * <详细介绍>  
 * @date 2017年4月12日 下午3:12:58   
 * @author 朱明民  
 *   
 */
@Service("UpdatePhone")
public class UpdatePhoneServiceImpl implements IUpdatePhoneService{

	@Resource
	private UpdatePhoneDao updatePhoneDao;
	
	/**
	 * 查询手机号码
	 */
	public String getPhoneById(int id) throws Exception {
		return updatePhoneDao.getPhoneById(id);
	}

	/**
	 * 修改手机号码
	 */
	public void updatePhone(Map<String, Object> map) throws Exception {
		updatePhoneDao.updatePhone(map);
	}


	/**
	 * 修改手机号（现场管理员）
	 */
	public void updatePhoneLive(Map<String, Object> map) throws Exception {
		updatePhoneDao.updatePhoneLive(map);
	}

}
