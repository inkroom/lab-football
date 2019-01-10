package com.nsu.service.student.exam.Impl;

import com.nsu.bean.student.exam.QuestionsBean;
import com.nsu.dao.student.exam.IStudentExamDao;
import com.nsu.entity.ExamInformation;
import com.nsu.entity.Problem;
import com.nsu.entity.Student;
import com.nsu.service.student.exam.IStudentExamService;
import com.nsu.utils.student.exame.ExamRecordHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * StudentExamServiceImpl类的描述
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @ClassName: StudentExamServiceImpl
 * @Package com.nsu.service.student.exam.Impl
 * @Description: 这里用一句话描述这个类的作用
 * @date 2017/7/19 9:34
 */
@Service
public class StudentExamServiceImpl implements IStudentExamService {

    //获取session
    @Resource
    private HttpSession session;

    //学生考试持久层
    @Resource
    private IStudentExamDao studentExamDao;


    /**
     * 获取考试问题信息(通过题目下标)
     *
     * @param index 题目的下标
     * @return object 题目信息
     * @throws IOException Json处理过程中的IO异常
     * @Title: loadAnswer
     * @Description: 通过问题下标获取问题信息
     * @author XueLong
     * @date 2017 -07-19 09:35:33
     */
    @Override
    public Object getQuestion(int index) throws Exception {
        //获取考试记录
        ExamRecordHelper examRecordHelper = (ExamRecordHelper)session.getAttribute("exam_record_helper");
        //取出考试记录中的考试问题
        QuestionsBean question = examRecordHelper.getQuestion(index);
        //判断以前是否取到过题目信息
        if (question.getQuestion() == null || question.getQuestion().equals("")){
            //若有则直接返回问题,若无则从数据库中获取信息
            Problem problem = studentExamDao.getQuestionById(question.getpId());
            question.setQuestion(problem.getProblem());
            question.setLevel(problem.getLevel());
            question.setSubject(problem.getSubject());
            question.setType(problem.getType());
            question.setSelects(ExamRecordHelper.jsonToSelectsMapAuto(problem.getSelect()));
        }
        return question;
    }

    /**
     * 提交考试问题作答答案
     *
     * @param index  当前考试问题的下标
     * @param answer 当前考试问题的作答答案
     * @return 提交是否成功
     * @throws IOException Json处理过程中的IO异常
     * @Title: submitAnswer
     * @Description: 提交考试问题作答答案
     * @author XueLong
     * @date 2017 -08-08 01:20:10
     */
    @Override
    public boolean submitAnswer(int index, String answer) throws IOException {
        //获取登陆的用户的学生信息
        Student student = (Student) session.getAttribute("student");
        //获取考试信息
        ExamInformation examInformation = (ExamInformation) session.getAttribute("exam_information");
        //获取考试记录
        ExamRecordHelper examRecordHelper = (ExamRecordHelper)session.getAttribute("exam_record_helper");
        //设置考试问题作答答案，并更新考试记录
        examRecordHelper.setAnswer(index,answer);
        studentExamDao.updateAnswers(student.getsId(),examInformation.geteIId(),examRecordHelper.getExamRecord().getStuAnswer());
        return true;
    }

    /**
     * 提交考试(修改考试已完成状态)
     *
     * @return 提交是否成功
     * @Title: submitExam
     * @Description: 提交考试
     * @author XueLong
     * @date 2017 -08-08 01:41:37
     */
    @Override
    public boolean submitExam() {
        //获取登陆的用户的学生信息
        Student student = (Student) session.getAttribute("student");
        //获取考试信息
        ExamInformation examInformation = (ExamInformation) session.getAttribute("exam_information");
        //提交考试
        studentExamDao.submitExam(student.getsId(),examInformation.geteIId());
        return true;
    }
}
