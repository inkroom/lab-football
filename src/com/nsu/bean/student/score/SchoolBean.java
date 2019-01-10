package com.nsu.bean.student.score;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/14
 * @Time 10:18
 * @Descorption 学校
 */
public class SchoolBean {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "SchoolBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
