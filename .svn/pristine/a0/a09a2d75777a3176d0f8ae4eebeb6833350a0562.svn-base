package cn.nsu.edu.web.four.daos.jdbc.live;

import cn.nsu.edu.web.four.beans.live.LiveMessageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveDao {

    List<LiveMessageBean> getAllMessage(@Param("id_sch") long id_sch,@Param("desc") Boolean desc) throws Exception;

    int addMessage(@Param("m") LiveMessageBean bean) throws Exception;

}
