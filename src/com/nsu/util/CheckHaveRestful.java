package com.nsu.util;

import java.lang.annotation.Annotation;

import org.springframework.web.method.HandlerMethod;

import com.nsu.common.annotation.RestfulUrlAnnotation;

public class CheckHaveRestful {
	public static String checkHaveRestful(HandlerMethod handlerMethod){
		Annotation[] annotations = handlerMethod.getMethod().getAnnotations();
		for (Annotation annotation : annotations) {
			try {
				RestfulUrlAnnotation re = (RestfulUrlAnnotation) annotation;
				return re.refulUrl();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
