package cn.nsu.edu.web.four.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现自定义contextPath
 */
public class ContextPathAop implements MethodInterceptor {
    private String contextPath;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.info(methodInvocation.getMethod().toString());
        logger.info(methodInvocation.getThis().toString());
        if (contextPath != null)
            return contextPath;
        return methodInvocation.proceed();
    }
}
