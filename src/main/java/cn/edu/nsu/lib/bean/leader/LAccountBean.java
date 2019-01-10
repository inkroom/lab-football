package cn.edu.nsu.lib.bean.leader;

/**
 * @author ChenGang
 * @Title: LAccontBean
 * @Package cn.edu.nsu.lib.bean.leader
 * @Description:
 * @date 2017/11/6 0006 下午 7:41
 **/
public class LAccountBean{
    private long id;
    private int gender;
    private long tel;
    private String name;
    private String salt;
    private String  passwd;
    private Integer identity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "LAccountBean{" +
                "id=" + id +
                ", gender=" + gender +
                ", tel=" + tel +
                ", name='" + name + '\'' +
                ", salt='" + salt + '\'' +
                ", passwd='" + passwd + '\'' +
                ", identity=" + identity +
                '}';
    }
}
