package cn.inkroom.web.money.gate.controllers.common;

import cn.inkroom.web.money.gate.beans.common.SearchResultBean;
import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.dto.stc.SearchResultDto;
import cn.inkroom.web.money.gate.services.common.SearchService;
import cn.inkroom.web.money.gate.utils.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SearchService service;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("search")
    public String index(String key, String page) {


        Integer i_page = ParseUtil.parseInt(page);
        if (i_page == null) {
            return "common/404";
        }

        long startTime = 0;
        SearchResultDto result = null;
        try {
            startTime = System.currentTimeMillis();
            result = service.search(key, i_page, ((SearchResultDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SEARCH_RESULT)));


            //将搜索结果存入session
            request.getSession().setAttribute(BaseStatic.KEY_SESSION_SEARCH_RESULT,result);

            request.setAttribute("all", result.getResult());
            request.setAttribute("time", System.currentTimeMillis() - startTime);
            request.setAttribute("count",result.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }



        return "search";
    }

}
