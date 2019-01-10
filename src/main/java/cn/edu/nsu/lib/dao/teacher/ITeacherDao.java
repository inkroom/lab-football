package cn.edu.nsu.lib.dao.teacher;

import cn.edu.nsu.lib.bean.teacher.NoticeEntity;
import cn.edu.nsu.lib.bean.teacher.StudentEntity;

import java.util.List;
import java.util.Map;

public interface ITeacherDao {
    /**
    * class_name: ITeacherDao
    * describe: 教师端数据库接口
    * creat_user: 蒋玖宏 julse@qq.com
    * creat_date: 2017/9/28
    * creat_time: 21:10
    **/



    /**
     * 通过教师id找到实验室列表
     * @param t_id
     * @return LabEntity实验室实体类
     * @throws Exception
     */
    List<Map<String, Object>> findLab(String t_id) throws Exception;

    /**
     *  通过实验室id找到实验室的详细信息
     * @param lab_id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> findLabInfo(String lab_id) throws Exception;

    /**
     * 通过实验室id找到学生列表
     */
    List<Map<String, Object>> findStuList(String lab_id) throws Exception;
    /**
     * 通过专业id号查找专业名称
     * @param major_id
     * @return
     */
    List<Map<String, Object>> findMajor(String major_id);

    List<Map<String, Object>> findLabAdmin(String lab_id);

    /**
     * 通过学生id查找学生详细信息
     * @param stu_id
     * @return
     */
    List<Map<String, Object>> findStuInfo(String stu_id);

    /**
     * 通过实验室id和教师id查找实验室通知列表
     * @param lab_id
     * @param teacher_id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> findNoticeList(String lab_id, String teacher_id) throws Exception;

    List<Map<String, Object>>  count_fre(String s_id) throws Exception;

    /**
     * 查询该实验室学生人数
     * @param lab_id
     * @return
     */
    List<Map<String,Object>> findStuNum(String lab_id);

    /**
     * 某实验室考勤总次数
     * @param lab_id
     * @return
     */
    List<Map<String,Object>> count_fre_total(String lab_id) throws Exception;

    /**
     * 通过实验室id号查询该实验室的教师团队
     * @param lab_id
     * @return
     */
    List<Map<String, Object>> findTeacherList(String lab_id) throws Exception;

    /**
     * 根据实验室id号查找实验室名称
     * @param lab_id
     * @return
     */
    List<Map<String,Object>> findlabName(String lab_id);

    /**
     * 根据学号查找学生获奖次数
     * @param stu_id
     * @return
     */
    List<Map<String,Object>> count_prize(String stu_id);

    /**
     * 根据学生学号查找获奖信息列表
     * @param stu_id
     * @return
     */
    List<Map<String,Object>> findPrizeList(String stu_id) throws Exception;

    /**
     * 根据学生学号查找成绩列表
     * @param stu_id
     * @return
     */
    List<Map<String,Object>> findCourseList(String stu_id);

    /**
     * 发布通知到对应实验室
     */
    void addNotice(NoticeEntity notice, String lab_id);

    /**
     * 添加学生信息（姓名，实验室）
     * @return
     */
    void addStu(StudentEntity stu) throws Exception;

    /**
     * 添加学生账号
     * @param stu_id
     */
    int addStuAccount(String stu_id)throws Exception;

    /**
     * 更新学生实验室id
     * @param stu_id
     * @param lab_id
     */
    int updateStuLab(String stu_id,String lab_id)throws Exception;


}

