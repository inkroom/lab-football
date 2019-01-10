package com.nsu.dao.teacher.exam;

import com.nsu.bean.teacher.StudentRecordBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenGang
 * @Title: ViewStuScoreDao
 * @Package com.nsu.dao.teacher.exam
 * @Description:
 * @date 2017/7/19 0019 上午 10:17
 **/
public interface ViewStuScoreDao{
    List<StudentRecordBean> findStuScoreByCidEidList(@Param("eiid") long eiid, @Param("classId") long classId)throws Exception;

}
