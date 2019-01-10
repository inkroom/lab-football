package cn.nsu.edu.web.four.daos.jdbc.team;

import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;

import java.util.Date;
import java.util.List;

import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author :孙帅
 * @Description: 信息查询的dao
 * @date :16:33 2018/3/16
 */

public interface FindInfoDao {

    /**
     * 根据id_org查询所有的球员
     */

    public List<Player> findSameOrgAllPlayer(@Param("idOrg") int idOrg,
                                             @Param("status") int status
                                             ) throws  Exception;

    /**
     * 根据id_org查询所有的教练
     */
    public  List<Coach> findSameOrgAllCoach(@Param("idOrg") int idOrg,
                                            @Param("status") int status
                                            ) throws  Exception;


    /**
     * 根据id_org查询所有的team
     */

    public List<Team> findSameOrgAllTeam(@Param("idOrg") int idOrg,
                                         @Param("status") int status
                                        ) throws  Exception;

    /**
     *根据id_team查询team具体信息
     */

    public Team findTeamInfo(@Param("idTeam")int idTeam) throws  Exception;



    //球队教练信息查询

    /**
     *查询教练队伍信息返回球队id
     * 映射表
     */

    public Integer findTeamByCoachid(@Param("idCoach") int idCoach,
                                  @Param("status") int status) throws  Exception;

    /**
     *查询教练队伍信息,返回join时间
     *
     */

    public Date findCoachJoinTeamTime(@Param("idCoach") int idCoach,
                                       @Param("status") int status) throws  Exception;

    /**
     *查询教练队伍信息，返回outTeam时间
     *
     */

    public Date findCoachOutTeamTime(@Param("idCoach") int idCoach,
                                      @Param("status") int status) throws  Exception;





    //球队球员信息查询



    /**
     *查询球员队伍信息返回球队id
     *
     */

    public Integer findTeamByPlayerid(@Param("idPlayer") int idPlayer,
                                  @Param("status") int status) throws  Exception;

    /**
     *查询球员队伍信息,返回join时间
     *
     */

    public Date findPlayerJoinTeamTime(@Param("idPlayer") int idPlayer,
                                       @Param("status") int status) throws  Exception;

    /**
     *查询球员队伍信息返回out时间
     *
     */

    public Date findPlayerOutTeamTime(@Param("idPlayer") int idPlayer,
                                      @Param("status") int status) throws  Exception;

    /**
     * 通过orgId和grade，status查询球员
     *
     */

    public List<Player>  findPlayers(@Param("idOrg") int idOrg,
                                     @Param("status") int status,
                                     @Param("grade") int grade,
                                     @Param("name") String name)throws Exception;


    /**
     * 通过idTeam查询coachId
     */

    public  Integer findCoachIdByIdTeam(@Param("idTeam") int idTeam,
                                        @Param("status") int status)throws  Exception;


    /**
     * 通过idTeam查询playerId
     */

    public List<Integer> findPlayerIdByIdTeam(@Param("idTeam") int idTeam,
                                        @Param("status") int status)throws Exception;

    /**
     *
     *
     */


    /**
     *
     * 通过orgId查询grade
     */

    public List<Integer> findGradeByIdOrg(@Param("orgId")int orgId,
                                          @Param("status")int status)throws Exception;


    /*
    *通过 grade查询classes
     */

    public List<Integer> findClassByGrade(@Param("grade") int grade) throws Exception;


    /**
     * 通过idTeam和idPlayer查询playerId
     */

    public Integer findSameIdTeamAndPlayerId(@Param("idTeam") int idTeam,
                                                   @Param("playerId")int playerId)throws Exception;




}
