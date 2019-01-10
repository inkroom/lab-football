package cn.nsu.edu.web.four.controllers.referee;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.referee.LoginService;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import cn.nsu.edu.web.four.utils.encrypt.AESEncryptUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.spring.AopTargetUtils;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/referee/")
public class LoginController {

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private AESEncryptUtil encryptUtil;
    @Autowired
    private RefereeService refereeService;
    @Autowired
    private LoginService service;
    @Autowired
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @Security
    public String index() {
        log.info("contextPath="+request.getServletContext().getContextPath());
        String values[] = RequestUtil.getCookeValues(request, BaseStatic.KEY_COOKIE_AUTO_NAME);
        if (values != null) {//记住密码
            try {
                String username = encryptUtil.decrypt(values[0]);
                String password = encryptUtil.decrypt(values[1]);

                request.setAttribute("username", username);
                request.setAttribute("password", password);
                if (values.length >= 3)
                    request.setAttribute("isRemember", ParseUtil.parseBoolean(encryptUtil.decrypt(values[2])));
                if (values.length >= 4)
                    request.setAttribute("isAuto", ParseUtil.parseBoolean(encryptUtil.decrypt(values[3])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "referee/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @Security(checkToken = true)
    @ResponseBody
    public MessageDto login(String username, String password, String isRemember, String isAuto) throws Exception {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(isRemember) || StringUtils.isEmpty(isAuto)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Boolean b_isRemember = ParseUtil.parseBoolean(isRemember);
        Boolean b_isAuto = ParseUtil.parseBoolean(isAuto);
        if (b_isAuto == null || b_isRemember == null)
            return new MessageDto(Result.PARAM_NOT_SUIT);

//        String sessionCode = (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_CODE);
//        if (sessionCode == null)
//            return new MessageDto(Result.CODE_NOT_EXISTS);

//        if (sessionCode.equals(code)) {

        Map<String, Object> map = service.login(username, password);
        if (map != null) {

            Integer schId = ParseUtil.parseInt(map.get(BaseStatic.KEY_SCHEDULE_ID));

            if (schId == null) {
                return new MessageDto(Result.REFEREE_NOE_CORRECT);
            }
            ScheduleInformationDto dto = refereeService.getScheduleInformation(schId);
            if (dto == null) {
                return new MessageDto(Result.REFEREE_NOE_CORRECT);
            }
            request.getSession().setAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION, dto);
            //读写cookie
            if (b_isRemember || b_isAuto) {

                StringBuilder builder = new StringBuilder();

                if (b_isRemember) {
                    builder.append(encryptUtil.encrypt(username));
                    builder.append(BaseStatic.SEPARATOR_COOKIE_VALUE);
                    builder.append(encryptUtil.encrypt(password));
                    builder.append(BaseStatic.SEPARATOR_COOKIE_VALUE);
                    builder.append(encryptUtil.encrypt(isRemember));
                    builder.append(BaseStatic.SEPARATOR_COOKIE_VALUE);
                    if (b_isAuto) {
                        builder.append(encryptUtil.encrypt(isAuto));
                        builder.append(BaseStatic.SEPARATOR_COOKIE_VALUE);
                    }
                }
                if (builder.length() > 2) {
                    builder.deleteCharAt(builder.length() - 1);
                }
                Cookie cookie = new Cookie(BaseStatic.KEY_COOKIE_AUTO_NAME, builder.toString());
                cookie.setMaxAge(24 * 60 * 1000);
                response.addCookie(cookie);
            }
            RequestUtil.login(request, map, Role.REFEREE);
            return new MessageDto(Result.SUCCESS);
        }
        return new MessageDto(Result.FAIL);
//        }
//        return new MessageDto(Result.CODE_NOT_CORRECT);

    }
}
