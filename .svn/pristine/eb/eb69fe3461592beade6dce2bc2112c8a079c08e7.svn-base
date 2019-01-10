package com.nsu.dao.teacher.login;

import com.nsu.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @PackageName : com.nsu.dao.teacher.login
 * @Author : BuDD
 * @CreateTime : 2017/7/31
 * @Version : 1.0
 * @Description : 登陆的Dao层
 */
@Repository
@Mapper
public interface LoginDao {

    /**
     * 教师登陆
     * @param username  用户名
     * @param passwordEncryption    加密后的密码
     * @return 没有返回NUll
     */
    @Select("select * from account where USERNAME = #{username} and PASSWORD = #{password}")
    Account login(@Param("username") String username, @Param("password") String passwordEncryption);
}
