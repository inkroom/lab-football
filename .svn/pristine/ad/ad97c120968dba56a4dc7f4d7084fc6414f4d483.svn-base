package cn.nsu.edu.web.four.services.referee.impl;

import cn.nsu.edu.web.four.daos.jdbc.referee.RefereeDao;
import cn.nsu.edu.web.four.dto.stc.referee.PlayerInformationDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.exception.RollbackException;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RefereeServiceImpl implements RefereeService {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    RefereeDao refereeDao;

    @Override
    public ScheduleInformationDto getScheduleInformation(Integer scheduleId) throws Exception {
        ScheduleInformationDto dto = new ScheduleInformationDto();
        try {

            dto.setScheduleInformation(refereeDao.getScheduleInformation(scheduleId));
            log.info("schId = "+scheduleId+"   "+dto.getScheduleInformation());
            dto.setTeamA(refereeDao.getTeamName(dto.getScheduleInformation().getTeamA()));
            dto.setTeamB(refereeDao.getTeamName(dto.getScheduleInformation().getTeamB()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    @Override
    public PlayerInformationDto getPlayerInformation(Integer teamId) throws Exception {
        PlayerInformationDto dto = new PlayerInformationDto();

        try {
            dto.setPlayer(refereeDao.getPlayerInformation(teamId));
            dto.setTeam(refereeDao.getTeamName(teamId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    @Transactional
    @Override
    public boolean insertPlayerInformation(List<Integer> playerAId, List<Integer> playerBId, Integer schedule, Integer teamAId, Integer teamBId,Integer refereeId) throws RollbackException {
        Date date = new Date();
        try {
            if (refereeDao.updateIsCheck(refereeId, 1) != 1) {
                throw new RollbackException("裁判员状态更新失败");
            }
            for (Integer id : playerAId) {
                if (!refereeDao.insertPlayerInformation(id, schedule, teamAId, date)) {
                    throw new RollbackException("球员" + id + "添加失败");
                }
            }
            for (Integer id : playerBId) {
                if (!refereeDao.insertPlayerInformation(id, schedule, teamBId, date)) {
                    throw new RollbackException("球员" + id + "添加失败");
                }
            }
            //更新裁判员是否验证状态
        } catch (Exception e) {
            throw new RollbackException(e);
//            return false;
        }
        return true;
    }

//    @Override
//    public boolean insertPlayerInformation(Integer playerId, Integer scheduleId) throws Exception {
//        boolean b = false;
//        try {
//            Integer teamId;
//            Date date = new Date();
//            teamId = refereeDao.getTeamId(playerId);
//            b = refereeDao.insertPlayerInformation(playerId, scheduleId, teamId, date);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return b;
//    }


}


