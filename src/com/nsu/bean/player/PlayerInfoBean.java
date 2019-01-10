package com.nsu.bean.player;

import java.io.Serializable;
import java.util.Arrays;

public class PlayerInfoBean implements Serializable {
	private Integer STU_ID;
	private Integer A_ID;
	private String A_NAME;
	private String P_SEX;
	private float P_PLAYER_HEIGHT;
	private float P_PLAYER_WEIGHT;
	private String P_SCHOOL;
	private String[] P_POSITION;
	private String P_GRADE_TABLE_ID;
	private String P_PEROSONAL_PHOTO;
	private String A_EMAIL;
	private String A_EMAIL_CHECK_CODE;
	private String positionInfo;
	public String getPositionInfo() {
		return positionInfo;
	}
	public void setPositionInfo(String positionInfo) {
		this.positionInfo = positionInfo;
	}
	public String getA_EMAIL_CHECK_CODE() {
		return A_EMAIL_CHECK_CODE;
	}
	public void setA_EMAIL_CHECK_CODE(String a_EMAIL_CHECK_CODE) {
		A_EMAIL_CHECK_CODE = a_EMAIL_CHECK_CODE;
	}
	public String getA_EMAIL() {
		return A_EMAIL;
	}public void setA_EMAIL(String a_EMAIL) {
		A_EMAIL = a_EMAIL;
	}
	public Integer getSTU_ID() {
		return STU_ID;
	}
	public void setSTU_ID(Integer sTU_ID) {
		STU_ID = sTU_ID;
	}
	public Integer getA_ID() {
		return A_ID;
	}
	public void setA_ID(Integer a_ID) {
		A_ID = a_ID;
	}
	public String getA_NAME() {
		return A_NAME;
	}
	public void setA_NAME(String a_NAME) {
		A_NAME = a_NAME;
	}
	public String getP_SEX() {
		return P_SEX;
	}
	public void setP_SEX(String p_SEX) {
		P_SEX = p_SEX;
	}
	public float getP_PLAYER_HEIGHT() {
		return P_PLAYER_HEIGHT;
	}
	public void setP_PLAYER_HEIGHT(float p_PLAYER_HEIGHT) {
		P_PLAYER_HEIGHT = p_PLAYER_HEIGHT;
	}
	public float getP_PLAYER_WEIGHT() {
		return P_PLAYER_WEIGHT;
	}
	public void setP_PLAYER_WEIGHT(float p_PLAYER_WEIGHT) {
		P_PLAYER_WEIGHT = p_PLAYER_WEIGHT;
	}
	public String getP_SCHOOL() {
		return P_SCHOOL;
	}
	public void setP_SCHOOL(String p_SCHOOL) {
		P_SCHOOL = p_SCHOOL;
	}
	public String[] getP_POSITION() {
		return P_POSITION;
	}
	public void setP_POSITION(String[] p_POSITION) {
		P_POSITION = p_POSITION;
	}
	public String getP_GRADE_TABLE_ID() {
		return P_GRADE_TABLE_ID;
	}
	public void setP_GRADE_TABLE_ID(String p_GRADE_TABLE_ID) {
		P_GRADE_TABLE_ID = p_GRADE_TABLE_ID;
	}
	public String getP_PEROSONAL_PHOTO() {
		return P_PEROSONAL_PHOTO;
	}
	public void setP_PEROSONAL_PHOTO(String p_PEROSONAL_PHOTO) {
		P_PEROSONAL_PHOTO = p_PEROSONAL_PHOTO;
	}

	@Override
	public String toString() {
		return "PlayerInfoBean{" +
				"STU_ID=" + STU_ID +
				", A_ID=" + A_ID +
				", A_NAME='" + A_NAME + '\'' +
				", P_SEX='" + P_SEX + '\'' +
				", P_PLAYER_HEIGHT=" + P_PLAYER_HEIGHT +
				", P_PLAYER_WEIGHT=" + P_PLAYER_WEIGHT +
				", P_SCHOOL='" + P_SCHOOL + '\'' +
				", P_POSITION=" + Arrays.toString(P_POSITION) +
				", P_GRADE_TABLE_ID='" + P_GRADE_TABLE_ID + '\'' +
				", P_PEROSONAL_PHOTO='" + P_PEROSONAL_PHOTO + '\'' +
				", A_EMAIL='" + A_EMAIL + '\'' +
				", A_EMAIL_CHECK_CODE='" + A_EMAIL_CHECK_CODE + '\'' +
				", positionInfo='" + positionInfo + '\'' +
				'}';
	}
}
