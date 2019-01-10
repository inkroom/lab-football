package cn.inkroom.web.money.gate.interceptors;

import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.utils.http.RequestUtil;
import cn.inkroom.web.money.gate.utils.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ((handler instanceof HandlerMethod)) {
            if (RequestUtil.getLogin(request)==null) {
                if (RequestUtil.isAjax(request)) {
                    ResponseUtil.outJson(response, new MessageDto(Result.LOGIN_NOT).toString());
                    return false;
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/common/power.jsp").forward(request, response);
                    return false;
                }
            }
        }
        return true;
    }
}
