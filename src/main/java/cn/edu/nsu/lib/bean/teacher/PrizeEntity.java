package cn.edu.nsu.lib.bean.teacher;
/**
 * 奖项表
 * creat_user: julse@qq.com creat_date: 2017/10/28
 **/
public class PrizeEntity {


    /**
     * 拥有奖项的学生学号
     */
    private String stu_id;
    /**
     * 拥有奖项的学生姓名
     */
    private String stu_name;
    /**
     * 获奖时间
     */
    private String time;
    /**
     * 奖项名称
     */
    private String prize_name;
    /**
     * 奖项相关链接
     */
    private String url;
    /**
     * 组委会
     */
    private String committee;
    /**
     * 指导老师
     */
    private String adviser;
    /**
     * 等级
     */
    private String rank;
    /**
     * 级别
     */
    private String region;
    /**
     * 种类
     */
    private String category;
    /**
     * 管理员是否审核
     */
    private String is_checked;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }
}
