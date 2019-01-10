/**
 * 
 */
package com.nsu.service.organization.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.dao.organization.OrgDao;
import com.nsu.service.organization.OrgService;

/**    
* @Title: OrgServiceImpl.java
* @Package com.nsu.service.organization.impl 
* @Description: TODO(䐧᳿⋤嫜㋎廯嫤㒆᷵Ὑᶿᵇ) 
* @author 潘泳言   * @date 2017年4月10日 下午4:23:15  
* @version V1.0    */
@Service
public class OrgServiceImpl implements OrgService{
	@Autowired
	private OrgDao orgDao;
	@Override
	public boolean orgLogin(String name, String pwd) {
		//orgDao.orgLogin(name, pwd);
		return true;
	}

}
