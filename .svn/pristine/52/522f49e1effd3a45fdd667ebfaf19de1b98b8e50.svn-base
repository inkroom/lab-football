package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Student类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Student
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Student {
    private long sId;
    private String type;
    private Integer doNo;
    private Integer integral;
    private String radioCompelecte;
    private Long errorNum;
    private Long correctNum;
    private Integer testStatus;
    private Timestamp cerateDate;
    private String createBy;
    private Timestamp operDate;
    private String operBy;
    private Integer status;
    private Long tId;
    private Long aId;
    private Long sCId;
    private Long pId;

    @Id
    @Column(name = "S_ID", nullable = false)
    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "DO_NO", nullable = true)
    public Integer getDoNo() {
        return doNo;
    }

    public void setDoNo(Integer doNo) {
        this.doNo = doNo;
    }

    @Basic
    @Column(name = "INTEGRAL", nullable = true)
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Basic
    @Column(name = "RADIO_COMPELECTE", nullable = true, length = 20)
    public String getRadioCompelecte() {
        return radioCompelecte;
    }

    public void setRadioCompelecte(String radioCompelecte) {
        this.radioCompelecte = radioCompelecte;
    }

    @Basic
    @Column(name = "ERROR_NUM", nullable = true)
    public Long getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(Long errorNum) {
        this.errorNum = errorNum;
    }

    @Basic
    @Column(name = "CORRECT_NUM", nullable = true)
    public Long getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(Long correctNum) {
        this.correctNum = correctNum;
    }

    @Basic
    @Column(name = "TEST_STATUS", nullable = true)
    public Integer getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Integer testStatus) {
        this.testStatus = testStatus;
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
    @Column(name = "STATUS", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
    @Column(name = "A_ID", nullable = true)
    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "S_C_ID", nullable = true)
    public Long getsCId() {
        return sCId;
    }

    public void setsCId(Long sCId) {
        this.sCId = sCId;
    }

    @Basic
    @Column(name = "P_ID", nullable = true)
    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (sId != student.sId) return false;
        if (type != null ? !type.equals(student.type) : student.type != null) return false;
        if (doNo != null ? !doNo.equals(student.doNo) : student.doNo != null) return false;
        if (integral != null ? !integral.equals(student.integral) : student.integral != null) return false;
        if (radioCompelecte != null ? !radioCompelecte.equals(student.radioCompelecte) : student.radioCompelecte != null)
            return false;
        if (errorNum != null ? !errorNum.equals(student.errorNum) : student.errorNum != null) return false;
        if (correctNum != null ? !correctNum.equals(student.correctNum) : student.correctNum != null) return false;
        if (testStatus != null ? !testStatus.equals(student.testStatus) : student.testStatus != null) return false;
        if (cerateDate != null ? !cerateDate.equals(student.cerateDate) : student.cerateDate != null) return false;
        if (createBy != null ? !createBy.equals(student.createBy) : student.createBy != null) return false;
        if (operDate != null ? !operDate.equals(student.operDate) : student.operDate != null) return false;
        if (operBy != null ? !operBy.equals(student.operBy) : student.operBy != null) return false;
        if (status != null ? !status.equals(student.status) : student.status != null) return false;
        if (tId != null ? !tId.equals(student.tId) : student.tId != null) return false;
        if (aId != null ? !aId.equals(student.aId) : student.aId != null) return false;
        if (sCId != null ? !sCId.equals(student.sCId) : student.sCId != null) return false;
        if (pId != null ? !pId.equals(student.pId) : student.pId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sId ^ (sId >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (doNo != null ? doNo.hashCode() : 0);
        result = 31 * result + (integral != null ? integral.hashCode() : 0);
        result = 31 * result + (radioCompelecte != null ? radioCompelecte.hashCode() : 0);
        result = 31 * result + (errorNum != null ? errorNum.hashCode() : 0);
        result = 31 * result + (correctNum != null ? correctNum.hashCode() : 0);
        result = 31 * result + (testStatus != null ? testStatus.hashCode() : 0);
        result = 31 * result + (cerateDate != null ? cerateDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tId != null ? tId.hashCode() : 0);
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        result = 31 * result + (sCId != null ? sCId.hashCode() : 0);
        result = 31 * result + (pId != null ? pId.hashCode() : 0);
        return result;
    }
}
