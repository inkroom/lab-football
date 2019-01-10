package com.nsu.action.admin.school;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.common.Constants;
import com.nsu.util.base.PageUtil;
import com.nsu.util.base.ResponseUtil;
import com.nsu.util.base.VerifyUtil;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin_school/")
public class AdminSchoolAction extends BaseAction {
//    private String schoolUrl;
//    private String schoolName;
//    private String schoolType;
//
//    private String errorInfo;
//
//    private Map<String, Object> school;
//
//    private String page;
//
//    private List<Map<String, Object>> allSchool;
//
//    private String pageCode;
//
//    private String username;

    @RequestMapping("add_school_view")
    public String addSchoolView() {
        try {
            String errorInfo = getSession().getAttribute("errorInfo").toString();
            getSession().removeAttribute("errorInfo");
            getRequest().setAttribute("errorInfo", errorInfo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return "/admin_school/add_school";
//		return SUCCESS;
    }

    @RequestMapping("add_school")
    public String addSchool(String schoolName, String schoolUrl, String schoolType) {
        String errorInfo = null;
        if (!((Map<String, String>) getSession().getAttribute(Constants.LOGIN_USER)).get("TYPE").toString().equals("1")) {
            errorInfo = "该账号没有权限执行此操作";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (schoolName == null || schoolName.equals("")) {
            errorInfo = "学校名不能为空";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (schoolUrl == null || schoolUrl.equals("")) {
            errorInfo = "域名不能为空";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (!schoolUrl.matches("^[a-zA-Z0-9]{1,40}$")) {
            errorInfo = "域名只能使用字母和数字";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (schoolUrl.equals("-1")) {
            errorInfo = "请选择网站类型";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (!getServiceManager().getAdminSchoolService().getSchoolName(schoolName)) {
            errorInfo = "学校名已存在";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        } else if (!getServiceManager().getAdminSchoolService().getSchoolName(schoolUrl)) {
            errorInfo = "域名已存在";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        }
        Map<String, Object> school = null;
        try {
            school = getServiceManager().getAdminSchoolService().addSchool(schoolName, schoolUrl, schoolType, ((Map<String, String>) getSession().getAttribute(Constants.LOGIN_USER)).get("USERNAME").toString());
            getRequest().setAttribute("school", school);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (school == null || school.get("USERNAME").toString().equals("-1")) {
            errorInfo = "操作失败，请重试！";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/add_school_view.action";
//            return "error";
        }
        return "/admin_school/add_school_success";
//        return "success";
    }

    @RequestMapping("get_school")
    public String getSchoolInfo(String page) {
        int pageSize = 10;
        int currPage = 1;
        try {
            currPage = Integer.parseInt(page);
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            currPage = 1;
            page = "1";
        }
        List<Map<String, Object>> allSchool = null;
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("pageStart", (currPage - 1) * pageSize);
        pageMap.put("pageSize", pageSize);
        allSchool = getServiceManager().getAdminSchoolService().getSchoolInfo(pageMap);
        long schoolTotal = getServiceManager().getAdminSchoolService().getSchoolTotal();
        StringBuffer param = new StringBuffer();
        param.append("token=" + getSession().getAttribute("token"));
        String pageCode = PageUtil.genPagination("get_school", schoolTotal, currPage, pageSize, param.toString());

        getRequest().setAttribute("pageCode", pageCode);
        getRequest().setAttribute("page", page);
        getRequest().setAttribute("allSchool", allSchool);

        return "/admin_school/add_school_list";
//        return SUCCESS;
    }

    @RequestMapping("restop_school")
    @ResponseBody
    public void reStopSchoolStatus(String schoolUrl, String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if (VerifyUtil.isNotEmpty(schoolUrl) && VerifyUtil.isNotEmpty(username)) {
            if (getServiceManager().getAdminSchoolService().updateSchoolStatus(schoolUrl, username, "2")) {
                jsonObject.put("success", true);
            } else {
                jsonObject.put("success", false);
                jsonObject.put("errorInfo", "操作失败，请重试!");
            }
        } else {
            jsonObject.put("success", false);
            jsonObject.put("errorInfo", "非法操作!");
        }
        jsonObject.put("token", getSession().getAttribute("token"));
        ResponseUtil.write(getResponse(), jsonObject);
    }

    @RequestMapping("restart_school")
    @ResponseBody
    public void reStartSchoolStatus(String schoolUrl, String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if (VerifyUtil.isNotEmpty(schoolUrl) && VerifyUtil.isNotEmpty(username)) {
            if (getServiceManager().getAdminSchoolService().updateSchoolStatus(schoolUrl, username, "1")) {
                jsonObject.put("success", true);
            } else {
                jsonObject.put("success", false);
                jsonObject.put("errorInfo", "操作失败，请重试!");
            }
        } else {
            jsonObject.put("success", false);
            jsonObject.put("errorInfo", "非法操作!");
        }
        jsonObject.put("token", getSession().getAttribute("token"));
        ResponseUtil.write(getResponse(), jsonObject);
    }


}
