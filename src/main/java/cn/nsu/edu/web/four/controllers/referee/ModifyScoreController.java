/**
 * @author :mengyixin
 * @Description: 2018/3/23
 */
package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.handler.socket.LiveWebSocket;
import cn.nsu.edu.web.four.services.referee.ModifyScoreService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/referee")
public class ModifyScoreController {
    @Autowired
    private ModifyScoreService modifyScoreService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    @ResponseBody
    @Security(roles = Role.REFEREE, checkToken = true)
    public MessageDto modifyScore(String scoreA, String scoreB) {

        if (StringUtils.isEmpty(scoreA) || StringUtils.isEmpty(scoreB)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer i_scoreA = ParseUtil.parseInt(scoreA);
        Integer i_scoreB = ParseUtil.parseInt(scoreB);

        if (i_scoreA == null || i_scoreB == null || i_scoreA < 0 || i_scoreB < 0)
            return new MessageDto(Result.PARAM_NOT_SUIT);

        Integer schId  = ((ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION)).getScheduleInformation().getIdSchedule();
//        Integer schId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(BaseStatic.KEY_REFEREE_ID));

        if (schId != null) {
            if (modifyScoreService.modifyScore(i_scoreA, i_scoreB, schId)) {
                //更新session存储的比分
                ScheduleInformationDto scheduleInformationDto = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
                scheduleInformationDto.getScheduleInformation().setGoalA(i_scoreA);
                scheduleInformationDto.getScheduleInformation().setGoalB(i_scoreB);


                MessageDto dto = new MessageDto(Result.SUCCESS);
                dto.put(BaseStatic.KEY_SCHEDULE_ID, schId);
                dto.put(BaseStatic.KEY_SOCKET_LIVE_TYPE, 3);
                dto.put("scoreA", scoreA);
                dto.put("scoreB", scoreB);


                LiveWebSocket.sendMessage(dto.toString());
                return dto;
            } else {
                return new MessageDto(Result.FAIL);
            }
        }
        return new MessageDto(Result.FAIL);
    }

    @RequestMapping("score")
    @Security(roles = Role.REFEREE)
    public String modify() {
//        ScheduleInformationDto dto = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
        return "referee/score";
    }

}
