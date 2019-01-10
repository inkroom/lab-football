package com.nsu.bean.teacher;

import java.util.Date;

/**
 * @author ChenGang
 * @Title: TExamInformation
 * @Package com.nsu.bean.teacher
 * @Description:查看考试信息的bean
 * @date 2017/7/17 0017 下午 6:28
 **/
public class TExamInforBean{
    private Long eIId;

    private Integer examLevel;

    private Date startTime;

    private Date endTime;

    private String testName;

    private String subject;

    private String grade;

    private Integer stutas;

    private Long classId;

    public Long geteIId() {
        return eIId;
    }

    public void seteIId(Long eIId) {
        this.eIId = eIId;
    }

    public Integer getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(Integer examLevel) {
        this.examLevel = examLevel;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getStutas() {
        return stutas;
    }

    public void setStutas(Integer stutas) {
        this.stutas = stutas;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    private Long tId;

}
