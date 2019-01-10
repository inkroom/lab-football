package cn.edu.nsu.lib.bean.admin.db;

import java.math.BigInteger;

public class DbAccount {
    private BigInteger id;//登录帐号
    private String salt;
    private String passwd;
    private int identity;

    public DbAccount(){
    }

    public DbAccount(BigInteger id, int identity){
        this.id = id;
        this.identity = identity;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "DbAccount{" +
                "id=" + id +
                ", salt='" + salt + '\'' +
                ", passwd='" + passwd + '\'' +
                ", identity=" + identity +
                '}';
    }
}
