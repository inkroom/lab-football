package cn.edu.nsu.lib.bean.leader;

import java.util.ArrayList;

/**
 * @author ChenGang
 * @Title: LlabBean
 * @Package cn.edu.nsu.lib.bean.leader
 * @Description:实验室bean
 * @date 2017/11/4 0004 上午 11:37
 **/
public class LLabBean{
    long id;//实验室id
    String  name ;//实验室名字
    String labDescribe;//实验室简
    long  qqGrop;//qq群
    String address;//实验室地址
    ArrayList<LTeacherBean> lTeacherBeans;//实验室下属老师列表
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabDescribe() {
        return labDescribe;
    }

    public void setLabDescribe(String labDescribe) {
        this.labDescribe = labDescribe;
    }

    public long getQqGrop() {
        return qqGrop;
    }

    public void setQqGrop(long qqGrop) {
        this.qqGrop = qqGrop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public ArrayList<LTeacherBean> getlTeacherBeans() {
        return lTeacherBeans;
    }

    public void setlTeacherBeans(ArrayList<LTeacherBean> lTeacherBeans) {
        this.lTeacherBeans = lTeacherBeans;
    }

    @Override
    public String toString() {
        return "LLabBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", labDescribe='" + labDescribe + '\'' +
                ", qqGrop=" + qqGrop +
                ", address='" + address + '\'' +
                ", lTeacherBeans=" + lTeacherBeans +
                '}';
    }
}
