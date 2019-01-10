package com.nsu.service.student.exam.Impl;

import com.nsu.dao.student.exam.IExamManagerDao;
import com.nsu.entity.*;
import com.nsu.service.student.exam.IExamManageService;
import com.nsu.utils.jedis.JedisClientPool;
import com.nsu.utils.student.exame.ExamRecordHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 学生考试管理服务类
 *
 * @author XueLong
 * @Title: ExamManageServiceImpl
 * @Description: 学生考试管理服务类
 * @version: V1.0
 * @date 2017 -07-14 16:29:15
 */
@Service
public class ExamManageServiceImpl implements IExamManageService {

    @Autowired
    private JedisClientPool pool;

    //获取session
    @Resource
    private HttpSession session;

    //学生考试管理持久层
    @Resource
    private IExamManagerDao examManagerDao;

    /**
     * 获取学生最近考试信息
     *
     * @return object 学生最近考试信息的集合
     * @Title: getExamList
     * @Description: 获取学生最近考试信息
     * @author XueLong
     * @date 2017 -07-14 16:31:46
     */
    @Override
    public Object getExamList() {
        //获取登陆的用户
        Student student = (Student)session.getAttribute("student");
        //返回数据
        return examManagerDao.getExamListByStudentId(student.getsId());
    }

    /**
     * 学生参加考试服务
     * 如果第一次参加这场考试，初始化学生考试记录
     * 如果已经参加过考试，则使用以前的记录
     *
     * @param e_I_Id 考试Id
     * @return boolean 是否成功参加考试
     * @Title: joinExam
     * @Description: 学生参加考试，初始化学生考试记录
     * @author XueLong
     * @date 2017 -07-18 10:24:49
     */
    @Override
    @Transactional
    public boolean joinExam(long e_I_Id) throws Exception {
        //获取登陆的用户的学生信息
        Student student = (Student) session.getAttribute("student");
        //获取考试信息
        ExamInformation examInformation = examManagerDao.getExamInformationById(e_I_Id);
        if (examInformation == null){
            throw new Exception("考试信息不正确!");
        }
        //获取学生考试记录
        ExamRecord examRecord = examManagerDao.getExamRecord(student.getsId(),e_I_Id);
        //判断学生考试记录是否存在，没有则创建
        if (examRecord == null) {
            //为学生创建考试记录
            examManagerDao.createExamRecord(student.getsId(), e_I_Id, student.getsCId());
        }
        //判断学生考试记录是否为空，为空则生成考题和空答案
        if (examRecord == null || examRecord.getStuAnswer() == null || examRecord.getStuAnswer().equals("")){
            //为学生随机生成考试题目
            ArrayList<Problem> queList = new ArrayList<>();
            //获取出题规则Map
            LinkedHashMap<Integer,Integer> ruleOfQues = ExamRecordHelper.makeRuleOfQues(examInformation.getNumOfQues());
            //遍历生成
            ruleOfQues.forEach((level,num)->{
                queList.addAll(examManagerDao.randExamQuestions(level,examInformation.getSubject(),num));
            });
            //将考题和空答案转成json格式存入数据库字段中
            examManagerDao.createExamQuestions(student.getsId(),e_I_Id, ExamRecordHelper.makeAnswersJson(queList));
            //修改考试状态为考试中
            examManagerDao.underExam(student.getsId(),examInformation.geteIId());
            //重新获取学生考试记录
            examRecord = examManagerDao.getExamRecord(student.getsId(),e_I_Id);
        }
        //判断是否为已考试状态
        if (examRecord.getExamStatus() == 2){
            throw new Exception("已考试完成,不能再次考试!");
        }
        // TODO: 2017/10/20 将考试时长存入redis
        //将考试信息和考试记录存入session，以便考试调用
        session.setAttribute("exam_information",examInformation);
        session.setAttribute("exam_record_helper",new ExamRecordHelper(examRecord));
        return true;
    }

    /**
     * 验证考试前身份信息
     *
     * @param IDCard 身份证号码
     * @return 验证结果
     * @Title: checkExamInfo
     * @Description: 验证考试前身份信息
     * @author qXueLong
     * @date 2017 -08-10 16:26:07
     */
    @Override
    public boolean checkExamInfo(String IDCard) {
        if (IDCard == null || "".equals(IDCard)){
            return false;
        }
        return IDCard.equals(((Account)session.getAttribute("account")).getIdCard());
    }
}
