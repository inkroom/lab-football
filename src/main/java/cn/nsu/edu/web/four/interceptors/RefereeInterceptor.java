package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 这是裁判拦截器，仅已完成身份验证可通过
 */
public class RefereeInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        log.info(" 登录拦截 ");
//        log.info(handler.getClass().toString());
        if ((handler instanceof HandlerMethod)) {

            Map<String, Object> login = RequestUtil.getLogin(request);
            Role role = RequestUtil.getRole(request);
            if (role == Role.REFEREE) {
                if (login.containsKey("phone"))
                    return true;
                log.debug("未完善信息");
                request.getRequestDispatcher("/referee/consummateIndex").forward(request, response);
                return false;
            }
            return true;
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
