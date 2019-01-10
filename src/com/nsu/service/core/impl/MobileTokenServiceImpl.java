package com.nsu.service.core.impl;

import com.nsu.common.mapper.JsonMapper;
import com.nsu.dao.core.MobileTokenDao;
import com.nsu.service.core.IMobileTokenService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.jedis.JedisClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.service.core.impl
 * @Description:
 * @date 17/4/20
 */
@Service("mobileTokenService")
public class MobileTokenServiceImpl implements IMobileTokenService {
    @Resource
    private MobileTokenDao mobileTokenDao;

    @Resource
    private JedisClient jedisClientPool;


    @Override
    public String getMobileToken(String username, String password, String type, String aId) throws Exception {
        List<String> result = mobileTokenDao.getMobileToken(username,password,type,aId);
        if (result.size() == 1){
            return result.get(0);
        }else {
            return null;
        }

    }

    @Override
    public String getMoileCode(String aId, String type) {
        String key = InfoProtUtil.xorInfo(aId+type);
        if (jedisClientPool.exists(key)){
            String[] value = (String[]) JsonMapper.fromJsonString(jedisClientPool.get(key),String[].class);
            return value[0];
        }else {
            String value = InfoProtUtil.getRandomString(6);
            String[] allValue = new String[]{value,"0"};
            jedisClientPool.set(key,60*5, JsonMapper.toJsonString(allValue));
            return value;
        }
    }

    @Override
    public boolean checkCode(String aId, String type, String code) {
        String key = InfoProtUtil.xorInfo(aId+type);
        try {
            String[] value = (String[]) JsonMapper.fromJsonString(jedisClientPool.get(key),String[].class);
            int reCheck = Integer.parseInt(value[1]);
            if (reCheck > 4){
                jedisClientPool.del(key);
                return false;
            }else {
                jedisClientPool.set(key,JsonMapper.toJsonString(new String[]{value[0], String.valueOf(++reCheck)}));
                boolean result = code.equals(value[0]);
                if (result){
                    jedisClientPool.del(key);
                }
                return result;
            }
        }catch (Exception e){
            return false;
        }
    }
}
