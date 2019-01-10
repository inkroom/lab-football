package cn.inkroom.web.money.gate.services.common.impl;

import cn.inkroom.web.money.gate.config.BaseStatic;
import cn.inkroom.web.money.gate.daos.jdbc.Common.LoginDAO;
import cn.inkroom.web.money.gate.services.common.ILoginService;
import cn.inkroom.web.money.gate.utils.encrypt.Md5EncryptUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService implements ILoginService{

    @Autowired
    private LoginDAO dao;

    private Log log = LogFactory.getLog(getClass());

    @Override
    public Map<String, Object> checkAccount(String username, String password) {
        Map<String, Object> user;
        user = dao.getuser(username);
        if(!user.isEmpty()){
            //匹配加盐加密的密码
            if(Md5EncryptUtil.comparePass(password,
                    user.get(BaseStatic.KEY_MAP_SALT).toString(),
                    user.get(BaseStatic.KEY_MAP_PASSWORD).toString())){
                return user;
            }else {
                log.info("LoginService密码匹配错误");
                return null;
            }
        }
        log.info("LoginService用户为空");
        return null;
    }
}
