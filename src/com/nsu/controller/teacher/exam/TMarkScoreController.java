package com.nsu.controller.teacher.exam;

import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.entity.Account;
import com.nsu.service.teacher.exam.TMarkScoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ChenGang
 * @Title: TMarkScoreController
 * @Package com.nsu.controller.teacher.exam
 * @Description:课外学习，特长专长，实践活动的打分
 * @date 2017/8/8 0009 上午 10:27
 **/
@Controller
@RequestMapping("/teacher/markScore")
public class TMarkScoreController extends BaseController implements Anonymous{
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private TMarkScoreService tMarkScoreService;
    //通过老师id查看编号
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/viewExamListByTid")
    @ResponseBody
    public AjaxBean viewExamListByTid ( )
    {
        Account account=(Account) httpSession.getAttribute("Account");
        //根据老师id查找考试信息
        long aid=account.getaId();
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","查看考试");

        try {
            //查看考试
            ajaxBean.put("exam_viewByTid",tMarkScoreService.viewExamListByTid(aid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //查看失败
            ajaxBean.put("exam_viewByTid",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("请求考试列表失败!");
        }
        return ajaxBean;
    }
    //通过考试id搜索考试
    @InterceptorAnno(isAjax = true)
    @RequestMapping("/viewExamListByEiid")
    @ResponseBody
    public AjaxBean viewExamListByEiid (@Param("eiid") long eiid )
    {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","查看考试");

        try {
            //创建考试
            ajaxBean.put("viewExamListByEiid",tMarkScoreService.viewExamListByEiid(eiid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //创建失败
            ajaxBean.put("viewExamListByEiid",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据查看失败!");
        }
        return ajaxBean;
    }
    @RequestMapping("/{type}/viewStuRecord")
    @InterceptorAnno(isAjax = true,isRestful = true)
    @ResponseBody
    //@Param:type 考试类型
    public AjaxBean viewStuRecordByEiid (@Param("type") int type,@Param("eiid") long eiid)
    {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","查看学生文档列表");
        try {
            //创建考试
            ajaxBean.put("viewStuRecord",tMarkScoreService.viewStuRecordByEiid(eiid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //创建失败
            ajaxBean.put("viewStuRecord",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("数据查找失败");
        }
        return ajaxBean;
    }
    //根据学生id考试id给学生打分
    @RequestMapping("/{type}/setStuREcord")
    @InterceptorAnno(isAjax = true,isRestful = true)
    @ResponseBody
    public AjaxBean setStuREcordBySid (@Param("type") int type,@Param("sid") long sid,@Param("eiid") long eiid,@Param("score") int score)
    {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","设置学生分数");
        try {
            //创建考试
            ajaxBean.put("setStuREcordBySid",tMarkScoreService.setStuREcordBySid(type,sid,eiid,score));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //创建失败
            ajaxBean.put("viewStuRecord",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("设置失败");
        }
        return ajaxBean;
    }
    //根据学生id考试id下载学生上传的文档
    @RequestMapping("/{type}/downStuExamRes")
    @InterceptorAnno(isAjax = true,isRestful = true)
    @ResponseBody
    public AjaxBean downStuExamRes (@Param("type") int type,@Param("sid") long sid,@Param("eiid") long eiid)
    {
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setSuccess(true);
        ajaxBean.put("name","下载学生考试资料");
        try {
            //创建考试
            ajaxBean.put("downStuExamRes",tMarkScoreService.downStuExamRes(type,sid,eiid));
            ajaxBean.setStatus("200");
        }catch (Exception e){
            //创建失败
            ajaxBean.put("downStuExamRes",null);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("下载失败");
        }
        return ajaxBean;
    }
}
