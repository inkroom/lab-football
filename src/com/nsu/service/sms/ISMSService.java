package com.nsu.service.sms;

import java.util.Map;

/**
 * 手机验证码的业务处理接口
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.service.sms
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/5/17 15:26
 */
public interface ISMSService {
    /**
     * 查询用户手机号是否被使用
     * @param phone
     * @return
     * @throws Exception
     */
    public boolean findUserMobile(String phone, String type) throws Exception;

    /**
     * 保存短信发送日志
     * @param map
     * @return
     * @throws Exception
     */
    public boolean addSMSLog(Map<String, Object> map) throws  Exception;

    /**
     * 根据用户名查找手机号
     * @param map
     * @return
     * @throws Exception
     */
    public String findPhoneByUsername(String username,int type);
}

