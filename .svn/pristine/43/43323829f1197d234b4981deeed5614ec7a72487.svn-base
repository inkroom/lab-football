package cn.nsu.edu.web.four.daos.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * 单机redis连接池
 */

public class SingleRedisPoolImpl implements RedisPool {
    @Autowired
    private RedisTemplate<String, String> template;

//    @Autowired
//    private JedisPool pool;

    public String set(String key, String value) {
        template.opsForValue().set(key, value);
        return value;
    }

    public String set(String key, int seconds, String value) {
        template.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return value;
    }

    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public Boolean exists(String key) {
        return template.opsForValue().get(key) != null;
    }

    public Long expire(String key, int seconds) {
        return template.expire(key, seconds, TimeUnit.SECONDS) ? seconds : -1L;
    }

    public Long ttl(String key) {
        return template.getExpire(key);
    }

    public Long incr(String key) {
        return null;
    }

    public void hset(String key, String field, String value) {
        template.opsForHash().put(key,field,value);
//        return template.getExpire(key);
    }

    public String hget(String key, String field) {
        return null;
//        return pool.getResource().hget(key, field);
    }

    public Long hdel(String key, String... field) {
        return null;
//        return pool.getResource().hdel(key, field);
    }

    public Boolean del(String key) {
        template.delete(key);
        return template.getExpire(key) == null;
    }

    public void close() {

//        if (pool.getResource().isConnected()) {
//            pool.getResource().close();
//        }
    }
}
