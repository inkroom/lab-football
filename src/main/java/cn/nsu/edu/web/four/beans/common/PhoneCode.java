package cn.nsu.edu.web.four.beans.common;

import java.util.Date;

public class PhoneCode {

    private String phone;//所属手机号
    private String code;//验证码
    private int count = 0;//已验证次数
    private Date sendTime;

    public PhoneCode(String phone, String code) {
        sendTime = new Date();
        this.phone = phone;
        this.code = code;
        count = 0;
    }

    public PhoneCode() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public String getPhone() {
        return phone;
    }


    public String getCode() {
        return code;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", count=" + count +
                ", sendTime=" + sendTime +
                '}';
    }
}
