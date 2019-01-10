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
    @Insert("insert into signed (stu_id,date,register,lab_id) values(#{username},#{date,typeHandler=cn.edu.nsu.lib.handlers.DateHandler},1,#{lab_id})")
    int insertStudent(@Param("username") String username, @Param("date") Date date, @Param("lab_id") int labId) throws Exception;
}
