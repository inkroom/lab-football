package cn.nsu.edu.web.four.daos.jdbc.referee;

import cn.nsu.edu.web.four.beans.matchStaff.MatchStaff;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;


public interface RefereeDao {

    //通过裁判员登陆后的赛程id查询主队，客队，时间，地点
    Schedule getScheduleInformation(@Param("scheduleId") Integer scheduleId) throws Exception;

    Team getTeamName(@Param("teamId") Integer teamId) throws Exception;

    Integer getTeamId(@Param("playerId") Integer playerId);

    List<Player> getPlayerInformation(@Param("teamId") Integer teamId) throws Exception;

    boolean insertPlayerInformation(@Param("playerId") Integer playerId, @Param("scheduleId") Integer scheduleId, @Param("teamId") Integer teamId, @Param("time") Date time) throws Exception;


    int updateScore(@Param("scoreA") int scoreA, @Param("scoreB") int scoreB, @Param("schId") int schId) throws Exception;

    int updateIsCheck(@Param("refId") int refId, @Param("isCheck") int isCheck) throws Exception;

}