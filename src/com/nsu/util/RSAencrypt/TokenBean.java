package com.nsu.util.RSAencrypt;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.util.RSAencrypt
 * @Description:
 * @date 17/4/20
 */
public class TokenBean  implements Serializable {
    private static final long serialVersionUID = -6193310436318894856L;

    public TokenBean(String username, String password, String IMEI) {
        this.username = username;
        this.password = password;
        this.IMEI = IMEI;
    }

    private String username;
    private String password;
    private String IMEI;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "TokenBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", date=" + date +
                '}';
    }
}
