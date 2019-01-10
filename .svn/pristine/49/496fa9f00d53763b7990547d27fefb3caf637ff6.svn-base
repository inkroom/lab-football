package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * LoginInformation类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: LoginInformation
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "login_information", schema = "art-exam", catalog = "")
public class LoginInformation {
    private long iId;
    private String tName;
    private String tSex;
    private String subject;
    private String school;
    private String studyGrade;
    private String idCard;
    private String email;
    private String phone;
    private String chStatus;
    private Integer status;
    private Long operBy;
    private Timestamp operDate;
    private Long createBy;
    private Timestamp createDate;
    private Long aId;

    @Id
    @Column(name = "I_ID", nullable = false)
    public long getiId() {
        return iId;
    }

    public void setiId(long iId) {
        this.iId = iId;
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
    @Column(name = "T_SEX", nullable = true, length = 10)
    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    @Basic
    @Column(name = "SUBJECT", nullable = true, length = 2)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "SCHOOL", nullable = true, length = 30)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "STUDY_GRADE", nullable = true, length = 30)
    public String getStudyGrade() {
        return studyGrade;
    }

    public void setStudyGrade(String studyGrade) {
        this.studyGrade = studyGrade;
    }

    @Basic
    @Column(name = "ID_CARD", nullable = true, length = 30)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "CH_STATUS", nullable = true, length = 30)
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

    @Basic
    @Column(name = "OPER_BY", nullable = true)
    public Long getOperBy() {
        return operBy;
    }

    public void setOperBy(Long operBy) {
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
    @Column(name = "CREATE_BY", nullable = true)
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
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
    @Column(name = "A_ID", nullable = true)
    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginInformation that = (LoginInformation) o;

        if (iId != that.iId) return false;
        if (tName != null ? !tName.equals(that.tName) : that.tName != null) return false;
        if (tSex != null ? !tSex.equals(that.tSex) : that.tSex != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (studyGrade != null ? !studyGrade.equals(that.studyGrade) : that.studyGrade != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (chStatus != null ? !chStatus.equals(that.chStatus) : that.chStatus != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (operBy != null ? !operBy.equals(that.operBy) : that.operBy != null) return false;
        if (operDate != null ? !operDate.equals(that.operDate) : that.operDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (aId != null ? !aId.equals(that.aId) : that.aId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (iId ^ (iId >>> 32));
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (tSex != null ? tSex.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (studyGrade != null ? studyGrade.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (chStatus != null ? chStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        return result;
    }
}
