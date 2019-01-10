package cn.nsu.edu.web.four.utils.time;

import java.util.Date;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.utils.time
 * @date 2018/3/21 9:27
 * @description
 **/
public class TimeJudge {
    //比较现在时间与预定时间  超时为false
    public static Boolean compareTime(Date date){
        if (System.currentTimeMillis()<date.getTime()){
            return true;
        }
        else return false;
    }

}
