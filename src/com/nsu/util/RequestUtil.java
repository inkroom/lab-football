package com.nsu.util;

import com.nsu.common.Constants;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/1/10
 * @Time 10:25
 * @Descorption
 */
public class RequestUtil {
    /**
     * 从session中获取值用户的登录的基本信息
     * @author ljl
     * @createDate 2017-04-14 14:20:52
     * @param session
     * @param key
     * @return
     */
    public static String getAccountInfoInSession(HttpSession session, String key){
        if(session==null || key==null || session.getAttribute(Constants.LOGIN_USER)==null){
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
        if(userMap == null || userMap.isEmpty()){
            return null;
        }else{
            if(V.checkEmpty(userMap.get(key)) ){
                return null;
            }else{
                return userMap.get(key).toString();
            }
        }
    }
}
