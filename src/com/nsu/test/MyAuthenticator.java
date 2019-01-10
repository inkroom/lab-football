package com.nsu.test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: MyAuthenticator
 * @Package com.nsu.test
 * @Description:
 * @date 4/25/17
 */
public class MyAuthenticator extends Authenticator {
    String userName="";
    String password="";
    public MyAuthenticator(){

    }
    public MyAuthenticator(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.userName, password);
    }
}
