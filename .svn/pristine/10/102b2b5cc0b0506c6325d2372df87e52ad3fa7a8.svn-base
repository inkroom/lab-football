package com.nsu.service.common.impl;

import com.nsu.bean.common.CsrfTokenBean;
import com.nsu.service.common.CsrfTokenRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.interceptor
 * @Description:
 * @date 17/4/21
 */
@Service(value = "csrfTokenRepository")
public class HttpSessionCsrfTokenRepository implements CsrfTokenRepository {
    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");
    private static final String DEFAULT_CACHE_URL_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CACHE_URL");

    private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;
    private String headerName = DEFAULT_CSRF_HEADER_NAME;
    private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;
    private String cacheUrlAttributeName = DEFAULT_CACHE_URL_ATTR_NAME;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.csrf.CsrfTokenRepository#saveToken(org.
     * springframework .security.web.csrf.InterceptorAnno,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void saveToken(CsrfTokenBean token, HttpServletRequest request,
                          HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(this.sessionAttributeName);
            }
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute(this.sessionAttributeName, token);
        }
    }

    /**
     *
     * @param request the {@link HttpServletRequest} to use
     * @return
     */
    public CsrfTokenBean loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfTokenBean) session.getAttribute(this.sessionAttributeName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.csrf.CsrfTokenRepository#generateToken(javax.
     * servlet .http.HttpServletRequest)
     */
    public CsrfTokenBean generateToken(HttpServletRequest request) {
        return new CsrfTokenBean(this.headerName, this.parameterName,
                createNewToken());
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void cacheUrl(HttpServletRequest request, HttpServletResponse response) {
        String queryString = request.getQueryString();
        // 被拦截前的请求URL
        String redirectUrl = request.getRequestURI();
        if (StringUtils.isNotBlank(queryString)) {
            redirectUrl = redirectUrl.concat("?").concat(queryString);
        }
        HttpSession session = request.getSession();
        session.setAttribute(this.cacheUrlAttributeName, redirectUrl);
    }

    @Override
    public String getRemoveCacheUrl(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        String redirectUrl = (String) session.getAttribute(this.cacheUrlAttributeName);
        if (StringUtils.isBlank(redirectUrl)) {
            return null;
        }
        session.removeAttribute(this.cacheUrlAttributeName);
        return redirectUrl;
    }
}
