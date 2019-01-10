package com.nsu.test;

import com.fasterxml.jackson.databind.JavaType;
import com.nsu.bean.demo.JacksonDemoBean;
import com.nsu.bean.index.LinkBean;
import com.nsu.bean.index.PolicyBean;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.dao.admin.AdminDao;
import com.nsu.dao.admin.AdminIndexDao;
import com.nsu.dao.organization.OrgCompetitionScheduleDao;
import com.nsu.dao.team.TeamDelateDao;
import com.nsu.service.admin.IAdminIndexService;
import com.nsu.service.admin.IAdminService;
import com.nsu.service.demo.IDemoService;
import com.nsu.util.InfoProtUtil;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: IndexAdminDaoTest
 * @Package com.nsu.test
 * @Description:
 * @date 5/15/17
 */
public class IndexAdminDaoTest {
    private ApplicationContext applicationContext;
    @Before
    public void setUp() throws Exception {
        applicationContext=new ClassPathXmlApplicationContext(new String[] {
                "spring/applicationContext-trans.xml",
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-redis.xml"
        });
    }

    @org.junit.Test
    public void imgTest() throws Exception {
//        OrgCompetitionScheduleDao orgCompetitionScheduleDao = (OrgCompetitionScheduleDao)applicationContext.getBean("orgCompetitionScheduleDao");
//        System.out.println(orgCompetitionScheduleDao.checkWouldEndCom("3"));
//        System.out.println(orgCompetitionScheduleDao.checkHaveRaceCom("3"));


        TeamDelateDao teamDelateDao = (TeamDelateDao)applicationContext.getBean("teamDelateDao");
        System.out.println(teamDelateDao.wouldDelete("22"));

//        AdminDao adminDao= (AdminDao) applicationContext.getBean("adminDao");
//        try {
//
//            System.out.println(adminDao.getUserByUsername(""));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(InfoProtUtil.xorInfo("18180517987"));
        //AdminIndexDao adminIndexDao = (AdminIndexDao)applicationContext.getBean("adminIndexDao");
       // IAdminIndexService adminIndexService = (IAdminIndexService)applicationContext.getBean("adminIndexService");

     //   MobileTokenService mobileTokenService = (MobileTokenService)applicationContext.getBean("mobileTokenService");

  //      System.out.println(mobileTokenService.getMoileCode("1","1"));

  //      System.out.println(mobileTokenService.checkCode("1","1","zuuwuo8o"));
        //LinkBean linkBean = new LinkBean();
        //linkBean.setLinkAddr("123");
        //linkBean.setLinkName("123");
        //.setId("2");
        //linkBean.setLinkType("1");
        //ystem.out.println(adminIndexService.toTopLinkById("2"));

//        System.out.println(adminIndexService.deleteLinkById("1"));

        //System.out.println(JsonMapper.toJsonString(adminIndexDao.getLinkById(linkBean)));


//        System.out.println(JsonMapper.toJsonString(adminIndexService.getLinkBean(1,1,"1")));
//        System.out.println(JsonMapper.toJsonString(adminIndexDao.getLinkPageAll(null)));

//        IAdminIndexService adminIndexService  = (IAdminIndexService)applicationContext.getBean("adminIndexService");
//
//
//        try {
//            PolicyBean policyBean = new PolicyBean();
//            policyBean.setId("2");
//            System.out.println(JsonMapper.toJsonString(adminIndexDao.getPolicyById(policyBean)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        //System.out.println(JsonMapper.toJsonString(adminIndexService.getPolicyBean(1,1)));


//        String tem = "/player/login_view.html";
//        System.out.println(tem.substring(1,tem.indexOf("/",1)));
    }



}
