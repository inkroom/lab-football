package cn.inkroom.web.money.gate.daos.jdbc.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;


public interface ProductDao {
    public ArrayList<ProductBean> selpro(@Param("pr_set")int pr_set);
    public ArrayList<ProductSetBean> setpro();
    public ArrayList<ProductBean> allprod();
    public String setname(@Param("pr_set")int pr_set);
    public void deleteprod(@Param("pr_id")int pr_id);
    public ArrayList<ProductBean>find(@Param("pr_name")String pr_name);
    public void addset(@Param("ps_name")String ps_name);
    public void deleteset(@Param("ps_id")int ps_id);
    public void addproduct(@Param("pr_name")String pr_name, @Param("pr_html")String pr_html,@Param("pr_photo")String pr_photo,@Param("pr_set")int pr_set, @Param("pr_date")Date pr_date);
    public void  updateprod(@Param("pr_id")int pr_id,@Param("pr_name")String pr_name,@Param("pr_html")String pr_html,@Param("pr_photo")String pr_photo,@Param("pr_set")int pr_set,@Param("pr_date")Date pr_date);
    public ProductBean getproduct(@Param("pr_id")int pr_id);
    public int getnextid();
}
