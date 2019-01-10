package cn.edu.nsu.lib.controllers.chat;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.chat.ISignService;
import cn.edu.nsu.lib.services.common.ILoginService;
import cn.edu.nsu.lib.utils.EncryptUtil;
import cn.edu.nsu.lib.utils.RequestUtil;
import cn.edu.nsu.lib.utils.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * The type Chat controller.
 *
 * @author 墨盒
 * @version 1.0
 * @Date 2017 /9/26
 * @Time 18 :22
 * @Descorption
 */
@Controller
@RequestMapping("/chat/")
public class ChatController extends BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISignService signService;
    @Autowired
    private ILoginService loginService;

    @RequestMapping("sign")
    @Authority
    public String chat() {
        return "student/sign";
    }

    /**
     * 返回二维码中的内容
     * <p>同时启动定时器，在五分钟后删除验证码</p>
     *
     * @return json数据 ajax bean
     */
    @RequestMapping("code")
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public AjaxBean qrCode() {
        AjaxBean ajax = new AjaxBean(Result.SUCCESS);
        final ServletContext context = request.getServletContext();
        @SuppressWarnings("checked")
        Map<String, String> code = (Map<String, String>) context.getAttribute(Constants.KEY_CONTEXT_CODE);
        Map<String, Long> times = (Map<String, Long>) context.getAttribute(Constants.KEY_CONTEXT_TIMES);
        final String username = getLogin(request).get(Constants.KEY_MAP_USERNAME).toString();
        log.info("输出");

        String message = null;

        do {//生成不重复的二维码
            message = EncryptUtil.getRandomString(10);
            message = EncryptUtil.xorInfo(message);
        } while ((message).equals(code.get(username)));

        log.info("生成的随机验证码==" + message);
        ajax.put(Constants.KEY_JSON_CODE, (message));


//        context.setAttribute(Constants.KEY_CONTEXT_CODE, (message));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 5);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                @SuppressWarnings("checked")
                Map<String, String> code = (Map<String, String>) context.getAttribute(Constants.KEY_CONTEXT_CODE);
                Map<String, Long> times = (Map<String, Long>) context.getAttribute(Constants.KEY_CONTEXT_TIMES);
                if (new Date().getTime() >= times.get(username)) {
                    code.remove(username);
//                context.removeAttribute(Constants.KEY_CONTEXT_CODE);
                    log.info("context域已清空");
                }
            }
        }, c.getTime());

        code.put(username, message);
        times.put(username, c.getTimeInMillis());

        log.info("验证码 = " + context.getAttribute(Constants.KEY_CONTEXT_CODE));

        return ajax;
    }

    /**
     * Ok ajax bean.
     *
     * @param code the code
     * @return the ajax bean
     * @throws Exception the exception
     */
    @RequestMapping("ok")
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public AjaxBean ok(String code) throws Exception {
        log.info("开始了  " + code);
        log.info(request.getCookies().length);
        for (Cookie c : request.getCookies()) {
            log.info("cookie name = " + c.getName() + "   value = " + c.getValue());
        }
        @SuppressWarnings("unchecked")
        Map<String, String> codes = (Map<String, String>) request.getServletContext().getAttribute(Constants.KEY_CONTEXT_CODE);
        log.info("session id = " + request.getSession().getId() + "   login = " + getLogin(request));
        String username = (getLogin(request).get(Constants.KEY_MAP_USERNAME).toString());
        String cCode = codes.get(getLogin(request).get(Constants.KEY_MAP_USERNAME).toString());
        log.info(" 账号 = " + getLogin(request).get(Constants.KEY_MAP_USERNAME) + "  cCode " + cCode);
        if (cCode == null) {
            return new AjaxBean(Result.CODE_NOT_EXISTS);
        } else if (!cCode.equals(code)) {
            return new AjaxBean(Result.CODE_NOT_CORRECT);
        }
        // TODO: 2017/9/26 插入考勤记录
        if (signService.insertStudent(getLogin(request)) == 1) {
            //清空验证码
            codes.remove(username);
            return new AjaxBean(Result.SUCCESS);
        }
        return new AjaxBean(Result.FAIL);
    }


    /**
     * Login ajax bean.
     *
     * @param username the username
     * @param password the password
     * @return the ajax bean
     * @throws Exception the exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxBean login(String username, String password) throws Exception {
        log.info(username + "   " + password);
        if (V.isEmpty(username) || V.isEmpty(password)) {
            return new AjaxBean(Result.PARAM_NOT_EMPTY);
        }
        //验证账号密码
        Map<String, Object> map = loginService.checkAccount(username, password);
        log.info("map = " + map);
        if (map != null) {//登陆成功
            log.info("class = " + map.get(Constants.KEY_MAP_AUTHORITY).getClass() + "    " + map.get(Constants.KEY_MAP_AUTHORITY));
            Map<String, Object> user = loginService.getUser(username, Integer.parseInt(map.get(Constants.KEY_MAP_AUTHORITY).toString()));
            AjaxBean ajax = new AjaxBean(Result.SUCCESS);
            ajax.put(Constants.KEY_JSON_SESSION_ID, request.getSession().getId());
            RequestUtil.login(request, user);
            return ajax;
        } else {//账号或密码错误
            return new AjaxBean(Result.FAIL);
        }
    }
    @RequestMapping("check")
    @ResponseBody
    public AjaxBean check(){
        if (getLogin(request)==null)
            return new AjaxBean(Result.LOGIN_NOT);
        else
            return new AjaxBean(Result.SUCCESS);
    }


    @RequestMapping("logout")
    @ResponseBody
    public AjaxBean logout() {
        RequestUtil.logout(request);
        request.getSession().invalidate();
        return new AjaxBean(Result.SUCCESS);
    }
}
