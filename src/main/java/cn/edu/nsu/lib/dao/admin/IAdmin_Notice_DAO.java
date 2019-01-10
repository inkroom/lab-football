package cn.edu.nsu.lib.dao.admin;

import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbNotice;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Repository
public interface IAdmin_Notice_DAO {

    @Select("select * from notice where lab_id = #{lab_id}")
    public List<DbNotice> getnotice_byid(@Param("lab_id") int lab_id);

    @Select("select * from student where id = #{id}")
    public DbStudent getstu_byid(@Param("id") BigInteger id);

    @Select("select * from teacher where id = #{id}")
    public DbTeacher getteacher_byid(@Param("id") BigInteger id);

    @Delete("delete from notice where id = #{id} and lab_id = #{lab_id}")
    public int deletenotice_byid(@Param("id") int id, @Param("lab_id") int lab_id);

    @Select("select * from lab where id = #{id}")
    DbLab getlab_byid(@Param("id") int id);
}
