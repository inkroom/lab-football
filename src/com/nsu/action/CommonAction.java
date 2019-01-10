package com.nsu.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/21
 * @Time 10:00
 * @Descorption
 */
@Controller
@RequestMapping("/common/")
public class CommonAction {
    @RequestMapping("token_error")
    public String tokenError() {
        return "/common/token_error";
    }

    @RequestMapping("authority_lose")
    public String authorityLose() {
        return "/common/authority_lose";
    }

    @RequestMapping("authority_error")
    public String authorityError() {
        return "/common/authority_error";
    }

    @RequestMapping("404")
    public String notFount(){
        return "/common/error";
    }
}
