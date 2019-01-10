package cn.inkroom.web.money.gate.controllers.prodAndTech;

import cn.inkroom.web.money.gate.beans.prodandtech.ProductSetBean;
import cn.inkroom.web.money.gate.beans.prodandtech.TechnicalSetBean;
import cn.inkroom.web.money.gate.services.prodandtech.ProductService;
import cn.inkroom.web.money.gate.services.prodandtech.ShowPAndTService;
import cn.inkroom.web.money.gate.services.prodandtech.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @author ChenGang
 * @Title: showPAndTController
 * @Package cn.inkroom.web.money.gate.controllers.prodAndTech
 * @Description:@type:技术或者产品类型 1或者2，
 * id 查看的产品id
 * @date 2018/3/14 0014 下午 4:17
 **/
@Controller
public class ShowPAndTController {
    @Autowired
    ShowPAndTService showPAndTService;
    @Autowired
    private ProductService proser;
    @Autowired
    private TechnicalService teser;


    @RequestMapping("ProdAndTech/proandte/signel")
    public ModelAndView ProdAndTech(ModelAndView model, int type, int id) {
        ArrayList<ProductSetBean> setprolist ;
        ArrayList<TechnicalSetBean> telist ;
        telist = teser.getSettech();
        setprolist = proser.getsetpro();
        int te_set = telist.get(0).getTs_id();
        model.addObject("pr_set", telist.get(0).getTs_id());
        model.addObject("settech", telist);
        model.addObject("te_set", te_set);
        model.addObject("type", 1);
        model.addObject("setproduct", setprolist);
        model.addObject("set", 1);
        model.addObject("type2", type);
        model.addObject("id2", id);
        model.setViewName("prodAndTech/prodAndTech");
        try {
            int setNum = (int)showPAndTService.ShowPAndTService(type,id).get("setType");
            model.addObject(     "setType",setNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;

    }

    @RequestMapping("showProAndTech")
    public String showProAndTech(Model m, int type, int id) {
        String htmlPage = null;
        try {

            htmlPage = (String) showPAndTService.ShowPAndTService(type, id).get("html");
        } catch (Exception e) {

            e.printStackTrace();
            htmlPage = "数据错误请联系管理员";
        }
        m.addAttribute("htmlPage", htmlPage);
        return "prodAndTech/superChange";
    }
}
