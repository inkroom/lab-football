package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.referee.LoginService;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import cn.nsu.edu.web.four.utils.encrypt.AESEncryptUtil;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 裁判自动登录拦截器
 */
public class AutoLoginInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService service;
    @Autowired
    private AESEncryptUtil encryptUtil;
    @Autowired
    private RefereeService refereeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取cookie
        //分解value
        String values[] = RequestUtil.getCookeValues(request, BaseStatic.KEY_COOKIE_AUTO_NAME);
        if (values != null && values.length == 4) {//自动登录
            //获取账号
            try {
                String username = encryptUtil.decrypt(values[0]);
                String password = encryptUtil.decrypt(values[1]);

                Map<String, Object> map = service.login(username, password);
                if (map != null) {
                    Integer schId = ParseUtil.parseInt(map.get(BaseStatic.KEY_SCHEDULE_ID));

                    if (schId == null) {
                        return true;
                    }
                    ScheduleInformationDto dto = refereeService.getScheduleInformation(schId);
                    if (dto == null) {
                        return true;
                    }
                    request.getSession().setAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION, dto);
                    RequestUtil.login(request, map, Role.REFEREE);
                } else {
//                            response.co
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return true;
    }
}
