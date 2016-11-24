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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.comm.Envirment.ComEnviorment;
import cn.nsu.ccl.teacher.dao.ExamDao;
import cn.nsu.ccl.teacher.entity.ExamInfoEntity;

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
	 * 
	 * <p>覆盖的getExamInfo函数-获取全部的考试信息</p>
	 * @return
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#getExamInfo()
	 */
	public List<Map<String, Object>> getExamInfo() {
		String sql= "call getExamInfo()";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * <p>覆盖的deleExamInfo函数--通过考试id删除考试信息</p>
	 * @return
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#deleExamInfo()
	 */
	public boolean deleteExamInfo(int examId){
		String sql = "call deleteExamInfo(?)";
		return jdbcTemplate.update(sql,examId)==1;
	}

	/**
	 * <p>覆盖的addExamInfo函数-添加一个考试信息</p>
	 * @return
	 * @throws Exception 
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#addExamInfo()
	 */
	public boolean addExamInfo(ExamInfoEntity e){
		String sql = "insert into Exam( libraryId,teacherUsername,examName,keyword,"
				+ "startTime,endTime,sChoice,mChoice,tofQuestion,"
				+ "sScore,mScore,tofScore)values (?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = jdbcTemplate.update(sql,
				e.getQuestionListNumber()+"",	//题库编号（由数据库自动生成）
				e.getTeacherId(),				//教师邮箱帐号
				e.getExamName(), 				//考试名字
				"这是一个keyword的初始值#@%……￥GEY%Y~`.",//keyword的初始值
				e.getStartTime(),				//考试开始时间
				e.getEndTime(),					//考试结束时间
				e.getChoiceNumber(),			//选择题个数
				e.getMultiputeChoiceNumber(),	//多选题个数
				e.getJudgeNumber(),				//判断题个数
				e.getChoiceScore(),				//单选题分数
				e.getMultiputeChoiceScore(), 	//多选题分数
				e.getJudgeScore()				//判断题分数
				);
		return i==1;
	}




	/**
	 * <p>覆盖的editExamInfo函数--修改（更新）考试信息</p>
	 * @param examId
	 * @return
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#editExamInfo(int)
	 */
	public boolean updateExamInfoByExamId(ExamInfoEntity e){
		String sql = "update Exam set startTime = ?"
				+ ",endTime = ?"
				+ ",sChoice = ?"
				+ ",sScore = ?"
				+ ",mChoice = ?"
				+ ",mScore = ?"
				+ ",tofQuestion = ?"
				+ ",tofScore = ?"
				+ "where examId = ?";
		return jdbcTemplate.update(sql,
				e.getStartTime(),				//考试开始时间
				e.getEndTime(),					//考试结束时间
				e.getChoiceNumber(),			//单选题个数
				e.getChoiceScore(),				//单选题分数
				e.getMultiputeChoiceNumber(),	//多选题个数
				e.getMultiputeChoiceScore(),	//多选题分数
				e.getJudgeNumber(),				//判断题个数
				e.getJudgeScore(),				//判断题分数
				e.getExamId()					//考试名称
				)==1;
	}

	/**
	 * <p>覆盖的getExamState函数--通过考试id获取考试状态信息数据</p>
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#getExamState(int)
	 */
	public List<Map<String, Object>> getExaming(int examId){
		String sql = "call getExamInfo_Student(?)";
		return jdbcTemplate.queryForList(sql,examId);
	
	}

	/**
	 * <p>覆盖的updateToken函数--</p>
	 * @param token
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#updateToken(java.lang.String, int)
	 */
	public boolean updateToken(String token, int examId){
		String sql = "call setKeyword(?,?)";
		return jdbcTemplate.update(sql,examId,token)==1;
	}

	/**
	 * <p>覆盖的updateNote函数--修改（更新在考试状态表中的学生的备注信息）</p>
	 * @param note
	 * @param studentNumber
	 * @param examId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.ExamDao#updateNote(java.lang.String, java.lang.String, int)
	 */
	public boolean updateNote(String note, String studentNumber, int examId){
		String sql = "call updateRemark(?,?,?)";
		return jdbcTemplate.update(sql,examId,studentNumber,note)==1;
	}
	
	/**
	 * 
	 * <p>getExamTakingInfo方法的描述</p>
	 * @Title: ExamDaoImpl的getExamTakingInfo方法
	 * @Description: 根据考试id获取所有的考试状态信息
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午6:59:58
	 * @param examId
	 * @return
	 */
	public List<Map<String, Object>> getExamingInfo(int examId) {
		return jdbcTemplate.queryForList("{call getExamInfo_Student(?)}",new Object[]{examId});
	}

	
}
