package cn.edu.nsu.lib.controllers.student;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.student.StuNoticeBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.student.StuNoticeService;
import cn.edu.nsu.lib.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*
*@auther ChenGang
*@说明：跳转到文档页面
**/
@Controller
@RequestMapping("/student")
public class StuDownFileController extends BaseController  {
    @Autowired
    private StuNoticeService stuNoticeService;
    @Autowired
    private HttpServletRequest request;

    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    @RequestMapping(value = "/stuFileList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxBean stuFileList()  {
        AjaxBean ajaxBean = new AjaxBean(Result.SUCCESS);
        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        log.info("loginMap不为空");

        if (loginMap.containsKey(Constants.KAY_MAP_LAB_ID)) {
       int lab_id=(int)loginMap.get(Constants.KAY_MAP_LAB_ID);
          log.info(lab_id);

            List<StuNoticeBean> stuNoticelist = null;
            try {
                stuNoticelist = stuNoticeService.findNoticeListByid(lab_id);
                ajaxBean.put("noticeList", stuNoticelist);
                return ajaxBean;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new AjaxBean(Result.FAIL);
    }


}
