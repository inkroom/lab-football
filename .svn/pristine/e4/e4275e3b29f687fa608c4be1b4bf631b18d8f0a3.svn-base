package com.nsu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Other类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: Other
 * @Package com.nsu.entity
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 10:03
 */
@Entity
public class Other {
    private long otherId;
    private Long aId;
    private Long uId;

    @Id
    @Column(name = "OTHER_ID", nullable = false)
    public long getOtherId() {
        return otherId;
    }

    public void setOtherId(long otherId) {
        this.otherId = otherId;
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

        Other other = (Other) o;

        if (otherId != other.otherId) return false;
        if (aId != null ? !aId.equals(other.aId) : other.aId != null) return false;
        if (uId != null ? !uId.equals(other.uId) : other.uId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (otherId ^ (otherId >>> 32));
        result = 31 * result + (aId != null ? aId.hashCode() : 0);
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        return result;
    }
}
