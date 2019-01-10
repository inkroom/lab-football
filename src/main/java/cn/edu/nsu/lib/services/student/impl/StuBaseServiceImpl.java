package cn.edu.nsu.lib.services.student.impl;

import cn.edu.nsu.lib.bean.student.*;
import cn.edu.nsu.lib.dao.student.StuBasicDao;
import cn.edu.nsu.lib.services.student.StuBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ChenGang
 * @Title: StuBaseServiceImpl
 * @Package cn.edu.nsu.lib.services.student.impl
 * @Description:
 * @date 2017/10/23 0023 下午 11:20
 **/
@Service
public class StuBaseServiceImpl implements StuBaseService{
    @Autowired
    StuBasicDao stuBasicDao;

    @Override
    public void insertStuInfo(StuBasicBean stuBasicBean) throws Exception {

        stuBasicDao.insertStuInfo(stuBasicBean);
    }

    @Override
    public void reSetStuInfo(StuBasicBean stuBasicBean) throws Exception {
        stuBasicDao.reSetStuInfo(stuBasicBean);

    }

    @Override
    public Map<String, Object> getStudentInfo(long username) throws Exception {
        return stuBasicDao.getStudentInfo(username);
    }

    @Override
    public void exitLab(long id) throws Exception {
        stuBasicDao.exitLab(id);
        stuBasicDao.exitLab2(id);
    }

    public Map<String, Object> getStuInfo(long id) throws Exception {

        return stuBasicDao.getStudentUser(id);

    }

    @Override
    public List<StuPrizeBean> getProzeList(long id) throws Exception {
        return stuBasicDao.getProzeList(id);
    }

    @Override
    public void setProzeList(StuPrizeBean stuPrizeBean) throws Exception {
        stuBasicDao.setProzeList(stuPrizeBean);
    }

    @Override
    public List<StuScoreImplBean> getStuScoreList(long id) throws Exception {
//从后台获取学生成绩信息
        List<StuScoreBean> stuScore = stuBasicDao.getStuScoreList(id);
        //新建前台页面成绩信息列表

        List<StuScoreImplBean> stuScoreImpl = new ArrayList();

        //给列表设置不重复的课程名
        Map<String, String> course = new HashMap();
        if (stuScore != null) {

            int num = stuScore.size();
            for (int i = 0; i < num; i++) {
                String name = stuScore.get(i).getCourseName();
                if(!course.containsKey(name)) {

                    course.put(name, name);

                }

            }
        }
//设置课程

        Iterator iter = course.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();

            StuScoreImplBean ss = new StuScoreImplBean();

            ss.setCourse((String) entry.getKey());
            stuScoreImpl.add(ss);

        }

//设置分数
        if (stuScore != null) {


            for (int i = 0; i < stuScore.size(); i++) {
                String name = stuScore.get(i).getCourseName();
                int score = stuScore.get(i).getGrade();
                int term = stuScore.get(i).getTerm();


                    for(int j = 0; j < stuScoreImpl.size(); j++) {
                        if (name.equals(stuScoreImpl.get(j).getCourse())) {

                            switch (term) {
                                case 101:

                                    stuScoreImpl.get(j).setOne1(score);
                                    break;
                                case 102:
                                    stuScoreImpl.get(j).setOne2(score);
                                    break;
                                case 103:
                                    stuScoreImpl.get(j).setTwo1(score);
                                    break;
                                case 104:
                                    stuScoreImpl.get(j).setTwo2(score);
                                    break;
                                case 105:
                                    stuScoreImpl.get(j).setThree1(score);
                                    break;
                                case 106:
                                    stuScoreImpl.get(j).setThree2(score);
                                    break;
                            }
                        }
                    }




            }
        }

        return stuScoreImpl;
    }

    @Override
    public void insertPriceInfo(StuPrizeBean stuPrizeBean) throws Exception {
        stuBasicDao.setProzeList(stuPrizeBean);
    }

    @Override
    public ArrayList<SLabBean> getLabInfoList() throws Exception {
        return stuBasicDao.getLabInfoList();
    }

    @Override
    public ArrayList<SMajorBean> getMajorList() throws Exception {
        return stuBasicDao.getMajorList();
    }


}
