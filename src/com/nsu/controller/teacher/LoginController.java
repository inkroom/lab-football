package com.nsu.controller.teacher;

import com.nsu.entity.Account;
import com.nsu.service.teacher.login.LoginService;
import com.nsu.utils.InfoProtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @PackageName : com.nsu.controller.teacher
 * @Author : BuDD
 * @CreateTime : 2017/8/4
 * @Version : 1.0
 * @Description :    教师登陆
 */
@RequestMapping("/teacher")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Account account = loginService.login(username, InfoProtUtil.parseStrToMd5L32(password));
        if(account != null) {
            session.setAttribute("account",account);
            return "Success";
        }
        return null;
    }

}
