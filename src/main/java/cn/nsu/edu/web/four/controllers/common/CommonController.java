package cn.nsu.edu.web.four.controllers.common;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.listeners.RedisSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static cn.nsu.edu.web.four.enums.Role.ORGANIZATION;
import static cn.nsu.edu.web.four.enums.Role.SCHOOL;

@Controller
public class CommonController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("404")
    public String notFound() {
        return "common/404";
    }

    @RequestMapping("401")
    public String noAuthority() {
        return "common/401";
    }

    @RequestMapping("500")
    public String error() {
        //获取异常信息
        Throwable throwable = ((Throwable) request.getAttribute("javax.servlet.error.exception"));
        String url = ((String) request.getAttribute("javax.servlet.error.request_uri"));
        if (url != null) {
            log.error("异常信息{}，来自页面{}", throwable, url);
        } else if (throwable != null) {
            log.error("异常信息{}", throwable);
        }
        return "common/500";
    }

    @RequestMapping("message")
    public String message() {
        return "live/end";
    }

    /**
     * 移动端结束比赛url
     *
     * @return 移动页面
     */
    @RequestMapping("end")
    public String end() {
        return "live/endMobile";
    }

    /**
     * 首页跳转
     *
     * @return 登陆后首页页面
     */
    @Security(roles = {SCHOOL, ORGANIZATION})
    @RequestMapping("index")
    public String index() {
        return "common/index";
    }

    @RequestMapping("session")
    @ResponseBody
    public MessageDto count() {
        return new MessageDto(Result.SUCCESS, "当前在线人数=" + RedisSessionListener.count + "人");
    }
}
