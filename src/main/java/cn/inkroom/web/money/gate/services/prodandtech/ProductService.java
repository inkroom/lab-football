package cn.inkroom.web.money.gate.services.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;

import java.util.ArrayList;
import java.util.Date;

public interface ProductService {
    public ArrayList<ProductBean> getpro(int pr_set);
    public ArrayList<ProductSetBean> getsetpro();
    public ArrayList<ProductBean> allprod();
    public void deleteprod(int pr_id);
    public ArrayList<ProductBean>find(String pr_name);
    public void deleteset(int ps_id);
    public void addset(String ps_name);
    public void addproduct(String pr_name, String pr_html,String pr_photo, int pr_set, Date pr_date);
    public void updateprod(int pr_id,String pr_name,String pr_html,String pr_photo,int pr_set,Date pr_date);
    public ProductBean getproduct(int pr_id);
    public int getnextid();
}
