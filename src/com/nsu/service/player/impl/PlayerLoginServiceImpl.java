package com.nsu.service.player.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.nsu.staticvar.CommonVar;
import com.nsu.util.InfoProUtil;
import com.nsu.util.StringHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.nsu.dao.player.PlayerLoginDao;
import com.nsu.service.player.login.PlayerLoginService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
* @Title: PlayerLoginServiceImpl.java
* @Package com.nsu.service.player.impl
* @Description: 球员登录PlayerLoginService实现类
* @author 侯松梁
* @date 2017年4月10日 下午7:31:06
* @version V1.0
 */
@Service
public class PlayerLoginServiceImpl implements PlayerLoginService {
	private final Logger logger=Logger.getLogger(getClass());
	@Resource
	PlayerLoginDao playerLoginDao;

	@Override
	public Map<String, Object> getPlayer(Map<String, Object> map) throws Exception {
		logger.info(map.toString());
		return playerLoginDao.getPlayer(map);
	}
	@Override
	@Transactional
	public Boolean insertUser(String user, String passwd, String phone,String bir) throws Exception {
		HashMap<String, Object> param=new HashMap<>();
		param.put("username", phone);
		param.put("password", passwd);
		param.put("id_card", user);
		param.put("phone", phone);
		param.put("bri", bir);
		int result=playerLoginDao.insertUser(param);
		Long a_id=playerLoginDao.getA_ID(phone);
		//
		param.put("a_id", a_id);
		playerLoginDao.insertAccountToPlayer(param);
		//
		Long p_id=playerLoginDao.getPid(a_id);
		param.put("p_id", p_id);
		playerLoginDao.insertPyScore(param);
		logger.info(param);
		playerLoginDao.updateBir(bir, a_id);
		return result == 1;
	}
	@Override
	public Boolean userIsExist(String username) throws Exception {
		return playerLoginDao.userIsExist(username) != 0;
	}
	@Override
	public Boolean idCardIsExist(String id_card) throws Exception {
		return playerLoginDao.getID_CARD(id_card) != 0;
	}

	@Override
	public Map<String, Object> getUserInfo(String username,String pushInfo,String deviceInfo) throws Exception {
		Map<String, Object> mobile=new HashMap<>();
		mobile.put("pushInfo", pushInfo);
		mobile.put("deviceInfo", deviceInfo);
		mobile.put("username", username);
		playerLoginDao.updateMobileInfo(mobile);
		//得到球员信息
		Map<String, Object> map = playerLoginDao.getUserInfo(username);
		//计算年龄,保存
		map.put("age", StringHelper.IDCardNoToAge(InfoProUtil.xorInfo(map.get(CommonVar.Account.ID_CARD).toString())));
		//移除身份证信息
		map.remove(CommonVar.Account.ID_CARD);
		return map;
	}

	@Override
	public void updateLoginTime(Map<String, Object> map) throws Exception {
		playerLoginDao.updateLoginTime(map);
	}
	@Override
	public Map<String, Object> getUserNameById(String A_ID) throws Exception {
		return playerLoginDao.getUserNameById(A_ID);
	}
	@Override
	public String getA_ID(String user) throws Exception {
		return String.valueOf(playerLoginDao.getA_ID(user));
	}

}
