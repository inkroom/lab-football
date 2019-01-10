package cn.edu.nsu.lib.dao.student;

import cn.edu.nsu.lib.bean.leader.LTeacherBean;
import cn.edu.nsu.lib.bean.student.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface StuBasicDao{
    @Insert("insert into student  (id, name, gender,grade, time, maj_id, " +
            "tel,instructor, IDcard, stuClass,department)" +
            "values (#{id},#{ name},#{ gender},#{ grade},#{time},#{maj_id},#{tel}," +
            "#{instructor},#{IDcard},#{stuClass},#{department})")
    void insertStuInfo(StuBasicBean stuBasicBean)throws  Exception;
    LTeacherBean getTeacherInfo(@Param("id") long id)throws Exception;

    @Update("UPDATE student SET  name=#{name}, gender=#{gender},grade=#{grade}" +
            ", time=#{time}, maj_id=#{maj_id}, " +
            "tel=#{tel},instructor=#{instructor}, IDcard=#{IDcard}, stuClass=#{stuClass}, " +
            "outTime=#{outTime},department=#{department}" +
            "where id= #{id}")
    void reSetStuInfo(StuBasicBean stuBasicBean)throws  Exception;

    @Select("select a.*,b.name as majorName,c.name as labName from student a \n" +
            "inner join major b on b.id=a.maj_id \n" +
            "inner join lab c on c.id=a.lab_id\n" +
            "where a.id=#{id}")
    Map<String, Object> getStudentUser(@Param("id") long id) throws Exception;
    //查找所有的奖项

    @Select("Select * from prize where owner=#{owner}")
List<StuPrizeBean> getProzeList(@Param("owner") long id)throws  Exception;

    @Insert("insert into prize  ( lab_id,owner, prize_name, region, rank, category, url, time, committee, adviser,is_checked)" +
            "values (#{lab_id},#{owner},#{prize_name},#{region},#{rank},#{category},#{url},#{time}," +
            "#{committee},#{adviser},#{is_checked})")
    void setProzeList(StuPrizeBean stuPrizeBean)throws  Exception;

//查找所有成绩信息

@Select("select a.course,c.`name` as courseName,a.term,a.grade FROM score a,major b,course c" +
        "  where a.stu_id=#{stu_id} and a.course=c.id and a.major =b.id")
    List<StuScoreBean> getStuScoreList(@Param("stu_id") long id)throws  Exception;
//查找所有实验室
@Select("SELECT id,name ,lab_describe as labDescribe ,qq_group as qqGrop,address FROM  lab")
ArrayList<SLabBean> getLabInfoList()throws Exception;
//查找所有专业
@Select("SELECT id,name FROM major")
ArrayList<SMajorBean> getMajorList()throws Exception;
    @Select("select * from student where id = #{username} limit 1")
    Map<String, Object> getStudentInfo(@Param("username") long id) throws Exception;

    //退出实验室功能
    //设置退出时间
    @Update("update  student SET outTime=NOW() where id=#{id} ")
    void exitLab(@Param("id") long id)throws  Exception;
    //设置账号身份
    @Update("update  account SET identity=4 where id=#{id}")
    void exitLab2(@Param("id") long id)throws  Exception;
}
