package com.nsu.action.school.admin;

import java.io.IOException;

import com.nsu.action.BaseAction;
import com.nsu.util.base.ResponseUtil;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/school_admin/")
public class SchoolUpdatePasswordAction extends BaseAction {

    @RequestMapping("modify_password_view")
    public String updatePasswordView() {
        return "/school_admin/modify_password";
    }

    @RequestMapping("modify_password")
    @ResponseBody
    public void updatePassword(String oldPassword, String newPassword, String confirmPassword) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getLoginUser().get("_PASSWORD").toString().equals(oldPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    String username = getLoginUser().get("username").toString();
                    getServiceManager().getSchoolAdminAccountService().updatePassword(username, newPassword);
                    jsonObject.put("status", "200");
                    jsonObject.put("success", true);
                    jsonObject.put("msg", "修改成功!");
                    getSession().invalidate();
                } else {
                    jsonObject.put("status", "200");
                    jsonObject.put("success", false);
                    jsonObject.put("msg", "两次密码不一致!");
                }
            } else {
                jsonObject.put("status", "200");
                jsonObject.put("success", false);
                jsonObject.put("msg", "原密码错误!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonObject.put("status", "200");
            jsonObject.put("success", false);
            jsonObject.put("msg", "系统异常");
            log.error(e.getMessage());
        }
        try {
            ResponseUtil.write(getResponse(), jsonObject);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
