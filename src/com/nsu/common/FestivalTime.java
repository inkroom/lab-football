package com.nsu.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FestivalTime {
	/**
	 * 将月份传入nextTime，返回一个day，为几月几日
	 * @param month
	 * @return
	 */
	public static String nextTime(int month){
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("MM月dd日");
		String day = "";
		cal.set(Calendar.MONTH, month-1); // 月份的起始为0
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		int sundays = 0;
		int thursdays = 0;
		for(int i = 1; i <= maxDate; i ++) {
			cal.set(Calendar.DATE, i);
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				sundays ++;
				//母亲节
				if(month == 5 && sundays == 2) {
					day = df.format(cal.getTime());
					break;
				}
				//父亲节
				if(month == 6 && sundays == 3){
					day = df.format(cal.getTime());
					break;
				}
			}
			//感恩节
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
				thursdays++;
				if(month == 11 && thursdays ==4){
					day = df.format(cal.getTime());
					break;
				}
			}
		}
		return day;
	}
	/**
	 * 将节日放到map中，再将两个Map放到一个list里面作为返回值返回
	 * @return
	 */
	public static List<Map<String,String>> AddFestival(){
		Map<String,String> Festival = new HashMap<String,String>();
        Map<String,String> LunarFestival = new HashMap<String,String>();
        List<Map<String,String>> FestivalList = new ArrayList<Map<String,String>>();
		String MothersDay = FestivalTime.nextTime(5);
        String FathersDay = FestivalTime.nextTime(6);
        String ThanksgivingDay = FestivalTime.nextTime(11);
        //阳历节日
        Festival.put("01月01日", "YuanDan.png");
        Festival.put("03月08日", "FuNv.png");
        Festival.put("05月01日", "LaoDong.png");
        Festival.put("05月04日", "QingNian.png");
        Festival.put("06月01日", "LiuYi.jpg");
        Festival.put("07月01日", "JianDang.png");
        Festival.put("08月01日", "JianJun.png");
        Festival.put("09月18日", "JiuYiBa.png");
        Festival.put("09月10日", "JiaoShi.jpg");
        Festival.put("10月01日", "GuoQing.png");
        Festival.put(MothersDay, "MuQin.png");
        Festival.put(FathersDay, "FuQin.png");
        Festival.put(ThanksgivingDay, "GanEn.png");
        
        //阴历节日
        LunarFestival.put("一月初一", "ChunJie.png");
        LunarFestival.put("五月初五", "DuanWu.png");
//        LunarFestival.put("七月初七", "QiXi.png");
        LunarFestival.put("八月十五", "ZhongQiu.png");
        
        FestivalList.add(Festival);
        FestivalList.add(LunarFestival);
        return FestivalList;
	}
	public static String isFestival(List<Map<String,String>> list){
		String isFestival = "";
		Calendar today = Calendar.getInstance();
		
		try {
			today.setTime(Lunar.chineseDateFormat.parse("2017年6月1日"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Lunar lunar = new Lunar(today);
        String nowTime = Lunar.chineseDateFormat.format(today.getTime());
        String lunarTime = lunar.toString();
        nowTime = nowTime.substring(nowTime.length()-6);
        lunarTime = lunarTime.substring(lunarTime.length()-4, lunarTime.length());
		for(Map.Entry<String, String> entry:list.get(0).entrySet()){
			if(entry.getKey().equals(nowTime) || entry.getKey() == nowTime){
				isFestival = entry.getValue();
			}
		}
		for(Map.Entry<String, String> entry:list.get(1).entrySet()){
			if(entry.getKey().equals(lunarTime) || entry.getKey() == lunarTime){
				isFestival = entry.getValue();
			}
		}
		return isFestival;
	}
}
