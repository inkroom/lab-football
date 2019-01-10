/**
 * <p>MarkDao.java文件的详细描述</p>
 * @Title: MarkDao.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:43:23
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao;

import cn.nsu.ccl.teacher.entity.Answer;

import java.util.List;
import java.util.Map;

/**
 * <p>MarkDao类的描述</p>
 * @ClassName: MarkDao
 * @Description: 学生成绩的数据的操作的接口
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:43:23
 */
public interface MarkDao {
	/**
	 * <p>getStudentGrade方法的描述</p>
	 * @Title: MarkDao的getStudentGrade方法
	 * @Description: 获取学生成绩
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:44:33
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getStudentGrade(int examId) throws Exception;
	/**
	 * <p>getAnswer方法的描述</p>
	 * @Title: MarkDao的getAnswer方法
	 * @Description: 学生考试答案
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:48:18
	 * @param StudentId
	 * @return
	 * @throws Exception
	 */
	public List<Answer> getAnswer(int studentId) throws Exception;
	/**
	 * <p>setGrade方法的描述</p>
	 * @Title: MarkDao的setGrade方法
	 * @Description: 保存成绩
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:50:00
	 * @param examId
	 * @param studentId
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	public boolean setGrade(int examId, String studentId, float grade)throws Exception;
}
