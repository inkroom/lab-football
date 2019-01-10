package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.utils.code.CodeUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((handler instanceof HandlerMethod)) {
            HandlerMethod method = (HandlerMethod) handler;
            Security a = method.getMethodAnnotation(Security.class);
            if (a != null) {

//                if (RequestUtil.getLogin(request) == null) {
//                    log.info("未登录");
//                    if (request.getRequestURI().contains("referee")) {
//                        log.info("裁判员");
//                        request.getRequestDispatcher("/referee/login").forward(request, response);
//                        return false;
//                    }
//                }
                log.info("已登录= " + RequestUtil.getLogin(request));

//                if (a.createToken()&&a.checkToken()){
//                    throw new RuntimeException("Security 属性 createToken 和 checkToken 不可同时为true");
//                }

                //验证操作权限
                Role[] roles;
                if ((roles = a.roles()).length > 0) {//请求要求有身份验证
                    Role role = RequestUtil.getRole(request);
                    if (role == null) {//未登录，直接401
                        forward401(request, response);
                        return false;
                    }
                    for (Role role1 : roles) {
                        if (role == role1) {
                            return true;
                        }
                    }
                    forward401(request, response);
                    return false;
                }
            }
        } else {
            log.info(request.getRequestURI());
        }
        return true;
    }

    private void forward401(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (RequestUtil.isAjax(request)) {
            ResponseUtil.outJson(response, new MessageDto(Result.NO_AUTHORITY).toString());
        } else {
            if (request.getRequestURI().contains("referee")) {
                request.getRequestDispatcher("/referee/login").forward(request, response);
            } else if (request.getRequestURI().contains("player")) {
                request.getRequestDispatcher("/player/").forward(request, response);
            } else {
                request.getRequestDispatcher("/organization/").forward(request, response);
            }
        }
    }
}
