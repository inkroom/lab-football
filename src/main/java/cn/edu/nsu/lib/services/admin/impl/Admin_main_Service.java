package cn.edu.nsu.lib.services.admin.impl;

import cn.edu.nsu.lib.bean.admin.Student_check;
import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.db.DbStudentCheck;
import cn.edu.nsu.lib.dao.admin.IAdmin_Main_DAO;
import cn.edu.nsu.lib.services.admin.IAdmin_main_Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王振科 on 2017/10/5.
 */
@Service
public class Admin_main_Service implements IAdmin_main_Service {
    private static Log log = LogFactory.getLog(Admin_main_Service.class);

    @Autowired
    private IAdmin_Main_DAO main_dao;


    @Override
    public ArrayList<Student_check> mainpage_Service(int lab_id) {
        //通过时间和实验室id 查询该实验室的考勤
        List<DbStudentCheck> checks = main_dao.getstucheck_bydate(lab_id);
        //最终返回的数据集合(学生考勤信息)
        ArrayList<Student_check> list = new ArrayList<>();
        //遍历实验室考勤表，封装考勤信息到list
        for (DbStudentCheck check : checks) {
            log.info("DAO查询到的id" + check.getId());
            Student_check student_check = new Student_check();
            //插入学生信息
            student_check.setRegister(check.isRegister());
            student_check.setStudent_id(check.getStu_id());
            student_check.setDate(check.getDate());
            //通过学生id查询到该学生的信息
            DbStudent student = main_dao.getstu_byid(check.getStu_id());
            student_check.setName(student.getName());
            System.out.println(student.getName());
            list.add(student_check);
        }
        return list;
    }

    public String getLabname_service(int lab_id) {
        DbLab db_lab = main_dao.getlab_byid(lab_id);
        return db_lab.getName();
    }


}
