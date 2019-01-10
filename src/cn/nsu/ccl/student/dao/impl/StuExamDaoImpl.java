package cn.nsu.ccl.student.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.comm.enviorment.ComEnviorment;
import cn.nsu.ccl.student.dao.ExamDao;
import cn.nsu.ccl.student.entity.ExamInfo;
import cn.nsu.ccl.student.entity.ExamQueInfo;
import cn.nsu.ccl.student.entity.ExamineeInfo;
import cn.nsu.ccl.student.entity.QueInfo;

/**
 * <p>描述：考试界面Dao层<p>
 * <p>类名：ExamDaoImpl<p>
 * <p>开发人员：薛龙<p>
 * <p>创建时间：2016年8月27日<p>
 * <p>作用和功能：从服务器获取数据以及发送数据到服务器<p>
 * <p>使用说明：处理考试界面的与服务器的数据交互<p>
 *
 * @author 薛龙
 */

@Repository
public class StuExamDaoImpl extends ComEnviorment implements ExamDao {

    @Override
    public ExamineeInfo getExamIneeInfo(String studentId, int examId) throws Exception {
        // TODO Auto-generated method stub

        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, examId + ""}, "CAll GET_EXAMINEEINFO('?',?)");
        SqlRowSet rowSet = getJdbc().queryForRowSet(sql);

        // 处理数据，并返回考生信息
        if (rowSet.next()) {
            ExamineeInfo examineeInfo = new ExamineeInfo();
            examineeInfo.setStudentName(rowSet.getString("studentName"));
            examineeInfo.setExamStatus(rowSet.getInt("studentStatus"));
            return examineeInfo;
        }

        return null;
    }


    @Override
    public ExamInfo getExamInfo(String studentId, int examId) throws Exception {

        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, examId + ""}, "CAll GET_EXAMINFO('?',?)");
        SqlRowSet rowSet = getJdbc().queryForRowSet(sql);

        // 处理数据，并返回考生信息
        if (rowSet.next()) {
            ExamInfo examInfo = new ExamInfo();
            examInfo.setTeacherName(rowSet.getString("teacherName"));
//            examInfo.setExamId(rowSet.getInt("examId"));
            examInfo.setExamName(rowSet.getString("examName"));
            examInfo.setStartTime(rowSet.getDate("startTime"));
            examInfo.setEndTime(rowSet.getDate("endTime"));
            return examInfo;
        }
        return null;
    }


    @Override
    public ExamQueInfo getExamqueInfo(String studentId, int examId) throws Exception {

        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, examId + ""}, "CALL GET_EXAMQUESINFO('?',?)");
        SqlRowSet rowSet = getJdbc().queryForRowSet(sql);

        ExamQueInfo examQueInfo = new ExamQueInfo();
        examQueInfo.setExamId(examId);
        Map<String, ArrayList<Integer>> ques = new HashMap<>();//题目数据
        Map<String, Integer> quesNum = new HashMap<>();//题目数量
        examQueInfo.setExamQues(ques);
        examQueInfo.setExamQueNum(quesNum);
        boolean flag = false;
        // 处理数据，并返回考生信息
        while (rowSet.next()) {
//            examQueInfo.setExamId(rowSet.getString("examId"));
            String type = rowSet.getString("types");

            ArrayList<Integer> ids = ques.get(type);
            if (quesNum.get(type) == null) {
                quesNum.put(type, 0);
            }
            if (ids == null) {//题目数据
                ids = new ArrayList<>();
                ques.put(type, ids);
            }
            flag = true;
            quesNum.put(type, quesNum.get(type) + 1);
            ids.add(rowSet.getInt("questionId"));
        }
        System.out.println("获取的题目=" + ques);

        return flag ? examQueInfo : null;
    }


    @Override
    public boolean submitKey(String studentId, int examId, int queId, String answer) throws Exception {
        // TODO Auto-generated method stub
        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, String.valueOf(examId), String.valueOf(queId), answer},
                "CALL SUBMIT_KEY('?',?,'?','?')");
        return getJdbc().update(sql) == 1;
    }


    @Override
    public QueInfo getSwichQue(String studentId, int examId, int swiQueId) throws Exception {
        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, examId + "", swiQueId + ""}, "CALL GET_SWICHQUE('?',?,'?')");
        SqlRowSet rowSet = getJdbc().queryForRowSet(sql);

        // 处理数据，并返回考生信息
        if (rowSet.next()) {
            QueInfo queInfo = new QueInfo();
            queInfo.setQueId(swiQueId);
//            queInfo.setQueId(rowSet.getString("questionId"));
            queInfo.setQueStem(rowSet.getString("question"));
            queInfo.setQueOption(rowSet.getString("choice"));
            queInfo.setQueKey(rowSet.getString("answerStudent"));
//            System.out.println("getSwichQue  " + queInfo.getQueKey());
            queInfo.setQueType(rowSet.getString("types"));
            return queInfo;
        }
        return null;
    }


    @Override
    public boolean endExam(String studentId, int examId) throws Exception {
        // 创建sql语句，调用数据库
        String sql = GET_SQL(new String[]{studentId, examId + ""}, "CALL UPDATE_STATUS('?','?',2)");
        return getJdbc().update(sql) == 1;
    }

}
