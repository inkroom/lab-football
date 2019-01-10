package cn.edu.nsu.lib.dao.admin;

import cn.edu.nsu.lib.bean.admin.db.DbNotice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Repository
public interface IAdmin_NoticeC_DAO {

    @Insert("insert into notice(lab_id, publisher, time, title, content, file_name, file_path)" +
            "values(#{notice.lab_id}," +
            "#{notice.publisher}," +
            "#{notice.time}," +
            "#{notice.title}," +
            "#{notice.content}," +
            "#{notice.file_name}," +
            "#{notice.file_path})")
    int add_notice(@Param("notice") DbNotice notice);

    @Insert("insert into student(id,lab_id,publisher,time,title,content,file_name,file_path) " +
            " values(#{notice.id}," +
            "#{notice.lab_id}," +
            "#{notice.publisher}," +
            "#{notice.time}," +
            "#{notice.title}," +
            "#{notice.content}," +
            "#{notice.file_name}," +
            "#{notice.file_path})")
    int addnotice_bybean(@Param("notice") DbNotice notice);
}
