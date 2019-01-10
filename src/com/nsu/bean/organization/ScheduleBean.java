package com.nsu.bean.organization;

import com.nsu.util.InfoProUtil;

import java.math.BigInteger;

/*赛程的信息*/
public class ScheduleBean {


    /*赛程ID*/
    private BigInteger r_id;

    /*球队赛程开始时间*/
    private String r_start_time;

    /*球队赛程结束时间*/
    private String r_end_time;

    /*比赛主队id*/
    private Integer r_h_team_id;

    /*比赛客队 id*/
    private Integer r_v_team_id;

    /*比赛主队名称*/
    private String r_h_team_name;

    /*比赛客队名称*/
    private String r_v_team_name;

    /*赛程地区*/
    private String r_region;

    /*队徽*/
    private String team_h_badge;

    private String team_v_badge;

    private String a_username;//现场管理员账号

    /*状态位： 1.启用 2.结束 3.删除*/
    private String r_status;

    private  int r_regular_h_t_s;//常规赛主队得分（数字）
    private  int r_regular_v_t_s;//常规赛客队得分（数字）
    private  int r_overtime_h_t_s;//加时赛主队得分（数字）
    private  int r_overtime_v_t_s;//加时赛客队得分（数字）
    private  int r_pena_h_t_s;//点球决胜主队得分（数字）
    private  int r_pena_v_t_s;//点球决胜客队得分（数字）

    private  int h_score;

    public int getH_score() {
        this.h_score=r_regular_h_t_s+r_overtime_h_t_s+r_pena_h_t_s;
        return this.h_score;
    }
    private  int v_score;

    public int getV_score() {
        this.v_score=r_regular_v_t_s+r_overtime_v_t_s+r_pena_v_t_s;
        return this.v_score;
    }

    public void setV_score(int v_score) {
        this.v_score = v_score;
    }

    public void setH_score(int h_score) {
        this.h_score = h_score;
    }

    public int getR_regular_h_t_s() {
        return r_regular_h_t_s;
    }

    public void setR_regular_h_t_s(int r_regular_h_t_s) {
        this.r_regular_h_t_s = r_regular_h_t_s;
    }

    public int getR_regular_v_t_s() {
        return r_regular_v_t_s;
    }

    public void setR_regular_v_t_s(int r_regular_v_t_s) {
        this.r_regular_v_t_s = r_regular_v_t_s;
    }

    public int getR_overtime_h_t_s() {
        return r_overtime_h_t_s;
    }

    public void setR_overtime_h_t_s(int r_overtime_h_t_s) {
        this.r_overtime_h_t_s = r_overtime_h_t_s;
    }

    public int getR_overtime_v_t_s() {
        return r_overtime_v_t_s;
    }

    public void setR_overtime_v_t_s(int r_overtime_v_t_s) {
        this.r_overtime_v_t_s = r_overtime_v_t_s;
    }

    public int getR_pena_h_t_s() {
        return r_pena_h_t_s;
    }

    public void setR_pena_h_t_s(int r_pena_h_t_s) {
        this.r_pena_h_t_s = r_pena_h_t_s;
    }

    public int getR_pena_v_t_s() {
        return r_pena_v_t_s;
    }

    public void setR_pena_v_t_s(int r_pena_v_t_s) {
        this.r_pena_v_t_s = r_pena_v_t_s;
    }

    public String getR_status() {
        return r_status;
    }

    public void setR_status(String r_status) {
        this.r_status = r_status;
    }

    public String getA_username() {
        return a_username;
    }

    public void setA_username(String a_username) {

        this.a_username = InfoProUtil.xorInfo(a_username);

    }


    public ScheduleBean() {
    }

    public String getTeam_h_badge() {
        return team_h_badge;
    }

    public void setTeam_h_badge(String team_h_badge) {
        this.team_h_badge = team_h_badge;
    }

    public String getTeam_v_badge() {
        return team_v_badge;
    }

    public void setTeam_v_badge(String team_v_badge) {
        this.team_v_badge = team_v_badge;
    }

    public String getR_region() {
        return r_region;
    }

    public void setR_region(String r_region) {
        this.r_region = r_region;
    }

    public BigInteger getR_id() {
        return r_id;
    }

    public void setR_id(BigInteger r_id) {
        this.r_id = r_id;
    }

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

    public Integer getR_h_team_id() {
        return r_h_team_id;
    }

    public void setR_h_team_id(Integer r_h_team_id) {
        this.r_h_team_id = r_h_team_id;
    }

    public Integer getR_v_team_id() {
        return r_v_team_id;
    }

    public void setR_v_team_id(Integer r_v_team_id) {
        this.r_v_team_id = r_v_team_id;
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

    @Override
    public String toString() {
        return "ScheduleBean{" +
                "r_id=" + r_id +
                ", r_start_time='" + r_start_time + '\'' +
                ", r_end_time='" + r_end_time + '\'' +
                ", r_h_team_id=" + r_h_team_id +
                ", r_v_team_id=" + r_v_team_id +
                ", r_h_team_name='" + r_h_team_name + '\'' +
                ", r_v_team_name='" + r_v_team_name + '\'' +
                ", r_region='" + r_region + '\'' +
                '}';
    }
}
