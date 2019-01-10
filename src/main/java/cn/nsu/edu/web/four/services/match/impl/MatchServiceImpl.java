package cn.nsu.edu.web.four.services.match.impl;

import cn.nsu.edu.web.four.beans.match.*;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.match.MatchDao;
import cn.nsu.edu.web.four.services.match.MatchService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Consumer;

import static cn.nsu.edu.web.four.config.BaseStatic.KEY_ORGANIZATION_ID;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.services.match.impl
 * @date 2018/3/20 11:07
 * @description
 **/
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDao mapper;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public List<Match> selectByIdWithOrg(int orgId) {
        try {
            return mapper.selectByIdWithOrg(orgId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Match> selectByStaff(int orgId) {
        try {
            return mapper.selectByStaff(orgId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Match> selectByStaffOther(int orgId) {
        try {
            List<Match> list = mapper.selectByStaffOther(orgId);
//            Iterator<Match> ite = list.iterator();
//            while (ite.hasNext()) {
//                Match match = ite.next();
//                if (mapper.statusCount(match.getIdMatch()) != 0) {
//                    ite.remove();
//                }
//            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Map<String, Object>> listTeamAndPlayer(int orgId, int mid) {
        List<Map<String, Object>> teamlist = null;
        try {
            List<MatchPlayer> players = mapper.selectPlayerByOrgId(orgId);
            teamlist = new ArrayList<>();
            for (Map<String, Object> map : mapper.listTeamIdByorgId(orgId)) {
                Map<String, Object> teamMap = new HashMap<>();
                teamMap.put("teamName", map.get("name"));
                List<MatchPlayer> playerList = new ArrayList<>();
                for (MatchPlayer player : players) {
                    if (map.get("id_team").equals(player.getTeamId())) {
                        player.setStatus(mapper.selectMstaff(player.getPlayerId(), mid, (Integer) map.get("id_team")));
                        playerList.add(player);
                    }
                }
                teamMap.put("playerList", playerList);
                teamlist.add(teamMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return teamlist;
    }

    @Override
    @Transactional
    public int insertPlayer(List<MatchPlayer> list, Integer matchId, Integer orgId) {
        try {
            if (mapper.statusCount(matchId, orgId) != 0) {
                return -1;
            }
            Date date = new Date();
            list.forEach(new Consumer<MatchPlayer>() {
                @Override
                public void accept(MatchPlayer matchPlayer) {
                    matchPlayer.setMatchId(matchId);
                    matchPlayer.setOrgId(orgId);
                    matchPlayer.setTime(date);
                }
            });
            int a = mapper.deleteStaff(orgId, matchId);
            return mapper.insertPlayer(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MatchExamine> listExamine(int matchId) {
        try {
            return mapper.selectExamineOrg(matchId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Player> listExaminePlayer(int teamId, int mid) {
        try {
            return mapper.selectExaminePlayer(teamId, mid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TeamInfo> listExamineTeam(int mId, int oid) {
        try {
            return mapper.selectExamineTeam(mId, oid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStaffStatus(int status, int orgId, int mid) {
        try {
            mapper.updateStaffStatus(status, orgId, mid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean checkMatchName(String name, Integer matchId) {
        MatchExample example = new MatchExample();
        MatchExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        if (matchId != null) {
            criteria.andIdMatchNotEqualTo(matchId);
        }
        int count = mapper.countByMatchName(example);
        return count == 0;
    }

    @Override
    public boolean insertMatch(Match match) {
        try {
            mapper.insert(match);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer getLevel(int matchId) {
        try {
            return mapper.getLevel(matchId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Match getMatchInfoById(Integer matchId) {
        try {
            return mapper.selectById(matchId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean modifyMatch(Match match) {
        try {
            if (mapper.updateById(match) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Organization> getMatchOrgList(Integer matchId) {
        try {
            return mapper.selectMatchStaffOrgsByMatchId(matchId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getMatchStaffTeamList(Integer matchId, Integer orgId) {
        try {
            return mapper.selectMatchStaffTeamsByMatchOrgId(matchId, orgId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkMatchDate(Date beforeDate, Date afterDate) {
        return beforeDate.before(afterDate);
    }

    @Override
    public int checkOrgStatus(Integer orgId, Integer mid) {
        try {
            Integer status = mapper.checkOrgStatus(orgId, mid);
            if (status == null) {
                return 0;
            } else {
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean checkAuthority(Integer matchId, HttpServletRequest request) {
        Integer orgIdInSession = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
        Integer orgIdInMatch = mapper.getOrgIdByMatchId(matchId);
        log.info("orgIdInMatch:" + orgIdInMatch);
        log.info("orgIdInSession:" + orgIdInSession);
        if (!Objects.equals(orgIdInMatch, orgIdInSession)) {
            return false;
        }
        return true;
    }

}
