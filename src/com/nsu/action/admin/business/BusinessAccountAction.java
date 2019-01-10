package com.nsu.action.admin.business;

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
@RequestMapping("/admin_business/")
public class BusinessAccountAction extends BaseAction {
//	private String userName;
//	private String userPassword;
//	private String randomCode;
//	private String userInfo;
//	private String errorInfo;


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
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }

        if (getSession().getAttribute(Constants.LOGIN_USER) != null) {
            Map<String, Object> map = (Map<String, Object>) getSession().getAttribute(Constants.LOGIN_USER);
            if (map.get("username") != null && !"".equals(map.get("username").toString().trim()) && map.get("type").toString().equals("2")) {
//				return "alreadyLogin";
                return "redirect:/admin_business/adminB_index.action";
            }
        }
        setSaltForSession();
//		return SUCCESS;
        return "/admin_business/adminB_login";
    }


    /**
     * 登录判断
     *
     * @return
     */
    @RequestMapping("login")
    public String login(String randomCode, String userName, String userPassword) {
        String errorInfo;
        try {
            if (randomCode == null || randomCode.equals("") || !randomCode.equals(getSession().getAttribute(Constants.RANDOM_CODE).toString().trim())) {
                errorInfo = "验证码错误";
                getSession().setAttribute("errorInfo", errorInfo);
                return "redirect:/admin_business/login_view.action";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            errorInfo = "异常";
            getSession().setAttribute("errorInfo", errorInfo);
            return "redirect:/admin_business/login_view.action";
//			return "login";
        }
        Map<String, Object> map = null;
        if (VerifyUtil.isNotEmpty(userName) && VerifyUtil.isNotEmpty(userPassword)) {
            try {
                map = getServiceManager().getBusinessAccountService().getAdminByUsername(userName, "2");
                getRequest().setAttribute("map", map);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.error(e.getMessage());
            }
            if (map != null) {
                if (InfoProtUtil.comparePass((String) map.get("_PASSWORD"), (String) getSession().getAttribute(Constants.SALT_IN_SESSION), userPassword)) {
                    map.put("info", "业务管理员");
                    try {
                        getServiceManager().getBusinessAccountService().updateAdminBLogin(userName);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                    getSession().setAttribute(Constants.LOGIN_USER, map);
                    saveLog(getRequest(), userName, "2");
                    return "redirect:/admin_business/adminB_index.action";
//					return SUCCESS;
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
        return "redirect:/admin_business/login_view.action";
//			return "login";
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
        return "redirect:/admin_business/login_view.action";
    }

}
