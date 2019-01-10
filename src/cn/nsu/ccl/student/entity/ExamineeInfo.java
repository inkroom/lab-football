package cn.nsu.ccl.student.entity;
/**
 * 考生信息
 * 包括某场考试状态
 * @author 薛龙
 */
public class ExamineeInfo {
	
	private String studentID;		//考生ID

	private String studentName;	//考生姓名
	
	private Integer examStatus;		//考试状态
	
	private Integer examId;			//考试ID号



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

	public Integer getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(Integer examStatus) {
		this.examStatus = examStatus;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	
}
