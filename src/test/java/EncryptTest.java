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

        System.out.println(aesEncryptUtil.decrypt("33CD2E2B0084DF479019782865DA6AE6"));

//        vnlisdis23x
//        38BEA424D2B5989DB3435F802F10AF93


        AESEncryptUtil util = new AESEncryptUtil("gate_1234");

        System.out.println(util.encrypt("vnlisdis23x"));


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
//        result = aesEncryptUtil.encrypt("3f32aaffb30d99e1");
//        System.out.println("加密结果："+result);
//        result = aesEncryptUtil.decrypt(result);
//        System.out.println("解密结果："+result);
    }
}
