package cn.nsu.ccl.teacher.entity;

/**
 * 
 * <p>TeacherEntity类的描述</p>
 * @ClassName: TeacherEntity
 * @Description: 教师属性实体类
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午5:52:45
 */
public class TeacherEntity {

	//教师登录帐号，唯一识别ID
	private String id;
	//教师登录密码
	private String password;
	//教师所属系部
	private String department;
	//教师真实姓名
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
