package com.nsu.dao.student.score;

import com.nsu.bean.student.score.SchoolBean;
import com.nsu.bean.student.score.ScoreBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/16
 * @Time 9:28
 * @Descorption 积分榜dao
 */
public interface ScoreDao {
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
     * @param name      学校或学生姓名
     * @param isStudent 是否是学生，false则是学校名称
     * @param isBlur    是否是模糊搜索
     * @param school    学校名称，可以为null
     * @return 积分集合
     * @throws Exception sql异常
     */
    List<ScoreBean> getScores(@Param("name") String name, @Param("isStudent") boolean isStudent, @Param("isBlur") boolean isBlur, @Param("school") String school) throws Exception;

}
