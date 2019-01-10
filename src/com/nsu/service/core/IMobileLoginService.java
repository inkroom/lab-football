package com.nsu.service.core;

import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IMobileLoginService
 * @Package com.nsu.service.core
 * @Description:
 * @date 4/23/17
 */
public interface IMobileLoginService {
    public Map<String, Object> getUserByUserName(String userName,String roleType) throws Exception;
    public boolean setDeviceInfo(String a_id)throws Exception;
    public boolean updateDeviceInfo(String aid,String deviceInfo)throws Exception;
}
