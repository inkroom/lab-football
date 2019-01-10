package com.nsu.dao.student.exam;

import com.nsu.entity.ExamInformation;
import com.nsu.entity.ExamRecord;
import com.nsu.entity.Problem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 学生考试管理持久层
 *
 * @author XueLong
 * @Title: IExamManagerDao
 * @Description: 学生考试管理持久层
 * @version: V1.0
 * @date 2017 -07-14 16:40:11
 */
@Mapper
public interface IExamManagerDao {

    //通过用户Id获取考试列表信息
    @Select(
            "SELECT * FROM exam_information WHERE exam_information.END_TIME > NOW() " +
                    "and exam_information.START_TIME < NOW() " +
                    "and exam_information.CLASS_ID in ( " +
                    "SELECT scm.CLASS_ID from stu_class_map scm,account a WHERE a.A_ID = #{a_id} AND scm.S_ID = a.PARENT_ID AND scm.`STATUS` = 1 " +
                    ")"
    )
    public List<ExamInformation> getExamListByAccountId(@Param("a_id") long a_id);

    //通过学生Id获取考试列表信息
    @Select(
            "SELECT * FROM exam_information WHERE exam_information.END_TIME > NOW() " +
                    "and exam_information.START_TIME < NOW() " +
                    "and exam_information.CLASS_ID in ( " +
                    "SELECT stu_class_map.CLASS_ID from stu_class_map WHERE " +
                    "stu_class_map.S_ID = #{s_id} AND stu_class_map.`STATUS` = 1 ) " +
                    "AND exam_information.E_I_ID NOT IN ( " +
                    "SELECT exam_record.E_I_ID FROM exam_record WHERE " +
                    "exam_record.S_ID = #{s_id} AND exam_record.EXAM_STATUS = 2 " +
                    ")"
    )
    public List<ExamInformation> getExamListByStudentId(@Param("s_id") long s_id);

    //通过考id获取考试信息
    @Select(
            "SELECT * FROM exam_information WHERE E_I_ID = #{e_i_id}"
    )
    public ExamInformation getExamInformationById(@Param("e_i_id") long e_i_id);

    //获取学生考试记录(通过学生Id和考试Id)
    @Select(
            "SELECT * FROM exam_record WHERE " +
                    "exam_record.S_ID = #{s_id} AND exam_record.E_I_ID = #{e_i_id}"
    )
    public ExamRecord getExamRecord(@Param("s_id") long s_id, @Param("e_i_id") long e_i_id);

    //给考生创建考试记录(数据为空,仅仅是创建)
    @Insert(
            "INSERT INTO exam_record(CREATE_BY,CREATE_DATE,OPER_BY,OPER_DATE,S_ID,E_I_ID,CLASS_ID) " +
                    "VALUES('XueLong',NOW(),'XueLong',NOW(),#{s_id},#{e_i_id},#{c_id})"
    )
    public int createExamRecord(@Param("s_id") long s_id,@Param("e_i_id") long e_i_id,@Param("c_id") long c_id);

    //给考生随机生成考试题目(学生id,考试id,考题数量)
    @Select(
            "SELECT * FROM problem p WHERE " +
                    "p.LEVEL = #{level} AND p.SUBJECT = #{subject} " +
                    "ORDER BY RAND() LIMIT 0,#{num_of_ques}"
    )
    public List<Problem> randExamQuestions(@Param("level") int level,@Param("subject") int subject, @Param("num_of_ques") int num_of_ques);

    //给考生插入生成的考试题目和空答案
    @Update(
            "UPDATE exam_record SET STU_ANSWER = #{stu_answer} WHERE S_ID = #{s_id} AND E_I_ID = #{e_i_id}"
    )
    public int createExamQuestions(@Param("s_id") long s_id, @Param("e_i_id") long e_i_id,@Param("stu_answer") String stu_answer);

    //提交考试,修改本场考试状态为考试中(通过学生Id和考试Id)
    @Update(
            "UPDATE exam_record SET EXAM_STATUS = 1 WHERE S_ID = #{s_id} AND E_I_ID = #{e_i_id}"
    )
    public int underExam(@Param("s_id") long s_id, @Param("e_i_id") long e_i_id);
}
