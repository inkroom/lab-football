package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Check类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Check
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Check {
    private long cId;
    private String type;
    private String threePius;
    private String cerName;
    private Integer checkStatus;
    private Timestamp cerateDate;
    private String createBy;
    private Timestamp operDate;
    private String operBy;
    private String status;
    private Long tId;
    private Long uId;
    private Long sId;
    private Long eIId;

    @Id
    @Column(name = "C_ID", nullable = false)
    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "THREE_PIUS", nullable = true, length = 10)
    public String getThreePius() {
        return threePius;
    }

    public void setThreePius(String threePius) {
        this.threePius = threePius;
    }

    @Basic
    @Column(name = "CER_NAME", nullable = true, length = 30)
    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    @Basic
    @Column(name = "CHECK_STATUS", nullable = true)
    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Basic
    @Column(name = "CERATE_DATE", nullable = true)
    public Timestamp getCerateDate() {
        return cerateDate;
    }

    public void setCerateDate(Timestamp cerateDate) {
        this.cerateDate = cerateDate;
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
    @Column(name = "STATUS", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "T_ID", nullable = true)
    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "U_ID", nullable = true)
    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
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
    @Column(name = "E_I_ID", nullable = true)
    public Long geteIId() {
        return eIId;
    }

    public void seteIId(Long eIId) {
        this.eIId = eIId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (cId != check.cId) return false;
        if (type != null ? !type.equals(check.type) : check.type != null) return false;
        if (threePius != null ? !threePius.equals(check.threePius) : check.threePius != null) return false;
        if (cerName != null ? !cerName.equals(check.cerName) : check.cerName != null) return false;
        if (checkStatus != null ? !checkStatus.equals(check.checkStatus) : check.checkStatus != null) return false;
        if (cerateDate != null ? !cerateDate.equals(check.cerateDate) : check.cerateDate != null) return false;
        if (createBy != null ? !createBy.equals(check.createBy) : check.createBy != null) return false;
        if (operDate != null ? !operDate.equals(check.operDate) : check.operDate != null) return false;
        if (operBy != null ? !operBy.equals(check.operBy) : check.operBy != null) return false;
        if (status != null ? !status.equals(check.status) : check.status != null) return false;
        if (tId != null ? !tId.equals(check.tId) : check.tId != null) return false;
        if (uId != null ? !uId.equals(check.uId) : check.uId != null) return false;
        if (sId != null ? !sId.equals(check.sId) : check.sId != null) return false;
        if (eIId != null ? !eIId.equals(check.eIId) : check.eIId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cId ^ (cId >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (threePius != null ? threePius.hashCode() : 0);
        result = 31 * result + (cerName != null ? cerName.hashCode() : 0);
        result = 31 * result + (checkStatus != null ? checkStatus.hashCode() : 0);
        result = 31 * result + (cerateDate != null ? cerateDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tId != null ? tId.hashCode() : 0);
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (sId != null ? sId.hashCode() : 0);
        result = 31 * result + (eIId != null ? eIId.hashCode() : 0);
        return result;
    }
}
