package com.nsu.interceptor;

import com.nsu.bean.common.JsonCommonBean;
import com.nsu.common.Anonymous;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.BaseController;
import com.nsu.service.core.IMobileTokenService;
import com.nsu.util.DateUtils;
import com.nsu.util.RSAencrypt.MobileToken;
import com.nsu.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.interceptor
 * @Description:
 * @date 17/4/20
 */
public class MobileInterceptor implements HandlerInterceptor {
    protected static final Log log = LogFactory.getLog(BaseController.class);

    /**
     * 移动端 token时间
     */
    private static final int TIME= 30;

    @Resource
    private IMobileTokenService mobileTokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        final String LOGIN_LOSE = "101";

        String url = httpServletRequest.getServletPath();
        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) handler;
            if (handlerMethod != null && handlerMethod.getBean() instanceof Anonymous ){
                return true;
            }
        }catch (Exception e){

        }
        if (url.contains("login") || url.contains("logout")) {
            return true;
        }else {
            String token = null;
            try {
                token = httpServletRequest.getParameter("token").toString();
            }catch (Exception e){
                log.info("   token 不存在   ");
                JsonCommonBean jsonCommonBean = new JsonCommonBean();
                jsonCommonBean.setStatus(JsonCommonBean.statusError);
                jsonCommonBean.setMsg("系统异常，请重试！");
                ResponseUtil.write(httpServletResponse, JsonMapper.getInstance().toJson(jsonCommonBean));
                return false;
            }

            try {
                String[] tokenArray= MobileToken.analysisToken(token);
                log.info(tokenArray[0]+"-"+tokenArray[1]+"-"+tokenArray[2]+"-"+tokenArray[3]+"-"+tokenArray[4]);
                if (mobileTokenService.getMobileToken(tokenArray[0],tokenArray[1],tokenArray[2],tokenArray[3]) == null){
                    log.info("    mobileTokenService.getMobileToken(tokenArray[0],tokenArray[2],tokenArray[1])      false        ");
                    log.info("token 错误");
                    JsonCommonBean jsonCommonBean = new JsonCommonBean();
                    jsonCommonBean.setStatus(LOGIN_LOSE);
                    jsonCommonBean.setMsg("登录失效，请重新登录！");
                    ResponseUtil.write(httpServletResponse, JsonMapper.getInstance().toJson(jsonCommonBean));
                    return false;
                }else if (mobileTokenService.getMobileToken(tokenArray[0],tokenArray[1],tokenArray[2],tokenArray[3]).equals(tokenArray[4])){
                    log.info("    mobileTokenService.getMobileToken(tokenArray[0],tokenArray[2],tokenArray[1])      true        ");
                    Date before = DateUtils.stringToDate(tokenArray[5]);
                    if (DateUtils.isIn(before,DateUtils.addTime(before,TIME*60*1000),new Date())){
                        log.info("    DateUtils.isIn(before,DateUtils.addTime(before,TIME*60*1000),new Date())     true    ");
                        log.info("token 验证通过");
                        return true;
                    }else {
                        log.info("    DateUtils.isIn(before,DateUtils.addTime(before,TIME*60*1000),new Date())     false    ");
                        log.info("登录时间过期");
                        JsonCommonBean jsonCommonBean = new JsonCommonBean();
                        jsonCommonBean.setStatus(LOGIN_LOSE);
                        jsonCommonBean.setMsg("登录时间过期");
                        ResponseUtil.write(httpServletResponse, JsonMapper.getInstance().toJson(jsonCommonBean));
                        return false;
                    }
                } else {
                    log.info("    mobileTokenService.getMobileToken(tokenArray[0],tokenArray[2],tokenArray[1])      false        ");
                    log.info("登录失效");
                    JsonCommonBean jsonCommonBean = new JsonCommonBean();
                    jsonCommonBean.setStatus(LOGIN_LOSE);
                    jsonCommonBean.setMsg("登录失效，请重新登录");
                    ResponseUtil.write(httpServletResponse, JsonMapper.getInstance().toJson(jsonCommonBean));
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
                log.info("   兄弟异常了     false    ");
                JsonCommonBean jsonCommonBean = new JsonCommonBean();
                jsonCommonBean.setStatus(JsonCommonBean.statusError);
                jsonCommonBean.setMsg("系统异常，请重试！");
                ResponseUtil.write(httpServletResponse, JsonMapper.getInstance().toJson(jsonCommonBean));
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
