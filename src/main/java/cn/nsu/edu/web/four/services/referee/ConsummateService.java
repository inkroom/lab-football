package cn.nsu.edu.web.four.services.referee;

import cn.nsu.edu.web.four.daos.jdbc.referee.ConsummateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsummateService {
    @Autowired
    private ConsummateDao dao;

    public int consummate(String name, String idCard, String phone, long refId) {
        try {
            return dao.consummate(name, idCard, phone, refId);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
