package cn.nsu.edu.web.four.filters;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XssFilter extends OncePerRequestFilter {

    @Override
    protected void initFilterBean() throws ServletException {
        XSSRequestWrapper.compile();
    }

    private final Log log = LogFactory.getLog(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        request.getSession().setAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE,"16161202825");
//        Map<String, Object> account = new HashMap<>();
//        account.put(BaseStatic.KEY_ORGANIZATION_ID, "10000");
//        RequestUtil.login(request, account, Role.ORGANIZATION);

        if (ServletFileUpload.isMultipartContent(request)) {
            XSSFormRequest xssRequestWrapper = new XSSFormRequest(request);
            filterChain.doFilter(xssRequestWrapper, response);
        } else {
            XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper(request);
            filterChain.doFilter(xssRequestWrapper, response);
        }

//        request.getSession().setAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE,"18161202824");

    }


}
