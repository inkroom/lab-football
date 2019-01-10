package com.nsu.service.student.exam;

import java.io.IOException;

/**
 * 学生考试服务接口
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @InterfaceName: IStudentExamService
 * @Package com.nsu.service.student.exam
 * @Description: 学生考试获取题目信息、考试作答等
 * @date 2017/7/19 9:02
 */
public interface IStudentExamService {
    //获取考试问题信息
    public Object getQuestion(int index) throws Exception;
    //提交问题作答答案
    public boolean submitAnswer(int index,String answer) throws IOException;
    //提交考试
    public boolean submitExam();
}
