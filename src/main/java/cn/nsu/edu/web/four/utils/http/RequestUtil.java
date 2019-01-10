package cn.nsu.edu.web.four.utils.http;

import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.enums.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/15
 * @Time 21:26
 * @Descorption 请求工具类，包括记录登陆，注销登陆，获取登录验证，判断请求类型等功能
 */
public class RequestUtil {
    private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

    @SuppressWarnings("unchecked")
    public static void login(HttpServletRequest request, Map<String, Object> account, Role role) {
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_LOGIN, account);

        request.getSession().setAttribute(BaseStatic.KEY_SESSION_ROLE, role);
        ((Map) request.getSession().getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .put(account.get(BaseStatic.KEY_MAP_USERNAME), request.getSession().getId());
//        account.setSalt(null);
//        account.setPassword(null);
    }

    public static void logout(HttpServletRequest request) {
        ((Map) request.getSession().getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .remove(((Map) request.getSession().getAttribute(BaseStatic.KEY_SESSION_LOGIN)).get(BaseStatic.KEY_MAP_USERNAME));
        request.getSession().invalidate();
    }

    public static Role getRole(HttpServletRequest request) {
        return getRole(request.getSession());
    }

    public static Role getRole(HttpSession session) {
        log.info("role = " + session.getAttribute(BaseStatic.KEY_SESSION_ROLE));
        Object obj = session.getAttribute(BaseStatic.KEY_SESSION_ROLE);
        if (obj == null) return null;
        if (obj instanceof String)
            return Role.valueOf(obj.toString());
        return (Role) obj;
    }


    public static void remove(HttpSession session) {
        ((Map) session.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .remove(session.getAttribute(BaseStatic.KEY_SESSION_LOGIN));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getLogin(HttpServletRequest request) {
        return (Map<String, Object>) request.getSession().getAttribute(BaseStatic.KEY_SESSION_LOGIN);
    }

    /**
     * 判断是否已登录
     *
     * @param request
     * @return
     */
    public static boolean isExists(HttpServletRequest request) {
        log.info("Context = " + request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION));
        if (request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION) == null)
            return false;
        if (((Map) request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .get(RequestUtil.getLogin(request).get(BaseStatic.KEY_MAP_USERNAME)) == null) return false;
        log.info("Context = " + ((Map) request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .get(RequestUtil.getLogin(request).get(BaseStatic.KEY_MAP_USERNAME)));
        log.info("session = " + request.getSession());
        log.info("username = " + ((Map) request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .get(RequestUtil.getLogin(request).get(BaseStatic.KEY_MAP_USERNAME)));
        boolean result = ((Map) request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION))
                .get(RequestUtil.getLogin(request).get(BaseStatic.KEY_MAP_USERNAME))
                .equals(request.getSession().getId());

        //获取存储的sessionId
//        log.debug(request.getServletContext().getAttribute(BaseStatic.KEY_CONTEXT_SESSION));
//        log.debug(request.getSession().getId());
        return result;
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || "XMLHttpRequest".equals(request.getParameter("X-Requested-With"));
    }

    public static String getIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
//            System.out.println("ip = "+request.getRemoteAddr()+"  host = "+request.getRemoteHost());
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static String getLastUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int start = uri.lastIndexOf("/");
        int end = uri.lastIndexOf(".html");
        log.info(uri + "   " + start + "   end" + end);
        return uri.substring(start + 1, end);
    }

    public static String[] getCookeValues(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue().split(BaseStatic.SEPARATOR_COOKIE_VALUE);
            }
        }
        return null;
    }
}
