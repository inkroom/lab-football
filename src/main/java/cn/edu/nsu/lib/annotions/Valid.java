package cn.edu.nsu.lib.annotions;

import cn.edu.nsu.lib.enums.Result;

import java.lang.annotation.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/16
 * @Time 13:12
 * @Descorption
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Valid {

    //验证顺序为，是否为空-》长度-》正则

    String regex() default ".*";//正则

    //长度验证在正则验证之前
    int minLength() default 0;//字符串最小长度

    int maxLength() default 10;//最大长度

    Result code() default Result.PARAM_NOT_SUIT;//错误代码

    boolean emptyAble() default false;//是否允许为空

}
