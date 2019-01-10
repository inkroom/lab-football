package com.nsu.bean.publicity;

public class PlayerPubBean {

	private String id;//球员ID
	private String name;//球员名
	private String sex;//球员性别
	private String age;//球员年龄
	private String score;//积分榜
	private String matches;//比赛场次
	private String height;//球员身高
	private String weight;//球员体重
	private String studentID;//球员学号
	private String gold;//金牌数
	private String sliver;//银牌数
	private String brezon;//铜牌数
	private String school;//就读学校
	private String photo;//个人照片
	private String createTime;
	private String position;//位置
	private String classHonor;//班级荣誉
	private String schoolHonor;//校级荣誉
	private String countryHonor;//市级荣誉
	private String cityHonor;//省级荣誉
	private String team;
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getClassHonor() {
		return classHonor;
	}
	public void setClassHonor(String classHonor) {
		this.classHonor = classHonor;
	}
	public String getSchoolHonor() {
		return schoolHonor;
	}
	public void setSchoolHonor(String schoolHonor) {
		this.schoolHonor = schoolHonor;
	}
	public String getCountryHonor() {
		return countryHonor;
	}
	public void setCountryHonor(String countryHonor) {
		this.countryHonor = countryHonor;
	}
	public String getCityHonor() {
		return cityHonor;
	}
	public void setCityHonor(String cityHonor) {
		this.cityHonor = cityHonor;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getMatches() {
		return matches;
	}
	public void setMatches(String matches) {
		this.matches = matches;
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
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getSliver() {
		return sliver;
	}
	public void setSliver(String sliver) {
		this.sliver = sliver;
	}
	public String getBrezon() {
		return brezon;
	}
	public void setBrezon(String brezon) {
		this.brezon = brezon;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return name+","+sex+"  申请成为球员员";
	}
}
