package com.nsu.bean.team;

public class TeamRegisterDO {

	private String A_ID;//account表主键ID
	private String user;//身份证号
	private String passwd;//密码
	private String passwd2;//确认密码
	private String Phone;//电话号码
	private String phoneCheck;//验证码
	public String result = "";//验证结果
	private String teamNum;//球队编号
	private String TEAM_ID;//球队ID
	
	
	public String getTEAM_ID() {
		return TEAM_ID;
	}
	public void setTEAM_ID(String tEAM_ID) {
		TEAM_ID = tEAM_ID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getUser() {
		return user==null?"":user.trim();
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	public String getPhone() {
		return Phone==null?"":Phone.trim();
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPhoneCheck() {
		return phoneCheck==null?"":phoneCheck.trim();
	}
	public void setPhoneCheck(String phoneCheck) {
		this.phoneCheck = phoneCheck;
	}
	public String getA_ID() {
		return A_ID;
	}
	public void setA_ID(String a_ID) {
		A_ID = a_ID;
	}
	public String getTeamNum() {
		return teamNum==null?"":teamNum.trim();
	}
	public void setTeamNum(String teamNum) {
		this.teamNum = teamNum;
	}
	@Override
	public String toString() {
		return "TeamRegisterDO [user= " + user + ", passwd=" + passwd + ", passwd2=" + passwd2 + ", Phone=" + Phone
				+ ", phoneCheck=" + phoneCheck + ", result=" + result + "]";
	}
	
}
