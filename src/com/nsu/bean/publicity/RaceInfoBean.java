package com.nsu.bean.publicity;

public class RaceInfoBean {

	private String raceNum;//赛程编号
	private String gameName;//赛事名称
	private String raceName;//赛程名称
	private String raceRegion;//赛程地区
	private String startTime;//赛程开始时间
	private String endTime;//赛程结束时间
	private String teamOne;//主队
	private String teamTwo;//客队
	private String fieldNum;//场序号
	private String formalScoreOne;//常规赛主队得分
	private String formalScoreTwo;//常规赛客队得分
	private String overScoreOne;//加时赛主队得分
	private String overScoreTwo;//加时赛客队得分
	private String spotScoreOne;//点球决胜主队得分
	private String spotScoreTwo;//点球决胜客队得分
	private String winTeam;//获胜队伍（0主队获胜，1客队获胜）
	private String textRed;//红牌情况说明
	private String textPunish;//罚球点球情况说明
	private String textVerify;//验证错判漏判说明
	private String referee;//裁判员
	private String status;//赛事直播状态 1：尚未直播，2：正在直播，3：直播结束
	public String getRaceNum() {
		return raceNum;
	}
	public void setRaceNum(String raceNum) {
		this.raceNum = raceNum;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public String getRaceRegion() {
		return raceRegion;
	}
	public void setRaceRegion(String raceRegion) {
		this.raceRegion = raceRegion;
	}
	public String getTeamOne() {
		return teamOne;
	}
	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}
	public String getTeamTwo() {
		return teamTwo;
	}
	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}
	public String getFieldNum() {
		return fieldNum;
	}
	public void setFieldNum(String fieldNum) {
		this.fieldNum = fieldNum;
	}
	public String getFormalScoreOne() {
		return formalScoreOne;
	}
	public void setFormalScoreOne(String formalScoreOne) {
		this.formalScoreOne = formalScoreOne;
	}
	public String getFormalScoreTwo() {
		return formalScoreTwo;
	}
	public void setFormalScoreTwo(String formalScoreTwo) {
		this.formalScoreTwo = formalScoreTwo;
	}
	public String getOverScoreOne() {
		return overScoreOne;
	}
	public void setOverScoreOne(String overScoreOne) {
		this.overScoreOne = overScoreOne;
	}
	public String getOverScoreTwo() {
		return overScoreTwo;
	}
	public void setOverScoreTwo(String overScoreTwo) {
		this.overScoreTwo = overScoreTwo;
	}
	public String getSpotScoreOne() {
		return spotScoreOne;
	}
	public void setSpotScoreOne(String spotScoreOne) {
		this.spotScoreOne = spotScoreOne;
	}
	public String getSpotScoreTwo() {
		return spotScoreTwo;
	}
	public void setSpotScoreTwo(String spotScoreTwo) {
		this.spotScoreTwo = spotScoreTwo;
	}
	public String getTextRed() {
		return textRed;
	}
	public void setTextRed(String textRed) {
		this.textRed = textRed;
	}
	public String getTextPunish() {
		return textPunish;
	}
	public void setTextPunish(String textPunish) {
		this.textPunish = textPunish;
	}
	public String getTextVerify() {
		return textVerify;
	}
	public void setTextVerify(String textVerify) {
		this.textVerify = textVerify;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWinTeam() {
		return winTeam;
	}
	public void setWinTeam(String winTeam) {
		this.winTeam = winTeam;
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
	
}
