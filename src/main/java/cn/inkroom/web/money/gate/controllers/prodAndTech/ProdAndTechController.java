package cn.inkroom.web.money.gate.controllers.prodAndTech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import cn.inkroom.web.money.gate.services.prodandtech.ProductService;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("ProdAndTech/")
public class ProdAndTechController {

    @Autowired
    private ProductService proser;
    @Autowired
    private TechnicalService teser;

    @RequestMapping("proandte")
    public ModelAndView ProdAndTech(ModelAndView model, @RequestParam(value="type",defaultValue="1")int type,@RequestParam("set")int set){
        ArrayList<ProductSetBean> setprolist;
        ArrayList<TechnicalSetBean>telist;
        telist=teser.getSettech();
        setprolist=proser.getsetpro();
        int  te_set=telist.get(0).getTs_id();
        model.addObject("pr_set",telist.get(0).getTs_id());
        model.addObject("settech",telist);
        model.addObject("te_set", te_set);
        model.addObject("type",type);
        model.addObject("setproduct",setprolist);
        model.addObject("set",set);
        model.setViewName("prodAndTech/prodAndTech");
        return model;

    }

    @RequestMapping("products")
    public ModelAndView  Products(ModelAndView model, @RequestParam("pr_set")int pr_set,@RequestParam("pr_page")int pr_page,@RequestParam("flag")int flag)
    {
        ArrayList<ProductBean> prolist;
        ArrayList<ProductBean> cuprolist=new ArrayList<ProductBean>();
        prolist=proser.getpro(pr_set);
        int maxsize;
        if(prolist.size()%8==0){
            maxsize=prolist.size()/8;
        }else{
            maxsize=prolist.size()/8+1;
        }
        if(flag==-1)pr_page--;
        if(flag==1)pr_page++;
        if(pr_page<1){
            pr_page=1;
        }
        if(pr_page>maxsize&&maxsize!=0)
        {
            pr_page=maxsize;
        }
        for(int i=(pr_page-1)*8;i<pr_page*8&&i<prolist.size();i++){
            cuprolist.add(prolist.get(i));
           }
        model.addObject("pr_set",pr_set);
        model.addObject("prolist",cuprolist);
        model.addObject("maxpage",maxsize);
        model.addObject("cupage",pr_page);
        model.setViewName("prodAndTech/product");
        return model;
    }




    @RequestMapping("technicals")
    public ModelAndView Technicals(ModelAndView model,@RequestParam("te_set")int te_set,@RequestParam("page")int page,@RequestParam("flag")int flag){
        ArrayList<TechnicalBean>techs=teser.getchnicals(te_set);
        ArrayList<TechnicalBean>curtechs=new ArrayList<TechnicalBean>();
        int maxpage;
        if(techs.size()%9==0){
            maxpage=techs.size()/9;
        }else{
            maxpage=techs.size()/9+1;
        }
        if(flag==1){
            page++;
        }
        if(flag==-1){
            page--;
        }
      if(page<1){
            page=1;
      }
      if(page>maxpage&&maxpage!=0){
          page=maxpage;
      }
      for(int i=(page-1)*8;i<page*8&&i<techs.size();i++){
          curtechs.add(techs.get(i));
      }
        model.addObject("te_set",te_set);
        model.addObject("techs",curtechs);
        model.addObject("maxpage",maxpage);
        model.addObject("page",page);
        model.setViewName("prodAndTech/technology");
        return model;
    }





}
