package cn.inkroom.web.money.gate.controllers.backstage;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductBean;
import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.services.prodandtech.ProductService;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backstage/product")
public class BackstageProductController {

    @Autowired
    private ProductService prodser;

    @RequestMapping("/index")
    public ModelAndView  index(ModelAndView model, @RequestParam("flag")int flag, @RequestParam("page")int page, @RequestParam(value = "find",defaultValue = "0")int find, @RequestParam(value="findname",defaultValue = "name")String findname,@RequestParam(value = "findset",defaultValue = "0")int findset){
        ArrayList<ProductBean> allprod=new ArrayList<ProductBean>();
        ArrayList<ProductSetBean>setprod=prodser.getsetpro();
        if(find==1){
            allprod=prodser.find(findname);
        }else if(findset!=0){
           allprod=prodser.getpro(findset);
        }else{
            allprod = prodser.allprod();
        }
        ArrayList<ProductBean> cutprod=new ArrayList<ProductBean>();
        int maxsize;
        if(allprod.size()%14==0&&(allprod.size()!=0)){
            maxsize=allprod.size()/14;
        }else{
            maxsize=allprod.size()/14+1;
        }
        if(allprod.size()==0)maxsize=0;
        if(flag==-1)page--;
        if(flag==1)page++;
        if(page<1){
            page=1;
        }
        if(page>maxsize&&maxsize!=0)
        {
            page=maxsize;
        }
        for(int i=(page-1)*14;i<page*14&&i<allprod.size();i++){
            cutprod.add(allprod.get(i));
        }
        if(find==1){
            model.addObject("find",1);
            model.addObject("findname",findname);
        }else{
            model.addObject("find",0);
        }
        model.addObject("prods",cutprod);
        model.addObject("maxpage",maxsize);
        model.addObject("page",page);
        model.addObject("findset",findset);
        model.addObject("setprod",setprod);
        model.setViewName("backstage/ProdEdit");

        return model;
    }

    @RequestMapping("deletetech")
    @ResponseBody
    public Map delete(@RequestParam("id")int pr_id){
        Map map=new HashMap();
        try {
            prodser.deleteprod(pr_id);
            map.put("flag",200);
        }catch (Exception e){
            map.put("flag",500);
            return  map;
        }

        return map;
    }

    @RequestMapping("addprod")
    public ModelAndView addtech(ModelAndView model,@RequestParam(value = "pr_id",defaultValue = "0")int pr_id)
    {
        boolean isupdate=false;
        ArrayList<ProductSetBean>setprod=prodser.getsetpro();
        model.addObject("setprod",setprod);
        model.addObject("isupdate",isupdate);
        model.addObject("pr_id",pr_id);
        model.setViewName("backstage/addProd");
        return model;
    }
    @RequestMapping("updateprod")
    public ModelAndView updateprod(ModelAndView model,@RequestParam("pr_id")int pr_id)
    {
        boolean isupdate=true;
        ArrayList<ProductSetBean>setprod=prodser.getsetpro();
        model.addObject("setprod",setprod);
        model.addObject("pr_id",pr_id);
        model.addObject("isupdate",isupdate);
        model.setViewName("backstage/addProd");
        return model;
    }

    @RequestMapping("upshowprod")
    @ResponseBody
    public ProductBean upshowtech(@RequestParam("pr_id")int pr_id){
        ProductBean prod=new ProductBean();
        try {
            prod=prodser.getproduct(pr_id);

        }catch (Exception e){
            e.printStackTrace();
             prod.setPr_id(0);
            return  prod;
        }
        return prod;

    }


    @RequestMapping("addset")
    @ResponseBody
    public Map addset(@RequestParam("setname")String setname){
        Map map=new HashMap();
        try {
            prodser.addset(setname);
            map.put("flag",200);
        }catch (Exception e){
            map.put("flag",500);
            e.printStackTrace();
            return map;
        }
        return map;
    }


    @RequestMapping("deleteset")
    @ResponseBody
    public Map deleteset(@RequestParam("ts_id")int ts_id){
        Map map=new HashMap();
        try{
            prodser.deleteset(ts_id);
            map.put("flag",200);
        }catch (Exception e){
            map.put("flag",500);
            e.printStackTrace();
            return map;

        }

        return map;
    }


    @RequestMapping("addproduct")
    @ResponseBody
    public Map addproduct(@RequestParam(value = "pr_id",defaultValue = "0")int pr_id,@RequestParam("pr_name")String pr_name,@RequestParam("content")String pr_html,@RequestParam(value = "pr_photo",defaultValue = "null")String pr_photo,@RequestParam("pr_set")int pr_set,@RequestParam("pr_date")Date pr_date,@RequestParam("isupdate")boolean isupdate){
        Map map=new HashMap();
        if(!isupdate){
            try{
                prodser.addproduct(pr_name,pr_html,pr_photo,pr_set,pr_date);
                map.put("flag",200);
            }catch (Exception e){
                map.put("flag",500);
                e.printStackTrace();
                return map;

            }


        }else{
            try{
                prodser.updateprod(pr_id,pr_name,pr_html,pr_photo,pr_set,pr_date);
                map.put("flag",201);
                map.put("pr_id",pr_id);
            }catch (Exception e){
                map.put("flag",501);
                map.put("pr_id",pr_id);
                e.printStackTrace();
                return map;

            }

        }

        return map;


    }

    @Value("${upload.image.base.path}")
    String basePath;
    @ResponseBody
    @RequestMapping("upload")
    public Map Temp(String image, HttpServletRequest request, HttpSession session) {
        Map map=new HashMap();
        try {
            int id=prodser.getnextid();
            String path=basePath+"product"+File.separator +id+".jpg";
            String sqlpath="product"+File.separator +id+".jpg";
            File file = base64ToFile(path, image);
            map.put("flag",200);
            map.put("name",sqlpath);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", 500);

        }

       return map;
    }
    public File base64ToFile(String fullPath, String base64) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();

        byte[] b = decoder.decodeBuffer(base64);
        for (int i = 0; i < b.length; i++) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OutputStream out = new FileOutputStream(fullPath);
        out.write(b);
        out.flush();
        out.close();

        return new File(fullPath);
    }


}
