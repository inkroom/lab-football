package com.nsu.bean.student.score;

import com.nsu.common.annotation.ValidateAnnotation;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/13
 * @Time 17:22
 * @Descorption 积分榜搜索功能表单bean
 */
public class ScoreSearchBean {
    //是否支持模糊搜索，0不支持，1支持
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[01]", errorMsg = "请指定是否支持模糊搜索")
    private Integer isBlur;
    //0按学校搜索，1按学生搜索
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE, customRegex = "[01]", errorMsg = "请指定搜索类型")
    private Integer isStudent;
    //搜索的具体内容
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN_EN_NUMBER, errorMsg = "搜索内容不合法")
    private String content;
    //下拉框选择的具体学校名称
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN_EN_NUMBER, errorMsg = "请正确选择学校", isAllowEmpty = true)
    private String schoolName;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_POSITIVE_INTEGER, errorMsg = "错误页码")
    private Integer now;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getNow() {
        return now;
    }

    public void setNow(Integer now) {
        this.now = now;
    }

    public Integer getIsBlur() {
        return isBlur;
    }

    public void setIsBlur(Integer isBlur) {
        this.isBlur = isBlur;
    }


    public Integer getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Integer isStudent) {
        this.isStudent = isStudent;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ScoreSearchBean{" +
                "isBlur=" + isBlur +
                ", isStudent=" + isStudent +
                ", content='" + content + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", now=" + now +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }
}
