package cn.nsu.ccl.student.dao;


public interface LoginDao {

	/**
	 * <p>更新考生的考试状态信息</p>
	 * @param studentID 考生编号
	 * @param ExamId 考试编号
	 * @param status 新的状态
	 * @return 记录成功返回true
	 * @throws Exception sql语句错误
	 */ 
	public boolean updateStatus(String studentID, String ExamId,  int status) throws Exception;

	/**
	 * <p>考生登录</p>
	 * @param studentId 考生编号
	 * @param studentName 考试编号
	 * @param keyWord 考试口令
	 * @return 登录成功返回考试编号，否则返回-1
	 * @throws Exception sql语句错误
	 */
	public int getExamId(String studentId, String studentName, String keyWord) throws Exception;

}
