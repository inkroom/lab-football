package cn.inkroom.web.money.gate.controllers.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.services.news.ShowNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenGang
 * @Title: ShowNewController
 * @Package cn.inkroom.web.money.gate.controllers.news
 * @Description:
 * @date 2018/3/15 0015 下午 2:34
 *
 */
@Controller
public class ShowNewController{
    @Autowired
    ShowNewsService showNewsTService;

    @RequestMapping("showNews")
    public String showNews(Model m, int id )
    {
        String htmlPage= null;
        try {

            htmlPage = (String)showNewsTService.getNewsHtmlPage(id).get("html");
        } catch (Exception e) {

            e.printStackTrace();
            htmlPage="数据错误请联系管理员";
        }
        System.out.println(htmlPage);
        m.addAttribute("htmlPage",htmlPage);
        return "news/superChange";
    }
    @RequestMapping("news/news")
    public String Newsindex()
    {
        return "news/news";
    }
    @RequestMapping("news/list")
    public String NewsList(Model model,int type,int page)
    {
//        int Num;//每页显示记录数
//        int page;//现在页

        List<NewsBean> newsList;
        try {
            int num = BaseStatic.EACH_PAGE_COUNT;
            newsList =showNewsTService.getNewsList(type,num,page);
            int count=showNewsTService.getNewsListnums(type);
            model.addAttribute("newsListItem",newsList);
            model.addAttribute("count",count/num+1);
            model.addAttribute("page",page);
            model.addAttribute("type",type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "news/newslist";
    }
    @RequestMapping("news/news/signel")
    public String Newsindexsignel(Model m,int type,int id)
    {
        m.addAttribute("newsType",type);
        m.addAttribute("newsId",id);
        return "news/news";
    }
}

