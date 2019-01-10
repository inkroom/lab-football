package cn.nsu.edu.web.four.daos.jdbc.coach;

import cn.nsu.edu.web.four.beans.coach.Coach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoachDao {
    //查询全部教练
    List<Coach> getAllCoach(@Param("org_id") Integer org_id, @Param("status") Integer status) throws Exception;

    //查询单个教练
    Coach getCoach(@Param("id_coach") Integer id_coach) throws Exception;

    //增加单个教练
    int addCoach(Coach coach) throws Exception;

    //修改教练信息（身高，体重，电话）
    int updateCoach(Coach coach) throws Exception;

    //修改头像
    int updatePhoto(Coach coach) throws Exception;

    //删除教练（修改教练status值不删除数据）
    int coachStatus(Coach coach) throws Exception;
}
