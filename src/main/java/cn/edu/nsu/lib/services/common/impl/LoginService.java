package cn.edu.nsu.lib.services.common.impl;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.dao.common.LoginDao;
import cn.edu.nsu.lib.services.common.ILoginService;
import cn.edu.nsu.lib.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 16:39
 * @Descorption
 */
@Service
public class LoginService implements ILoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Map<String, Object> checkAccount(String username, String password) throws Exception {
        Map<String, Object> map = loginDao.getAccount(username);
        if (map != null) {
            if (EncryptUtil.comparePass(password,
                    map.get(Constants.KEY_MAP_SALT).toString(),
                    map.get(Constants.KEY_MAP_PASSWORD).toString())) {//密码正确
                return map;
            } else {//密码不正确
                return null;
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getUser(String username, int role) throws Exception {
        Map<String, Object> map = null;
        if (role == Authority.Role.STUDENT.ordinal() || role == Authority.Role.MANAGER.ordinal()) {
            map = loginDao.getStudentUser(username);
        } else if (role == Authority.Role.TEACHER.ordinal() || role == Authority.Role.LEADER.ordinal()) {
            map = loginDao.getTeacherUser(username);
        }
        if (map != null) {
            map.put(Constants.KEY_MAP_USERNAME, username);
            map.put(Constants.KEY_MAP_AUTHORITY, role);
        }
        return map;
    }

}
