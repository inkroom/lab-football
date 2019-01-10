package cn.nsu.edu.web.four.services.team.Impl;


import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.team.CreateTeamDao;
import cn.nsu.edu.web.four.services.team.CreateTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CreateTeamServiceImpl implements CreateTeamService {

    @Autowired
    private CreateTeamDao createTeamDao;

    public int createTeam(Team team) {

        try {
           return  createTeamDao.createTeam(team);
        } catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }


    }


    public int addPlayer(List<Integer> list, int idTeam, int status, Date time)  {

        try {
            return createTeamDao.addPlayer(list,idTeam,status,time);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    public int addCoach(int idTeam, int idCoach, int status, Date time) {

        try {
            return createTeamDao.addCoach(idTeam,idCoach,status,time);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
