package com.nsu.action.school;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetInternalMethods;
import com.nsu.action.BaseAction;
import com.nsu.common.Anonymous;
import com.nsu.service.school.SchoolAdvertisementService;
import com.nsu.util.base.Clear;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school/")
public class SchoolIndex extends BaseAction  {
//    private String schoolUrl;
//    private List<Map<String, Object>> indexRollPicture;
//    private String schoolName;
//
//    private String scId;
//    private String featureId;
//
//
//    private List<Map<String, Object>> advertisementFirst;
//    private List<Map<String, Object>> advertisementSecond;
//    private List<Map<String, Object>> advertisementThird;
//    private List<Map<String, Object>> advertisementFouth1;
//    private List<Map<String, Object>> advertisementFouth2;
//    private List<Map<String, Object>> advertisementFouth3;
//    private List<Map<String, Object>> advertisementFouth4;
//
//
//    private List<Map<String, Object>> schoolNews;
//    private List<Map<String, Object>> studyRecommend;
//    private List<Map<String, Object>> footballCourse;
//    private List<Map<String, Object>> artCourse;
//
//
//    private Map<String, Object> found;

    @RequestMapping("index")
    public String index(String schoolUrl) {

        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (school == null) {
//            throw new IllegalArgumentException("");
            return "/common/error";
        } else {
            try {
                List<Map<String, Object>> indexRollPicture = getServiceManager().getSchoolService().getSchoolIndexRollImage(schoolUrl);
                // 类型 0 保留 1 校园新闻 2 教育新闻 3 合作交流 4 通知通告 5 升学推荐 6 课外练习 7 自我拓展 8 招生信息 9 招聘信息
                // 类型 0 保留 1 足球课程 2 学校球队 3 比赛展示 4 艺术课程 5 作品展示
                List<Map<String, Object>> schoolNews = getServiceManager().getSchoolService().getSchoolNewsList(schoolUrl, "1");
                List<Map<String, Object>> studyRecommend = getServiceManager().getSchoolService().getSchoolNewsList(schoolUrl, "5");
                List<Map<String, Object>> footballCourse = getServiceManager().getSchoolService().getSchoolFeatureList(schoolUrl, "1");
                List<Map<String, Object>> artCourse = getServiceManager().getSchoolService().getSchoolFeatureList(schoolUrl, "4");
                List<Map<String, Object>> advertisementFirst = getServiceManager().getSchoolAdvertisementService().getAdvertisement("1", schoolUrl);
                List<Map<String, Object>> advertisementSecond = getServiceManager().getSchoolAdvertisementService().getAdvertisement("2", schoolUrl);
                List<Map<String, Object>> advertisementThird = getServiceManager().getSchoolAdvertisementService().getAdvertisement("3", schoolUrl);
                List<Map<String, Object>> advertisementFouth1 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("1", "4", schoolUrl);
                List<Map<String, Object>> advertisementFouth2 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("2", "4", schoolUrl);
                List<Map<String, Object>> advertisementFouth3 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("3", "4", schoolUrl);
                List<Map<String, Object>> advertisementFouth4 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("4", "4", schoolUrl);

                getRequest().setAttribute("indexRollPicture", indexRollPicture);
                getRequest().setAttribute("schoolNews", schoolNews);
                getRequest().setAttribute("studyRecommend", studyRecommend);
                getRequest().setAttribute("footballCourse", footballCourse);
                getRequest().setAttribute("artCourse", artCourse);
                getRequest().setAttribute("advertisementFirst", advertisementFirst);
                getRequest().setAttribute("advertisementSecond", advertisementSecond);
                getRequest().setAttribute("advertisementThird", advertisementThird);
                getRequest().setAttribute("advertisementFouth2", advertisementFouth2);
                getRequest().setAttribute("advertisementFouth1", advertisementFouth1);
                getRequest().setAttribute("advertisementFouth3", advertisementFouth3);
                getRequest().setAttribute("advertisementFouth4", advertisementFouth4);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.error(e.getMessage());
            }
            saveLog(getRequest(), schoolUrl, "4");
            saveCount(schoolUrl);
        }
        String schoolType = null;
        try {
            schoolType = school.get("school_type").toString();
            String schoolName = school.get("school_name").toString();

            getRequest().setAttribute("schoolName", schoolName);
            getRequest().setAttribute("schoolType", schoolType);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school";
//            return "primary_school";
            case "2":
                return "/school_new/junior/junior_school";
//            return "junior_school";
            case "3":
                return "/school_new/senior/senior_school";
//            return "senior_school";
            default:
                return null;
        }
    }

    @RequestMapping("01")
    public String index01(String schoolUrl) {


        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (schoolType.equals("1")) {
            return "/school_new/primary/primary_school_01";
        } else if (schoolType.equals("2")) {
            return "/school_new/junior/junior_school_01";
        } else if (schoolType.equals("3")) {
            return "/school_new/senior/senior_school_01";
        } else {
            return null;
        }
    }

    @RequestMapping("02")
    public String index02(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_02";
            case "2":
                return "/school_new/junior/junior_school_02";
            case "3":
                return "/school_new/senior/senior_school_02";
            default:
                return null;
        }
    }

    @RequestMapping("03")
    public String index03(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_03";
            case "2":
                return "/school_new/junior/junior_school_03";
            case "3":
                return "/school_new/senior/senior_school_03";
            default:
                return null;
        }
    }

    @RequestMapping("04")
    public String index04(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_04";
            case "2":
                return "/school_new/junior/junior_school_04";
            case "3":
                return "/school_new/senior/senior_school_04";
            default:
                return null;
        }
    }

    @RequestMapping("05")
    public String index05(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_05";
            case "2":
                return "/school_new/junior/junior_school_05";
            case "3":
                return "/school_new/senior/senior_school_05";
            default:
                return null;
        }
    }

    @RequestMapping("06")
    public String index06(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception、
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_06";
            case "2":
                return "/school_new/junior/junior_school_06";
            case "3":
                return "/school_new/senior/senior_school_06";
            default:
                return null;
        }
    }

    @RequestMapping("07")
    public String index07(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            schoolType = getData(school, schoolUrl);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_07";
            case "2":
                return "/school_new/junior/junior_school_07";
            case "3":
                return "/school_new/senior/senior_school_07";
            default:
                return null;
        }
    }

    @RequestMapping("08")
    public String index08(String schoolUrl) {
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getSchoolService().getSchoolBySchoolUrl(schoolUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.error(e1.getMessage());
        }
        if (school == null) {
            throw new IllegalArgumentException("");
        }
        String schoolType = null;
        try {
            Map<String, Object> found = getServiceManager().getSchoolFoundAdminService().getFound(schoolUrl);
            schoolType = getData(school, schoolUrl);
            getRequest().setAttribute("found", found);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        switch (schoolType) {
            case "1":
                return "/school_new/primary/primary_school_08";
            case "2":
                return "/school_new/junior/junior_school_08";
            case "3":
                return "/school_new/senior/senior_school_08";
            default:
                return null;
        }
    }

    public String getData(Map school, String schoolUrl) throws Exception {
        String schoolName = school.get("school_name").toString();
        SchoolAdvertisementService service = getServiceManager().getSchoolAdvertisementService();
//        advertisementSecond = getServiceManager().getSchoolAdvertisementService().getAdvertisement("2", schoolUrl);
//        advertisementThird = getServiceManager().getSchoolAdvertisementService().getAdvertisement("3", schoolUrl);
//        advertisementFouth1 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("1", "4", schoolUrl);
//        advertisementFouth2 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("2", "4", schoolUrl);
//        advertisementFouth3 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("3", "4", schoolUrl);
//        advertisementFouth4 = getServiceManager().getSchoolAdvertisementService().getAdvertisementFouth("4", "4", schoolUrl);
        getRequest().setAttribute("advertisementSecond", service.getAdvertisement("2", schoolUrl));
        getRequest().setAttribute("advertisementThird", service.getAdvertisement("3", schoolUrl));
        getRequest().setAttribute("advertisementFouth1", service.getAdvertisementFouth("1", "4", schoolUrl));
        getRequest().setAttribute("advertisementFouth2", service.getAdvertisementFouth("2", "4", schoolUrl));
        getRequest().setAttribute("advertisementFouth3", service.getAdvertisementFouth("3", "4", schoolUrl));
        getRequest().setAttribute("advertisementFouth4", service.getAdvertisementFouth("4", "4", schoolUrl));

        getRequest().setAttribute("schoolName", schoolName);
        getRequest().setAttribute("schoolType", school.get("school_type").toString());
        return school.get("school_type").toString();
    }


}
