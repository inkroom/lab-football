package cn.edu.nsu.lib.bean.student;

public class StuPrizeBean {
    /*获奖信息bean*/
    private int id;
    private int lab_id;
    private long  owner;   //学号
    private String prize_name;//奖项名
    private String region;  //奖项级别 国家 省级
    private String rank;  //奖项等级 几等奖
    private String category; //类别  学科竞赛 课外活动
    private String url; //奖项官网
    private String time; //获奖时间
    private String  committee;//提交者
    private String adviser;//回复
    private int is_checked;//审核通过状态
    public int getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(int is_checked) {
        this.is_checked = is_checked;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String commitee) {
        this.committee = commitee;
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    @Override
    public String toString() {
        return "StuPrizeBean{" +
                "id=" + id +
                ", lab_id=" + lab_id +
                ", owner=" + owner +
                ", prize_name='" + prize_name + '\'' +
                ", region='" + region + '\'' +
                ", rank='" + rank + '\'' +
                ", category='" + category + '\'' +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                ", committee='" + committee + '\'' +
                ", adviser='" + adviser + '\'' +
                ", is_checked=" + is_checked +
                '}';
    }
}
