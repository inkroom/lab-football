package com.nsu.bean.student.classmanager;

import com.nsu.common.annotation.ValidateAnnotation;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 10:25
 * @Descorption 加入班级表单bean
 */
public class JoinClassBean {
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_NUMBER, errorMsg = "班级号只能是数字")
    private Long classId;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_NUMBER, errorMsg = "验证口令只能是数字")
    private Long key;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
