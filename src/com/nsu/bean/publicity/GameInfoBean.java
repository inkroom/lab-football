package com.nsu.bean.publicity;

public class GameInfoBean {

	private String gameNum;//赛事编号
	private String gameName;//赛事名称
	private String issue;//赛事发布者
	private String organization;//所属机构
	private String startTime;//赛事开始时间
	private String endTime;//赛事结束时间
	private String enrollStartTime;//赛事发布时间
	private String enrollEndTime;//赛事报名截止时间
	private String type;//赛事类型：1男子，2女子，3混合
	private String grade;//赛事级别：1小学，2中学，3高中，4大学 ，5不限
	private String gro;//足球组别：1、 5人， 2、7人 3、11人 4、不限
	private String level;//1省，2市，3县，4校，5不限
	private String createTime;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getGameNum() {
		return gameNum;
	}
	public void setGameNum(String gameNum) {
		this.gameNum = gameNum;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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
	public String getEnrollStartTime() {
		return enrollStartTime;
	}
	public void setEnrollStartTime(String enrollStartTime) {
		this.enrollStartTime = enrollStartTime;
	}
	public String getEnrollEndTime() {
		return enrollEndTime;
	}
	public void setEnrollEndTime(String enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return organization+" 添加一场赛事  "+gameName;
	}
}
