package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.config.RegexStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 后缀拦截器拦截器，禁止一切有后缀的action
 */
public class SuffixInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            if (request.getRequestURI().matches(RegexStatic.URL_SUFFIX)) {
                response.sendError(404);
                return false;
            }

        }
        //记录url，用于导航栏
        request.setAttribute("url",request.getRequestURI());

//        log.info(" 登录拦截 ");
//        log.info(handler.getClass().toString());
//        if ((handler instanceof ResourceHttpRequestHandler)){
//            ResourceHttpRequestHandler resourceHandler = (ResourceHttpRequestHandler) handler;
//
//            log.info(resourceHandler.getLocations().toString());
//            log.info(resourceHandler.getResourceResolvers().toString());
//        }
//        if (RequestUtil.getLogin(request)==null){
//            if (RequestUtil.isAjax(request)){
//                ResponseUtil.outJson(response,new MessageDto(Result.LOGIN_NOT).toString());
//                return false;
//            }else{
//                //将地址记录到session中
//                request.getSession().setAttribute(BaseStatic.KEY_SESSION_ORG_URL,request.getRequestURI());
//
//                response.sendRedirect(BaseStatic.URL_LOGIN);
//                return false;
//            }
//        }
        return true;
    }
}
