package cn.edu.nsu.lib.bean.admin.db;

import java.math.BigInteger;

public class DbStudent {
    /**
     * 学号存取用这个类型 ：BigInteger
     * 前端数据类型用text只输入数字的时候可以自动转换为整形数据进行存入
     * String类型可以获取到前端页面数据类型为date的数据
     * mysql中date对应的java类型是string类型
     */
    private BigInteger id;//学号
    private String name;//名字
    private int gender; //性别
    private int grade;//年级
    private String time;//加实验室时间
    private int lab_id;//实验室id
    private int maj_id;//专业id
    private String instructor;//辅导员名字
    private BigInteger tel;//电话
    private String IDcard;//身份证
    private int stuClass;//班级
    private String department;//宿舍楼
    private String outTime;//退出实验室的时间

    private String lab_name;//实验室名字
    private String maj_name;//专业名字

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getMaj_name() {
        return maj_name;
    }

    public void setMaj_name(String maj_name) {
        this.maj_name = maj_name;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public int getStuClass() {
        return stuClass;
    }

    public void setStuClass(int stuClass) {
        this.stuClass = stuClass;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public BigInteger getTel() {
        return tel;
    }

    public void setTel(BigInteger tel) {
        this.tel = tel;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public int getMaj_id() {
        return maj_id;
    }

    public void setMaj_id(int maj_id) {
        this.maj_id = maj_id;
    }

    @Override
    public String toString() {
        return "DbStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                ", time='" + time + '\'' +
                ", lab_id=" + lab_id +
                ", maj_id=" + maj_id +
                ", instructor='" + instructor + '\'' +
                ", tel=" + tel +
                ", IDcard='" + IDcard + '\'' +
                ", stuClass=" + stuClass +
                ", department='" + department + '\'' +
                ", outTime='" + outTime + '\'' +
                '}';
    }
}
