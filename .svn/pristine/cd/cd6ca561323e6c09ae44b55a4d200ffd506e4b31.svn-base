package com.nsu.controller.teacher.exam;


import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.service.teacher.exam.TStudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*/**
 * <p>教师查看学生成绩控制器</p>
 * @ClassName:
 * @Description: 查看学生的成绩以及答题情况
 * @author ChenGang
 * @date 2017/7/18 0018 下午 5:08
 */
@Controller
@RequestMapping("/teacher")
public class ViewStuScoreController implements Anonymous {
    @Autowired
    private TStudentScoreService tStudentScoreService;
/*/**
 * <p>查看学生的成绩以及答题情况</p>
 * @Title: ViewStuScoreController的通过方法
 * @Description: 通过考试ID和班级id查看学生考试情况
 * @author ChenGang
 * @date 2017/7/18 0018 下午 5:10
 * @param
 * @return com.nsu.bean.common.AjaxBean
 */
    @ResponseBody
    @RequestMapping("/ViewStuScore")
    @InterceptorAnno(createToken = true)
    public AjaxBean findExaminformationbyId(long eiid,long classId) {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","查询考试");
        //根据老师id查找考试信息

        try {
            //根据考试id,班级查找考试
            ajaxBean.put("exam_find",tStudentScoreService.findStuScoreByCidEid(eiid,classId));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //查找失败
            ajaxBean.put("exam_find",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据查询失败!");
        }

        return ajaxBean;
    }




}
