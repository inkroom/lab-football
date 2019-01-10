package cn.nsu.edu.web.four.controllers.team;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.beans.teams.TeamInfos;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.coach.CoachService;
import cn.nsu.edu.web.four.services.player.impl.PlayerServiceImpl;
import cn.nsu.edu.web.four.services.team.Impl.CreateTeamServiceImpl;
import cn.nsu.edu.web.four.services.team.Impl.FindInfoServiceImpl;
import cn.nsu.edu.web.four.services.team.Impl.OperateInfoServiceImpl;
import cn.nsu.edu.web.four.services.team.Impl.TeamInfosToCoachServiceImpl;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import com.sun.org.apache.bcel.internal.generic.FREM;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "team")
public class UpdateTeamController {

    private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
    OperateInfoServiceImpl operateInfoService;

   @Autowired
   private FindInfoServiceImpl findInfoService;

   @Autowired
   private PlayerServiceImpl playerService;

   @Autowired
   private CoachService coachService;

   @Autowired
   private CreateTeamServiceImpl createTeamService;

   @Autowired
   private TeamInfosToCoachServiceImpl teamInfosToCoachService;


   @RequestMapping(value = "updateInfo")
   @Security(roles = Role.SCHOOL )
    public  String UpdateAction(HttpServletRequest request,@RequestParam("idTeam") String id){
       Integer idTeam = ParseUtil.parseInt(id);
       if (idTeam == null){
           return  "team/displayTeam";
       }
       request.getSession().setAttribute("idTeam",idTeam);
       return "/team/update";
   }

   @RequestMapping(value = "findInfo")
   @ResponseBody
   @Security(roles = Role.SCHOOL ,checkToken = true)
    public MessageDto getTeamInfo(@RequestParam("idTeam") String id,HttpServletRequest request){

        Integer idTeam = ParseUtil.parseInt(id);
       MessageDto dto = new MessageDto(Result.SUCCESS);
       if(idTeam == null){
           dto.setStatus(Result.PARAM_NOT_SUIT);
       }
        String sex = null;
        int status = 0;
        List<Player> players = new ArrayList<Player>();
        Team team = null;
        Coach coach = null;
        try {
             team = findInfoService.findTeamInfo(idTeam);
             if (team == null){
                 return  new MessageDto(Result.FAIL);
             }
             coach = coachService.getCoach(findInfoService.findCoachIdByIdTeam(idTeam,status));

             if (coach == null){
                 return  new MessageDto(Result.FAIL);
             }
            List<Integer> playerIdList = findInfoService.findPlayerIdByIdTeam(idTeam,status);
             if (playerIdList == null){
                 return  new MessageDto(Result.FAIL);
             }

            for (Integer p : playerIdList) {
                Player player = playerService.selectPlayerByIdStatus(p,status);
                if( player!=null) {
                    players.add(player);
                }else{
                    return  new MessageDto(Result.FAIL);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageDto(Result.FAIL);

        }

       dto.put("team",team);
       dto.put("coach",coach);
       //dto.put("sex",sex);
       dto.put("players",players);
        return dto;
   }


    @RequestMapping(value = "deletePlayer")
    @ResponseBody
    @Security(roles = Role.SCHOOL ,checkToken = true)
    public  MessageDto delectPlayer(@RequestParam("idTeam")Integer id,
                                    @RequestParam("playerId")String playerIds){

            Integer idTeam = ParseUtil.parseInt(id);
            Integer playerId = ParseUtil.parseInt(playerIds);
        if (idTeam == null || playerId == null){
            return  new MessageDto(Result.PARAM_NOT_SUIT);
        }

        int status = 1;
        MessageDto dto = new MessageDto(Result.SUCCESS);
        try {
            operateInfoService.updatePlayer(idTeam,playerId,status,new Date());
        }catch (Exception e){
            return  new MessageDto(Result.FAIL);
        }
        return dto;
    }

    @RequestMapping(value = "findPlayers")
    @ResponseBody
    @Security(roles = Role.SCHOOL ,checkToken = true)
    public  MessageDto findPlays(@RequestParam("idTeam")String id,@RequestParam("type")String types,
                                 @RequestParam("name") String name){

        Integer idTeam = ParseUtil.parseInt(id);
        Integer type = ParseUtil.parseInt(types);

        if (idTeam == null || type == null || name == null){
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        try {
            int status = 0;
            MessageDto dto = new MessageDto(Result.SUCCESS);
            Team team = findInfoService.findTeamInfo(idTeam);
            if (team == null){
                return new MessageDto(Result.FAIL);
            }
            int orgId = team.getOrgId();
            List<Integer> grades = findInfoService.findGradeByIdOrg(orgId,status);
            if (grades == null){
                return new MessageDto(Result.FAIL);
            }

            for (Integer grade:grades){
                List<Integer> classes = findInfoService.findClassByGrade(grade);
                if (classes == null){
                    return new MessageDto(Result.FAIL);
                }
            }
            List<Player> players= findInfoService.findPlayers(orgId,status,type,name);
            if (players == null){
                return  new MessageDto(Result.FAIL);
            }
            dto.put("players",players);
            return  dto;
        }catch (Exception e){
            return  new MessageDto(Result.FAIL);
        }

    }

    @RequestMapping(value = "insert")
    @ResponseBody
    @Security(roles = Role.SCHOOL ,checkToken = true)
    public  MessageDto insertInfo(HttpServletRequest request,String idTeams,String name,String  types ,String preIds,String newIds,String arrPlayer ){

        Integer idTeam = ParseUtil.parseInt(idTeams);
        Integer type = ParseUtil.parseInt(types);
        Integer newId = ParseUtil.parseInt(newIds);
        Integer preId = ParseUtil.parseInt(preIds);
        Integer teamid = (Integer) request.getSession().getAttribute("idTeam");
        if (idTeam == null){
            return  new MessageDto(Result.PARAM_NOT_SUIT);
        }
       if (!teamid.equals(idTeam)){
            return  new MessageDto(Result.FAIL);
        }
        if ( name == null || type ==null || preId == null || newId ==null || arrPlayer == null || "".equals(name.trim())){
            return  new MessageDto(Result.PARAM_NOT_SUIT);
        }
        try {
        int status = 0;
        MessageDto dto = new MessageDto(Result.SUCCESS);
        String[] playerIds= arrPlayer.split(",");
        //log.info(playerIds.length+"");
        Date date = new Date();
        Team team = new Team();
        team.setIdTeam(idTeam);
        team.setStatus(0);
        team.setName(name.trim());
        team.setCreateTime(new Date());
        team.setType(type);
        List<Integer> idplayers = new ArrayList<Integer>();
        TeamInfos teamInfos = new TeamInfos();
        Map<String, Object> map = RequestUtil.getLogin(request);
        Integer orgId = (Integer) map.get(BaseStatic.KEY_ORGANIZATION_ID);
        teamInfos.setOrgId(orgId);
        teamInfos.setTeamCoachId(newId);

        int flag = 0;
        List<Player> sameOrgAllPlayer= findInfoService.findSameOrgAllPlayer(orgId,status);
            for (Player player : sameOrgAllPlayer){
                log.info(player.getIdPlayer()+"");
                for (String id : playerIds){
                    if (player.getIdPlayer() == Integer.parseInt(id)){
                        flag++;
                    }
                }
            }
            log.info(playerIds.length+"" + flag+"");
        if (flag < playerIds.length){
                return new MessageDto(Result.FAIL);
        }

      if (teamInfosToCoachService.findCoachByOrg(teamInfos)==null){
            return new MessageDto(Result.FAIL);
        };

         if (operateInfoService.upDateTeamInfo(team) == 0){
             return  new MessageDto(Result.FAIL);
         };

                if (!preId.equals(newId)) {
                    if (operateInfoService.updateCoach(idTeam, preId, 1, date) == 0){
                        return  new MessageDto(Result.FAIL);
                    };
                    if (createTeamService.addCoach(idTeam, newId, status, date) == 0){
                        return  new MessageDto(Result.FAIL);
                    }
                }

                for (int i = 0; i < playerIds.length; i++) {
                    if (!findInfoService.findSameIdTeamAndPlayerId(idTeam, Integer.parseInt(playerIds[i]))) {
                        idplayers.add(Integer.parseInt(playerIds[i]));
                    } else {
                        if (operateInfoService.updatePlayer(idTeam, Integer.parseInt(playerIds[i]), status, date) ==0){
                            return  new MessageDto(Result.FAIL);
                        }
                    }
                }
                if (!idplayers.isEmpty()) {
                    if(createTeamService.addPlayer(idplayers, idTeam, status, date)==0){
                        return  new MessageDto(Result.FAIL);
                    }
                }
        return dto;

        }catch (Exception e){
            e.printStackTrace();
            return  new MessageDto(Result.FAIL);
        }

    }



}
