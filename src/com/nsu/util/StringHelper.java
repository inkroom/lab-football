package com.nsu.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串处理工具类
 * 
 * @author Auger
 * @version 1.0
 */
public class StringHelper {

	/**
	 * 
	 * 获取当前格式化时间（yyyy-MM-dd hh:mm:ss）<BR/>
	 * 方法名：getCurrentFormatDate<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @return String<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static String getCurrentFormatDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String datetime = sdf.format(date);
		return datetime;
	}

	/**
	 * 
	 * 获取当前时间戳<BR/>
	 * 方法名：getCurrentTimeStamp<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @return long<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static long getCurrentTimeStamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * 将时间戳转换成时间格式为 yyy-MM-dd hh:mm:ss 的时间<BR/>
	 * 方法名：getDateByTimeStamp<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @param timestamp
	 * @return String<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static String getDateByTimeStamp(long timestamp) {
		if (timestamp != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = sdf.format(new Date(timestamp));
			System.out.println(date);
			return date;
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 将时间戳转换成时间格式为 yyy-MM-dd hh:mm:ss或者yyy-MM-dd 的时间<BR/>
	 * 方法名：getDateByTimeStamp<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @param timestamp
	 * @param format
	 * @return String<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static String getDateByTimeStamp(long timestamp, String format) {
		if (timestamp != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String date = sdf.format(new Date(timestamp));
			return date;
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 将时间字符串转换成时间戳<BR/>
	 * 方法名：getTime<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @param user_time
	 * @return String<BR/>
	 * @throws ParseException
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static Long getTime(String user_time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(user_time);
			Long timeStemp = date.getTime();
			return timeStemp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * 获取size长度的一个随机字母字符串
	 * @param size
	 * @return
	 */
	public static String getRandomGeneration(int size){
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder random = new StringBuilder();
		for (int i = 0; i<size; i++){
			random.append(chars.charAt((int)(Math.random() * 26))) ;
		}
		return random.toString();
	}
}
