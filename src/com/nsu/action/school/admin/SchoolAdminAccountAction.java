package com.nsu.action.school.admin;

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
@RequestMapping("/school_admin/")
public class SchoolAdminAccountAction extends BaseAction {
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
//            getRequest().setAttribute("errorInfo", errorInfo);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (getSession().getAttribute(Constants.LOGIN_USER) != null) {
            Map<String, Object> map = (Map<String, Object>) getSession().getAttribute(Constants.LOGIN_USER);
            if (map.get("username") != null && !"".equals(map.get("username").toString().trim()) && map.get("type").toString().equals("3") && map.get("_status").toString().equals("1")) {
                return "redirect:/school_admin/school_admin_index.action";
            }
        }
        setSaltForSession();
        return "/school_admin/school_login";
    }

    @RequestMapping("school_admin_index")
    public String index() {
        return "/school_admin/school_admin_index";
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
                return "redirect:/school_admin/login_view.action";
            }
        } catch (Exception e) {
            errorInfo = "异常";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/school_admin/login_view.action";
        }
        if (VerifyUtil.isNotEmpty(userName) && VerifyUtil.isNotEmpty(userPassword)) {
            Map<String, Object> map = null;
            try {
                map = getServiceManager().getSchoolAdminAccountService().getSchoolAccountByUsername(userName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.error(e.getMessage());
            }
            if (map != null) {
                if (map.get("_status").toString().equals("1")) {
                    if (InfoProtUtil.comparePass((String) map.get("_PASSWORD"), (String) getSession().getAttribute(Constants.SALT_IN_SESSION), userPassword)) {
                        Map<String, Object> schoolInfo = null;
                        try {
                            schoolInfo = getServiceManager().getSchoolAdminAccountService().getSchoolInfo(userName);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            log.error(e.getMessage());
                            schoolInfo = new HashMap<>();
                        }
                        map.put("info", schoolInfo.get("school_name"));
                        map.put("school_url", schoolInfo.get("school_url"));
                        try {
                            getServiceManager().getSchoolAdminAccountService().updateSchoolLogin(userName);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            log.error(e.getMessage());
                        }
                        getSession().setAttribute(Constants.LOGIN_USER, map);
                        saveLog(getRequest(), userName, "3");
                        getRequest().setAttribute("map", map);
                        return "redirect:/school_admin/school_admin_index.action";
                    } else {
                        errorInfo = "密码错误！";
                        getSession().setAttribute("errorInfo", errorInfo);
                    }
                } else {
                    errorInfo = "帐号被停用";
                    getSession().setAttribute("errorInfo", errorInfo);
                }
            } else {
                errorInfo = "用户名不存在或密码错误！";
                getSession().setAttribute("errorInfo", errorInfo);
            }
        } else {
            errorInfo = "用户名不存在或密码错误！";
            getSession().setAttribute("errorInfo", errorInfo);
        }
        return "redirect:/school_admin/login_view.action";
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
        return "redirect:/school_admin/login_view.action";
    }


//    public String getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(String userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    private Map<String, Object> map = new HashMap<String, Object>();
//
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//    public String getRandomCode() {
//        return randomCode;
//    }
//
//    public void setRandomCode(String randomCode) {
//        this.randomCode = randomCode;
//    }
//
//
//    public String getErrorInfo() {
//        return errorInfo;
//    }
//
//    public void setErrorInfo(String errorInfo) {
//        this.errorInfo = errorInfo;
//    }
}
