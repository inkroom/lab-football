/**
 * <p>AnswerEntity.java文件的详细描述</p>
 * @Title: AnswerEntity.java
 * @Package cn.nsu.ccl.teacher.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:22:13
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>AnswerEntity类的描述</p>
 * @ClassName: AnswerEntity
 * @Description: 每一道题的属性
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:22:13
 */
public class AnswerEntity {
	private String studentNumber;			//考生学号
	private int state;						//考生的考试状态 0=为考试，1=考试中，2=考试完
	private String studentAnswer;			//考生的答案
	private String trueAnswer;				//正确答案
	private String score;					//单题回答正确的分数
	private String eachType;				//对应的题的类型
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getEachType() {
		return eachType;
	}
	public void setEachType(String eachType) {
		this.eachType = eachType;
	}
	
}
