package cn.edu.nsu.lib.bean.student;

/**
 * @author ChenGang
 * @Title: SLabBean
 * @Package cn.edu.nsu.lib.bean.student
 * @Description:
 * @date 2017/11/5 0005 下午 7:17
 **/
public class SLabBean{
    long id;//实验室id
    String  name ;//实验室名字
    String labDescribe;//实验室简
    long  qqGrop;//qq群
    String address;//实验室地址

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
}
