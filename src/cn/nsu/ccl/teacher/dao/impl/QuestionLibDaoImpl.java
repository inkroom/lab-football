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

import cn.nsu.ccl.teacher.dao.QuestionLibDao;
import cn.nsu.ccl.teacher.entity.Question;
import cn.nsu.ccl.teacher.entity.QuestionLibList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>QuestionListDaoImpl类的描述</p>
 * @ClassName: QuestionListDaoImpl
 * @Description: TODO(实现与数据库之间关于题库属性类的相关操作（增删查）)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午4:49:40
 */
@Repository
public class QuestionLibDaoImpl implements QuestionLibDao {
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
	public List<QuestionLibList> getQuestionLibList(){
		String sql = "call getLibraryInfo()";
		List<QuestionLibList> list = new ArrayList<>();
		List<Map<String, Object>> listMap = new ArrayList<>();
		listMap =  jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map : listMap){
			QuestionLibList questionLibListEntity = new QuestionLibList();
			//获取题库id
			questionLibListEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
			//获取题库名字
			questionLibListEntity.setLibraryName(map.get("libraryName").toString());
			//获取单选题数量
			questionLibListEntity.setsChoice(map.get("sChoice").toString());
			//获取多选题数量
			questionLibListEntity.setmChoice(map.get("mChoice").toString());
			//获取判断题数量
			questionLibListEntity.setTofChoice(map.get("tofChoice").toString());
			//获取填空题数量
			questionLibListEntity.settChoice(map.get("tChoice").toString());
			//获取简单解答题数量
			questionLibListEntity.setJdChoice(map.get("jdChoice").toString());
			//获取中等解答题数量
			questionLibListEntity.setZdChoice(map.get("zdChoice").toString());
			//获取困难解答题数量
			questionLibListEntity.setKnChoice(map.get("knChoice").toString());
			list.add(questionLibListEntity);
		}
		return list;
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
		String sql = "insert into QuestionMapping (libraryName,teacherUsername) values(?,?)";
		return jdbcTemplate.update(sql,questionLibName,teacherId)==1;
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
	public boolean addQuestion(ArrayList<Question> questionList, int questionLibId){
		String sql = "INSERT INTO QuestionList(questionId,libraryId,question,choice,answer,types) VALUES(?,?,?,?,?,?)";
		int j =-1;
		for (int i = 0; i < questionList.size(); i++) {
			Question question= questionList.get(i);
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
		return Integer.parseInt(listMap.get(0).get("libraryId").toString());
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
	public List<QuestionLibList> getQuestionLibByTeacherEmail(String teacherEmail){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select QuestionMapping.libraryId, QuestionMapping.libraryName,")
		.append("(select count(types) from QuestionList where types = '单选题') AS schoice,")
		.append("(select count(types) from QuestionList where types = '多选题') AS mchoice,")
		.append("(select count(types) from QuestionList where types = '判断题') AS tofChoice,")
		.append("(select count(types) from QuestionList where types = '填空题') AS tChoice,")
		.append("(select count(types) from QuestionList where types = '简单解答题') AS jdChoice,")
		.append("(select COUNT(types) from QuestionList where types = '中等解答题') AS zdChoice,")
		.append("(select COUNT(types) from QuestionList where types = '困难解答题') AS knChoice")
		.append(" FROM QuestionMapping")
		.append(" WHERE questionmapping.teacherUsername = '")
		.append(teacherEmail)
		.append("';");
		
		String sql = stringBuilder.toString();
		System.out.println(sql);
		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);
		List<QuestionLibList> list = new ArrayList<>();
		for(Map<String, Object> map : listMap){
			QuestionLibList questionLibListEntity = new QuestionLibList();
			//获取题库id
			questionLibListEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
			//获取题库名字
			questionLibListEntity.setLibraryName(map.get("libraryName").toString());
			//获取单选题数量
			questionLibListEntity.setsChoice(map.get("sChoice").toString());
			//获取多选题数量
			questionLibListEntity.setmChoice(map.get("mChoice").toString());
			//获取判断题数量
			questionLibListEntity.setTofChoice(map.get("tofChoice").toString());
			//获取填空题数量
			questionLibListEntity.settChoice(map.get("tChoice").toString());
			//获取简单解答题数量
			questionLibListEntity.setJdChoice(map.get("jdChoice").toString());
			//获取中等解答题数量
			questionLibListEntity.setZdChoice(map.get("zdChoice").toString());
			//获取困难解答题数量
			questionLibListEntity.setKnChoice(map.get("knChoice").toString());
			list.add(questionLibListEntity);
		}
		return list;
	}

}
