package cn.edu.nsu.lib.controllers.admin;

/**
 * Created by 王振科 on 2017/9/26.
 */

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.admin.Notice;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.impl.Admin_Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * 该类实现显示实验室公告的信息
 * 1.显示公告信息
 * 2.跳转到添加公告页面
 * 3.下载公告
 * 4.根据公告id删除某条公告
 */
@Controller
@RequestMapping("/NoticeAdministrator")
public class Administrator_Notice_Controller extends BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Admin_Manager service;
    @Autowired
    private HttpSession session;
    Log log = LogFactory.getLog(Administrator_Notice_Controller.class);

    /**
     * toNotice() 这个方法的描述
     *
     * @param
     * @ClassName: toNotice
     * @Description:
     * @Author: 王振科
     * @Date: 11:21
     * @URL: /NoticeAdministrator/toNotice
     */
    @RequestMapping("/toNotice")
    @Authority(role = Authority.Role.MANAGER)
    public String toNotice() throws Exception {

        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        //通过实验室id获得公告
        ArrayList<Notice> notices = service.getAdmini_notice_service()
                .toNotice_Service(lab_id);
        request.setAttribute("notices", notices);

        String lab_name = service.getAdmini_service().
                getLabname_service(lab_id);
        //request存入实验室对象给前端
        request.setAttribute("lab_name", lab_name);

        //公告的key是notices
        return "/administrator/toNotice";
    }

    /**
     * Deletenotice() 这个方法的描述
     *
     * @param id
     * @ClassName: Deletenotice
     * @Description: 通过公告id，删除公告
     * @Author: 王振科
     * @Date: 11:21
     * @URL: /NoticeAdministrator/Deletenotice
     */
    @RequestMapping("/Deletenotice")
    @ResponseBody
    @Authority(role = Authority.Role.MANAGER)
    public AjaxBean Deletenotice(int notice_id) throws Exception {

        int lab_id = (int) getLogin(request).get(Constants.KAY_MAP_LAB_ID);
        Result status = service.getAdmini_notice_service()
                .Deletenotice_Service(notice_id, lab_id);
        if (status == Result.FAIL) {
            return new AjaxBean(Result.FAIL);
        } else {
            return new AjaxBean(Result.SUCCESS);
        }
    }

    @RequestMapping("/toUploadnotice")
    @Authority(role = Authority.Role.MANAGER)
    public String toUploadnotice() {
        /**
         toUploadnotice() 这个方法的描述
         * @ClassName: Uploadnotice
         * @Description: 跳转到上传公告页面
         * @Author: 王振科
         * @Date: 11:21
         * @URL: /NoticeAdministrator/toUploadnotice
         * @param
         */
        return "/administrator/toUploadnotice";
    }

}
