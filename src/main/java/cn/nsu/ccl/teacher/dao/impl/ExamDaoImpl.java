/**
 * <p>ExamDaoImpl.java文件的详细描述</p>
 * @Title: ExamDaoImpl.java
 * @Package cn.nsu.ccl.teacher.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:47:03
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.comm.Envirment.ComEnviorment;
import cn.nsu.ccl.teacher.dao.ExamDao;
import cn.nsu.ccl.teacher.entity.ExamInfoEntity;
import cn.nsu.ccl.teacher.entity.ExamingInfoEntity;

/**
 * <p>ExamDaoImpl类的描述</p>
 * @ClassName: ExamDaoImpl
 * @Description: 实现与数据库之间关于考试属性类的相关操作（增删查改）
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:47:03
 */
@Repository
public class ExamDaoImpl extends ComEnviorment implements ExamDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * <p>覆盖的getExamInfo函数</p>
	 * @return
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#getExamInfo()
	 */
	public List<Map<String, Object>> getExamInfo() {
		String sql= "call getExamInfo()";
		return jt.queryForList(sql);
	}

	/**
	 * <p>覆盖的deleExamInfo函数</p>
	 * @return
	 * @throws Exception 
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#deleExamInfo()
	 */
	@Override
	public boolean deleteExamInfo(int examId) throws Exception {
		// TODO Auto-generated method stub
		String sql=GET_SQL(new String[]{examId+""}, "call deleteExamInfo(?)");
		return jt.update(sql)==1;
	}

	/**
	 * <p>覆盖的addExamInfo函数</p>
	 * @return
	 * @throws Exception 
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#addExamInfo()
	 */
	@Override
	public boolean addExamInfo(ExamInfoEntity e) throws Exception {
		// TODO Auto-generated method stub
		
			String sql= GET_SQL(new String[]{
					e.getExamName(), 				//考试名字
					e.getTeacherId(),				//教师编号
					e.getQuestionListNumber()+"",	//题库编号（由数据库自动生成）
					e.getChoiceNumber(),			//选择题个数
					e.getMultiputeChoiceNumber(),	//多选题个数
					e.getJudgeNumber(),				//判断题个数
					e.getChoiceScore(),				//选择题分数
					e.getJudgeScore(),				//判断题分数
					e.getMultiputeChoiceScore(),	//多选题分数
					e.getStartTime(),				//考试开始时间
					e.getEndTime()},				//考试结束时间
					"call setExamInfo('?','?','?','?','?','?','?','?','?','?','?')");
					return jt.update(sql)==1;
	}




	/**
	 * <p>覆盖的editExamInfo函数</p>
	 * @param examId
	 * @return
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#editExamInfo(int)
	 */
	@Override
	public boolean editExamInfo(ExamInfoEntity e) throws Exception{
		// TODO Auto-generated method stub
		String sql= GET_SQL(new String[]{
				e.getExamName(), 				//考试名字
				e.getTeacherId(),				//教师编号
				e.getQuestionListNumber()+"",	//题库编号（由数据库自动生成）
				e.getChoiceNumber(),			//选择题个数
				e.getMultiputeChoiceNumber(),	//多选题个数
				e.getJudgeNumber(),				//判断题个数
				e.getChoiceScore(),				//选择题分数
				e.getJudgeScore(),				//判断题分数
				e.getMultiputeChoiceScore(),	//多选题分数
				e.getStartTime(),				//考试开始时间
				e.getEndTime()},				//考试结束时间
		 "call editExamInfo('?','?','?','?','?','?','?','?','?','?','?')");
		return jt.update(sql)==1;
	}

	/**
	 * <p>覆盖的getExamState函数</p>
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#getExamState(int)
	 */
	@Override
	public List<Map<String, Object>> getExamState(int examId) throws Exception {
		// TODO Auto-generated method stub
		String sql = GET_SQL(new String[]{examId+""}, "call getExamInfo_Student(?)");
		return jt.queryForList(sql);
	
	}

	/**
	 * <p>覆盖的updateToken函数</p>
	 * @param token
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#updateToken(java.lang.String, int)
	 */
	@Override
	public boolean updateToken(String token, int examId){
		String sql = null;
		try {
			sql = GET_SQL(new String[]{examId+"",token}, "call setKeyword(?,?)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jt.update(sql)==1;
	}

	/**
	 * <p>覆盖的updateNote函数</p>
	 * @param note
	 * @param studentNumber
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#updateNote(java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean updateNote(String note, String studentNumber, int examId){
		String sql = null;
		try {
			sql = GET_SQL(new String[]{examId+"", studentNumber,note},"call updateRemark(?,?,?)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jt.update(sql)==1;
	}
	
	/**
	 * 获取考试状态信息
	 */
	public ArrayList<ExamingInfoEntity> getExamTakingInfo(int examId) {
		List<Map<String, Object>> listMap = null;
		ArrayList<ExamingInfoEntity> list = new ArrayList<ExamingInfoEntity>();
		listMap = jdbcTemplate.queryForList("{call getExamInfo_Student(?)}",new Object[]{examId});
		for (int i = 0; i < listMap.size(); i++) {
			ExamingInfoEntity examTakingInfo = new ExamingInfoEntity();
			Map<String, Object> map = listMap.get(i);
			examTakingInfo.setStudentNumber(map.get("studentId").toString());
			examTakingInfo.setStudentName(map.get("studentName").toString());
			examTakingInfo.setState(map.get("studentStatus").toString());
			examTakingInfo.setBrowserInfo(map.get("browser").toString());
			examTakingInfo.setIp(map.get("ipAddr").toString());
			examTakingInfo.setNote(map.get("remark")+"");
			list.add(examTakingInfo);
		}
		return list;
	
	}

	
}
