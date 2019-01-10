package com.nsu.service.student.score;

import com.nsu.bean.student.score.SchoolBean;
import com.nsu.bean.student.score.ScoreBean;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/14
 * @Time 10:21
 * @Descorption
 */
public interface ScoreService {
    /**
     * 获取所有学校
     *
     * @return 学校集合
     * @throws Exception sql异常
     */
    List<SchoolBean> getSchools() throws Exception;

    /**
     * 获取所有分数
     *
     * @param name       学校或学生姓名
     * @param isStudent  是否是学生，false则是学校名称
     * @param isBlur     是否是模糊搜索
     * @param schoolName 学校名称，可以为null
     * @return 积分集合
     * @throws Exception sql异常
     */
    List<ScoreBean> getScores(@Nullable String name, @Nullable Boolean isStudent, @Nullable Boolean isBlur, @Nullable String schoolName) throws Exception;

    /**
     * 获取我的积分
     *
     * @param studentId 学生id
     * @param scores    积分集合
     * @return 我的积分
     * @throws Exception sql异常
     */
    ScoreBean getMyScore(long studentId, List<ScoreBean> scores) throws Exception;

    /**
     *
     * @param scores
     * @return
     */
//    String splitScore(List<ScoreBean> scores);
}
