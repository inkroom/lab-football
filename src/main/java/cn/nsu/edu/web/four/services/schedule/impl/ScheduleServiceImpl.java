package cn.nsu.edu.web.four.services.schedule.impl;


import cn.nsu.edu.web.four.beans.match.MatchStaffTeamId;
import cn.nsu.edu.web.four.beans.referee.Referee;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.match.MacthStaffDao;
import cn.nsu.edu.web.four.daos.jdbc.match.MatchDao;
import cn.nsu.edu.web.four.daos.jdbc.schedule.CurrentTeamDao;
import cn.nsu.edu.web.four.daos.jdbc.schedule.ScheduleDao;
import cn.nsu.edu.web.four.daos.jdbc.team.FindInfoDao;
import cn.nsu.edu.web.four.services.schedule.ScheduleService;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.nsu.edu.web.four.beans.schedule.ScheduleStatic.REFEREE_STATUS_VALID;
import static cn.nsu.edu.web.four.config.BaseStatic.KEY_ORGANIZATION_ID;
import static cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil.parseMd5WithSalt;

/**
 * @author 灵魂都在冒香气的神
 * @date 2018/3/20.
 */
@Service(value = "schedule")
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDao schMapper;
    @Autowired
    private MatchDao matchDao;
    @Autowired
    private CurrentTeamDao teamMapper;
    @Autowired
    private MacthStaffDao macthStaffMapper;
    @Autowired
    private FindInfoDao findInfoMapper;
    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public Boolean createSchedule(Schedule schedule)    //创建赛程
    {
        try {
            return schMapper.insertSelective(schedule) != 0;
        } catch (Exception e) {
            return false;
        }
    }

//    @Override
//    public List<Memchanism> getMemchanism()    //获取机构列表
//    {
//        try
//        {
//            List<Memchanism> list= mechMapper.getMechanismList();
//            return list.size()!=0?list:null;
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//    }

//    @Override
//    public List<CurrentTeam> getTeam(Integer orgId)    //获取机构所在队伍列表
//    {
//        List list=teamMapper.getTeamByOrg(orgId);
//        return list.size()!=0?list:null;
//    }

    /**
     * 修改赛程
     *
     * @param schedule
     * @return
     */
    @Override
    public Boolean modifySchedule(Schedule schedule) {
        try {
            return schMapper.updateByIdSelective(schedule) != 0;
        } catch (Exception se) {
            return false;
        }
    }

    /**
     * 删除赛程
     *
     * @param idSchedule
     * @return
     */
    @Override
    public Boolean deleteSchedule(Integer idSchedule) {
        try {
            return schMapper.deleteSch(idSchedule) != 0;
        } catch (Exception se) {
            se.printStackTrace();
            return false;
        }
    }

    @Override
    /**
     * 根据赛事ID获取赛程列表
     */ public List<Schedule> getScheduleByMatchID(Integer idMatch) {
        try {
            List<Schedule> list = schMapper.selectByMatchID(idMatch);
            return list.size() != 0 ? list : null;
        } catch (Exception se) {
            return null;
        }
    }

    @Override
    public Schedule getScheduleInfoById(Integer schId) {
        try {
            return schMapper.selectById(schId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getOrgIdByMatch(Integer matchId) {
        try {
            return matchDao.getOrgIdByMatch(matchId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Team> getTeamByMatchId(Integer matchId)   //根据赛事ID获取所有审核通过的参赛队伍
    {
        List<MatchStaffTeamId> list = macthStaffMapper.getTeamIdByMatch(matchId);
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Team> teamList = new ArrayList<>();
        for (int i = 0, len = list.size(); i < len; i++) {
            Integer idTeam = list.get(i).getTeamId();
            try {
                Team team = findInfoMapper.findTeamInfo(idTeam);
                if (team != null) {
                    teamList.add(team);
                }
            } catch (Exception e) {

            }
        }
        return teamList.size() == 0 ? null : teamList;
    }

    @Override
    public boolean createReferee(Referee referee) {
        try {
            String[] password = parseMd5WithSalt(referee.getPassword());
            referee.setPassword(password[0]);
            referee.setSalt(password[1]);
            referee.setStatus(REFEREE_STATUS_VALID);
            return schMapper.insertReferee(referee) != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Schedule getScheduleInfo(Integer schId) {
        try {
            return schMapper.selectById(schId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Referee checkReferee(Integer schId) {
        try {
            return schMapper.selectRefereeBySchId(schId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean updateRefereeBySchId(Referee referee) {
        try {
            String[] e = Md5EncryptUtil.parseMd5WithSalt(referee.getPassword());
            referee.setPassword(e[0]);
            referee.setSalt(e[1]);
            return schMapper.updateRefereeBySchId(referee) == 1;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean checkAuthority(Schedule schedule, HttpServletRequest request) {
        Integer matchId = schedule.getMatchId();
        Integer orgIdInSch = matchId == null ? getOrgIdBySchId(schedule.getIdSchedule()) : matchDao.getOrgIdByMatchId(matchId);
        Integer orgIdInSession = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
        return checkAuthority(orgIdInSch, orgIdInSession);
    }

    @Override
    public boolean checkAuthority(Integer scheduleId, HttpServletRequest request) {
        Integer orgIdInSession = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
        return checkAuthority(getOrgIdBySchId(scheduleId), orgIdInSession);
    }

    public boolean checkAuthority(Integer orgIdInSch, Integer orgIdInSession) {
        log.info("orgIdInMatch:" + orgIdInSch);
        log.info("orgIdInSession:" + orgIdInSession);
        if (!Objects.equals(orgIdInSch, orgIdInSession)) {
            return false;
        }
        return true;
    }

    private Integer getOrgIdBySchId(Integer idSchedule) {
        if (idSchedule != null) {
            Schedule schedule = schMapper.selectById(idSchedule);
            return matchDao.getOrgIdByMatchId(schedule.getMatchId());
        }
        return null;
    }

//    @Override
//    public boolean deleteRefereeBySchId(Integer idSch) {
//        try {
//            return schMapper.deleteRefereeBySchId(idSch);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
}
