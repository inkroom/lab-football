package cn.inkroom.web.money.gate.daos.jdbc.news;

import cn.inkroom.web.money.gate.beans.news.NewsBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ChenGang
 * @Title: ShowsNewsDao
 * @Package cn.inkroom.web.money.gate.daos.jdbc.news
 * @Description:
 * @date 2018/3/15 0015 下午 2:41
 **/


@Repository
public interface ShowNewsDao{
    @Select("SELECT nc_id as id,nc_title as title,nc_date as date,nc_type as type,nc_flag as flag,nc_content as html FROM `news_center` where nc_id =#{id} and nc_flag=1")
    Map<String, Object> getNews(@Param("id") int id)throws Exception;
    @Select("SELECT nc_id as id,nc_title as title,nc_date as date,nc_type as type,nc_flag as flag FROM `news_center` where nc_type= #{type} and nc_flag=1 ORDER BY nc_date DESC LIMIT #{page}, #{num}")
List<NewsBean> getNewsList(@Param("type") int type, @Param("num") int num, @Param("page") int page)throws Exception;
    @Select("SELECT COUNT(nc_id) FROM `news_center` where nc_type= #{type} and nc_flag=1")
    int getNewsListnums(@Param("type") int type)throws  Exception;
}
