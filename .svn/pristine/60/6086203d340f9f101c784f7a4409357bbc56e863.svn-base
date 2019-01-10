package cn.nsu.edu.web.four.annotation;

import cn.nsu.edu.web.four.enums.Role;

import java.lang.annotation.*;

/**
 * 安全注解
 * 包括权限拦截、token验证
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    //权限身份
    Role[] roles() default {};
    //是否生成token
    boolean createToken () default true;
    //是否使用token
    boolean checkToken() default false;
}
