package cn.edu.nsu.lib.listeners;

import cn.edu.nsu.lib.config.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/12
 * @Time 21:44
 * @Descorption
 */
public class ContextListener implements ServletContextListener {
    private Log logger = LogFactory.getLog(getClass());

    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(Constants.KEY_CONTEXT_PATH, event.getServletContext().getContextPath());
        //key 是账号username，value是session Id
        HashMap<String, Object> sessions = new HashMap<String, Object>();
        event.getServletContext().setAttribute(Constants.KEY_CONTEXT_SESSION, sessions);

        //存放二维码
        HashMap<String, String> codes = new HashMap<>();
        event.getServletContext().setAttribute(Constants.KEY_CONTEXT_CODE, codes);

        HashMap<String, Long> time = new HashMap<>();
        event.getServletContext().setAttribute(Constants.KEY_CONTEXT_TIMES, time);
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}
