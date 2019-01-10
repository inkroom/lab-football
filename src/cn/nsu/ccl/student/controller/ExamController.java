package cn.nsu.ccl.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

import cn.nsu.ccl.student.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nsu.ccl.student.entity.*;
import cn.nsu.ccl.student.service.StudentServiceManager;
import net.sf.json.JSONObject;


/**
 * <p>考试方面的controller</p>
 * <p>该类下的action需要加上前缀 student </p>
 *
 * @author 欧磊
 * @ClassName: ExamController
 * @Description: TODO(管理考试界面的action，与考试前段界面进行会话, 分配管理处理逻辑)
 * @date 2016年11月27日 下午8:23:41
 */
@Controller
@RequestMapping("/student")
public class ExamController {

    // 获取request、session和response
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    // @Autowired
    // private HttpServletResponse response;
    @Autowired
    private StudentServiceManager examServiceManager; // service管理


    /**
     * <p>开始考试action，url是student/exam</p>
     * <p>request域里放key为result的boolean数据，代表能否惨叫考试</p>
     * <p>如果可以考试，session域里放key为endTime的long型数据，代表考试结束时间</p>
     *
     * @return 视图层页面或者其它action
     * @Title: ExamController的startExam方法
     * @Description: TODO(开始考试的action)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:24:28
     */
    @RequestMapping(value = "exam", method = RequestMethod.GET)
    public String startExam() {
        //测试使用
//        session.setAttribute("studentId", "15310320108");
//        session.setAttribute("examId", 939);

        // 获取信息
        String studentId = (String) session.getAttribute("studentId"); // 获取考生学号
        Integer examId = (Integer) session.getAttribute("examId"); // 获取考场ID号

        // 启动服务
        ExaminationInfo examinationInfo = examServiceManager.getExamService().getExaminationInfo(studentId, examId);
        // 返回视图层 提交数据
        if (examinationInfo == null) {
            System.out.println("获取数据失败");
            return "student/login";
        }
        // 返回数据到视图层
//        request.setAttribute("examinationInfo", examinationInfo);
        // session.setAttribute("examinationInfo",
        // examinationInfo);//无需放到session
        // 判断是否还能考试
        if (examinationInfo.getExamineeInfo().getExamStatus() == 1
                && examinationInfo.getExamInfo().getEndTime().after(new Date())
                && examinationInfo.getExamInfo().getStartTime().before(new Date())) {
            request.setAttribute("result", true);
            session.setAttribute("endTime", examinationInfo.getExamInfo().getEndTime().getTime());
            return "student/index";
        }
        System.out.println("不能考试");
        request.setAttribute("result", false);
        return "student/index";
    }


    /**
     * <p>获取考题，并且提交答案的action，url是student/switchQue</p>
     *
     * @param nowQueId 当前题目的id
     * @param swiQueId 下一道题目的id
     * @param answer   当前题目考生的答案
     * @return
     * @Title: ExamController的switchQue方法
     * @Description: TODO(获取题目信息的map集合)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:26:12
     */
    @RequestMapping(value = "/switchQue", method = RequestMethod.POST)
    @ResponseBody
    public String switchQue(@RequestParam(defaultValue = "-1") String nowQueId, @RequestParam(defaultValue = "-1") String swiQueId, @RequestParam String answer) {
        // public String switchQue(int nowQueNum, String nowQueType, String
        // answer, int swiQueNum) {
        Integer i_nowQueId = StringUtil.parseInt(nowQueId);
        Integer i_swiQueId = StringUtil.parseInt(swiQueId);
        System.out.println("nowQueId=" + nowQueId + "   swiQueId=" + swiQueId
                + "    answer=" + answer);

        if (StringUtil.isNull(new Object[]{i_nowQueId, i_swiQueId, answer})) {
            return "{\"status\":false}";
        }

        // 获取信息
        String studentId = (String) session.getAttribute("studentId"); // 获取考生学号
        Integer examId = (Integer) session.getAttribute("examId"); // 获取考场ID号

        // 启动服务
        JSONObject queInfo = examServiceManager.getExamService().swichQue(studentId, examId, i_nowQueId, i_swiQueId,
                answer);
        if (queInfo == null) {
            System.out.println("后台获取题目失败");
            return "{\"status\":false}";
        }
        System.out.println("获取的题目信息：" + queInfo.toString());
        return queInfo.toString();
    }


    /**
     * <p>结束考试的action，url 是 student/endExam</p>
     *
     * @return 视图层页面或者其它action
     * @Title: ExamController的endExam方法
     * @Description: TODO(考试结束的action)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:28:37
     */
    @RequestMapping(value = "/endExam", method = RequestMethod.POST)
    @ResponseBody
    public String endExam() {
        // 获取信息
        String studentId = (String) session.getAttribute("studentId"); // 获取考生学号
        Integer examId = (Integer) session.getAttribute("examId"); // 获取考场ID号
        Long endTime = (Long) session.getAttribute("endTime");
        // 启动服务
        boolean endState = false;
        if (endTime >= new Date().getTime()) {
            endState = examServiceManager.getExamService().endExam(studentId, examId);
            // 返回视图层 提交数据
//            if (endState) {
//                // return "redirect:/exam/StartExam.do";
//            } else {
//                // return "redirect:/exam/PageError.do";
//            }
        } else {
            return "{\"status\":" + endState + ",\"message\":\"考试已结束，不能交卷\"}";
        }
        System.out.println("考试结束否：" + endState);
        return "{\"status\":" + endState + "}";
    }

    /**
     * <p>初始化考试的action，url是student/initExam</p>
     *
     * @return 初始化的json字符串
     */
    @RequestMapping(value = "initExam", method = RequestMethod.GET)
    @ResponseBody
    public String initExam() {
        // 获取信息
        String studentId = (String) session.getAttribute("studentId"); // 获取考生学号
        Integer examId = (Integer) session.getAttribute("examId"); // 获取考场ID号

        System.out.println("student=" + studentId + "  examId=" + examId);

        JSONObject json = examServiceManager.getExamService().initExam(studentId, examId);

        if (json != null) {
            json.put("status", true);
        } else {
            json = new JSONObject();
            json.put("status", false);
        }
        System.out.println("初始化的json数据：" + json.toString());
        return json.toString();
    }

}
