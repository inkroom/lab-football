package com.nsu.bean.index;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: UserBean
 * @Package com.nsu.bean.index
 * @Description:
 * @date 5/8/17
 */
public class UserBean {

    private String id;
    private String userName;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
