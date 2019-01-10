package cn.nsu.edu.web.four.beans.coach;


import cn.nsu.edu.web.four.config.RegexStatic;

import javax.validation.constraints.Pattern;



/**
 * @program: four
 * @description:
 * @author: ZhuShengpeng
 * @create: 2018-03-19 20:33
 **/

public class Coach {

    private int idCoach;//教练id


    private Integer orgId;//组织id

    @Pattern(regexp = RegexStatic.CHINESE)
    private String name;//姓名

    private Integer sex;//性别（1男，0女）

    @Pattern(regexp = RegexStatic.ID_CARD)
    private String idcard;//身份证号

    private float height;//身高

    private float weight;//体重

    private Integer status;//状态（0在任，1退休）

    @Pattern(regexp = RegexStatic.PHONE)
    private String phone;//电话

    private String photo;//照片

    private int age;//年龄

    private int score;//积分

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Integer idCoach) {
        this.idCoach = idCoach;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Coach() {
    }

    @Override
    public String toString() {
        return "Coach{" +
                "idCoach=" + idCoach +
                ", orgId=" + orgId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idcard='" + idcard + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
