package com.nsu.bean.student.compute;


/**
 * Score bean类的描述
 *
 * @author Yjh
 * @Description: 成绩页面显示bean
 * @version: V1.0
 * @date 2017 -07-18 14:20:10
 */
public class ComputeViewBean {
    private Double Score;
    private String SchoolName;
    private String ExamName;


    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }
}
