package cn.inkroom.web.money.gate.config;

public class BaseStatic {
    public static final String KEY_SESSION_ORG_URL = "_KEY_SESSION_ORG_URL_";
    public static final String KEY_SESSION_LOGIN = "_KEY_SESSION_LOGIN_USER_";
    public static final String KEY_SESSION_SEARCH_RESULT = "_KEY_SESSION_SEARCH_RESULT_";

    public static final String KEY_CONTEXT_SESSION = "_KEY_CONTEXT_SESSION_";

    //用户Map的key
    public static final String KEY_MAP_USERNAME = "USERNAME";
    public static final String KEY_MAP_PASSWORD = "PASSWORD";
    public static final String KEY_MAP_SALT = "SALT";
    public static final String CHARSET = "UTF-8";


    public static final String RESPONSE_CONTENT_TYPE_JSON = "application/json;charset=utf-8";


    public static final String URL_LOGIN = "";


    public static final String PREFIX_REDIS = "_GATE_";

    public static final String KEY_SESSION_CODE = "_KEY_SESSION_CODE_";//登陆验证码

    //返回的json数据的key
    public static final String KEY_JSON_CODE = "code";//返回的json中的key
    public static final String KEY_JSON_SESSION_ID = "sessionId";//sessionId


    //搜索分页相关
    public static final int EACH_PAGE_COUNT = 15;

}
