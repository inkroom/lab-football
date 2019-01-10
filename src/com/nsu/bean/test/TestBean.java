package com.nsu.bean.test;

import com.nsu.common.annotation.ValidateAnnotation;

import static com.nsu.common.annotation.ValidateAnnotation.VALIDATE_NUMBER;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: TestBean
 * @Package com.nsu.bean.test
 * @Description:
 * @date 7/10/17
 */
public class TestBean {
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN,isAllowEmpty = true,errorMsg = "用户名错误")
    private String username;

    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_NUMBER,errorMsg = "密码错误")
    private String password;

    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE,customRegex="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$",errorMsg = "customs只能输入两位小数！")
    private String constom;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConstom() {
        return constom;
    }

    public void setConstom(String constom) {
        this.constom = constom;
    }
}
