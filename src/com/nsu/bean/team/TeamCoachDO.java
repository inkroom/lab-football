package com.nsu.bean.team;

import com.nsu.controller.team.TeamUtil;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.V;

/**
 * 
* @ClassName: 教练员信息
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月21日 上午3:18:10 
*
 */
public class TeamCoachDO {
	
	private String COACH_NAME;//教练员姓名
	private String COACH_SEX;//教练员性别
	private String COACH_AGE;//教练员年龄
	private String COACH_STATUS;//教练员状态
	private String ID_CARD;//身份证
	private String COACH_PHOTO;//教练照片
	private String COACH_ID;//教练编号
	private String CS_PRCVINCE_WIN;//省级获胜
	private String CS_CITY_WIN;//市级获胜
	private String CS_COUNTRY_WIN;//县级获胜
	private String CS_SCHOOL_WIN;//学校获胜
	private String CS_PRCVINCE_LOSE;//省级失败
	private String CS_CITY_LOSE;//市级失败
	private String CS_COUNTRY_LOSE;//县级失败
	private String CS_SCHOOL_LOSS;//市级失败	
	private String CS_OTHER_WIN;//其他获胜
	private String CS_OTHER_LOSS;//其他失败
	private String COACH_JOB;//工作单位
	private String COACH_EXP;//工作精力
	private String PRCVINCE_GRADE;//省级得分
	private String CITY_GRADE;//市级得分
	private String COUNTRY_GRADE;//县级得分
	private String SCHOOL_GRADE;//校级得分
	private String OTHER_GRADE;//其他得分
	private String COACH_TEAM_STATSU;//审核状态
	private String TIME;//审核时间
	
	
	public String getCOACH_NAME() {
		return COACH_NAME;
	}
	public void setCOACH_NAME(String cOACH_NAME) {
		COACH_NAME = cOACH_NAME;
	}
	public String getCOACH_SEX() {
		return COACH_SEX;
	}
	public void setCOACH_SEX(String cOACH_SEX) {
		COACH_SEX = cOACH_SEX;
	}
	public String getCOACH_AGE() {
		return V.checkEmpty(ID_CARD)==true?"":TeamUtil.IdNOToAge(InfoProtUtil.xorInfo(ID_CARD))+"";
	}
	public void setCOACH_AGE(String cOACH_AGE) {
		COACH_AGE = cOACH_AGE;
	}
	public String getCOACH_STATUS() {
        return COACH_STATUS==null?"":
        	COACH_STATUS.equals("0")?"待审核":
        		COACH_STATUS.equals("1")?"通过":"未通过";
	}
	public void setCOACH_STATUS(String cOACH_STATUS) {
		COACH_STATUS = cOACH_STATUS;
	}
	public String getID_CARD() {
		return ID_CARD==null?"":ID_CARD;
	}
	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}
	public String getCOACH_PHOTO() {
		return COACH_PHOTO;
	}
	public void setCOACH_PHOTO(String cOACH_PHOTO) {
		COACH_PHOTO = cOACH_PHOTO;
	}
	public String getCOACH_ID() {
		return COACH_ID;
	}
	public void setCOACH_ID(String cOACH_ID) {
		COACH_ID = cOACH_ID;
	}
	public String getCS_PRCVINCE_WIN() {
		return CS_PRCVINCE_WIN==null?"0":CS_PRCVINCE_WIN;
	}
	public void setCS_PRCVINCE_WIN(String cS_PRCVINCE_WIN) {
		CS_PRCVINCE_WIN = cS_PRCVINCE_WIN;
	}
	public String getCS_CITY_WIN() {
		return CS_CITY_WIN==null?"0":CS_CITY_WIN;
	}
	public void setCS_CITY_WIN(String cS_CITY_WIN) {
		CS_CITY_WIN = cS_CITY_WIN;
	}
	public String getCS_COUNTRY_WIN() {
		return CS_COUNTRY_WIN==null?"0":CS_COUNTRY_WIN;
	}
	public void setCS_COUNTRY_WIN(String cS_COUNTRY_WIN) {
		CS_COUNTRY_WIN = cS_COUNTRY_WIN;
	}
	public String getCS_SCHOOL_WIN() {
		return CS_SCHOOL_WIN==null?"0":CS_SCHOOL_WIN;
	}
	public void setCS_SCHOOL_WIN(String cS_SCHOOL_WIN) {
		CS_SCHOOL_WIN = cS_SCHOOL_WIN;
	}
	public String getCS_PRCVINCE_LOSE() {
		return CS_PRCVINCE_LOSE==null?"0":CS_PRCVINCE_LOSE;
	}
	public void setCS_PRCVINCE_LOSE(String cS_PRCVINCE_LOSE) {
		CS_PRCVINCE_LOSE = cS_PRCVINCE_LOSE;
	}
	public String getCS_CITY_LOSE() {
		return CS_CITY_LOSE==null?"0":CS_CITY_LOSE;
	}
	public void setCS_CITY_LOSE(String cS_CITY_LOSE) {
		CS_CITY_LOSE = cS_CITY_LOSE;
	}
	public String getCS_COUNTRY_LOSE() {
		return CS_COUNTRY_LOSE==null?"0":CS_COUNTRY_LOSE;
	}
	public void setCS_COUNTRY_LOSE(String cS_COUNTRY_LOSE) {
		CS_COUNTRY_LOSE = cS_COUNTRY_LOSE;
	}
	public String getCS_SCHOOL_LOSS() {
		return CS_SCHOOL_LOSS==null?"0":CS_SCHOOL_LOSS;
	}
	public void setCS_SCHOOL_LOSS(String cS_SCHOOL_LOSS) {
		CS_SCHOOL_LOSS = cS_SCHOOL_LOSS;
	}
	public String getCOACH_JOB() {
		return COACH_JOB==null?"无":COACH_JOB;
	}
	public void setCOACH_JOB(String cOACH_JOB) {
		COACH_JOB = cOACH_JOB;
	}
	public String getCOACH_EXP() {
		return COACH_EXP==null?"无":COACH_EXP;
	}
	public void setCOACH_EXP(String cOACH_EXP) {
		COACH_EXP = cOACH_EXP;
	}
	public String getCOACH_TEAM_STATSU() {
		return COACH_TEAM_STATSU;
	}
	public void setCOACH_TEAM_STATSU(String cOACH_TEAM_STATSU) {
		COACH_TEAM_STATSU = cOACH_TEAM_STATSU;
	}
	public String getPRCVINCE_GRADE() {
		return PRCVINCE_GRADE;
	}
	public void setPRCVINCE_GRADE(String pRCVINCE_GRADE) {
		PRCVINCE_GRADE = pRCVINCE_GRADE;
	}
	public String getCITY_GRADE() {
		return CITY_GRADE;
	}
	public void setCITY_GRADE(String cITY_GRADE) {
		CITY_GRADE = cITY_GRADE;
	}
	public String getCOUNTRY_GRADE() {
		return COUNTRY_GRADE;
	}
	public void setCOUNTRY_GRADE(String cOUNTRY_GRADE) {
		COUNTRY_GRADE = cOUNTRY_GRADE;
	}
	public String getSCHOOL_GRADE() {
		return SCHOOL_GRADE;
	}
	public void setSCHOOL_GRADE(String sCHOOL_GRADE) {
		SCHOOL_GRADE = sCHOOL_GRADE;
	}
	public String getCS_OTHER_WIN() {
		return CS_OTHER_WIN==null?"0":CS_OTHER_WIN;
	}
	public void setCS_OTHER_WIN(String cS_OTHER_WIN) {
		CS_OTHER_WIN = cS_OTHER_WIN;
	}
	public String getCS_OTHER_LOSS() {
		return CS_OTHER_LOSS==null?"0":CS_OTHER_LOSS;
	}
	public void setCS_OTHER_LOSS(String cS_OTHER_LOSS) {
		CS_OTHER_LOSS = cS_OTHER_LOSS;
	}
	public String getOTHER_GRADE() {
		return OTHER_GRADE;
	}
	public void setOTHER_GRADE(String oTHER_GRADE) {
		OTHER_GRADE = oTHER_GRADE;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	@Override
	public String toString() {
		return "TeamCoachDO [COACH_NAME=" + COACH_NAME + ", COACH_SEX=" + COACH_SEX + ", COACH_AGE=" + COACH_AGE
				+ ", COACH_STATUS=" + COACH_STATUS + ", ID_CARD=" + ID_CARD + ", COACH_PHOTO=" + COACH_PHOTO
				+ ", COACH_ID=" + COACH_ID + ", CS_PRCVINCE_WIN=" + CS_PRCVINCE_WIN + ", CS_CITY_WIN=" + CS_CITY_WIN
				+ ", CS_COUNTRY_WIN=" + CS_COUNTRY_WIN + ", CS_SCHOOL_WIN=" + CS_SCHOOL_WIN + ", CS_PRCVINCE_LOSE="
				+ CS_PRCVINCE_LOSE + ", CS_CITY_LOSE=" + CS_CITY_LOSE + ", CS_COUNTRY_LOSE=" + CS_COUNTRY_LOSE
				+ ", CS_SCHOOL_LOSS=" + CS_SCHOOL_LOSS + ", CS_OTHER_WIN=" + CS_OTHER_WIN + ", CS_OTHER_LOSS="
				+ CS_OTHER_LOSS + ", COACH_JOB=" + COACH_JOB + ", COACH_EXP=" + COACH_EXP + ", PRCVINCE_GRADE="
				+ PRCVINCE_GRADE + ", CITY_GRADE=" + CITY_GRADE + ", COUNTRY_GRADE=" + COUNTRY_GRADE + ", SCHOOL_GRADE="
				+ SCHOOL_GRADE + ", OTHER_GRADE=" + OTHER_GRADE + ", COACH_TEAM_STATSU=" + COACH_TEAM_STATSU + ", TIME="
				+ TIME + "]";
	}




}
