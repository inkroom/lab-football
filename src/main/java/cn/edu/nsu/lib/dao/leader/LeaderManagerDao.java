package cn.edu.nsu.lib.dao.leader;

import cn.edu.nsu.lib.bean.leader.*;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@Resource
public interface LeaderManagerDao{
    @Insert("insert INTO lab (`name`,address,qq_group,lab_describe) VALUES " +
            "(#{name},#{address},#{qqGrop},#{labDescribe})")
    void setLabInfo(LLabBean labInfo)throws  Exception;
   @Select("SELECT id,name ,lab_describe as labDescribe ,qq_group as qqGrop,address FROM  lab")
    ArrayList<LLabBean> getLabInfoList()throws Exception;
   //查询实验室下属老师
    @Select("select * from teacher a  ,relationship b where " +
            "b.lab_id =#{lId} and b.teacher_id =a.id ")
    ArrayList<LTeacherBean> getLabTeacher(@Param("lId") long id )throws Exception;
    //查询老师加入的实验室
    @Select("select a.id ,a.name from lab a  ,relationship b where " +
            "b.teacher_id =#{tId} and b.lab_id=a.id ")
    ArrayList<LTLabBean> getTeacherLab(@Param("tId") long id )throws Exception;
    //根据id查询实验室
    @Select("SELECT id,name ,lab_describe as labDescribe ,qq_group as qqGrop,address FROM  lab where id= #{lId}")
    LLabBean getLabInfo(@Param("lId") long lId)throws Exception;
    //查询所有老师信息
    @Select("SELECT a.* FROM  teacher a,account b where  a.id=b.id AND b.identity=2")
   ArrayList<LTeacherBean> getTeacherInfoList()throws Exception;
    //给实验室添加老师
    @Insert("insert INTO relationship (lab_id,teacher_id) VALUES (#{lId},#{tId})")
     void addLabTeacher(@Param("lId") long lId,@Param("tId") long tId)throws  Exception;
    //根据id查询老师信息

    @Select("SELECT a.* FROM  teacher a,account b  where  a.id=#{id} And a.id=b.id AND b.identity=2")
    LTeacherBean getTeacherInfo(@Param("id") long id)throws Exception;
    @Update("UPDATE Account SET passwd=#{password},salt=#{salt} where id = #{username} limit 1")
    void  resetPasswd(@Param("username") long username,@Param("password")String password,@Param("salt")String salt) throws Exception;

    @Delete("DELETE from account WHERE id = #{id}")
    void delTeacher(@Param("id") long id)throws  Exception;

    //添加老师信息加入账号信息
  @Insert("insert into account (id,salt,passwd,identity) " +
          "VALUES (#{id},#{salt},#{passwd},#{identity})")
    void addAccount(LAccountBean lAccountBean)throws  Exception;
    @Insert("insert into teacher (id,name,gender,tel) " +
            "VALUES (#{id},#{name},#{gender},#{tel})")
    void addTeacher(LAccountBean lAccountBean)throws  Exception;
    @Insert("insert into notice  (lab_id,publisher,time,title,content,file_name,file_path) " +
            "            VALUES ( #{lab_id},#{publisher},#{time},#{title},#{content},#{file_name},#{file_path})")
void insertNotice(LFileBean lFileBean)throws  Exception;

    @Delete("DELETE from lab WHERE id = #{id}")
    void delLab(@Param("id") int id)throws  Exception;
    @Delete("DELETE from relationship WHERE lab_id = #{labId} and teacher_id=#{tId}")
    //从实验室移除老师
    void removeTeacher(@Param("tId") long t_id,@Param("labId") long labId)throws  Exception;


    //    @Select("select * from student where id = #{id} limit 1")
//    Map<String, Object> getStudentUser(@Param("id") long id) throws Exception;
//    //查找所有的奖项
//
//    @Select("Select * from prize where owner=#{owner}")
//    List<StuPrizeBean> getProzeList(@Param("owner") long id)throws  Exception;
//
//    @Insert("insert into prize  ( lab_id,owner, prize_name, region, rank, category, url, time, committee, adviser,is_checked)" +
//            "values (#{lab_id},#{ owner},#{prize_name},#{region},#{rank},#{category},#{url},#{time}," +
//            "#{committee},#{adviser},#{is_checked)")
//    void setProzeList(StuPrizeBean stuPrizeBean)throws  Exception;
//
////查找所有成绩信息
//
//    @Select("select a.course,c.`name` as courseName,a.term,a.grade FROM score a,major b,course c" +
//            "  where a.stu_id=#{stu_id} and a.course=c.id and a.major =b.id")
//    List<StuScoreBean> getStuScoreList(@Param("stu_id") long id)throws  Exception;



}
