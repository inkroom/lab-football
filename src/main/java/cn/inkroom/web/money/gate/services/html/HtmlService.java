package cn.inkroom.web.money.gate.services.html;

import cn.inkroom.web.money.gate.beans.common.HtmlBean;
import cn.inkroom.web.money.gate.daos.jdbc.html.HtmlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HtmlService {

    @Autowired
    private HtmlDao dao;


    private Logger log = LoggerFactory.getLogger(getClass());

    public HtmlBean getHtml(int id) {
        try {
            return dao.getUnion(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HtmlBean> getAllTypeHtml(int owner) {
        try {
            return dao.getAllTypeUnion(owner);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HtmlBean> getAllTitleHtml(int owner) {
        try {
            return dao.getAllTitleUnion(owner);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HtmlBean getHtml(int owner, int type) {
        try {
            return dao.getHtml(owner, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public boolean setHtml(int owner, int type, String content, String title, String url,String test) throws Exception {
        log.info("service开始setHtml");
        if (dao.setFlag(owner, type, 1, 0) == 0) {
//            throw new RuntimeException();
        }
        if (dao.addHtml(owner, type, content, title, url) == 0) {
            throw new RuntimeException();
        }
        if ("2".equals(test)){
            throw new RuntimeException();
        }
        log.info("service结束setHtml");
        return true;
    }
}
