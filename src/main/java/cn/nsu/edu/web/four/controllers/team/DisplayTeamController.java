package cn.nsu.edu.web.four.controllers.team;


import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.coach.CoachService;
import cn.nsu.edu.web.four.services.player.impl.PlayerServiceImpl;
import cn.nsu.edu.web.four.services.team.Impl.FindInfoServiceImpl;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "team")

public class DisplayTeamController {

    @Autowired
    private FindInfoServiceImpl findInfoService;

    @Autowired
    private  PlayerServiceImpl  playerService;

    @Autowired
    private CoachService coachService;

    //日志文件
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "display")
    @Security(roles = Role.SCHOOL)
    public String displayTeam(HttpServletRequest request){
        //request.getSession().setAttribute("base_path",request.getContextPath());
        return  "team/displayTeam";
    }

    @RequestMapping(value = "findTeams")
    @ResponseBody
    @Security(roles = Role.SCHOOL,checkToken = true)
    public  MessageDto findTeams(@RequestParam("page") String pg, HttpServletRequest request){
        //分页插件
        Integer page = ParseUtil.parseInt(pg);
        if (page == null){
            return  new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer idOrg = ParseUtil.parseInt(RequestUtil.getLogin(request).get(BaseStatic.KEY_ORGANIZATION_ID));
        int status = 0;
        List<Team> list;
        List<Integer> coachList = new ArrayList<Integer>();
        List teamSize = new ArrayList();
        MessageDto dto = new MessageDto(Result.SUCCESS);
        PageHelper.startPage(page,BaseStatic.TEAMPAGE_DISPLAY);
        list = findInfoService.findSameOrgAllTeam(idOrg,status);
        if (list == null){
            //log.info(list.toString());
            return new MessageDto(Result.FAIL);
        }
        request.getSession().setAttribute("TeamList",list);
        PageInfo<Team> pageInfo = new PageInfo<>(list,BaseStatic.TEAMPAGE_SHOWCODE);
        for (Team team :pageInfo.getList()){
            Integer coach = findInfoService.findCoachIdByIdTeam(team.getIdTeam(),status);
            if (coach == null){
                return  new MessageDto(Result.FAIL);
            }
            coachList.add(coach);
            List <Integer> listPlayer = findInfoService.findPlayerIdByIdTeam(team.getIdTeam(),status);
            if (listPlayer == null){
                return new MessageDto(Result.FAIL);
            }
            teamSize.add(listPlayer.size());
        }
        dto.put("teamSize",teamSize);
        dto.put("teams",list);
        dto.put("coachList",coachList);
        dto.put("pageInfo",pageInfo);
        return  dto;
    }

    @RequestMapping(value = "teamInfo")
    @ResponseBody
    @Security(roles = Role.SCHOOL,checkToken = true)
    public MessageDto findTeamInfo(@RequestParam(value = "idTeam") String ids) {
        Integer idTeam = ParseUtil.parseInt(ids);
        log.info(idTeam+"");
        if (idTeam == null){
            return  new MessageDto(Result.PARAM_NOT_SUIT);
        }
        List<Player> players = new ArrayList<Player>();
        MessageDto dto = new MessageDto(Result.SUCCESS);

        List<Integer> playerId= findInfoService.findPlayerIdByIdTeam(idTeam,0);
        if (playerId == null){
            return  new MessageDto(Result.FAIL);
        }
        for (Integer id : playerId){
            Player player = playerService.selectPlayerById(id);
            if (player != null) {
                players.add(player);
            }else {
                return new MessageDto(Result.FAIL);
            }
        }
        Integer idCoach = findInfoService.findCoachIdByIdTeam(idTeam, 0);
        if (idCoach == null){
            return  new MessageDto(Result.FAIL);
        }
        Coach coach = coachService.getCoach(idCoach);
        if (coach == null){
            return  new MessageDto(Result.FAIL);
        }
        Team team = findInfoService.findTeamInfo(idTeam);
        if (team == null){
            return  new MessageDto(Result.FAIL);
        }
            dto.put("players",players);
            dto.put("playerId",playerId);
            dto.put("coach",coach);
            dto.put("team",team);

        //dto.put("sex",sex);
        //dto.put("date",date);

        return dto;
    }
}




