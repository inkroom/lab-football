package com.nsu.controller.teacher.exam;

/**
 * 教師考試管理控制器
 * @author ChenGang
 * @Title: ExamManagerController
 * @Package com.nsu.controller.teacher.exam
 * @Description:
 * @date 2017/7/17 0017 上午 8:49
 **/
import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.teacher.TExamInformationBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.entity.Account;
import com.nsu.service.teacher.exam.TExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/teacher")
public class TExamManagController extends BaseController implements Anonymous {
    @Autowired
    private HttpSession httpSession;
    @Autowired
private TExamManageService tExamManageService;



    /**
     * @author ChenGang
     * @Title: ExamManagerController
     * @Package com.nsu.controller.teacher.exam
     * @Description:教师创建考试
     * @date 2017/7/17 0017 上午 10:20
     **/
    @RequestMapping("/creatExam")
    @InterceptorAnno(isAjax = true)
    @ResponseBody
    public AjaxBean creatExam(@RequestBody TExamInformationBean tExamInformationBean) {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","创建考试");
        try {
            //创建考试
            ajaxBean.put("exam_creat",tExamManageService.creatExamInformation(tExamInformationBean));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //创建失败
            ajaxBean.put("exam_creat",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据创建异常!");
        }
        return ajaxBean;
    }
    /**
     * @author ChenGang
     * @Title: ExamManagerController
     * @Package com.nsu.controller.teacher.exam
     * @Description:根据教师id查询考试
     * @date 2017/7/17 0017 下午 15:20
     **/
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/findExamBytId")
    @ResponseBody
    public AjaxBean findExaminformationbyId() {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","根据教师id查询考试");
        //从session中获取登陆信息
        Account account=(Account) httpSession.getAttribute("Account");
        //根据老师id查找考试信息
       long aid=account.getaId();
        try {
            //根据老师id查找考试
            ajaxBean.put("exam_findByTid",tExamManageService.findExamInforByTid(aid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //查找失败
            ajaxBean.put("exam_findByTid",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据查询失败!");
        }
        return ajaxBean;
    }
    /**
     * @author ChenGang
     * @Title: ExamManagerController
     * @Package com.nsu.controller.teacher.exam
     * @Description:根据班级id查询考试
     * @date 2017/7/17 0017 下午 15:20
     **/
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/findExamByCid")
    @ResponseBody
    public AjaxBean findExaminformationbyClass() {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","根据班级id查询考试");
        //从session中获取登陆信息
        Account account=(Account) httpSession.getAttribute("Account");
        //根据班级id查找考试信息
        long aid=account.getaId();
        try {
            //根据班级id查找考试
            ajaxBean.put("exam_findBycid",tExamManageService.findExamByCid(aid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //查找失败
            ajaxBean.put("exam_findBycid",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据查找失败!");
        }
        return ajaxBean;
    }
    /**
     * @author ChenGang
     * @Title: ExamManagerController
     * @Package com.nsu.controller.teacher.exam
     * @Description:教师删除考试
     * @date 2017/7/17 0017 上午 10:20
     **/
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/deleteExamId")
    @ResponseBody
    public AjaxBean deleteExam(Long id) {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","删除考试");
        //从session中获取登陆信息
        try {
            //删除考试，将考试状态设为删除
            tExamManageService.deleteExamInformation(id);
            ajaxBean.put("exam_delete","删除成功");
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //删除失败
            ajaxBean.put("exam_delete",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据删除失败!");
        }
        return ajaxBean;
    }
}
