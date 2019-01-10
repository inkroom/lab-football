package cn.inkroom.web.money.gate.controllers.common;

import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.services.common.impl.ChangepswService;
import cn.inkroom.web.money.gate.services.common.impl.LoginService;
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
public class ChangePswController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ChangepswService service;
    private Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/changepsw" , method = RequestMethod.GET)
    public String Tochangepsw() throws Exception {
        return "backstage/passEdit";
    }

    @RequestMapping(value = "/changepsw" , method = RequestMethod.POST)
    @ResponseBody
    public MessageDto changepsw(String password, String newPassword, String newPassword2) throws Exception {//newpassword2
        //传入值为空
        if(LoginUtil.isEmpty(password) || LoginUtil.isEmpty(newPassword) || LoginUtil.isEmpty(newPassword2)){
            return new MessageDto(Result.PARAM_NOT_EMPTY);
        }

        //密码匹配
        if(!newPassword.trim().equals(newPassword2) ){
            return new MessageDto(Result.FAIL);
        }

        //通过请求获取用户map信息并匹配数据库
        Map<String, Object> map = RequestUtil.getLogin(request);
        if(map != null){
            //通过用户信息获得用户名字
            String username = (String) map.get(BaseStatic.KEY_MAP_USERNAME);
            Map<String, Object> user = loginService.checkAccount(username, password);
            if(user != null){
                service.changepsw(username,newPassword);
                log.info("修改密码成功,并把用户信息登出！");
                RequestUtil.logout(request);
                return new MessageDto(Result.LOGIN_SUCCESS);
            }else{
                //密码不正确
                log.info("密码不正确");
            }
        }else {
            //该用户没有登录
            log.info("用户没有登陆，session没有信息");
        }
        //存入数据库
        return new MessageDto(Result.FAIL);
    }
}
