package cn.edu.nsu.lib.bean.admin.db;

import java.math.BigInteger;

public class DbLab {
    private int id;
    private String name;
    private String lab_describe;
    private BigInteger qq_group;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLab_describe() {
        return lab_describe;
    }

    public void setLab_describe(String lab_describe) {
        this.lab_describe = lab_describe;
    }

    public BigInteger getQq_group() {
        return qq_group;
    }

    public void setQq_group(BigInteger qq_group) {
        this.qq_group = qq_group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
