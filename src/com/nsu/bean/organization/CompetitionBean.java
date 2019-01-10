package com.nsu.bean.organization;

import java.math.BigInteger;

public class CompetitionBean {

    /*机构ID*/
    private BigInteger com_id;

    /*赛事名*/
    private  String com_name;


    private  Integer com_level;

    /*赛事开始时间*/
    private String com_start;

    /*赛事结束时间*/
    private String com_end;

 
    private  Integer com_grounp;

    /*赛事级别*/
    private Integer com_big_level;

    /*状态位： 1.启用 2.结束 3.删除 */
    private String com_status;

    public String getCom_status() {
        return com_status;
    }

    public void setCom_status(String com_status) {
        this.com_status = com_status;
    }

    public Integer getCom_big_level() {
        return com_big_level;
    }

    public void setCom_big_level(Integer com_big_level) {
        this.com_big_level = com_big_level;
    }

    public String getCom_start() {
        return com_start;
    }

    public void setCom_start(String com_start) {
        this.com_start = com_start;
    }

    public String getCom_end() {
        return com_end;
    }

    public void setCom_end(String com_end) {
        this.com_end = com_end;
    }

    public BigInteger getCom_id() {
        return com_id;
    }

    public void setCom_id(BigInteger com_id) {
        this.com_id = com_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public Integer getCom_level() {
        return com_level;
    }

    public void setCom_level(Integer com_level) {
        this.com_level = com_level;
    }


    public Integer getCom_grounp() {
        return com_grounp;
    }

    public void setCom_grounp(Integer com_grounp) {
        this.com_grounp = com_grounp;
    }

    public CompetitionBean() {
    }
}
