/**
 * <p>QuestionListDao.java文件的详细描述</p>
 * @Title: QuestionListDao.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(题库的增删查的实现接口)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:35:08
 * @version V1.0
 */
package cn.nsu.ccl.teacher.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.Library;

import cn.nsu.ccl.teacher.entity.Question;
import cn.nsu.ccl.teacher.entity.QuestionListEntity;

/**
 * <p>QuestionListDao类的描述</p>
 * @ClassName: QuestionListDao
 * @Description: 题库表的增删查的实现接口
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:35:08
 */
public interface QuestionLibDao {



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
	public List<Map<String, Object>> getQuestionlib(int teacherId) throws Exception;
	
	/**
	 * <p>addQuestionList方法的描述</p>
	 * @Title: QuestionListDao的addQuestionList方法
	 * @Description: 根据题库名字和教师ID创建题库
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午3:08:37
	 * @param q
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestionLib(String questionLibName,String teacherId)throws Exception;
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
	public boolean deletQuestionLib(int libraryId) throws Exception;
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
	public List<Map<String, Object>> getQuestions(int libraryId) throws Exception;
	
	/**
	 * <p>addQuestion方法的描述</p>
	 * @Title: QuestionLibDao的addQuestion方法
	 * @Description:将题目集合添加到题库中
	 * @author: 蒋玖宏
	 * @author 2213974854@qq.com
	 * @date 2016年11月19日 下午4:24:04
	 * @param questionList
	 * @param questionLibId
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestion(ArrayList<Question> questionList,int questionLibId) throws Exception;
	
}
