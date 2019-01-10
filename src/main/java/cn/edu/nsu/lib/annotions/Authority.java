package cn.edu.nsu.lib.annotions;

import java.lang.annotation.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/2
 * @Time 21:26
 * @Descorption 权限
 */

@Retention(RetentionPolicy.RUNTIME)
//使用范围
@Target({ElementType.METHOD})
//写入javadoc中
@Documented
//@interface让该类继承Annotation
public @interface Authority {
    Role[] role() default Role.STUDENT;


    enum Role {
        // TODO: 2017/9/17 权限需具体分类
        STUDENT,//学生
        MANAGER,//实验室管理员
        TEACHER,//实验室指导老师
        LEADER//最高管理员，领导
    }
}
