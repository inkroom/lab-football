package cn.nsu.edu.web.four.config;

public class BaseStatic {

    public static final String CHARSET = "utf-8";


    public static final String KEY_SESSION_ORG_URL = "_KEY_SESSION_ORG_URL_";//原始路径，登陆后跳回
    public static final String KEY_SESSION_LOGIN = "_KEY_SESSION_LOGIN_USER_";//登录后的路径
    //public static final String KEY_SESSION_LOGIN = "/player";
    public static final String KEY_SESSION_ROLE = "_KEY_SESSION_ROLE_";//角色
    public static final String KEY_SESSION_SEARCH_RESULT = "_KEY_SESSION_SEARCH_RESULT_";
    public static final String KEY_SESSION_PHONE = "_KEY_SESSION_PHONE_";//手机号码，用于发送手机验证码

    public static final String KEY_SESSION_LIVE_PHONE = "live_phone";//直播认证之后的电话号码

    public static final String KEY_SESSION_SCHEDULE_INFORMATION = "schedule";//存储赛程信息

    public static final String KEY_SESSION_TOKEN = "token";//存放token

    public static final String KEY_CONTEXT_SESSION = "_KEY_CONTEXT_SESSION_";

    public static final String KEY_PARAMETER_TOKEN = KEY_SESSION_TOKEN;

    public static final String KEY_SOCKET_LIVE_TYPE = "type";//直播socket消息类型

    //用户Map的key
    public static final String KEY_MAP_USERNAME = "USERNAME";
    public static final String KEY_MAP_PASSWORD = "PASSWORD";
    public static final String KEY_MAP_SALT = "SALT";
    public static final String KEY_MAP_NAME="name";
    public static final String KEY_MAP_PHONE="phone";
    public static final String KEY_MAP_ID_CARE="idcrad";//身份证号码
    public static final String KEY_MAP_REFEREE_IS_CHECK="isCheck";//裁判员是否进行过球员验证

    public static final String KEY_SCHEDULE_ID = "schId";
    public static final String KEY_REFEREE_ID = "refId";
    public static final String KEY_MATCH_ID = "matchId";
    public static final String KEY_PLAYER_ID = "playerId";
    public static final String KEY_ORGANIZATION_ID = "orgId";


    public static final String KEY_COOKIE_AUTO_NAME = "_KEY_COOKIE_AUTO_NAME_";
    public static final String SEPARATOR_COOKIE_VALUE = "&";

    public static final String RESPONSE_CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    public static final String RESPONSE_CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    public static final String URL_LOGIN = "";


    public static final String PREFIX_REDIS = "_FOOTBALL4TH_";

    public static final String PREFIX_LIVE_DIRECTORY = "4TH_IMAGE_";//直播消息文件夹前缀

//    public static final long CLEAR_LIVE_INTERVAL =  60 * 1000;//清空直播消息间隔，单位毫秒

    public static final String KEY_SESSION_CODE = "_KEY_SESSION_CODE_";//登陆验证码

    //返回的json数据的key
    public static final String KEY_JSON_CODE = "code";//返回的json中的key
    public static final String KEY_JSON_SESSION_ID = "sessionId";//sessionId

    //手机验证码相关
    public static final int SMS_RETAIN_TIME = 20 * 60;//保留时间，20分钟
    public static final String SMS_PREFIX = "_SMS_PREFIX_";//手机验证码在redis中的key前缀
    public static final int SMS_MAX_ERROR_COUNT = 3;//手机验证码最多错误次数

    //球队分页相关
    public  static  final  int TEAMPAGE_DISPLAY = 15;
    //球队球员分页相关
    public  static  final  int TEAMPAGE_FINDPLAYER = 8;

    //分页显示页码数
    public  static  final  int TEAMPAGE_SHOWCODE= 5;

}
