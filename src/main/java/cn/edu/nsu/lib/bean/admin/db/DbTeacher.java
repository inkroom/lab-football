package cn.edu.nsu.lib.bean.admin.db;

import java.math.BigInteger;

public class DbTeacher {
    private BigInteger id;//老师id
    private String name;//老师名字
    private int gender;//老师性别
    private BigInteger tel;//电话

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public BigInteger getTel() {
        return tel;
    }

    public void setTel(BigInteger tel) {
        this.tel = tel;
    }
}
