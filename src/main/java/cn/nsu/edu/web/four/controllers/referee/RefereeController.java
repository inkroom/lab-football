package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.PlayerInformationDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/referee")
public class RefereeController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RefereeService refereeService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    HttpServletRequest request;

    /**
     * @return return java.lang.String
     * @author 杨康
     * @description 通过裁判员登陆之后的赛程id得到球队赛程信息(登陆需将scheduleId存入session)
     * @date 2018/3/29 8:28
     */
    @Security(createToken = false, roles = Role.REFEREE)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getScheduleInformation(Model model) throws Exception {
        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        ScheduleInformationDto dto;
        //将主队信息存入赛程信息
        log.info(loginMap.toString());
        dto = refereeService.getScheduleInformation(ParseUtil.parseInt(loginMap.get(BaseStatic.KEY_SCHEDULE_ID)));
        model.addAttribute("dto", dto);
        //将主队id存入session
        request.setAttribute("teamAId", dto.getScheduleInformation().getTeamA());
        //将客队id存入session
        request.setAttribute("teamBId", dto.getScheduleInformation().getTeamB());
        request.setAttribute("dto", dto);
        return "/referee/index";
    }

    /**
     * @return return java.lang.String
     * @author 杨康
     * @description 通过球队id获取球员信息
     * @date 2018/3/29 8:28
     */
    @Security(roles = Role.REFEREE)
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String getPlayerInformation(Model model) throws Exception {
        ScheduleInformationDto dto = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
        if (dto == null) {
            return "common/404";
        }

        PlayerInformationDto Pdto1;
        PlayerInformationDto Pdto2;
        //Pdto1 = refereeService.getPlayerInformation((Integer) loginMap.get("teamAId"));
        //Pdto2 = refereeService.getPlayerInformation((Integer) loginMap.get("teamBId"));
        //测试
        Pdto1 = refereeService.getPlayerInformation(dto.getScheduleInformation().getTeamA());
        Pdto2 = refereeService.getPlayerInformation(dto.getScheduleInformation().getTeamB());
        model.addAttribute("Pdto1", Pdto1);
        model.addAttribute("Pdto2", Pdto2);
        return "/referee/test";
    }

    /**
     * @return return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author 杨康
     * @description 通过球队id向赛程表插入球员信息
     * @date 2018/3/29 8:28
     */
    @ResponseBody
    @Security(checkToken = true, roles = Role.REFEREE)
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public MessageDto insertPlayerInformation(String playerIdA, String playerIdB) throws Exception {
        if (StringUtils.isEmpty(playerIdA) || StringUtils.isEmpty(playerIdB)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        ArrayList<Integer> playerA = null;
        ArrayList<Integer> playerB = null;

        try {
            playerA = mapper.readValue(playerIdA, mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class));
            playerB = mapper.readValue(playerIdB, mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Map<String, Object> map = RequestUtil.getLogin(request);
        if (Objects.equals(ParseUtil.parseInt(map.get(BaseStatic.KEY_MAP_REFEREE_IS_CHECK)), 1)) {
            return new MessageDto(Result.REFEREE_CHECK_ALREADY);
        }
        ScheduleInformationDto dto = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
        log.info("dto = " + dto);
        log.debug("赛程信息=" + dto.toString());
        if (refereeService.insertPlayerInformation(playerA, playerB, dto.getScheduleInformation().getIdSchedule(),
                dto.getScheduleInformation().getTeamA(), dto.getScheduleInformation().getTeamB(),
                ParseUtil.parseInt(RequestUtil.getLogin(request).get(BaseStatic.KEY_REFEREE_ID)))) {
            map.put(BaseStatic.KEY_MAP_REFEREE_IS_CHECK, 1);
            return new MessageDto(Result.SUCCESS);
        }

//        Map<String, Object> map = RequestUtil.getLogin(request);
//        for (String i : playerA) {
//            Integer Id_PlayerA = ParseUtil.parseInt(i);
//            refereeService.insertPlayerInformation(Id_PlayerA, ParseUtil.parseInt(map.get(BaseStatic.KEY_SCHEDULE_ID)));
//        }
//
//        for (String j : playerB) {
//            Integer Id_PlayerB = ParseUtil.parseInt(j);
//            refereeService.insertPlayerInformation(Id_PlayerB, ParseUtil.parseInt(map.get(BaseStatic.KEY_SCHEDULE_ID)));
//        }
        return new MessageDto(Result.FAIL);
    }

    /**
     * @return java.lang.String
     * @author 杨康
     * @description 获取二维码页面
     * @date 2018/3/29 8:28
     */
    @Security(createToken = false, roles = Role.REFEREE)
    @RequestMapping("/QRcode")
    public String viewQRcode() {
        request.setAttribute("schId", RequestUtil.getLogin(request).get(BaseStatic.KEY_SCHEDULE_ID));
        return "/referee/qrCode";
    }

}
