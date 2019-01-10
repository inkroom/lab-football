package cn.edu.nsu.lib.services.chat.impl;

import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.dao.chat.SignDao;
import cn.edu.nsu.lib.services.chat.ISignService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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
    public int insertStudent(Map<String, Object> user) throws Exception {
        Log log = LogFactory.getLog(getClass());
        log.info("user = " + user);
        return signDao.insertStudent(user.get(Constants.KEY_MAP_USERNAME).toString(), new Date(), (Integer) user.get(Constants.KAY_MAP_LAB_ID));
    }
}
