package cn.nsu.edu.web.four.beans.match;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.beans.match
 * @date 2018/3/23 13:42
 * @description
 **/
public class MatchExamine {
    private Integer orgId;
    private String orgName;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    @Override
    public String toString() {
        return "MatchExamine{" +
                "orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
