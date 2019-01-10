package cn.nsu.edu.web.four.beans.schedule;

import cn.nsu.edu.web.four.beans.match.valida.SpecialNums;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

/**
 * @author Xuing
 * @date 2018/3/20.
 */
public class Schedule {

    private String teamNameA;

    private String teamNameB;

    private Integer idSchedule;

    private Integer matchId;

    private Integer teamA;
    @SpecialNums(value = {0, 1, 2})
    private Integer level;

    private Integer teamB;
    @DecimalMin("0")
    @DecimalMax("100")
    private Integer goalA;
    @DecimalMin("0")
    @DecimalMax("100")
    private Integer goalB;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    private Long time;

    private String place;

    private Integer status;

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", matchId=" + matchId +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                ", goalA=" + goalA +
                ", goalB=" + goalB +
                ", beginTime=" + beginTime +
                ", time=" + time +
                ", place='" + place + '\'' +
                ", status=" + status +
                '}';
    }

    public String getTeamNameA() {
        return teamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        this.teamNameA = teamNameA;
    }

    public String getTeamNameB() {
        return teamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        this.teamNameB = teamNameB;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeamA() {
        return teamA;
    }

    public void setTeamA(Integer teamA) {
        this.teamA = teamA;
    }

    public Integer getTeamB() {
        return teamB;
    }

    public void setTeamB(Integer teamB) {
        this.teamB = teamB;
    }

    public Integer getGoalA() {
        return goalA;
    }

    public void setGoalA(Integer goalA) {
        this.goalA = goalA;
    }

    public Integer getGoalB() {
        return goalB;
    }

    public void setGoalB(Integer goalB) {
        this.goalB = goalB;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
        this.beginTime = new Date(time);
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        this.time = beginTime.getTime();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}