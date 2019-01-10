/**
 * @Title: ForgetPasswordController.java
 * @Package com.nsu.controller.common
 * @Description: 忘记密码Controller
 * @author 朱明民
 * @date 2017年4月13日 下午4:25:51
 * @version V1.0
 */
package com.nsu.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.staticvar.CommonVar;
import com.nsu.util.InfoProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.common.annotation.RestfulUrlAnnotation;
import com.nsu.controller.BaseController;
import com.nsu.service.common.IForgetPasswordService;
import com.nsu.service.common.IUpdateEmailService;
import com.nsu.service.common.IUpdatePhoneService;
import com.nsu.util.ResponseUtil;
import com.nsu.util.jedis.JedisClient;

import net.sf.json.JSONObject;

/**
 * @ClassName: ForgetPasswordController
 * @Description: 忘记密码Controller
 * @date 2017年4月13日 下午4:25:51
 * @author 朱明民
 *
 */
@Controller
@RequestMapping(value = "/forgetPassword")
public class ForgetPasswordController extends BaseController implements Anonymous {

    @Resource(name = "ForgetPassword")
    private IForgetPasswordService iForgetPasswordService;

    @Resource(name = "UpdatePhone")
    private IUpdatePhoneService iUpdatePhoneService;

    @Resource(name = "UpdateEmail")
    private IUpdateEmailService iUpdateEmailService;

    private String error;

    @Autowired
    private JedisClient jedisClient;

    /**
     * 进入忘记密码页面1-输入账号以及验证码
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/firstPassword/{TYPE}")
    @RestfulUrlAnnotation(refulUrl = "/firstPassword/*.html")
    public String firstPassword(@PathVariable("TYPE") String TYPE, HttpSession session, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", error);
        session.removeAttribute("A_EMAIL");
        session.removeAttribute("A_PHONE");
        session.removeAttribute("Forgetstatus");
        session.setAttribute("A_TYPE", TYPE);
        return "/common/find_password1";

    }

    /**
     * 进入忘记密码页面2-选择邮箱验证或者手机验证
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/secondPassword")
    public String secondPassword(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("A_USERNAME") == null || session.getAttribute("A_USERNAME") == "") {
            error = "请先输入账号";
            String type = "1";
            if (session.getAttribute("A_TYPE") != null) {
                type = session.getAttribute("A_TYPE").toString();
            }
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/forgetPassword/firstPassword/" + type + ".html";
        }
        return "/common/find_password2";
    }

    /**
     * 进入忘记密码页面2.1-手机验证
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/secondPassword_message")
    public String secondPasswordMessage(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("A_USERNAME") == null || session.getAttribute("A_USERNAME") == "") {
            error = "请先输入账号";
            String type = "1";
            if (session.getAttribute("A_TYPE") != null) {
                type = session.getAttribute("A_TYPE").toString();
            }
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/forgetPassword/firstPassword/" + type + ".html";
        }
        return "/common/find_password2-message";
    }

    /**
     * 进入忘记密码页面2.2-邮箱验证
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/secondPassword_email")
    public String secondPasswordEmail(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("A_USERNAME") == null || session.getAttribute("A_USERNAME") == "") {
            error = "请先输入账号";
            String type = "1";
            if (session.getAttribute("A_TYPE") != null) {
                type = session.getAttribute("A_TYPE").toString();
            }
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/forgetPassword/firstPassword/" + type + ".html";
        }
        return "/common/find_password2-email";
    }

    /**
     * 进入忘记密码页面3-填写新密码
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/thirdPassword")
    public String thirdPasswordEmail(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("A_USERNAME") == null || session.getAttribute("A_USERNAME") == "" || session.getAttribute("Forgetstatus") == null || !session.getAttribute("Forgetstatus").toString().equals("2")) {
            error = "请先输入账号";
            String type = "1";
            if (session.getAttribute("A_TYPE") != null) {
                type = session.getAttribute("A_TYPE").toString();
            }
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/forgetPassword/firstPassword/" + type + ".html";
        }
        session.removeAttribute("Forgetstatus");
        return "/common/find_password3";
    }

    /**
     * 忘记密码页面1验证
     *
     * @param code
     * @param username
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/firstPassword_check")
    public String update(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        JSONObject info = new JSONObject();
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        try {
            // 获取账号是否存在
            int count = iForgetPasswordService.getUsernameById((username.trim()), type);
            if (count == 0) {
                info.put("error", "账号错误!");
                ResponseUtil.write(response, info);
                // 验证账号验证码
            } else if (code != null && !code.equals("") && code.equals(session.getAttribute(Constants.RANDOM_CODE).toString())) {
                //获取账号信息
                Map<String, Object> map1;
                map1 = iForgetPasswordService.getUsernameInfoById((username.trim()), type);
                String email = null;
                String phone = null;
                //判断手机邮箱是否为空
                if (map1.get("A_EMAIL") == null || map1.get("A_EMAIL") == "") {
                } else {
                    email = map1.get("A_EMAIL").toString();
                    session.setAttribute("A_EMAIL", email.replaceAll(email.substring(2, email.lastIndexOf("@")), "*****"));
                }
                if (map1.get("A_PHONE") == null || map1.get("A_PHONE") == "") {
                } else {
                    phone = InfoProUtil.xorInfo(map1.get("A_PHONE").toString());
                    session.setAttribute("A_PHONE", InfoProUtil.xorInfo(map1.get("A_PHONE").toString()).replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }

                if (map1 != null) {
                    session.setAttribute("A_ID", map1.get("A_ID").toString());
                    session.setAttribute("phone", phone);
                    session.setAttribute("email", email);
                    session.setAttribute("A_TYPE", map1.get("A_TYPE"));
                    session.setAttribute("A_USERNAME", map1.get("A_USERNAME"));
                }
                ResponseUtil.write(response, info);
            } else {
                info.put("error", "验证码错误!");
                ResponseUtil.write(response, info);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "/common/find_password4";
        }
        return null;
    }

    /**
     * 忘记密码页面3-填写新密码验证
     *
     * @param new_password
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/thirdPassword_check")
    public String update(String new_password, HttpSession session, HttpServletResponse response) {
        log.info("-----------------前台获取数据------------------" + new_password);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PASSWORD", new_password);
        map.put("USERNAME", session.getAttribute("A_USERNAME"));
        map.put("A_TYPE", session.getAttribute("A_TYPE"));
        try {
            iForgetPasswordService.updateUsernamePassword(map);
            return "/common/find_password5";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "/common/find_password4";
        }
    }


    /**
     *
     * @param id
     * @param type
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/forgetPhoneCode", method = RequestMethod.POST)
    @ResponseBody
    public String getPhoneCode(@RequestParam(value = "randomCode") String randomCode, @RequestParam(value = "type") String type, HttpSession session, HttpServletResponse response) {
        JSONObject info = new JSONObject();
        try {
            Long A_ID = Long.parseLong(session.getAttribute(CommonVar.Account.ID).toString());
            String phone = InfoProUtil.xorInfo(iUpdatePhoneService.getPhoneById(A_ID));
            if (jedisClient.get(type + "web" + phone + "num") != null) {
                if (Integer.parseInt(jedisClient.get(type + "web" + phone + "num")) > 5) {
                    info.put("success", 1);
                    log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + phone + "num"));
                    jedisClient.del(type + "web" + phone);
                    jedisClient.del(type + "web" + phone + "num");
                    return info.toString();
                }
            }
            if (randomCode.equals(jedisClient.get(type + "web" + phone))) {
                info.put("success", 200);
                session.setAttribute("Forgetstatus", 2);
            } else {
                info.put("success", false);
                info.put("success", 1);
                if (jedisClient.get(type + "web" + phone + "num") != null) {
                    jedisClient.incr(type + "web" + phone + "num");
                }
            }
            return info.toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return info.toString();
    }


    /**
     * 验证旧邮箱验证码
     */
    @RequestMapping(value = "/forgetEmailCode", method = RequestMethod.POST)
    @ResponseBody
    public String getEmailCode(@RequestParam(value = "oldEmailCode") String oldEmailCode, HttpSession session, HttpServletResponse response) {
        JSONObject info = new JSONObject();
        try {
            int A_ID = Integer.parseInt(session.getAttribute("A_ID").toString());
            String old_email_sql = iUpdateEmailService.getEmailById(A_ID);
            if (jedisClient.get(old_email_sql + "num") != null) {
                if (Integer.parseInt(jedisClient.get(old_email_sql + "num")) > 5) {
                    info.put("success", 1);
                    jedisClient.del(old_email_sql + "num");
                    jedisClient.del(old_email_sql);
                }
            }
            if (oldEmailCode.equals(jedisClient.get(old_email_sql))) {
                info.put("success", 200);
                session.setAttribute("Forgetstatus", 2);
            } else {
                info.put("success", 1);
                if (jedisClient.get(old_email_sql + "num") != null) {
                    jedisClient.incr(old_email_sql + "num");
                }
            }
            return info.toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return info.toString();
    }
}
