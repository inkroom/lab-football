package cn.inkroom.web.money.gate.daos.jdbc.Common;

import cn.inkroom.web.money.gate.config.BaseStatic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface LoginDAO {

    @Select("select name as " + BaseStatic.KEY_MAP_USERNAME +
            ",password as " + BaseStatic.KEY_MAP_PASSWORD +
            ",salt as " + BaseStatic.KEY_MAP_SALT +
            " from user where name = #{username} limit 1")
    Map<String,Object> getuser(@Param("username") String username);
}
