package com.nsu.dao.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.dao.core
 * @Description:
 * @date 17/4/20
 */
public interface MobileTokenDao {
    public List<String> getMobileToken(@Param("username") String username, @Param("password")String password, @Param("a_type")String type, @Param("aId")String aId) throws Exception;
}
