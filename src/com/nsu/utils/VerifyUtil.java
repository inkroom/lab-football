package com.nsu.utils;

public class VerifyUtil {
	
	/**
	 *  判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static boolean isChineseString(String target){
		String regexStr = "[\\u4e00-\\u9fa5]+";
			return target.matches(regexStr);
	}
	
	public static boolean idChineseAndNum(String target){
		String regexStr = "[\\d\\u4e00-\\u9fa5]+";
		return target.matches(regexStr);
	}
	
	
	public static void main(String[] args) {
		// /^[\d\u4E00-\u9FA5]+$/;
		
//		String targetString = "吗12 ";
//		System.out.println(targetString.matches(regexStr));
	}
	
	
	
}
