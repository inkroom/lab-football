package com.nsu.controller.site;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.site.SiteService;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.VerifyUtil;

/**
 * 现场管理员登录
 *
 * @author 刘俊
 * @date 2017年4月10日 下午14:19
 */

@Controller
@RequestMapping("site")
public class SiteLogin extends BaseController {

    @Autowired
    SiteService siteService;
    private String error;
    private String reURL = SitePages.REDIRECT_SITE_LOGIN;
    private String suRUL = SitePages.REDIRECT_SITE_HOME;
    private Map<String, Object> map;

    /**
     * 进入登录页面
     *
     * @param session
     * @return
     */
    @RequestMapping("login_view")
    public String login(HttpSession session) {
        // 判断session中是否存在盐
        if (session.getAttribute(Constants.SALT_IN_SESSION) == null) {
            setSaltForSession(session);
        }
        return SitePages.SITE_LOGIN;
    }

    /**
     * 登录验证
     *
     * @param user_name
     * @param user_password
     * @param identifying_code
     * @param session
     * @param redirectAttributes
     * @return String
     */
    @RequestMapping(value = "/login_checking")
    public String loginChecking(String user_name, String user_password, String identifying_code, HttpSession session,
                                RedirectAttributes redirectAttributes, Model model) {
        map = new HashMap<String, Object>();
        user_name = user_name.trim();
        user_password = user_password.trim();
        if (!userCode(identifying_code, session, redirectAttributes)) {
            redirectAttributes.addFlashAttribute("error", "验证码错误或者过期");
            return reURL;
        } else if (!VerifyUtil.isNotEmpty(user_name) || !VerifyUtil.isNotEmpty(user_password)) {
            error = "账号密码不能为空";
            redirectAttributes.addFlashAttribute("error", error);
            return reURL;
        } else if (!pwdJudge(user_name, user_password, redirectAttributes, session)) {
            redirectAttributes.addFlashAttribute("error", "账号或密码出错");
            return reURL;
        } else {
            boolean isStatus = false;
            try {
                isStatus = siteService.selectStatus(InfoProtUtil.xorInfo(user_name));
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            if (isStatus) {
                //将数据放入session
                map.put("A_USERNAME", InfoProtUtil.xorInfo(user_name));
                map.put("A_PASSWORD", user_password);
                try {
                    map.put("A_ID", siteService.selectA_ID(InfoProtUtil.xorInfo(user_name)));
                    String tempId = siteService.selectA_ID(InfoProtUtil.xorInfo(user_name));
                    map.put("SE_ID", siteService.selectSE_ID(Integer.parseInt(tempId)));
                    map.put("couldRead", 0);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
                map.put("A_TYPE", 5);
                session.setAttribute(Constants.LOGIN_USER, map);
                return suRUL;
            } else {
                redirectAttributes.addFlashAttribute("error", "当前账号无法使用");
                return reURL;
            }
        }

    }
    /**
     * 验证验证码是否输入正确
     *
     * @param identifying_code
     * @param session
     * @param redirectAttributes
     * @return boolean
     */
    public boolean userCode(String identifying_code, HttpSession session, RedirectAttributes redirectAttributes) {
        if (identifying_code != null && !identifying_code.equals("")
                && identifying_code.equals(session.getAttribute(Constants.RANDOM_CODE).toString())) {
            return true;
        } else {
            error = "验证码输入有误";
            redirectAttributes.addFlashAttribute("error", error);
            return false;
        }
//        return true;
    }

    /**
     * 跳转管理员主页
     *
     * @param session
     * @return String
     */
    @RequestMapping("site_home")
    private String loginSite(HttpSession session, Model model, HttpServletRequest request) {
        String path = null;
        int couldRead = Integer.parseInt(SitePages.getAccountInfoInSession(session, "couldRead"));
        session.removeAttribute("error");
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
        log.info("ip = " + request.getRemoteAddr() + " map = " + map);
        if (map != null) {
            if (map.get("A_ID") == null) {
                model.addAttribute("error", "登陆失效,请重新登陆");
                path = SitePages.REDIRECT_SITE_LOGIN;
            } else {
                int A_ID = Integer.parseInt(map.get("A_ID"));
                String info = null;
                try {
                    info = siteService.PreInfo(A_ID);
                    model.addAttribute("isPre", info);
                    model.addAttribute("userName", siteService.selectuserName(A_ID));
                    model.addAttribute("readTime", couldRead);
                    path = SitePages.SITE_MANAGER;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }

            }
        } else {
            path = SitePages.REDIRECT_SITE_LOGIN;
        }
        return path;
    }

    /**
     * 登出
     *
     * @param redirectAttributes
     * @param session
     * @return String
     */
    @RequestMapping("loginOut")
    private String loginOut(RedirectAttributes redirectAttributes, HttpSession session) {
        error = "账号已经安全退出！";
        session.removeAttribute(Constants.LOGIN_USER);
        redirectAttributes.addFlashAttribute("error", error);
        return SitePages.REDIRECT_SITE_LOGIN;
    }

    /**
     * 密码及加密校验
     *
     * @param username
     * @param p
     * @param redirectAttributes
     * @param session
     * @return
     */
    public boolean pwdJudge(String username, String p, RedirectAttributes redirectAttributes, HttpSession session) {
        boolean iPwd = false;
        try {
            if (siteService.selectPassword(InfoProtUtil.xorInfo(username)).equals("error")) {
                iPwd = false;
            } else if (InfoProtUtil.comparePass(siteService.selectPassword(InfoProtUtil.xorInfo(username)),
                    session.getAttribute(Constants.SALT_IN_SESSION).toString()
                    , p)) {
                iPwd = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return iPwd;
    }

}
