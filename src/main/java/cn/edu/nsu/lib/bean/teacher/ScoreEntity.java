package cn.edu.nsu.lib.bean.teacher;

public class ScoreEntity {
    /**
     * 学期
     */
    private String term;
    /**
     * 课程
     */
    private String course;
    /**
     * 成绩
     */
    private String score;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
