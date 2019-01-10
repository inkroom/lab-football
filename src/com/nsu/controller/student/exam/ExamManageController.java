package com.nsu.controller.student.exam;


import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.entity.ExamInformation;
import com.nsu.entity.Student;
import com.nsu.service.student.exam.IExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 学生考试管理控制器
 *
 * @author qingyi xuelongqy@foxmail.com
 * @Title: ExamManageController
 * @Description: 学生查看最近考试并参加考试
 * @version: V1.0
 * @date 2017 -07-14 14:35:30
 */
@Controller
@RequestMapping("/student/exam")
public class ExamManageController extends BaseController implements Anonymous {

    //获取session
    @Resource
    private HttpSession session;

    //考试管理服务
    @Resource
    IExamManageService examManageService;

    /**
     * 获取学生最近考试列表信息
     *
     * @return object 异步请求的数据(学生最近考试的信息)
     * @Title: examList
     * @Description: 获取学生最近考试信息
     * @author XueLong
     * @date 2017 -07-14 15:03:05
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("exam_list")
    @ResponseBody
    public Object examList(){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "学生考试管理");
        try{
            //获取考试信息列表
            ajaxBean.put("exam_list",examManageService.getExamList());
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //发生异常
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("系统发生异常!");
            e.printStackTrace();
        }
        return ajaxBean;
    }


    /**
     * 学生进入考试
     *
     * @return object 异步请求的数据(学生是否成功进入考试)
     * @Title: joinExam
     * @param e_I_Id 考试Id号
     * @Description: 学生参加考试，初始化学生考试记录（生成考卷，初始化分数）
     * @author XueLong
     * @date 2017 -07-18 10:04:59
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("join_exam")
    @ResponseBody
    public Object joinExam(long e_I_Id){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "学生进入考试");
        try{
            //学生参加考试
            if (examManageService.joinExam(e_I_Id)){
                ajaxBean.setStatus("200");
                ajaxBean.setMsg("进入考试!");
            }else{
                ajaxBean.setStatus("500");
                ajaxBean.setMsg("系统异常.请稍后再试!");
            }
        }catch (IOException e){
            //IO异常，一般出现在json的格式转换
            e.printStackTrace();
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("系统异常,未能成功获取数据!");
        }catch (Exception e){
            e.printStackTrace();
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getLocalizedMessage());
        }
        return ajaxBean;
    }

    /**
     * 验证考试前身份信息
     *
     * @param IDCard 身份证号码(目前是这个)
     * @return 验证结果的Json数据
     * @Title: checkExamInfo
     * @Description: 验证考试前身份信息
     * @author XueLong
     * @date 2017 -08-10 16:19:34
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("check_IDCard")
    @ResponseBody
    public Object checkExamInfo(String IDCard){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "验证身份信息");
        if (examManageService.checkExamInfo(IDCard)){
            ajaxBean.setStatus("200");
            ajaxBean.setMsg("信息正确!");
        }else{
            ajaxBean.setStatus("200");
            ajaxBean.setMsg("信息错误!");
        }
        return ajaxBean;
    }
}
