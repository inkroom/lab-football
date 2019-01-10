package cn.nsu.edu.web.four.beans.referee;

import java.util.Date;

public class Report {

    private int id;
    private int schId;
    private int refId;//裁判
    private Integer result;//结果
    private int scoreA;
    private int scoreB;
    private String redDes;
    private String pointDes;
    private String missDes;
    private Date date;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public String getRedDes() {
        return redDes;
    }

    public void setRedDes(String redDes) {
        this.redDes = redDes;
    }

    public String getPointDes() {
        return pointDes;
    }

    public void setPointDes(String pointDes) {
        this.pointDes = pointDes;
    }

    public String getMissDes() {
        return missDes;
    }

    public void setMissDes(String missDes) {
        this.missDes = missDes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", schId=" + schId +
                ", refId=" + refId +
                ", result=" + result +
                ", redDes='" + redDes + '\'' +
                ", pointDes='" + pointDes + '\'' +
                ", missDes='" + missDes + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
