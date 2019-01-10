import cn.inkroom.web.money.gate.utils.encrypt.AESEncryptUtil;
import cn.inkroom.web.money.gate.utils.encrypt.DecryptAble;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-util.xml")
public class EnTest {

    @Autowired
    private DecryptAble decryptAble;

    @Autowired
    private AESEncryptUtil encryptUtil;

    @Test
    public void encryptUtil() {


        String result = "gate";
        result = encryptUtil.encrypt(result);
        System.out.println("加密结果=" + result);
        System.out.println("解密结果=" + encryptUtil.decrypt(result));


    }
}
