package com.nsu.interceptor;

import com.nsu.bean.common.CsrfTokenBean;
import com.nsu.bean.index.AjaxBean;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.service.common.CsrfTokenRepository;
import com.nsu.util.Clear;
import com.nsu.util.RSAencrypt.TokenBean;
import com.nsu.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${CLASS_NAME}
 * @Package com.nsu.interceptor
 * @Description:
 * @date 17/4/21
 */
public class CsrfInterceptor implements HandlerInterceptor {

    private static final String ANNOTAION_CONFLICT =" 注释冲突！";

    private static final String TOKEN_NULL = " token 为空 ！";

    private static final String TOKEN_PAST = " token 错误！";

    private static final String TOKEN_ERROR = " 提交失败，请重试！";

    private static final String TOKEN_ERROR_COMMON = "抱歉,页面已失效，请重新提交!";

    private final Log log = LogFactory.getLog(getClass());

    @Resource(name = "csrfTokenRepository")
    private CsrfTokenRepository csrfTokenRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        InterceptorAnno interceptorAnno = null;
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 非控制器请求直接跳出
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
             interceptorAnno = handlerMethod.getMethodAnnotation(InterceptorAnno.class);
        }catch (Exception e){

        }
        // 判断是否含有@CsrfToken注解
        if (null == interceptorAnno) {
            return true;
        }

        if (interceptorAnno.isRestful()){
            if (!request.getServletPath().equals(Clear.clearAll(request.getServletPath()))){
                log.error(" XSS URL"+request.getServletPath());
                response.sendRedirect(request.getContextPath()+"/"+Clear.clearAll(request.getServletPath().toString()));
                return false;
            }
        }


        // create、remove同时为true时异常
        if (interceptorAnno.createToken() && interceptorAnno.removeToken()) {
            log.error(ANNOTAION_CONFLICT);
            return renderError(request, response, Boolean.FALSE, ANNOTAION_CONFLICT);
        }
        // 创建
        if (interceptorAnno.createToken()) {
            log.info("create token");
            CsrfTokenBean token = csrfTokenRepository.generateToken(request);
            csrfTokenRepository.saveToken(token, request, response);
            // 缓存一个表单页面地址的url
            csrfTokenRepository.cacheUrl(request, response);
            request.setAttribute(token.getParameterName(), token);
            return true;
        }else if (interceptorAnno.checkToken()){
            log.info("check token exists");
            // 判断是否ajax请求
            boolean isAjax = false;
            if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") )|| interceptorAnno.isAjax()){
                isAjax = true;
            }

            // 校验，并且清除
            CsrfTokenBean tokenBean = csrfTokenRepository.loadToken(request);
            if (tokenBean == null) {
                return renderError(request, response, isAjax, TOKEN_NULL);
            }
            log.info("--tokenBean--"+tokenBean.getHeaderName());
            log.info("--tokenBean--"+tokenBean.getToken());
            log.info("--tokenBean--"+tokenBean.getParameterName());
            String actualToken = request.getHeader(tokenBean.getHeaderName());
            if (actualToken == null) {
                actualToken = request.getParameter(tokenBean.getParameterName());
            }
            log.info("-------------------------------------------");
            log.info("-------------------------------------------"+actualToken);

            log.info("-------------------------------------------");
            if (!tokenBean.getToken().equals(actualToken)) {
                return renderError(request, response, isAjax, TOKEN_PAST);
            }
            return true;
        }else {
            return true;
        }


    }

    private boolean renderError(HttpServletRequest request, HttpServletResponse response,
                                boolean isAjax, String message) throws IOException {
        // 获取缓存的cacheUrl
        String cachedUrl = csrfTokenRepository.getRemoveCacheUrl(request, response);
        // ajax请求直接抛出异常，因为{@link ExceptionResolver}会去处理
        if (isAjax) {
            AjaxBean ajaxBean = new AjaxBean();
            ajaxBean.setStatus("500");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("抱歉,页面已失效，请重新提交!");
            try {
                ResponseUtil.write(response, JsonMapper.toJsonString(ajaxBean));
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("抱歉,页面已失效，请重新提交!");
            return false;
        }
        // 非ajax CsrfToken校验异常，先清理token
        csrfTokenRepository.saveToken(null, request, response);
        log.info("Csrf[redirectUrl]:\t" + cachedUrl);
        response.sendRedirect(cachedUrl);
        log.info("GGG!");
        return false;
    }

    /**
     * 用于清理@CsrfToken保证只能请求成功一次
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 非控制器请求直接跳出
            if (!(handler instanceof HandlerMethod)) {
                return;
            }
            InterceptorAnno interceptorAnno = handlerMethod.getMethodAnnotation(InterceptorAnno.class);
            if (interceptorAnno == null || !interceptorAnno.removeToken()) {
                return;
            }
            csrfTokenRepository.getRemoveCacheUrl(request, response);
            csrfTokenRepository.saveToken(null, request, response);
        }catch (Exception e){

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
