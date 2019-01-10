package cn.inkroom.web.money.gate.controllers.backstage;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/backstage/technical")
public class BackstageTechnicalController {

    @Autowired
    private TechnicalService techser;

    @RequestMapping("/index")
    public ModelAndView  index(ModelAndView model,@RequestParam("flag")int flag,@RequestParam("page")int page,@RequestParam(value = "find",defaultValue ="0")int find,@RequestParam(value ="findname",defaultValue ="name")String findname,@RequestParam(value = "findset",defaultValue = "0")int findset){
        ArrayList<TechnicalBean> alltechs=new ArrayList<TechnicalBean>();
        ArrayList<TechnicalSetBean>settech=techser.getSettech();

        if(find==1){
            alltechs=techser.findtech(findname);
        }else if(findset!=0){
                 alltechs=techser.getchnicals(findset);
        }else{
            alltechs=techser.getalltechs();
        }
        ArrayList<TechnicalBean> cutechs=new ArrayList<TechnicalBean>();
        int maxsize;
        if(alltechs.size()%14==0&&alltechs.size()!=0){
            maxsize=alltechs.size()/14;
        }else{
            maxsize=alltechs.size()/14+1;
        }
        if(alltechs.size()==0)maxsize=0;
        if(flag==-1)page--;
        if(flag==1)page++;
        if(page<1){
            page=1;
        }
        if(page>maxsize&&maxsize!=0)
        {
            page=maxsize;
        }
        for(int i=(page-1)*14;i<page*14&&i<alltechs.size();i++){
            cutechs.add(alltechs.get(i));
        }
        if(find==1){
            model.addObject("find",1);
            model.addObject("findname",findname);
        }else {
            model.addObject("find",0);
        }
        model.addObject("techs",cutechs);
        model.addObject("maxpage",maxsize);
        model.addObject("page",page);
        model.addObject("settech",settech);
        model.addObject("findset",findset);
        model.setViewName("backstage/TechEdit");

        return model;
    }

    @RequestMapping("deletetech")
    @ResponseBody
    public Map delete(@RequestParam("id")int te_id){
        Map map=new HashMap();
        try {
            techser.deletetech(te_id);
            map.put("flag",200);
        }catch (Exception e){
            map.put("flag",500);
            return  map;
        }

        return map;
    }



    @RequestMapping("addtech")
    public ModelAndView addtech(ModelAndView model,@RequestParam(value = "te_id",defaultValue = "0")int te_id)
    {
        boolean isupdate=false;
        ArrayList<TechnicalSetBean>settech=techser.getSettech();
        model.addObject("settech",settech);
        model.addObject("te_id",te_id);
        model.addObject("isupdate",isupdate);
        model.setViewName("backstage/addTech");
        return model;
    }

    @RequestMapping("updatetech")
    public ModelAndView updatetech(ModelAndView model,@RequestParam("te_id")int te_id)
    {
        boolean isupdate=true;
        ArrayList<TechnicalSetBean>settech=techser.getSettech();
        model.addObject("settech",settech);
        model.addObject("te_id",te_id);
        model.addObject("isupdate",isupdate);
        model.setViewName("backstage/addTech");
        return model;
    }

    @RequestMapping("upshowtech")
    @ResponseBody
    public TechnicalBean upshowtech(@RequestParam("te_id")int te_id){
        TechnicalBean tech=new TechnicalBean();
        try {
             tech=techser.gettechnical(te_id);

        }catch (Exception e){
            e.printStackTrace();
             tech.setTe_id(0);
             return  tech;
        }
       return tech;

    }

    @RequestMapping("addset")
    @ResponseBody
    public Map addset(@RequestParam("setname")String setname){
        Map map=new HashMap();
        try {
            techser.addset(setname);
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
            techser.deleteset(ts_id);
            map.put("flag",200);
        }catch (Exception e){
            map.put("flag",500);
            e.printStackTrace();
            return map;

        }

          return map;
    }

    @RequestMapping("addtechnical")
    @ResponseBody
    public Map addtechnical(@RequestParam(value = "te_id",defaultValue = "0")int te_id,@RequestParam("te_name")String te_name, @RequestParam("content")String te_html, @RequestParam("te_set")int te_set, @RequestParam("te_date")Date te_date){
        Map map=new HashMap();
        if(te_id==0){
            try{
                techser.addtechnical(te_name,te_html,te_set,te_date);
                map.put("flag",200);
            }catch (Exception e){
                map.put("flag",500);
                e.printStackTrace();
                return map;

            }

        }else{
            try{
                techser.updatetech(te_id,te_name,te_html,te_set,te_date);
                map.put("flag",201);
                map.put("te_id",te_id);
            }catch (Exception e){
                map.put("flag",501);
                map.put("te_id",te_id);
                e.printStackTrace();
                return map;

            }

        }

        return map;
    }

}
