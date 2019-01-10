package cn.nsu.edu.web.four.beans.coach;

/**
 * @program: four
 * @description: 球队与教练的映射表bean
 * @author: ZhuShengpeng
 * @create: 2018-03-27 09:43
 **/
public class MTeamCoach {

    public int coachIdM;//教练id
    public String outTime;//离队时间
    public String joinTime;//入队时间
    public int status;//在役状态
    public String teamName;//球队名称
    public int type;//球队类型（具体看数据库注释）
    private int teamId;//球队id

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getCoachIdM() {
        return coachIdM;
    }

    public void setCoachIdM(int coachIdM) {
        this.coachIdM = coachIdM;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MTeamCoach{" +
                "teamId=" + teamId +
                ", coachIdM=" + coachIdM +
                ", outTime='" + outTime + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", status=" + status +
                ", teamName='" + teamName + '\'' +
                ", type=" + type +
                '}';
    }
}
