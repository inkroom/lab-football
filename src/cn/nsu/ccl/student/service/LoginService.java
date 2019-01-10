package cn.nsu.ccl.student.service;

/**
 * <p>考生端登录service</p>
 *
 * @author 墨盒
 */
public interface LoginService {

    /**
     * 考生登录
     *
     * @param studentId   学生编号
     * @param studentName 学生姓名
     * @param keyWord     考试口令
     * @return 考试编号，失败返回-1
     */
    public int login(String studentId, String studentName, String keyWord);

    /**
     * 记录考生信息
     *
     * @param studentID 考生编号
     * @param ExamId    考试Id
     * @param ipAdress  考试ip地址
     * @param status    考生状态
     * @return 记录成功返回true，失败返回false
     */
    public boolean updateStatus(String studentID, int ExamId, int status);
}
