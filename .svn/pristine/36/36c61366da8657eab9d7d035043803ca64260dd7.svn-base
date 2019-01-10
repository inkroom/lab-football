package cn.nsu.edu.web.four.controllers.team;


import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.team.Impl.OperateInfoServiceImpl;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.omg.CORBA.Request;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "team")
public class BreakTeamController {


    @Autowired
    private OperateInfoServiceImpl operateInfoService;

    private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "breakTeam")
    @ResponseBody
    @Security(roles = Role.SCHOOL,checkToken = true)
    public MessageDto retireTeam(HttpServletRequest request ,@RequestParam("idTeam") String id){
        Integer idTeam = ParseUtil.parseInt(id);

        List<Team> teamList = (List<Team>) request.getSession().getAttribute("TeamList");
        int flag = 0;
        for (Team team :teamList){
            if (idTeam != team.getIdTeam()){
                flag+=1;
            }
        }
        if (flag == teamList.size()){
            return new MessageDto(Result.FAIL);
        }
        if (idTeam == null){
            return  new MessageDto(Result.FAIL);
        }
        MessageDto dto = new MessageDto(Result.SUCCESS);

        int status = 1;
        if (operateInfoService.retireTeam(idTeam,status,new Date()) == 0){
            return new MessageDto(Result.FAIL);
        }
        return dto;
    }



}
