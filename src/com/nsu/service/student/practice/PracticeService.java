package com.nsu.service.student.practice;

import com.nsu.bean.student.practice.PracticeSearchBean;
import com.nsu.bean.student.practice.QuestionBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/17
 * @Time 9:41
 * @Descorption 练习
 */
public interface PracticeService {

    // TODO: 2017/8/23 所有使用了HttpServletRequest的方法均需要修改成使用redis

    //缓存时间，10小时
    public static final int EXERCISE_SECENDS = 36000;
    //缓存的题目集合key
    public static final String EXERCISE_QUESTION_LIST_KEY = "_EXERCISE_QUESTION_LIST_KEY_";
    //缓存的题目搜索条件key
    public static final String EXERCISE_QUESTION_SEARCH_KEY = "_EXERCISE_QUESTION_SEARCH_KEY_";

    /**
     * 获取所有合适的题目
     *
     * @param search 条件
     * @return 题目集合
     * @throws Exception sql
     * @see PracticeSearchBean
     */
    List<QuestionBean> getAllQuestion(PracticeSearchBean search) throws Exception;
//    List<QuestionBean> getAllQuestion(PracticeSearchBean search, HttpServletRequest request) throws Exception;

    /**
     * 获取题目答案，json数组格式，只包括key
     *
     * @param questionId 题目id
     * @return 题目
     * @throws Exception sql
     */
    QuestionBean getAnswer(long questionId) throws Exception;
//    QuestionBean getAnswer(long questionId, HttpServletRequest request) throws Exception;


    /**
     * 验证题目答案是否正确
     *
     * @param questionId 题目id
     * @param answer     学生提交的答案
     * @return 正确返回true，否则返回false，不存在题目返回null
     * @throws Exception sql
     */
    Boolean checkAnswer(long questionId, String answer) throws Exception;
//    Boolean checkAnswer(long questionId, String answer, HttpServletRequest request) throws Exception;

    /**
     * 学生回答后修改数据
     *
     * @param studentId 学生id
     * @param score     题目积分
     * @param isRight   是否回答正确
     * @return 修改成功返回true，否则返回false
     * @throws Exception sql
     */
    boolean commit(long studentId, int score, boolean isRight) throws Exception;

    /**
     * 获取单个题目
     *
     * @param search 搜索条件
     * @return 获取的题目
     * @throws Exception sql
     */
    QuestionBean getOneQuestion(PracticeSearchBean search) throws Exception;
//    QuestionBean getOneQuestion(PracticeSearchBean search, HttpServletRequest request) throws Exception;

    /**
     * 获取单个题目
     *
     * @param id 题目id
     * @return 题目
     * @throws Exception sql
     */
    QuestionBean getOneQuestion(long id) throws Exception;

    /**
     * 获取答案，包括key和选项
     *
     * @param questionBean 题目
     * @return 答案，包括序号和答案本身
     * @throws Exception 未知
     */
    String getAnswer(QuestionBean questionBean) throws Exception;
}
