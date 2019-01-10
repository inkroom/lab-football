package cn.inkroom.web.money.gate.daos.jdbc.Common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePswDAO {

    @Update("update user set password = #{psw} , salt = #{salt} where name = #{name} ")
    public void changpsw(@Param("name") String name , @Param("psw") String psw , @Param("salt") String salt);
}
