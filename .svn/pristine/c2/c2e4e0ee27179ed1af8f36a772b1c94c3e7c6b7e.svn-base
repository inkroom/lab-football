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
 * token拦截器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());

    private String tokenErrorUrl;

    public void setTokenErrorUrl(String tokenErrorUrl) {
        this.tokenErrorUrl = tokenErrorUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((handler instanceof HandlerMethod)) {
            HandlerMethod method = (HandlerMethod) handler;
            Security a = method.getMethodAnnotation(Security.class);
            if (a != null) {
                if (a.checkToken()) {
                    //验证token
                    //获取token
                    String requestToken = CodeUtil.getToken(request);
                    String sessionToken = (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_TOKEN);
                    if (requestToken == null || sessionToken == null || !requestToken.equals(sessionToken)) {
                        log.debug("token错误");
                        if (RequestUtil.isAjax(request)) {
                            MessageDto dto = new MessageDto(Result.TOKEN_ERROR);
                            ResponseUtil.outJson(response, dto.toString());
                        } else {
                            request.getRequestDispatcher(tokenErrorUrl).forward(request, response);
                        }
                        return false;
                    }
                }
                if (a.createToken()) {//创建token
                    String token = CodeUtil.createToken();
                    request.getSession().setAttribute(BaseStatic.KEY_SESSION_TOKEN, token);
                    log.debug("创建token=" + token);
                }
            }
        } else {
            log.info(request.getRequestURI());
        }
        return true;
    }

}
