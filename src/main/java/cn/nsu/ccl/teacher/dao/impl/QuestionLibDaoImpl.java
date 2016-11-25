/**
 * <p>QuestionListDaoImpl.java文件的详细描述</p>
 * @Title: QuestionListDaoImpl.java
 * @Package cn.nsu.ccl.teacher.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:49:40
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
import cn.nsu.ccl.teacher.dao.QuestionLibDao;
import cn.nsu.ccl.teacher.entity.QuestionEntity;

/**
 * <p>QuestionListDaoImpl类的描述</p>
 * @ClassName: QuestionListDaoImpl
 * @Description: TODO(实现与数据库之间关于题库属性类的相关操作（增删查）)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:49:40
 */
@Repository
public class QuestionLibDaoImpl extends ComEnviorment implements QuestionLibDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * <p>getQuestionlib方法的描述</p>
	 * @Title: QuestionListDao的getQuestionlib方法
	 * @Description: 根据教师ID获得该教师创建的题库集合
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午3:20:00
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestionLibList(){
		String sql = "call getLibraryInfo()";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * <p>addQuestionList方法的描述</p>
	 * @Title: QuestionListDao的addQuestionList方法
	 * @Description: 根据题库名字和教师ID创建题库列表信息
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午3:08:37
	 * @param q
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestionLib(String questionLibName, String teacherId){
		String sql = "call createLibrary('?','?')";
		return jdbcTemplate.update(sql,teacherId,questionLibName)==1;
	}

	/**
	 * <p>deletQuestionList方法的描述</p>
	 * @Title: QuestionListDao的deletQuestionList方法
	 * @Description:根据题库编号删除题库
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午3:14:21
	 * @param libraryId
	 * @return
	 * @throws Exception
	 */
	public boolean deletQuestionLib(int libraryId){
		String sql = "call deleteLibrary(?)";
		return jdbcTemplate.update(sql,libraryId)==1;
	}

	/**
	 * <p>getQuestions方法的描述</p>
	 * @Title: QuestionListDao的getQuestions方法
	 * @Description: 根据题库ID获取该题库下的所有题目
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午3:23:15
	 * @param libraryId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestions(int libraryId){
		String sql = "select * from QuestionList where libraryId = ?";
		return jdbcTemplate.queryForList(sql,libraryId);
	}

	/**
	 * <p>addQuestion方法的描述</p>
	 * @Title: QuestionLibDao的addQuestion方法
	 * @Description:将题目集合添加到题库中（操作题库中的题目集合）
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:24:04
	 * @param questionList
	 * @param questionLibId
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestion(ArrayList<QuestionEntity> questionList, int questionLibId){
		String sql ="call insertQuestion('?',?,'?','?','?','?')";
		int j =-1;
		for (int i = 0; i < questionList.size(); i++) {
			QuestionEntity question= questionList.get(i);
			j = jdbcTemplate.update(sql,
					(i+1)+"", 							//题号
					questionLibId+"",					//题库编号
					question.getQuestionContent(),		//提干
					question.getChoice(),				//备选答案
					question.getAnswer(),				//正确答案
					question.getType()					//题目类型
					);
			if (j==0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>getQuestionLibId方法的描述</p>
	 * @Title: QuestionLibDaoImpl的getQuestionLibId方法
	 * @Description: 通过教师邮箱和题库名字获取题库id
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午8:42:40
	 * @param teacherId
	 * @return
	 */
	public int getQuestionLibId(String questionLibName,String teacherEmail) {
		String sql = "select * from QuestionMapping where libraryName = ? and teacherUsername = ?";
		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql,questionLibName,teacherEmail);
		if (listMap.size()==0) {
			return -1;
		}
		Map map = listMap.get(0);
		return Integer.parseInt(map.get("libraryId").toString());
	}
	
	/**
	 * 
	 * <p>getQuestionLibByTeacherEmail方法的描述</p>
	 * @Title: QuestionLibDaoImpl的getQuestionLibByTeacherEmail方法
	 * @Description: 通过teacherEmail获取题库信息（题库id，题库名，教师邮箱）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午10:17:05
	 * @param teacherEmail
	 * @return
	 */
	public List<Map<String, Object>> getQuestionLibByTeacherEmail(String teacherEmail){
		String sql = "select * from QuestionMapping where teacherUsername = ?";
		return jdbcTemplate.queryForList(sql,teacherEmail);
	}

}
