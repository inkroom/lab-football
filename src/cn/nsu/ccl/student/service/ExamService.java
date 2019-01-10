package cn.nsu.ccl.student.service;

import cn.nsu.ccl.student.entity.ExaminationInfo;
import net.sf.json.JSONObject;

/**
 * 考试 service 接口
 *
 * @author 蔡娅蕊
 */
public interface ExamService {

    /**
     * <p>获取考生考试信息，包括考生信息，考试信息，题目概览</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @return
     * @Title: ExamService的getExaminationInfo方法
     * @Description: TODO(获取考试信息)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:58:52
     */
    public ExaminationInfo getExaminationInfo(String studentId, Integer examId);


    /**
     * <p>提交答案，并获取考题</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @param nowQueId  现在的题目编号，即数据库里的ID字段
     * @param swiQueId  下一道题的题目ID
     * @param answer    当前题目的答案
     * @return 获取到的题目json数据
     * @author qingyi xuelongqy@foxmail.com
     */
    public JSONObject swichQue(String studentId, Integer examId, int nowQueId, int swiQueId, String answer);


    /**
     * <p>结束考试</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @return 结束成功返回true
     * @author qingyi xuelongqy@foxmail.com
     */
    public boolean endExam(String studentId, Integer examId);

    /**
     * <p>初始化考试</p>
     *
     * @param studentId 考生编号
     * @param examId    考试编号
     * @return 包括初始信息的json数据
     * @author 墨盒 fuqianqing@163.com
     */

    public JSONObject initExam(String studentId, Integer examId);

}
