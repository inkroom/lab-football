package log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogStash {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        logger.info("------------ info信息1");
//        logger.info("info 121");
//        logger.debug("sf1");
//        logger.warn("警告1");
//        logger.error("-------error 错误信息1");
//        logger.error("----error----");
//
//        try {
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //测试多行有意义的输出
//        logger.info("干扰航1");
//        logger.info(" Preparing:第一行\n第一行还有的");
//        logger.info(" Parameters:第二行");
//        logger.info("干扰航3");
//        logger.info(" Total: 第三行");
//        logger.info("干扰航2");
    }
}
