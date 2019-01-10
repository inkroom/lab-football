package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.team.OperateInfoDao;
import cn.nsu.edu.web.four.services.team.OperateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OperateInfoServiceImpl implements OperateInfoService {


   @Autowired
   private OperateInfoDao operateInfoDao;


    @Override
    public Integer updatePlayer(int idTeam, int idPlayer, int status, Date time)  {

        try {
            return operateInfoDao.updatePlayer(idTeam,idPlayer,status,time);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer updateCoach(int idTeam, int idCoach, int status, Date time) {

        try {
             return  operateInfoDao.updateCoach(idTeam,idCoach,status,time);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer retireTeam(int idTeam, int status, Date retireTime){

        try {
            return operateInfoDao.retireTeam(idTeam,status,retireTime);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer upDateTeamInfo(Team team) {

        try {
            return operateInfoDao.upDateTeamInfo(team);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
