package com.nsu.action;

import com.nsu.action.BaseAction;

/**
 * 功能描述:自己用的工具类
 * @author:肖宇轩
 * @version:1.0
 * Create Date:2016.11.22
 */
public class Tools extends BaseAction{

	/**
	 * 判断一个字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumer(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	/**
	 * 判断一个字符串是否为中文
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str){
        String reg = "[\\u4e00-\\u9fa5]+";
          return str.matches(reg);
	}
}