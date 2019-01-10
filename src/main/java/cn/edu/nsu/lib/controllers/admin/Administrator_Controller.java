package cn.edu.nsu.lib.controllers.admin;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.Constants.AdminConstants;
import cn.edu.nsu.lib.bean.admin.Student_check;
import cn.edu.nsu.lib.bean.admin.form.Notice_form;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 王振科 on 2017/9/24.
 */


/**
 * 该类实现管理员主页
 * 1.显示学生（姓名，学号，考勤）等信息显示的功能
 * 2.根据前端传的日期查询当天的考勤信息
 */
@Controller
@RequestMapping("/Administrator")
public class Administrator_Controller extends BaseController {
    //    我把管理员视图藏在该路径下了  /administrator/
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;
    private Log log = LogFactory.getLog(Administrator_Controller.class);

    /**
     * mainpage() 这个方法的描述
     *
     * @param
     * @ClassName: mainpage
     * @Description: 跳转到管理员主页面，显示考勤信息
     * @Author: 王振科
     * @Date: 21:10
     * @URL: localhost/Administrator/mainpage
     */
    @RequestMapping("/mainpage")
    @Authority(role = Authority.Role.MANAGER)
    public String mainpage(HttpServletResponse response) throws Exception {
//        获得主页面的日期
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //获得学生考勤的array
        ArrayList<Student_check> student_checks = service.getAdmini_service().
                mainpage_Service(lab_id);
        request.setAttribute("student_checks", student_checks);
        String lab_name = service.getAdmini_service().
                getLabname_service(lab_id);
        //request存入实验室对象给前端
        request.setAttribute("lab_name", lab_name);

        //页面打印测试***
        for (Student_check check :
                student_checks) {
            log.info("mainpage");
            log.info("id:" + check.getStudent_id());
            log.info("Name:" + check.getName());
            log.info("Date:" + check.getDate());
            log.info("Register:" + check.isRegister());
        }
        //学生考勤key是student_checks
        return "/administrator/mainpage";
    }

    /**
     * timechangedata() 这个方法的描述
     *
     * @param time
     * @ClassName: timechangedata
     * @Description: 根据前端传的日期查询刷新当天的考勤信息
     * @Author: 王振科
     * @Date: 20:50
     * @URL:
     */
    @RequestMapping("/timechangedata")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean timechangedata(@RequestParam(AdminConstants.RQUESTP_C_TCD) String time) throws Exception {
        //        获得传来主页面的日期
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        log.info("方法timechangedata获得的参数是：" + time);
        ArrayList<Student_check> student_checks = service.getAdmini_service().mainpage_Service( lab_id);
        request.setAttribute("student_checks", student_checks);
        //学生考勤key是student_checks
        //页面打印测试***
        for (Student_check check :
                student_checks) {
            log.info("***************************");
            log.info("id:" + check.getStudent_id());
            log.info("Name:" + check.getName());
            log.info("Date:" + check.getDate());
            log.info("Register:" + check.isRegister());
        }
        AjaxBean ajaxBean;
        if (student_checks.isEmpty()) {
            log.info("ajaxbean执行失败");
            ajaxBean = new AjaxBean(Result.FAIL);
        } else {
            log.info("ajaxbean执行成功");
            ajaxBean = new AjaxBean(Result.SUCCESS);
            ajaxBean.setMessage("该ajax不为空");
        }
        ajaxBean.put("student_checks", student_checks);
        //ajax学生考勤key是student_checks
        return ajaxBean;
    }


    /*
    测试
     */
    @RequestMapping("/Test")
    @Authority(role = Authority.Role.MANAGER)
    public void Test(HttpServletResponse response,
                     @RequestParam Notice_form notice_form) {
        request.setAttribute("rpublisher", notice_form.getPublisher());
        request.setAttribute("rtime", notice_form.getTime());
        log.info("rpublisher属性：" + notice_form.getPublisher());
        response.setContentType("utf-8");
        try {
            request.getRequestDispatcher("/Administrator/mainpage").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
