package cn.nsu.edu.web.four.controllers.schedule;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.referee.Referee;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.match.MatchService;
import cn.nsu.edu.web.four.services.schedule.impl.ScheduleServiceImpl;
import cn.nsu.edu.web.four.services.team.FindInfoService;
import cn.nsu.edu.web.four.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static cn.nsu.edu.web.four.beans.schedule.ScheduleStatic.SCHEDULE_STATUS_READY;


/**
 * Created by 灵魂都在冒香气的神 on 2018/3/19.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource(name = "schedule")
    private ScheduleServiceImpl service;
    @Autowired
    private MatchService matchService;
    @Autowired
    private FindInfoService teamService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    HttpServletRequest request;

    /***
     * 新增赛程
     * @param schedule
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MessageDto create(@Valid Schedule schedule, BindingResult result) {
        if ((schedule != null) && (result.getFieldError() == null)) {
            if (!service.checkAuthority(schedule, request)) {
                return new MessageDto(Result.NO_AUTHORITY);
            }
            log.info(schedule.toString());
            Integer level = matchService.getLevel(schedule.getMatchId());
            schedule.setLevel(level);
            schedule.setStatus(SCHEDULE_STATUS_READY);
            if (service.createSchedule(schedule)) {
                return new MessageDto(Result.SUCCESS);
            }
        }
        log.info(result.getFieldError().toString());
        return new MessageDto(Result.FAIL);
    }

    /*
            获取机构列表
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/showMechanism", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public MessageDto showMechanism(@RequestParam(name = "matchId") Integer matchId) {
        log.info("matchId:" + matchId);
        List<Organization> list = matchService.getMatchOrgList(matchId);
        if (list == null) {
            return new MessageDto(Result.FAIL);
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("orgs", list);
        return dto;
    }

    /*
            根据机构列表获取获取队伍列表
     */
//    @ResponseBody
//    @RequestMapping(value = "/team",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
//    public MessageDto showTeam(@RequestParam(name = "orgId",defaultValue = "-1") Integer orgId)
//    {
//        if (orgId ==-1)
//        {
//            return new MessageDto(Result.EXCEPTION);
//        }
////        List<CurrentTeam> list=service.getTeam(orgId);
//        if (list==null)
//        {
//            return new MessageDto(Result.EXCEPTION);
//        }
//        MessageDto dto=new MessageDto(Result.SUCCESS);
//        dto.put("team",list);
//        return dto;
//    }


    /**
     * 根据ID修改赛程
     *
     * @param schedule
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MessageDto modify(@RequestBody @Valid Schedule schedule, BindingResult result) {
        if (!service.checkAuthority(schedule, request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        if (result.getFieldError() != null) {
            return new MessageDto(Result.FAIL);
        }
        if (schedule == null || (!service.modifySchedule(schedule))) {
            return new MessageDto(Result.FAIL);
        }
        return new MessageDto(Result.SUCCESS);
    }


    /**
     * 根据ID删除赛程
     *
     * @param idSchedule
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MessageDto delete(@RequestParam(name = "idSchedule", defaultValue = "-1") Integer idSchedule) {
        if (idSchedule == -1 || (!service.deleteSchedule(idSchedule))) {
            return new MessageDto(Result.FAIL);
        }
        if (!service.checkAuthority(idSchedule, request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
//        if (!service.checkAuthority(ParseUtil.parseInt(idSchedule), request)) {
//            return new MessageDto(Result.NO_AUTHORITY);
//        }
        return new MessageDto(Result.SUCCESS);
    }

    /**
     * 根据赛事ID获取获取赛程列表
     *
     * @param idMatch
     * @param page
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/showSchedule/{page:[1-9]+[0-9]*}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MessageDto showSchedule(@RequestParam(name = "idMatch", defaultValue = "-1") Integer idMatch, @PathVariable Integer page) {
        if (idMatch == -1) {
            return new MessageDto(Result.FAIL);
        }
        List<Schedule> list = service.getScheduleByMatchID(idMatch);
        if (list == null) {
            return new MessageDto(Result.FAIL);
        }
        List<Schedule> childList = new Page<Schedule>().subList(list, page);
        for (int i = 0, len = childList.size(); i < len; i++) {
            int teamA = childList.get(i).getTeamA();
            int teamB = childList.get(i).getTeamB();
            try {
                Team teamInfoA = teamService.findTeamInfo(teamA);
                Team teamInfoB = teamService.findTeamInfo(teamB);
                childList.get(i).setTeamNameA(teamInfoA.getName());
                childList.get(i).setTeamNameB(teamInfoB.getName());
            } catch (Exception e) {
                childList.get(i).setTeamNameA("无法获取队伍信息");
                childList.get(i).setTeamNameB("无法获取队伍信息");
            }
        }
        MessageDto dto = new MessageDto();
        dto.put("schedule", childList);
        dto.put("size", list.size());   //赛程总数目
        return dto;
    }

    /**
     * 根据赛事ID获取获取参加此赛事的所有队伍列表
     *
     * @param matchId
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/getTeam", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MessageDto getTeamByMatch(@RequestParam(name = "matchId", defaultValue = "-1") Integer matchId
            , @RequestParam(name = "sid", defaultValue = "-1") Integer sid) {
        List<Team> list = null;
        if ((matchId == -1) || (list = service.getTeamByMatchId(matchId)) == null) {
            return new MessageDto(Result.FAIL);
        }
        Schedule schedule = null;
        if (sid == -1 || (schedule = service.getScheduleInfo(sid)) == null) {
            return new MessageDto(Result.FAIL);
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("team", list);
        dto.put("schedule", schedule);
        return dto;
    }


    /**
     * 转发，中间
     *
     * @param idSchedule
     * @param match
     * @param model
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public String modifyPage(@RequestParam("idSchedule") Integer idSchedule, @RequestParam("match") Integer match, Model model) {
        model.addAttribute("idSchedule", idSchedule);
        model.addAttribute("match", match);
        if (!service.checkAuthority(idSchedule, request)) {
            return "common/401";
        }
        return "/match/schModify";
    }

    /**
     * @param matchId 赛事Id
     * @return 机构List包含id和name
     * @author Xuing
     * @description 通过赛事Id查询出该赛事中审核通过的机构列表
     * @date 2018-3-29 16:53:34
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/showOrgs/{matchId:[1-9]+[0-9]*}")
    public MessageDto showMatchOrgs(@PathVariable Integer matchId) {
        log.info("========================================");
        log.info("matchId" + matchId);
        log.info("========================================");
        List<Organization> orgs = matchService.getMatchOrgList(matchId);
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("orgs", orgs);
        return dto;
    }

    /**
     * @param schId 赛程Id
     * @return DTO包含赛程信息
     * @author Xuing
     * 通过赛程Id获取赛程信息
     * @date 2018-4-3 09:43:53
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/getSch/{schId:[1-9]+[0-9]*}")
    public MessageDto getSchInfoByid(@PathVariable Integer schId) {
        log.info("========================================");
        log.info("schId" + schId);
        log.info("========================================");
        Schedule schedule = service.getScheduleInfo(schId);
        if (schedule != null) {
            MessageDto dto = new MessageDto(Result.SUCCESS);
            dto.put("schedule", schedule);
            return dto;
        }
        return new MessageDto(Result.FAIL);
    }

    /**
     * @param matchId 赛事Id
     * @return 机构List包含id和name
     * @author Xuing
     * 通过赛事Id查询出该赛事中审核通过的机构列表
     * @date 2018-3-29 16:53:34
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/showTeams/{matchId:[1-9]+[0-9]*}/{orgId:[1-9]+[0-9]*}")
    public MessageDto showMatchTeamByOrgId(@PathVariable Integer matchId, @PathVariable Integer orgId) {
        List<Team> teams = matchService.getMatchStaffTeamList(matchId, orgId);
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("teams", teams);
        return dto;
    }

    /**
     * @param referee 裁判
     * @return Dto
     * @author Xuing
     * 创建赛程裁判员账号，可创建多个。
     * @date 2018-4-2 10:00:03
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/createReferee")
    public MessageDto createReferee(Referee referee) {
        MessageDto dto = new MessageDto(Result.SUCCESS);
        log.info(referee.getIdSch().toString());
        Referee myReferee = service.checkReferee(referee.getIdSch());
        if (!service.checkAuthority(referee.getIdSch(), request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        if (myReferee != null) {
            if (!service.updateRefereeBySchId(referee)) {
                log.info("裁判员删除失败，裁判员创建失败。");
                dto.setStatus(Result.FAIL);
                dto.setMessage("修改失败");
                return dto;
            }
            dto.setStatus(Result.SUCCESS);
            return dto;
        }
        if (service.createReferee(referee)) {
            log.info("裁判员账号创建成功" + referee);
            dto.setStatus(Result.SUCCESS);
        }
        return dto;
    }

    /**
     * 转发，创建赛程裁判员
     *
     * @param idSchedule
     * @param model
     * @return
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "/turnReferee", method = RequestMethod.GET)
    public String createReferee(@RequestParam("idSchedule") Integer idSchedule, Model model) {
        if (!service.checkAuthority(idSchedule, request)) {
            return "common/401";
        }
        model.addAttribute("idSchedule", idSchedule);
        Referee myReferee = service.checkReferee(idSchedule);
        if (myReferee != null) {
            model.addAttribute("refereeUser", myReferee.getUsername());
            model.addAttribute("title", "修改裁判员");
        } else {
            model.addAttribute("title", "创建裁判员");
        }
        return "/match/addReferee";
    }
}

