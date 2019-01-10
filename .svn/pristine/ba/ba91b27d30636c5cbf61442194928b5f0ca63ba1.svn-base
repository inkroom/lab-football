package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Log类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Log
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Log {
    private long lId;
    private String ip;
    private String username;
    private String event;
    private Timestamp operDate;
    private Timestamp createDate;
    private String createBy;
    private String operBy;

    @Id
    @Column(name = "L_ID", nullable = false)
    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    @Basic
    @Column(name = "IP", nullable = false, length = 255)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "EVENT", nullable = false, length = 200)
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Basic
    @Column(name = "OPER_DATE", nullable = false)
    public Timestamp getOperDate() {
        return operDate;
    }

    public void setOperDate(Timestamp operDate) {
        this.operDate = operDate;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 255)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "OPER_BY", nullable = true, length = 255)
    public String getOperBy() {
        return operBy;
    }

    public void setOperBy(String operBy) {
        this.operBy = operBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (lId != log.lId) return false;
        if (ip != null ? !ip.equals(log.ip) : log.ip != null) return false;
        if (username != null ? !username.equals(log.username) : log.username != null) return false;
        if (event != null ? !event.equals(log.event) : log.event != null) return false;
        if (operDate != null ? !operDate.equals(log.operDate) : log.operDate != null) return false;
        if (createDate != null ? !createDate.equals(log.createDate) : log.createDate != null) return false;
        if (createBy != null ? !createBy.equals(log.createBy) : log.createBy != null) return false;
        if (operBy != null ? !operBy.equals(log.operBy) : log.operBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (lId ^ (lId >>> 32));
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        return result;
    }
}
