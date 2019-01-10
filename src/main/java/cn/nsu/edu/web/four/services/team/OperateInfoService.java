package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.teams.Team;

import java.util.Date;

public interface OperateInfoService {

    public  Integer  updatePlayer (int idTeam,int idPlayer,int status,Date time) ;

    public Integer  updateCoach (int idTeam,int idCoach,int status,Date time) ;

    public  Integer  retireTeam (int idTeam,int status,Date retireTime) ;

    public  Integer  upDateTeamInfo(Team team) ;


}
