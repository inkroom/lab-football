package cn.nsu.edu.web.four.beans.teams;

import java.util.ArrayList;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
//球队的所有信息(队员，教练，球队名)
public class TeamInfos {
    private Integer teamId;//球队Id
    private String teamName;//球队名称
    private Integer teamClazz;//球队的班级
    private Integer teamStatus = 0;//球队的状态
    private ArrayList<Integer> playerIds;//选中球员的所有id
    private Integer teamCoachId;//教练的id
    private Integer orgId;//组织id，默认为1
//    private Integer playerId;//每一个球员的ID

//    public Integer getPlayerId() {
//        return playerId;
//    }
//
//    public void setPlayerId(Integer playerId) {
//        this.playerId = playerId;
//    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTeamCoachId() {
        return teamCoachId;
    }

    public void setTeamCoachId(Integer teamCoachId) {
        this.teamCoachId = teamCoachId;
    }

    public ArrayList<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(ArrayList<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamClazz() {
        return teamClazz;
    }

    public void setTeamClazz(Integer teamClazz) {
        this.teamClazz = teamClazz;
    }

    public Integer getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Integer teamStatus) {
        this.teamStatus = teamStatus;
    }
}
