package cn.edu.nsu.lib.controllers.student;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.student.SLabBean;
import cn.edu.nsu.lib.bean.student.SMajorBean;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.services.student.StuBaseService;
import cn.edu.nsu.lib.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author ChenGang
 * @Title: StudentLoginController
 * @Package cn.edu.nsu.lib.bean.student
 * @Description:学生端的页面跳转控制器
 * @date 2017/10/10 0010 下午 5:07
 **/
@Controller
@RequestMapping("/student")
public class StudentLoginController extends BaseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;
    @Autowired
    StuBaseService stuBaseService;
    //登陆界面修改为统一登陆
//    //进入登录界面
//    @RequestMapping("/login")
//    public String studentLogin() {
//
//        log.info("进入学生登录界面");
//        return "student/studentLogin";
//    }
    //进入学生系统

    @RequestMapping("/index")
    @Authority(role = {Authority.Role.STUDENT})
    public String studentIndex() {
        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        //是否取得登录信息
//        if (loginMap != null) {
        log.info("loginMap不为空");

        if (loginMap.containsKey("name")) {
            request.setAttribute("username", loginMap.get("name"));
            log.info("设置名字成功" + loginMap.get(("name")));
            return "common/student_index";
        }
        //第一次登录没有usernamm没有填写，设置为未完善
        else if (loginMap.containsKey("id")) {
            request.setAttribute("username", "未完善");
            log.info("登陆成功");
            return "common/student_index";
        }
//        }
//        //拿不到登录信息自动跳转到登录失效页面
        return "common/500";

    }

    //退出登录
    @RequestMapping(value = "/exitLogin", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public String exitLogin() {
        RequestUtil.remove(request.getSession());
        session.invalidate();
        log.info("退出成功");
        return "common/login";
    }

    //进入完善信息页面
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    @RequestMapping(value = "fillInformation", method = RequestMethod.GET)
    public String fillInformation(Model model) {
        Map<String, Object> student= RequestUtil.getLogin(request);
           try {
               Map<String,Object> studentMap=stuBaseService.getStudentInfo((long)student.get("id"));
//                studentMap.put("gender",()studentMap.get("gender"));
               List<SLabBean> sLabBeans = stuBaseService.getLabInfoList();
            List<SMajorBean> sMajorBeans = stuBaseService.getMajorList();
            model.addAttribute("sLabBeans", sLabBeans);
            model.addAttribute("sMajorBeans", sMajorBeans);
            if(studentMap!=null) {
                model.addAttribute("studentMap", studentMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "student/student_baseInformation";
    }

    //进入查看信息页面
    @RequestMapping(value = "stuInformation", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public String stuInformation() {
        return "student/studentInformation";
    }

    //进入添加获奖信息
    @RequestMapping(value = "fillAwardInformation", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public String fillAwardInformation() {
        return "student/student_prize";
    }

    @RequestMapping(value = "stuDocument", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    //进入查看公告页面
    public String stuDocument() {
        return "student/student_document";
    }

}
