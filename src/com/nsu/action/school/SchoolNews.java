package com.nsu.action.school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.common.Anonymous;
import com.nsu.util.base.PageUtilForPeople;
import com.nsu.util.base.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school/")
public class SchoolNews extends BaseAction  {
//    private String schoolUrl;
//    private String page;
//    private String typeNews;
//    private String title;
//    private String pageCode;
//    private String scId;
//
//    private List<Map<String, Object>> newsList;
//    private Map<String, Object> newsMap;
//
//    private String temp;

    @RequestMapping("news")
    public String schoolNews(String page, String typeNews, String schoolUrl) {
        int pageSize = 7;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            // TODO: handle exception
            currPage = 1;
        }
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageEnd", pageSize);
        long featureTotal = 0;
        if (typeNews.equals("1") || typeNews.equals("2") || typeNews.equals("3") || typeNews.equals("4") || typeNews.equals("5") || typeNews.equals("6") || typeNews.equals("7") || typeNews.equals("8") || typeNews.equals("9")) {
            try {
                List<Map<String, Object>> newsList = getServiceManager().getSchoolNewsService().getNewsList(schoolUrl, typeNews, pageMap, getRequest());
                featureTotal = getServiceManager().getSchoolNewsService().getNewsTATOL(schoolUrl, typeNews);

                getRequest().setAttribute("newsList", newsList);
                getRequest().setAttribute("featureTotal", "featureTotal");
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
            }
            saveCount(schoolUrl);
            String pageCode = PageUtilForPeople.genPagination(getServletContext().getAttribute("basePath").toString(), featureTotal, currPage, pageSize, schoolUrl, "news", typeNews);

            getRequest().setAttribute("pageCode", pageCode);
            getRequest().setAttribute("page", page);

            return "/school_new/common/news_item";
        }
        return null;
    }

    @RequestMapping("news_info")
    public String getNewsInfo(String typeNews, String scId, String schoolUrl) {
        if (typeNews.equals("1") || typeNews.equals("2") || typeNews.equals("3") || typeNews.equals("4") || typeNews.equals("5") || typeNews.equals("6") || typeNews.equals("7") || typeNews.equals("8") || typeNews.equals("9")) {
            Map<String, Object> newsMap = null;
            try {
                newsMap = getServiceManager().getSchoolNewsService().getNewMap(schoolUrl, typeNews, scId, getRequest());
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
            }
            if (newsMap != null) {
                getRequest().setAttribute("newsMap", newsMap);
                return "/school_new/common/news_detail";
            } else {
                return null;
            }
        }
        return null;
    }


}
