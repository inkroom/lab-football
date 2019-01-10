package cn.edu.nsu.lib.bean.student;

public class StuScoreBean {
    /*成绩信息bean*/
    private long stu_id; //学号
    private int grade; //成绩
    private String major_name; //专业名
    private String courseName; //课程名
    private int term; //学期 101 大一 102 大二 103 大三

    public long getStu_id() {
        return stu_id;
    }

    public void setStu_id(long stu_id) {
        this.stu_id = stu_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }



    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
