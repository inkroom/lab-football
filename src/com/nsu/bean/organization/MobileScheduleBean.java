package com.nsu.bean.organization;

public class MobileScheduleBean {

    /*球队赛程开始时间*/
    private String  r_start_time;

    /*球队赛程结束时间*/
    private String r_end_time;


    /*比赛主队名称*/
    private  String r_h_team_name;

    /*比赛客队名称*/
    private  String r_v_team_name;


    public String getR_start_time() {
        return r_start_time;
    }

    public void setR_start_time(String r_start_time) {
        this.r_start_time = r_start_time;
    }

    public String getR_end_time() {
        return r_end_time;
    }

    public void setR_end_time(String r_end_time) {
        this.r_end_time = r_end_time;
    }

    public String getR_h_team_name() {
        return r_h_team_name;
    }

    public void setR_h_team_name(String r_h_team_name) {
        this.r_h_team_name = r_h_team_name;
    }

    public String getR_v_team_name() {
        return r_v_team_name;
    }

    public void setR_v_team_name(String r_v_team_name) {
        this.r_v_team_name = r_v_team_name;
    }

    public MobileScheduleBean() {
    }
}
