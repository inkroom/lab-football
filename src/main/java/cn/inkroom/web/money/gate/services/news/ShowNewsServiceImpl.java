package cn.inkroom.web.money.gate.services.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import cn.inkroom.web.money.gate.daos.jdbc.news.ShowNewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ChenGang
 * @Title: ShowNewsServiceImpl
 * @Package cn.inkroom.web.money.gate.services.news
 * @Description:
 * @date 2018/3/15 0015 下午 2:40
 **/
@Service
public class ShowNewsServiceImpl implements ShowNewsService{
    @Autowired
    ShowNewsDao showNewsDao;

    @Override
    public List<NewsBean> getNewsList(int type, int num, int page) throws Exception {
        return showNewsDao.getNewsList(type,num,(page-1)*num);
    }

    @Override
    public int getNewsListnums(int type) throws Exception {
        return showNewsDao.getNewsListnums(type);
    }


    @Override
    public Map<String, Object> getNewsHtmlPage(int id) throws Exception {
          return   showNewsDao.getNews(id);
    }
}
