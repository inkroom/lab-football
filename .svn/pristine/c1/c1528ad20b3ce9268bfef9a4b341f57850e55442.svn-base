package com.nsu.entity;

import javax.persistence.*;

/**
 * PrivConf类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: PrivConf
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
@Table(name = "priv_conf", schema = "art-exam", catalog = "")
public class PrivConf {
    private int prId;
    private String roleName;
    private String actionName;
    private String type;
    private Integer isImportant;
    private String actionDiscription;

    @Id
    @Column(name = "PR_ID", nullable = false, precision = 0)
    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    @Basic
    @Column(name = "ROLE_NAME", nullable = true, length = 20)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ACTION_NAME", nullable = true, length = 100)
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
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
    @Column(name = "IS_IMPORTANT", nullable = true)
    public Integer getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Integer isImportant) {
        this.isImportant = isImportant;
    }

    @Basic
    @Column(name = "ACTION_DISCRIPTION", nullable = true, length = 100)
    public String getActionDiscription() {
        return actionDiscription;
    }

    public void setActionDiscription(String actionDiscription) {
        this.actionDiscription = actionDiscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivConf privConf = (PrivConf) o;

        if (prId != privConf.prId) return false;
        if (roleName != null ? !roleName.equals(privConf.roleName) : privConf.roleName != null) return false;
        if (actionName != null ? !actionName.equals(privConf.actionName) : privConf.actionName != null) return false;
        if (type != null ? !type.equals(privConf.type) : privConf.type != null) return false;
        if (isImportant != null ? !isImportant.equals(privConf.isImportant) : privConf.isImportant != null)
            return false;
        if (actionDiscription != null ? !actionDiscription.equals(privConf.actionDiscription) : privConf.actionDiscription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (actionName != null ? actionName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isImportant != null ? isImportant.hashCode() : 0);
        result = 31 * result + (actionDiscription != null ? actionDiscription.hashCode() : 0);
        return result;
    }
}
