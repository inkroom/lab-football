package cn.nsu.ccl.student.service.impl;


import cn.nsu.ccl.student.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.nsu.ccl.student.dao.ExamDao;
import cn.nsu.ccl.student.entity.ExamInfo;
import cn.nsu.ccl.student.entity.ExamQueInfo;
import cn.nsu.ccl.student.entity.ExaminationInfo;
import cn.nsu.ccl.student.entity.ExamineeInfo;
import cn.nsu.ccl.student.entity.QueInfo;
import cn.nsu.ccl.student.enviorment.DealQues;
import cn.nsu.ccl.student.service.ExamService;
import net.sf.json.JSONObject;


/**
 * <p>ExamServiceimpl类的描述</p>
 *
 * @author 欧磊
 * @ClassName: ExamServiceimpl
 * @Description: TODO(考试界面Service层)
 * @date 2016年11月27日 下午9:05:50
 */
@Service
public class StuExamServiceimpl extends DealQues implements ExamService {

    // Dao对象
    @Autowired
    private ExamDao examDao;

    @Override
    public ExaminationInfo getExaminationInfo(String studentId, Integer examId) {

        // 考试界面显示信息对象(考试信息、考生信息、开始考题信息)
        ExaminationInfo examinationInfo = new ExaminationInfo();
        ExamineeInfo examineeInfo;
        ExamQueInfo examQueInfo;
        ExamInfo examInfo;
        // 启用Dao层,获取数据(考试信息、考生信息、开始考题信息)
        try {
            examineeInfo = examDao.getExamIneeInfo(studentId, examId);
            examInfo = examDao.getExamInfo(studentId, examId);
            examQueInfo = examDao.getExamqueInfo(studentId, examId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // 逻辑处理，返回考场信息
        // if (examineeInfo == null || examInfo == null && examQueInfo == null)
        // {
        if (StringUtil.isNull(new Object[]{examineeInfo, examInfo, examQueInfo})) {
            System.out.println(
                    "examineeInfo=" + examineeInfo + "     examInfo=" + examInfo + "    examQueInfo=" + examQueInfo);
            // 数据获取失败
            return null;
        }
        // 数据获取成功并返回
        examinationInfo.setExamineeInfo(examineeInfo);
        examinationInfo.setExamInfo(examInfo);
        examinationInfo.setExamQueInfo(examQueInfo);
        return examinationInfo;
    }


    @Override
    public JSONObject swichQue(String studentId, Integer examId, int nowQueId, int swiQueId,
                               String answer) {
        // 考题对象，单个题目
        QueInfo queInfo = null;
        boolean result = false;
        answer = azyAnswer(answer);//将答案处理成json数组
        try {
            if (nowQueId == -1 && swiQueId != -1) {//开始获取第一道题，此时没有答案需要提交，只需要获取题目
                queInfo = examDao.getSwichQue(studentId, examId, swiQueId);
                if (queInfo != null){
                    result = true;
                    System.out.println("获取题目成功==");
                }

            } else if (swiQueId == -1 && nowQueId != -1) {//回答完最后一题，只需要提交答案
//                if (StringUtil.isJsonArray(answer) != null){
                    result = examDao.submitKey(studentId, examId, nowQueId, answer);
                    System.out.println("提交答案的结果="+result);
//                }

            } else {//答题中间，既需要提交答案，也需要获取题目
                queInfo = examDao.getSwichQue(studentId, examId, swiQueId);
                if (queInfo != null){
                    result = true;
                    System.out.println("获取题目成功==");
                }
                if (result){
                    result = examDao.submitKey(studentId, examId, nowQueId, answer);
                    System.out.println("提交答案的结果="+result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // 把queInfo转换为JSON对象,并返回
        JSONObject queInfoJson = new JSONObject();
        if (queInfo != null) {
            queInfoJson.put("queType", queInfo.getQueType());
            queInfoJson.put("queId", queInfo.getQueId());
            queInfoJson.put("queStem", queInfo.getQueStem());
            queInfoJson.put("queOptions", JSONObject.fromObject(queInfo.getQueOptions()));
            queInfoJson.put("queKeys", queInfo.getQueKey());//考生答案，json数组
        }
        queInfoJson.put("status", result);
        return queInfoJson;
    }


    @Override
    public boolean endExam(String studentId, Integer examId) {

        // 启用Dao层,向数据库提交考试结束信号，考生提交考试
        try {
            return examDao.endExam(studentId, examId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public JSONObject initExam(String studentId, Integer examId) {
        try {
            ExamQueInfo examQueInfo = examDao.getExamqueInfo(studentId, examId);
            // QueInfo firstQue = examDao.getSwichQue(examineeId, examId,
            // swiQueId)
            ExamInfo examInfo = examDao.getExamInfo(studentId, examId);
            System.out.println("ExamQueInfo=" + examQueInfo + "   ExamInfo=" + examInfo);
            JSONObject json = azyExam(examQueInfo, examInfo);
            JSONObject stuObj = new JSONObject();
            stuObj.put("id", studentId);
            // stuObj.put("name", value);
            json.put("student", stuObj);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
