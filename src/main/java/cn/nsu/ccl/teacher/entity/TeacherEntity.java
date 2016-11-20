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

<<<<<<< HEAD
	//教师登录帐号，唯一识别ID,邮箱帐号
	private String id;
=======
	//教师登录帐号，唯一识别ID,教师用邮箱作为唯一标识
	private String email;
>>>>>>> 39a62f519a82e0a4809e03700e32a7fd10a7a652
	//教师登录密码
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//教师所属系部
	private String department;
	//教师真实姓名
	private String name;
	
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
