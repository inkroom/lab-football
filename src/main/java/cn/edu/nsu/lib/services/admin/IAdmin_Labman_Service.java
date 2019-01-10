package cn.edu.nsu.lib.services.admin;

import cn.edu.nsu.lib.bean.admin.db.DbMajor;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.enums.Result;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/9/26.
 */
public interface IAdmin_Labman_Service {

    /**
     * 判断通过id删除成员是否成功
     *
     * @param id
     * @return
     */
    public Result deleteLabman_Service(BigInteger id) throws Exception;

    /**
     * 通过成员id返回成员对象
     * @return
     */
    public Student_form tochangeLabman_Service(BigInteger id, int lab_id) throws Exception;

    /**
     * 通过session里的 实验室id，前端传入的修改的学生的id
     *  返回该学生表单对象
     * @param Lab_id
     * @return
     */
    public ArrayList<DbStudent> toLabmaninfo_Service(int Lab_id) throws Exception;

    /**
     * 通过session里的实验室id，返回该实验室指导老师
     * @param i
     * @return 老师list
     */
    public ArrayList<DbTeacher> toLabman_Teacherinfo_Service(int i) ;


    /**
     * 添加人员的时候传递给前端所有的专业名字
     * @return
     */
    public ArrayList<DbMajor>  toaddLabman_Service();
}
