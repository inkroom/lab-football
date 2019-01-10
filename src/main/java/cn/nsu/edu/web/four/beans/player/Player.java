package cn.nsu.edu.web.four.beans.player;

import cn.nsu.edu.web.four.beans.matchStaff.MatchStaff;
import cn.nsu.edu.web.four.config.RegexStatic;

import javax.validation.constraints.*;

/**
 * @author :王新璋
 * @Description: 球员的实体类
 * @date :18.01 2018/3/15
 */

public class Player {

    private Integer idPlayer;//球员编号

    private String userName;//球员用户名

    @NotNull
    @Pattern(regexp = RegexStatic.CHINESE)
    private String name;//球员姓名

    @NotNull
    private String password;//球员密码

    private String headPic;//球员头像

    private String salt;//是否加密

    private Integer orgId;//机构编号

    @NotNull
    @Min(1)
    @Max(9)
    private Integer grade;//球员年级（例如2015）

    @NotNull
    @Min(1)
    private Integer classes;//球员班级

    private Integer status;//球员状态

    private Integer score;//球员个人积分

    private Integer sex;//球员性别

    @NotNull
    @Pattern(regexp = RegexStatic.ID_CARD)
    private String idCard;//身份证


    private MatchStaff matchStaff;

    public MatchStaff getMatchStaff() {
        return matchStaff;
    }

    public void setMatchStaff(MatchStaff matchStaff) {
        this.matchStaff = matchStaff;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Player{" +
                "idPlayer=" + idPlayer +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", headPic='" + headPic + '\'' +
                ", salt='" + salt + '\'' +
                ", orgId=" + orgId +
                ", grade=" + grade +
                ", classes=" + classes +
                ", status=" + status +
                ", score=" + score +
                ", sex=" + sex +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
