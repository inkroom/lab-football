package com.nsu.bean.student.practice;

import com.nsu.common.annotation.ValidateAnnotation;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/13
 * @Time 17:32
 * @Descorption 练习功能搜索表单bean
 */
public class PracticeSearchBean {
    //难度
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[123]", errorMsg = "难度选择错误")
    private String difficulty;
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[12]", errorMsg = "类型选择错误")
    private String type;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN_EN_NUMBER, errorMsg = "考点选择错误")
    private String point;
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[12]", errorMsg = "科目选择错误")
    private String subject;

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "PracticeSearchBean{" +
                "difficulty='" + difficulty + '\'' +
                ", type='" + type + '\'' +
                ", point='" + point + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PracticeSearchBean that = (PracticeSearchBean) o;

        if (!difficulty.equals(that.difficulty)) return false;
        if (!type.equals(that.type)) return false;
        if (!point.equals(that.point)) return false;
        return subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        int result = difficulty.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + point.hashCode();
        result = 31 * result + subject.hashCode();
        return result;
    }


    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

