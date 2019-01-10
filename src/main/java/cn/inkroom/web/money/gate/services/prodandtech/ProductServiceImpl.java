package cn.inkroom.web.money.gate.services.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.daos.jdbc.prodandtech.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao prodao;

    public ArrayList<ProductBean> getpro(int pr_set){
        ArrayList<ProductBean> prolist=new ArrayList<ProductBean>();
        prolist=prodao.selpro(pr_set);
        return prolist;

    }

    @Override
    public ArrayList<ProductSetBean> getsetpro() {
        ArrayList<ProductSetBean> setprolist=new ArrayList<ProductSetBean>();
        setprolist=prodao.setpro();
        return setprolist;
    }

    @Override
    public ArrayList<ProductBean> allprod() {
        ArrayList<ProductBean> allprod=prodao.allprod();
        return  allprod;
    }

    @Override
    public void deleteprod(int pr_id) {
        prodao.deleteprod(pr_id);
    }

    @Override
    public ArrayList<ProductBean> find(String pr_name) {
        ArrayList<ProductBean> findprod=prodao.find(pr_name);
        return findprod;
    }

    @Override
    public void deleteset(int ps_id) {
        prodao.deleteset(ps_id);
    }

    @Override
    public void addset(String ps_name) {
        prodao.addset(ps_name);

    }

    @Override
    public void addproduct(String pr_name, String pr_html,String pr_photo,int pr_set, Date pr_date) {
        prodao.addproduct(pr_name,pr_html,pr_photo,pr_set,pr_date);
    }

    @Override
    public void updateprod(int pr_id, String pr_name, String pr_html, String pr_photo, int pr_set, Date pr_date) {
        prodao.updateprod(pr_id,pr_name,pr_html,pr_photo,pr_set,pr_date);
    }

    @Override
    public ProductBean getproduct(int pr_id) {
        ProductBean prod=prodao.getproduct(pr_id);
        return prod;
    }

    @Override
    public int getnextid() {
        int nextid=prodao.getnextid();
        return nextid;
    }


}
