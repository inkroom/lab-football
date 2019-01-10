package com.nsu.action.school.admin;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.common.Constants;
import com.nsu.util.base.Clear;
import com.nsu.util.core.UrlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/school_admin/")
public class SchoolInfoAdminAction extends BaseAction {
    private String infoInfo;

    @RequestMapping({"admin_info_01", "admin_info_02", "admin_info_03", "admin_info_04"})
    public String getInfo() {
        try {
            infoInfo = (String) getSession().getAttribute("infoInfo");
            getRequest().setAttribute("infoInfo", infoInfo);
            getSession().removeAttribute("infoInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String schoolUrl = getSchoolUrl();
            Map<String, Object> infoMap = getServiceManager().getSchoolInfoAdminService().getOverviewAdminService(schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()));
            getRequest().setAttribute("infoMap", infoMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return "/school_admin/admin_overview/school_admin_info_update_0" + UrlUtil.getNumber(getRequest().getRequestURI());
    }
    /**
     * 学校概况
     1、校长寄语
     2、
     3、
     4、
     */
    @RequestMapping({"update_info_01", "update_info_02", "update_info_03", "update_info_04"})
    public String updateInfo(@RequestParam("myContent") String htmlContent) {
        log.info("清理之前----html内容为" + htmlContent+"   ");
        htmlContent = Clear.cleraJs(htmlContent);
        log.info("html内容为" + htmlContent);
        if (htmlContent.length() > 1900 || htmlContent.length() == 0) {
            infoInfo = "内容不能超过0-1000字！";
            return "redirect:/school_admin/admin_info_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
        try {
            String schoolUrl = getSchoolUrl();
            if (getServiceManager().getSchoolInfoAdminService().updateOverviewInfo(htmlContent, schoolUrl, UrlUtil.getNumber(getRequest().getRequestURI()))) {
                infoInfo = "操作成功！";
            } else {
                infoInfo = "操作失败，请重试";
            }
            getSession().setAttribute("infoInfo", infoInfo);
            return "redirect:/school_admin/admin_info_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        } catch (Exception e) {
            infoInfo = "操作错误，请重试";
            getSession().setAttribute("infoInfo", infoInfo);
            e.printStackTrace();
            log.error(e.getMessage());
            return "redirect:/school_admin/admin_info_0" + UrlUtil.getNumber(getRequest().getRequestURI()) + ".action";
        }
    }


}
