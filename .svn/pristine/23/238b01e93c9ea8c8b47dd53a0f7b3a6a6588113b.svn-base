package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Clazz类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Clazz
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "class", schema = "art-exam", catalog = "")
public class Clazz {
    private long classId;
    private String gred;
    private String keyWord;
    private String className;
    private Timestamp createDate;
    private String createBy;
    private Timestamp operDate;
    private String operBy;
    private String status;
    private Long tId;
    private Long oId;

    @Id
    @Column(name = "CLASS_ID", nullable = false)
    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "GRED", nullable = true, length = 10)
    public String getGred() {
        return gred;
    }

    public void setGred(String gred) {
        this.gred = gred;
    }

    @Basic
    @Column(name = "KEY_WORD", nullable = true, length = 30)
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Basic
    @Column(name = "CLASS_NAME", nullable = true, length = 30)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
    @Column(name = "STATUS", nullable = true, length = 30)
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
    @Column(name = "O_ID", nullable = true)
    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (classId != clazz.classId) return false;
        if (gred != null ? !gred.equals(clazz.gred) : clazz.gred != null) return false;
        if (keyWord != null ? !keyWord.equals(clazz.keyWord) : clazz.keyWord != null) return false;
        if (className != null ? !className.equals(clazz.className) : clazz.className != null) return false;
        if (createDate != null ? !createDate.equals(clazz.createDate) : clazz.createDate != null) return false;
        if (createBy != null ? !createBy.equals(clazz.createBy) : clazz.createBy != null) return false;
        if (operDate != null ? !operDate.equals(clazz.operDate) : clazz.operDate != null) return false;
        if (operBy != null ? !operBy.equals(clazz.operBy) : clazz.operBy != null) return false;
        if (status != null ? !status.equals(clazz.status) : clazz.status != null) return false;
        if (tId != null ? !tId.equals(clazz.tId) : clazz.tId != null) return false;
        if (oId != null ? !oId.equals(clazz.oId) : clazz.oId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (classId ^ (classId >>> 32));
        result = 31 * result + (gred != null ? gred.hashCode() : 0);
        result = 31 * result + (keyWord != null ? keyWord.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tId != null ? tId.hashCode() : 0);
        result = 31 * result + (oId != null ? oId.hashCode() : 0);
        return result;
    }
}
