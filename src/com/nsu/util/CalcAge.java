package com.nsu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: CalcAge.java
 * @Package com.nsu.util
 * @Description: 根据身份证号计算年龄
 * @author 侯松梁
 * @date 2017年4月12日 上午11:13:59
 * @version V1.0
 */
public class CalcAge {
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

}
