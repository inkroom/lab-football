package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Teacher类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Teacher
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Teacher {
    private long tId;
    private String tName;
    private Timestamp createDate;
    private String createBy;
    private Timestamp operDate;
    private String operBy;
    private Integer status;
    private Long sId;
    private Long aId;
    private Long tCId;

    @Id
    @Column(name = "T_ID", nullable = false)
    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "T_NAME", nullable = true, length = 30)
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
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
    @Column(name = "CREATE_BY", nullable = true, length = 30)
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
    @Column(name = "OPER_BY", nullable = true, length = 30)
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

    @Basic
    @Column(name = "S_ID", nullable = true)
    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "A_ID", nullable = true)
    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "T_C_ID", nullable = true)
    public Long gettCId() {
        return tCId;
    }

    public void settCId(Long tCId) {
        this.tCId = tCId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (tId != teacher.tId) return false;
        if (tName != null ? !tName.equals(teacher.tName) : teacher.tName != null) return false;
        if (createDate != null ? !createDate.equals(teacher.createDate) : teacher.createDate != null) return false;
        if (createBy != null ? !createBy.equals(teacher.createBy) : teacher.createBy != null) return false;
        if (operDate != null ? !operDate.equals(teacher.operDate) : teacher.operDate != null) return false;
        if (operBy != null ? !operBy.equals(teacher.operBy) : teacher.operBy != null) return false;
        if (status != null ? !status.equals(teacher.status) : teacher.status != null) return false;
        if (sId != null ? !sId.equals(teacher.sId) : teacher.sId != null) return false;
        if (aId != null ? !aId.equals(teacher.aId) : teacher.aId != null) return false;
        if (tCId != null ? !tCId.equals(teacher.tCId) : teacher.tCId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tId ^ (tId >>> 32));
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sId != null ? sId.hashCode() : 0);
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        result = 31 * result + (tCId != null ? tCId.hashCode() : 0);
        return result;
    }
}
