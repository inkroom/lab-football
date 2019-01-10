package cn.nsu.edu.web.four.beans.match;

import cn.nsu.edu.web.four.config.BaseStatic;

/**
 * @author Lika
 * @date 2018/04/02 9:05
 * Description:赛事相关常量
 */
public class MatchStatic extends BaseStatic {
    //赛事状态
    public static final int MATCH_STATUS_SIGN_UP = 0;
    public static final int MATCH_STATUS_DURING = 1;
    public static final int MATCH_STATUS_END = 2;
    public static final int MATCH_STATUS_DELETE = 3;

    //赛事级别
    public static final int MATCH_LEVEL_SCHOOL = 0;
    public static final int MATCH_LEVEL_COUNTY = 1;
    public static final int MATCH_LEVEL_CITY = 2;
    public static final int MATCH_LEVEL_PROVINCE = 3;

    //搜索分页相关
    public static final int EACH_PAGE_COUNT = 15;
    public static final int MATCH_PAGE_DATA_COUNT = 8;
    public static final int MATCH_NAVIGATE_PAGES = 5;
}
