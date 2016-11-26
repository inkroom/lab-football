/**
 * <p>QuestionLibServiceImpl.java文件的详细描述</p>
 * @Title: QuestionLibServiceImpl.java
 * @Package cn.nsu.ccl.teacher.service.impl
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午8:02:56
 * @version V1.0
 */
package cn.nsu.ccl.teacher.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.nsu.ccl.teacher.dao.impl.QuestionLibDaoImpl;
import cn.nsu.ccl.teacher.entity.QuestionEntity;
import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;
import cn.nsu.ccl.teacher.service.QestionLibService;

/**
 * <p>QuestionLibServiceImpl类的描述</p>
 * @ClassName: QuestionLibServiceImpl
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午8:02:56
 */
@Service
public class QuestionLibServiceImpl implements QestionLibService {
	@Autowired
	private QuestionLibDaoImpl questionLibDao;
	/**
	 * 
	 * <p>getQuestion方法的描述</p>
	 * @Title: QestionLibService的getQuestion方法
	 * @Description: 通过题库Id获取题库中的题目信息
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:54:20
	 * @param questionLibId
	 * @return
	 */
	public ArrayList<QuestionEntity> getQuestion(int questionLibId) {
		List<Map<String, Object>> listMap = questionLibDao.getQuestions(questionLibId);
		ArrayList<QuestionEntity> list = new ArrayList<>();
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			QuestionEntity questionEntity = new QuestionEntity();
			questionEntity.setQuestionId(map.get("questionId").toString());
			questionEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
			questionEntity.setQuestionContent(map.get("question").toString());
			questionEntity.setChoice(map.get("choice").toString());
			questionEntity.setAnswer(map.get("answer").toString());
		}
		return list;
	}

	/**
	 * 
	 * <p>addQuestions方法的描述</p>
	 * @Title: QestionLibService的addQuestions方法
	 * @Description: 传入一个集合，直接实现创建一个题库（包含所有）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午8:01:29
	 * @param questionEntities
	 * @return
	 */
	public boolean addQuestions(String questionLibName,
			String teacherId,
			ArrayList<QuestionEntity> questionEntities) {
		//在题库列表中创建一个题库信息
		if (this.addQuestionLibList(questionLibName, teacherId)) {
			//在题库列表信息中获取题库的id
			int questionLibId = this.getQuestionLibId(questionLibName, teacherId);
			//最后调用dao层方法将整个题库的所有题库存储数据库
			return questionLibDao.addQuestion(questionEntities, questionLibId);
		}
		return false;
	}

	/**
	 * 
	 * <p>addQuestionLibList方法的描述</p>
	 * @Title: QestionLibService的addQuestionLibList方法
	 * @Description: 传入题库名字和教师id（邮箱）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:59:15
	 * @param questionLibName
	 * @param teacherId
	 * @return
	 */
	public boolean addQuestionLibList(String questionLibName, String teacherId) {
		return questionLibDao.addQuestionLib(questionLibName, teacherId);
	}
	
	/**
	 * <p>覆盖的getQuestionLibList函数</p>
	 * @Description: 通过教师id获取该教师所创建的题库列表信息（题库id，题库名字，单选题个数，多选题个数，判断题个数）
	 * @param teacherId
	 * @return
	 * @see cn.nsu.ccl.teacher.service.QestionLibService#getQuestionLibList(int)
	 */
	public QuestionLibListEntity getQuestionLibList(String questionLibName,String teacherId) {
		//先获取题库id列表
		int libraryId = this.getQuestionLibId(questionLibName, teacherId);
		//使用题库id查找题库信息
		return this.getQuestionLibListByLibraryId(libraryId);
	}
	
	/**
	 * 
	 * <p>getQuestionList方法的描述</p>
	 * @Title: QestionLibService的getQuestionList方法
	 * @Description: 通过传入的题库信息获取题库的具体信息（题库名字，单选题数量，多选题数量，判断题数量）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:55:16
	 * @param teacherId
	 * @return
	 */
	private QuestionLibListEntity getQuestionLibListByLibraryId(int libraryId) {
		//获得数据库中所有的题库信息（包含题库的id，题库的名字，该题库的单选题个数，多选题各位，判断题个数）
		List<Map<String, Object>> listMap = questionLibDao.getQuestionLibList();
		//新建一个类去获取题库信息
		QuestionLibListEntity questionLibListEntity = new QuestionLibListEntity();
		//遍历所有的题库信息，找到与传入的题库id相等的题库信息
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			if (Integer.parseInt(map.get("libraryId").toString())==libraryId) {
				//获取题库id
				questionLibListEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
				//获取题库名字
				questionLibListEntity.setLibraryName(map.get("libraryName").toString());
				//获取单选题数量
				questionLibListEntity.setChoiceNumber(map.get("s_Num").toString());
				//获取多选题数量
				questionLibListEntity.setMultiputeChoiceNumber(map.get("m_Num").toString());
				//获取判断题数量
				questionLibListEntity.setJudgeNumber(map.get("tof_Num").toString());
			}
		}
		return questionLibListEntity;
	}
	/**
	 * 
	 * <p>deleteQuestionLibList方法的描述</p>
	 * @Title: QestionLibService的deleteQuestionLibList方法
	 * @Description: 通过题库名字和教师邮箱帐号（教师id）删除题库（整个删除）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:57:35
	 * @param questionLibId
	 * @return
	 */
	public boolean deleteQuestionLibList(String questionLibName,String teacherId) {
		//获取题库id
		int questionLibId = this.getQuestionLibId(questionLibName, teacherId);
		//调用dao层实现删除题库的操作
		return questionLibDao.deletQuestionLib(questionLibId);
	}

	/**
	 * <p>覆盖的getQuestionLibId函数--通过教师邮箱和题库名称查找数据库id</p>
	 * @param teacherId
	 * @return
	 * @see cn.nsu.ccl.teacher.service.QestionLibService#getQuestionLibId(java.lang.String)
	 */
	public int getQuestionLibId(String questionLibName,String teacherId) {
		return questionLibDao.getQuestionLibId(questionLibName,teacherId);
	}
	
	/**
	 * 
	 * <p>getQuestionLibList方法的描述</p>
	 * @Title: QuestionLibServiceImpl的getQuestionLibList方法
	 * @Description: 获取题库信息（题库id，题库名，单选题个数，多选题个数，判断题个数）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午10:07:45
	 * @return
	 */
	private ArrayList<QuestionLibListEntity> getQuestionLibList(){
		//获得数据库中所有的题库信息（包含题库的id，题库的名字，该题库的单选题个数，多选题各位，判断题个数）
		List<Map<String, Object>> listMap = questionLibDao.getQuestionLibList();
		//
		ArrayList<QuestionLibListEntity> list = new ArrayList<>();
		//遍历所有的题库信息，找到与传入的题库id相等的题库信息
		for(int i = 0; i < listMap.size();i++){
			Map<String, Object> map = listMap.get(i);
			QuestionLibListEntity questionLibListEntity = new QuestionLibListEntity();
				//获取题库id
				questionLibListEntity.setLibraryId(Integer.parseInt(map.get("libraryId").toString()));
				//获取题库名字
				questionLibListEntity.setLibraryName(map.get("libraryName").toString());
				//获取单选题数量
				questionLibListEntity.setChoiceNumber(map.get("s_Num").toString());
				//获取多选题数量
				questionLibListEntity.setMultiputeChoiceNumber(map.get("m_Num").toString());
				//获取判断题数量
				questionLibListEntity.setJudgeNumber(map.get("tof_Num").toString());
			list.add(questionLibListEntity);
		}
		return list;
	}
	
	
	/**
	 * <p>覆盖的getQuestionLibListByTeacherEmail函数</p>
	 * @param teacherEmail
	 * @Description: 通过教师邮箱获取该教师所创建的题库列表信息（包含题库的id，题库的名字，题库的单选题个数，多选题个数，判断题个数）
	 * @return
	 * @see cn.nsu.ccl.teacher.service.QestionLibService#getQuestionLibListByTeacherEmail(java.lang.String)
	 */
	public ArrayList<QuestionLibListEntity> getQuestionLibListByTeacherEmail(String teacherEmail) {
		//使用dao获取题库信息（id，题库名，教师邮箱）
		List<Map<String, Object>> list = questionLibDao.getQuestionLibByTeacherEmail(teacherEmail);
		//获取题库信息（题库id，题库名，单选题数量，多选题数量，判断题数量）
		ArrayList<QuestionLibListEntity> questionLibListEntities = this.getQuestionLibList();
		//新建一个集合用于存储匹配的数据
		ArrayList<QuestionLibListEntity> newList = new ArrayList<>();
		//对list进行遍历，找到该属于该教师的题库信息（题库id，题库名，单选题数量，多选题数量，判断题数量）
		for(int i = 0; i < list.size();i++){
			//遍历questionLibListEntities找到匹配信息
			for(int j = 0; j < list.size();j++){
				QuestionLibListEntity questionLibListEntity = questionLibListEntities.get(j);
				if (questionLibListEntity.getLibraryId()==Integer.parseInt(list.get(i).get("libraryId").toString())) {
					newList.add(questionLibListEntity);
				}
			}
		}
		return newList;
	}




	
}
