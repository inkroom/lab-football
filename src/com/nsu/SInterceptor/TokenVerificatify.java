package com.nsu.SInterceptor;

import com.nsu.common.Anonymous;
import com.nsu.common.TokenImmune;
import com.nsu.util.core.InfoProtUtil;
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
 * @Time 9:53
 * @Descorption
 */
public class TokenVerificatify implements HandlerInterceptor {
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        String actionName = request.getRequestURI();
        String token = request.getParameter("token");
        HandlerMethod method = null;
        if (o instanceof HandlerMethod) {
            method = (HandlerMethod) o;
            if (method.getMethodAnnotation(TokenImmune.class) != null)
                return true;
        } else {
            return true;
        }
//        Action action = (Action) invocation.getAction();
//
//        //拥有tokenImmue注解的action方法直接跳过此拦截器
//        Method method = invocation.getAction().getClass().getMethod(invocation.getProxy().getMethod(), null);
//        Annotation antation = method.getAnnotation(TokenImmune.class);
//        if(antation!=null){
//            return invocation.invoke();
//        }

        //action名称中带有login词语的都会重新分配token令牌
        if (actionName.toLowerCase().contains("login")) {
            String newToken = InfoProtUtil.getRandomString(16);
            request.getSession().setAttribute("token", newToken);
            response.setHeader("token", newToken);
        }

        //没有请求参数的action直接放行
        if (request.getParameterMap().size() == 0 ||
                method.getBean() instanceof Anonymous || request.getRequestURI().contains("login")) {
            return true;
//            return invocation.invoke();
        }

        //token令牌比对
        if (token != null && token.equals(request.getSession().getAttribute("token"))) {
            //生成新的token
            String newToken = InfoProtUtil.getRandomString(16);
            request.getSession().setAttribute("token", newToken);


            response.setHeader("token", newToken);

//            String result = invocation.invoke();
//            return result;
            return true;
        }

        //将错误信息打给浏览器
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().write("<script>alert('此页面为高度安全页面，为了您信息的安全,请不要点击浏览器的回退、刷新按钮或重复提交数据！')</script>");

        //将页面返回访问者的地址
        log.info("url = " + request.getRequestURL().toString());
        response.sendRedirect("/common/token_error.action");
//        return "tokenReturn";
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
