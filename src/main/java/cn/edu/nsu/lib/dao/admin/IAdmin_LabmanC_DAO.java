package cn.edu.nsu.lib.dao.admin;

import cn.edu.nsu.lib.bean.admin.db.DbAccount;
import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbMajor;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Repository
public interface IAdmin_LabmanC_DAO {

    @Select("select * from major where name = #{name}")
    public DbMajor getmajor_byname(@Param("name") String name);

//    insert into student(id,name,gender,grade,maj_id,lab_id,time,instructor,tel)
//        values(153105,'COCO',1,11,1,123,'2017-10-07','王',8749)
    @Insert("insert into student(id,name,gender,grade,maj_id,lab_id,time,instructor,IDcard,stuClass,department,tel) " +
            " values(#{student.id}," +
            "#{student.name}," +
            "#{student.gender}," +
            "#{student.grade}," +
            "#{student.maj_id}," +
            "#{student.lab_id}," +
            "#{student.time}," +
            "#{student.instructor}," +
            "#{student.IDcard}," +
            "#{student.stuClass}," +
            "#{student.department}," +
            "#{student.tel})")
    public int addstu(@Param("student") DbStudent student);

    //修改不能改学号，要盖学号要删了再添加
    @Update("update student set " +
            "name = #{student.name}," +
            "gender = #{student.gender}," +
            "grade = #{student.grade}," +
            "maj_id = #{student.maj_id}," +
            "lab_id = #{student.lab_id}," +
            "time = #{student.time}," +
            "instructor = #{student.instructor}," +
            "tel =  #{student.tel}," +
            "IDcard =  #{student.IDcard}," +
            "stuClass =  #{student.stuClass}," +
            "department =  #{student.department}," +
            "outTime =  #{student.outTime}" +
            "where id = #{student.id} limit 1")
    public int alterstu(@Param("student") DbStudent student);


    @Insert("insert into account(id,identity,salt,password) " +
            " values(#{account.id}," +
            "#{account.identity},#{account.salt},#{account.passwd})")
    public int addaccount_byid(@Param("account") DbAccount account);

    @Select("select * from account where id = #{id} limit 1")
    DbAccount getaccount_byid(@Param("id") BigInteger id);

    @Select("select * from lab where id = #{id} limit 1")
    DbLab getlab_byid(@Param("id") int id);
}
