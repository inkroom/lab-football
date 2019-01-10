package com.nsu.bean.team;

/**
 * 球队基本信息bean
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-12 14:22:33
 */
public class TeamBasicInfoBean {

	//	球队名
	private String teamName;
	//	励志标语
	private String teamInspirationalSlogan;
	//	队徽
	private String teamLogo;
	//	队旗
	private String teamFlag;
	// 队歌
	private String teamMusic;
	//	球队类型
	private String teamType;
	//	球队等级
	private String teamRank;
	//	领队名字
	private String teamLeader;
	//	领队手机号
	private String teamLeaderPhone;
	//  当前登录账户
	private String account;
	// 当前账户的aID
	private String aID;
	// 球队ID
	private String teamID;
	// 教练员
	private String teamCoach;
	//球队球员数量
	private int teamPlayerNum;
	//球队创建时间
	private String teamCreateTime;
	//邮箱
	private String email;
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTeamCoach() {
		return teamCoach==null?"":teamCoach;
	}
	public void setTeamCoach(String teamCoach) {
		this.teamCoach = teamCoach;
	}
	public int getTeamPlayerNum() {
		return teamPlayerNum;
	}
	public void setTeamPlayerNum(int teamPlayerNum) {
		this.teamPlayerNum = teamPlayerNum;
	}
	public String getTeamCreateTime() {
		return teamCreateTime;
	}
	public void setTeamCreateTime(String teamCreateTime) {
		this.teamCreateTime = teamCreateTime;
	}
	public String getTeamID() {
		return teamID==null?"":teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getaID() {
		return aID;
	}
	public void setaID(String aID) {
		this.aID = aID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamInspirationalSlogan() {
		return teamInspirationalSlogan;
	}
	public void setTeamInspirationalSlogan(String teamInspirationalSlogan) {
		this.teamInspirationalSlogan = teamInspirationalSlogan;
	}
	public String getTeamLogo() {
		return teamLogo==null?"":teamLogo;
	}
	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}
	public String getTeamFlag() {
		return teamFlag==null?"":teamFlag;
	}
	public void setTeamFlag(String teamFlag) {
		this.teamFlag = teamFlag;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getTeamLeaderPhone() {
		return teamLeaderPhone==null?"":teamLeaderPhone;
	}
	public void setTeamLeaderPhone(String teamLeaderPhone) {
		this.teamLeaderPhone = teamLeaderPhone;
	}
	public String getTeamRank() {
		return teamRank;
	}
	public void setTeamRank(String teamRank) {
		this.teamRank = teamRank;
	}
	public String getTeamMusic() {
		return teamMusic;
	}
	public void setTeamMusic(String teamMusic) {
		this.teamMusic = teamMusic;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "TeamBasicInfoBean [teamName=" + teamName + ", teamInspirationalSlogan=" + teamInspirationalSlogan
				+ ", teamLogo=" + teamLogo + ", teamFlag=" + teamFlag + ", teamMusic=" + teamMusic + ", teamType="
				+ teamType + ", teamRank=" + teamRank + ", teamLeader=" + teamLeader + ", teamLeaderPhone="
				+ teamLeaderPhone + ", account=" + account + ", aID=" + aID + ", teamID=" + teamID + ", teamCoach="
				+ teamCoach + ", teamPlayerNum=" + teamPlayerNum + ", teamCreateTime=" + teamCreateTime + ", email="
				+ email + "]";
	}
	
	
}
