package cn.inkroom.web.money.gate.services.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;

import java.util.List;
import java.util.Map;

public interface ShowNewsService{
    public List<NewsBean> getNewsList(int type, int num, int page) throws Exception;

    public int getNewsListnums(int type) throws Exception;

    public Map<String,Object>  getNewsHtmlPage( int id) throws Exception;
}
