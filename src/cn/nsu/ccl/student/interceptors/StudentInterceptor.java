package cn.nsu.ccl.student.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/4/5.
 * 学生登录验证拦截器
 */
public class StudentInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || "Microsoft.XMLHTTP".equals(request.getHeader("X-Requested-With"))) {//ajax
            if (session.getAttribute("studentId") == null
                    || session.getAttribute("examId") == null
                    ) {// TODO: 2017/4/5 未登录
                if (url.endsWith("login"))
                    return true;
                response.getWriter().write("{\"status:false\",\"message\":\"你需要登录才能执行此操作\"}");
                response.getWriter().flush();
                return false;
            }
            return true;
        } else {
            if (session.getAttribute("studentId") == null
                    || session.getAttribute("examId") == null) {// TODO: 2017/4/5 未登录
                if (url.endsWith("login")) {
                    return true;
                }
                response.sendRedirect("login");
                return false;
            } else {// TODO: 2017/4/5 已登录
                if (url.endsWith("login")) {//不让二次登录
                    response.sendRedirect("exam");
                    return false;
                }
                return true;
            }
        }
    }
}
