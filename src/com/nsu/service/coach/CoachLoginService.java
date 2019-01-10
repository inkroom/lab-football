package com.nsu.service.coach;

import java.util.Map;

public interface CoachLoginService {
	boolean coachLogin(String name, String pass,String salt);
	Map<String, Object> coachInfo(String phone);
	Map<String, Object> getUserInfo(String username,String pushInfo,String deviceInfo);
}

