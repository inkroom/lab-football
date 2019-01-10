package com.nsu.dao.teacher.exam;

import com.nsu.bean.teacher.StudentRecordBean;
import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.entity.UploadFiles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenGang
 * @Title: TMarkStuScoreDao
 * @Package com.nsu.dao.teacher.exam
 * @Description:
 * @date 2017/8/11 0011 下午 12:18
 **/
public interface TMarkScoreDao{
    //输入id查看考试列表
    List<TExamInforBean> viewExamListByEiid (@Param("eiid")long eiid)throws Exception;
    //查看具体的一场考试的信息
    List<StudentRecordBean> viewStuRecordByEiid(@Param("eiid")long eiid)throws Exception;
    int setOutActivityBySid(@Param("eiid")long eiid,@Param("cid")long cid,@Param("score")int score)throws Exception;
    int setStrongSkillBySid(@Param("eiid")long eiid,@Param("cid")long cid,@Param("score")int score)throws Exception;
    int setPracticeActivityBySid(@Param("eiid")long eiid,@Param("cid")long cid,@Param("score")int score)throws Exception;
    UploadFiles uploadByUid(@Param("uid")long uid)throws Exception;
    long findOcidByEiidAndSid(@Param("eiid")long eiid,@Param("sid")long sid)throws Exception;
    long findPcidByEiidAndSid(@Param("eiid")long eiid,@Param("sid")long sid)throws Exception;
    long findScidByEiidAndSid(@Param("eiid")long eiid,@Param("sid")long sid)throws Exception;
}
