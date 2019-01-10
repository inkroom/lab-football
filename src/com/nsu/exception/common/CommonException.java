package com.nsu.exception.common;

import com.nsu.bean.common.AjaxBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.utils.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: CommonException
 * @Package com.nsu.exception
 * @Description:
 * @date 5/5/17
 */
public class CommonException extends SimpleMappingExceptionResolver {
    private static final Log log = LogFactory.getLog(CommonException.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String url = request.getServletPath();

        log.info("----------------eroor---------------------"+url);
        boolean isAjax = false;

        String viewName = determineViewName(ex,request);
        response.setCharacterEncoding("UTF-8");
        HandlerMethod handlerMethod = null;

        ex.printStackTrace();
        log.info("==============================================================================");
        log.info("===============================ERROR INFO START=====================================");
        log.info(ex.getMessage());
        log.error(ex.getMessage());
        log.info("===============================ERROR INFO END=====================================");
        log.info("==============================================================================");

        try {
            handlerMethod = (HandlerMethod) handler;
            System.out.println(((HandlerMethod) handler).getMethod());
            isAjax = handlerMethod.getMethodAnnotation(InterceptorAnno.class).isAjax();
        }catch (Exception e){
        	log.error(e.getMessage());
        }
        try {
            url = url.substring(1,url.indexOf("/",1));
        }catch (Exception e){
            url = "index";
        }
        log.info("==============================================================================");
        log.info("               ajax         "+isAjax);
        log.info("==============================================================================");
        if (((request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").contains("XMLHttpRequest") ))) || isAjax) {
            log.info("==============================================================================");
            log.info("                 json return         ");
            log.info("==============================================================================");
            AjaxBean ajaxBean = new AjaxBean();
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("系统错误！");
            ajaxBean.put("url",url);
            ajaxBean.put("msg",ex.getMessage());
            try {
                ResponseUtil.write(response, JsonMapper.toJsonString(ajaxBean));
            } catch (Exception e) {
            	log.error(e.getMessage());
            }
            return null;
        } else {
            // 如果不是异步请求
            // Apply HTTP status code for error views, if specified.
            // Only apply it if we're processing a top-level request.
            log.info("==============================================================================");
            log.info("                 view return         ");
            log.info("==============================================================================");
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }
            url =  url + "/error.html";
            ModelAndView modelAndView = new ModelAndView("redirect:/"+url);
            return modelAndView;
        }

    }
}
