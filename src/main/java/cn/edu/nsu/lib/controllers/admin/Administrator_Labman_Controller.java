package cn.edu.nsu.lib.controllers.admin;

/**
 * Created by 王振科 on 2017/9/26.
 */

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.db.DbMajor;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 该类实现管理员查看 实验室成员和指导老师 信息表页面
 * 1.查看学生的所有信息，指导老师的信息
 * 2.跳转到添加实验室成员页面
 * 3.跳转到修改实验室成员页面
 * 4.根据id删除该实验室成员
 */
@Controller
@RequestMapping("/LabmanAdministrator")
public class Administrator_Labman_Controller extends BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;

    /**
     * toLabmaninfo() 这个方法的描述
     *
     * @ClassName: toLabmaninfo
     * @Description: 跳转到成员信息页面，显示信息
     * @Author: 王振科
     * @Date: 11:19
     * @URL: /LabmanAdministrator/toLabmaninfo
     */
    @RequestMapping("/toLabmaninfo")
    @Authority(role = Authority.Role.MANAGER)
    public String toLabmaninfo(HttpServletResponse response) throws Exception {

        //获得实验室的id
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        ArrayList<DbStudent> students = service.getAdmini_labman_service().
                toLabmaninfo_Service(lab_id);
        ArrayList<DbTeacher> teachers = service.getAdmini_labman_service().
                toLabman_Teacherinfo_Service(lab_id);
        request.setAttribute("id",getLogin(request).get(Constants.KEY_MAP_ID));
        request.setAttribute("students", students);
        request.setAttribute("teachers", teachers);

        String lab_name = service.getAdmini_service().
                getLabname_service(lab_id);
        //request存入实验室对象给前端
        request.setAttribute("lab_name", lab_name);

        //学生key是students
        return "/administrator/toLabmaninfo";
    }

    /**
     * <p>显示页面显示的是该实验室的学生，</p>
     * <p>但是并未判断删除的该学生的实验室id是否等于管理员的实验室id</p>
     *
     * @param student_id
     * @ClassName: deleteLabman
     * @Description: 根据id，删除该成员
     * @Author: 王振科
     * @Date: 11:20
     * @URL: /LabmanAdministrator/deleteLabman
     */
    @RequestMapping("/deleteLabman")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean deleteLabman(BigInteger student_id) throws Exception {


        Result status = service.getAdmini_labman_service()
                .deleteLabman_Service(student_id);
        if (status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        } else {
            return new AjaxBean(Result.SUCCESS);
        }
    }

    /**
     * tochangeLabman() 这个方法的描述
     *
     * @param id
     * @ClassName: tochangeLabman
     * @Description: 跳转到更改成员信息页面
     * @Author: 王振科
     * @Date: 11:20
     * @URL: /LabmanAdministrator/tochangeLabman
     */
    @RequestMapping("/tochangeLabman")
    @Authority(role = Authority.Role.MANAGER)
    public String tochangeLabman(@RequestParam("student_id") BigInteger student_id) throws Exception {

        //根据id返回该学生对象
        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        log.info("跳转到修改页面");
        log.info("要修改的学生学号：" + student_id + "实验室id" + lab_id);
        log.info("跳转到修改页面");
        Student_form student = service.getAdmini_labman_service()
                .tochangeLabman_Service(student_id, lab_id);
        request.setAttribute("student", student);

        ArrayList<DbMajor> db_majors = service.getAdmini_labman_service()
                .toaddLabman_Service();
        request.setAttribute("majors", db_majors);

        return "/administrator/tochangeLabman";
    }

    /**
     * toaddLabman() 这个方法的描述
     *
     * @ClassName: toaddLabman
     * @Description: 跳转到添加成员信息页面
     * @Author: 王振科
     * @Date: 11:20
     * @URL: /LabmanAdministrator/toaddLabman
     */
    @RequestMapping("/toaddLabman")
    @Authority(role = Authority.Role.MANAGER)
    public String toaddLabman() {

        ArrayList<DbMajor> db_majors = service.getAdmini_labman_service()
                .toaddLabman_Service();
        request.setAttribute("majors", db_majors);
        return "/administrator/toaddLabman";
    }
}
