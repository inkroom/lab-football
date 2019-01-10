package com.nsu.controller.test;

import com.nsu.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.controller.test
 * @Description:
 * @date 17/4/20
 */
@Controller
@RequestMapping("/mobile")
public class MobileTestController extends BaseController{

    @RequestMapping("/test")
    public void mobileTest(HttpServletRequest request){
        System.out.println(request.getParameter("token"));
        System.out.println("12fasdfasdfasdfasedf");
    }
}
