package com.nsu.dao.core;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: MobileLoginDao
 * @Package com.nsu.dao.core
 * @Description:
 * @date 4/23/17
 */
public interface MobileLoginDao {
    public Map<String,Object> getUserByUserName(@Param("userName") String userName,@Param("roleType") String roleType);
   
    public int setDeviceInfo(@Param("a_id") String a_id);

    public boolean updateDeviceInfo(@Param("a_id") String a_id,@Param("deviceInfo") String deviceInfo);

}
