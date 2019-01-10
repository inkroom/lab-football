package cn.edu.nsu.lib.bean.admin;

import java.math.BigInteger;

public class Prize {
    /**
     * 用来和前端交互的实体类
     * prize表缺少学生名字，通过student表找到学生名字添加到这个实体类
     */
    private int	id ; //奖项主键
    private BigInteger owner;//所属人学号
    private String owner_name;//所属人名字
    private String prize_name;//获奖名字
    private String category; //学科竞赛 课外活动
    private String region;//国家级 省级什么的
    private String rank;//获奖等级
    private String url;//奖项官网
    private String time;//获奖时间
    private boolean check;//审核结果
    private  int lab_id;//实验室id
    private String committee;//委员会
    private String adviser;//指导老师

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getOwner() {
        return owner;
    }

    public void setOwner(BigInteger owner) {
        this.owner = owner;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
