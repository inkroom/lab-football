package com.nsu.filter;

import com.nsu.common.Constants;
import com.nsu.controller.student.classmanage.ClassManagerController;
import com.nsu.controller.student.score.ScoreController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 15:10
 * @Descorption
 */
public class TestFilter implements Filter {
    Log log = LogFactory.getLog(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest) servletRequest).getSession().setAttribute(ClassManagerController.KEY, 1L);

        HashMap<String, Object> user = new HashMap<>();
        user.put(ClassManagerController.KEY, 9L);
        user.put(ScoreController.KEY_NUMBER, 10);
        user.put(ScoreController.KEY_SCORE, 20);
        user.put(ScoreController.KEY_SCHOOL, "第一个组织");

        ((HttpServletRequest) servletRequest).getSession().setAttribute(ClassManagerController.KEY, user);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public static String toString(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        Enumeration<String> em = request.getAttributeNames();
        while (em.hasMoreElements()) {
            String key = em.nextElement();
            builder.append("key = " + key + "---");
            builder.append("value = " + request.getAttribute(key).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
