package cn.nsu.ccl.student.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nsu.ccl.student.service.StudentServiceManager;


/**
 * <p>学生登录的controller，action前缀是student</p>
 *
 * @author 欧磊
 * @ClassName: SubjectController
 * @Description: TODO(学生端的跳转)
 * @date 2016年11月27日 下午8:30:11
 */
@Controller
@RequestMapping("/student")
public class StuLoginController {

    @Autowired
    private HttpSession session;
    //	@Autowired
//	private HttpServletRequest request;
    @Autowired
    private StudentServiceManager serviceManager;


    /**
     * <p>登录action，url是student/login，method是post，负责登录判断</p>
     *
     * @param studentId   学生编号
     * @param studentName 学生姓名
     * @param keyWord     考试口令
     * @return 返回登录结果，如{"status":false,"code":2}
     * @author qingyi xuelongqy@foxmail.com
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String studentId, String studentName, String keyWord) {
        if (studentId == null || studentName == null || keyWord == null) {
            return "{\"status\":false,\"code\":2}";
        } else {
            int examId = serviceManager.getLoginService().login(studentId, studentName, keyWord);
            if (examId != -1) {
                if (serviceManager.getLoginService().updateStatus(studentId, examId, 1)) {
//					session.removeAttribute("examId");
                    session.setAttribute("examId", examId);
                    session.setAttribute("studentId", studentId);
                    return "{\"status\":true}";
                } else {// 更新考试状态失败，已经参加过考试
                    return "{\"status\":false,\"code\":1}";
                }
            }
            return "{\"status\":false,\"code\":0}";
        }
    }


    /**
     * <p>登录action，url是student/login，method是get，负责跳往登录jsp</p>
     *
     * @return
     * @Title: SubjectController的login方法
     * @Description: TODO(跳转到登录页面)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:32:53
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "student/login";
    }
}
