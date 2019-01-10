package com.nsu.util.core;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/21
 * @Time 15:05
 * @Descorption
 */
public class UrlUtil {
    public static String getNumber(String url) {
        return String.valueOf(url.charAt(url.lastIndexOf(".action") - 1));
    }
}
