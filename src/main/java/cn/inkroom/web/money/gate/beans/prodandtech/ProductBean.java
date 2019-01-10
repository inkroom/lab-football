package cn.inkroom.web.money.gate.beans.prodandtech;

import java.util.Date;

public class ProductBean {
    private int pr_id;
    private String pr_name;
    private String pr_html;
    private String pr_photo;
    private int pr_set;
    private Date pr_date;
    private String pr_setname;

    public String getPr_setname() {
        return pr_setname;
    }

    public void setPr_setname(String pr_setname) {
        this.pr_setname = pr_setname;
    }

    public Date getPr_date() {
        return pr_date;
    }

    public void setPr_date(Date pr_date) {
        this.pr_date = pr_date;
    }

    public String getPr_html() {
        return pr_html;
    }

    public void setPr_html(String pr_html) {
        this.pr_html = pr_html;
    }

    public int getPr_id() {
        return pr_id;
    }

    public void setPr_id(int pr_id) {
        this.pr_id = pr_id;
    }

    public String getPr_name() {
        return pr_name;
    }

    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

    public String getPr_photo() {
        return pr_photo;
    }

    public void setPr_photo(String pr_photo) {
        this.pr_photo = pr_photo;
    }

    public int getPr_set() {
        return pr_set;
    }

    public void setPr_set(int pr_set) {
        this.pr_set = pr_set;
    }
}
