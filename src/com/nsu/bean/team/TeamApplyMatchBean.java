package com.nsu.bean.team;

/**
 * 球队申请比赛bean
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-17 10:03:08
 */
public class TeamApplyMatchBean {

	//赛事编号
	private String comID;
	//比赛名称
	private String comName;
	//赛事级别
	private String comLevel;
	private String comBigLevel;
	//赛事类型
	private String comType;
	//赛事组别
	private String comGroup;
	//报名截止日期
	private String comEndTime;
	//比赛申请状态（0：待审核，1：通过，2：未通过 ，3： 没有则默认还没申请 ）
	private String comStatus;
	//申请按钮提示文字
	private String applyText;
	//是否允许操作申请报名，不允许操作情况：0.报名时间已经过了不能再报名、待审核和报名通过了不能再报名； 允许操作：1/null
	private int allowApply;
	//是否允许操作安排球员，不允许操作情况：0.没申请成功时不能安排球员；允许操作：1/null
	private int allowPlanPlayers;
	
	//比赛时间
	private String comTime;
	//比赛开始时间
	private String comStart;
	//比赛结束时间
	private String comEnd;
	
	
	public String getApplyText() {
		return applyText;
	}
	public void setApplyText(String applyText) {
		this.applyText = applyText;
	}
	public int getAllowApply() {
		return allowApply;
	}
	public void setAllowApply(int allowApply) {
		this.allowApply = allowApply;
	}
	public int getAllowPlanPlayers() {
		return allowPlanPlayers;
	}
	public void setAllowPlanPlayers(int allowPlanPlayers) {
		this.allowPlanPlayers = allowPlanPlayers;
	}
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComLevel() {
		return comLevel==null?" ":
			comLevel.equals("1")==true?"小学":
				comLevel.equals("2")==true?"中学":
					comLevel.equals("3")==true?"高中":
						comLevel.equals("4")==true?"大学":"不限";
	}
	public void setComLevel(String comLevel) {
		this.comLevel = comLevel;
	}
	public String getComType() {
		return comType==null?" ":
					comType.equals("1")==true?"男子":
						comType.equals("2")==true?"女子":"不限";
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public String getComGroup() {
		return comGroup==null?" ":
				 comGroup.equals("1")==true?"5人":
					comGroup.equals("2")==true?"7人":
						comGroup.equals("3")==true?"11人":"不限";
	}
	public void setComGroup(String comGroup) {
		this.comGroup = comGroup;
	}
	public String getComEndTime() {
		return comEndTime;
	}
	public void setComEndTime(String comEndTime) {
		this.comEndTime = comEndTime;
	}
	public String getComStatus() {
		return comStatus;
	}
	public void setComStatus(String comStatus) {
		this.comStatus = comStatus;
	}
	public String getComTime() {
		String time = getComStart()+"--"+getComEnd();
		if(time == null || time.length()==2){
			time = "待定";
		}
		return comTime!=null?comTime:time;
	}
	public void setComTime(String comTime) {
		this.comTime = comTime;
	}
	public String getComStart() {
		return comStart;
	}
	public void setComStart(String comStart) {
		this.comStart = comStart;
	}
	public String getComEnd() {
		return comEnd;
	}
	public void setComEnd(String comEnd) {
		this.comEnd = comEnd;
	}
	
	public String getComBigLevel() {
		return comBigLevel==null?"无":
			comBigLevel.equals("1")==true?"省级":
				comBigLevel.equals("2")==true?"市级":
					comBigLevel.equals("3")==true?"县级":
						comBigLevel.equals("4")==true?"校级":"不限";
	}
	public void setComBigLevel(String comBigLevel) {
		this.comBigLevel = comBigLevel;
	}
	@Override
	public String toString() {
		return "TeamApplyMatchBean [comID=" + comID + ", comName=" + comName + ", comLevel=" + comLevel
				+ ", comBigLevel=" + comBigLevel + ", comType=" + comType + ", comGroup=" + comGroup + ", comEndTime="
				+ comEndTime + ", comStatus=" + comStatus + ", applyText=" + applyText + ", allowApply=" + allowApply
				+ ", allowPlanPlayers=" + allowPlanPlayers + ", comTime=" + comTime + ", comStart=" + comStart
				+ ", comEnd=" + comEnd + "]";
	}


}
