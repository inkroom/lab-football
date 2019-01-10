package cn.edu.nsu.lib.services.admin.impl;

import cn.edu.nsu.lib.bean.admin.db.DbNotice;
import cn.edu.nsu.lib.bean.admin.form.HandleForm.HandleNotice;
import cn.edu.nsu.lib.bean.admin.form.Notice_form;
import cn.edu.nsu.lib.bean.admin.form.Utils.FormUtil;
import cn.edu.nsu.lib.dao.admin.IAdmin_NoticeC_DAO;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.IAdmin_NoticeC_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 王振科 on 2017/9/26.
 */
@Service
public class Admin_NoticeC_Service implements IAdmin_NoticeC_Service {
    @Autowired
    private IAdmin_NoticeC_DAO noticeC_dao;

    @Override
    public Result Uploadnoticems_Service(Notice_form notice_form,String File_path) throws Exception {
        DbNotice notice = new DbNotice();
        //不传入公告id，数据库有自增
        notice.setLab_id(FormUtil.getInt(notice_form.getLab_id()));
        notice.setPublisher(FormUtil.getBI(notice_form.getPublisher()));
        notice.setTitle(notice_form.getTitle());
        notice.setContent(notice_form.getContent());
        notice.setTime(notice_form.getTime());
        MultipartFile file = notice_form.getFile();
        notice.setFile_name(file.getName());
        //Controller层处理后，形参方式传递进来
        notice.setFile_path(File_path);

        int count = noticeC_dao.add_notice(notice);
        if (count == 1) {
            return Result.SUCCESS;
        } else {
            return Result.FAIL;
        }
    }

    public int Uploadnoticems_Service(Notice_form notice_form)throws Exception {
        return 0;
    }

    public Result Uploadnoticems_Service(Notice_form notice_form, int lab_id) throws Exception {
        //Notice封装好的处理类(会把form表单的数据处理后传进notic实体类)，
        // 构造方法依次传入Notice表单和Notice的实体类
        HandleNotice handleNotice = new HandleNotice(notice_form);
        DbNotice notice = handleNotice.getdb_Notice();
        int count = noticeC_dao.addnotice_bybean(notice);
        if(count == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }





}
