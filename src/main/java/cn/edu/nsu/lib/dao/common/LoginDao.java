package cn.edu.nsu.lib.dao.common;

import cn.edu.nsu.lib.config.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 16:31
 * @Descorption
 */
public interface LoginDao {
    @Select("select id as " + Constants.KEY_MAP_USERNAME + ",identity as " + Constants.KEY_MAP_AUTHORITY +
            ",passwd as " + Constants.KEY_MAP_PASSWORD + ",salt as " + Constants.KEY_MAP_SALT +
            " from account where id = #{username} limit 1")
    Map<String, Object> getAccount(@Param("username") String username) throws Exception;

    @Select("select * from teacher where id = #{username} limit 1")
    Map<String, Object> getTeacherUser(@Param("username") String username) throws Exception;

    @Select("select * from student where id = #{username} limit 1")
    Map<String, Object> getStudentUser(@Param("username") String username) throws Exception;
}
