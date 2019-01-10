package cn.nsu.ccl.student.dao;

import cn.nsu.ccl.student.entity.ExamInfo;
import cn.nsu.ccl.student.entity.ExamQueInfo;
import cn.nsu.ccl.student.entity.ExamineeInfo;
import cn.nsu.ccl.student.entity.QueInfo;

/**
 * <p>ExamDao类的描述</p>
 *
 * @author 欧磊
 * @ClassName: ExamDao
 * @Description: TODO(考试DAO层，获取相关数据)
 * @date 2016年11月27日 下午8:33:23
 */
public interface ExamDao {

    /**
     * <p>获取考生的具体信息，包括考生姓名和考试状态</p>
     *
     * @param studentId 考试编号
     * @param examId    考试编号
     * @return 考生信息
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public ExamineeInfo getExamIneeInfo(String studentId, int examId) throws Exception;


    /**
     * <p>获取考生的某场考试的信息，如开始时间，结束时间等</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @return 考试信息
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public ExamInfo getExamInfo(String studentId, int examId) throws Exception;


    /**
     * <p>获取考生的考题信息概览，只有ID和类型，初始化时使用</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @return 考试题目信息概览
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public ExamQueInfo getExamqueInfo(String studentId, int examId) throws Exception;


    /**
     * <p>提交考试答案</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @param nowQueId  现在的题目编号
     * @param answer    答案，json数组
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public boolean submitKey(String studentId, int examId, int nowQueId, String answer) throws Exception;

    /**
     * <p>获取某道题目的具体信息</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @param swiQueId  下一道题的题目id
     * @return 考试信息
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public QueInfo getSwichQue(String studentId, int examId, int swiQueId) throws Exception;


    /**
     * <p>结束考试</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @throws Exception sql语句错误
     * @author qingyi xuelongqy@foxmail.com
     */
    public boolean endExam(String studentId, int examId) throws Exception;


}
