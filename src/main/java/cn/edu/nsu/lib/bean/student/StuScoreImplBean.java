package cn.edu.nsu.lib.bean.student;

/**
 * @author ChenGang
 * @Title: StuScoreImplBean
 * @Package cn.edu.nsu.lib.bean.student
 * @Description:
 * @date 2017/10/31 0031 下午 3:57
 **/
public class StuScoreImplBean{
    String course;
    int one1;
    int one2;
    int two1;
    int two2;
    int three1;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getOne1() {
        return one1;
    }

    public void setOne1(int one1) {
        this.one1 = one1;
    }

    public int getOne2() {
        return one2;
    }

    public void setOne2(int one2) {
        this.one2 = one2;
    }

    public int getTwo1() {
        return two1;
    }

    public void setTwo1(int two1) {
        this.two1 = two1;
    }

    public int getTwo2() {
        return two2;
    }

    public void setTwo2(int two2) {
        this.two2 = two2;
    }

    public int getThree1() {
        return three1;
    }

    public void setThree1(int three1) {
        this.three1 = three1;
    }

    public int getThree2() {
        return three2;
    }

    public void setThree2(int three2) {
        this.three2 = three2;
    }

    int three2;

    @Override
    public String toString() {
        return "StuScoreImplBean{" +
                "course='" + course + '\'' +
                ", one1=" + one1 +
                ", one2=" + one2 +
                ", two1=" + two1 +
                ", two2=" + two2 +
                ", three1=" + three1 +
                ", three2=" + three2 +
                '}';
    }
}
