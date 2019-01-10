package com.nsu.bean.teacher.information;

import com.nsu.common.annotation.ValidateAnnotation;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackageName : com.nsu.bean.teacher.information
 * @Author : BuDD
 * @CreateTime : 2017/7/18
 * @Version : 1.0
 * @Description : 获取页面提交上来的数据,暂时没有加入自定义正则
 * TODO：未加入自定义的规则
 */
public class InformationBean {
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_EN,errorMsg = "用户名错误")
    private String username;
    @ValidateAnnotation(validateType = ValidateAnnotation.CUSTOM_VALIDATE,errorMsg = "密码格式不正确")
    private String password;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_PERSON_NAME,errorMsg = "姓名不正确")
    private String tName;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_EN,errorMsg = "性别错误")
    private String tSex;
    @ValidateAnnotation(validateType = ValidateAnnotation.VALIDATE_CN,errorMsg = "教授年级")
    private String studygrade;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

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

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public String getStudygrade() {
        return studygrade;
    }

    public void setStudygrade(String studygrade) {
        this.studygrade = studygrade;
    }
}
