package cn.inkroom.web.money.gate.controllers.common;

import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.common.ILoginService;
import cn.inkroom.web.money.gate.utils.LoginUtil;
import cn.inkroom.web.money.gate.utils.http.RequestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ILoginService loginService;


    //跳转到登陆页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() throws Exception {
        return "backstage/login";
    }

    //退出功能
    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public String exitLogin() throws Exception {
        if( request.getSession().getAttribute(BaseStatic.KEY_SESSION_LOGIN) != null){
            RequestUtil.logout(request);
        }
        log.info("退出成功");
        return "redirect:/login.html";
    }

    //登陆页面表单验证post
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageDto login(String username, String password, String code) throws Exception {
        //信息为空
        if(LoginUtil.isEmpty(username) || LoginUtil.isEmpty(password) || LoginUtil.isEmpty(code)){
            return new MessageDto(Result.FAIL);
        }

        String sCode = (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_CODE);
        if (sCode == null) {//验证码过期 11
            return new MessageDto(Result.CODE_NOT_EXISTS);
        }

        //将验证码转换为大写
        if (!sCode.equalsIgnoreCase(code)) {//验证码不正确 12
            return new MessageDto(Result.CODE_NOT_CORRECT);
        }
        //检查帐号密码并且返回数据
        Map<String, Object> user = loginService.checkAccount(username, password);
        if (user != null) {
            //登陆成功,session存储用户信息
            RequestUtil.login(request, user);
            //属性为0会jackson会自动忽略前台json拿不到数据 （200）
            MessageDto ajax = new MessageDto(Result.LOGIN_SUCCESS);
            ajax.put(BaseStatic.KEY_JSON_SESSION_ID, request.getSession().getId());
            log.info("login success");
            return ajax;
        } else {
            //登陆失效或者未登录  1
            return new MessageDto(Result.FAIL);
        }
    }

    @RequestMapping(value = "/loginError" , method = RequestMethod.POST)
    public String loginerror(){
        return "backstage/loginError";
    }
}

