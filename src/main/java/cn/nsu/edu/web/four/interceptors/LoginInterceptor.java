package cn.nsu.edu.web.four.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        log.info(" 登录拦截 ");
//        log.info(handler.getClass().toString());
        if ((handler instanceof ResourceHttpRequestHandler)){
            ResourceHttpRequestHandler resourceHandler = (ResourceHttpRequestHandler) handler;

            log.info(resourceHandler.getLocations().toString());
            log.info(resourceHandler.getResourceResolvers().toString());
        }
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
