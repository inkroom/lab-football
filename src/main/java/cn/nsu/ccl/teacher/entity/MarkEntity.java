/**
 * <p>MarkEntity.java文件的详细描述</p>
 * @Title: MarkEntity.java
 * @Package cn.nsu.ccl.teacher.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:12:34
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>MarkEntity类的描述</p>
 * @ClassName: MarkEntity
 * @Description: 学生成绩实体类
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:12:34
 */
public class MarkEntity {
	private int examId;				//考试编号
	private String examName;		//考试名称
	private float mark;				//成绩
	private String studentNumber;	//学生的学号
	private String studentName;		//学生的名字
	private String ip;				//学生的ip地址
	private String studentAnswer;	//学生答案
	private String trueAnswer;		//正确答案
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
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	public String getTrueAnswer() {
		return trueAnswer;
	}
	public void setTrueAnswer(String trueAnswer) {
		this.trueAnswer = trueAnswer;
	}
	

}
