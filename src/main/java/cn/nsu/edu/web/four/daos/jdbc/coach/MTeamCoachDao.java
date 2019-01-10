package cn.nsu.edu.web.four.daos.jdbc.coach;

import cn.nsu.edu.web.four.beans.coach.MTeamCoach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MTeamCoachDao {
    //根据教练id查询教练与球队的映射表
    List<MTeamCoach> getAllTeamByCoach(@Param("coach_id") Integer coach_id) throws Exception;

}
