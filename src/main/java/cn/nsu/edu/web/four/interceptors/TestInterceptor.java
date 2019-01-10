package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.config.RegexStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试formDate拦截器
 */
public class TestInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //记录url，用于导航栏
        log.info("开始");
        log.info(request.getParameter("name"));
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

    private void check(HttpServletRequest request){

    }
}
