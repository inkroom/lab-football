package com.nsu.controller.student.classmanage;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.classmanager.JoinClassBean;
import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.exception.upload.SizeException;
import com.nsu.exception.validate.AnalysisException;
import com.nsu.exception.validate.CustomValidateException;
import com.nsu.exception.validate.DataException;
import com.nsu.exception.validate.IllegalFormatException;
import com.nsu.service.student.classmanager.ClassManagerService;
import com.nsu.utils.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/13
 * @Time 17:17
 * @Descorption 班级管理
 */
@Controller
@RequestMapping("/student/class_manager/")
public class ClassManagerController extends BaseController implements Anonymous {
    @Autowired
    private ClassManagerService managerService;
    @Autowired
    private HttpServletRequest request;

    public static final String KEY = "user";

    @RequestMapping("index")
    @InterceptorAnno(createToken = true)
    public String toIndex() {
        try {
            request.setAttribute("classes", managerService.getAllClass(((Long) ((Map) request.getSession().getAttribute(KEY)).get(KEY))));
            log.info("***  " + request.getAttribute("classes").toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "redirect:/1/error.html";
        }
        log.info(" 跳转到test.jsp页面");
        return "student/classManage";
    }

    @RequestMapping("init")
    @ResponseBody
    @InterceptorAnno(checkToken = true, isAjax = true)
    public AjaxBean init() {
        AjaxBean ajax = new AjaxBean("200");
        try {
            ajax.put("classes",
                    managerService.getAllClass(((Long) ((Map) request.getSession().getAttribute(KEY)).get(KEY))));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new AjaxBean("500", "服务器异常，请联系管理人员！");
        }
        return ajax;
    }


    @RequestMapping(value = "join_class")
    @InterceptorAnno(checkToken = true, isAjax = true)
    @ResponseBody
    public AjaxBean joinClass(JoinClassBean joinClass) {
        //验证表单，表单验证通过返回null，不通过返回500
        AjaxBean ajax = checkForm(joinClass);
        if (ajax != null)//表单验证不通过
            return ajax;
        long id = ((Long) ((Map) request.getSession().getAttribute(KEY)).get(KEY));
        try {
//            if (managerService.isStayClass(id)) {
//                return new AjaxBean("500", "已有所在班级，不能再重复申请！");
//            } else {//学生没有所在班级
            Boolean result = managerService.joinClass(id, joinClass.getClassId(), joinClass.getKey());
            if (result == null) {
                return new AjaxBean("500", "班级号或口令错误！");
            } else if (result == Boolean.TRUE) {
                return new AjaxBean("200", "申请加入班级成功！等待教师审核！");
            } else {
                return new AjaxBean("500", "加入班级失败！原因未知！");
            }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new AjaxBean("500", "服务器异常，请联系管理人员！");
        }
    }

}
