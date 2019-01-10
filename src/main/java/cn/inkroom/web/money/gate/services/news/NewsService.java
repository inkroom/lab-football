package cn.inkroom.web.money.gate.services.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import cn.inkroom.web.money.gate.daos.jdbc.news.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDao dao;

    /**
     *
     * @param flag 是否有效，-1则不管是否有效
     * @param type 类型，-1则所有类型
     * @return 获取到的数据，发生异常返回null
     */
    public List<NewsBean> getAllNews(int flag,int type) {
        try {
            return dao.getAllNews(flag,type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取单个新闻
     * @param id 新闻编号
     * @return 有数据返回实例，否则返回null
     */
    public NewsBean getNews(int id) {
        try {
            return dao.getNews(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public NewsBean setNews(int id, int type, String title, String content) {

        NewsBean bean = new NewsBean();
        bean.setId(id);
        bean.setType(type);
        bean.setTitle(title);
        bean.setContent(content);
        bean.setCreateTime(new Date());

        int result = 0;
        try {
            dao.setFlag(1, 0);
            if (id == -1) {
                result = dao.insertNews(bean);
            } else {
                result = dao.updateNews(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 1) {
            return bean;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * 设置对应新闻的状态
     * @param id 要修改的新闻编号
     * @param flag 新的状态
     * @return 成功或失败
     */
    public boolean setFlagById(int id,int flag) {
        try {
            return dao.setFlagById(id, flag) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
