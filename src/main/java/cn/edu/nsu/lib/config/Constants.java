package cn.edu.nsu.lib.config;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/12
 * @Time 21:51
 * @Descorption 静态常量
 */
public class Constants {

    //用户Map中存放username的key
    public static final String KEY_MAP_USERNAME = "username";
    public static final String KEY_MAP_AUTHORITY = "identity";
    public static final String KEY_MAP_PASSWORD = "password";
    public static final String KEY_MAP_SALT = "salt";
    public static final String KAY_MAP_LAB_ID ="lab_id";
    public static final String KEY_MAP_ID = "id";

    public static final String INIT_PASSWORD="20171116";

    //session域的key
    public static final String KEY_SESSION_LOGIN = "_KEY_SESSION_LOGIN_";//session中存储的登录key
    public static final String KEY_SESSION_CODE = "_KEY_SESSION_CODE_";//存储的验证码
    //context域的key
    public static final String KEY_CONTEXT_CODE = "_KEY_CONTEXT_CODE_";//存放微信小程序验证码
    public static final String KEY_CONTEXT_PATH = "base_path";//存放项目基础路径
    public static final String KEY_CONTEXT_SESSION = "_KEY_CONTEXT_SESSION_";//servletContext中存储的sessionId的map的key
    public static final String KEY_CONTEXT_TIMES="_KEY_CONTEXT_TIMES_";//存放二维码过期时间
    //cookie的key
    public static final String KEY_COOKIES_STATUS = "_KEY_COOKIES_STATUS_";//自动登陆的cookie name
    //request域的key
    public static final String KEY_REQUEST_MESSAGE = "_KEY_REQUEST_MESSAGE_";//request中存储message

    //前后缀
    public static final String PREFIX_URL_COMMON = "/common/";
    public static final String PREFIX_VIEW = "/WEB-INF/views/";
    public static final String SUFFIX_VIEW = ".jsp";

    //返回的json数据的key
    public static final String KEY_JSON_CODE = "code";//返回的json中的key
    public static final String KEY_JSON_SESSION_ID = "sessionId";//sessionId
    //获取前台数据的key
    public static final String KEY_PARAMETER_ANSWER = "answer";//获取参数的key
    //一些常用的url
//    public static final String URL_PREFIX_COMMON = "/common/";
    public static final String URL_404_ALL = PREFIX_VIEW + "/404" + SUFFIX_VIEW;
    public static final String URL_AUTHORITY_ALL = PREFIX_VIEW + "/403" + SUFFIX_VIEW;
    public static final String URL_MESSAGE = "/common/message";//直接指向jsp文件
    public static final String URL_MESSAGE_ALL = PREFIX_VIEW + URL_MESSAGE + SUFFIX_VIEW;
    public static final String URL_LOGIN = "/login";

    public static final String CHARSET = "utf-8";//字符集
    public static final String RESPONSE_CONTENT_TYPE_JSON = "text/html;charset=" + CHARSET;

}
