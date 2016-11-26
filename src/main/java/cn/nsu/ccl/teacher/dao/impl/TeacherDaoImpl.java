package cn.nsu.ccl.teacher.dao.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.teacher.dao.TeacherDao;

/**
 * 
 * 描述：实现与数据库之间关于教师属性类的相关操作（查询）
 * 开发人员：暴沸
 * 开发时间： 2016年11月17日 下午4:18:21
 * 联系方式：admin@baofeidyz.com 
 *
 */

@Repository
public class TeacherDaoImpl implements TeacherDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * <p>getTeachers方法的描述</p>
	 * @Title: TeacherDaoImpl的getTeachers方法
	 * @Description: 查询教师信息
	 * @author 2213974854@qq.com
	 * @date 2016年11月18日 下午3:32:58
	 * @return
	 */
	public List<Map<String, Object>> getTeachers() {
		String SQL = "select * from Teacher";
		return jdbcTemplate.queryForList(SQL);
	}

	public boolean updatePassword(String teacherEmail,String newPassword){
		System.out.println("dao="+newPassword);
		String sql = "update Teacher set teacherPassword = ? where teacherUsername = ?";
		return jdbcTemplate.update(sql,newPassword,teacherEmail)==1;
	}


}
