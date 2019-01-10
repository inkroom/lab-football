package cn.edu.nsu.lib.services.student;

import cn.edu.nsu.lib.bean.student.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StuBaseService{
    //插入学生信息
    void insertStuInfo(StuBasicBean stuBasicBean)throws  Exception;
//获取学生信息
    Map<String, Object> getStuInfo( long id) throws Exception;

    //查找所有的奖项

    List<StuPrizeBean> getProzeList( long id)throws  Exception;
    //新建奖项
    void setProzeList(StuPrizeBean stuPrizeBean)throws  Exception;
    //获得学生成绩集合
    List<StuScoreImplBean> getStuScoreList(long id)throws  Exception;
    //插入学生奖项
    void insertPriceInfo(StuPrizeBean stuPrizeBean)throws  Exception;

    ArrayList<SLabBean> getLabInfoList()throws Exception;
    //查找所有专业
    ArrayList<SMajorBean> getMajorList()throws Exception;

    void reSetStuInfo(StuBasicBean stuBasicBean)throws Exception;
   // 获取学生信息
    Map<String, Object> getStudentInfo( long username) throws Exception;

    void exitLab(long id)throws Exception;
}
