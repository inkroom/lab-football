package com.nsu.SInterceptor;

import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/21
 * @Time 10:03
 * @Descorption
 */
public class LoginInterceptor implements HandlerInterceptor {
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HandlerMethod method;

        if (o instanceof HandlerMethod) {
            method = (HandlerMethod) o;
        } else {
            return true;
        }
//        String actionName = invocation.getInvocationContext().getName();
        String actionName = request.getRequestURI();
        //这里要求实现了Anonymous接口的Action以及固定的login和logoutAction可以跳过登录拦截
        //TODO:Action名是否可改为从配置文件读取
        if (method.getBean() instanceof Anonymous || actionName.contains("login"))
            return true;
//        if (method.getBean() instanceof Anonymous || "login".equals(actionName) || "logout".equals(actionName)) {
////            return invocation.invoke();
//            return true;
//        } else {
        if (request.getSession().getAttribute(Constants.LOGIN_USER) != null) {
//                return invocation.invoke();
            return true;
        }
//        }

        request.getSession().setAttribute(Constants.ORIGINAL_URL, actionName);
//        ActionContext.getContext().getSession().put(Constants.ORIGINAL_URL,
//                QueryUtil.getRequestURL(ServletActionContext.getRequest()));
//        return Action.LOGIN;
        log.info("url = " + request.getRequestURL().toString() + "   " + actionName);
        response.sendRedirect("/common/authority_lose.action");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
