package cn.edu.nsu.lib.services.leader;

import cn.edu.nsu.lib.bean.leader.LAccountBean;
import cn.edu.nsu.lib.bean.leader.LFileBean;
import cn.edu.nsu.lib.bean.leader.LLabBean;
import cn.edu.nsu.lib.bean.leader.LTeacherBean;

import java.util.ArrayList;

/**
 * @author ChenGang
 * @Title: LeaderMangerService
 * @Package cn.edu.nsu.lib.services.leader
 * @Description:
 * @date 2017/11/4 0004 下午 12:06
 **/
public interface LeaderMangerService{
    //添加实验室
    void setLabInfo(LLabBean labInfo)throws  Exception;
    //获得实验室列表
    ArrayList<LLabBean> getLabInfoList()throws Exception;

    LLabBean getLabInfo( long lId)throws Exception;

    ArrayList<LTeacherBean> getTeacherInfoList()throws Exception;
    //设置实验室老师
    void addLabTeacher(long lId, long tId)throws  Exception;
    //获得老师信息根据老师id
    LTeacherBean getTeacherInfo(long id)throws Exception;
    //重置密码
    void resetPassword(long lid)throws Exception;

    void delTeacher(Long id)throws  Exception;
    //添加老师
    void addTeacher(LAccountBean lAccountBean)throws  Exception;

     ArrayList<LLabBean> getLabInfoListNoTeacher() throws Exception;
    void insertNotice(LFileBean lFileBean)throws  Exception;
    //删除实验室
    void delLab(int id)throws  Exception;
    //从实验室移除老师
    void removeTeacher(long t_id,long lab_id)throws  Exception;
}
