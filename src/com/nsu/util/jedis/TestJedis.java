package com.nsu.util.jedis;

import com.nsu.util.InfoProtUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.util.jedis
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/5/17 21:41
 */
public class TestJedis {
    @Test
    public void testJedisClientPool() throws Exception{
        //初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        //使用JedisClient对象操作redis
//        jedisClient.set("testA", "mytest");
//        jedisClient.set("testB",1*69,"mytestB");
//        String result = jedisClient.get("testB");
//        System.out.println(result);
        System.out.println(InfoProtUtil.xorInfo("13458187837"));
    }
}
