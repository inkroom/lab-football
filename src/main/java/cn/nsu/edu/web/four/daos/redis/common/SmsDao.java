package cn.nsu.edu.web.four.daos.redis.common;

import cn.nsu.edu.web.four.beans.common.PhoneCode;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.daos.redis.RedisPool;
import cn.nsu.edu.web.four.session.serializer.SessionSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Repository
public class SmsDao {

    @Autowired
    private RedisPool pool;
    @Autowired
    private ObjectMapper mapper;
//    @Autowired
//    private StringRedisTemplate template;

    private Logger log = LoggerFactory.getLogger(getClass());

    public boolean setPhoneCode(PhoneCode code) {
        try {
            pool.set(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + code.getPhone(), BaseStatic.SMS_RETAIN_TIME,mapper.writeValueAsString(code));
//            template.opsForValue()
//                    .set(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + code.getPhone(), mapper.writeValueAsString(code), BaseStatic.SMS_RETAIN_TIME, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PhoneCode getPhoneCode(String phone) {
        String value = null;
        try {
            value = pool.get(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + phone);
//            value = template.opsForValue()
//                    .get(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + phone);
        } catch (JedisConnectionException e) {
            return null;
        }
        if (value == null) return null;
        try {
            return mapper.readValue(value, PhoneCode.class);
        } catch (IOException e) {
            return null;
        }
    }

    public void removePhoneCode(String phone) {
//        template.delete(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + phone);
        pool.del(BaseStatic.PREFIX_REDIS + BaseStatic.SMS_PREFIX + phone);
    }


}
