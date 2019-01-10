package com.nsu.service.teacher.exam;

import com.nsu.bean.teacher.StudentRecordBean;
import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.entity.UploadFiles;

import java.util.List;

/**
 * @author ChenGang
 * @Title: TMarkScoreService
 * @Package com.nsu.service.teacher.exam
 * @Description:
 * @date 2017/8/11 0011 下午 12:19
 **/
public interface TMarkScoreService{
    List<TExamInforBean> viewExamListByTid(long aid)throws Exception;
    //根据考试id号返回考试列表
    List<TExamInforBean> viewExamListByEiid(long eiid)throws  Exception;
    //根据类型，考试id搜索学生列表
    List<StudentRecordBean> viewStuRecordByEiid( long eiid)throws Exception;
    //根据学生id考试id给学生打分
    int setStuREcordBySid(int Type,long sid,long eiid,int score)throws  Exception;
    //跟据学生id下载学生资料
    UploadFiles downStuExamRes(int Type, long sid, long eiid)throws Exception;
}
