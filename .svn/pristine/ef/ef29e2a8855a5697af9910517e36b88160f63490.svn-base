package com.nsu.test;

import com.nsu.bean.test.TestBean;
import com.nsu.entity.LoginInformation;
import com.nsu.exception.validate.AnalysisException;
import com.nsu.exception.validate.CustomValidateException;
import com.nsu.exception.validate.DataException;
import com.nsu.exception.validate.IllegalFormatException;
import com.nsu.service.teacher.information.ImproveInformationService;
import com.nsu.utils.V;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: TestClass
 * @Package com.nsu.test
 * @Description:
 * @date 7/10/17
 */
public class TestClass {
    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void test() {
        TestBean testBean = new TestBean();
//        testBean.setUsername("梅谢兵");
        testBean.setPassword("123");
        testBean.setConstom("123.231");
        try {
            V.validateByAnnotation(testBean, "");
        } catch (AnalysisException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (DataException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (CustomValidateException e) {
            e.printStackTrace();
            log.error(e.getMessage());

        }
    }

    @Test
    public void improveInformation() {
        LoginInformation loginInformation = new LoginInformation();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        ImproveInformationService improveInformationService = (ImproveInformationService) context.getBean("improveInformationService");
        improveInformationService.improveInformation(loginInformation);
    }
}
