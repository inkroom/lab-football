package com.nsu.action.admin.school;

import java.util.HashMap;
import java.util.Map;

import com.nsu.action.BaseAction;
import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.util.base.VerifyUtil;
import com.nsu.util.core.InfoProtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_school/")
public class AdminSchoolAccountAction extends BaseAction  {
//    private String userName;
//    private String userPassword;
//    private String randomCode;
//    private String userInfo;
//    private String errorInfo;


    /**
     * 登录页面
     *
     * @return
     */

    @RequestMapping("login_view")
    public String loginView() {

        try {
            String errorInfo = getSession().getAttribute("errorInfo").toString();
            getSession().removeAttribute("errorInfo");
            getRequest().setAttribute("errorInfo", errorInfo);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (getSession().getAttribute(Constants.LOGIN_USER) != null) {
            Map<String, Object> map = (Map<String, Object>) getSession().getAttribute(Constants.LOGIN_USER);
            if (map.get("username") != null && !"".equals(map.get("username").toString().trim()) && map.get("type").toString().equals("1") && map.get("_status").toString().equals("1")) {
                return "redirect:/admin_school/admin_school_index.action";
//                return "alreadyLogin";
            }
        }
        setSaltForSession();
        return "/admin_school/school_login";
//        return SUCCESS;
    }

    @RequestMapping("admin_school_index")
    public String toIndex() {
        return "/admin_school/admin_school_index";
    }

    /**
     * 登录判断
     *
     * @return
     */
    @RequestMapping("login")
    public String login(String randomCode, String userName, String userPassword) {
        String errorInfo = null;
        try {
            if (randomCode == null || randomCode.equals("") || !randomCode.equals(getSession().getAttribute(Constants.RANDOM_CODE).toString().trim())) {
                errorInfo = "验证码错误";
                getSession().setAttribute("errorInfo", errorInfo);
                return "redirect:/admin_school/login_view.action";
//                return "login";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            errorInfo = "异常";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_school/login_view.action";
//                return "login";
        }
        if (VerifyUtil.isNotEmpty(userName) && VerifyUtil.isNotEmpty(userPassword)) {
            Map<String, Object> map = null;
            try {
                map = getServiceManager().getAdminSchoolAccountService().getShoolById(userName, "1");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.error(e.getMessage());
            }
            if (map != null) {
                if (InfoProtUtil.comparePass((String) map.get("_PASSWORD"), (String) getSession().getAttribute(Constants.SALT_IN_SESSION), userPassword)) {
                    map.put("info", "学校管理员");
                    try {
                        getServiceManager().getAdminSchoolAccountService().updateSchoolLogin(userName);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                    getSession().setAttribute(Constants.LOGIN_USER, map);
                    saveLog(getRequest(), userName, "1");
                    return "redirect:/admin_school/admin_school_index.action";
//                    return SUCCESS;
                } else {
                    errorInfo = "密码错误！";
                    getSession().setAttribute("errorInfo", errorInfo);
                }
            }
            errorInfo = "用户名不存在或密码错误！";
            getSession().setAttribute("errorInfo", errorInfo);

        } else {
            errorInfo = "用户名不存在或密码错误！";
            getSession().setAttribute("errorInfo", errorInfo);
        }
        return "redirect:/admin_school/login_view.action";
//                return "login";
    }


    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("common_logout")
    public String logout() {
        String errorInfo = "账号已安全退出!";
        getSession().invalidate();
        getSession().setAttribute("errorInfo", errorInfo);
        return "redirect:/admin_school/login_view.action";
//        return SUCCESS;
    }

}
