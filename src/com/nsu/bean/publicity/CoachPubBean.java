package com.nsu.bean.publicity;

import java.util.List;

public class CoachPubBean {

	private String id;//教练ID
	private String name;//教练名
	private String sex;//教练性别
	private String birthday;//教练出生日期
	private String height;//教练身高
	private String weight;//教练员体重
	private String healthy;//教练员健康情况
	private String job;//教练工作单位
	private String exp;//工作经历
	private String photo;//教练员照片
	private String team;
	private String createTime;
	//积分
	private String pWin;
	private String pAll;
	private String pLose;
	private String cityWin;
	private String cityAll;
	private String cityLose;
	private String counWin;
	private String counAll;
	private String counLose;
	private String scWin;
	private String scAll;
	private String scLose;
	private String otherWin;
	private String otherAll;
	private String otherLose;
	
	public String getOtherWin() {
		return otherWin;
	}
	public void setOtherWin(String otherWin) {
		this.otherWin = otherWin;
	}
	public String getOtherAll() {
		return otherAll;
	}
	public void setOtherAll(String otherAll) {
		this.otherAll = otherAll;
	}
	public String getOtherLose() {
		return otherLose;
	}
	public void setOtherLose(String otherLose) {
		this.otherLose = otherLose;
	}
	public String getpWin() {
		return pWin;
	}
	public void setpWin(String pWin) {
		this.pWin = pWin;
	}
	public String getpAll() {
		return pAll;
	}
	public void setpAll(String pAll) {
		this.pAll = pAll;
	}
	public String getpLose() {
		return pLose;
	}
	public void setpLose(String pLose) {
		this.pLose = pLose;
	}
	public String getCityWin() {
		return cityWin;
	}
	public void setCityWin(String cityWin) {
		this.cityWin = cityWin;
	}
	public String getCityAll() {
		return cityAll;
	}
	public void setCityAll(String cityAll) {
		this.cityAll = cityAll;
	}
	public String getCityLose() {
		return cityLose;
	}
	public void setCityLose(String cityLose) {
		this.cityLose = cityLose;
	}
	public String getCounWin() {
		return counWin;
	}
	public void setCounWin(String counWin) {
		this.counWin = counWin;
	}
	public String getCounAll() {
		return counAll;
	}
	public void setCounAll(String counAll) {
		this.counAll = counAll;
	}
	public String getCounLose() {
		return counLose;
	}
	public void setCounLose(String counLose) {
		this.counLose = counLose;
	}
	public String getScWin() {
		return scWin;
	}
	public void setScWin(String scWin) {
		this.scWin = scWin;
	}
	public String getScAll() {
		return scAll;
	}
	public void setScAll(String scAll) {
		this.scAll = scAll;
	}
	public String getScLose() {
		return scLose;
	}
	public void setScLose(String scLose) {
		this.scLose = scLose;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHealthy() {
		return healthy;
	}
	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return name+","+sex+"  申请成为教练员";
	}
}
