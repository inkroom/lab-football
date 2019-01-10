package com.nsu.util.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
	
	
	public static boolean checkNumber(String number,double maxNum,double minNum,int place) {
		double numberD = 0;
		try {
			numberD = Double.parseDouble(number); 
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		if (numberD > maxNum || numberD < minNum) {
			return false;
		}else {
			String patternString = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,"+place+"})?$";
			Pattern pattern=Pattern.compile(patternString);
			Matcher match=pattern.matcher(number);
			if (match.matches()) {
				return true;
			}else {
				return false;
			}
		}	
	}
	
	
	public static void main(String[] ge){
		System.out.println(checkNumber("12",10000,0,3));
	}
	
}
