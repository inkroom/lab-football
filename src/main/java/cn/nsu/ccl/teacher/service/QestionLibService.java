/**
 * <p>QestionService.java文件的详细描述</p>
 * @Title: QestionService.java
 * @Package cn.nsu.ccl.teacher.service
 * @Description: TODO
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午7:53:10
 * @version V1.0
 */
package cn.nsu.ccl.teacher.service;

import java.util.ArrayList;


import cn.nsu.ccl.teacher.entity.QuestionEntity;
import cn.nsu.ccl.teacher.entity.QuestionLibListEntity;

/**
 * <p>QestionService类的描述</p>
 * @ClassName: QestionService
 * @Description: 题库
 * @author 暴沸 baofeidyz@foxmail.com
 * @date 2016年11月20日 下午7:53:10
 */
public interface QestionLibService {

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
	public ArrayList<QuestionEntity> getQuestion(int questionLibId);
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
			ArrayList<QuestionEntity> questionEntities);
	
	
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
	public boolean deleteQuestionLibList(String questionLibName,String teacherId);
	
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
	public boolean addQuestionLibList(String questionLibName,String teacherId);
	/**
	 * 
	 * <p>getQuestionList方法的描述</p>
	 * @Title: QestionLibService的getQuestionList方法
	 * @Description: 通过教师id获取该教师所创建的题库列表信息
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:55:16
	 * @param teacherId
	 * @return
	 */
	public QuestionLibListEntity getQuestionLibList(String questionLibName,String teacherId);
	/**
	 * 
	 * <p>getQuestionLibId方法的描述</p>
	 * @Title: QestionLibService的getQuestionLibId方法
	 * @Description: 通过题库名字和教师邮箱获取题库id
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午9:58:33
	 * @param questionLibName
	 * @param teacherId
	 * @return
	 */
	public int getQuestionLibId(String questionLibName,String teacherId);
	
	/**
	 * 
	 * <p>getQuestionLibListByTeacherEmail方法的描述</p>
	 * @Title: QestionLibService的getQuestionLibListByTeacherEmail方法
	 * @Description: 通过教师邮箱获取该教师所创建的题库列表信息（包含题库的id，题库的名字，题库的单选题个数，多选题个数，判断题个数）
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午10:00:17
	 * @param teacherEmail
	 * @return
	 */
	public ArrayList<QuestionLibListEntity> getQuestionLibListByTeacherEmail(String teacherEmail);
	
	
}
