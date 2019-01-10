package cn.nsu.edu.web.four.beans.organization;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author :王新璋
 * @Description: 机构的实体类
 * @date :18.01 2018/3/16
 */

public class Organization {

    @NotNull
    private Integer idOrg;//机构id

    private String name;//机构姓名

    @NotNull
    private String password;//密码
    private String salt;//加密

    @NotNull
    private Integer schoolCode;//学校代码
    private Integer provinceCode;//省代码
    private Integer countryCode;//城市代码
    private Integer villageCode;//乡村代码
    private Integer type;//机构类型
    @NotNull
    private Long phone;//机构手机号

    private Integer status;//机构是否激活（后台是否注册）



    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(Integer villageCode) {
        this.villageCode = villageCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "organization{" +
                "idOrg=" + idOrg +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", schoolCode=" + schoolCode +
                ", provinceCode=" + provinceCode +
                ", countryCode=" + countryCode +
                ", villageCode=" + villageCode +
                ", type=" + type +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }
}
