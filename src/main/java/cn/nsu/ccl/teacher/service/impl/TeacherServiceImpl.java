package cn.nsu.ccl.teacher.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nsu.ccl.teacher.dao.impl.TeacherDaoImpl;
import cn.nsu.ccl.teacher.entity.TeacherEntity;
import cn.nsu.ccl.teacher.service.TeacherService;
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
	public ArrayList<TeacherEntity> getTeachers() {
		List<Map<String, Object>> listMap = teacherDao.getTeachers();
		ArrayList<TeacherEntity> teacherEntities = new ArrayList<>();
		for(int i = 0; i < listMap.size();i++){
			TeacherEntity teacher = new TeacherEntity();
			Map<String, Object> map = listMap.get(i);
			teacher.setId(map.get("teacherUsername").toString());
			teacher.setPassword(map.get("teacherPassword").toString());
			teacher.setDepartment(map.get("departId").toString());
			teacher.setName(map.get("teacherName").toString());
			teacherEntities.add(teacher);
		}
		return teacherEntities;
	}
	
	public boolean login(String name, String password) {
		ArrayList<TeacherEntity> teacherEntities = this.getTeachers();
		for(int i = 0; i < teacherEntities.size();i++){
			TeacherEntity teacher = teacherEntities.get(i);
			if (teacher.getId().equals(name)&&teacher.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	

}
