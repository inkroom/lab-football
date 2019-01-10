package cn.inkroom.web.money.gate.daos.jdbc.prodandtech;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author ChenGang
 * @Title: showPAndTDao
 * @Package cn.inkroom.web.money.gate.daos.jdbc.prodandtech
 * @Description:
 * @date 2018/3/14 0014 下午 4:53
 **/
@Repository
public interface ShowPAndTDao {
    @Select("select pr_name as name,pr_html as html,pr_photo as photo,pr_set as setType,pr_date as date from product where pr_id=#{id} and pr_flag=1")
    Map<String, Object> getProt(@Param("id") int id)throws Exception;
    @Select("select te_name as name,te_html as html,te_set as setType ,te_date as date  from technical where te_id=#{id} and te_flag=1 ")
    Map<String, Object> getTech(@Param("id") int id)throws  Exception;

}
