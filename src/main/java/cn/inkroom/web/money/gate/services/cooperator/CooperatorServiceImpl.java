package cn.inkroom.web.money.gate.services.cooperator;

import cn.inkroom.web.money.gate.beans.cooperator.CooperatorBean;
import cn.inkroom.web.money.gate.daos.jdbc.cooperator.CooperatorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CooperatorServiceImpl implements CooperatorService {

    @Autowired
    private CooperatorDao cooperatordao;

    private Logger log = LoggerFactory.getLogger(getClass());

    public List<CooperatorBean> getCooperatorList() {
        try {
            return cooperatordao.getCooperatorList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CooperatorBean getCooperator(int id) {
        try {
            return cooperatordao.getCooperator(id,1);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int delCooperator(int id) {
        try {
            return cooperatordao.deleteCooperator(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateCooperator(String name, String content, Integer id) {
        CooperatorBean bean = new CooperatorBean();
        bean.setContent(content);
        bean.setName(name);
        bean.setId(id);
        try {
            return cooperatordao.updateCooperator(bean);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int addCooperator(String name, String content) {
        try {
            CooperatorBean bean = new CooperatorBean();
            bean.setContent(content);
            bean.setName(name);
            return cooperatordao.addCooperator(bean);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


}
