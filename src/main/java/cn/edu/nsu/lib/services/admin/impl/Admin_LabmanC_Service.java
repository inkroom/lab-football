package cn.edu.nsu.lib.services.admin.impl;

import cn.edu.nsu.lib.bean.admin.db.DbAccount;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.bean.admin.form.Utils.FormUtil;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.dao.admin.IAdmin_LabmanC_DAO;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.IAdmin_LabmanC_Service;
import cn.edu.nsu.lib.utils.EncryptUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Service
public class Admin_LabmanC_Service implements IAdmin_LabmanC_Service {
    @Autowired
    private IAdmin_LabmanC_DAO labmanC_dao;
    private Log log = LogFactory.getLog(Admin_LabmanC_Service.class);

    @Override
    public Result checkadd_Service(Student_form student_form, int labid)throws Exception {
        DbStudent student = new DbStudent();
            //给要传入数据库的实体类注入信息
            student.setId(FormUtil.getBI(student_form.getStudent_id()));//学号
            //当前实验室管理员只能添加给自己实验室的人员
            student.setLab_id(labid);
            student.setMaj_id(FormUtil.getInt(student_form.getMajor()));

            student.setTel(FormUtil.getBI(student_form.getTel()));
            student.setGender(FormUtil.getInt(student_form.getGender()));
            student.setGrade(FormUtil.getInt(student_form.getGrade()));
            student.setTime(student_form.getTime());
            student.setName(student_form.getName());
            student.setInstructor(student_form.getInstructor());
            student.setIDcard(student_form.getIDcard());//身份证
            student.setStuClass(FormUtil.getInt(student_form.getStuClass()));//班级
            student.setDepartment(student_form.getDepartment());//宿舍楼

        //查询account表，如果帐号已经存在就不用重复创建了
        DbAccount isnullaccount = labmanC_dao.getaccount_byid(student.getId());
        //count1用来判断account表添加学生，是否成功添加
        int count1 = 1;//默认为1，除非没有根据表单 查询到账户表中的
        if(isnullaccount==null){
            log.info("要添加的用户不存在，先添加进account表");
            //传值给db_account关于数据库的account实体
            DbAccount db_account = new DbAccount(student.getId(),0);
            String pws[] = EncryptUtil.parseMd5(Constants.INIT_PASSWORD);
            db_account.setSalt(pws[0]);
            db_account.setPasswd(pws[1]);

            //管理员添加学生，通过student_form表单填写的学号 添加到account表中
            count1 = labmanC_dao.addaccount_byid(db_account);
        }
        if(count1 == 1){
            //通过传入学生实体类
            int count = labmanC_dao.addstu(student);
            if(count == 1){
                return  Result.SUCCESS;
            }
            log.info("添加学生到student表失败");
        }else {
            log.info("添加到学生表的帐号就问题");
        }
        return  Result.FAIL;
    }

    @Override
    public Result checkalter_Service(Student_form student_form, int lab_id)throws Exception {
        DbStudent student = new DbStudent();
        //给要传入数据库的实体类注入类型对等的信息
        student.setId(FormUtil.getBI(student_form.getStudent_id()));
        student.setTel(FormUtil.getBI(student_form.getTel()));
        //通过Controller层从session中获得的实验室id 传进要添加的student中
        student.setLab_id(lab_id);
        student.setMaj_id(FormUtil.getInt(student_form.getMajor()));
        student.setGender(FormUtil.getInt(student_form.getGender()));
        student.setGrade(FormUtil.getInt(student_form.getGrade()));
        student.setTime(student_form.getTime());
        student.setName(student_form.getName());
        student.setInstructor(student_form.getInstructor());
        student.setIDcard(student_form.getIDcard());//身份证
        student.setStuClass(FormUtil.getInt(student_form.getStuClass()));//班级
        student.setDepartment(student_form.getDepartment());//宿舍楼
        student.setOutTime(student_form.getOutTime());//退出实验室的时间
        //通过传入学生实体类进行修改
        int count = labmanC_dao.alterstu(student);
        if(count == 1){
            return  Result.SUCCESS;
        }
        return  Result.FAIL;
    }
}
