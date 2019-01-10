package com.nsu.dao.student.practice;

import com.nsu.bean.student.practice.QuestionBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/17
 * @Time 9:28
 * @Descorption 练习dao
 */
public interface PracticeDao {
    /**
     * 筛选出所有符合条件的题目
     *
     * @param type       题目类型
     * @param difficulty 题目难度
     * @param object     科目
     * @throws Exception sql异常
     */
    List<QuestionBean> getAllQuestion(@Param("type") int type, @Param("difficulty") int difficulty, @Param("subject") int subject) throws Exception;

    /**
     * 学生回答后修改数据
     *
     * @param studentId 学生id
     * @param score     题目积分
     * @param isRight   是否回答正确
     * @return 修改成功返回true，否则返回false
     * @throws Exception sql
     */
    int commit(long studentId, int score, boolean isRight) throws Exception;

    /**
     * 获取单个题目
     *
     * @param id 题目id
     * @return 题目
     * @throws Exception sql
     */
    QuestionBean getOneQuestion(long id) throws Exception;

}
