/**
 * <p>ExamInfoEntity.java文件的详细描述</p>
 * @Title: ExamInfoEntity.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:15:00
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>ExamInfoEntity类的描述</p>
 * @ClassName: ExamInfoEntity
 * @Description: 试卷实体类
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:15:00
 */
public class ExamInfo {
	private int examId;						//考试编号（由数据库自动生成）
	private String examName;				//考试名字
	private String teacherId;				//教师编号
	private String studentNumber;			//学生学号                 
	private String studentName;				//学生姓名
	private int questionListNumber;			//题库编号（由数据库自动生成）
	private String choiceNumber;			//单选题个数
	private String multiputeChoiceNumber;	//多选题个数
	private String judgeNumber;				//判断题个数
	private String choiceScore;				//单选题分数
	private String judgeScore;				//判断题分数
	private String multiputeChoiceScore;	//多选题分数
	private String startTime;				//考试开始时间
	private String endTime;					//考试结束时间
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getQuestionListNumber() {
		return questionListNumber;
	}
	public void setQuestionListNumber(int questionListNumber) {
		this.questionListNumber = questionListNumber;
	}
	public String getChoiceNumber() {
		return choiceNumber;
	}
	public void setChoiceNumber(String choiceNumber) {
		this.choiceNumber = choiceNumber;
	}
	public String getMultiputeChoiceNumber() {
		return multiputeChoiceNumber;
	}
	public void setMultiputeChoiceNumber(String multiputeChoiceNumber) {
		this.multiputeChoiceNumber = multiputeChoiceNumber;
	}
	public String getJudgeNumber() {
		return judgeNumber;
	}
	public void setJudgeNumber(String judgeNumber) {
		this.judgeNumber = judgeNumber;
	}
	public String getChoiceScore() {
		return choiceScore;
	}
	public void setChoiceScore(String choiceScore) {
		this.choiceScore = choiceScore;
	}
	public String getJudgeScore() {
		return judgeScore;
	}
	public void setJudgeScore(String judgeScore) {
		this.judgeScore = judgeScore;
	}
	public String getMultiputeChoiceScore() {
		return multiputeChoiceScore;
	}
	public void setMultiputeChoiceScore(String multiputeChoiceScore) {
		this.multiputeChoiceScore = multiputeChoiceScore;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
