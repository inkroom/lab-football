package cn.edu.nsu.lib.services.teacher;

import cn.edu.nsu.lib.bean.teacher.*;

import java.util.ArrayList;
import java.util.List;
public interface ITeacherService {
    /**
    * class_name: ITeacherService
    * describe: 教师端Service接口
    * creat_user:  julse@qq.com
    * creat_date: 2017/9/28
    * creat_time: 20:46
    **/

    /**
     * 获取学生列表
     * @param lab_id 实验室id
     * @return
     */
    public ArrayList<StudentEntity> findStuListAll(String lab_id);
    /**
    * creat_user:  julse@qq.com
    * creat_date: 2017/9/28
    **/

    /**
     * 获取实验室id和名称列表
     * @param t_id 教师id
     * @return
     */
    public List<LabEntity> findLabList(String t_id) throws Exception;

    /**
     * 通过实验室id号查询实验室的学生信息列表
     * @param lab_id 实验室id
     * @return
     * @throws Exception
     */
    public List<StudentEntity> findStuList(String lab_id) throws Exception;

    /**
     * 通过学生id号查询学生详细信息
     * @param stu_id
     * @return
     */
    StudentEntity findStuInfo(String stu_id) throws Exception;

    /**
     * 通过实验室id查询实验室通知列表
     * @param lab_id
     * @return
     */
    List<NoticeEntity> findNoticeList(String lab_id,String t_id) throws Exception;

    /**
     * 通过实验室id号查询该实验室的教师管理团队
     * @param lab_id
     * @return
     */
    List<TeacherEntity> findTeacherList(String lab_id) throws Exception;

    /**
     * 根据学生学号查找学生获奖信息列表
     * @param stu_id
     * @return
     */
    List<PrizeEntity> findPrizeList(String stu_id) throws Exception;

    /**
     * 根据学生学号查找成绩列表
     * @param stu_id
     * @return
     */
    List<ScoreEntity> findCourseList(String stu_id);

    /**
     * 发布通知到对应实验室
     * @param notice
     * @param labs_id
     * @return
     */
    boolean addNotice(NoticeEntity notice,String[] labs_id);

    /**
     * 添加学生信息
     * @return
     */
    int addStu(StudentEntity stu);

    /**
     * 添加学生信息
     * 根据实验室ID获取实验室的所有公告
     */
    List<NoticeEntity> findNoticeList(String lab_id) throws Exception;
}
