/**
 * <p>MarkDaoImpl.java文件的详细描述</p>
 * @Title: MarkDaoImpl.java
 * @Package cn.nsu.ccl.teacher.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:52:33
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.teacher.dao.MarkDao;

/**
 * <p>MarkDaoImpl类的描述</p>
 * @ClassName: MarkDaoImpl
 * @Description: 成绩实现接口
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:52:33
 */
@Repository
public class MarkDaoImpl implements MarkDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * <p>覆盖的getStudentGrade函数</p>
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#getStudentGrade(int)
	 */
	@Override
	public List<Map<String, Object>> getStudentGrade(int examId) {
		String sql = "call getStudentMark(?)";
		return jdbcTemplate.queryForList(sql,examId);
	}
	/**
	 * <p>覆盖的getAnswer函数
	 * 通过考试id获取考生答案
	 * </p>
	 * @param studentId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#getAnswer(int)
	 */
	public List<Map<String, Object>> getAnswer(int studentId) {
		
		String sql = "call getExamInfo_Student(?)";
		return jdbcTemplate.queryForList(sql,studentId);
	}

	/**
	 * <p>覆盖的setGrade函数
	 * 存入考生的单场考试成绩
	 * </p>
	 * @param examId
	 * @param studentId
	 * @param grade
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#setGrade(int, java.lang.String, float)
	 */
	public boolean setGrade(int examId, String studentId, float grade){
		String sql = "call setMark(?,?,?)";
		return jdbcTemplate.update(sql,examId,studentId,grade)==1;
		
	}
	
	/**
	 * 获取考试表中的examId，用于controller中计算成绩时做重复判断
	 */
	public ArrayList<Integer> getExamId() {
		ArrayList<Integer> list = new ArrayList<>();
		
		//生成SQL执行语句
		String SQL = "call getExamId_Mark()";
		List<Map<String, Object>> listMap = null;
		
		listMap = jdbcTemplate.queryForList(SQL);
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			int examId = (int) map.get("examId");
			list.add(examId);
		}
		
		return list;
	}
	
}
