package com.nsu.SInterceptor;

import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.service.core.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/21
 * @Time 10:09
 * @Descorption
 */
public class RoleAuthorityInterceptor implements HandlerInterceptor {
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    RoleService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HandlerMethod method;
        if (o instanceof HandlerMethod) {
            method = (HandlerMethod) o;
        } else {
            return true;
        }

//        Object action = (Action) invocation.getAction();
//        String uri = invocation.getInvocationContext().getName();
//        String actionURL = ServletActionContext.getRequest().getServletPath();
        Object action = method.getBean();
        String uri = request.getRequestURI();

        log.info("-----action:" + action + "-" + uri + "-");


        // 这里要求实现了Anonymous接口的Action以及固定的login和logoutAction可以跳过登录拦截
        if (action instanceof Anonymous || uri.contains("login")) {
            log.info("-------action instanceof Anonymous-------true---");
//            log.info("--------actionUrl:" + actionURL);
            if (userService.ifRoleHasAuthority("0", uri)) {
//                return invocation.invoke();
                return true;
            } else {
//                log.info("----------error:role:uri:" + invocation.getProxy().getMethod());
                //                    return "authorityError";
                log.info("url = " + request.getRequestURL().toString());
                response.sendRedirect("/common/authority_error.action");
                return false;
            }
        } else {
            log.info("-------action instanceof Anonymous-------false---");
            log.debug("actionURL ==================" + uri);
            Map user = (Map) (request.getSession().getAttribute(Constants.LOGIN_USER));
            if (user != null) {
                log.info("-------action instanceof Anonymous-------false------------user != null------------true--");
                String role = user.get("TYPE").toString();
                log.debug("actionURL ==================" + role.toString());

                if (role != null) {
                    log.debug("role     true      ");
                }

                if (userService.ifRoleHasAuthority(role.toString(), uri)) {
                    log.debug("userService     true      ");
                }

                if (role != null && userService.ifRoleHasAuthority(role, uri)) {
                    log.info("-------action instanceof Anonymous-------false------------user != null------------true--------role != null&&userService.ifRoleHasAuthority(role.toString(), actionURL--------------true-------");
                    return true;
                } else {
                    log.info("-------action instanceof Anonymous-------false------------user != null------------true--------role != null&&userService.ifRoleHasAuthority(role.toString(), actionURL--------------false-------");
//                    log.info("----------error:role:uri:" + invocation.getProxy().getMethod());
//                    return "authorityError";
                    log.info("url = " + request.getRequestURL().toString());
                    response.sendRedirect("/common/authority_error.action");
                    return false;
                }
            } else {
                log.info("-------action instanceof Anonymous-------false------------user != null------------false------");
//                log.info("----------error:role:uri:" + invocation.getProxy().getMethod());
//                    return "authorityError";
                log.info("url = " + request.getRequestURL().toString());
                response.sendRedirect("/common/authority_error.action");
                return false;
            /*	ActionContext.getContext().getSession().put(Constants.ORIGINAL_URL,
                        QueryUtil.getRequestURL(ServletActionContext.getRequest()));
				return Action.LOGIN;*/
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
