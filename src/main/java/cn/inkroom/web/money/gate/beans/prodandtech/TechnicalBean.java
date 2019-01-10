package cn.inkroom.web.money.gate.beans.prodandtech;

import java.util.Date;

public class TechnicalBean {
    private int te_id;
    private String te_name;
    private String te_html;
    private int te_set;
    private Date  te_date;
    private String te_setname;

    public String getTe_setname() {
        return te_setname;
    }

    public void setTe_setname(String te_setname) {
        this.te_setname = te_setname;
    }

    public Date getTe_date() {
        return te_date;
    }

    public void setTe_date(Date te_date) {
        this.te_date = te_date;
    }

    public String getTe_html() {
        return te_html;
    }

    public void setTe_html(String te_html) {
        this.te_html = te_html;
    }

    public int getTe_id() {
        return te_id;
    }

    public void setTe_id(int te_id) {
        this.te_id = te_id;
    }

    public String getTe_name() {
        return te_name;
    }

    public void setTe_name(String te_name) {
        this.te_name = te_name;
    }

    public int getTe_set() {
        return te_set;
    }

    public void setTe_set(int te_set) {
        this.te_set = te_set;
    }
}
