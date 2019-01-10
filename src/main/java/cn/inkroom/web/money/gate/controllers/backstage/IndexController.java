package cn.inkroom.web.money.gate.controllers.backstage;

import cn.inkroom.web.money.gate.services.news.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/backstage/")
public class IndexController {
    @Autowired
    private NewsService service;
    @Autowired
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("index")
    public String index() {
        return "backstage/index";
    }

    @RequestMapping(value = "text", method = RequestMethod.GET)
    public String text() {
        return "backstage/textEditing";
    }

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public String news() {
        request.setAttribute("news", service.getAllNews(-1,-1));

        return "backstage/newsEdit";
    }
}
