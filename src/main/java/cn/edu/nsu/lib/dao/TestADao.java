package cn.edu.nsu.lib.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 23:04
 * @Descorption 基于注解的dao层
 */
public interface TestADao {
    @Select("select * from account where nickname=#{a}")
    public Map<String, Object> sel(@Param("a") String a) throws Exception;
}
