package com.nsu.bean.coach;

public class CoachInfoBean {
	private String coachID;
	
	private String coachName;
	
	private String coachBirth;
	
	private String coachHeight;
	
	private String coachWeight;
	
	private String coachSex;
	
	private String coachEmail;
	
	private String coachExp;
	public String getCoachExp() {
		return coachExp;
	}

	public void setCoachExp(String coachExp) {
		this.coachExp = coachExp;
	}

	public String getCoachID() {
		return coachID;
	}

	public void setCoachID(String coachID) {
		this.coachID = coachID;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getCoachBirth() {
		return coachBirth;
	}

	public void setCoachBirth(String coachBirth) {
		this.coachBirth = coachBirth;
	}

	public String getCoachHeight() {
		return coachHeight;
	}

	public void setCoachHeight(String coachHeight) {
		this.coachHeight = coachHeight;
	}

	public String getCoachWeight() {
		return coachWeight;
	}

	public void setCoachWeight(String coachWeight) {
		this.coachWeight = coachWeight;
	}

	public String getCoachSex() {
		return coachSex;
	}

	public void setCoachSex(String coachSex) {
		this.coachSex = coachSex;
	}

	public String getCoachEmail() {
		return coachEmail;
	}

	public void setCoachEmail(String coachEmail) {
		this.coachEmail = coachEmail;
	}

	@Override
	public String toString() {
		return "CoachInfoBean [coachID=" + coachID + ", coachName=" + coachName + ", coachBirth=" + coachBirth
				+ ", coachHeight=" + coachHeight + ", coachWeight=" + coachWeight + ", coachSex=" + coachSex
				+ ", coachEmail=" + coachEmail + "]";
	}
	
	
	
	
	
}
