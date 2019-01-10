package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.match.Match;
import cn.nsu.edu.web.four.beans.referee.PlayerDescription;
import cn.nsu.edu.web.four.beans.referee.Report;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.exception.RollbackException;
import cn.nsu.edu.web.four.handler.socket.LiveWebSocket;
import cn.nsu.edu.web.four.schedule.FinishSchedule;
import cn.nsu.edu.web.four.services.match.MatchService;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import cn.nsu.edu.web.four.services.referee.ReportService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/referee/")
public class ReportController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MatchService matchService;
    @Autowired
    private ReportService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RefereeService refereeService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @Security(roles = Role.REFEREE)
    public String index() throws Exception {
        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        Integer matchId = ((ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION)).getScheduleInformation().getMatchId();
//        Integer matchId = ParseUtil.parseInt(loginMap.get(BaseStatic.KEY_MATCH_ID).toString());
        Integer schId = ParseUtil.parseInt(loginMap.get(BaseStatic.KEY_SCHEDULE_ID).toString());
        //获取赛事名称
        Match match = matchService.getMatchInfoById(matchId);

        //获取赛程信息
        ScheduleInformationDto information = refereeService.getScheduleInformation(schId);

//        Schedule schedule = service.getSchedule(schId);
        log.info("赛程信息=" + information);

        if (match == null || information == null || information.getScheduleInformation() == null) {
            return "common/404";
        }

        //获取出场球员信息
        List<PlayerDescription> players = service.getPlayers(schId);
        if (players == null) {
            log.info("没有获取到出场球员信息");
        } else {
            request.setAttribute("players", players);
            log.info("获取到的球员=" + players.toString());
        }
        //获取裁判员信息
        request.setAttribute("phone", loginMap.get("phone"));
        request.setAttribute("name", loginMap.get("name"));

        request.setAttribute("match", match);
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION, information);
        return "referee/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @Security(roles = Role.REFEREE, checkToken = true)
    @ResponseBody
//    @SendTo("/live")
    public MessageDto report(String playerJson, Report report) {

        log.info("json=" + playerJson + ".,,,," + report);
        try {
            List<PlayerDescription> list = mapper.readValue(playerJson, mapper.getTypeFactory().constructParametricType(ArrayList.class, PlayerDescription.class));

            ScheduleInformationDto dto = ((ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION));
            log.info("session = " + dto);
            if (dto == null) {
                return new MessageDto(Result.FAIL);
            }
            Schedule schedule = dto.getScheduleInformation();
            Integer refId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(BaseStatic.KEY_REFEREE_ID));
            if (service.addReport(report, list, schedule, refId)) {

                MessageDto messageDto = new MessageDto(Result.SUCCESS);

                messageDto.put(BaseStatic.KEY_SOCKET_LIVE_TYPE, 2);//结束比赛
                messageDto.put(BaseStatic.KEY_SCHEDULE_ID, schedule.getIdSchedule());

                LiveWebSocket.sendMessage(messageDto.toString());

                return new MessageDto(Result.SUCCESS);
            }
            return new MessageDto(Result.FAIL);
        } catch (IOException e) {
//            e.printStackTrace();
            return new MessageDto(Result.PARAM_NOT_SUIT);
        } catch (RollbackException e) {
            return new MessageDto(Result.FAIL);
        }

//        log.info("list = " + playerJson);
//        return new MessageDto(Result.SUCCESS);
    }

}
