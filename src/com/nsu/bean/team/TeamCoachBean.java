package com.nsu.bean.team;
/**
 * 球队教练基本信息
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-13 18:24:10
 */
public class TeamCoachBean {

	//教练姓名
	private String coachName;
	//教练电话
	private String coachPhone;
	//教练照片
	private String coachPhoto;
	//教练表主键ID
	private String coachID;
	
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getCoachPhone() {
		return coachPhone;
	}
	public void setCoachPhone(String coachPhone) {
		this.coachPhone = coachPhone;
	}
	public String getCoachPhoto() {
		return coachPhoto;
	}
	public void setCoachPhoto(String coachPhoto) {
		this.coachPhoto = coachPhoto;
	}
	public String getCoachID() {
		return coachID;
	}
	public void setCoachID(String coachID) {
		this.coachID = coachID;
	}
	@Override
	public String toString() {
		return "TeamCoachBean [coachName=" + coachName + ", coachPhone=" + coachPhone + ", coachPhoto=" + coachPhoto
				+ ", coachID=" + coachID + "]";
	}

}
