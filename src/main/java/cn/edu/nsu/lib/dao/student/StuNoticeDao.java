package cn.edu.nsu.lib.dao.student;

import cn.edu.nsu.lib.bean.student.StuNoticeBean;
import cn.edu.nsu.lib.bean.teacher.NoticeEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    StuNoticeDao {
    @Select("select * from notice")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lab_id", property = "lab_id"),
            @Result(column = "publisher", property = "publisher"),
            @Result(column = "time", property = "time"),
            @Result(column = "file_name", property = "file_name"),
            @Result(column = "file_path", property = "file_path"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "text")
    })
    List<StuNoticeBean> findNoticeList() throws Exception;
    @Select("select * from notice where lab_id =#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lab_id", property = "lab_id"),
            @Result(column = "publisher", property = "publisher"),
            @Result(column = "time", property = "time"),
            @Result(column = "file_name", property = "file_name"),
            @Result(column = "file_path", property = "file_path"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "text")
    })
    List<StuNoticeBean> findNoticeListByid(@Param("id") int id) throws Exception;


    @Select("select * from notice where lab_id =#{id}")
    List<NoticeEntity> findNotice(@Param("id") String id) throws Exception;

    @Update("update account set identity = #{identity} where id = #{stuId}")
    int setIdentity(@Param("stuId") String stuId, @Param("identity") int identity) throws Exception;
}
