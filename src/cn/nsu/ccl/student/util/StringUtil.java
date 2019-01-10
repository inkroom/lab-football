package cn.nsu.ccl.student.util;

import net.sf.json.JSONArray;

/**
 * Created by Administrator on 2017/4/5.
 * 字符串工具类
 */
public class StringUtil {

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 检查一个数组是否全部不为null
     *
     * @param objects 需要判断的数组
     * @return 有一个为null返回true，全部不为null返回false
     */
    public static boolean isNull(Object[] objects) {
        if (objects == null)
            return true;
        for (Object item :
                objects) {
            if (item == null)
                return true;
        }
        return false;
    }

    /**
     * 判断一个字符串是否是json数组
     * @param value 是数组，则返回构造的JSONArray对象，如果为null或者空字符串，则返回一个new JSONArray()对象
     *              如果不是空字符串，但是不能解析成json数组，则返回null
     * @return
     */
    public static JSONArray isJsonArray(String value) {
        try {
            if (value == null || value.equals(""))
                return new JSONArray();
            return JSONArray.fromObject(value);
        } catch (Exception e) {
            return null;
        }
    }

}
