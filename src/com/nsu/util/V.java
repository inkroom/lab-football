package com.nsu.util;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.nsu.util.jedis.JedisClient;

/**
 * 验证工具类
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
	 * 校验是否是全为简体中文、英文、数字、逗号、句号、感叹号、顿号
	 * @author ljl
	 * @create_date 2017/3/13  17:18:36
	 * @param str 要校验的字符串
	 * @param length 字符串最大长度
	 * @return Returns true if It is all correct character, otherwise it returns false
	 */
	public static boolean checkCHSAndENOrPeriodOrComma(String str, int length){
		String regExpSpace = "^[\u4E00-\u9FA5A-Za-z0-9\\,\\。\\，\\!\\！\\、\\.\\s]+$";
		if(str != null && str.length() > 0 && str.length() < length){
			if(regularVerification(regExpSpace, str) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验是否是全为简体中文、英文、数字
	 * @author ljl
	 * @create_date 2017/3/13  17:18:36
	 * @param str 要校验的字符串
	 * @param length 字符串最大长度
	 * @return Returns true if It is all correct character, otherwise it returns false
	 */
	public static boolean checkCHSAndENAndNumber(String str, int length){
		String regExpSpace = "^[\u4E00-\u9FA5A-Za-z0-9]+$";
		if(str != null && str.length() > 0 && str.length() <= length){
			if(regularVerification(regExpSpace, str) == true){
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验是否是一个正整数
	 * @author ljl
	 * @create_date 2017/3/13  16:59:06
	 * @param number  要校验的正整数
	 * @param length  正整数的最大长度
	 * @return Returns true if it is a integer, otherwise it returns false
	 */
	public static boolean checkPositiveInteger(String number, int length){
		
		String regExp = "^(0|[1-9][0-9]*)$";
		if(number != null && number.length() > 0 && number.length()<=length){
			if(regularVerification(regExp, number) == true){
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验是否是一个正实数
	 *    小数位数最大位数是11位
	 *    注意：可能还有些bug，但目前还没发现
	 * @author ljl
	 * @create_date 2017/3/13  17:05:08
	 * @param number 要验证的数字
	 * @param length 实数的整数部分的长度
	 * @param decimalPlaces 小数点后保留的位数
	 * @return Returns true if it is a real number, otherwise it returns false
	 */
	public static boolean checkRealNumber(String number, int length, int decimalPlaces){

		//验证整数部分只有一位的情况
		String regExpOneSizeInHead = "^[0-9]{1}\\.[0-9]{1,"+decimalPlaces+"}$";
		//验证整数部分以非0开头的情况
		String regExp = "^[1-9][0-9]+\\.[0-9]{1,"+decimalPlaces+"}$";
		int pointIndex = number.lastIndexOf(".");
		if(pointIndex == -1){
			//直接验正整数
			if(checkPositiveInteger(number, length) == true){
				
				return true;
			}else{
				
				return false;
			}
		}
		if(number != null && pointIndex > 0 && pointIndex <= length && decimalPlaces >= 0 && decimalPlaces < 12){
			//验证整数部分只有一位的情况
			if(regularVerification(regExpOneSizeInHead, number) == true){
				
				return true;
			}
			//验证整数部分有一位以上的情况
			if(regularVerification(regExp, number) == true){
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验是否是全为中文字符
	 * @author ljl
	 * @create_date 2017/3/13  17:14:01
	 * @param str 要校验的字符串
	 * @return Returns true if It is all simplified Chinese, otherwise it returns false
	 */
	public static boolean checkCHS(String str){
		String regExp = "^[\u4e00-\u9fa5]{1,}$";
		if(str != null && str.length() > 0){
			if(regularVerification(regExp, str) == true){
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
     * 校验11位手机号码
     * @author ljl
     * @createDate 2017-04-11 15:00:43
     * @param phone
     * @return
     */
    public static boolean checkPhone(Object phone){
    	String regExpPhone = "^[1]{1}[2-578]{1}[0-9]{9}$";
    	if(checkEmpty(phone) == true){
    		return false;
    	}else{
    		if(checkPositiveInteger(phone.toString(), 11) == false 
    				|| regularVerification(regExpPhone, phone.toString()) == false){
    			return false;
    		}
    		return true;
    	}
    	
    }
    
    /**
     * 校验是否是一个中文名字或英文名字
     * @author ljl
     * @createDate 2017-04-13 10:56:44
     * @param name 要验证的名字
     * @param length 名字最大长度
     * @return 正确的名字返回true，否则返回false
     */
    public static boolean checkPersonName(String name, int length){
    	
    	String regExpName = "^[\u4e00-\u9fa5a-zA-Z]+[\\·]{0,1}[\u4e00-\u9fa5a-zA-Z]*$";
		if(checkEmpty(name)==true || name.length() > length || regularVerification(regExpName, name) == false){
			return false;
		}else{
			return true;
		}
    }
    
    
    
    /**
     * 判断是否是一张图片
     * @author ljl
     * @createDate 2017-04-13 10:18:17
     * @param imageFile
     * @return 是一张图片返回true,否则返回false
     */
    public static boolean isImage(File imageFile) {  
        if (!imageFile.exists()) {  
            return false;  
        }  
        Image img = null;  
        try {  
            img = ImageIO.read(imageFile);  
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {  
                return false;  
            }  
            return true;  
        } catch (Exception e) {  
            return false;  
        } finally {  
            img = null;  
        }  
    } 
    
    /**
	 * 获取图片格式
	 * 只能验证 jpg, png
	 * @author ljl
	 * @create_date 2017/3/16  10:09:51
	 * @param fileName
	 * @return 获取成功返回文件格式，否则返回null
	 */
	public static String findFileTypes(String fileName){
		int extIndex = fileName.lastIndexOf(".")+1;
		String ext = fileName.substring(extIndex).toLowerCase();
		String[] fileType = {"jpg", "png"};
		boolean tmp = false;
		for(int i = 0 ; i < 2; i++){
			if(ext.equals(fileType[i])){
				tmp = true;
				break;
			}
		}
		if(tmp == true){
			return ext;
		}else{
			return null;
		}
	}

	/**
	 * 邮箱验证
	 * @author ljl
	 * @createDate 2017-04-25 16:20:50
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email, int length) {
		String regExpName = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		if(checkEmpty(email)==true || email.length() > length || regularVerification(regExpName, email) == false){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 验证18位身份证号码
	 * [特别说明：只能验证18位的身份证]
	 * @author ljl
	 * @createDate 2017-05-03 18:40:46
	 * @param idCard 验证的身份证号
	 * @return 身份证号合法返回true,否则返回false
	 */
	public static boolean checkIDCard(Object idCard){
		String regExpName = "^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$";
		if(checkEmpty(idCard)==true || idCard.toString().length() != 18 || regularVerification(regExpName, idCard.toString()) == false){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 取出redis中的验证码进行验证
	 * @author ljl
	 * @createDate 2017-06-09 10:15:01
	 * @param jedisClient redis Java客户端
	 * @param accounType 账户类型
	 * @param VerificationType 验证类型：1 手机    2 邮箱
	 * @param mobile 手机号
	 * @param email 邮箱
	 * @param randomCode 验证码
	 * @return 返回值Map的key值说明：
	 * 				result  -1：验证码验证次数超过5次；0  验证码错误； 1 验证码正确  -2 验证码长度不对；
	 * 				resultMsg 错误提示
	 */
	public static Map<String, String> verificationCode(JedisClient jedisClient, int accounType, int VerificationType, String mobile, String email, String randomCode){

		Map<String, String> resultMap = new HashMap<String, String>();
		if(randomCode.length() != 6 ){
			resultMap.put("result", "-2");
			resultMap.put("resultMsg", "请输入6位验证码");
			return resultMap;
		}
		if(VerificationType == 1){
        	//手机
        	if (jedisClient.get(accounType+"web"+mobile+"num") != null){
        		if (Integer.parseInt(jedisClient.get(accounType+"web"+mobile+"num"))>5){
        			//操作次数大于5次，销毁
                    jedisClient.del(accounType+"web"+mobile);
                    jedisClient.del(accounType + "web" + mobile + "num");
                    
                    resultMap.put("result", "-1");
                    resultMap.put("resultMsg", "验证码错误");
                    return resultMap;
                }
        	}
        	if (randomCode.equals(jedisClient.get(accounType+"web"+mobile))) {
        		
        		resultMap.put("result", "1");
                resultMap.put("resultMsg", "验证通过");
                return resultMap;
             } else {
                 if (jedisClient.get(accounType+"web"+mobile+"num") != null){
                     jedisClient.incr(accounType + "web" + mobile + "num");
                 }
                 resultMap.put("result", "0");
                 resultMap.put("resultMsg", "验证码错误");
                 return resultMap;
             }
        }else{
        	//邮箱
        	if (jedisClient.get(email+"num") != null){
        		if (Integer.parseInt(jedisClient.get(email+"num"))>5){
        			//操作次数大于5次，销毁
                    jedisClient.del(email);
                    jedisClient.del(email+"num");
                    resultMap.put("result", "-1");
                    resultMap.put("resultMsg", "验证码错误");
                    return resultMap;
                }
        	}
        	if (randomCode.equals(jedisClient.get(email))) {
        		resultMap.put("result", "1");
                resultMap.put("resultMsg", "验证通过");
                return resultMap;
             } else {
                 if (jedisClient.get(email+"num") != null){
                     jedisClient.incr(email+"num");
                 }
                 resultMap.put("result", "0");
                 resultMap.put("resultMsg", "验证码错误");
                 return resultMap;
             }
        }
	}
	
	/**
	 * @Description: 去掉小数点后多余的0
	 * @author 侯润达
	 * @create_date 2017年6月14日 上午11:39:35
	 * @return 去除多余的”0“后的值
	 */
		public static String subZeroAndDot(String s){  
	        if(s.indexOf(".") > 0){  
	            s = s.replaceAll("0+?$", "");//去掉多余的0  
	            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
	        }  
	        return s;  
	    }  
	
}
