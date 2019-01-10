package com.nsu.utils;

import com.nsu.common.annotation.ValidateAnnotation;
import com.nsu.exception.validate.AnalysisException;
import com.nsu.exception.validate.CustomValidateException;
import com.nsu.exception.validate.DataException;
import com.nsu.exception.validate.IllegalFormatException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 正则验证工具类
 * @author ljl
 * @version 1.0.0
 * @date 2017/3/13 16:01:45
 */
public class V {

	/**
	 * 正则表达式验证
	 * @author ljl
	 * @create_date 2017/3/13  18:46:45
	 * @param regex 正则表达式
	 * @param str   要验证的字符串
	 * @return 匹配返回TRUE 否则返回FALSE
	 */
	public static boolean regularVerification(String regex, String str){
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 校验是否是全为简体中文、英文、数字、逗号、句号,且最长位300
	 * @author ljl
	 * @create_date 2017/3/13  17:18:36
	 * @param str 要校验的字符串
	 * @return Returns true if It is all correct character, otherwise it returns false
	 */
	public static boolean checkCHSAndENOrPeriodOrComma(String str){
		String regExpSpace = "^[\u4E00-\u9FA5A-Za-z0-9\\,\\。\\，\\s]+$";
		String regExp = "[\u4E00-\u9FA5A-Za-z0-9\\,\\。\\，]+";
		if(str != null && str.length() > 0 && str.length() < 301){
			if(regularVerification(regExpSpace, str) == true){
				if(regularVerification(regExp, str)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * <p>校验是否是一个正整数</p>
	 * @author VF
	 * @date 2017-05-15 20:07:45
	 * @param value 要校验的正整数
	 * @param length 正整数的最大长度
	 * @return 是一个正整数返回true，否则返回false
	 */
	public static boolean validatePositiveInteger(Object value, int length){
		
		String regExp = "^(0|[1-9][0-9]*)$";
		if(isEmpty(value) == false){
			String number = value.toString();
			if(number.length()<=length){
				if(regularVerification(regExp, number) == true){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * <p>校验是否是一个正实数</p>
	 * @author VF
	 * @date 2017-05-15 20:11:53
	 * @param value 要验证的数据
	 * @param length 实数的整数部分的长度
	 * @param decimalPlaces 小数点后保留的位数
	 * @return 是一个正实数返回true， 否则返回false
	 */
	public static boolean validateDecimal(Object value, int length, int decimalPlaces) {
		//验证整数部分只有一位的情况
		String regExpOneSizeInHead = "^[0-9]{1}\\.[0-9]{1,"+decimalPlaces+"}$";
		//验证整数部分以非0开头至少2位的情况
		String regExp = "^[1-9][0-9]+\\.[0-9]{1,"+decimalPlaces+"}$";
		if(isEmpty(value) == false){
			String number = value.toString();
			int pointIndex = number.lastIndexOf(".");
			if(pointIndex == -1){
				//直接验正整数
				if(validatePositiveInteger(number, length) == true){
					return true;
				}
			}else{
				if(pointIndex > 0 && pointIndex <= length && decimalPlaces >= 0) {
					//验证整数部分只有一位的情况
					if(regularVerification(regExpOneSizeInHead, number) == true){
						return true;
					}else{
						//验证整数部分有1位以上的情况
						if(regularVerification(regExp, number) == true){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * <p>校验是否全为中文字符</p>
	 * @author ljl
	 * @date 2017-05-15 20:39:09
	 * @param value 要校验的字符串
	 * @param length 最大长度
	 * @return 数据合法返回true， 否则返回false
	 */
	public static boolean validateCN(Object value, int length) {
		String regExp = "^[\u4e00-\u9fa5]+$";
		if(isEmpty(value) == false){
			String str = value.toString();
			if(str.length()<=length && regularVerification(regExp, str) == true){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 判断输入的字符串是否为空或空字符串
	 * @author ljl
	 * @create_date 2017年3月22日 下午8:07:55
	 * @param param 进行判断的对象
	 * @return 判断的对象为空返回true,否则返回false
	 */
	public static boolean checkEmpty(Object param){
		if(param == null)
			return true;
		if("".equals(param.toString().trim()))
			return true;
		return false;
	}
	
	/**
	 * 验证是否为空
	 * @author VF
	 * @date 2017-05-15 16:31:41
	 * @param obj 要验证的数据
	 * @return 数据不为null并且长度大于0时返回false，否则返回true
	 */
	public static boolean isEmpty(Object obj) {
		
		if(obj!= null && obj.toString().length()>0) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * <p>验证是否是数字</p>
	 * @author VF
	 * @date 2017-05-15 20:26:40
	 * @param value 要验证的数据
	 * @param length 最大长度
	 * @return
	 */
	public static boolean validateNumber(Object value, int length) {
		
		String regex = "^[0-9]+$";
		if(isEmpty(value) == false){
			String number = value.toString();
			if(number.length()<= length && regularVerification(regex, number) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>校验是否全为英文字符</p>
	 * @author VF
	 * @date 2017-05-15 20:46:59
	 * @param value 要校验的字符串
	 * @param length 最大长度
	 * @return 数据合法返回true， 否则返回false
	 */
	public static boolean validateEN(Object value, int length) {
		String regExp = "^[a-zA-Z]+$";
		if(isEmpty(value) == false){
			String str = value.toString();
			if(str.length()<=length && regularVerification(regExp, str) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>校验是否只包含中英文</p>
	 * @author VF
	 * @date 2017-05-15 20:49:55
	 * @param value 要验证的数据
	 * @param length 最大长度
	 * @return 数据合法返回true， 否则返回false
	 */
	public static boolean validateCNAndEN(Object value, int length) {
		String regExp = "^[\u4e00-\u9fa5a-zA-Z]+$";
		if(isEmpty(value) == false){
			String str = value.toString();
			if(str.length()<=length && regularVerification(regExp, str) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>验证邮箱</p>
	 * 邮箱最大长度300
	 * @author VF
	 * @date 2017-05-15 20:53:03
	 * @param email 要验证的邮箱
	 * @return 是合法的邮箱返回true， 否则返回false
	 */
	public static boolean validateEmail(Object email) {
		String regExp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		if(isEmpty(email) == false){
			String emailStr = email.toString();
			if(emailStr.length()<=300 && regularVerification(regExp, emailStr) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
     * 校验11位手机号码
     * @author ljl
     * @createDate 2017-04-11 15:00:43
     * @param phone 验证的手机号
     * @return 是合法的手机号返回true， 否则返回false
     */
    public static boolean isPhoneNumber(Object phone){
    	String regExpPhone = "^[1]{1}[2-578]{1}[0-9]{9}$";
    	if(isEmpty(phone) == false){
    		if(phone.toString().length()==11 && regularVerification(regExpPhone, phone.toString()) == true){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 校验是否是一个中文名字或英文名字
     * <p>名字中的点是 ·  。这个是中文输入法下的 `~ 这个键</p>
     * @author ljl
     * @createDate 2017-04-13 10:56:44
     * @param name 要验证的名字
     * @param length 名字最大长度
     * @return 正确的名字返回true，否则返回false
     */
    public static boolean validatePersonName(Object name, int length){
    	
    	String regExp = "^[\u4e00-\u9fa5a-zA-Z]+[\\·]{0,1}[\u4e00-\u9fa5a-zA-Z]*$";
    	if(isEmpty(name) == false){
    		String nameStr = name.toString();
    		if(nameStr.length()<=length && regularVerification(regExp, nameStr)){
    			return true;
    		}
    	}
    	return false;
    }
	
    /**
	 * 校验是否是全为简体中文、英文、数字
	 * @author ljl
	 * @create_date 2017/3/13  17:18:36
	 * @param length 数据最大长度
	 * @return 数据合法返回true， 否则返回false
	 */
	public static boolean validateCHAndENAndNumber(Object value, int length){
		String regExp = "^[\u4E00-\u9FA5A-Za-z0-9]+$";
		if(isEmpty(value) == false){
			String str = value.toString();
			if(str.length()<=length && regularVerification(regExp, str) == true){
				return true;
			}
		}
		return false;
	}
    
	/**
	 * <p>校验是否含有非法字符</p>
	 * <p>仅匹配中英文、数字、换行、空格、回车、常用的中英文标点符号</p>
	 * @author VF
	 * @date 2017-05-15 21:47:13
	 * @param value 要校验的数据
	 * @param length 最大长度
	 * @return 数据合法返回true， 否则返回false
	 */
	public static boolean validateText(Object value, int length){
		String regex = "^[\u4E00-\u9FA5A-Za-z0-9\\s\\?\\\\,\\.\\'\"\\、\\，\\。\\？\\；\\’\\‘\\“\\”\\:\\：]+$";
		if(isEmpty(value) == false) {
			String text = value.toString();
			if(text.length() <= length && regularVerification(regex, text) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证18位身份证号码
	 * [特别说明：只能验证18位的身份证]
	 * @author ljl
	 * @createDate 2017-05-03 18:40:46
	 * @param idCard 验证的身份证号
	 * @return 身份证号合法返回true,否则返回false
	 */
	public static boolean validateIDCard(Object idCard){
		String regExpName = "^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$";
		if(isEmpty(idCard)==true || idCard.toString().length() != 18 || regularVerification(regExpName, idCard.toString()) == false){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * <p>验证pojo中有ValidateAnnotation此注解的变量的数据是否合法</p>
	 * <p>特别说明：</p>
	 * <p>1.若注解中errorMeg是默认的，则错误提示将使用默认提示；</p>
	 * <p>2.若是自定义验证,需要设置自定义的正则表达式,若没有设置则不验证</p>
	 * <p>3.数字验证长度最大30位</p>
	 * <p>4.正实数验证整数部分最大20位，小数部分最大6位</p>
	 * <p>5.正整数验证长度最大20位</p>
	 * @author VF
	 * @date 2017-05-15 16:36:30
	 * @param obj 验证的pojo
	 * @param specialMark 特殊验证标记(多个标记时请使用英文逗号分隔)，specialMark注解值包含此传入的specialMark值时将不会验证此变量
	 * @return 数据合法返回null; 否则返回存放错误信息的Map
	 * <p>Map错误信息key说明：errorFiled ：非法数据的变量名；errorMsg : 错误提示</p>
	 */
	public static Map<String, Object> validateByAnnotation(Object obj, String specialMark) throws AnalysisException, DataException, IllegalFormatException, CustomValidateException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//默认错误提示
		String errorMsg = "请输入合法的数据";
		
		//处理不需要验证的标记
		Set<String> specialMarkSet = null;
		if(!V.isEmpty(specialMark)){
			specialMarkSet = new HashSet<String>();
			String[] specialMarkArr = specialMark.split(",");
			for(String key : specialMarkArr){
				specialMarkSet.add(key);
			}	
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f: fields){
			
			ValidateAnnotation v = f.getAnnotation(ValidateAnnotation.class);
			f.setAccessible(true);
			//验证是否有验证的注解
			if(v != null){
				//获取验证标记
				if(null!=specialMarkSet && noNeedValidata(v.specialMark(), specialMarkSet)){
                    continue;
                }

				if(isEmpty(v.errorMsg()) == false){
					errorMsg = v.errorMsg();
				}

				//获取数据
				Object value = null;
				try {
					value = f.get (obj);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new IllegalFormatException();
				}
				//验证数据
				boolean result = isLegalDataValidateByValidateType(v.validateType(), value, v.customRegex(), v.isAllowEmpty());

                if (!result){
					throw new DataException(errorMsg);
				}

//					if(result == false){
//						//获取错误提示消息
//
//						resultMap.put("errorFiled", f.getName());
//						resultMap.put("errorMsg", errorMsg);
//						return resultMap;
//					}
			}
		}
		return null;
	}
	
	/**
	 * 判断是否有特殊标记为不用验证
	 * @author ljl
	 * @createDate 2017-07-04 21:51:16
	 * @param marks 注解的特殊标记
	 * @param markSet 传递的特殊标记
	 * @return 不用验证返回true； 否则返回false
	 */
	private static boolean noNeedValidata(String marks, Set<String> markSet){
		
		if(V.isEmpty(marks)){
			return false;
		}
		String[] marksArr = marks.split(",");
		for(String key : marksArr){
			
			if(markSet.contains(key)){
				//存在当前特殊标记key值，不用验证
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>根据验证类型验证数据</p>
	 * @author VF
	 * @date 2017-05-15 17:21:29
	 * @param type 验证的类型
	 * @param regex 自定义正则表达式（未使用自定义验证时，请传入null）
	 * @param isAllowEmpty 是否允许为空
	 * @return 数据合法返回true， 否则返回false
	 */
	private static boolean isLegalDataValidateByValidateType(int type, Object value, String regex, boolean isAllowEmpty) throws CustomValidateException {
		
		//验证是否为空
		if(isEmpty(value) == true){
			return isAllowEmpty;
		}
		boolean validateResult = false;
		switch(type) {
			case 1:
				// 小数
				validateResult = validateDecimal(value.toString().trim(), 20, 6);
				break;
			case 2:
				// 正整数
				validateResult = validatePositiveInteger(value.toString().trim(), 20);
				break;
			case 3:
				// 数字
				validateResult = validateNumber(value.toString().trim(), 30);
				break;
			case 4:
				// 中文
				validateResult = validateCN(value.toString().trim(), 200);
				break;
			case 5:
				// 英文
				validateResult = validateEN(value.toString().trim(), 200);
				break;
			case 6:
				// 中英文
				validateResult = validateCNAndEN(value.toString().trim(), 200);
				break;
			case 7:
				// 邮箱
				validateResult = validateEmail(value.toString().trim());
				break;
			case 8:
				// 手机号码
				validateResult = isPhoneNumber(value.toString().trim());
				break;
			case 9:
				// 人名
				validateResult = validatePersonName(value.toString().trim(), 50);
				break;
			case 10:
				// 文本
				validateResult = validateText(value.toString().trim(), 300);
				break;
			case 11:
				// 中英文数字
				validateResult = validateCHAndENAndNumber(value.toString().trim(), 50);
				break;
			case 12:
				// 自定义验证
				if(isEmpty(regex) == false){
					try {
						validateResult = regularVerification(regex, value.toString().trim());
					}catch (PatternSyntaxException exception){
						throw new CustomValidateException();
					}

				}else{
					validateResult = true;
				}
				break;
			case 13:
				// 身份证号
				validateResult = validateIDCard(value.toString().trim());
				break;
		}
		return validateResult;
	}
	
	/**
	 * 简单处理敏感字符串
	 * @author ljl
	 * @createDate 2017-07-07 09:17:04
	 * @param str 验证的字符串
	 * @param defaultValue 默认值
	 * @param validateType 验证类型
	 * <p>验证类型相关值</p>
	 * <p>1 小数，2 正整数， 3 数字， 4中文， 5英文，  6中英文， 7邮箱， 8手机号码
	 * ， 9人名， 10文本， 11中英文数字，  12自定义验证， 13身份证号</p>
	 * @param regx 自定义正则表达式
	 * @param enhancementProcessing 是否增强处理(即对 中英文数字进行特殊字符过滤处理)
	 * @return 字符串非法，返回默认值；否则返回原字符串
	 */
	public static String stringsSnsitiveProcessing(String str, String defaultValue, int validateType, String regx, boolean enhancementProcessing) throws CustomValidateException {
		if(false ==isLegalDataValidateByValidateType(validateType, str, null, false)){
			if((11 == validateType || 4 == validateType || 5 == validateType || 6 == validateType)
					&& !V.isEmpty(str) && enhancementProcessing){
				//对于中英文数字,进行特殊字符过滤处理
				str = replaceSpecStr(str);
			}else{
				str = defaultValue;
			}
		}
		return str;
	}
	
	/**
	 * 字符串增强过滤处理
	 * <p>将字符串中除了中英文数字的字符替换掉</p>
	 * @author ljl
	 * @createDate 2017-07-07 09:55:06
	 * @param str
	 * @return
	 */
	public static String replaceSpecStr(String str){
		
		String regx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
		return replaceSpecStr(str, regx, "");
	}
	
	/**
	 * 字符串过滤处理
	 * <p>根据正则表达式替换掉</p>
	 * @author ljl
	 * @createDate 2017-07-07 09:55:06
	 * @param str 要处理的字符串
	 * @param regx 正则表达式
	 * @param replaceStr 替换内容
	 * @return 返回处理后的字符串
	 */
	public static String replaceSpecStr(String str, String regx, String replaceStr){
		
		if(V.isEmpty(replaceStr)){
			replaceStr = "";
		}
		
		if(!V.isEmpty(str)){
			Pattern p = Pattern.compile(regx);
		    Matcher m = p.matcher(str);
		    str = m.replaceAll(replaceStr).trim();
		}
		return str;
	}
	
}
