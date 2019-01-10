package cn.edu.nsu.lib.services.admin;

import cn.edu.nsu.lib.bean.admin.form.Student_form;
import cn.edu.nsu.lib.enums.Result;

/**
 * Created by 王振科 on 2017/9/26.
 */
public interface IAdmin_LabmanC_Service {

    /**
     * 判断表单添加是否成功
     * @param student_form
     * @param labid
     * @return
     */
    public Result checkadd_Service(Student_form student_form, int labid) throws Exception;

    /**
     * 判断表单修改是否成功
     * @param student_form
     * @param labid
     * @return
     */
    public Result checkalter_Service(Student_form student_form, int labid) throws Exception;

}
