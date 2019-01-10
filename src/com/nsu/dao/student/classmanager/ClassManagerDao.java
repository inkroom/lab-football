package com.nsu.dao.student.classmanager;

import com.nsu.bean.student.classmanager.ClassBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 9:43
 * @Descorption 班级管理dao
 */
public interface ClassManagerDao {
    /**
     * 获取所有班级
     *
     * @param id 学生id
     * @return 班级集合
     * @throws Exception sql
     */
    List<ClassBean> getAllClasses(@Param("id") long id) throws Exception;

    /**
     * 加入班级
     *
     * @param id      学生id
     * @param classId 班级id
     * @return 受影响行数
     * @throws Exception sql
     */
    int joinClass(@Param("id") long id, @Param("classId") long classId) throws Exception;

    /**
     * 验证班级号和验证口令是否正确
     *
     * @param classId 班级id
     * @param key     验证口令
     * @return 符合的记录行数
     * @throws Exception sql
     */
    int checkKey(@Param("classId") long classId, @Param("key") long key) throws Exception;

    /**
     * 判断学生是否已有所在班级
     *
     * @param id 学生id
     * @return 符合条件的记录行数，0或者1
     * @throws Exception SQL
     */
    int isStayClass(long id) throws Exception;
}
