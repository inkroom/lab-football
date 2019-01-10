package cn.edu.nsu.lib.controllers.common;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.common.ILoginService;
import cn.edu.nsu.lib.services.common.ModifyPasswdService;
import cn.edu.nsu.lib.utils.RequestUtil;
import cn.edu.nsu.lib.utils.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ChenGang
 * @Title: ModifyPasswdController
 * @Package cn.edu.nsu.lib.dao.common
 * @Description:修改密码
 * @date 2017/9/29 0029 上午 8:53
 **/
@Controller
public class ModifyPasswdController extends BaseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ModifyPasswdService modifyPasswdService;
    @Autowired
    ILoginService loginService;

    //进入修改密码页面，传入角色值跳转
    @RequestMapping(value = "/modifyPasswd/{role}", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER, Authority.Role.LEADER, Authority.Role.TEACHER})
    public String modifyPasswd(@PathVariable("role") String role) throws Exception {
//       EncryptUtil encryptUtil=new EncryptUtil();
//       String[] pas=encryptUtil.parseMd5("12345678");
//      System.out.println(pas[0]+"盐："+pas[1]);
//        modifyPasswdService.updatePasswd("15310320243","87654321");
        switch (role) {
            case "student":
                return "student/student_amend";
        }
        return "common/404";
    }

    //更新密码功能
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER, Authority.Role.LEADER, Authority.Role.TEACHER})
    public AjaxBean updatePasswd(String password, String newPassword, String newPassword2) throws Exception {
        //验证是否为空
        if (V.isEmpty(password) || V.isEmpty(newPassword) || V.isEmpty(newPassword2)) {
            return new AjaxBean(Result.PARAM_NOT_EMPTY);
        }
//        log.info(password+"****"+newPassword+"**********"+newPassword2);
//       log.info(newPassword.equals(newPassword2));
//        log.info(!newPassword.equals(newPassword2));
//        boolean ss=newPassword.equals(newPassword2);

//两次密码不一致
        if (!newPassword.equals(newPassword2)) {
            return new AjaxBean(Result.FILE_TYPE_NOT_SUIT);
        }
        //获取登陆信息
        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        log.info(loginMap);
        if (loginMap != null) {
            String userName = String.valueOf((long) loginMap.get("id"));
            log.info(userName);
            Map<String, Object> map = loginService.checkAccount(userName, password);
            if (map != null) {
                //更新密码
                modifyPasswdService.updatePasswd(userName, newPassword);
                return new AjaxBean(Result.LOGIN_SUCCESS);

            }
            return new AjaxBean(Result.FAIL);
        } else {//登陆失效
            return new AjaxBean(Result.LOGIN_NOT);
        }

    }

}
