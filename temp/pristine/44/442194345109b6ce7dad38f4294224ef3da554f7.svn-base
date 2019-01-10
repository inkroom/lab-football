package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.teams.TeamInfos;
import cn.nsu.edu.web.four.daos.jdbc.team.TeamInfosToTeamMapper;
import cn.nsu.edu.web.four.services.team.TeamInfosToTeamService;
import org.apache.ibatis.annotations.Param;
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
@Service("teamInfosToTeamServiceImpl")
public class TeamInfosToTeamServiceImpl implements TeamInfosToTeamService {
    @Autowired
    private TeamInfosToTeamMapper teamInfosToTeamMapper;
    @Override
    public int addTeamInfo(TeamInfos teamInfos) {
       return teamInfosToTeamMapper.insertTeamInfo(teamInfos);
    }

    @Override
    public Integer findTeamInfo(String teamName) {

        return teamInfosToTeamMapper.selectTeamInfo(teamName);
    }

    @Override
    public Integer findTeamName(String teamName) {
        return teamInfosToTeamMapper.selectTeamName(teamName);
    }
}
