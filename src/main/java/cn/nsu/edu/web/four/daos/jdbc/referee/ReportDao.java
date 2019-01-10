package cn.nsu.edu.web.four.daos.jdbc.referee;

import cn.nsu.edu.web.four.beans.referee.PlayerDescription;
import cn.nsu.edu.web.four.beans.referee.Report;
import cn.nsu.edu.web.four.beans.referee.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportDao {


    int insertReport(@Param("r") Report report) throws Exception;

    // TODO: 18-3-26 多行插入暂不可行，可能是传入多个参数的原因
    int insertPlayer(@Param("p") PlayerDescription player, @Param("reportId") int reportId) throws Exception;
//    int insertPlayer(@Param("list") List<PlayerDescription> list, @Param("reportId") int reportId) throws Exception;

    List<PlayerDescription> selectPlayer(@Param("schId") int schId) throws Exception;

    Schedule selectSchedule(@Param("schId") int schId) throws Exception;

    int updateScheduleStatus(@Param("schId") int schId, @Param("status") int status) throws Exception;

    int updateCoachScore(@Param("teamId") int teamId, @Param("score") int score) throws Exception;

    int updateTeamScore(@Param("teamId") int teamId, @Param("score") int score) throws Exception;

    int updatePlayerScore(@Param("playerId") int playerId, @Param("score") int score) throws Exception;


    int deleteMessage(@Param("schId") int schId) throws Exception;

}
