package cn.nsu.edu.web.four.beans.scheduleStaff;

import java.util.Date;

public class ScheduleStaff {
    private Integer id;
    private Integer playerId;
    private Integer schId;
    private Integer teamId;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
