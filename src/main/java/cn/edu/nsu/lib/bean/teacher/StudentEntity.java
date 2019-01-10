package cn.edu.nsu.lib.bean.teacher;

/**
 * 学生实体类
 */
public class StudentEntity {
    /**
     * 实验室学生信息列表展示的信息
    * creat_user: julse@qq.com creat_date: 2017/10/28
    **/
    /**
     * 学号
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年级
     */
    private String grade;
    /**
     * 专业
     */
    private String major;
    /**
     * 电话
     */
    private String tel;
    /**
     * 月考勤次数
     */
    private String frequency;
    /**
     * 以下信息是更加详细的信息
    * creat_user: julse@qq.com creat_date: 2017/10/28
    **/
    /**
     * 性别
     */
    private String gender;

    /**
     * 班级
     */
    private String stuClass;
    /**
     * 加入实验室时间
     */
    private String time;

    /**
     * 辅导员
     */
    private String instructor;

    /**
     * 所属实验室
     */
    private String lab_name;
    /**
     * 系部
     */
    private String department;
    /**
     * 获奖次数
     */
    private String prize_sum;
    /**
     * 所属实验室id
     */
    private String lab_id;

    public String getLab_id() {
        return lab_id;
    }

    public void setLab_id(String lab_id) {
        this.lab_id = lab_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getPrize_sum() {
        return prize_sum;
    }

    public void setPrize_sum(String prize_sum) {
        this.prize_sum = prize_sum;
    }
}
