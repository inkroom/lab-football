package cn.nsu.ccl.student.entity;

import java.util.Date;
/**
 * 考试信息
 * @author 薛龙
 */
public class ExamInfo {

	private int questionLibId;//题库编号	
	private String studentID;  //考生学号	
	private String studentName;//考生姓名
	private String examName;//考试名称
	private String teacherName;//教师名称
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String keyWord;//考试口令
	private Integer examId;//考试编号
	
	public int getQuestionLibId() {
		return questionLibId;
	}
	public void setQuestionLibId(int questionLibId) {
		this.questionLibId = questionLibId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
