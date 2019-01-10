package cn.nsu.edu.web.four.interceptors;

import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.referee.RefereeService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.http.ResponseUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 赛程拦截器，仅允许在赛程未结束的时候进行操作
 */
public class ScheduleInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private RefereeService refereeService;

    private String endUrl;
    private String endMobileUrl;

    public void setEndUrl(String endUrl) {
        this.endUrl = endUrl;
    }

    public void setEndMobileUrl(String endMobileUrl) {
        this.endMobileUrl = endMobileUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //获取赛程id
        Integer schId;
        Role role = RequestUtil.getRole(request);
        if (role != null && role == Role.REFEREE) {//裁判员
            log.info(RequestUtil.getLogin(request).toString());
            schId = ParseUtil.parseInt(RequestUtil.getLogin(request).get("schId"));
            log.info("从session获取=" + schId);
        } else {
            schId = getScheduleIdFromUrl(request.getRequestURI());
            log.info("from url = " + schId);
        }
        //获取赛程信息
        ScheduleInformationDto dto = (ScheduleInformationDto) request.getSession().getAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION);
        if (request.getRequestURI().contains("live")) {//直播模块必须每次重新获取数据
            dto = refereeService.getScheduleInformation(schId);
        } else if (dto == null) {//裁判员模块

            dto = refereeService.getScheduleInformation(schId);

        }
        if (dto == null) {
            log.debug("没有获取到赛程信息,404");
//            if (RequestUtil.isAjax(request)){
//
//            }
            response.sendError(404);

            return false;
        }
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_SCHEDULE_INFORMATION, dto);
        //判断赛程状况
        if (dto.getScheduleInformation().getStatus() > 1) {
            log.debug("比赛结束");
            if (request.getRequestURI().contains("live")) {
                return true;
            }
            //比赛结束
            if (RequestUtil.isAjax(request)) {
                ResponseUtil.outJson(response, new MessageDto(Result.SCHEDULE_FINISH).toString());
                return false;
            }
            if (request.getRequestURI().contains("m")) {
                request.getRequestDispatcher(endMobileUrl).forward(request, response);
            } else {
                request.getRequestDispatcher(endUrl).forward(request, response);
            }
            return false;
        }
        return true;
    }

    /**
     * 从url中获取赛程id
     * @param url 路径
     * @return 有返回id，没有返回null
     */
    private Integer getScheduleIdFromUrl(String url) {
        Pattern pattern = Pattern.compile("/live/(" + RegexStatic.NUMBER + ")");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return ParseUtil.parseInt(matcher.group(1));
        }
        return null;
    }
}
