package com.nsu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
		String date2 = dateFormat2.format(new Date());
		System.out.println(date2);
	}
}
