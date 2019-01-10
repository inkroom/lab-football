package com.nsu.controller.admin;

import com.nsu.bean.index.AjaxBean;
import com.nsu.bean.index.UserBean;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.admin.IAdminService;
import com.nsu.util.InfoProtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.HashMap;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: AdminLogin
 * @Package com.nsu.controller.admin
 * @Description:
 * @date 5/5/17
 */

@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

    private static String SYSTEM_ERROR="登录失败，稍后请请重试!";
    private static String LOGIN_ERROR_COMMON = "登录失败，账号或密码错误!";
    private static String LOGIN_ERROR_CODE = "登录失败，验证码错误!";
    private static String LOGIN_SUCCESS = "登录成功!";

    //bean 注入 adminService
    @Resource(name="adminService")
    private IAdminService adminService;

    /**
     * 登录 view
     * @param session
     * @return
     */
    @RequestMapping(value = "/login_view",method = RequestMethod.GET)
    public String loginView(HttpSession session){
        setSaltForSession(session);
        try {
            Map<String, Object> map = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
            String temp = map.get("A_TYPE").toString();
            if (temp.equals("0")){
                return "redirect:index.html";
            }else{
                return "/admin/admin_login";
            }

        }catch (Exception e){

        }
        return "/admin/admin_login";
    }

    /**
     * 登录校验
     * @param userName
     * @param password
     * @param code
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login_view",method = RequestMethod.POST)
    public AjaxBean login(@RequestParam(value = "userName",required = true) String userName,@RequestParam(value = "password",required = true) String password,@RequestParam(value = "code",required = true) String code,HttpSession session){
        AjaxBean ajaxBean = new AjaxBean();
        try {

            //教研验证码是否为空
            if (code == null || code.equals("")){
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg(LOGIN_ERROR_CODE);
                ajaxBean.setStatus("200");
            }else {
                // 校验username 是否为空
                if (userName == null || userName.equals("")){
                    ajaxBean.setSuccess(false);
                    ajaxBean.setMsg(LOGIN_ERROR_COMMON);
                    ajaxBean.setStatus("200");
                }else {
                    //校验password 是否为空
                   if (password == null || password.equals("")){
                       ajaxBean.setSuccess(false);
                       ajaxBean.setMsg(LOGIN_ERROR_COMMON);
                       ajaxBean.setStatus("200");
                   }else {

                       if (code.equals(session.getAttribute(Constants.RANDOM_CODE))){
                           //获取数据中的user
                           UserBean userBean = adminService.getUserByUsername(InfoProtUtil.xorInfo(userName));
                           if (userBean != null){
                               if (InfoProtUtil.comparePass(userBean.getPassword(),session.getAttribute(Constants.SALT_IN_SESSION).toString(),password)){
                                   ajaxBean.setSuccess(true);
                                   ajaxBean.setMsg(LOGIN_SUCCESS);
                                   ajaxBean.setStatus("200");
                                   Map<String,Object> userMap = new HashMap<String,Object>();
                                   userMap.put("A_TYPE", userBean.getType());
                                   userMap.put("USERNAME",userBean.getUserName());
                                   userMap.put("A_ID",userBean.getId());
                                   session.setAttribute(Constants.LOGIN_USER,userMap);
                               }else {
                                   ajaxBean.setSuccess(false);
                                   ajaxBean.setMsg(LOGIN_ERROR_COMMON);
                                   ajaxBean.setStatus("200");
                               }
                           }else {
                               ajaxBean.setSuccess(false);
                               ajaxBean.setMsg(LOGIN_ERROR_COMMON);
                               ajaxBean.setStatus("200");
                           }
                       }else {
                           ajaxBean.setSuccess(false);
                           ajaxBean.setMsg(LOGIN_ERROR_CODE);
                           ajaxBean.setStatus("200");
                       }


                   }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxBean.setStatus("400");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg(SYSTEM_ERROR);
        }
        return ajaxBean;
    }

    /**
     * 后台管理主页面
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String indexView(){
        return "/admin/admin_index";
    }


    /**
     * 后台管理主页面
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public AjaxBean logout(HttpSession session){
        session.invalidate();
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setStatus("200");
        ajaxBean.setMsg("账号已登出!");
        return ajaxBean;
    }

}
