package cn.edu.nsu.lib.interceptors;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.handlers.Anyone;
import cn.edu.nsu.lib.handlers.MessageException;
import cn.edu.nsu.lib.utils.RequestUtil;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/2
 * @Time 21:19
 * @Descorption 权限拦截器
 */
public class AuthorityInterceptor extends BaseInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("url = " + request.getRequestURL());
        if (o instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) o;
            if (method.getBean() != null && method.getBean() instanceof Anyone) {
                log.info("url = " + request.getRequestURL() + "    接口，");
                return true;
            }

            Authority a = method.getMethodAnnotation(Authority.class);
            if (a != null) {//需要权限拦截
                boolean isAjax = RequestUtil.isAjax(request);
                if (RequestUtil.getLogin(request) != null) {//已登录
                    //记录的sessionId与本机不同，说明已在异地二次登录
                    if (!RequestUtil.isExists(request)) {
                        log.debug("   二次登录了");
//                        if (isAjax) {
//                            throw new MessageException(Result.LOGIN_EXISTS);
//                        }
//                        response.sendRedirect(request.getContextPath() + "/" + Constants.URL_LOGIN);
////                        request.getRequestDispatcher(Constants.URL_LOGIN).forward(request, response);
//                        return false;
                    }
                } else {//未登录
                    if (isAjax)
                        throw new MessageException(request.getContextPath(), Result.LOGIN_NOT);
                    log.info("url = " + request.getRequestURL() + "    调回登陆，");
                    response.sendRedirect(request.getContextPath() + Constants.URL_LOGIN);
//                    request.getRequestDispatcher(Constants.URL_LOGIN).forward(request, response);
                    return false;
                }
                //获取url权限
                Authority.Role roles[] = a.role();
                // TODO: 2017/9/17 权限拦截处理未完成
                //获取登录角色权限
                Map<String, Object> map = RequestUtil.getLogin(request);
                if (map == null) {//未登录，直接拦截请求
                    log.info("未登录，不能访问该url(" + request.getRequestURL().toString() + ")");
                    throw new MessageException(Result.NO_AUTHORITY);
                }
                for (Authority.Role r : roles) {
                    if (r.ordinal() == (int) map.get(Constants.KEY_MAP_AUTHORITY)) {
                        log.info("url = " + r.toString() + "  " + map.get(Constants.KEY_MAP_AUTHORITY));
                        return true;
                    }
                }
                log.info("权限不足，不能访问该url(" + request.getRequestURL().toString() + ")");
                response.sendRedirect(request.getContextPath() + Constants.URL_LOGIN);
//                throw new MessageException(Result.NO_AUTHORITY);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        String date = httpServletResponse.getHeader("Date");
        if (date != null && date.endsWith("GMT")) {
            log.info("之前的时间 = " + date);
//            Calendar calendar = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.UK);
//            calendar.setTime(DateFormat.getInstance().parse(date));
//            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 8);
            httpServletResponse.setHeader("Date", format.parse(date).toString());
            log.info("之后的时间" + format.parse(date).toString());
//            SimpleDateFormat format = new SimpleDateFormat()
        }
    }

    public static void main(String[] args) {
        try {
            DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.UK);
            Date date = format.parse("Mon, 16 Oct 2017 07:49:43 GMT");
//            Date date = format.parse("Wed, 4 Jul 2001 12:08:56 -0700");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
