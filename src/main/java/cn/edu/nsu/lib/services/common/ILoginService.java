package cn.edu.nsu.lib.services.common;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 16:36
 * @Descorption
 */
public interface ILoginService {
    Map<String, Object> checkAccount(String username, String password) throws Exception;

    Map<String, Object> getUser(String username, int role) throws Exception;

}
