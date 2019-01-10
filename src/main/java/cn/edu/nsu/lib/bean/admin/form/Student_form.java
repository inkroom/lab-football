package cn.edu.nsu.lib.bean.admin.form;

/**
 * Created by 王振科 on 2017/9/27.
 */
public class Student_form {
    /**
     * db_Student和Student_form区别：
     *  Student_form用的是       private String lab_name;//实验室名字
                                private String major;//专业名字

     db_Student用的是      private int lab_id;//实验室id
                            private int maj_id;//专业id

     *
     */

    private String student_id;//BigInteger
    private String name;
    private String gender;
    private String grade;//int
    private String time;
    private String lab_name;//实验室名字
    private String major;//专业名字（之后会找到id）
    private String instructor;//指导老师
    private String tel;//BigInteger
    private String IDcard;//身份证
    private String stuClass;//班级
    private String department;//宿舍楼
    private String outTime;//退出实验室的时间

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Student_form{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", grade='" + grade + '\'' +
                ", time='" + time + '\'' +
                ", lab_name='" + lab_name + '\'' +
                ", major='" + major + '\'' +
                ", instructor='" + instructor + '\'' +
                ", tel='" + tel + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", department='" + department + '\'' +
                ", outTime='" + outTime + '\'' +
                '}';
    }
}
