package com.nsu.service.teacher.exam.impl;

import com.nsu.bean.teacher.StudentRecordBean;
import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.dao.teacher.exam.TExamManagerDao;
import com.nsu.dao.teacher.exam.TMarkScoreDao;
import com.nsu.dao.teacher.exam.ViewStuScoreDao;
import com.nsu.entity.UploadFiles;
import com.nsu.service.teacher.exam.TMarkScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenGang
 * @Title: TMarkScoreServiceImpl
 * @Package com.nsu.service.teacher.exam.impl
 * @Description:
 * @date 2017/8/11 0011 下午 12:19
 **/
@Service
public class TMarkScoreServiceImpl implements TMarkScoreService{
    @Autowired
    TExamManagerDao tExamManagerDao;
    @Autowired
    TMarkScoreDao tMarkScoreDao;
    @Autowired
    ViewStuScoreDao viewStuScoreDao;
    @Override
    //根据老师id返回考试列表
    public List<TExamInforBean> viewExamListByTid(long aid)throws  Exception
    {
        long tid=tExamManagerDao.findTidByAid(aid);
        return tExamManagerDao.selectExamInformationList(tid);
    }
    //根据考试id号返回考试列表
    @Override
    public List<TExamInforBean> viewExamListByEiid(long eiid)throws  Exception
    {
        return tMarkScoreDao.viewExamListByEiid(eiid);
    }
    //查看具体的一场考试的信息
    public List<StudentRecordBean> viewStuRecordByEiid(long eiid)throws  Exception
    {
            return tMarkScoreDao.viewStuRecordByEiid(eiid);
    }
    //设置学生成绩
    public int setStuREcordBySid(int Type,long sid,long eiid,int score)throws Exception {
        //设置OUT_ACTIVITY课外活动
        if(Type==1)
        {
           tMarkScoreDao. setOutActivityBySid(eiid,sid,score);
        }
        //设置STRONG_SKILL专长特长
        else if(Type==2)
    {
        tMarkScoreDao. setStrongSkillBySid(eiid,sid,score);
    }
    //设置PRACTICE_ACTIVITY社会实践
    else if(Type==3)
    {
        tMarkScoreDao. setPracticeActivityBySid(eiid,sid,score);
    }
        return 0;
    }
    //下载学生的文件
    public UploadFiles downStuExamRes(int Type, long sid, long eiid)throws Exception
    {
        UploadFiles uploadFiles=null ;
        //下载OUT_ACTIVITY课外活动
        long uid;
        if(Type==1)
        {
             uid=tMarkScoreDao.findOcidByEiidAndSid(eiid,sid);
            uploadFiles= tMarkScoreDao.uploadByUid(uid);
        }
        //下载STRONG_SKILL专长特长
        else if(Type==2)
        {
            uid=tMarkScoreDao.findScidByEiidAndSid(eiid,sid);
            uploadFiles= tMarkScoreDao.uploadByUid(uid);
        }
        //下载PRACTICE_ACTIVITY社会实践
        else if(Type==3)
        {
          uid =tMarkScoreDao.findPcidByEiidAndSid(eiid,sid);
            uploadFiles= tMarkScoreDao.uploadByUid(uid);
        }

        return uploadFiles;
    }
}
