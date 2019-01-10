package cn.edu.nsu.lib.bean.student;
import java.sql.Date;
public class StuBasicBean {
    /*基础信息bean*/

    private long id ; //学号
    private String name;//学生姓名
    private int gender;  //性别
    private int grade;   //年级
    private Date time;	//加入实验室时间
    private int lab_id;	//实验室名称
    private int maj_id;   //专业
    private long tel;    //电话
    private String instructor;   //辅导员
    private String IDcard;//身份证
    private int stuClass;//班级
    private String  department;//系
    private String highSchool;//高中毕业院校
    private String outTime;//离开实验室时间
    private String region;//地区
    private String labName;
    private String majorName;

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public int getstuClass() {
        return stuClass;
    }

    public void setstuClass(int stuClass) {
        this.stuClass = stuClass;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "StuBasicBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                ", time=" + time +
                ", lab_id=" + lab_id +
                ", maj_id=" + maj_id +
                ", tel=" + tel +
                ", instructor='" + instructor + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", stuClass=" + stuClass +
                ", department='" + department + '\'' +
                ", highSchool='" + highSchool + '\'' +
                ", outTime='" + outTime + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
