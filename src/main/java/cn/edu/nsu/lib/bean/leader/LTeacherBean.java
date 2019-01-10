package cn.edu.nsu.lib.bean.leader;

import java.util.List;

/**
 * @author ChenGang
 * @Title: LteacherBean
 * @Package cn.edu.nsu.lib.bean.leader
 * @Description:老师的bean
 * @date 2017/11/4 0004 上午 11:29
 **/
public class LTeacherBean{
    private  long id;//老师id
    private String name;//老师姓名
    private int gender;//性别 0男 1女
    private long tel ;//老师电话;
    private List<LTLabBean> ltLabBeans;

    public List<LTLabBean> getLtLabBeans() {
        return ltLabBeans;
    }

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public void setLtLabBeans(List<LTLabBean> ltLabBeans) {
        this.ltLabBeans = ltLabBeans;
    }
}
