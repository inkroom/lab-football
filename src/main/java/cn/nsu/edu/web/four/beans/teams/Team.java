package cn.nsu.edu.web.four.beans.teams;

import java.util.Date;

/**
 * @author :孙帅
 * @Description: 球队的实体类
 * @date :15:55 2018/3/15
 */

public class Team {

    private  int idTeam;//球队编号

    private  String  name; //球队名称

    private  int  type;//球队类型（具体看数据库注释）

    private  int  status;//球队状态

    private  int orgId;//机构编号

    private Date createTime;//球队创建时间

    private int teamSum;//球对人数

    private int teamScore;//球队积分


    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTeamSum() {
        return teamSum;
    }

    public void setTeamSum(int teamSum) {
        this.teamSum = teamSum;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idTeam=" + idTeam +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", orgId=" + orgId +
                ", createTime=" + createTime +
                ", teamSum=" + teamSum +
                ", teamScore=" + teamScore +
                '}';
    }
}
