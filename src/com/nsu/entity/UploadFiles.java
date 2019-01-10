package com.nsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * UploadFiles类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: UploadFiles
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "upload_files", schema = "art-exam", catalog = "")
public class UploadFiles {
    private long uId;
    private String savePath;
    private Integer fileType;
    private String download;
    private String grade;
    private String certificate;
    private Integer status;
    private Timestamp createDate;
    private Long createBy;
    private Timestamp operDate;
    private Long operBy;
    private Long oId;
    private Long aId;
    private Long otherId;

    @Id
    @Column(name = "U_ID", nullable = false)
    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "SAVE_PATH", nullable = true, length = -1)
    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Basic
    @Column(name = "FILE_TYPE", nullable = true)
    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "DOWNLOAD", nullable = true, length = 255)
    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    @Basic
    @Column(name = "GRADE", nullable = true, length = 20)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "CERTIFICATE", nullable = true, length = 30)
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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
    @Column(name = "CREATE_DATE", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
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
    public Timestamp getOperDate() {
        return operDate;
    }

    public void setOperDate(Timestamp operDate) {
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
    @Column(name = "O_ID", nullable = true)
    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
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
    @Column(name = "OTHER_ID", nullable = true)
    public Long getOtherId() {
        return otherId;
    }

    public void setOtherId(Long otherId) {
        this.otherId = otherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UploadFiles that = (UploadFiles) o;

        if (uId != that.uId) return false;
        if (savePath != null ? !savePath.equals(that.savePath) : that.savePath != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (download != null ? !download.equals(that.download) : that.download != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (certificate != null ? !certificate.equals(that.certificate) : that.certificate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (operDate != null ? !operDate.equals(that.operDate) : that.operDate != null) return false;
        if (operBy != null ? !operBy.equals(that.operBy) : that.operBy != null) return false;
        if (oId != null ? !oId.equals(that.oId) : that.oId != null) return false;
        if (aId != null ? !aId.equals(that.aId) : that.aId != null) return false;
        if (otherId != null ? !otherId.equals(that.otherId) : that.otherId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uId ^ (uId >>> 32));
        result = 31 * result + (savePath != null ? savePath.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (download != null ? download.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (certificate != null ? certificate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (oId != null ? oId.hashCode() : 0);
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        result = 31 * result + (otherId != null ? otherId.hashCode() : 0);
        return result;
    }
}
