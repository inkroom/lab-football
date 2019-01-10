package cn.nsu.edu.web.four.controllers.team;


import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "team")
public class CreateTeamController {

    @RequestMapping(value = "create")
    @Security(roles = Role.SCHOOL)
    public  String test(){

        return  "team/createTeam";
    }






}
