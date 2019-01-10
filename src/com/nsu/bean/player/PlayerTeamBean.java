package com.nsu.bean.player;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName PlayerTeamBean
 * @Description (球员相关球队bean)
 * @author hm
 * @Date 2017年4月17日 下午9:12:37
 * @version 1.0.0
 */
public class PlayerTeamBean {
	private String teamPlayerStatus;
	// 球队ID
	private String teamID;
//	// 球队编号
//	private String teaId;
	// 球队名
	private String teamName;
	// 队徽
	private String teamBadge;
	// 队旗
	private String teamFlag;
	// 励志标语
	private String teamInspirationalSlogan;
	// 球队类型
	private String teamType;
	// 球队等级
	private String teamRank;
	// 球队地区
	@Value(value="无")
	private String teamRegion;
	// 球队所属机构
	@Value(value="无")
	private String teamAffiliation;
	//球队胜率
	@Value(value="0")
	private String winRate;
	//比赛场次
	@Value(value="0")
	private String matchNum;
	//积分
	@Value(value="0")
	private String integral;
	
	private String applyTime;
	
	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getTeamPlayerStatus() {
		return teamPlayerStatus;
	}

	public void setTeamPlayerStatus(String teamPlayerStatus) {
		this.teamPlayerStatus = teamPlayerStatus;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamBadge() {
		return teamBadge;
	}

	public void setTeamBadge(String teamBadge) {
		this.teamBadge = teamBadge;
	}

	public String getTeamFlag() {
		return teamFlag;
	}

	public void setTeamFlag(String teamFlag) {
		this.teamFlag = teamFlag;
	}

	public String getTeamInspirationalSlogan() {
		return teamInspirationalSlogan;
	}

	public void setTeamInspirationalSlogan(String teamInspirationalSlogan) {
		this.teamInspirationalSlogan = teamInspirationalSlogan;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public String getTeamRank() {
		return teamRank;
	}

	public void setTeamRank(String teamRank) {
		this.teamRank = teamRank;
	}

	public String getTeamRegion() {
		return teamRegion;
	}

	public void setTeamRegion(String teamRegion) {
		this.teamRegion = teamRegion;
	}

	public String getTeamAffiliation() {
		return teamAffiliation;
	}

	public void setTeamAffiliation(String teamAffiliation) {
		this.teamAffiliation = teamAffiliation;
	}

	public String getWinRate() {
		return winRate;
	}

	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}

	public String getMatchNum() {
		return matchNum;
	}

	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	@Override
	public String toString() {
		return "PlayerTeamBean [teamPlayerStatus=" + teamPlayerStatus + ", teamID=" + teamID + ", teaId=" 
				+ ", teamName=" + teamName + ", teamBadge=" + teamBadge + ", teamFlag=" + teamFlag
				+ ", teamInspirationalSlogan=" + teamInspirationalSlogan + ", teamType=" + teamType + ", teamRank="
				+ teamRank + ", teamRegion=" + teamRegion + ", teamAffiliation=" + teamAffiliation + ", winRate="
				+ winRate + ", matchNum=" + matchNum + ", integral=" + integral + "]";
	}
	
}
