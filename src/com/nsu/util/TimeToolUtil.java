package com.nsu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间日期处理工具类
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-17 16:12:16
 */
public class TimeToolUtil {

	/**
	 * 计算与现在时间相差多少天
	 * @author ljl
	 * @createDate 2017-04-17 16:17:23
	 * @param timeObj
	 * @return 返回相差多少天，发生异常返回-1
	 */
	public static double nowTimeInterval(Object timeObj){
		Calendar calendar = Calendar.getInstance();
		long nowTime = calendar.getTime().getTime(); // 获得现在毫秒型日期
		try {
			long time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timeObj.toString()).getTime();
			double betweenDay = (time - nowTime) / (1000 * 60 * 60 * 24.0);
			return betweenDay;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
}
