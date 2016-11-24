/**
 * <p>StudentDao.java文件的详细描述</p>
 * @Title: StudentDao.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:39:36
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao;

import java.util.List;
import java.util.Map;

import cn.nsu.ccl.teacher.entity.StudentInfoEntity;

/**
 * <p>StudentDao类的描述</p>
 * @ClassName: StudentDao
 * @Description:学生表的增删查的实现接口
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:39:36
 */
public interface  StudentDao {
	
	/**
	 * <p>addStudentInfo方法的描述</p>
	 * @Title: StudentDao的addStudentInfo方法
	 * @Description: 增加考试信息中的学生信息和老师信息（for循环）
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午12:07:36
	 * @param studentInfo
	 * @param teacherId
	 * @return
	 */
	public boolean addStudentInfo(int examId,String teacherEmail,StudentInfoEntity studentInfo);
	/**
	 * <p>getStudentInfo方法的描述</p>
	 * @Title: StudentDao的getStudentInfo方法
	 * @Description: 获取学生信息（考试信息表中）
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午2:06:05
	 * @param examId
	 * @return
	 */
	public List<Map<String, Object>> getStudentInfo(int examId) throws Exception;
	
}
