package cn.edu.nsu.lib.filters;

import cn.edu.nsu.lib.handlers.SaveWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Save filter.
 *
 * @author 墨盒
 * @version 1.0
 * @Date 2017 /9/17
 * @Time 21 :55
 * @Descorption xss和sql防范 ，来自<a href="http://blog.csdn.net/hithedy/article/details/50630109">网上</a>
 */
public class SaveFilter implements Filter {
    private Log log = LogFactory.getLog(getClass());


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SaveWrapper xssRequest = new SaveWrapper((HttpServletRequest) request);
        log.debug(((HttpServletRequest) request).getRequestURL());
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
