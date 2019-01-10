package cn.edu.nsu.lib.interceptors;

/**
 * @author 墨盒
 * @Descorption 自动登陆拦截器
 */
public class AutoLoginInterceptor extends BaseInterceptor {

//    @Autowired
//    private AccountService accountService;
//
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        if (request.getSession().getAttribute(Constants.KEY_SESSION_LOGIN) == null) {//未登录
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null && cookies.length > 0) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals(Constants.KEY_COOKIES_STATUS)) {
//                        try {
//                            CookieUtil cookieUtil = new CookieUtil(cookie.getValue());
//                            AccountBean accountBean = null;
//                            if (!cookieUtil.isAuto()
//                                    || (accountBean = this.accountService.login(cookieUtil.getAccount(), cookieUtil.getPassword(), false)) == null)
//                                break;
//                            RequestUtil.login(request, accountBean);
//                        } catch (Exception e) {
////                            e.printStackTrace();
//                            throw new MessageException("request.common.information", e);
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }

}
