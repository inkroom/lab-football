package com.nsu.dao.teacher.exam;

import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.bean.teacher.TExamInformationBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenGang
 * @Title: TExamManagerDao
 * @Package com.nsu.dao.teacher.exam
 * @Description:
 * @date 2017/7/17 0017 上午 10:47
 **/

public interface TExamManagerDao{
        /**
         *@auther ChenGang
         *@说明：创建考试
         * @Param classId:班级id
         **/
        int insertExamInformation(TExamInformationBean tExamInformationBean)throws Exception;
        /**
         *@auther ChenGang
         *@Param eIId 考试ID
         * * 查询已有考试
         **/
      List<TExamInforBean> selectExamInformationList(@Param("tId") long t_id)throws Exception;
    /**
     *@auther ChenGang
     *@Param eIId 考试ID
     * * 查询已有考试
     **/
    List<TExamInforBean> selectExamInformationListByclass(@Param("tId") long t_id )throws Exception;
        /**
         *@auther ChenGang
         *@Param eIId 考试ID
         **/
      int updateExamInformationStatuts(@Param("eIId") long eIId)throws Exception;
    //通过老师id找到老师班级id
    long findCidByTid (@Param("tid")long tid)throws Exception;
    //通过账户id找到老师id
    long findTidByAid(@Param("aid")long aid)throws Exception;

}




