package cn.edu.nsu.lib.dao.teacher.impl;

import cn.edu.nsu.lib.bean.teacher.NoticeEntity;
import cn.edu.nsu.lib.bean.teacher.StudentEntity;
import cn.edu.nsu.lib.dao.BaseDao;
import cn.edu.nsu.lib.dao.teacher.ITeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TeacherDaoImpl extends BaseDao implements ITeacherDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 通过教师id找到实验室id列表
     *
     * @param t_id 教师id
     * @return 实验室id
     */
    @Override
    public List<Map<String, Object>> findLab(String t_id) throws Exception {

        /**
         * creat_user: 蒋玖宏 julse@qq.com
         * creat_date: 2017/9/28
         **/
        String sql = getSql("SELECT * FROM RELATIONSHIP WHERE TEACHER_ID = '?'", new String[]{t_id});
        log.info(sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过实验室id号查找实验室的详细信息
     *
     * @param lab_id
     * @return
     */
    public List<Map<String, Object>> findLabInfo(String lab_id) {
        /**
         * creat_user: 蒋玖宏 julse@qq.com creat_date: 2017/9/29
         **/
        String sql = "SELECT * FROM LAB WHERE ID = " +
                lab_id;
        log.info(sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过实验室id找到学生列表，只查找学生的简单信息
     *
     * @param lab_id
     */
    @Override
    public List<Map<String, Object>> findStuList(String lab_id) {
        String sql = "SELECT\n" +
//                "lab.`name` AS lab_name,\n" +
                "student.tel,\n" +
                "major.`name` AS major_name,\n" +
                "student.time,\n" +
                "student.grade,\n" +
                "student.`name`,\n" +
//                "student.gender,\n" +
                "student.id\n" +
//                "student.instructor,\n" +
//                "student.stuClass,\n" +
//                "student.department\n" +
                "FROM\n" +
                "\tstudent\n" +
                "INNER JOIN lab ON student.lab_id = lab.id\n" +
                "INNER JOIN major ON student.maj_id = major.id \n" +
                "and lab_id = " + lab_id;
        log.info(sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据专业编号查找专业
     *
     * @param major_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findMajor(String major_id) {
        String sql = "SELECT NAME FROM MAJOR WHERE ID = " +
                major_id;
        log.info(sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查找实验室管理员：理论上只能有一个管理员，但是由于数据库的表结构，一个实验室的管理员可能在测试数据中有多个
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findLabAdmin(String lab_id) {
        String sql = "SELECT S.ID FROM STUDENT S JOIN ACCOUNT A ON  S.ID=A.ID AND IDENTITY = 1 AND LAB_ID = " +
                lab_id;
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> findStuInfo(String stu_id) {//写到此处，要修改数据库查询语句
        String sql = "SELECT\n" +
                "lab.`name` AS lab_name,\n" +
                "student.tel,\n" +
                "major.`name` AS major_name,\n" +
                "student.time,\n" +
                "student.grade,\n" +
                "student.`name`,\n" +
                "student.gender,\n" +
                "student.id,\n" +
                "student.instructor,\n" +
                "student.stuClass,\n" +
                "student.department\n" +
                "FROM\n" +
                "\tstudent\n" +
                "INNER JOIN lab ON student.lab_id = lab.id\n" +
                "INNER JOIN major ON student.maj_id = major.id \n" +
                "and student.id = " + stu_id;
        log.info("查询学生详细信息" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过实验室id查找实验室通知列表
     *
     * @param lab_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findNoticeList(String lab_id, String teacher_id) throws Exception {
//        String sql = "SELECT * FROM NOTICE WHERE LAB_ID =" +
//                lab_id;
        String sql = getSql("select * from notice where lab_id = ?", lab_id);
//        String sql = getSql("SELECT \n" +
//                "ID,NAME,\n" +
//                "TIME,TITLE,CONTENT,FILE_NAME,FILE_PATH\n" +
//                "FROM TEACHER join NOTICE on teacher.id = publisher WHERE LAB_ID = ?", lab_id);
        log.info("实验室通知查找：" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据学生学号查询学生的月考勤次数
     *
     * @param s_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> count_fre(String s_id) throws Exception {
        String sql = getSql("select count(*) frequency from signed where stu_id=? and register = 1\n" +
                "and DATE_FORMAT( date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' );", new String[]{s_id});
        log.info("查询学生月考勤次数：" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查询该实验室学生人数
     *
     * @param lab_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findStuNum(String lab_id) {
        String sql = "SELECT COUNT(*) STU_NUM FROM STUDENT WHERE LAB_ID = " + lab_id;
        log.info("该实验室人数：" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 某实验室考勤总次数
     *
     * @param lab_id
     * @return
     */
    @Override
    public List<Map<String, Object>> count_fre_total(String lab_id) throws Exception {
        String sql = getSql("SELECT COUNT(*) fre_total FROM SIGNED WHERE LAB_ID=? AND REGISTER = 1\n" +
                "AND DATE_FORMAT( DATE, '%Y%M' ) = DATE_FORMAT( CURDATE( ) , '%Y%M' )", new String[]{lab_id});
        log.info("查询实验室月考勤总次数:" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过实验室id号查询该实验室的教师团队
     *
     * @param lab_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findTeacherList(String lab_id) throws Exception {
        String sql = getSql("select * from teacher where id in (SELECT teacher_id from relationship where lab_id = ?);", new String[]{lab_id});
        log.info("查询实验室教师团队：" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据实验室id号查找实验室名称
     *
     * @param lab_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findlabName(String lab_id) {
        String sql = "select name from lab where id =" + lab_id;
        log.info("根据实验室id号查找实验室名称");
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据学号查找学生获奖次数
     *
     * @param stu_id
     * @return
     */
    @Override
    public List<Map<String, Object>> count_prize(String stu_id) {
        String sql = "select count(*) prize_sum from prize WHERE `owner` =" + stu_id;
        log.info("根据学号查找学生获奖次数\n" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据学生学号查找获奖信息列表
     *
     * @param stu_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findPrizeList(String stu_id) throws Exception {
        String sql = getSql("select * from prize where OWNER= '?'", stu_id);
        log.info("根据学生学号查找获奖信息列表\n" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据学生学号查找成绩列表
     *
     * @param stu_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findCourseList(String stu_id) {
        String sql = "select grade,term,name FROM score JOIN course on course.id = score.course and stu_id = " + stu_id;
        log.info("根据学生学号查找成绩列表:" + sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 添加通知
     */
    @Override
    public void addNotice(NoticeEntity notice, String lab_id) {
        String sql = "INSERT INTO notice (\n" +
                " lab_id,\n" +
                " publisher,\n" +
                " time,\n" +
                " title,\n" +
                " content,\n" +
                " file_name,\n" +
                " file_path\n" +
                ")\n" +
                "VALUES\n" +
                " (\n" +
                lab_id +
                ",\n" +
                notice.getPublisher() +
                ",\n" +
                "date_format(now(),'%y-%m-%d'),'" +
                notice.getTitle() +
                "',\n'" +
                notice.getContent() +
                "',\n'" +
                notice.getFile_name() +
                "',\n'" +
                notice.getFile_path() +
                "' );";
        log.info("添加通知" + sql);
        jdbcTemplate.execute(sql);
    }

    /**
     * 添加学生信息（学号，姓名，实验室）
     *
     * @param stu
     * @return
     */
    @Override
    public void addStu(StudentEntity stu) throws Exception {//此处抛出的异常有可能是字段拼接的问题
        String sql = getSql("INSERT student(id,name,lab_id)\n" +
                "VALUES(?,'?',?);", new String[]{stu.getId(), stu.getName(), stu.getLab_id()});
        jdbcTemplate.execute(sql);
    }

    /**
     * 更新学生实验室id
     *
     * @param stu_id
     * @throws Exception
     */
    @Override
    public int addStuAccount(String stu_id) throws Exception {
        String sql = getSql("insert account(id,salt,passwd,identity)\n" +
                "VALUES('?','qmha7w9g','EmkXwUP69X4aZo/xQAP2vQ==',0);",stu_id);
        log.info("添加学生账户：\n" + sql);
       return jdbcTemplate.update(sql);
    }

    /**
     * 更新学生实验室id
     *
     * @param stu_id
     * @param lab_id
     */
    @Override
    public int updateStuLab(String stu_id, String lab_id) throws Exception {
        String sql = getSql("UPDATE student\n" +
                "set lab_id =?\n" +
                "where id = ?;", new String[]{stu_id, lab_id});
        log.info("更新学生的实验室id：\n" + sql);
      return   jdbcTemplate.update(sql);
    }


}
