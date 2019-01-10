package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ExamRecord类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: ExamRecord
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/8/8 16:08
 */
@Entity
@Table(name = "exam_record", schema = "art-exam", catalog = "")
public class ExamRecord {
    private long rId;
    private String stuAnswer;
    private Integer practiceActivity;
    private Integer strongSkill;
    private Integer outActivity;
    private Integer exam;
    private int examStatus;
    private Double record;
    private Long erroeNo;
    private Long correctNo;
    private Long oCId;
    private Long pCId;
    private Long sCId;
    private String createBy;
    private Timestamp createDate;
    private String operBy;
    private Timestamp operDate;
    private String status;
    private Long sId;
    private Long classId;
    private Long eIId;
    private Long uId;

    @Id
    @Column(name = "R_ID", nullable = false)
    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    @Basic
    @Column(name = "STU_ANSWER", nullable = true, length = 225)
    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    @Basic
    @Column(name = "PRACTICE_ACTIVITY", nullable = true)
    public Integer getPracticeActivity() {
        return practiceActivity;
    }

    public void setPracticeActivity(Integer practiceActivity) {
        this.practiceActivity = practiceActivity;
    }

    @Basic
    @Column(name = "STRONG_SKILL", nullable = true)
    public Integer getStrongSkill() {
        return strongSkill;
    }

    public void setStrongSkill(Integer strongSkill) {
        this.strongSkill = strongSkill;
    }

    @Basic
    @Column(name = "OUT_ACTIVITY", nullable = true)
    public Integer getOutActivity() {
        return outActivity;
    }

    public void setOutActivity(Integer outActivity) {
        this.outActivity = outActivity;
    }

    @Basic
    @Column(name = "EXAM", nullable = true)
    public Integer getExam() {
        return exam;
    }

    public void setExam(Integer exam) {
        this.exam = exam;
    }

    @Basic
    @Column(name = "EXAM_STATUS", nullable = false)
    public int getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(int examStatus) {
        this.examStatus = examStatus;
    }

    @Basic
    @Column(name = "RECORD", nullable = true, precision = 0)
    public Double getRecord() {
        return record;
    }

    public void setRecord(Double record) {
        this.record = record;
    }

    @Basic
    @Column(name = "ERROE_NO", nullable = true)
    public Long getErroeNo() {
        return erroeNo;
    }

    public void setErroeNo(Long erroeNo) {
        this.erroeNo = erroeNo;
    }

    @Basic
    @Column(name = "CORRECT_NO", nullable = true)
    public Long getCorrectNo() {
        return correctNo;
    }

    public void setCorrectNo(Long correctNo) {
        this.correctNo = correctNo;
    }

    @Basic
    @Column(name = "O_C_ID", nullable = true)
    public Long getoCId() {
        return oCId;
    }

    public void setoCId(Long oCId) {
        this.oCId = oCId;
    }

    @Basic
    @Column(name = "P_C_ID", nullable = true)
    public Long getpCId() {
        return pCId;
    }

    public void setpCId(Long pCId) {
        this.pCId = pCId;
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
    @Column(name = "STATUS", nullable = true, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    @Column(name = "CLASS_ID", nullable = true)
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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
    @Column(name = "U_ID", nullable = true)
    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamRecord that = (ExamRecord) o;

        if (rId != that.rId) return false;
        if (examStatus != that.examStatus) return false;
        if (stuAnswer != null ? !stuAnswer.equals(that.stuAnswer) : that.stuAnswer != null) return false;
        if (practiceActivity != null ? !practiceActivity.equals(that.practiceActivity) : that.practiceActivity != null)
            return false;
        if (strongSkill != null ? !strongSkill.equals(that.strongSkill) : that.strongSkill != null) return false;
        if (outActivity != null ? !outActivity.equals(that.outActivity) : that.outActivity != null) return false;
        if (exam != null ? !exam.equals(that.exam) : that.exam != null) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;
        if (erroeNo != null ? !erroeNo.equals(that.erroeNo) : that.erroeNo != null) return false;
        if (correctNo != null ? !correctNo.equals(that.correctNo) : that.correctNo != null) return false;
        if (oCId != null ? !oCId.equals(that.oCId) : that.oCId != null) return false;
        if (pCId != null ? !pCId.equals(that.pCId) : that.pCId != null) return false;
        if (sCId != null ? !sCId.equals(that.sCId) : that.sCId != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (operBy != null ? !operBy.equals(that.operBy) : that.operBy != null) return false;
        if (operDate != null ? !operDate.equals(that.operDate) : that.operDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (sId != null ? !sId.equals(that.sId) : that.sId != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (eIId != null ? !eIId.equals(that.eIId) : that.eIId != null) return false;
        if (uId != null ? !uId.equals(that.uId) : that.uId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rId ^ (rId >>> 32));
        result = 31 * result + (stuAnswer != null ? stuAnswer.hashCode() : 0);
        result = 31 * result + (practiceActivity != null ? practiceActivity.hashCode() : 0);
        result = 31 * result + (strongSkill != null ? strongSkill.hashCode() : 0);
        result = 31 * result + (outActivity != null ? outActivity.hashCode() : 0);
        result = 31 * result + (exam != null ? exam.hashCode() : 0);
        result = 31 * result + examStatus;
        result = 31 * result + (record != null ? record.hashCode() : 0);
        result = 31 * result + (erroeNo != null ? erroeNo.hashCode() : 0);
        result = 31 * result + (correctNo != null ? correctNo.hashCode() : 0);
        result = 31 * result + (oCId != null ? oCId.hashCode() : 0);
        result = 31 * result + (pCId != null ? pCId.hashCode() : 0);
        result = 31 * result + (sCId != null ? sCId.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sId != null ? sId.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (eIId != null ? eIId.hashCode() : 0);
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        return result;
    }
}
