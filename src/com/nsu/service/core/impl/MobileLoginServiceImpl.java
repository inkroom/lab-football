package com.nsu.service.core.impl;

import com.nsu.dao.core.MobileLoginDao;
import com.nsu.dao.core.MobileTokenDao;
import com.nsu.service.core.IMobileLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: MobileLoginServiceImpl
 * @Package com.nsu.service.core.impl
 * @Description:
 * @date 4/23/17
 */

@Service("mobileLoginService")
public class MobileLoginServiceImpl implements IMobileLoginService {

	@Resource
	private MobileLoginDao mobileLoginDao;

	@Override
	public Map<String, Object> getUserByUserName(String userName, String roleType) throws Exception {
		return mobileLoginDao.getUserByUserName(userName, roleType);
	}

	@Override
	public boolean setDeviceInfo(String a_id) throws Exception {
		int result = mobileLoginDao.setDeviceInfo(a_id);
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDeviceInfo(String aid,String deviceInfo) throws Exception {
		return mobileLoginDao.updateDeviceInfo(aid,deviceInfo);
	}

}
