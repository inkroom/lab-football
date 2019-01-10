package cn.inkroom.web.money.gate.daos.jdbc.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {

    List<NewsBean> getAllNews(@Param("flag") int flag,@Param("type") int type) throws Exception;

    NewsBean getNews(@Param("id") int id) throws Exception;

    int insertNews(@Param("n") NewsBean bean) throws Exception;

    int updateNews(@Param("n") NewsBean bean) throws Exception;

    int setFlag(@Param("oldFlag") int oldFlag,@Param("newFlag") int newFlag) throws Exception;

    int setFlagById(@Param("id") int id,@Param("flag") int flag) throws Exception;
}
