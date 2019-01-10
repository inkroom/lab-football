package cn.inkroom.web.money.gate.services.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;

import java.util.ArrayList;
import java.util.Date;

public interface TechnicalService {
    public ArrayList<TechnicalSetBean> getSettech();
    public ArrayList<TechnicalBean>getchnicals(int te_set);
    public void deletetech(int te_id);
    public ArrayList<TechnicalBean>getalltechs();
    public ArrayList<TechnicalBean>findtech(String te_name);
    public void addset(String ts_name);
    public void deleteset(int ts_id);
    public void addtechnical(String te_name, String te_html, int te_set, Date te_date);
    public TechnicalBean gettechnical(int te_id);
    public void updatetech(int te_id,String te_name,String te_html,int te_set,Date te_date);
}
