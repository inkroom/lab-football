package cn.nsu.ccl.teacher.service.impl;

import cn.nsu.ccl.teacher.dao.impl.TeacherDaoImpl;
import cn.nsu.ccl.teacher.entity.Teacher;
import cn.nsu.ccl.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 描述：实现与教师属性类的相关操作（查询）
 * 开发人员：暴沸
 * 开发时间： 2016年11月17日 下午4:17:55
 * 联系方式：admin@baofeidyz.com 
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDaoImpl teacherDao;

	/**
	 * 
	 * 描述：获取所有的教师信息
	 * 开发人员：暴沸
	 * 开发时间： 2016年11月17日 下午4:17:34
	 * 联系方式：admin@baofeidyz.com 
	 *
	 * @return
	 */
	public ArrayList<Teacher> getTeachers() {
		List<Map<String, Object>> listMap = teacherDao.getTeachers();
		ArrayList<Teacher> teacherEntities = new ArrayList<>();
		for(int i = 0; i < listMap.size();i++){
			Teacher teacher = new Teacher();
			Map<String, Object> map = listMap.get(i);
			teacher.setEmail(map.get("teacherUsername").toString());
			teacher.setPassword(map.get("teacherPassword").toString());
			teacher.setDepartment(map.get("departId").toString());
			teacher.setName(map.get("teacherName").toString());
			teacherEntities.add(teacher);
		}
		return teacherEntities;
	}
	/**
	 * 
	 * <p>覆盖的login函数，完成教师登录逻辑</p>
	 * @param name
	 * @param password
	 * @return
	 * @see cn.nsu.ccl.teacher.service.TeacherService#login(String, String)
	 */
	public boolean login(String name, String password) {
		ArrayList<Teacher> teacherEntities = this.getTeachers();
		for(int i = 0; i < teacherEntities.size();i++){
			Teacher teacher = teacherEntities.get(i);
			if (teacher.getEmail().equals(name)&&teacher.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean updatePassword(String teacherEmail,String oldPasswd,String newPasswd){
		//验证原密码是否匹配
		if (this.login(teacherEmail, oldPasswd)) {
			//匹配则进行密码更新
			return teacherDao.updatePassword(teacherEmail, newPasswd);
		}
		return false;
	}

}
