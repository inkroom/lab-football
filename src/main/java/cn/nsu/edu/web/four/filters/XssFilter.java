package cn.nsu.edu.web.four.filters;

import cn.nsu.edu.web.four.config.BaseStatic;
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

        if (ServletFileUpload.isMultipartContent(request)) {
            XSSFormRequest xssRequestWrapper = new XSSFormRequest(request);
            filterChain.doFilter(xssRequestWrapper, response);
        } else {
            XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper(request);
            filterChain.doFilter(xssRequestWrapper, response);
        }


    }


}
