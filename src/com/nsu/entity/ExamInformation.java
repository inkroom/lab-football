package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ExamInformation类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: ExamInformation
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/8/10 16:01
 */
@Entity
@Table(name = "exam_information", schema = "art-exam", catalog = "")
public class ExamInformation {
    private long eIId;
    private Integer examLevel;
    private Timestamp startTime;
    private Timestamp endTime;
    private String numOfQues;
    private String testName;
    private Integer subject;
    private String grade;
    private String createBy;
    private Timestamp createDate;
    private String operBy;
    private Timestamp operDate;
    private String testStutas;
    private Integer stutas;
    private Long classId;
    private Long tId;

    @Id
    @Column(name = "E_I_ID", nullable = false)
    public long geteIId() {
        return eIId;
    }

    public void seteIId(long eIId) {
        this.eIId = eIId;
    }

    @Basic
    @Column(name = "EXAM_LEVEL", nullable = true)
    public Integer getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(Integer examLevel) {
        this.examLevel = examLevel;
    }

    @Basic
    @Column(name = "START_TIME", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "NUM_OF_QUES", nullable = true, length = 100)
    public String getNumOfQues() {
        return numOfQues;
    }

    public void setNumOfQues(String numOfQues) {
        this.numOfQues = numOfQues;
    }

    @Basic
    @Column(name = "TEST_NAME", nullable = true, length = 30)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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
    @Column(name = "GRADE", nullable = true, length = 10)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
    @Column(name = "TEST_STUTAS", nullable = true, length = 10)
    public String getTestStutas() {
        return testStutas;
    }

    public void setTestStutas(String testStutas) {
        this.testStutas = testStutas;
    }

    @Basic
    @Column(name = "STUTAS", nullable = true)
    public Integer getStutas() {
        return stutas;
    }

    public void setStutas(Integer stutas) {
        this.stutas = stutas;
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
    @Column(name = "T_ID", nullable = true)
    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamInformation that = (ExamInformation) o;

        if (eIId != that.eIId) return false;
        if (examLevel != null ? !examLevel.equals(that.examLevel) : that.examLevel != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (numOfQues != null ? !numOfQues.equals(that.numOfQues) : that.numOfQues != null) return false;
        if (testName != null ? !testName.equals(that.testName) : that.testName != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (operBy != null ? !operBy.equals(that.operBy) : that.operBy != null) return false;
        if (operDate != null ? !operDate.equals(that.operDate) : that.operDate != null) return false;
        if (testStutas != null ? !testStutas.equals(that.testStutas) : that.testStutas != null) return false;
        if (stutas != null ? !stutas.equals(that.stutas) : that.stutas != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (tId != null ? !tId.equals(that.tId) : that.tId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (eIId ^ (eIId >>> 32));
        result = 31 * result + (examLevel != null ? examLevel.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (numOfQues != null ? numOfQues.hashCode() : 0);
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (testStutas != null ? testStutas.hashCode() : 0);
        result = 31 * result + (stutas != null ? stutas.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (tId != null ? tId.hashCode() : 0);
        return result;
    }
}
