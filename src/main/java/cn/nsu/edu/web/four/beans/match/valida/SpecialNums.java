package cn.nsu.edu.web.four.beans.match.valida;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Lika
 * @date 2018/04/13 15:00
 * Description:验证为特定的数值
 * 可用于检测状态，人数等信息的合法性
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SpecialNumsValidator.class)
public @interface SpecialNums {
    String message() default "{cn.xuing.constraints.specialnums}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] value();

}
