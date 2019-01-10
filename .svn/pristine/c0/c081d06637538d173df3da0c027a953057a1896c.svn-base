package cn.nsu.edu.web.four.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class RedisSessionListener implements HttpSessionListener {
    // TODO: 18-4-23 人数统计有问题
    public static int count = 0;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        count++;
        log.info("session 创建=" + httpSessionEvent.getSession().getId() + "   " + httpSessionEvent.getSession().getServletContext().getContextPath());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("session 销毁=" + httpSessionEvent.getSession().getId() + "   " + httpSessionEvent.getSession().getServletContext().getContextPath());
        count--;
    }
}
