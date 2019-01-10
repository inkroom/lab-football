package cn.inkroom.web.money.gate.services.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import cn.inkroom.web.money.gate.daos.jdbc.prodandtech.TechnicalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TechnicalServiceImpl implements TechnicalService {

    @Autowired
    private TechnicalDao techdao;
    public ArrayList<TechnicalSetBean> getSettech() {
        ArrayList<TechnicalSetBean>telist=new ArrayList<TechnicalSetBean>();
        telist=techdao.settech();
        return telist;
    }


    public ArrayList<TechnicalBean> getchnicals(int te_set) {
        ArrayList<TechnicalBean> techs=techdao.techs(te_set);
        return techs;
    }


    public void deletetech(int te_id) {
        techdao.deletetech(te_id);
    }


    public ArrayList<TechnicalBean> getalltechs() {
        ArrayList<TechnicalBean> alltechs=techdao.alltechs();
        return alltechs;
    }

    @Override
    public ArrayList<TechnicalBean> findtech(String te_name) {
        ArrayList<TechnicalBean> findtech=techdao.find(te_name);
        return findtech;
    }

    @Override
    public void addset(String ts_name) {
        techdao.addset(ts_name);
    }

    @Override
    public void deleteset(int ts_id) {
        techdao.deleteset(ts_id);
    }

    @Override
    public void addtechnical(String te_name, String te_html, int te_set, Date te_date) {
        techdao.addtechnical(te_name,te_html,te_set,te_date);
    }

    @Override
    public TechnicalBean gettechnical(int te_id) {
        TechnicalBean tech=techdao.gettechnical(te_id);
        return  tech;
    }

    @Override
    public void updatetech(int te_id, String te_name, String te_html, int te_set, Date te_date) {
        techdao.updatetech(te_id,te_name,te_html,te_set,te_date);
    }
}
