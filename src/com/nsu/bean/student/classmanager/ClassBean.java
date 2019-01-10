package com.nsu.bean.student.classmanager;


/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 9:45
 * @Descorption 班级bean
 */
public class ClassBean {
    private String school;
    private String name;
    private String grade;
    private int status;
    private String joinTime;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "ClassBean{" +
                "school='" + school + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", status=" + status +
                ", joinTime=" + joinTime +
                '}';
    }
}
