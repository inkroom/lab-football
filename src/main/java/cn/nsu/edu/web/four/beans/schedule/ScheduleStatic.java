package cn.nsu.edu.web.four.beans.schedule;

import cn.nsu.edu.web.four.config.BaseStatic;

/**
 * @author Lika
 * @date 2018/04/02 9:12
 * Description:
 */
public class ScheduleStatic extends BaseStatic {
    //赛程状态
    public static final int SCHEDULE_STATUS_READY = 0;
    public static final int SCHEDULE_STATUS_DURING = 1;
    public static final int SCHEDULE_STATUS_END = 2;
    public static final int SCHEDULE_STATUS_DELETE = 3;

    //裁判状态
    public static final int REFEREE_STATUS_VALID = 0;
    public static final int REFEREE_STATUS_INVALID = 1;

}
