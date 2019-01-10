package redis;

import cn.nsu.edu.web.four.beans.common.PhoneCode;
import cn.nsu.edu.web.four.daos.redis.common.SmsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-util.xml",
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-redis.xml",
        "classpath:spring/spring-encrypt.xml"})
public class TestStringRedisTemplate {
    @Autowired
    private SmsDao dao;

    @Test
    public void test() {
        PhoneCode code = new PhoneCode();
        code.setPhone("18161202825");
        code.setCount(1);
        code.setSendTime(new Date());
        dao.setPhoneCode(code);

        code = dao.getPhoneCode("18161202825");
        System.out.println(code);
    }
}
