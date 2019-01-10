package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.daos.jdbc.team.TeamInfosToPlayerMapper;
import cn.nsu.edu.web.four.services.team.TeamInfosToPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
@Service("teamInfosToPlayerServiceImpl")
public class TeamInfosToPlayerServiceImpl implements TeamInfosToPlayerService {
    @Autowired
    private TeamInfosToPlayerMapper teamInfosToPlayerMapper;
    @Override
    public Integer addPlayerInfo(ArrayList<Integer> playerIds,Integer teamId) {
        return teamInfosToPlayerMapper.insertPlayerInfo(playerIds,teamId);

    }

    @Override
    public List<Integer> findAllPlayersByTeamId(Integer dbTeamId) {
       return teamInfosToPlayerMapper.selectAllPlayersByTeamId(dbTeamId);

    }
}
