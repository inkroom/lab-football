package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * StuClassMap类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: StuClassMap
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "stu_class_map", schema = "art-exam", catalog = "")
public class StuClassMap {
    private long sCMap;
    private Long sId;
    private Timestamp joinTime;
    private Long classId;
    private String createBy;
    private Timestamp createDate;
    private String operBy;
    private Timestamp operDate;
    private String chStatus;
    private Integer status;

    @Id
    @Column(name = "S_C_MAP", nullable = false)
    public long getsCMap() {
        return sCMap;
    }

    public void setsCMap(long sCMap) {
        this.sCMap = sCMap;
    }

    @Basic
    @Column(name = "S_ID", nullable = true)
    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
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
    @Column(name = "CLASS_ID", nullable = true)
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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
    @Column(name = "CREATE_DATE", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
    @Column(name = "OPER_DATE", nullable = true)
    public Timestamp getOperDate() {
        return operDate;
    }

    public void setOperDate(Timestamp operDate) {
        this.operDate = operDate;
    }

    @Basic
    @Column(name = "CH_STATUS", nullable = true, length = 2)
    public String getChStatus() {
        return chStatus;
    }

    public void setChStatus(String chStatus) {
        this.chStatus = chStatus;
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

        StuClassMap that = (StuClassMap) o;

        if (sCMap != that.sCMap) return false;
        if (sId != null ? !sId.equals(that.sId) : that.sId != null) return false;
        if (joinTime != null ? !joinTime.equals(that.joinTime) : that.joinTime != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (operBy != null ? !operBy.equals(that.operBy) : that.operBy != null) return false;
        if (operDate != null ? !operDate.equals(that.operDate) : that.operDate != null) return false;
        if (chStatus != null ? !chStatus.equals(that.chStatus) : that.chStatus != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sCMap ^ (sCMap >>> 32));
        result = 31 * result + (sId != null ? sId.hashCode() : 0);
        result = 31 * result + (joinTime != null ? joinTime.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (chStatus != null ? chStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
