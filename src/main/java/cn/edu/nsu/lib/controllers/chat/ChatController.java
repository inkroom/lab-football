package cn.edu.nsu.lib.controllers.chat;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.handlers.Anyone;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 18:22
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

    /**
     * 返回二维码中的内容
     * <p>同时启动定时器，在五分钟后删除验证码</p>
     *
     * @return json数据
     */
    @RequestMapping("code")
    @ResponseBody
    @Authority
    public AjaxBean qrCode()throws Exception {
        AjaxBean ajax = new AjaxBean(Result.SUCCESS);

        ServletContext context = request.getServletContext();
        String message = null;
        do {
            message = EncryptUtil.getRandomString(10);
            message = EncryptUtil.xorInfo(message);
        } while ((message).equals(context.getAttribute(Constants.KEY_CONTEXT_CODE)));

        ajax.put(Constants.KEY_JSON_CODE, (message));

        context.setAttribute(Constants.KEY_CONTEXT_CODE, (message));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 5);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                context.removeAttribute(Constants.KEY_CONTEXT_CODE);
                log.info("context域已清空");
            }
        }, c.getTime());
        return ajax;
    }

    @RequestMapping("ok")
    @ResponseBody
    @Authority
    public AjaxBean ok(String code) throws Exception {
        String cCode = (String) request.getServletContext().getAttribute(Constants.KEY_CONTEXT_CODE);
        if (cCode == null) {
            return new AjaxBean(Result.CODE_NOT_EXISTS);
        } else if (!cCode.equals(code)) {
            return new AjaxBean(Result.CODE_NOT_CORRECT);
        }
        // TODO: 2017/9/26 插入考勤记录
        if (signService.insertStudent(getLogin(request).get(Constants.KEY_MAP_USERNAME).toString()) == 1) {
            return new AjaxBean(Result.SUCCESS);
        }
        return new AjaxBean(Result.FAIL);
    }


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
}
