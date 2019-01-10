package com.nsu.service.site.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.nsu.dao.site.SiteLoginDao;
import com.nsu.service.site.SiteService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.V;

import javax.annotation.Resource;


@Service
public class SiteServiceImpl implements SiteService {

	@Resource
	SiteLoginDao site;

	/**
	 * 查询所有存在的账号放入list
	 */
	@Override
	public boolean selectName(String user_name) throws  Exception{
		boolean isExist = false;
		List<String> names = site.selectName();
		for (int i = 0; i < names.size(); i++) {
			if (user_name.equals(names.get(i))) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 通过用户输入的账号匹配密码
	 */
	@Override
	public String selectPassword(String name) throws  Exception{
		String Repassword = site.selectPasswordByName(name);
		if (Repassword == null || Repassword.equals("")) {
			Repassword = "error";
		}
		return Repassword;
	}

	@Override
	public String selectA_ID(String name) throws  Exception{
		int a_id = site.selectAIDByName(name);
		
		// TODO
		if (String.valueOf(a_id) == null || String.valueOf(a_id).equals("")) {
			a_id = 0;
		}
		return String.valueOf(a_id);
		
	}

	@Override
	public String PreInfo(int AID) throws  Exception{
		String result = "notPre";
		Map<String, String> map = site.selectInfoByAID(AID);
		if (map != null) {
			if (map.get("A_NAME") != null && !map.get("A_NAME").equals("") && map.get("A_PHONE") != null
					&& !map.get("A_PHONE").equals("")) {
				result = "isPre";
			}
		}
		return result;
	}

	@Override
	public String insertPreInfo(String name, String phone, int AID) throws  Exception{
		String result = "success";
		
		if (name == null || name.equals("") || phone == null || phone.equals("")) {
			result = "姓名或手机号为空";
		} else {
			if (!V.checkPersonName(name, 6)) {
				result = "姓名格式错误";
			} else if (!V.checkPhone(phone)) {
				result = "手机号格式出错";
			} else {
				String InPhone  = InfoProtUtil.xorInfo(phone);
				site.insertPreInfo(name, InPhone, AID);
			}
		}
		return result;
	}

	@Override
	public boolean selectStatus(String userName) throws  Exception{
		boolean isUse = false;
		if (userName == null) {
			return isUse;
		} else {
			if (String.valueOf(site.selectStatus(userName))==null){
				return isUse;
			}else {
				int status = site.selectStatus(userName);
				if (status == 1) {
					isUse = true;
				} else {
					isUse = false;
				}
			}
		}
		return isUse;
	}

	@Override
	public String selectuserName(int A_ID) throws  Exception{
		String userName = site.selectUserName(A_ID);
		if (userName == null || userName.equals("")) {
			userName = "现场管理员";
		}
		return userName;
	}

	@Override
	public Map<String, Object> getUserInfo(String username, String pushInfo, String deviceInfo) throws Exception {
		Map<String, Object> mobile=new HashMap<String,Object>();
		mobile.put("pushInfo", pushInfo);
		mobile.put("deviceInfo", deviceInfo);
		mobile.put("username", username);
		site.updateMobileInfo(mobile);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> Temp = site.getUserInfo(username);
		if (Temp!=null) {
			for (String key : Temp.keySet()) {
				if (key.equals("R_START_TIME")){
					resultMap.put(key, String.valueOf(Temp.get(key)));
				}else {
					resultMap.put(key,Temp.get(key));
				}
			}
		}
		return resultMap;
	}

	@Override
	public long selectSE_ID(int AID) throws Exception {
		return site.selectSEID(AID);
	}


}
