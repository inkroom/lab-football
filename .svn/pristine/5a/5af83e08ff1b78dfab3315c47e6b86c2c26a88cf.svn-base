package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.teams.TeamInfos;
import cn.nsu.edu.web.four.daos.jdbc.team.TeamInfosToCoachMapper;
import cn.nsu.edu.web.four.services.team.TeamInfosToCoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
@Service("teamInfosToCoachServiceImpl")
public class TeamInfosToCoachServiceImpl implements TeamInfosToCoachService {
    @Autowired
    private TeamInfosToCoachMapper teamInfosToCoachMapper;
    @Override
    public int addCoachInfo(TeamInfos teamInfos) {
        return teamInfosToCoachMapper.insertCoachInfo(teamInfos);

    }

    @Override
    public Integer findCoachByOrg(TeamInfos teamInfos) {

        return teamInfosToCoachMapper.selectCoachByOrg(teamInfos);
    }
}
