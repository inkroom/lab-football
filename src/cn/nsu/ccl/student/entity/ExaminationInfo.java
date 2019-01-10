package cn.nsu.ccl.student.entity;

/**
 * <p>描述：当前考场信息</p>
 * <p>类名：ExaminationInfo</p>
 * <p>创建时间：2016年8月29日</p>
 * <p>作用和功能：当前考场信息</p>
 * <p>使用说明：考生登录后，得到当前考场信息</p>
 *
 * @author 蔡娅蕊
 */
public class ExaminationInfo {

    private ExamQueInfo examQueInfo; // 考试题目信息

    private ExamineeInfo examineeInfo; // 考生信息

    private ExamInfo ExamInfo; // 考试信息

    /**
     * <p>获取考题信息</p>
     *
     * @return 考题信息
     * @author 墨盒 fuqianqing@163.com
     */
    public ExamQueInfo getExamQueInfo() {
        return examQueInfo;
    }

    public void setExamQueInfo(ExamQueInfo examQueInfo) {
        this.examQueInfo = examQueInfo;
    }

    /**
     * <p>获取考试信息</p>
     *
     * @return 考试信息
     * @author 墨盒 fuqianqing@163.com
     */
    public ExamineeInfo getExamineeInfo() {
        return examineeInfo;
    }

    /**
     * @param examineeInfo 考生信息
     */
    public void setExamineeInfo(ExamineeInfo examineeInfo) {
        this.examineeInfo = examineeInfo;
    }

    /**
     * @return 考试信息
     */
    public ExamInfo getExamInfo() {
        return ExamInfo;
    }

    /**
     * @param examInfo 考试信息
     */
    public void setExamInfo(ExamInfo examInfo) {
        ExamInfo = examInfo;
    }
}
