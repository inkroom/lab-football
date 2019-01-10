package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.common.SMSService;
import cn.nsu.edu.web.four.services.referee.ConsummateService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 裁判员完善信息
 */
@Controller
@RequestMapping("/referee/")
public class ConsummateController {
    @Autowired
    private SMSService smsService;
    @Autowired
    private ConsummateService service;
    @Autowired
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("consummate")
    @Security(roles = Role.REFEREE, checkToken = true)
    @ResponseBody
    public MessageDto consummate(String name, String phone, String id, String code) {
        log.info("code = " + code);
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(id) || StringUtils.isEmpty(code)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        //验证是否是中文姓名
        if (!name.matches(RegexStatic.CHINESE)) {
            log.debug("中文");
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        //验证是否是身份证号
        if (!id.matches(RegexStatic.ID_CARD)) {
            log.debug("身份证");
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        //验证是否是正确手机号
        if (!phone.matches(RegexStatic.PHONE)) {
            log.debug("手电");
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        //获取电话号码
        String sessionPhone = request.getSession().getAttribute(BaseStatic.KEY_SESSION_PHONE).toString();
        if (sessionPhone == null)
            return new MessageDto(Result.CODE_NOT_EXISTS);
        if (!sessionPhone.equals(phone)) {//新提交的手机号码与接收号码不一致
            return new MessageDto(Result.PHONE_NOT_PATTERN);
        }
        //验证手机验证码
        Result result = smsService.checkCode(code, request);
//
//        Result result = Result.SUCCESS;
        if (result == Result.SUCCESS) {

            Map<String, Object> loginMap = RequestUtil.getLogin(request);
            //获取赛程ID
            Long refId = ParseUtil.parseLong(loginMap.get(BaseStatic.KEY_REFEREE_ID).toString());

            if (service.consummate(name, id, phone, refId) >= 1) {

                loginMap.put(BaseStatic.KEY_MAP_NAME, name);
                loginMap.put(BaseStatic.KEY_MAP_PHONE, phone);
                loginMap.put(BaseStatic.KEY_MAP_ID_CARE, id);

                return new MessageDto(Result.SUCCESS);
            }


            return new MessageDto(Result.FAIL);
        } else {
            return new MessageDto(result);
        }
    }

    @RequestMapping("consummateIndex")
    @Security(roles = Role.REFEREE)
    public String consummate() {
        return "referee/consummate";
    }


}
