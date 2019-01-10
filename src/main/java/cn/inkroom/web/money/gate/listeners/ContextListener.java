package cn.inkroom.web.money.gate.listeners;

import cn.inkroom.web.money.gate.config.BaseStatic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;


public class ContextListener implements ServletContextListener {
    private Log logger = LogFactory.getLog(getClass());

    public void contextInitialized(ServletContextEvent event) {
        HashMap<String, Object> sessions = new HashMap<String, Object>();
        event.getServletContext().setAttribute(BaseStatic.KEY_CONTEXT_SESSION, sessions);
    }

    public void contextDestroyed(ServletContextEvent event) {

    }
}
