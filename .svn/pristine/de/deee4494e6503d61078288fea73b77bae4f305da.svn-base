package cn.nsu.edu.web.four.services.schedule;

import cn.nsu.edu.web.four.beans.referee.Referee;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.beans.teams.Team;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 灵魂都在冒香气的神
 * @date 2018/3/20.
 */
public interface ScheduleService {
    Boolean createSchedule(Schedule schedule);    //创建赛程

//    List<Memchanism> getMemchanism();   删除，更新为MatchService里的getMatchOrgList()

//    List<CurrentTeam> getTeam(Integer orgId);    删除 更新为MatchService里的getMatchStaffTeamList()

    Boolean modifySchedule(Schedule schedule);     //修改赛程

    Boolean deleteSchedule(Integer idSchedule);   //删除赛程

    List<Schedule> getScheduleByMatchID(Integer idMatch);    //根据赛事ID获取赛程列表

    /**
     * 通过赛程ID获取赛程信息
     *
     * @param schId 赛程ID
     * @return 赛程信息
     */
    Schedule getScheduleInfoById(Integer schId);

    Integer getOrgIdByMatch(Integer matchId);

    List<Team> getTeamByMatchId(Integer matchId);   //根据赛事ID获取所有审核通过的参赛队伍

    /**
     * 创建裁判员
     *
     * @param referee 裁判员信息
     * @return true成功 false 失败
     */
    boolean createReferee(Referee referee);

    /**
     * 通过赛程Id获取赛程信息
     *
     * @param schId 赛程Id
     * @return 队伍列表
     * @author Xuing
     */
    Schedule getScheduleInfo(Integer schId);

    /**
     * 通过赛程Id返回裁判员信息，没有返回null。
     *
     * @param idSch 赛程Id
     * @return 裁判员信息
     */
    Referee checkReferee(Integer idSch);

    /**
     * 更新裁判员账号密码，裁判员必须有赛程id
     * @param referee 裁判员，必须有赛程id
     * @return
     */
    boolean updateRefereeBySchId(Referee referee);


    boolean checkAuthority(Integer scheduleId, HttpServletRequest request);

    boolean checkAuthority(Schedule schedule, HttpServletRequest request);


}
