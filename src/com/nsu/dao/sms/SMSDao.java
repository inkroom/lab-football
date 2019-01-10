package com.nsu.dao.sms;

import java.util.Map;

/**
 * (短信验证码的Dao接口)
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.dao.sms
 * @date 2017/5/17 11:33
 */
public interface SMSDao {
    /**
     * 查询用户手机号是否存在
     * @param map
     * @return
     * @throws Exception
     */
    public Integer findUserMobile(Map<String, Object> map) throws Exception;

    /**
     * 保存短信发送日志
     * @param map
     * @return
     * @throws Exception
     */
    public boolean addSMSLog(Map<String, Object> map)  throws  Exception;
}
