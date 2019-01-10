package cn.edu.nsu.lib.controllers.admin;

/**
 * Created by 王振科 on 2017/9/26.
 */

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.form.Notice_form;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 该类实现添加公告
 * 1.点击添加，可以添加上传的 标题，内容，文件（文件名字，文件路径）
 */
@Controller
@RequestMapping("/NoticeCAdministrator")
public class Administrator_NoticeC_Controller extends  BaseController{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;
    Log log = LogFactory.getLog(Administrator_NoticeC_Controller.class);

    @RequestMapping("/Uploadnotice")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean Uploadnoticems(Notice_form notice_form) throws Exception {
        /**
            Uploadnoticems() 这个方法的描述
         * @ClassName: Uploadnoticems
         * @Description: 确认上传公告信息
         * @Author: 王振科
         * @Date: 11:21
         * @URL: /NoticeCAdministrator/Uploadnotice
         * @param notice_form
         */
        //session获得实验室id
        int lab_id = (int)getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //session获得的id值强转成string类型，方便form表单的提交
        String id = (String)getLogin(request).get("id");
//        service.getAdmini_noticeC_service().Uploadnoticems_Service(notice_form,notice_form.getFile().getName());
        /********************************添加***************************************************
         */
        int Lab_id = (int) session.getAttribute("Lab_id");
//         Result status = service.getAdmini_noticeC_service().
//                Uploadnoticems_Service(notice_form,Lab_id);

        notice_form.setLab_id(Integer.toString(lab_id));
        notice_form.setPublisher(id);
        log.info(notice_form.toString());
        String File_path = getFileupPath(notice_form.getFile());

        Result status = service.getAdmini_noticeC_service()
                .Uploadnoticems_Service(notice_form,File_path);
        if(status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        }else {
            return new AjaxBean(Result.SUCCESS);
        }
    }

    protected String getFileupPath(MultipartFile file) throws Exception {
        //获得文件全部名字
        String fileFileName = file.getOriginalFilename();
        //如果文件名字不为空就以字符串形式取 .后面的类型
        if (!fileFileName.trim().equals("") && fileFileName.length() > 0) {
            //获得文件类型
            String filetype = fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());

            return uploadFile(file,filetype);
        }
        return null;
    }
}
