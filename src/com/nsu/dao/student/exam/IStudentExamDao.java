package com.nsu.dao.student.exam;

import com.nsu.entity.Problem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 学生考试持久层
 *
 * @author XueLong
 * @version V1.0
 * @InterfaceName: IStudentExamDao
 * @Package com.nsu.dao.student.exam
 * @Description: 学生考试操作、获取题目信息、作答记录等
 * @date 2017/7/19 9:37
 */
public interface IStudentExamDao {
    //通过题目id获取从题库题目信息
    @Select(
            "SELECT * FROM problem WHERE P_ID = #{p_id}"
    )
    public Problem getQuestionById(@Param("p_id") long p_id);

    //更新考试作答答案(通过学生Id和考试Id)
    @Update(
            "UPDATE exam_record SET STU_ANSWER = #{answers} WHERE S_ID = #{s_id} AND E_I_ID = #{e_i_id}"
    )
    public int updateAnswers(@Param("s_id") long s_id, @Param("e_i_id") long e_i_id,@Param("answers") String anwsers);

    //提交考试,修改本场考试状态未已考试(通过学生Id和考试Id)
    @Update(
            "UPDATE exam_record SET EXAM_STATUS = 2 WHERE S_ID = #{s_id} AND E_I_ID = #{e_i_id}"
    )
    public int submitExam(@Param("s_id") long s_id, @Param("e_i_id") long e_i_id);
}
