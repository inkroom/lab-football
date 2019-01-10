package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.services.common.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/live/{sch:[1-9]+[0-9]*}")
public class VerificationController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SMSService smsService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/verification")
    @Security
    public String index() {
        return "live/verification";
    }

    @RequestMapping("ver")
    @ResponseBody
    @Security(checkToken = true)
    public MessageDto verification(String phone, String code, @PathVariable int sch) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code) || !phone.matches(RegexStatic.PHONE)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        String sessionPhone = smsService.getPhone(request);
        if (sessionPhone == null) {//手机号或者验证码不存在
            return new MessageDto(Result.CODE_NOT_EXISTS);
        } else if (!sessionPhone.equals(phone)) {//前后手机号码不一致
            return new MessageDto(Result.PHONE_NOT_PATTERN);
        }
        Result result = smsService.checkCode(code, request);
        if (result == Result.SUCCESS) {
            request.getSession().setAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE, phone);
            request.getSession().setAttribute(BaseStatic.KEY_SESSION_VER_SCH_ID, sch);
            return new MessageDto(Result.SUCCESS);
        }
        return new MessageDto(result);

//        log.info("前台传来的phone" + phone);
//        request.getSession().setAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE, phone);
//        log.info("存了" + request.getSession().getAttribute(BaseStatic.KEY_SESSION_LIVE_PHONE).toString());
//        return new MessageDto(Result.SUCCESS);
    }
}
