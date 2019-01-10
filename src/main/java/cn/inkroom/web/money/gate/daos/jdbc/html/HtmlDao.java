package cn.inkroom.web.money.gate.daos.jdbc.html;

import cn.inkroom.web.money.gate.beans.common.HtmlBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtmlDao {
    HtmlBean getUnion(@Param("id") int id) throws Exception;

    List<HtmlBean> getAllTypeUnion(@Param("owner") int owner) throws Exception;

    List<HtmlBean> getAllTitleUnion(@Param("owner") int owner) throws Exception;

    HtmlBean getHtml(@Param("owner") int owner, @Param("type") int type) throws Exception;

    Integer setFlag(@Param("owner") int owner, @Param("type") int type, @Param("oldFlag") int oldFlag, @Param("newFlag") int newFlag) throws Exception;

    Integer addHtml(@Param("owner") int owner, @Param("type") int type,@Param("content") String content,@Param("title") String title,@Param("url") String url) throws Exception;

}
