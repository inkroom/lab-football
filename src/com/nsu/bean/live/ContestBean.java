package com.nsu.bean.live;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.bean.live
 * @Description: (赛事信息Bean)
 * @date 2017/4/19 15:14
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContestBean {
    @JsonProperty("comId")
    private String competitionId; //赛事ID
    @JsonProperty("comName")
    private String competitionName; //赛事名称
    @JsonProperty("rId")
    private String raceId; //赛程ID
    @JsonProperty("hTId")
    private String homeTeamId; //主队ID
    @JsonProperty("vTId")
    private String visitingTeamId; //客队ID

    private String regularHomeTeamScore; //常规赛主队得分

    private String regularVisitingTeamScore; //常规赛客队得分

    private String overtimeHomeTeamScore; //加时赛主队得分

    private String overtimeVisitingTeamScore; //加时赛客队得分

    private String penaHomeTeamScore; //点球决胜主队得分

    private String penaVisitingTeamScore; //点球决胜客队得分
    @JsonProperty("startTime")
    private String startTime; //赛程开始时间
    @JsonProperty("endTime")
    private String endTime; //赛课结束时间
    @JsonProperty("hTName")
    private String homeTeamName;//主队名称
    @JsonProperty("vTName")
    private String visitingTeamName;//客队名称
    @JsonProperty("hTBadgePach")
    private String homeTeamBadgePach;//主队头像路径
    @JsonProperty("vTBadgepach")
    private String visitingTeamBadgePach;//客队头像路径
    @JsonProperty("level")
    private String competitionLevel; //赛事级别：1小学，2中学，3高中，4大学 ，5不限
    @JsonProperty("cityCode")
    private String competitionCityCode;// 市级代码
    @JsonProperty("liveType")
    private String raceLiveType; //直播 状态 1未开始,2正在直播,3直播结束 ; 查询时1查询尚未开始和已经开始，2查询结束的比赛
    @JsonProperty("homeScore")
    private Integer homeScore;  //主队最终得分
    @JsonProperty("visitingScore")
    private Integer visitingScore; //客队最终得分

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getVisitingScore() {
        return visitingScore;
    }

    public void setVisitingScore(Integer visitingScore) {
        this.visitingScore = visitingScore;
    }

    public String getCompetitionLevel() {
        return competitionLevel;
    }

    public void setCompetitionLevel(String competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    public String getCompetitionCityCode() {
        return competitionCityCode;
    }

    public void setCompetitionCityCode(String competitionCityCode) {
        this.competitionCityCode = competitionCityCode;
    }

    public String getRaceLiveType() {
        return raceLiveType;
    }

    public void setRaceLiveType(String raceLiveType) {
        this.raceLiveType = raceLiveType;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getVisitingTeamId() {
        return visitingTeamId;
    }

    public void setVisitingTeamId(String visitingTeamId) {
        this.visitingTeamId = visitingTeamId;
    }

    public String getRegularHomeTeamScore() {
        return regularHomeTeamScore;
    }

    public void setRegularHomeTeamScore(String regularHomeTeamScore) {
        this.regularHomeTeamScore = regularHomeTeamScore;
    }

    public String getRegularVisitingTeamScore() {
        return regularVisitingTeamScore;
    }

    public void setRegularVisitingTeamScore(String regularVisitingTeamScore) {
        this.regularVisitingTeamScore = regularVisitingTeamScore;
    }

    public String getOvertimeHomeTeamScore() {
        return overtimeHomeTeamScore;
    }

    public void setOvertimeHomeTeamScore(String overtimeHomeTeamScore) {
        this.overtimeHomeTeamScore = overtimeHomeTeamScore;
    }

    public String getOvertimeVisitingTeamScore() {
        return overtimeVisitingTeamScore;
    }

    public void setOvertimeVisitingTeamScore(String overtimeVisitingTeamScore) {
        this.overtimeVisitingTeamScore = overtimeVisitingTeamScore;
    }

    public String getPenaHomeTeamScore() {
        return penaHomeTeamScore;
    }

    public void setPenaHomeTeamScore(String penaHomeTeamScore) {
        this.penaHomeTeamScore = penaHomeTeamScore;
    }

    public String getPenaVisitingTeamScore() {
        return penaVisitingTeamScore;
    }

    public void setPenaVisitingTeamScore(String penaVisitingTeamScore) {
        this.penaVisitingTeamScore = penaVisitingTeamScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getVisitingTeamName() {
        return visitingTeamName;
    }

    public void setVisitingTeamName(String visitingTeamName) {
        this.visitingTeamName = visitingTeamName;
    }

    public String getHomeTeamBadgePach() {
        return homeTeamBadgePach;
    }

    public void setHomeTeamBadgePach(String homeTeamBadgePach) {
        this.homeTeamBadgePach = homeTeamBadgePach;
    }

    public String getVisitingTeamBadgePach() {
        return visitingTeamBadgePach;
    }

    public void setVisitingTeamBadgePach(String visitingTeamBadgePach) {
        this.visitingTeamBadgePach = visitingTeamBadgePach;
    }

    @Override
    public String toString() {
        return "ContestBean{" +
                "competitionId='" + competitionId + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", raceId='" + raceId + '\'' +
                ", homeTeamId='" + homeTeamId + '\'' +
                ", visitingTeamId='" + visitingTeamId + '\'' +
                ", regularHomeTeamScore='" + regularHomeTeamScore + '\'' +
                ", regularVisitingTeamScore='" + regularVisitingTeamScore + '\'' +
                ", overtimeHomeTeamScore='" + overtimeHomeTeamScore + '\'' +
                ", overtimeVisitingTeamScore='" + overtimeVisitingTeamScore + '\'' +
                ", penaHomeTeamScore='" + penaHomeTeamScore + '\'' +
                ", penaVisitingTeamScore='" + penaVisitingTeamScore + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", visitingTeamName='" + visitingTeamName + '\'' +
                ", homeTeamBadgePach='" + homeTeamBadgePach + '\'' +
                ", visitingTeamBadgePach='" + visitingTeamBadgePach + '\'' +
                ", competitionLevel='" + competitionLevel + '\'' +
                ", competitionCityCode='" + competitionCityCode + '\'' +
                ", raceLiveType='" + raceLiveType + '\'' +
                '}';
    }
}
