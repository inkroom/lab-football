package com.nsu.util.base;

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
	
	//     /^[a-zA-z\u4E00-\u9FA5\(^\s+)|(\s+$)/g]*$/.test(name)
	
	public static boolean isNameEC(String target){
		String regexStr = "[a-zA-z\\u4E00-\\u9FA5\\(^\\s+)|(\\s+$)/g]+";
		return target.matches(regexStr);
	}
	
	
	public static void main(String[] args) {
		// /^[\d\u4E00-\u9FA5]+$/;
		String regexStr = "a ";
		System.out.println(isNameEC(regexStr));
//		String targetString = "吗12 ";
//		System.out.println(targetString.matches(regexStr));
	}
	
	
	
}
