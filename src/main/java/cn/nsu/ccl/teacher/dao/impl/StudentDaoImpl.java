/**
 * <p>StudentDaoImpl.java文件的详细描述</p>
 * @Title: StudentDaoImpl.java
 * @Package cn.nsu.ccl.teacher.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:40:01
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.teacher.dao.StudentDao;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;

/**
 * <p>StudentDaoImpl类的描述</p>
 * @ClassName: StudentDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:40:01
 */
@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * <p>覆盖的addStudentInfo函数
	 * 需要传入的参数有：examId（考试id），teacherEmail（教师邮箱）,studentInfoEntity（学生信息）
	 * </p>
	 * @param studentInfo
	 * @param teacherId
	 * @return
	 * @throws Exception 
	 * @see cn.nsu.ccl.teacher.dao.StudentDao#addStudentInfo(cn.nsu.ccl.teacher.entity.StudentInfoEntity, java.lang.String)
	 */
	public boolean addStudentInfo(int examId,String teacherEmail,StudentInfoEntity studentInfo){
		String sql = "call setStudentInfo(?,?,?,?)";
		return jdbcTemplate.update(sql,examId,teacherEmail,studentInfo.getStudentId(),studentInfo.getStudentName())==-1;
	}

	/**
	 * <p>覆盖的getStudentInfo函数</p>
	 * @param examId
	 * @return
	 * @throws Exception 
	 * @see cn.nsu.ccl.teacher.dao.StudentDao#getStudentInfo(int)
	 */
	@Override
	public List<Map<String, Object>> getStudentInfo(int examId) {
		String sql = "call getStudentInfo(?)";
		return jdbcTemplate.queryForList(sql,examId);
	}

}
