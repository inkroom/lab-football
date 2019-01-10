package cn.edu.nsu.lib.services.chat.impl;

import cn.edu.nsu.lib.dao.chat.SignDao;
import cn.edu.nsu.lib.services.chat.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/26
 * @Time 17:43
 * @Descorption 打卡服务
 */
@Service
public class SignService implements ISignService {
    @Autowired
    private SignDao signDao;

    @Override
    public int insertStudent(String username) throws Exception {
        return signDao.insertStudent(username, new Date());
    }
}
