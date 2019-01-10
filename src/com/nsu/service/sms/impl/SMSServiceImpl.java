package com.nsu.service.sms.impl;

import com.nsu.dao.sms.SMSDao;
import com.nsu.service.sms.ISMSService;
import com.nsu.util.InfoProUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.service.sms.impl
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/5/17 15:31
 */
@Service
public class SMSServiceImpl implements ISMSService{
    @Autowired
    private SMSDao dao;
    @Override
    public boolean findUserMobile(String phone, String type) throws Exception {
        Map<String, Object> map = new HashedMap();
        map.put("phone", InfoProUtil.xorInfo(phone));
        map.put("type",type);
        if (dao.findUserMobile(map) > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean addSMSLog(Map<String, Object> map) throws Exception {

        return dao.addSMSLog(map);
    }

    @Override
    public String findPhoneByUsername(String username, int type) {
        return dao.findPhoneByUsername(username,type);
    }
}
