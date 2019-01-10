package cn.nsu.edu.web.four.services.referee;

import cn.nsu.edu.web.four.daos.jdbc.referee.LoginDao;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public Map<String, Object> login(String username, String password) throws Exception {
        Map<String, Object> map = loginDao.selectAccount(username);
        if (map == null) return null;
        if (Md5EncryptUtil.comparePass(password, map.get("salt").toString(), map.get("password").toString())) {
            return map;
        }
        return null;
    }


}
