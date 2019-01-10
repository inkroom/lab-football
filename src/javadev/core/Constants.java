package javadev.core;

import java.io.File;

public class Constants {

	/* 系统内部编码 */
	public static final String ENCODING = "UTF-8";

	/* 通用操作结果页面返回值 */
	public static final String EXECUTE_RESULT = "executeResult";

	/* 异常结果返回值 */
	public static final String EXCEPTION = "exception";

	/* 异常信息在上下文中的存储值 */
	public static final String CONTEXT_EXCEPTION = "_exception_";
	public static final String CONTEXT_EXCEPTION_INFO = "_exception_info_";


	
	/* 默认分页尺寸及分页标记 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int MAX_PAGE_SIZE = 1000;
	public static final String NORMAL_MARK = "?";
	public static final String START_MARK = ":_START_INDEX_";
	public static final String END_MARK = ":_END_INDEX_";

	/* 记录返回页面地址用的session key */
	public static final String REFER_URL = "_REFER_URL_";
	public static final String REFER_URL_DEFAULT_KEY = "_REFER_URL_DEFAULT_KEY_";

	/* 登录后用户信息在Session中的Key */
	public static final String LOGIN_USER = "_LOGIN_USER_";
	/* 登录用户ID在用户信息map中的Key */
	public static final String LOGIN_USER_ID = "_LOGIN_USER_ID_";
	
	/* 登录用户ID在用户信息map中的C_ID */
	public static final String LOGIN_USER_C_ID = "_LOGIN_USER_C_ID_";

	/* 登录后用户信息在Session中的Key */
	public static final String RANDOM_CODE = "_RANDOM_CODE_";

	/* 记录用户登录前想要访问的地址在Session中的Key */
	public static final String ORIGINAL_URL = "_ORIGINAL_URL_";

	/* 缓存相关 */
	public static final String CACHE_BASE_PATH = "/cache"; // 文件缓存目录
	public static final int COMMON_PERIOD = 60; // 内存缓存有效时间，单位为秒，-1表示缓存不失效
	public static final String SEPSTR = "_sEpStR_"; // 分隔字符串

	/* JSON操作结果页面返回值 */
	public static final String JSON_RESULT = "jsonResult";
	
	/*用户初始密码*/
	public static final String USER_INITIAL_PASSWORD = "123456";
	
	/*运动员初始图片存储地址*/
	public static final String PLAYER_DEFAULT_PHOTO = "player"+File.separator+"avatar_default.jpg";
	
	/* 点击赛事的规则存储 */
	public static final String MATCH_RULLES = "_MATCH_RULLES_";
	/*验证信息*/
	public static final String MSG = "_MSG_";
	/* 已验证的运动员 */
	public static final String VERIFIED = "_VERIFIED_";
	
	
	
	/* session中存放的密码盐 */
	public static final String SALT_IN_SESSION = "_SALT_IN_SESSION_";
}