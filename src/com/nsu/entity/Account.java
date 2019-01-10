package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Account类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Account
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/8/10 16:01
 */
@Entity
public class Account {
    private int aId;
    private String username;
    private String password;
    private Date recentLogin;
    private String idCard;
    private Integer type;
    private Integer status;
    private Timestamp createDate;
    private Long createBy;
    private Timestamp operDate;
    private Long operBy;
    private Long parentId;
    private Long otherId;

    @Id
    @Column(name = "A_ID", nullable = false)
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 40)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "RECENT_LOGIN", nullable = true)
    public Date getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(Date recentLogin) {
        this.recentLogin = recentLogin;
    }

    @Basic
    @Column(name = "ID_CARD", nullable = true, length = 50)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
    @Column(name = "PARENT_ID", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

        Account account = (Account) o;

        if (aId != account.aId) return false;
        if (username != null ? !username.equals(account.username) : account.username != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        if (recentLogin != null ? !recentLogin.equals(account.recentLogin) : account.recentLogin != null) return false;
        if (idCard != null ? !idCard.equals(account.idCard) : account.idCard != null) return false;
        if (type != null ? !type.equals(account.type) : account.type != null) return false;
        if (status != null ? !status.equals(account.status) : account.status != null) return false;
        if (createDate != null ? !createDate.equals(account.createDate) : account.createDate != null) return false;
        if (createBy != null ? !createBy.equals(account.createBy) : account.createBy != null) return false;
        if (operDate != null ? !operDate.equals(account.operDate) : account.operDate != null) return false;
        if (operBy != null ? !operBy.equals(account.operBy) : account.operBy != null) return false;
        if (parentId != null ? !parentId.equals(account.parentId) : account.parentId != null) return false;
        if (otherId != null ? !otherId.equals(account.otherId) : account.otherId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (recentLogin != null ? recentLogin.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (operDate != null ? operDate.hashCode() : 0);
        result = 31 * result + (operBy != null ? operBy.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (otherId != null ? otherId.hashCode() : 0);
        return result;
    }
}
