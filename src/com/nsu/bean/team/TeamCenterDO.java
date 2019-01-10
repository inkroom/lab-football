package com.nsu.bean.team;

import com.nsu.controller.team.TeamUtil;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.V;

public class TeamCenterDO {

//	//***********************从TEAM表,TEAM_SCORE表, MID_COACH_TEAM表,COACH表和ACCOUNT表查询球队名称**********************
//	private String TEAM_NAME;//球队名称
//	private String TEA_ID;//球队编号
//	private String TEAM_LEADER;//领队
//	private String TEAM_TYPE;//球队类型
//	private String TEAM_ISPIRATION_LOGO;//球队励志标语
//	private String TEAM_PHONE_NUM;//领队手机号
//	private String TEAM_SCORE;//球队积分  由胜数*3+负场*1(计算)
//	private String MATCHTIMES;//比赛场次 由胜负场数相加(计算)
//	private String AVGWIN;//平均胜率  由总场数除以胜利场数(计算)
//	private String TS_SCHOOL_VICTOR;//校级获胜球数 
//	private String TS_COUNTRY_VICTOR;//县级获胜球数  
//	private String TS_CITY_VICTOR;//市级获胜球数 
//	private String TS_PROVINCE_VICTOR;//省级获胜球数
//	private String TS_SCHOOL_LOSE;//校级输球数
//	private String TS_COUNTRY_LOSE;//县级输球数 
//	private String TS_CITY_LOSE;//市级输球数 
//	private String TS_PROVINCE_LOSE;//省级输球数  
//	private String COACH_NAME;//A_NAME教练员名字
//	private String TEAM_AFFILIATION;//所属机构


	//**********************关联MID_PLAYER_TEAM表,PLAYER表和ACCOUNT表查询
	private String pID;//队员ID
	private String P_BIRTHDAY;//球员生日
	private String PLAYER_NAME;//队员
	private String pAge;//年龄
	private String idCard;//身份证
	private String P_POSITION;//擅长位置
	private String P_GRADE_TABLE_ID;//个人积分
	private String PLAYER_PHONE;//球员电话
	private String pSex;//性别
	private String COACH_NAME;//教练员姓名
	private String COACH_TEAM_STATSU;//教练状态
	private String TEAM_PLAYER_STATUS;//学生状态
	private String COACH_PHOTO;//教练员照片
	private String TEAM_ID;
	private String A_ID;
	private String P_ID;
	private String COACH_ID;
	private String pSchool;//球员就读学校
	private String reviewPlayerStatusText;//审核状态提示文本
	//是否加入赛程 0：没有；1：加入了
	private int isAddMatch;
	private String btnValue;//button value值
	private String rID;//赛程ID
	private String ORG_ID;//机构账号
	private String ORG_NAME;//机构名字
	private String TEAM_PLAYER_ID;//球员球队中间表
	private String tpTime;//球员申请加入时间
	private String TEAM_NAME;//球队名称
	
	public String getTpTime() {
		return tpTime;
	}
	public void setTpTime(String tpTime) {
		this.tpTime = tpTime;
	}
	public String getPLAYER_NAME() {
		return PLAYER_NAME==null?"":PLAYER_NAME;
	}
	public void setPLAYER_NAME(String pLAYER_NAME) {
		PLAYER_NAME = pLAYER_NAME;
	}
	
	public String getpAge() {
		return V.checkEmpty(idCard)==true?"":TeamUtil.IdNOToAge(InfoProtUtil.xorInfo(idCard))+"";
	}
	public void setpAge(String pAge) {
		this.pAge = pAge;
	}
	public String getP_POSITION() {
		return P_POSITION==null?"":P_POSITION;
	}
	public void setP_POSITION(String p_POSITION) {
		P_POSITION = p_POSITION;
	}
	public String getP_GRADE_TABLE_ID() {
		return P_GRADE_TABLE_ID==null?"0":P_GRADE_TABLE_ID;
	}
	public void setP_GRADE_TABLE_ID(String p_GRADE_TABLE_ID) {
		P_GRADE_TABLE_ID = p_GRADE_TABLE_ID;
	}
	public String getPLAYER_PHONE() {
		return V.checkEmpty(PLAYER_PHONE)==true?"":InfoProtUtil.xorInfo(PLAYER_PHONE);
	}
	public void setPLAYER_PHONE(String pLAYER_PHONE) {
		PLAYER_PHONE = pLAYER_PHONE;
	}
	public String getpSex() {
		return pSex;
	}
	public void setpSex(String pSex) {
		this.pSex = pSex;
	}
	public String getTEAM_ID() {
		return TEAM_ID==null?"":TEAM_ID;
	}
	public void setTEAM_ID(String tEAM_ID) {
		TEAM_ID = tEAM_ID;
	}
	
	public String getA_ID() {
		return A_ID;
	}
	public void setA_ID(String a_ID) {
		A_ID = a_ID;
	}
	public String getCOACH_NAME() {
		return COACH_NAME==null?"":COACH_NAME;
	}
	public void setCOACH_NAME(String cOACH_NAME) {
		COACH_NAME = cOACH_NAME;
	}
	public int getIsAddMatch() {
		return isAddMatch;
	}
	public void setIsAddMatch(int isAddMatch) {
		this.isAddMatch = isAddMatch;
	}
	public String getpID() {
		return pID;
	}
	public void setpID(String pID) {
		this.pID = pID;
	}
	public String getCOACH_TEAM_STATSU() {
		return COACH_TEAM_STATSU==null?"":COACH_TEAM_STATSU;
	}
	public void setCOACH_TEAM_STATSU(String cOACH_TEAM_STATSU) {
		COACH_TEAM_STATSU = cOACH_TEAM_STATSU;
	}
	public String getTEAM_PLAYER_STATUS() {
		return TEAM_PLAYER_STATUS==null?"":TEAM_PLAYER_STATUS;
	}
	public void setTEAM_PLAYER_STATUS(String tEAM_PLAYER_STATUS) {
		TEAM_PLAYER_STATUS = tEAM_PLAYER_STATUS;
	}
	public String getBtnValue() {
		return isAddMatch==0?"添加":"移除";
	}
	public void setBtnValue(String btnValue) {
		this.btnValue = btnValue;
	}
	public String getrID() {
		return rID==null?"":rID;
	}
	public void setrID(String rID) {
		this.rID = rID;
	}
	public String getIdCard() {
		return idCard==null?"":idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getP_BIRTHDAY() {
		return P_BIRTHDAY==null?"":P_BIRTHDAY;
	}
	public void setP_BIRTHDAY(String p_BIRTHDAY) {
		P_BIRTHDAY = p_BIRTHDAY;
	}
	public String getpSchool() {
		return pSchool;
	}
	public void setpSchool(String pSchool) {
		this.pSchool = pSchool;
	}
	public String getReviewPlayerStatusText() {
		return TEAM_PLAYER_STATUS==null?"":
				    TEAM_PLAYER_STATUS.equals("0")?"待审核":
						 TEAM_PLAYER_STATUS.equals("1")?"通过":"未通过";
	}
	public void setReviewPlayerStatusText(String reviewPlayerStatusText) {
		this.reviewPlayerStatusText = reviewPlayerStatusText;
	}
	public String getP_ID() {
		return P_ID;
	}
	public void setP_ID(String p_ID) {
		P_ID = p_ID;
	}
	public String getCOACH_ID() {
		return COACH_ID;
	}
	public void setCOACH_ID(String cOACH_ID) {
		COACH_ID = cOACH_ID;
	}
	public String getCOACH_PHOTO() {
		return COACH_PHOTO;
	}
	public void setCOACH_PHOTO(String cOACH_PHOTO) {
		COACH_PHOTO = cOACH_PHOTO;
	}
	public String getORG_ID() {
		return ORG_ID==null?"":ORG_ID;
	}
	public void setORG_ID(String oRG_ID) {
		ORG_ID = oRG_ID;
	}
	public String getORG_NAME() {
		return ORG_NAME==null?"无":ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getTEAM_PLAYER_ID() {
		return TEAM_PLAYER_ID;
	}
	public void setTEAM_PLAYER_ID(String tEAM_PLAYER_ID) {
		TEAM_PLAYER_ID = tEAM_PLAYER_ID;
	}
	public String getTEAM_NAME() {
		return TEAM_NAME;
	}
	public void setTEAM_NAME(String tEAM_NAME) {
		TEAM_NAME = tEAM_NAME;
	}
	@Override
	public String toString() {
		return "TeamCenterDO [pID=" + pID + ", P_BIRTHDAY=" + P_BIRTHDAY + ", PLAYER_NAME=" + PLAYER_NAME + ", pAge="
				+ pAge + ", idCard=" + idCard + ", P_POSITION=" + P_POSITION + ", P_GRADE_TABLE_ID=" + P_GRADE_TABLE_ID
				+ ", PLAYER_PHONE=" + PLAYER_PHONE + ", pSex=" + pSex + ", COACH_NAME=" + COACH_NAME
				+ ", COACH_TEAM_STATSU=" + COACH_TEAM_STATSU + ", TEAM_PLAYER_STATUS=" + TEAM_PLAYER_STATUS
				+ ", COACH_PHOTO=" + COACH_PHOTO + ", TEAM_ID=" + TEAM_ID + ", A_ID=" + A_ID + ", P_ID=" + P_ID
				+ ", COACH_ID=" + COACH_ID + ", pSchool=" + pSchool + ", reviewPlayerStatusText="
				+ reviewPlayerStatusText + ", isAddMatch=" + isAddMatch + ", btnValue=" + btnValue + ", rID=" + rID
				+ ", ORG_ID=" + ORG_ID + ", ORG_NAME=" + ORG_NAME + ", TEAM_PLAYER_ID=" + TEAM_PLAYER_ID + ", tpTime="
				+ tpTime + ", TEAM_NAME=" + TEAM_NAME + "]";
	}

}