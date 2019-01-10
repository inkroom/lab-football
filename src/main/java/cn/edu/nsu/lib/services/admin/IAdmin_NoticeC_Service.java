package cn.edu.nsu.lib.services.admin;

import cn.edu.nsu.lib.bean.admin.form.Notice_form;
import cn.edu.nsu.lib.enums.Result;

/**
 * Created by 王振科 on 2017/9/26.
 */
public interface IAdmin_NoticeC_Service {
    /**
     * Controller传入公告表单，并且处理得到文件file的上传路径
     * 传进Service层
     * @param notice_form
     * @return
     */
    public Result Uploadnoticems_Service(Notice_form notice_form, String File_path) throws Exception;

    public int Uploadnoticems_Service(Notice_form notice_form) throws Exception;

    public Result Uploadnoticems_Service(Notice_form notice_form, int lab_id) throws Exception;
}
