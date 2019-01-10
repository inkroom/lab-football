package com.nsu.bean.player;

public class MobilePlayerMatchBean {
	private String r_region;
	private String r_end_time;
	private String r_start_time;
	private String h_team_name;
	private String v_team_name;
	public String getR_region() {
		return r_region;
	}
	public void setR_region(String r_region) {
		this.r_region = r_region;
	}
	public String getR_end_time() {
		return r_end_time;
	}
	public void setR_end_time(String r_end_time) {
		this.r_end_time = r_end_time;
	}
	public String getR_start_time() {
		return r_start_time;
	}
	public void setR_start_time(String r_start_time) {
		this.r_start_time = r_start_time;
	}
	public String getH_team_name() {
		return h_team_name;
	}
	public void setH_team_name(String h_team_name) {
		this.h_team_name = h_team_name;
	}
	public String getV_team_name() {
		return v_team_name;
	}
	public void setV_team_name(String v_team_name) {
		this.v_team_name = v_team_name;
	}
	@Override
	public String toString() {
		return "MobilePlayerMatchBean [r_region=" + r_region + ", r_end_time=" + r_end_time + ", r_start_time="
				+ r_start_time + ", h_team_name=" + h_team_name + ", v_team_name=" + v_team_name + "]";
	}

}
