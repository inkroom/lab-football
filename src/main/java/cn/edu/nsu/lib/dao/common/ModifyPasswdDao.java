package cn.edu.nsu.lib.dao.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ModifyPasswdDao{

    @Update("UPDATE Account SET passwd=#{password},salt=#{salt} where id = #{username} limit 1")
   void  updatePasswd(@Param("username") String username,@Param("password")String password,@Param("salt")String salt) throws Exception;


}
