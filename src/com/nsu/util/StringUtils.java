package com.nsu.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 处理字符串相关
 * @author Tig
 *
 */
public class StringUtils {


	/**
	 * 判断一个字符串是否全为数字
	 * @param str 字符串
	 * @return
	 */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

	/**
	 * 判断字符串是否是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str == null || "".equals(str.trim());
	}

	/**
	 * 字符串范围检查
	 * @param str
	 * @return
	 */
	public static boolean fitInterval(String str) {

		return !isEmpty(str) || (str.length() >= 0 && str.length() <= 18);

	}


	public static Set<String> stringToSet(String[] strings){
		List<String> list = Arrays.asList(strings);
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		return set;
	}
}
