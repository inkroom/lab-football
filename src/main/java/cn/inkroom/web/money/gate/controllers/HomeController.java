package cn.inkroom.web.money.gate.controllers;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import cn.inkroom.web.money.gate.services.cooperator.CooperatorService;
import cn.inkroom.web.money.gate.services.html.HtmlService;
import cn.inkroom.web.money.gate.services.news.NewsService;
import cn.inkroom.web.money.gate.services.prodandtech.ProductService;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class HomeController {

    protected Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private HttpServletRequest request;
//    @Autowired
//    private HtmlService htmlService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("home")
    public String home() {

        request.setAttribute("news",newsService.getAllNews(1,-1));

        return "home";

    }

    @RequestMapping("under")
    public String under() {
        return "under";
    }
    @RequestMapping("backstage/welcome")
    public String backIndex(){
        return "backstage/welcome";
    }
}
