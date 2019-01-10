package com.nsu.bean.student.exercise;

import com.nsu.common.annotation.ValidateAnnotation;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/13
 * @Time 17:32
 * @Descorption 练习功能搜索表单bean
 */
public class ExerciseSearchBean {
    //难度
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[123]", errorMsg = "难度选择错误")
    private Integer difficulty;
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[1234]", errorMsg = "类型选择错误")
    private Integer type;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN_EN, errorMsg = "考点选择错误")
    private String point;
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[12]", errorMsg = "科目选择错误")
    private Integer object;

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseSearchBean that = (ExerciseSearchBean) o;

        if (!difficulty.equals(that.difficulty)) return false;
        if (!type.equals(that.type)) return false;
        if (!point.equals(that.point)) return false;
        return object.equals(that.object);
    }

    @Override
    public int hashCode() {
        int result = difficulty.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + point.hashCode();
        result = 31 * result + object.hashCode();
        return result;
    }
}

