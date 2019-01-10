package cn.edu.nsu.lib.bean.admin.db;

import java.math.BigInteger;

//signed (打卡表)｛date，register｝ 学号外键-》 student(学生表)｛student_id，name｝
public class DbStudentCheck {
    private int id;//实验室id
    private BigInteger stu_id;//学生id
    private String date;//时间
    private int lab_id;//所属实验室id
    private boolean register;//是否签到

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getStu_id() {
        return stu_id;
    }

    public void setStu_id(BigInteger stu_id) {
        this.stu_id = stu_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }
}
