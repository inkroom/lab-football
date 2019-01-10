package cn.edu.nsu.lib.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 22:57
 * @Descorption 基于xml的dao层
 */
public interface TestIntDao {
    public Map<String, Object> sel(@Param("a") String a) throws Exception;
}
