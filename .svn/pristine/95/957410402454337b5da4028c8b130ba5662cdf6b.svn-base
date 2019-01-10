package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Problem类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Problem
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/8/7 17:15
 */
@Entity
public class Problem {
    private long pId;
    private String schoolName;
    private Integer type;
    private String point;
    private String grade;
    private Integer level;
    private Integer subject;
    private String annottation;
    private String answer;
    private String problem;
    private String select;
    private String value;
    private String status;
    private Long createBy;
    private Timestamp createDate;
    private Long operBy;
    private Timestamp operDate;
    private Long sId;
    private Long eIId;
    private Long aId;

    @Id
    @Column(name = "P_ID", nullable = false)
    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "SCHOOL_NAME", nullable = true, length = 20)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "TYPE", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "POINT", nullable = true, length = 30)
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Basic
    @Column(name = "GRADE", nullable = true, length = 30)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "LEVEL", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "SUBJECT", nullable = true)
    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "ANNOTTATION", nullable = true, length = 255)
    public String getAnnottation() {
        return annottation;
    }

    public void setAnnottation(String annottation) {
        this.annottation = annottation;
    }

    @Basic
    @Column(name = "ANSWER", nullable = true, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "PROBLEM", nullable = true, length = 255)
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Basic
    @Column(name = "SELECT", nullable = true, length = 225)
    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Basic
    @Column(name = "VALUE", nullable = true, length = 10)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        Problem problem1 = (Problem) o;

        if (pId != problem1.pId) return false;
        if (schoolName != null ? !schoolName.equals(problem1.schoolName) : problem1.schoolName != null) return false;
        if (type != null ? !type.equals(problem1.type) : problem1.type != null) return false;
        if (point != null ? !point.equals(problem1.point) : problem1.point != null) return false;
        if (grade != null ? !grade.equals(problem1.grade) : problem1.grade != null) return false;
        if (level != null ? !level.equals(problem1.level) : problem1.level != null) return false;
        if (subject != null ? !subject.equals(problem1.subject) : problem1.subject != null) return false;
        if (annottation != null ? !annottation.equals(problem1.annottation) : problem1.annottation != null)
            return false;
        if (answer != null ? !answer.equals(problem1.answer) : problem1.answer != null) return false;
        if (problem != null ? !problem.equals(problem1.problem) : problem1.problem != null) return false;
        if (select != null ? !select.equals(problem1.select) : problem1.select != null) return false;
        if (value != null ? !value.equals(problem1.value) : problem1.value != null) return false;
        if (status != null ? !status.equals(problem1.status) : problem1.status != null) return false;
        if (createBy != null ? !createBy.equals(problem1.createBy) : problem1.createBy != null) return false;
        if (createDate != null ? !createDate.equals(problem1.createDate) : problem1.createDate != null) return false;
        if (operBy != null ? !operBy.equals(problem1.operBy) : problem1.operBy != null) return false;
        if (operDate != null ? !operDate.equals(problem1.operDate) : problem1.operDate != null) return false;
        if (sId != null ? !sId.equals(problem1.sId) : problem1.sId != null) return false;
        if (eIId != null ? !eIId.equals(problem1.eIId) : problem1.eIId != null) return false;
        if (aId != null ? !aId.equals(problem1.aId) : problem1.aId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pId ^ (pId >>> 32));
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (annottation != null ? annottation.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        result = 31 * result + (select != null ? select.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (sId != null ? sId.hashCode() : 0);
        result = 31 * result + (eIId != null ? eIId.hashCode() : 0);
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        return result;
    }
}
