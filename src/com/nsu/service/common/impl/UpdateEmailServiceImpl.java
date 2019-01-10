/**
 * @Title: UpdateEmailServiceImpl.java 
 * @Package com.nsu.service.common.impl  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年4月27日 下午4:37:33
 * @version V1.0 
 */
package com.nsu.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.dao.common.UpdateEmailDao;
import com.nsu.service.common.IUpdateEmailService;

/** 
 * @ClassName: UpdateEmailServiceImpl   
 * @Description: 更新邮箱实现类
 * @date 2017年4月27日 下午4:37:33   
 * @author 朱明民  
 *   
 */
@Service("UpdateEmail")
public class UpdateEmailServiceImpl implements IUpdateEmailService{

	@Resource
	private UpdateEmailDao updateEmailDao;
	
	
	/**
	 * 根据A_ID查询邮箱
	 */
	@Override
	public String getEmailById(long id) throws Exception {
		return updateEmailDao.getEmailById(id);
	}


	/**
	 * 获取邮箱验证码
	 */
	@Override
	public String getEmailCheckCode(long A_ID) throws Exception {
		return updateEmailDao.getEmailCheckCode(A_ID);
	}


	/**
	 * 更新邮箱验证码
	 */
	@Override
	public void updateEmailCheckCode(Map<String, Object> map) throws Exception {
		updateEmailDao.updateEmailCheckCode(map);
	}


	/**
	 * 更新邮箱
	 */
	@Override
	public void updateEmail(Map<String, Object> map) throws Exception {
		updateEmailDao.updateEmail(map);
	}

}
