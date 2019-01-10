package cn.edu.nsu.lib.dao.admin;

import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbMajor;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Repository
public interface IAdmin_Labman_DAO {

    @Select("select * from student where lab_id = #{lab_id}")
    public ArrayList<DbStudent> getstus_byid(@Param("lab_id") int lab_id);

    @Delete("delete from account where id = #{id} limit 1")
    public int deleteman_byid(@Param("id") BigInteger id);

    @Select("select * from student where id = #{id} and lab_id = #{lab_id}")
    public DbStudent getstu_byid(@Param("id") BigInteger id, @Param("lab_id") int lab_id);

    @Select("select teacher_id from relationship where lab_id = #{lab_id}")
    public ArrayList<BigInteger> gettechids_bylabid(@Param("lab_id") int lab_id);

    @Select("select * from teacher where id = #{id}")
    public DbTeacher getteachs_byid(@Param("id") BigInteger id);

    @Select("select * from lab where id = #{id}")
    public DbLab getlab_byid(@Param("id") int id);

    @Select("select * from major where id = #{id} limit 1")
    public DbMajor getmajor_byid(@Param("id") int id);

    /**
     * 根据实验室id进行name查询
     * @param lab_id
     * @return
     */
    @Select("select name from lab where id = #{lab_id} limit 1")
    String getlabname_byid(@Param("lab_id") int lab_id);

    /**
     * 根据专业id进行 专业name查询
     * @param maj_id
     * @return
     */
    @Select("select name from major where id = #{maj_id} limit 1")
    String getmajname_byid(@Param("maj_id") int maj_id);


    @Select("select * from major")
    ArrayList<DbMajor> getmajor_all();

}
