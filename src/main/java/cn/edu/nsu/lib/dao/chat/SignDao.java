package cn.edu.nsu.lib.dao.chat;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 17:18
 * @Descorption 打卡
 */
public interface SignDao {
    @Insert("insert into signed values(#{username},#{date},1)")
    int insertStudent(@Param("username") String username, @Param("date") Date date) throws Exception;
}
