package cn.nsu.edu.web.four.daos.jdbc.schedule;

import cn.nsu.edu.web.four.beans.referee.Referee;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Xuing
 * @date 2018/3/20.
 */
public interface ScheduleDao {
    int deleteSch(Integer idSchedule);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    /**
     * 通过赛程ID获取赛程信息
     * @param idSchedule 赛程ID
     * @return 赛程Bean
     */
    Schedule selectById(Integer idSchedule);

    ArrayList<Schedule>selectByMatchID(Integer idMatch);

    int updateByIdSelective(Schedule record);

    int updateById(Schedule record);

    /**
     * 创建裁判员
     *
     * @param referee
     * @return 影响行数
     */
    int insertReferee(Referee referee);

    Referee selectRefereeBySchId(Integer schId);

    /**
     * 更新裁判员账号密码
     * 要求有裁判员id
     * @param referee 必须有赛程id
     * @return 结果
     */
    int updateRefereeBySchId(@Param("r") Referee referee);

    boolean deleteRefereeBySchId(Integer idSch);
}