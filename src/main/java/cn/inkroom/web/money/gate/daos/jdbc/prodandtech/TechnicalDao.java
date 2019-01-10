package cn.inkroom.web.money.gate.daos.jdbc.prodandtech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

public interface TechnicalDao {
    public ArrayList<TechnicalSetBean>settech();
    public  ArrayList<TechnicalBean>techs(@Param("te_set")int te_set);
    public void deletetech(@Param("te_id")int te_id);
    public ArrayList<TechnicalBean>alltechs();
    public String techname(@Param("te_set")int te_set);
    public ArrayList<TechnicalBean>find(@Param("te_name")String te_name);
    public void addset(@Param("ts_name")String ts_name);
    public void deleteset(@Param("ts_id")int ts_id);
    public void addtechnical(@Param("te_name")String te_name,@Param("te_html")String te_html,@Param("te_set")int te_set,@Param("te_date")Date te_date);
    public TechnicalBean gettechnical(@Param("te_id")int te_id);
    public void updatetech(@Param("te_id")int te_id,@Param("te_name")String te_name,@Param("te_html")String te_html,@Param("te_set")int te_set,@Param("te_date")Date te_date);


}
