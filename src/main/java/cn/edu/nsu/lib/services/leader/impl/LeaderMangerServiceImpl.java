package cn.edu.nsu.lib.services.leader.impl;

import cn.edu.nsu.lib.bean.leader.*;
import cn.edu.nsu.lib.dao.leader.LeaderManagerDao;
import cn.edu.nsu.lib.services.leader.LeaderMangerService;
import cn.edu.nsu.lib.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ChenGang
 * @Title: LeaderMangerServiceImpl
 * @Package cn.edu.nsu.lib.services.leader.impl
 * @Description:
 * @date 2017/11/4 0004 下午 12:16
 **/
@Service
public class LeaderMangerServiceImpl implements LeaderMangerService{
    @Autowired
    LeaderManagerDao leaderManagerDao;

    @Override
    public void setLabInfo(LLabBean labInfo) throws Exception {
        leaderManagerDao.setLabInfo(labInfo);

    }

    @Override
    public ArrayList<LLabBean> getLabInfoList() throws Exception {
        ArrayList<LLabBean> lLabBeans = leaderManagerDao.getLabInfoList();
        //遍历设置教师
        if (lLabBeans != null) {
            int x = lLabBeans.size();
            for (int i = 0; i < x; i++) {
                ArrayList<LTeacherBean> teacherBeans = leaderManagerDao.getLabTeacher(lLabBeans.get(i).getId());
                if (teacherBeans != null) {
                    lLabBeans.get(i).setlTeacherBeans(teacherBeans);
                }

            }
        }
        return lLabBeans;
    }

    //只取实验室列表
    public ArrayList<LLabBean> getLabInfoListNoTeacher() throws Exception {
        ArrayList<LLabBean> lLabBeans = leaderManagerDao.getLabInfoList();
        return lLabBeans;
    }

    @Override
    public void insertNotice(LFileBean lFileBean) throws Exception {
        leaderManagerDao.insertNotice(lFileBean);

    }

    @Override
    public void delLab(int id) throws Exception {
        leaderManagerDao.delLab(id);
    }

    @Override
    public void removeTeacher(long t_id, long lab_id) throws Exception {
        leaderManagerDao.removeTeacher(t_id,lab_id);
    }


    @Override
    public LLabBean getLabInfo(long lId) throws Exception {
        LLabBean lLabBean = leaderManagerDao.getLabInfo(lId);
        if (lLabBean != null) {
            ArrayList<LTeacherBean> teacherBeans = leaderManagerDao.getLabTeacher(lLabBean.getId());
            if (teacherBeans != null) {
                lLabBean.setlTeacherBeans(teacherBeans);
                return lLabBean;
            }
        }
        return lLabBean;
    }

    @Override
    public ArrayList<LTeacherBean> getTeacherInfoList() throws Exception {
        ArrayList<LTeacherBean> lTeacherBeans = leaderManagerDao.getTeacherInfoList();
        //遍历设置教师
        if (lTeacherBeans != null) {
            int x = lTeacherBeans.size();
            for (int i = 0; i < x; i++) {
                ArrayList<LTLabBean> ltLabBeans = leaderManagerDao.getTeacherLab(lTeacherBeans.get(i).getId());
                if (ltLabBeans != null) {
                    lTeacherBeans.get(i).setLtLabBeans(ltLabBeans);
                }

            }
        }
        return lTeacherBeans;
    }

    @Override
    public void addLabTeacher(long lId, long tId) throws Exception {
        leaderManagerDao.addLabTeacher(lId, tId);
    }

    @Override
    public LTeacherBean getTeacherInfo(long id) throws Exception {
        return leaderManagerDao.getTeacherInfo(id);
    }

    @Override
    public void resetPassword(long lid) throws Exception {
        EncryptUtil encryptUtil = new EncryptUtil();
        String[] pas = encryptUtil.parseMd5("123456");
        //  System.out.println(pas[0]+"盐："+pas[1]);
        leaderManagerDao.resetPasswd(lid, pas[0], pas[1]);
    }

    @Override
    public void delTeacher(Long id) throws Exception {
        leaderManagerDao.delTeacher(id);
    }

    @Override
    public void addTeacher(LAccountBean lAccountBean) throws Exception {

        EncryptUtil encryptUtil = new EncryptUtil();
        String[] pas = encryptUtil.parseMd5("123456");
        //System.out.println(pas[0]+"_____________________"+pas[1]);
        lAccountBean.setId(lAccountBean.getTel());
        lAccountBean.setSalt(pas[1]);
        lAccountBean.setIdentity(2);
        lAccountBean.setPasswd(pas[0]);
        leaderManagerDao.addAccount(lAccountBean);
        leaderManagerDao.addTeacher(lAccountBean);
    }
}
