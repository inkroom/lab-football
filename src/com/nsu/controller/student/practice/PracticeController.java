package com.nsu.controller.student.practice;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.practice.PracticeSearchBean;
import com.nsu.bean.student.practice.QuestionBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.BaseController;
import com.nsu.service.student.practice.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @date 2017/7/13
 * @time 17:12
 * @Description 练习功能controller
 */
@Controller
@RequestMapping("/student/practice/")
public class PracticeController extends BaseController implements Anonymous {
    private static final String KEY_QUESTION_INDEX = "_KEY_QUESTION_INDEX_";//存储做过的题目ID
    private static final String KEY_QUESTION = "_question_";

    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PracticeService service;

    @RequestMapping({"{type}/index", "index"})
    @InterceptorAnno(createToken = true)
    public String toIndex(@PathVariable String type) {
        request.setAttribute("type", type);
        return "student/practice";
    }

    @RequestMapping("search")
    @ResponseBody
    @InterceptorAnno(checkToken = true, isAjax = true)
    public AjaxBean search(PracticeSearchBean search) {
//        AjaxBean ajaxBean = new AjaxBean("200");
//        ajaxBean.put("question", testQuest());
//        return ajaxBean;
        AjaxBean ajax = checkForm(search);
        if (ajax != null && "500".equals(ajax.getStatus()))
            return ajax;
        try {
            ajax = new AjaxBean();
            QuestionBean question = service.getOneQuestion(search);
            if (question == null) {
                return new AjaxBean("500", "没有符合条件的题目");
            }
            if (getIdList().size() > 0) {
                Long prev = getIdList().get(getIdList().size() - 1);
                if (prev == question.getId())
                    ajax.put("other", "查到的新题目与上一题相同");
            }
            //添加题目id
            getIdList().add((question.getId()));
//            question.setAnswer(null);//去掉答案
            //存储题目
            session.setAttribute(KEY_QUESTION, question);
            ajax.put("question", question);
            ajax.setStatus("200");
            return ajax;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new AjaxBean("500", "服务器异常，请重试");
        }
    }

    @RequestMapping("commit")
    @InterceptorAnno(checkToken = true, isAjax = true)
    @ResponseBody
    public AjaxBean commit(String key) {
        AjaxBean ajax = new AjaxBean();
        ajax.setStatus("500");
        try {
            Boolean isRight = service.checkAnswer(((QuestionBean) session.getAttribute(KEY_QUESTION)).getId(), key);

            if (isRight == null) {
                ajax.setMsg("指定题目不存在！");
            } else {
                ajax.setStatus("200");
                ajax.put("result", isRight);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            ajax.setMsg("服务器异常，请重试");
            e.printStackTrace();
        }
        return ajax;
    }

    @RequestMapping("get_answer")
    @InterceptorAnno(checkToken = true, isAjax = true)
    @ResponseBody
    public AjaxBean getAnswer() {
        AjaxBean ajax = new AjaxBean("200");
        try {
            ajax.put("answer", service.getAnswer((QuestionBean) session.getAttribute(KEY_QUESTION)));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new AjaxBean("500", "服务器异常，请重试");
        }
        return ajax;
    }

    @RequestMapping("prev")
    @InterceptorAnno(checkToken = true, isAjax = true)
    @ResponseBody
    public AjaxBean prev() {
        List<Long> idList = getIdList();
        if (idList.size() == 0) {
            return new AjaxBean("500", "没有上一题");
        }
        try {
            AjaxBean ajax = new AjaxBean("200");
            QuestionBean question = service.getOneQuestion(idList.get(idList.size() - 1));
            if (getIdList().size() > 0) {
                Long prev = getIdList().get(getIdList().size() - 1);
                if (prev == question.getId())
                    ajax.put("other", "查到的新题目与上一题相同");
            }
            idList.remove(idList.size() - 1);
            if (question == null) {
                return new AjaxBean("500", "上一题已经失效");
            }
            ajax.put("question", question);
            return ajax;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new AjaxBean("500", "服务器异常");
        }

    }

    private List<Long> getIdList() {
        List<Long> idList = (List<Long>) request.getSession().getAttribute(KEY_QUESTION_INDEX);
        if (idList == null) {
            idList = new ArrayList<>();
            request.getSession().setAttribute(KEY_QUESTION_INDEX, idList);
        }
        return idList;
    }
}
