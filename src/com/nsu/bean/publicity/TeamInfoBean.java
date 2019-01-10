package com.nsu.bean.publicity;

public class TeamInfoBean {

	private String teamNum;//球队编号
	private String teamName;//球队名字
	private String coachName;//教练员名字
	private String leaderName;//领队名字
	private String createDate;//球队创建时间
	private String createBy;//创建人
	private String organization;//所属机构
	private String level;//球队等级（1省，2，3，4校）
	private String type;//球队类型：1.小学 、2.初中、3.高中、4.混合
	private String logo;//励志标语
	private String region;//地区
	
	public String getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(String teamNum) {
		this.teamNum = teamNum;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return organization+" 添加一个球队  "+teamName;
	}
	
}
