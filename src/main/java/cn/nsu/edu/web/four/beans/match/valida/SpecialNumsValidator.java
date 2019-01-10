package cn.nsu.edu.web.four.beans.match.valida;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author Lika
 * @date 2018/04/13 15:14
 * Description:
 */
public class SpecialNumsValidator implements ConstraintValidator<SpecialNums, Integer> {

    private int[] nums;

    @Override
    public void initialize(SpecialNums constraintAnnotation) {
        this.nums = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(nums).anyMatch(i -> i == value);
    }
}
