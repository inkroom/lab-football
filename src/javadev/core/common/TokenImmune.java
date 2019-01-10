package javadev.core.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * 有此注解的method可以跳过token拦截器
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenImmune {}
