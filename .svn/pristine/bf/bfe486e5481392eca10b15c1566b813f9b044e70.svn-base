package com.nsu.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.common.annotation
 * @Description:
 * @date 17/4/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @ interface InterceptorAnno {
    boolean createToken() default false;
    boolean checkToken() default false;
    boolean removeToken() default false;
    boolean isAjax() default false;
    boolean isRestful() default false;
}
