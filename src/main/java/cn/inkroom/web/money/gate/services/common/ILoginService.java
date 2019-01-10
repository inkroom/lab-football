package cn.inkroom.web.money.gate.services.common;

import java.util.Map;

public interface ILoginService {

    //通过 用户的用户名获取加盐md5加密的密码 和 用户密码比较
    public Map<String, Object> checkAccount(String username, String password);

}
