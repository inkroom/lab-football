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

import org.springframework.stereotype.Repository;

import cn.nsu.ccl.comm.Envirment.ComEnviorment;
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
public class MarkDaoImpl extends ComEnviorment implements MarkDao {

	/**
	 * <p>覆盖的getStudentGrade函数</p>
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#getStudentGrade(int)
	 */
	@Override
	public List<Map<String, Object>> getStudentGrade(int examId) {
		String sql = null;
		try {
			sql = GET_SQL(new String[]{examId+""}, "call getStudentMark(?)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jt.queryForList(sql);
	}
	/**
	 * <p>覆盖的getAnswer函数</p>
	 * @param studentId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#getAnswer(int)
	 */
	@Override
	public List<Map<String, Object>> getAnswer(int studentId) {
		try {
			String sql =  GET_SQL(new String[]{studentId+""}, "call getExamInfo_Student(?)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>覆盖的setGrade函数</p>
	 * @param examId
	 * @param studentId
	 * @param grade
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.MarkDao#setGrade(int, java.lang.String, float)
	 */
	@Override
	public boolean setGrade(int examId, String studentId, float grade){
		String SQL = "{call setMark(?,?,?)}";
		return jt.update(SQL,new Object[]{examId,studentId,grade})==1;
		
	}
	
	/**
	 * 获取考试表中的examId，用于action计算成绩时做重复判断
	 */
	public ArrayList<Integer> getExamId() {
		ArrayList<Integer> list = new ArrayList<>();
		
		//生成SQL执行语句
		String SQL = "{call getExamId_Mark()}";
		List<Map<String, Object>> listMap = null;
		
		listMap = jt.queryForList(SQL);
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			int examId = (int) map.get("examId");
			list.add(examId);
		}
		
		return list;
	}
	
}
