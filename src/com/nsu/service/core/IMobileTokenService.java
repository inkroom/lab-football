package com.nsu.service.core;

import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.service.core
 * @Description:
 * @date 17/4/20
 */
public interface IMobileTokenService {
    public String getMobileToken(String username,String password,String type,String aId) throws Exception;
    public String getMoileCode(String aId,String type);
    public boolean checkCode(String aId,String type,String code);
}
