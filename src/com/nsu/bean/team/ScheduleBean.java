package com.nsu.bean.team;

import com.nsu.util.TimeToolUtil;

/**
 * 赛程bean
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-18 10:09:10
 */
public class ScheduleBean {
	
	//赛程编号
	private String rID;
	//赛事编号
	private String comID;
	//设置主队就是当前球队
	//主队ID
	private String rHTeamID;
	//主队名
	private String rHTeamName;
	//主队队旗
	private String rHTeamFlag;
	//主队队徽
	private String rHTeamBadge;
		
	//客队ID
	private String rVTeamID;
	//客队名
	private String rVTeamName;
	//客队队旗
	private String rVTeamFlag;
	//客队队徽
	private String rVTeamBadge;
	
	//赛程时间
	private String rTime;
	//赛程开始时间
	private Object sTime;
	//赛程结束时间
	private Object eTime;
	
	//常规主队得分
	private String RE_WIN;
	//加时主队得分
	private String OV_WIN;
	//点球主队得分
	private String PE_WIN;
	
	//常规客队得分
	private String RE_FAILE;
	//加时客队得分
	private String OV_FAILE;
	//点球客队得分
	private String PE_FAILE;
	
	//主队得分
	private String HOME_GRADE;
	
	//客队得分
	private String VISITING_GRADE;
	
	//比赛名称
	private String COM_NAME;
	
	private String COM_ID;
	
	//比赛状态
	private String SE_STATUS;
	
	//是否可以安排上场球员  0：不可，1：可以
	private int planContinue;
	
	private String arrangedNum;
	
	public String getrID() {
		return rID;
	}
	public void setrID(String rID) {
		this.rID = rID;
	}
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getrHTeamID() {
		return rHTeamID;
	}
	public void setrHTeamID(String rHTeamID) {
		this.rHTeamID = rHTeamID;
	}
	public String getrVTeamID() {
		return rVTeamID;
	}
	public void setrVTeamID(String rVTeamID) {
		this.rVTeamID = rVTeamID;
	}
	public String getrTime() {
		return getsTime()+" -- "+geteTime();
	}
	public void setrTime(String rTime) {
		this.rTime = rTime;
	}
	public int getPlanContinue() {
		return  TimeToolUtil.nowTimeInterval(sTime)>0?1:0;
	}
	public void setPlanContinue(int planContinue) {
		this.planContinue = planContinue;
	}
	public String getrHTeamName() {
		return rHTeamName;
	}
	public void setrHTeamName(String rHTeamName) {
		this.rHTeamName = rHTeamName;
	}
	public String getrVTeamName() {
		return rVTeamName;
	}
	public void setrVTeamName(String rVTeamName) {
		this.rVTeamName = rVTeamName;
	}
	public String getrHTeamFlag() {
		return rHTeamFlag;
	}
	public void setrHTeamFlag(String rHTeamFlag) {
		this.rHTeamFlag = rHTeamFlag;
	}
	public String getrVTeamFlag() {
		return rVTeamFlag;
	}
	public void setrVTeamFlag(String rVTeamFlag) {
		this.rVTeamFlag = rVTeamFlag;
	}
	public Object getsTime() {
		return sTime==null?"":sTime;
	}
	public void setsTime(Object sTime) {
		this.sTime = sTime;
	}
	public Object geteTime() {
		return eTime==null?"":eTime;
	}
	public void seteTime(Object eTime) {
		this.eTime = eTime;
	}
	public String getRE_WIN() {
		return RE_WIN;
	}
	public void setRE_WIN(String rE_WIN) {
		RE_WIN = rE_WIN;
	}
	public String getOV_WIN() {
		return OV_WIN;
	}
	public void setOV_WIN(String oV_WIN) {
		OV_WIN = oV_WIN;
	}
	public String getPE_WIN() {
		return PE_WIN;
	}
	public void setPE_WIN(String pE_WIN) {
		PE_WIN = pE_WIN;
	}
	public String getRE_FAILE() {
		return RE_FAILE;
	}
	public void setRE_FAILE(String rE_FAILE) {
		RE_FAILE = rE_FAILE;
	}
	public String getOV_FAILE() {
		return OV_FAILE;
	}
	public void setOV_FAILE(String oV_FAILE) {
		OV_FAILE = oV_FAILE;
	}
	public String getPE_FAILE() {
		return PE_FAILE;
	}
	public void setPE_FAILE(String pE_FAILE) {
		PE_FAILE = pE_FAILE;
	}
	public String getHOME_GRADE() {
		return HOME_GRADE;
	}
	public void setHOME_GRADE(String hOME_GRADE) {
		HOME_GRADE = hOME_GRADE;
	}
	public String getVISITING_GRADE() {
		return VISITING_GRADE;
	}
	public void setVISITING_GRADE(String vISITING_GRADE) {
		VISITING_GRADE = vISITING_GRADE;
	}
	public String getCOM_NAME() {
		return COM_NAME;
	}
	public void setCOM_NAME(String cOM_NAME) {
		COM_NAME = cOM_NAME;
	}
	public String getCOM_ID() {
		return COM_ID;
	}
	public void setCOM_ID(String cOM_ID) {
		COM_ID = cOM_ID;
	}
	public String getArrangedNum() {
		return arrangedNum==null?"0":arrangedNum;
	}
	public void setArrangedNum(String arrangedNum) {
		this.arrangedNum = arrangedNum;
	}
	public String getrHTeamBadge() {
		return rHTeamBadge;
	}
	public void setrHTeamBadge(String rHTeamBadge) {
		this.rHTeamBadge = rHTeamBadge;
	}
	public String getrVTeamBadge() {
		return rVTeamBadge;
	}
	public void setrVTeamBadge(String rVTeamBadge) {
		this.rVTeamBadge = rVTeamBadge;
	}
	
	public String getSE_STATUS() {
		return SE_STATUS;
	}
	public void setSE_STATUS(String sE_STATUS) {
		SE_STATUS = sE_STATUS;
	}
	@Override
	public String toString() {
		return "ScheduleBean [rID=" + rID + ", comID=" + comID + ", rHTeamID=" + rHTeamID + ", rHTeamName=" + rHTeamName
				+ ", rHTeamFlag=" + rHTeamFlag + ", rHTeamBadge=" + rHTeamBadge + ", rVTeamID=" + rVTeamID
				+ ", rVTeamName=" + rVTeamName + ", rVTeamFlag=" + rVTeamFlag + ", rVTeamBadge=" + rVTeamBadge
				+ ", rTime=" + rTime + ", sTime=" + sTime + ", eTime=" + eTime + ", RE_WIN=" + RE_WIN + ", OV_WIN="
				+ OV_WIN + ", PE_WIN=" + PE_WIN + ", RE_FAILE=" + RE_FAILE + ", OV_FAILE=" + OV_FAILE + ", PE_FAILE="
				+ PE_FAILE + ", HOME_GRADE=" + HOME_GRADE + ", VISITING_GRADE=" + VISITING_GRADE + ", COM_NAME="
				+ COM_NAME + ", COM_ID=" + COM_ID + ", SE_STATUS=" + SE_STATUS + ", planContinue=" + planContinue
				+ ", arrangedNum=" + arrangedNum + "]";
	}


}
