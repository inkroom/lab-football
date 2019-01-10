package com.nsu.controller.student.exam;

import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.service.student.exam.IStudentExamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 学生考试控制器
 *
 * @author XueLong
 * @version V1.0
 * @ClassName: StudentExamController
 * @Package com.nsu.controller.student.exam
 * @Description: 学生考试选题、保存作答
 * @date 2017/7/19 8:51
 */
@Controller
@RequestMapping("/student/exam")
public class StudentExamController extends BaseController implements Anonymous {

    //学生考试服务
    @Resource
    IStudentExamService studentExamService;

    /**
     * 考试选题(对应前端页面上一题、下一题、选题的按钮)
     * 记录当前作答答案
     *
     * @param nextIndex 所选题目的下标
     * @param nowIndex  当前作答答案的下标,-1为当前未作答
     * @param nowAnswer 当前作答答案
     * @return object 问题信息的json数据
     * @Title: choiceQuestion
     * @Description: 获取题目信息 、包括题干、选项、类型等
     * @author XueLong
     * @date 2017 -07-19 09:00:16
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("choice_question")
    @ResponseBody
    public Object choiceQuestion(int nextIndex, int nowIndex, String nowAnswer){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "第" + (nextIndex+1) + "题");
        try{
            //提交考试问题答案
            studentExamService.submitAnswer(nowIndex,nowAnswer);
            //获取考试问题信息
            ajaxBean.put("question",studentExamService.getQuestion(nextIndex));
            ajaxBean.setStatus("200");
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
     * 获取考试问题
     *
     * @param nextIndex 考试问题的下标
     * @return the 问题信息的json数据
     * @Title: loadAnswer
     * @Description: 获取题目信息 、包括题干、选项、类型等
     * @author XueLong
     * @date 2017 -08-08 00:17:14
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("get_question")
    @ResponseBody
    public Object getQuestion(int nextIndex){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "第" + (nextIndex+1) + "题");
        try{
            //获取考试问题信息
            ajaxBean.put("question",studentExamService.getQuestion(nextIndex));
            ajaxBean.setStatus("200");
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
     * 记录当前作答答案
     *
     * @param nowIndex  当前作答答案的下标
     * @param nowAnswer 当前作答答案
     * @return the 提交情况
     * @Title: submitAnswer
     * @Description: 记录当前作答答案
     * @author XueLong
     * @date 2017 -08-08 00:14:11
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("submit_answer")
    @ResponseBody
    public Object submitAnswer(int nowIndex, String nowAnswer){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "提交答案");
        try{
            //提交考试问题答案
            studentExamService.submitAnswer(nowIndex,nowAnswer);
            ajaxBean.setStatus("200");
        }catch (IOException e){
            e.printStackTrace();
            //IO异常，一般出现在json的格式转换
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
     * 提交考试(完成考试)
     * 修改本次考试状态为已考试
     *
     * @return 提交情况
     * @Title: submitExam
     * @Description: 提交考试(完成考试)
     * @author XueLong
     * @date 2017 -08-08 00:21:01
     */
    @InterceptorAnno(isAjax = true)
    @RequestMapping("submit_exam")
    @ResponseBody
    public Object submitExam(){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.put("name", "提交考试");
        try{
            //提交考试
            studentExamService.submitExam();
            ajaxBean.setStatus("200");
        }catch (Exception e){
            e.printStackTrace();
            ajaxBean.setStatus("500");
            ajaxBean.setMsg(e.getLocalizedMessage());
        }
        return ajaxBean;
    }
}
