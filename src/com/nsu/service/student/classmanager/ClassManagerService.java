package com.nsu.service.student.classmanager;

import com.nsu.bean.student.classmanager.ClassBean;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/18
 * @Time 9:51
 * @Descorption
 */
public interface ClassManagerService {
    /**
     * 获取所有班级
     *
     * @param id 学生id
     * @return 班级集合
     * @throws Exception sql
     */
    List<ClassBean> getAllClass(long id) throws Exception;

    /**
     * 加入班级
     *
     * @param id      学生id
     * @param classId 班级id
     * @param key     验证码口令
     * @return 成功返回true，失败返回false，classId或者口令错误返回null，
     * @throws Exception sql
     */
    Boolean joinClass(long id, long classId, long key) throws Exception;

    /**
     * 判断学生是否已有所在班级
     *
     * @param id 学生id
     * @return 已有所在班级返回true，否则返回false
     * @throws Exception SQL
     */
    boolean isStayClass(long id) throws Exception;
}
