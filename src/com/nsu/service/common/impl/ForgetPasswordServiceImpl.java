/**
 * @Title: ForgetPasswordServiceImpl.java 
 * @Package com.nsu.service.common.impl  
 * @Description: 忘记密码实现类
 * @author 朱明民 
 * @date 2017年4月14日 上午9:26:33
 * @version V1.0 
 */
package com.nsu.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.dao.common.ForgetPasswordDao;
import com.nsu.service.common.IForgetPasswordService;

/** 
 * @ClassName: ForgetPasswordServiceImpl   
 * @Description: 忘记密码实现类
 * @date 2017年4月14日 上午9:26:33   
 * @author 朱明民  
 *   
 */
@Service("ForgetPassword")
public class ForgetPasswordServiceImpl implements IForgetPasswordService{

	@Resource
	private ForgetPasswordDao forgetPasswordDao;
	
	/**
	 * 根据账号查询是否存在
	 */
	@Override
	public int getUsernameById(String username,String type) throws Exception {
		// TODO Auto-generated method stub
		return forgetPasswordDao.getUsernameById(username,type);
	}

	/**
	 * 更新密码
	 */
	public void updateUsernamePassword(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		forgetPasswordDao.updateUsernamePassword(map);
	}

	/**
	 * 查询用户信息
	 */
	public Map<String, Object> getUsernameInfoById(String username,String type) throws Exception {
		// TODO Auto-generated method stub
		return forgetPasswordDao.getUsernameInfoById(username,type);
	}

	
	/**
	 * 根据邮箱查询账号
	 */
	public Map<String, Object> getUsernameEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return forgetPasswordDao.getUsernameEmail(email);
	}

	/**
	 * 插入邮箱验证码
	 */
	public void updateEmailCode(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		forgetPasswordDao.updateEmailCode(map);
	}

	/**
	 * 查询邮箱验证码
	 */
	public String getEmailCode(String A_USERNAME,String type) throws Exception {
		// TODO Auto-generated method stub
		return forgetPasswordDao.getEmailCode(A_USERNAME,type);
	}

	

}
