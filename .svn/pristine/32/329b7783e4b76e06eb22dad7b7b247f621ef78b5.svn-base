package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Org类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Org
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Org {
    private long oId;
    private String oName;
    private int levelCode;
    private Integer pCode;
    private Integer cCode;
    private Integer dCode;
    private Integer tCode;
    private Long parentId;
    private Date createDate;
    private Long createBy;
    private Date operDate;
    private Long operBy;
    private Integer status;
    private Integer stage;

    @Id
    @Column(name = "O_ID", nullable = false)
    public long getoId() {
        return oId;
    }

    public void setoId(long oId) {
        this.oId = oId;
    }

    @Basic
    @Column(name = "O_NAME", nullable = false, length = 50)
    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    @Basic
    @Column(name = "LEVEL_CODE", nullable = false)
    public int getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    @Basic
    @Column(name = "P_CODE", nullable = true)
    public Integer getpCode() {
        return pCode;
    }

    public void setpCode(Integer pCode) {
        this.pCode = pCode;
    }

    @Basic
    @Column(name = "C_CODE", nullable = true)
    public Integer getcCode() {
        return cCode;
    }

    public void setcCode(Integer cCode) {
        this.cCode = cCode;
    }

    @Basic
    @Column(name = "D_CODE", nullable = true)
    public Integer getdCode() {
        return dCode;
    }

    public void setdCode(Integer dCode) {
        this.dCode = dCode;
    }

    @Basic
    @Column(name = "T_CODE", nullable = true)
    public Integer gettCode() {
        return tCode;
    }

    public void settCode(Integer tCode) {
        this.tCode = tCode;
    }

    @Basic
    @Column(name = "PARENT_ID", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true)
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "OPER_DATE", nullable = true)
    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    @Basic
    @Column(name = "OPER_BY", nullable = true)
    public Long getOperBy() {
        return operBy;
    }

    public void setOperBy(Long operBy) {
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
    @Column(name = "STAGE", nullable = true)
    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Org org = (Org) o;

        if (oId != org.oId) return false;
        if (levelCode != org.levelCode) return false;
        if (oName != null ? !oName.equals(org.oName) : org.oName != null) return false;
        if (pCode != null ? !pCode.equals(org.pCode) : org.pCode != null) return false;
        if (cCode != null ? !cCode.equals(org.cCode) : org.cCode != null) return false;
        if (dCode != null ? !dCode.equals(org.dCode) : org.dCode != null) return false;
        if (tCode != null ? !tCode.equals(org.tCode) : org.tCode != null) return false;
        if (parentId != null ? !parentId.equals(org.parentId) : org.parentId != null) return false;
        if (createDate != null ? !createDate.equals(org.createDate) : org.createDate != null) return false;
        if (createBy != null ? !createBy.equals(org.createBy) : org.createBy != null) return false;
        if (operDate != null ? !operDate.equals(org.operDate) : org.operDate != null) return false;
        if (operBy != null ? !operBy.equals(org.operBy) : org.operBy != null) return false;
        if (status != null ? !status.equals(org.status) : org.status != null) return false;
        if (stage != null ? !stage.equals(org.stage) : org.stage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (oId ^ (oId >>> 32));
        result = 31 * result + (oName != null ? oName.hashCode() : 0);
        result = 31 * result + levelCode;
        result = 31 * result + (pCode != null ? pCode.hashCode() : 0);
        result = 31 * result + (cCode != null ? cCode.hashCode() : 0);
        result = 31 * result + (dCode != null ? dCode.hashCode() : 0);
        result = 31 * result + (tCode != null ? tCode.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        return result;
    }
}
