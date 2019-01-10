/**
 * <p>Examing.java文件的详细描述</p>
 * @Title: Examing.java
 * @Package cn.nsu.ccl.teacher.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午12:05:35
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>Examing类的描述</p>
 * @ClassName: Examing
 * @Description: 考试状态实体类
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午12:05:35
 */
public class ExamingInfo {
	private String studentNumber;	//学生的学号
	private String studentName;		//学生的姓名
	private String ip;				//学生的IP地址
	private String browserInfo;		//浏览器信息
	private String isLack;			//学生的缺考状态
	private String state;			//学生的考试状态
	private String note;			//备注
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
	public String getBrowserInfo() {
		return browserInfo;
	}
	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}
	public String getIsLack() {
		return isLack;
	}
	public void setIsLack(String isLack) {
		this.isLack = isLack;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
