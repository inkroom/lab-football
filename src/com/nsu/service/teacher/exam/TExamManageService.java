package com.nsu.service.teacher.exam;

import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.bean.teacher.TExamInformationBean;

import java.util.List;

/**
 * @author ChenGang
 * @Title: ExamManageService
 * @Package com.nsu.service.teacher.exam.Impl
 * @Description:教师考试管理接口
 * @date 2017/7/17 0017 上午 9:01
 **/
public  interface TExamManageService  {
    //创建考试service接口
    public TExamInformationBean creatExamInformation(TExamInformationBean tExamInformationBean)throws Exception;
    //根据id查看考试接口
    public List<TExamInforBean> findExamInforByTid(long aid)throws Exception;
    //根据班级号查看考试接口
    public List<TExamInforBean> findExamByCid(long aid)throws Exception;
    //删除考试接口
    public void deleteExamInformation(long id)throws Exception;

}
