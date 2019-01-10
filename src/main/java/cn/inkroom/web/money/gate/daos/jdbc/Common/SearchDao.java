package cn.inkroom.web.money.gate.daos.jdbc.Common;

import cn.inkroom.web.money.gate.beans.common.SearchResultBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SearchDao {

    List<SearchResultBean> searchHtml(@Param("key") String key) throws Exception;

    List<SearchResultBean> searchNews(@Param("key") String key) throws Exception;

    List<SearchResultBean> searchProATe(@Param("key") String key, @Param("prefix") String prefix, @Param("table") String table) throws Exception;

    List<SearchResultBean> searchContactUs(@Param("key") String key) throws Exception;
}
