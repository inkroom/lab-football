package com.nsu.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
		return sdf.format(date);
	}


	/**
	 * 
	 * 将时间戳转换成时间格式为 yyy-MM-dd hh:mm:ss 的时间<BR/>
	 * 方法名：getDateFormat<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @param timestamp
	 * @return String<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static String getDateFormat(long timestamp) {
		if (timestamp != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = sdf.format(new Date(timestamp));
			return date;
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 将时间戳转换成时间格式为 yyy-MM-dd hh:mm:ss或者yyy-MM-dd 的时间<BR/>
	 * 方法名：getDateFormat<BR/>
	 * 创建人：auger <BR/>
	 * 
	 * @param timestamp
	 * @param format
	 * @return String<BR/>
	 * @exception <BR/>
	 * @since 1.0.0
	 */
	public static StringBuilder getDateFormat(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return new StringBuilder(sdf.format((date))) ;
		} else {
			return new StringBuilder();
		}
	}
	public static Integer IDCardNoToAge(String idCardNo) {

		int length = idCardNo.length();
		String dates = "";
		//18位身份证
		if (length == 18) {
			dates = idCardNo.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new Date());
			int u = Integer.parseInt(year) - Integer.parseInt(dates);
			return u;
			//15位身份证
		} else if (length == 15) {
			dates = idCardNo.substring(6, 8);
			return Integer.parseInt(dates);
		} else {
			return 0;
		}

	}
	/**
	 * 将base64还原成文件
	 * @author ljl
	 * @createDate 2017-04-17 20:52:04
	 * @param fullPath
	 * @param base64
	 * @return
	 */
	public static File base64ToFile(String fullPath, String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; i++) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(fullPath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new File(fullPath);
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
