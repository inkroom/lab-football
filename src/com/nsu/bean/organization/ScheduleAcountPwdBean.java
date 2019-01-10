package com.nsu.bean.organization;

import com.nsu.util.InfoProtUtil;

import java.io.Serializable;

/**
 * Created by Jerry on 17/6/19.
 */

public class ScheduleAcountPwdBean implements Serializable {
    private  String a_username;
    private  String a_password;

    public ScheduleAcountPwdBean() {
    }

    public String getA_username() {
        return a_username;
    }

    public void setA_username(String a_username) {
        this.a_username = InfoProtUtil.xorInfo(a_username);
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {


        this.a_password = InfoProtUtil.parseStrToMd5L32(a_password);
    }

    @Override
    public String toString() {
        return "ScheduleAcountPwdBean{" +
                "a_username='" + a_username + '\'' +
                ", a_password='" + a_password + '\'' +
                '}';
    }
}
