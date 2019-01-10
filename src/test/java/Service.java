import cn.nsu.edu.web.four.beans.referee.PlayerDescription;
import cn.nsu.edu.web.four.beans.referee.Report;
import cn.nsu.edu.web.four.services.common.SMSService;
import cn.nsu.edu.web.four.services.live.LiveService;
import cn.nsu.edu.web.four.services.referee.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class Service {
    @Autowired
    private LiveService service;
    @Autowired
    private SMSService smsService;
    @Autowired
    private ReportService reportService;

    private Logger log = LoggerFactory.getLogger(getClass());


    private Report report;

    private List<PlayerDescription> playerDescriptionList;

    @Before
    public void ready() {

        report = new Report();
        report.setSchId(1);
        report.setRefId(1);
//        report.setMissDes("错判信息");
        report.setRedDes("红牌");
        report.setPointDes("点球");

        report.setResult(-1);

        playerDescriptionList = new ArrayList<>();

        PlayerDescription description = new PlayerDescription();
        description.setPosition("守门员");
        description.setTeamId(1);
        description.setName("球员名字5");
        description.setDate(new Date());
        description.setNumber("15");
        description.setOther("备注");
        description.setType(1);

        playerDescriptionList.add(description);

        description = new PlayerDescription();
        description.setPosition("中锋");
        description.setTeamId(1);
        description.setName("球员名字6");
        description.setDate(new Date());
        description.setNumber("14");
        description.setType(2);
//        description.setOther("备注");

        playerDescriptionList.add(description);
    }


    @Test
    public void test() {

//        boolean result = reportService.addReport(report, playerDescriptionList);
//        log.info("结果=" + result);

//        System.out.println(smsService);

//        System.out.println(service.getAllMessage(1));
//
//        System.out.println("-----------------");
//
//        LiveMessageBean bean = new LiveMessageBean();
//        bean.setPhone("18161202825");
//        bean.setContent("测试<img src=\"1234.jpg\"/>");
//        bean.setTime(new Date());
//        System.out.println(bean.getTime());
//        System.out.println(service.addMessage(1,bean));
    }

}
