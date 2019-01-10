package com.nsu.controller.student.profile;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.profile.ProfileBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.service.student.profile.ProfileService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Profile view controller类的描述
 *
 * @author Yjh
 * @Title: ProfileViewController
 * @Description: 学生查看自己的档案信息
 * @version: V1.0
 * @date 2017 -07-14 16:24:25
 */
@Controller
@RequestMapping("student/profile")
public class ProfileViewController implements Anonymous {
    @Resource
    ProfileService profileService;
    AjaxBean ajaxBean = new AjaxBean();
    @InterceptorAnno(isAjax=true)
    @RequestMapping("{S_id}")
    @ResponseBody
    public AjaxBean profileview(@PathVariable Integer S_id){
        try {
            //根据S_id查找学生信息
            System.out.println(S_id);
            ProfileBean profileBean = profileService.getStudentProfileById(3);
            profileBean.setNowClassName(profileService.getStudentClassNameById(3));
            profileBean.setPastSchool(profileService.getPastSchoolNameById(3));
            ajaxBean.setSuccess(true);
            ajaxBean.setStatus("200");
            ajaxBean.put("profileInformation",profileBean);
            ajaxBean.put("scoreInfo",profileService.getStudentExamById(3));
            return ajaxBean;
        }
        catch(Exception e){

            ajaxBean.setSuccess(false);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("查看档案失败");
            return ajaxBean;
        }
    }
}
