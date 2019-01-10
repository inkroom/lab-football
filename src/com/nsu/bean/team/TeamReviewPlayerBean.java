package com.nsu.bean.team;

import java.io.Serializable;

import com.nsu.staticvar.TeamVar;
import com.nsu.util.InfoProUtil;
import com.nsu.util.StringHelper;
import com.nsu.util.V;

/**
 * 球员所有基本信息bean
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-20 18:18:38
 */
public class TeamReviewPlayerBean implements Serializable{

	// 球员ID
	private String pid;
	// account ID
	private String aid;
	// 身份证号
	private String idCard;
	// 球员名
	private String playerName;
	// 身高
	private String playerHight;
	// 体重
	private String playerWeight;
	// 性别
	private String playerSex;
	//球员生日
	private String playerBirthday;
	//球员年龄
	private String age;
	// 所在学校
	private String school;
	//球员学号
	private String playerStuID;
	//比赛场次
	private String playerMatchPlay;
	//平均胜率
	private String avgWin;
	// 擅长位置
	private String position;
	//胜利场次
	private String winSession;
	//失败场次
	private String loseSesion;
	// 积分榜
	private String integral;
	//总积分
	private int totalIntegral;
	// 金牌
	private String goldModalCount;
	// 银牌
	private String sliverModalCount;
	// 铜牌
	private String brezonModelCount;
	// 头像
	private String photo;
	// 获奖情况
		// 班级 
	private String classHonors;
		// 校级
	private String schoolHonrs;
		// 县级
	private String countryHonrs;
		// 市级
	private String cityHours;
		// 省级
	private String provenceHours;
		// 其他
	private String otherHours;
	//比赛输球
	private String lose;
	//比赛赢球
	private String win;
	//射门次数
	private String shootCount;
	// 进球次数
	private String goalCount;
	//犯规次数
	private String foalCount;
	//球衣号
	private String ptNum;
	
	public String getProvenceHours() {
		return provenceHours;
	}
	public void setProvenceHours(String provenceHours) {
		this.provenceHours = provenceHours;
	}
	public String getOtherHours() {
		return otherHours;
	}
	public void setOtherHours(String otherHours) {
		this.otherHours = otherHours;
	}
	public String getPtNum() {
		return ptNum;
	}
	public void setPtNum(String ptNum) {
		this.ptNum = ptNum;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	public String getPlayerHight() {
		return playerHight;
	}
	public void setPlayerHight(String playerHight) {
		this.playerHight = playerHight;
	}
	public String getPlayerWeight() {
		return playerWeight;
	}
	public void setPlayerWeight(String playerWeight) {
		this.playerWeight = playerWeight;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGoldModalCount() {
		return goldModalCount==null?"0":goldModalCount;
	}
	public void setGoldModalCount(String goldModalCount) {
		this.goldModalCount = goldModalCount;
	}
	public String getSliverModalCount() {
		return sliverModalCount==null?"0":sliverModalCount;
	}
	public void setSliverModalCount(String sliverModalCount) {
		this.sliverModalCount = sliverModalCount;
	}
	public String getBrezonModelCount() {
		return brezonModelCount==null?"0":brezonModelCount;
	}
	public void setBrezonModelCount(String brezonModelCount) {
		this.brezonModelCount = brezonModelCount;
	}
	public String getSchool() {
		return V.checkEmpty(school)==true?"无":school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getClassHonors() {
		return classHonors==null?"无":classHonors;
	}
	public void setClassHonors(String classHonors) {
		this.classHonors = classHonors;
	}
	public String getSchoolHonrs() {
		return schoolHonrs==null?"无":schoolHonrs;
	}
	public void setSchoolHonrs(String schoolHonrs) {
		this.schoolHonrs = schoolHonrs;
	}
	public String getCountryHonrs() {
		return countryHonrs==null?"无":countryHonrs;
	}
	public void setCountryHonrs(String countryHonrs) {
		this.countryHonrs = countryHonrs;
	}
	public String getCityHours() {
		return cityHours==null?"无":cityHours;
	}
	public void setCityHours(String cityHours) {
		this.cityHours = cityHours;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPlayerSex() {
		return playerSex;
	}
	public void setPlayerSex(String playerSex) {
		this.playerSex = playerSex;
	}
	public String getPlayerBirthday() {
		return playerBirthday;
	}
	public void setPlayerBirthday(String playerBirthday) {
		this.playerBirthday = playerBirthday;
	}
	public String getAge() {
		return V.checkEmpty(idCard) ?"": StringHelper.IDCardNoToAge(InfoProUtil.xorInfo(idCard))+"";
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPlayerStuID() {
		return V.checkEmpty(playerStuID) ?"无":playerStuID;
	}
	public void setPlayerStuID(String playerStuID) {
		this.playerStuID = playerStuID;
	}
	public String getPlayerMatchPlay() {
		return playerMatchPlay;
	}
	public void setPlayerMatchPlay(String playerMatchPlay) {
		this.playerMatchPlay = playerMatchPlay;
	}
	public String getAvgWin() {
		return avgWin;
	}
	public void setAvgWin(String avgWin) {
		this.avgWin = avgWin;
	}
	public String getWinSession() {
		return winSession;
	}
	public void setWinSession(String winSession) {
		this.winSession = winSession;
	}
	public String getLoseSesion() {
		return loseSesion;
	}
	public void setLoseSesion(String loseSesion) {
		this.loseSesion = loseSesion;
	}
	public String getLose() {
		return lose;
	}
	public void setLose(String lose) {
		this.lose = lose;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getShootCount() {
		return shootCount;
	}
	public void setShootCount(String shootCount) {
		this.shootCount = shootCount;
	}
	public String getGoalCount() {
		return goalCount;
	}
	public void setGoalCount(String goalCount) {
		this.goalCount = goalCount;
	}
	public String getFoalCount() {
		return foalCount;
	}
	public void setFoalCount(String foalCount) {
		this.foalCount = foalCount;
	}
	public int getTotalIntegral() {
		return totalIntegral;
	}
	public void setTotalIntegral(int totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	@Override
	public String toString() {
		return "TeamReviewPlayerBean [pid=" + pid + ", aid=" + aid + ", idCard=" + idCard + ", playerName=" + playerName
				+ ", playerHight=" + playerHight + ", playerWeight=" + playerWeight + ", playerSex=" + playerSex
				+ ", playerBirthday=" + playerBirthday + ", age=" + age + ", school=" + school + ", playerStuID="
				+ playerStuID + ", playerMatchPlay=" + playerMatchPlay + ", avgWin=" + avgWin + ", position=" + position
				+ ", winSession=" + winSession + ", loseSesion=" + loseSesion + ", integral=" + integral
				+ ", totalIntegral=" + totalIntegral + ", goldModalCount=" + goldModalCount + ", sliverModalCount="
				+ sliverModalCount + ", brezonModelCount=" + brezonModelCount + ", photo=" + photo + ", classHonors="
				+ classHonors + ", schoolHonrs=" + schoolHonrs + ", countryHonrs=" + countryHonrs + ", cityHours="
				+ cityHours + ", lose=" + lose + ", win=" + win + ", shootCount=" + shootCount + ", goalCount="
				+ goalCount + ", foalCount=" + foalCount + "]";
	}
	
	
	
}
