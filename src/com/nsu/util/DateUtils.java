package com.nsu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.util
 * @Description:
 * @date 17/4/20
 */
public class DateUtils {

    private static final int TIME_SECOND = 1000;

    public static SimpleDateFormat getSDF(){
        SimpleDateFormat sdf=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        return sdf;
    }

    /**
     * 比较两个时间，如果后者大，或者相等 返回true 反之返回false
     * @param date1
     * @param date2
     * @return
     */
    public static boolean competeTime(Date date1,Date date2) throws Exception {
        try {
            if (date1.getTime() < date2.getTime()) {
                return true;
            } else if (date1.getTime() > date2.getTime()) {
                return false;
            } else {//相等
                return true;
            }
        }catch (Exception e){
            throw new Exception("DateUtils competeTime() 比较时间失败");
        }

    }

    /**
     * 判断是否在 before 和 after 的时间区间内
     * @param before
     * @param after
     * @param now
     * @return
     */
    public static boolean isIn(Date before,Date after,Date now) throws Exception {
        try {
            if (competeTime(before,now) && competeTime(now,after)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new Exception("DateUtils isIn() 比较时间失败");
        }

    }



    /**
     * 加上多少秒
     * @param date
     * @param second
     * @return
     */
    public static Date addTime(Date date,long second) throws Exception {
        try {
            return new Date(date.getTime() + second);
        }catch (Exception e){
            throw new Exception("DateUtils addTime() 时间计算异常");
        }
    }

    /**
     * 减去多少秒
     * @param date
     * @param second
     * @return
     */
    public static Date subTime(Date date,long second) throws Exception {
        try {
            return new Date(date.getTime() - second);
        }catch (Exception e){
            throw new Exception("DateUtils subTime() 时间计算异常");
        }

    }


    public static Date stringToDate(String date) throws Exception {

        try {
            return getSDF().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new Exception("DateUtils stringToDate() 时间转换异常");
        }
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Date data = new Date();
        String dataString = data.toString();
        System.out.println(dataString);
        System.out.println(getSDF().parse(dataString));
    }

}
