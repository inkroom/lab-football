/**
 * <p>StudentInfoEntity.java文件的详细描述</p>
 * @Title: StudentInfoEntity.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:15:37
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>StudentInfoEntity类的描述</p>
 * @ClassName: StudentInfoEntity
 * @Description: 学生属性实体类
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:15:37
 */
public class StudentInfo {
	private String studentId;	//学生的学号
	private String studentName;		//学生的姓名
	private String teacherId;		//教师帐号
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}
