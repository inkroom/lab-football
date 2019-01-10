package cn.nsu.edu.web.four.beans.match;


import java.util.Date;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.beans.match
 * @date 2018/3/20 10:05
 * @description
 **/
public class MatchPlayer {

    private int matchId;

    private int orgId;

    private String orgName;

    private int teamId;

    private int playerId;

    private String teamName;

    private String playerName;

    private int status;

    private String sex;

    private Date time;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public MatchPlayer() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public MatchPlayer(int matchId, int orgId, int teamId, int playerId) {
        this.matchId = matchId;
        this.orgId = orgId;
        this.teamId = teamId;
        this.playerId = playerId;
        this.status=0;
    }

    @Override
    public String toString() {
        return "MatchPlayer{" +
                "matchId=" + matchId +
                ", orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", teamId=" + teamId +
                ", playerId=" + playerId +
                ", teamName='" + teamName + '\'' +
                ", playerName='" + playerName + '\'' +
                ", status=" + status +
                '}';
    }
}
