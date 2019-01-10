package cn.nsu.edu.web.four.daos.jdbc.referee;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface LoginDao {

    Map<String, Object> selectAccount(@Param("username") String username) throws Exception;

}
