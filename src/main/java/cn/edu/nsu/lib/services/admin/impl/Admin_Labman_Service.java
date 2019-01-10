package cn.edu.nsu.lib.services.admin.impl;

import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbMajor;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import cn.edu.nsu.lib.bean.admin.db.DbTeacher;
import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.dao.admin.IAdmin_Labman_DAO;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.IAdmin_Labman_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Service
public class Admin_Labman_Service implements IAdmin_Labman_Service {
    @Autowired
    private IAdmin_Labman_DAO labman_dao;

    @Override
    public Result deleteLabman_Service(BigInteger id) throws Exception {
        //通过学生id直接删除account表中的数据，级联删除了student表的数据
        int count = labman_dao.deleteman_byid(id);
        //如果影响行数为0就返回fail
        if (count == 0) {
            return Result.FAIL;
        }
        return Result.SUCCESS;
    }

    @Override
    public Student_form tochangeLabman_Service(BigInteger id, int lab_id) throws Exception {
        DbStudent student = labman_dao.getstu_byid(id, lab_id);
        //传回给前端一个修改的该学生的信息表单
        Student_form student_form = new Student_form();

        //通过学生表信息中的实验室id查到实验室名字
        DbLab db_lab = labman_dao.getlab_byid(lab_id);
        student_form.setLab_name(db_lab.getName());
        //通过学生表信息中的专业id查到专业名字
        DbMajor db_major = labman_dao.getmajor_byid(student.getMaj_id());
        if (db_major != null)
            student_form.setMajor(db_major.getName());

        student_form.setStudent_id(student.getId() + "");
        student_form.setName(student.getName());
        student_form.setGender(student.getGender() + "");
        student_form.setGrade(student.getGrade() + "");
        student_form.setTime(student.getTime());
        student_form.setInstructor(student.getInstructor());
        student_form.setTel(student.getTel() + "");
        student_form.setIDcard(student.getIDcard());
        student_form.setStuClass(student.getStuClass() + "");
        student_form.setDepartment(student.getDepartment());
        student_form.setOutTime(student.getOutTime());

        // TODO: 2017/11/16  上面代码过于臃肿，之后改进
        return student_form;
    }


    @Override
    public ArrayList<DbStudent> toLabmaninfo_Service(int Lab_id) throws Exception {
        ArrayList<DbStudent> list = labman_dao.getstus_byid(Lab_id);
        for (DbStudent student : list) {
            String lab_name = labman_dao.getlabname_byid(student.getLab_id());
            String maj_name = labman_dao.getmajname_byid(student.getMaj_id());
            //传入实验室名字
            student.setLab_name(lab_name);
            //传入专业名字
            student.setMaj_name(maj_name);
        }
        return list;
    }

    public ArrayList<DbTeacher> toLabman_Teacherinfo_Service(int Lab_id) {
        ArrayList<BigInteger> teacher_ids = labman_dao.gettechids_bylabid(Lab_id);
        //最终返回的老师list
        ArrayList<DbTeacher> teachers = new ArrayList<>();
        //iter快捷foreach
        for (BigInteger teacher_id : teacher_ids) {
            //通过老师id 在teacher表获得老师全部信息
            DbTeacher teacher = labman_dao.getteachs_byid(teacher_id);
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public ArrayList<DbMajor> toaddLabman_Service() {
        ArrayList<DbMajor> db_majors = labman_dao.getmajor_all();
        return db_majors;
    }
}
