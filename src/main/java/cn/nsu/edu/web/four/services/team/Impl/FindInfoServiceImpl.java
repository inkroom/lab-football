package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.player.PlayerDao;
import cn.nsu.edu.web.four.daos.jdbc.team.FindInfoDao;
import cn.nsu.edu.web.four.services.team.FindInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FindInfoServiceImpl implements FindInfoService {

    @Autowired
    private FindInfoDao findInfoDao;

    @Autowired
    private PlayerDao playerDao;


    //球队教练信息查询

    @Override
    public Integer findTeamIdByCoachid(int idCoach, int status) {

        try {
            return   findInfoDao.findTeamByCoachid(idCoach,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Date findCoachJoinTeamTime(int idCoach, int status) {

        try {
          return   findInfoDao.findCoachJoinTeamTime(idCoach,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Date findCoachOutTeamTime(int idCoach, int status) {

        try {
           return findInfoDao.findCoachOutTeamTime(idCoach,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //球队球员信息查询

    @Override
    public Integer findTeamIdByPlayerid(int idPlayer, int status) {

        try {
            return  findInfoDao.findTeamByPlayerid(idPlayer,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Date findPlayerJoinTeamTime(int idPlayer, int status) {

        try {
           return  findInfoDao.findPlayerJoinTeamTime(idPlayer,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Date findPlayerOutTeamTime(int idPlayer, int status) {

        try {
           return findInfoDao.findPlayerOutTeamTime(idPlayer,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer findCoachIdByIdTeam(int idTeam, int staus) {
        try {
            return findInfoDao.findCoachIdByIdTeam(idTeam,staus);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findPlayerIdByIdTeam(int idTeam, int staus) {
        try {
            return findInfoDao.findPlayerIdByIdTeam(idTeam,staus);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //球队

    @Override
    public List<Player> findSameOrgAllPlayer(int idOrg, int status)  {

        try {
            return  findInfoDao.findSameOrgAllPlayer(idOrg,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Player> findPlayers(int idOrg, int status, int grade, String name) {
        try {
            return  findInfoDao.findPlayers(idOrg,status,grade,name);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<Coach> findSameOrgAllCoach(int idOrg, int status)  {

        try {
            return  findInfoDao.findSameOrgAllCoach(idOrg,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> findSameOrgAllTeam(int idOrg, int status)  {

        try {
            return  findInfoDao.findSameOrgAllTeam(idOrg,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team findTeamInfo(int idTeam)  {

        try {
            return  findInfoDao.findTeamInfo(idTeam);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findGradeByIdOrg(int orgId, int status) {

        try {
            return  findInfoDao.findGradeByIdOrg(orgId,status);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean findSameIdTeamAndPlayerId(Integer idTeam, Integer playerId) {
        try {
            if (findInfoDao.findSameIdTeamAndPlayerId(idTeam,playerId) != null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findClassByGrade(int grade) {
        try {
            return  findInfoDao.findClassByGrade(grade);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<Player> findPlayersByIdTeam(int idTeam, int status) {
        try {
            List<Player> playerIdByIdTeam= new ArrayList<Player>();
            List<Integer> playerIds = findInfoDao.findPlayerIdByIdTeam(idTeam,status);
            for (Integer playerid : playerIds){
               playerIdByIdTeam.add(playerDao.selectPlayerById(playerid));
            }
            return  playerIdByIdTeam;
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException(e);
        }

    }
}
