package cn.edu.nsu.lib.services.common;

/**
*@auther ChenGang
*@说明：修改密码service层
**/
public interface ModifyPasswdService {

    String updatePasswd(String username, String password) throws Exception;

}
