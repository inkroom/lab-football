package cn.inkroom.web.money.gate.services.prodandtech;

import cn.inkroom.web.money.gate.daos.jdbc.prodandtech.ShowPAndTDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ChenGang
 * @Title: ShowPAndTServiceImpl
 * @Package cn.inkroom.web.money.gate.services.prodandtech
 * @Description:
 * @date 2018/3/14 0014 下午 4:32
 **/
@Service
public class ShowPAndTServiceImpl implements ShowPAndTService {
    @Autowired
    ShowPAndTDao showPAndTDao;

    @Override
    public Map<String, Object> ShowPAndTService(int type, int id) throws Exception {
        Map<String, Object> htmlMap = null;
        if (type == 1) {
            htmlMap = showPAndTDao.getProt(id);
        } else if (type == 2) {
            htmlMap = showPAndTDao.getTech(id);
        } else {
            return htmlMap;
        }

        return htmlMap;
    }
}
