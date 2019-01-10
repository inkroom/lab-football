package cn.edu.nsu.lib.controllers.admin;

/**
 * Created by 王振科 on 2017/9/26.
 */

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 该类实现实验室人员信息的添加和修改
 * 1.添加学生的信息
 * 2.修改学生的信息
 */
@Controller
@RequestMapping("/LabmanCAdministrator")
public class Administrator_LabmanC_Controller  extends BaseController{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;
    Log log = LogFactory.getLog(Administrator_LabmanC_Controller.class);
    /**
     checkadd() 这个方法的描述
     * @ClassName: checkadd
     * @Description: 确认添加成员信息
     * @Author:  王振科
     * @Date: 11:20
     * @URL:/LabmanCAdministrator/checkadd
     * @param student_form
     */
    @RequestMapping("/checkadd")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean checkadd(Student_form student_form) throws Exception {

        int lab_id = (int)getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //传入前端学生表单，实验室id 进行增加操作
        log.info("***************checkadd******************");
        log.info(student_form.toString());
        log.info("***************checkadd******************");
        Result status = service.getAdmini_labmanC_service()
                .checkadd_Service(student_form,lab_id);
        if(status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        }else {
            return new AjaxBean(Result.SUCCESS);
        }
    }
    /**
     checkalter() 这个方法的描述
     * @ClassName: checkaltert
     * @Description: 确认更改成员信息
     * @Author:  王振科
     * @Date: 11:22
     * @URL: /LabmanCAdministrator/checkalter
     * @param student_form
     */
    @RequestMapping("/checkalter")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean checkalter(Student_form student_form) throws Exception {

        int lab_id = (int)getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //传入学生表单和实验室id 进行修改操作
        log.info("**********student_form*************");
        log.info(student_form.toString());
        log.info("**********student_form*************");
        Result status = service.getAdmini_labmanC_service()
                .checkalter_Service(student_form,lab_id);
        AjaxBean ajaxBean;
        if(status == Result.FAIL) {
            log.info("修改信息失败");
            ajaxBean = new AjaxBean(Result.FAIL);
        }else {
            ajaxBean = new AjaxBean(Result.SUCCESS);
            log.info("修改信息成功:"+ajaxBean.getStatus());
        }
        return ajaxBean;
    }

}
