package com.nsu.service.teacher.exam.impl;

import com.nsu.bean.teacher.StudentRecordBean;
import com.nsu.dao.teacher.exam.ViewStuScoreDao;
import com.nsu.service.teacher.exam.TStudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*/**
 * <p>TStudentScoreServiceImpl查看学生成绩实现类</p>
 * @ClassName: TStudentScoreServiceImpl
 * @Description: 查看学生的成绩
 * @author ChenGang
 * @date 2017/7/19 0019 上午 10:26
 */

@Service
public class TStudentScoreServiceImpl  implements TStudentScoreService{
    @Autowired
    ViewStuScoreDao viewStuScoreDao;//查看学生成绩dao层
    //根据老师id返回该老师创建的考试列表

    @Override
    public List<StudentRecordBean> findStuScoreByCidEid(long eiid,long classId) throws Exception {
        return viewStuScoreDao.findStuScoreByCidEidList(eiid,classId);

    }

}
