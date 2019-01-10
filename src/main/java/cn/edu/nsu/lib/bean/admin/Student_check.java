package cn.edu.nsu.lib.bean.admin;

import java.math.BigInteger;

//signed (打卡表)｛date，register｝ 学号外键-》 student(学生表)｛student_id，name｝
public class Student_check {
    /**
     * 此表是数据库学生表中名字和考勤表中考勤信息的结合
     * 由于考勤表中只有学生的id，没有学生的name
     */
    private BigInteger student_id;
    private String name;
    private String date;
    private boolean register;


    public BigInteger getStudent_id() {
        return student_id;
    }

    public void setStudent_id(BigInteger student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

}
