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

import cn.nsu.ccl.comm.Envirment.ComEnviorment;
import cn.nsu.ccl.teacher.dao.QuestionLibDao;
import cn.nsu.ccl.teacher.entity.Question;

/**
 * <p>QuestionListDaoImpl类的描述</p>
 * @ClassName: QuestionListDaoImpl
 * @Description: TODO(实现与数据库之间关于题库属性类的相关操作（增删查）)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:49:40
 */
public class QuestionLibDaoImpl extends ComEnviorment implements QuestionLibDao {

	
	/**
	 * <p>覆盖的getQuestionlib函数</p>
	 * @param teacherId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.QuestionLibDao#getQuestionlib(int)
	 */
	@Override
	public List<Map<String, Object>> getQuestionlib(int teacherId) throws Exception {
		// TODO Auto-generated method stub
		String sql=GET_SQL(new String[]{teacherId+""}, "call getQMList('?')");
		return jt.queryForList(sql);
	}

	/**
	 * <p>覆盖的addQuestionList函数</p>
	 * @param q
	 * @param teacherId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.QuestionLibDao#addQuestionList(cn.nsu.ccl.teacher.entity.QuestionListEntity, java.lang.String)
	 */
	@Override
	public boolean addQuestionLib(String questionLibName, String teacherId) throws Exception {
		// TODO Auto-generated method stub
		String sql=GET_SQL(new String[]{teacherId,questionLibName},"call createLibrary('?','?')");
		if (jt.update(sql)==1) {
			return true;
		}
		return false;
	}

	/**
	 * <p>覆盖的deletQuestionList函数</p>
	 * @param libraryId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.QuestionLibDao#deletQuestionList(int)
	 */
	@Override
	public boolean deletQuestionLib(int libraryId) throws Exception {
		// TODO Auto-generated method stub
		String sql=GET_SQL(new String[]{libraryId+""}, "call deleteLibrary(?)");
		
		if (jt.update(sql)==1) {
			return true;
		}
		return false;
	}

	/**
	 * <p>覆盖的getQuestions函数</p>
	 * @param libraryId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.QuestionLibDao#getQuestions(int)
	 */
	@Override
	public List<Map<String, Object>> getQuestions(int libraryId) throws Exception {
		// TODO Auto-generated method stub
		String sql=GET_SQL(new String[]{libraryId+""}, "call getQList(?)");
		return jt.queryForList(sql);
	}

	/**
	 * <p>覆盖的addQuestion函数</p>
	 * @param questionList
	 * @param questionLibId
	 * @return
	 * @throws Exception
	 * @see cn.nsu.ccl.teacher.dao.QuestionLibDao#addQuestion(java.util.ArrayList, int)
	 */
	@Override
	public boolean addQuestion(ArrayList<Question> questionList, int questionLibId) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < questionList.size(); i++) {
			Question question= questionList.get(i);
			String sql=GET_SQL(new String[]{
					(i+1)+"", 							//题号
					questionLibId+"",					//题库编号
					question.getQuestionContent(),		//提干
					question.getChoice(),				//备选答案
					question.getAnswer(),				//正确答案
					question.getType()},				//题目类型
					"call insertQuestion('?',?,'?','?','?','?')");
			if (jt.update(sql)==0) {
				return false;
			}
		}
		
		return true;
	}

}
