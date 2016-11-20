/**
 * <p>ExamDao.java文件的详细描述</p>
 * @Title: ExamDao.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:36:40
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao;
import java.util.List;
import java.util.Map;

import cn.nsu.ccl.teacher.entity.ExamInfoEntity;

/**
 * <p>ExamDao类的描述</p>
 * @ClassName: ExamDao
 * @Description: 考试信息表的增删查改的实现接口(不包括学生信息修改)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:36:40
 */

public interface ExamDao {
	
	/**
	 * <p>getExamInfo方法的描述</p>
	 * @Title: ExamDao的getExamInfo方法
	 * @Description: 显示全部考试信息
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 上午10:33:31
	 * @return
	 */
	public List<Map<String, Object>> getExamInfo() throws Exception;
	
	/**
	 * <p>addExamInfo方法的描述</p>
	 * @Title: ExamDao的addExamInfo方法
	 * @Description: 创建考试,不包括学生信息
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 上午10:33:38
	 * @param e
	 * @return
	 */
	public boolean addExamInfo(ExamInfoEntity e) throws Exception;
	/**
	 * <p>editExamInfo方法的描述</p>
	 * @Title: ExamDao的editExamInfo方法
	 * @Description: 修改考试
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 上午10:33:41
     * @param e
	 * @return
	 */
	public boolean editExamInfo( ExamInfoEntity e) throws Exception;
	/**
	 * <p>deleExamInfo方法的描述</p>
	 * @Title: ExamDao的deleExamInfo方法
	 * @Description:删除考试
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 上午10:38:44
	 * @param examId
	 * @return
	 */
	public boolean deleteExamInfo(int examId) throws Exception;
	
	/**
	 * <p>getExamState方法的描述</p>
	 * @Title: ExamDao的getExamState方法
	 * @Description: 获取考试状态信息
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:29:29
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExamState(int examId) throws Exception;
	/**
	 * <p>updateToken方法的描述</p>
	 * @Title: ExamDao的updateToken方法
	 * @Description: 更新教师口令
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:30:09
	 * @param token
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public boolean updateToken(String token,int examId) throws Exception;
	/**
	 * <p>updateNote方法的描述</p>
	 * @Title: ExamDao的updateNote方法
	 * @Description: 更新备注
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:31:44
	 * @param note
	 * @param studentNumber
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public boolean updateNote(String note,String studentNumber, int examId)throws Exception;
}
