package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;

import java.util.Date;
import java.util.List;

public interface FindInfoService {

    public List<Player> findSameOrgAllPlayer (int idOrg,int status) ;

    public  List<Coach> findSameOrgAllCoach (int idOrg,int status) ;

    public List<Team> findSameOrgAllTeam (int idOrg,int status) ;

    public Team findTeamInfo (int idTeam) ;

    //球队教练信息接口

    public  Integer  findTeamIdByCoachid (int idCoach,int status) ;

    public  Date findCoachJoinTeamTime (int idCoach, int status) ;

    public  Date findCoachOutTeamTime (int idCoach,int status) ;

    //球队球员信息接口

    public  Integer  findTeamIdByPlayerid (int  idPlayer,int status) ;

    public  Date findPlayerJoinTeamTime (int idPlayer,int status) ;

    public  Date findPlayerOutTeamTime (int idPlayer,int status) ;

    public Integer findCoachIdByIdTeam(int idTeam,int staus);

    public List<Integer> findPlayerIdByIdTeam(int idTeam,int staus);

    public List<Player> findPlayersByIdTeam(int idTeam,int status);

    public List<Player>  findPlayers(int idOrg, int status, int grade,String name);

    public List<Integer> findGradeByIdOrg(int orgId,int status);

    public List<Integer> findClassByGrade(int grade);

    public boolean findSameIdTeamAndPlayerId(Integer idTeam, Integer playerId);


}
