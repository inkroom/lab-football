import cn.nsu.edu.web.four.utils.encrypt.AESEncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-encrypt.xml")
public class EncryptTest {
    @Autowired
    private AESEncryptUtil aesEncryptUtil;
    @Test
    public void testEncrypt(){

        System.out.println(aesEncryptUtil.decrypt("632DF8DA3500702006256E12AC4C9DA6"));

//        String result = aesEncryptUtil.encrypt("a7217lab");
//        System.out.println("加密结果："+result);
//        result = aesEncryptUtil.decrypt(result);
//        System.out.println("解密结果："+result);
//        result = aesEncryptUtil.encrypt("ink");
//        System.out.println("加密结果："+result);
//        result = aesEncryptUtil.decrypt(result);
//        System.out.println("解密结果："+result);
//
//
//        result = aesEncryptUtil.encrypt("632DF8DA3500702006256E12AC4C9DA6");
//        System.out.println("加密结果："+result);
//        result = aesEncryptUtil.decrypt(result);
//        System.out.println("解密结果："+result);
    }
}
