package com.nsu.service.core.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.dao.core.RoleAuthorityDao;
import com.nsu.service.core.IRoleAuthorityService;

@Service("iRoleAuthorityService")
public class RoleAuthorityServiceImpl implements IRoleAuthorityService {
	@Resource(name="roleAuthorityDao")
	private RoleAuthorityDao roleAuthorityDao;

	@Override
	public boolean getRoleAuthority(String roleCode, String url) throws Exception {
		// TODO Auto-generated method stub
		if (roleAuthorityDao.getRoleAuthority(roleCode, url)!=null) {
			return true;
		}else {
			return false;
		}
	}

}
