package com.nsu.bean.team;

import com.sun.javafx.binding.StringFormatter;

/**
 * 
* @ClassName: 比赛球队基本信息
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月20日 下午7:45:12 
*
 */
public class TeamMatchBean {

	private String comID;//主键
	private String TEA_NUM;//队伍人数
	private String W_NAME;//警告人姓名
	private String W_NUM;//警告人号码
	private String W_REASON;//警告原因
	private String S_NAME;//罚出人姓名
	private String S_NUM;//罚出人号码
	private String S_REASON;//罚出原因
	
	private String R_RED_C_TEXT;//红牌说明
	private String R_PENA_TEXT;//罚球点球情况说明
	private String R_WRONG_TEXT;//严重错错漏判说明
	
	private String R_REGULAR_H_T_S;//常规赛主队得分
	private String R_OVERTIME_H_T_S;//加时赛主队得分
	private String R_PENA_H_T_S;//点球决胜主队得分
	
	
	private String R_REGULAR_V_T_S;//常规赛客队得分
	private String R_OVERTIME_V_T_S;//加时赛客队得分
	private String R_PENA_V_T_S;//点球决胜客队得分
	
	private String H_GRADE;//主队总分
	private String v_GRADE;//客队总分
	
	
	private String COM_NAME;//赛事名称
	private String COM_TYPE;//赛事类型
	private String COM_LEVEL;//赛事级别
	private String COM_ORGAZITION;//所属机构
	private String COM_START;//赛事开始时间
	private String COM_END;//赛事结束时间
	private String TEAM_COM_STATUS;//审核状态
	private String COM_GROUNP;//足球组别
	
	public String getTEA_NUM() {
		return TEA_NUM==null?"":TEA_NUM;
	}
	public void setTEA_NUM(String tEA_NUM) {
		TEA_NUM = tEA_NUM;
	}
	public String getW_NAME() {
		return W_NAME==null?"":W_NAME;
	}
	public void setW_NAME(String w_NAME) {
		W_NAME = w_NAME;
	}
	public String getW_NUM() {
		return W_NUM==null?"":W_NUM;
	}
	public void setW_NUM(String w_NUM) {
		W_NUM = w_NUM;
	}
	public String getW_REASON() {
		return W_REASON==null?"":W_REASON;
	}
	public void setW_REASON(String w_REASON) {
		W_REASON = w_REASON;
	}
	public String getS_NAME() {
		return S_NAME==null?"":S_NAME;
	}
	public void setS_NAME(String s_NAME) {
		S_NAME = s_NAME;
	}
	public String getS_NUM() {
		return S_NUM==null?"":S_NUM;
	}
	public void setS_NUM(String s_NUM) {
		S_NUM = s_NUM;
	}
	public String getS_REASON() {
		return S_REASON==null?"":S_REASON;
	}
	public void setS_REASON(String s_REASON) {
		S_REASON = s_REASON;
	}
	public String getR_RED_C_TEXT() {
		return R_RED_C_TEXT==null?"":R_RED_C_TEXT;
	}
	public void setR_RED_C_TEXT(String r_RED_C_TEXT) {
		R_RED_C_TEXT = r_RED_C_TEXT;
	}
	public String getR_PENA_TEXT() {
		return R_PENA_TEXT==null?"":R_PENA_TEXT;
	}
	public void setR_PENA_TEXT(String r_PENA_TEXT) {
		R_PENA_TEXT = r_PENA_TEXT;
	}
	public String getR_WRONG_TEXT() {
		return R_WRONG_TEXT==null?"":R_WRONG_TEXT;
	}
	public void setR_WRONG_TEXT(String r_WRONG_TEXT) {
		R_WRONG_TEXT = r_WRONG_TEXT;
	}
	public String getR_REGULAR_H_T_S() {
		return R_REGULAR_H_T_S==null?"":R_REGULAR_H_T_S;
	}
	public void setR_REGULAR_H_T_S(String r_REGULAR_H_T_S) {
		R_REGULAR_H_T_S = r_REGULAR_H_T_S;
	}
	public String getR_OVERTIME_H_T_S() {
		return R_OVERTIME_H_T_S==null?"":R_OVERTIME_H_T_S;
	}
	public void setR_OVERTIME_H_T_S(String r_OVERTIME_H_T_S) {
		R_OVERTIME_H_T_S = r_OVERTIME_H_T_S;
	}
	public String getR_PENA_H_T_S() {
		return R_PENA_H_T_S==null?"":R_PENA_H_T_S;
	}
	public void setR_PENA_H_T_S(String r_PENA_H_T_S) {
		R_PENA_H_T_S = r_PENA_H_T_S;
	}
	public String getR_REGULAR_V_T_S() {
		return R_REGULAR_V_T_S==null?"":R_REGULAR_V_T_S;
	}
	public void setR_REGULAR_V_T_S(String r_REGULAR_V_T_S) {
		R_REGULAR_V_T_S = r_REGULAR_V_T_S;
	}
	public String getR_OVERTIME_V_T_S() {
		return R_OVERTIME_V_T_S==null?"":R_OVERTIME_V_T_S;
	}
	public void setR_OVERTIME_V_T_S(String r_OVERTIME_V_T_S) {
		R_OVERTIME_V_T_S = r_OVERTIME_V_T_S;
	}
	public String getR_PENA_V_T_S() {
		return R_PENA_V_T_S==null?"":R_PENA_V_T_S;
	}
	public void setR_PENA_V_T_S(String r_PENA_V_T_S) {
		R_PENA_V_T_S = r_PENA_V_T_S;
	}
	public String getH_GRADE() {
		return H_GRADE==null?"":H_GRADE;
	}
	public void setH_GRADE(String h_GRADE) {
		H_GRADE = h_GRADE;
	}
	public String getV_GRADE() {
		return v_GRADE==null?"":v_GRADE;
	}
	public void setV_GRADE(String v_GRADE) {
		this.v_GRADE = v_GRADE;
	}
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getCOM_NAME() {
		return COM_NAME;
	}
	public void setCOM_NAME(String cOM_NAME) {
		COM_NAME = cOM_NAME;
	}
	public String getCOM_TYPE() {
		 return COM_TYPE==null?"":
			      COM_TYPE.equals("1")?"男子":
				     COM_TYPE.equals("2")?"女子":"混合";
	}
	public void setCOM_TYPE(String cOM_TYPE) {
		COM_TYPE = cOM_TYPE;
	}
	public String getCOM_LEVEL() {
		 return COM_LEVEL==null?"":
			 COM_LEVEL.equals("1")?"小学":
				 COM_LEVEL.equals("2")?"初中":
					 COM_LEVEL.equals("3")?"高中":  
						 COM_LEVEL.equals("4")?"大学":"其他";
	}
	public void setCOM_LEVEL(String cOM_LEVEL) {
		COM_LEVEL = cOM_LEVEL;
	}
	public String getCOM_ORGAZITION() {
		return COM_ORGAZITION;
	}
	public void setCOM_ORGAZITION(String cOM_ORGAZITION) {
		COM_ORGAZITION = cOM_ORGAZITION;
	}
	public String getCOM_START() {
		return COM_START;
	}
	public void setCOM_START(String cOM_START) {
		COM_START = cOM_START;
	}
	public String getCOM_END() {
		return COM_END;
	}
	public void setCOM_END(String cOM_END) {
		COM_END = cOM_END;
	}
	public String getTEAM_COM_STATUS() {
        return TEAM_COM_STATUS==null?"":
        	     TEAM_COM_STATUS.equals("0")?"待审核":
        		    TEAM_COM_STATUS.equals("1")?"通过":"未通过";
	}
	public void setTEAM_COM_STATUS(String tEAM_COM_STATUS) {
		TEAM_COM_STATUS = tEAM_COM_STATUS;
	}
	public String getCOM_GROUNP() {
		return COM_GROUNP==null?"":
			COM_GROUNP.equals("1")?"5人级":
				COM_GROUNP.equals("2")?"7人级":
					COM_GROUNP.equals("3")?"11人级":"其他";
	}
	public void setCOM_GROUNP(String cOM_GROUNP) {
		COM_GROUNP = cOM_GROUNP;
	}
	@Override
	public String toString() {
		return "TeamMatchBean [comID=" + comID + ", TEA_NUM=" + TEA_NUM + ", W_NAME=" + W_NAME + ", W_NUM=" + W_NUM
				+ ", W_REASON=" + W_REASON + ", S_NAME=" + S_NAME + ", S_NUM=" + S_NUM + ", S_REASON=" + S_REASON
				+ ", R_RED_C_TEXT=" + R_RED_C_TEXT + ", R_PENA_TEXT=" + R_PENA_TEXT + ", R_WRONG_TEXT=" + R_WRONG_TEXT
				+ ", R_REGULAR_H_T_S=" + R_REGULAR_H_T_S + ", R_OVERTIME_H_T_S=" + R_OVERTIME_H_T_S + ", R_PENA_H_T_S="
				+ R_PENA_H_T_S + ", R_REGULAR_V_T_S=" + R_REGULAR_V_T_S + ", R_OVERTIME_V_T_S=" + R_OVERTIME_V_T_S
				+ ", R_PENA_V_T_S=" + R_PENA_V_T_S + ", H_GRADE=" + H_GRADE + ", v_GRADE=" + v_GRADE + ", COM_NAME="
				+ COM_NAME + ", COM_TYPE=" + COM_TYPE + ", COM_LEVEL=" + COM_LEVEL + ", COM_ORGAZITION="
				+ COM_ORGAZITION + ", COM_START=" + COM_START + ", COM_END=" + COM_END + ", TEAM_COM_STATUS="
				+ TEAM_COM_STATUS + ", COM_GROUNP=" + COM_GROUNP + "]";
	}




}
