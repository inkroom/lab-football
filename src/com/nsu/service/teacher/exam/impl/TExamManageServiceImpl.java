package com.nsu.service.teacher.exam.impl;

import com.nsu.bean.teacher.QuesOfNumBean;
import com.nsu.bean.teacher.TExamInforBean;
import com.nsu.bean.teacher.TExamInformationBean;
import com.nsu.dao.teacher.exam.TExamManagerDao;
import com.nsu.entity.Account;
import com.nsu.service.teacher.exam.TExamManageService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ChenGang
 * @Title: ExamManageServiceImpl
 * @Package com.nsu.service.teacher.exam.Impl
 * @Description:考试管理service 的实现类
 * @date 2017/7/17 0017 上午 9:06
 **/
@Service
public class TExamManageServiceImpl implements TExamManageService{
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private TExamManagerDao tExamManagerDao;
//创建考试
    @Override
    public TExamInformationBean creatExamInformation(TExamInformationBean tExamInformationBean)throws Exception {
        //从session中获取登陆信息
        Account account=(Account) httpSession.getAttribute("Account");
        tExamInformationBean.setCreateBy(account.getUsername());
        tExamInformationBean.settId(tExamManagerDao.findTidByAid(account.getaId()));
        //设置试卷状态
        tExamInformationBean.setStutas(0);
        //设置创建时间
        tExamInformationBean.setCreateDate(new Date());
        //设置班级id
        tExamInformationBean.setClassId(tExamManagerDao.findCidByTid(account.getaId()));
        //通过老师确定老师所在班级id
        String cid=Long.toString(tExamManagerDao.findCidByTid(account.getaId()));
        // 通过学校 当前时间 试卷名称字母生成考试编号
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.setTime(new Date());
        String date=String.valueOf(c.get(Calendar.YEAR))+String.valueOf(c.get(Calendar.DATE))+
        String.valueOf(c.get(Calendar.HOUR_OF_DAY))+ String.valueOf(c.get(Calendar.MINUTE));
        //设置试卷编号
        tExamInformationBean.seteIId(Long.valueOf(cid+date));
        // 设置题的难度

        int a=0;//0为简单，1为困难
        String grade=tExamInformationBean.getGrade();
        if(grade.equals("七年级")||grade.equals("八年级")||grade.equals("九年级"))
        {
            a = 1;
        }
        //设置题的数量保存在json中
        QuesOfNumBean quesOfNumBean=new QuesOfNumBean();
        quesOfNumBean.setSumnum(50);
        if(a==0)
        {
            quesOfNumBean.setEasynum(50);
            quesOfNumBean.setHardnum(0);
        }else
        {
            int hardNum=(int)(50*0.7);
            quesOfNumBean.setHardnum(hardNum);
            quesOfNumBean.setEasynum(50-hardNum);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        tExamInformationBean.setProblemId(objectMapper.writeValueAsString(quesOfNumBean));
        tExamInformationBean.setExamLevel(a);
        tExamManagerDao.insertExamInformation(tExamInformationBean);
        return tExamInformationBean;

    }
    //查看考试按老师id查看
    @Override
    public List<TExamInforBean> findExamInforByTid(long aid)throws Exception{
        long tid=tExamManagerDao.findTidByAid(aid);
        return tExamManagerDao.selectExamInformationList(tid);
    }
    //查看考试按班级号查看
    @Override
    public List<TExamInforBean> findExamByCid(long aid)throws Exception{
        //通过账号信息找到老师id
        long tid=tExamManagerDao.findTidByAid(aid);
        //通过老师id找到班级id
        long cid=tExamManagerDao.findCidByTid(tid);
        return tExamManagerDao.selectExamInformationListByclass(cid);
    }

    //删除考试接口
    @Override
    public void deleteExamInformation(long id)throws Exception
    {
        tExamManagerDao.updateExamInformationStatuts(id);

    }

}
