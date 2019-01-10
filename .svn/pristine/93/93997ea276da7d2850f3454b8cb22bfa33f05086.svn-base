package cn.nsu.edu.web.four.beans.match;


import cn.nsu.edu.web.four.beans.match.valida.SpecialNums;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static cn.nsu.edu.web.four.beans.match.MatchStatic.*;

public class Match {

//0 报名阶段
//1 赛事期间
//2 赛事结束
//3 删除
//状态码

    private Integer idMatch;

    private Staff orgStaff;

    @NotNull
    private String name;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull
    @SpecialNums(value = {5, 7, 11}, message = "赛制人数有误")
    private Integer numPlayer;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDeadline;

    @NotNull
    @SpecialNums(value = {MATCH_LEVEL_SCHOOL, MATCH_LEVEL_COUNTY, MATCH_LEVEL_CITY, MATCH_LEVEL_PROVINCE}, message = "赛事级别有误")
    private Integer level;

    //@Pattern(regexp = RegexStatic.NUMBER) Integer不能用
    @SpecialNums(value = {MATCH_STATUS_SIGN_UP, MATCH_STATUS_DURING, MATCH_STATUS_END, MATCH_STATUS_DELETE}, message = "赛事状态有误")
    private Integer status;

    private Integer exstatus;

    public Integer getExstatus() {
        return exstatus;
    }

    public void setExstatus(Integer exstatus) {
        this.exstatus = exstatus;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Staff getOrgStaff() {
        return orgStaff;
    }

    public void setOrgStaff(Staff orgStaff) {
        this.orgStaff = orgStaff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(Integer numPlayer) {
        this.numPlayer = numPlayer;
    }

    public Date getApplyDeadline() {
        return applyDeadline;
    }

    public void setApplyDeadline(Date applyDeadline) {
        this.applyDeadline = applyDeadline;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", orgStaff=" + orgStaff +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numPlayer=" + numPlayer +
                ", applyDeadline=" + applyDeadline +
                ", level=" + level +
                ", status=" + status +
                ", exstatus=" + exstatus +
                '}';
    }
}