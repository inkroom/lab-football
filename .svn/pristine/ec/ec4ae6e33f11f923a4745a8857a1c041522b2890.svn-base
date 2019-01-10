package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * TCMap类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: TCMap
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "t_c_map", schema = "art-exam", catalog = "")
public class TCMap {
    private long tCId;
    private Timestamp joinTime;
    private long classId;
    private long tId;
    private Timestamp createDate;
    private String createBy;
    private Timestamp operDate;
    private String operBy;
    private Integer status;

    @Id
    @Column(name = "T_C_ID", nullable = false)
    public long gettCId() {
        return tCId;
    }

    public void settCId(long tCId) {
        this.tCId = tCId;
    }

    @Basic
    @Column(name = "JOIN_TIME", nullable = true)
    public Timestamp getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }

    @Basic
    @Column(name = "CLASS_ID", nullable = false)
    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "T_ID", nullable = false)
    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
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
    @Column(name = "CREATE_BY", nullable = true, length = 10)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "OPER_DATE", nullable = true)
    public Timestamp getOperDate() {
        return operDate;
    }

    public void setOperDate(Timestamp operDate) {
        this.operDate = operDate;
    }

    @Basic
    @Column(name = "OPER_BY", nullable = true, length = 10)
    public String getOperBy() {
        return operBy;
    }

    public void setOperBy(String operBy) {
        this.operBy = operBy;
    }

    @Basic
    @Column(name = "STATUS", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCMap tcMap = (TCMap) o;

        if (tCId != tcMap.tCId) return false;
        if (classId != tcMap.classId) return false;
        if (tId != tcMap.tId) return false;
        if (joinTime != null ? !joinTime.equals(tcMap.joinTime) : tcMap.joinTime != null) return false;
        if (createDate != null ? !createDate.equals(tcMap.createDate) : tcMap.createDate != null) return false;
        if (createBy != null ? !createBy.equals(tcMap.createBy) : tcMap.createBy != null) return false;
        if (operDate != null ? !operDate.equals(tcMap.operDate) : tcMap.operDate != null) return false;
        if (operBy != null ? !operBy.equals(tcMap.operBy) : tcMap.operBy != null) return false;
        if (status != null ? !status.equals(tcMap.status) : tcMap.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tCId ^ (tCId >>> 32));
        result = 31 * result + (joinTime != null ? joinTime.hashCode() : 0);
        result = 31 * result + (int) (classId ^ (classId >>> 32));
        result = 31 * result + (int) (tId ^ (tId >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
